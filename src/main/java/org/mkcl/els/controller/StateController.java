/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.StateController.java
 * Created On: Dec 19, 2011
 */
package org.mkcl.els.controller;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.State;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The Class StateController.
 *
 * @author sandeeps
 * @version v1.0.0
 */

@Controller
@RequestMapping("/states")
public class StateController extends BaseController {

    /**
     * Gets the module page.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "module", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        return "masters/states/module";
    }


    /**
     * Index.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public final String list(final ModelMap model) {
        final Grid grid = Grid.findByName("STATE_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/states/list";
    }

    /**
     * _new.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public final String newForm(final ModelMap model, final Locale locale) {
        final State state = new State();
        state.setLocale(locale.toString());
        model.addAttribute("state", state);
        return "masters/states/new";
    }

    /**
     * Edits the.
     *
     * @param id the id
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public final String edit(@PathVariable final Long id, final ModelMap model) {
        final State state = State.findById(id);
        model.addAttribute("state", state);
        return "masters/states/edit";
    }

    /**
     * Creates the.
     *
     * @param state the state
     * @param result the result
     * @param model the model
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping(method = RequestMethod.POST)
    public final String create(@Valid @ModelAttribute("state") final State state,
            final BindingResult result,
            final ModelMap model,
            final RedirectAttributes redirectAttributes) {
        this.validate(state, result);
        if (result.hasErrors()) {
            model.addAttribute("state", state);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/states/new";
        }
        state.persist();
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("msg", "create_success");
        return "redirect:states/" + state.getId() + "/edit?type=success&msg=create_success";
    }

    /**
     * Edits the.
     *
     * @param state the state
     * @param result the result
     * @param model the model
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping(method = RequestMethod.PUT)
    public final String edit(@Valid @ModelAttribute("state") final State state,
            final BindingResult result,
            final ModelMap model, final RedirectAttributes redirectAttributes) {
        this.validate(state, result);
        if (result.hasErrors()) {
            model.addAttribute("state", state);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/states/edit";
        }
        state.update();
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("msg", "update_success");
        return "redirect:states/" + state.getId() + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete.
     *
     * @param id the id
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public @ResponseBody boolean delete(@PathVariable final Long id, final ModelMap model) {
        final State state = State.findById(id);
        state.remove();
        return true;
    }

    /**
     * Validate.
     *
     * @param state the state
     * @param errors the errors
     */
    private void validate(final State state, final Errors errors) {
        if (state.getName() != null && state.getLocale().equals("en")) {
            final String name = state.getName();
            final Pattern pattern = Pattern.compile("[A-Za-z ]{1,50}");
            final Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                errors.rejectValue("name", "Pattern");
            }
            if (name.length() > 50 || name.length() < 1) {
                errors.rejectValue("name", "Size");
            }
        }
        final State duplicateParameter = State.findByName(state.getName());
        if (duplicateParameter != null
                && !duplicateParameter.getId().equals(state.getId())) {
            errors.rejectValue("name", "NonUnique");
        }

        if (state.getId() != null
                && !state.checkVersion()) {
            errors.reject("name", "Version_Mismatch");
        }
    }

}
