/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.ConstituencyController.java
 * Created On: Jan 2, 2012
 */
package org.mkcl.els.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.State;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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

/**
 * The Class ConstituencyController.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Controller
@RequestMapping("/constituencies")
public class ConstituencyController extends BaseController {

    /**
     * Index.
     *
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        Grid grid = Grid.findByName("CONSTITUENCY_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/constituencies/list";
    }

    /**
     * _new.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model, final Locale locale) {
        Constituency constituency = new Constituency();
        constituency.setLocale(locale.toString());
        populateModel(
                model, constituency, CustomParameter
                .findByName("DEFAULT_STATE").getValue());
        return "masters/constituencies/new";
    }

    /**
     * Edits the.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable final Long id, final ModelMap model) {
        final Constituency constituency = Constituency.findById(id);
        List<District> districts = constituency.getDistricts();
        String stateName = null;
        if (!districts.isEmpty()) {
            stateName = districts.iterator().next().getState().getName();
        }
        populateModel(model, constituency, stateName);
        return "masters/constituencies/edit";
    }

    /**
     * Creates the.
     *
     * @param constituency the constituency
     * @param result the result
     * @param model the model
     * @param state the state
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("constituency") final Constituency constituency,
                         final BindingResult result,
                         final ModelMap model,
                         @RequestParam final String state) {
        this.validate(constituency, result);
        if (result.hasErrors()) {
            populateModel(model, constituency, state);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/constituencies/new";
        }
        constituency.persist();
        return "redirect:constituencies/" + constituency.getId()
                + "/edit?type=success&msg=create_success";
    }

    /**
     * Update.
     *
     * @param constituency the constituency
     * @param result the result
     * @param model the model
     * @param state the state
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("constituency") final Constituency constituency,
                         final BindingResult result,
                         final ModelMap model,
                         @RequestParam final String state) {
        this.validate(constituency, result);
        if (result.hasErrors()) {
            populateModel(model, constituency, state);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/constituencies/edit";
        }
        constituency.update();
        return "redirect:constituencies/" + constituency.getId()
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        Constituency.findById(id).remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

    /**
     * Validate.
     *
     * @param constituency the constituency
     * @param errors the errors
     * @author meenalw
     * @since v1.0.0
     */
    private void validate(final Constituency constituency, final Errors errors) {
        if (constituency.getName() != null) {
            if (constituency.getLocale().equals("en")) {
                String name = constituency.getName();
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
        if (constituency.getNumber() != null) {
            if (constituency.getLocale().equals("en")) {
                String number = constituency.getNumber();
                if (number.length() > 100 || number.length() < 1) {
                    errors.rejectValue("number", "Size");
                }
            }
        }
        Constituency duplicateParameter = Constituency.findByName(constituency
                .getName());
        if (duplicateParameter != null) {
            if (!duplicateParameter.getId().equals(constituency.getId())) {
                errors.rejectValue("name", "NonUnique");
            }
        }
        if (constituency.getId() != null) {
            if (!constituency.getVersion().equals(
                    Constituency.findById(constituency.getId()).getVersion())) {
                errors.rejectValue("name", "Version_Mismatch");
            }
        }
    }

    /**
     * Inits the binder.
     *
     * @param binder the binder
     * @author meenalw
     * @since v1.0.0
     */
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(
                List.class, "districts",
                new CustomCollectionEditor(List.class) {

                    @Override
                    protected Object convertElement(final Object element) {
                        String id = null;

                        if (element instanceof String) {
                            id = (String) element;
                        }

                        return id != null ? District.findById(Long.valueOf(id))
                                : null;
                    }
                });
    }

    /**
     * Populate model.
     *
     * @param model the model
     * @param constituency the constituency
     * @param stateName the state name
     * @author meenalw
     * @since v1.0.0
     */
    private void populateModel(final ModelMap model,
                               final Constituency constituency,
                               final String stateName) {
        List<State> states = State.findAllSorted(
                "name", constituency.getLocale(), false);
        State selectedState = State.findByName(stateName);
        List<State> newStates = new ArrayList<State>();
        newStates.add(selectedState);
        states.remove(selectedState);
        newStates.addAll(states);
        model.addAttribute("constituency", constituency);
        model.addAttribute("states", newStates);
        List<District> ldistrict = District.findDistrictsByStateId(
                selectedState.getId(), "name", false);
        model.addAttribute("districts", ldistrict);

    }
}
