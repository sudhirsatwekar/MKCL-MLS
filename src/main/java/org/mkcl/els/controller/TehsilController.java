/*
 ******************************************************************
File: org.mkcl.els.controller.TehsilController.java
Copyright (c) 2011, sandeeps, MKCL
All rights reserved.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.

 ******************************************************************
 */
package org.mkcl.els.controller;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.mkcl.els.common.editors.DistrictEditor;
import org.mkcl.els.common.editors.StateEditor;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.State;
import org.mkcl.els.domain.Tehsil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The Class TehsilController.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Controller
@RequestMapping("/tehsils")
public class TehsilController extends BaseController {

    /**
     * Index.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "module", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        return "masters/tehsils/module";
    }


    /**
     * Index.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        Grid grid = Grid.findByName("TEHSIL_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/tehsils/list";
    }

    /**
     * _new.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model, final Locale locale) {
        State defaultState = State.findByName(CustomParameter.findByName(
                "DEFAULT_STATE_" + locale.toString().toUpperCase()).getValue());
        Tehsil tehsil = new Tehsil();
        tehsil.setState(defaultState);
        tehsil.setLocale(locale.toString());

        model.addAttribute("states", State.findAll(locale.toString()));
        model.addAttribute("districts", District.findDistrictsByStateId(defaultState.getId()));
        model.addAttribute("tehsil", tehsil);
        return "masters/tehsils/new";
    }

    /**
     * Edits the.
     *
     * @param id the id
     * @param model the model
     * @param locale the locale
     * @return the string
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable final Long id, final ModelMap model, final Locale locale) {
        Tehsil tehsil = Tehsil.findById(id);
        model.addAttribute("states", State.findAll(locale.toString()));
        model.addAttribute("districts", District.findDistrictsByStateId(tehsil.getState().getId()));
        model.addAttribute("tehsil", tehsil);
        return "masters/tehsils/edit";
    }

    /**
     * Creates the.
     *
     * @param tehsil the tehsil
     * @param result the result
     * @param model the model
     * @param state the state
     * @param redirectAttributes the redirect attributes
     * @param locale the locale
     * @return the string
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("tehsil") final Tehsil tehsil,
            final BindingResult result, final ModelMap model,
            @RequestParam final String state,
            final RedirectAttributes redirectAttributes,
            final Locale locale) {
        this.validate(tehsil, result);
        if (result.hasErrors()) {
            model.addAttribute("states", State.findAll(locale.toString()));
            model.addAttribute("districts",
                    District.findDistrictsByStateId(tehsil.getState().getId()));
            model.addAttribute("tehsil", tehsil);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/tehsils/new";
        }
        tehsil.persist();
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("msg", "create_success");
        return "redirect:tehsils/" + tehsil.getId() + "/edit";

    }

    /**
     * Edits the.
     *
     * @param tehsil the tehsil
     * @param result the result
     * @param model the model
     * @param state the state
     * @param redirectAttributes the redirect attributes
     * @param locale the locale
     * @return the string
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String edit(@Valid @ModelAttribute("tehsil") final Tehsil tehsil,
            final BindingResult result, final ModelMap model,
            @RequestParam final String state,
            final RedirectAttributes redirectAttributes,
            final Locale locale) {
        this.validate(tehsil, result);
        if (result.hasErrors()) {
            model.addAttribute("states", State.findAll(locale.toString()));
            model.addAttribute("districts",
                    District.findDistrictsByStateId(tehsil.getState().getId()));
            model.addAttribute("tehsil", tehsil);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/tehsils/edit";
        }
        tehsil.update(tehsil);
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("msg", "create_success");
        return "redirect:tehsils/" + tehsil.getId() + "/edit";

    }

    /**
     * Delete.
     *
     * @param id the id
     * @return the string
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public @ResponseBody boolean delete(@PathVariable final Long id) {
        Tehsil tehsil = Tehsil.findById(id);
        tehsil.remove();
        return true;
    }

    /**
     * Validate.
     *
     * @param tehsil the tehsil
     * @param errors the errors
     */
    private void validate(final Tehsil tehsil, final Errors errors) {
        if (tehsil.getName() != null) {
            if (tehsil.getLocale().equals("en")) {
                String name = tehsil.getName();
                Pattern pattern = Pattern.compile("[A-Za-z ]{1,50}");
                Matcher matcher = pattern.matcher(name);
                if (!matcher.matches()) {
                    errors.rejectValue("name", "Pattern");
                }
                if (name.length() > 100 || name.length() < 1) {
                    errors.rejectValue("name", "Size");

                }
            }
        }
        Tehsil duplicateParameter = Tehsil.findByName(tehsil.getName());
        if (duplicateParameter != null) {
            if (!duplicateParameter.getId().equals(tehsil.getId())) {
                errors.rejectValue("name", "NonUnique");
            }
        }
        if (tehsil.getId() != null) {
            if (!tehsil.getVersion().equals(
                    Tehsil.findById(tehsil.getId()).getVersion())) {
                errors.reject("name", "Version_Mismatch");
            }
        }
    }

    /**
     * Inits the binder.
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(District.class, new DistrictEditor());
        binder.registerCustomEditor(State.class, new StateEditor());
    }

}
