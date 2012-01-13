/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.CustomParameterController.java
 * Created On: Dec 30, 2011
 */
package org.mkcl.els.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.Grid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class CustomParameterController.
 *
 * @author amitd
 * @version v1.0.0
 */
@Controller
@RequestMapping("/custom_params")
public class CustomParameterController extends BaseController {

    /**
     * Lists all custom parameters.
     *
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        final Grid grid = Grid.findByName("CUSTOM_PARAMETER_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/custom_params/list";
    }

    /**
     * New form.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model, final Locale locale) {
        final CustomParameter customParameter = new CustomParameter();
        model.addAttribute(customParameter);
        return "masters/custom_params/new";
    }

    /**
     * Edits the.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable final Long id, final ModelMap model) {
        final CustomParameter customParameter = CustomParameter.findById(id);
        model.addAttribute(customParameter);
        return "masters/custom_params/edit";
    }

    /**
     * Creates a new custom parameter.
     *
     * @param customParameter the custom parameter
     * @param result the result
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("customParameter")
                         final CustomParameter customParameter,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(customParameter, result);

        if (result.hasErrors()) {
            model.addAttribute(customParameter);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/custom_params/new";
        }

        customParameter.persist();
        return "redirect:custom_params/" + customParameter.getId()
                + "/edit?type=success&msg=create_success";

    }

    /**
     * Updates the custom parameter.
     *
     * @param customParameter the custom parameter
     * @param result the result
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("customParameter")
                         final CustomParameter customParameter,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(customParameter, result);

        if (result.hasErrors()) {
            model.addAttribute(customParameter);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/custom_params/edit";
        }

        customParameter.update();
        return "redirect:custom_params/" + customParameter.getId()
                + "/edit?type=success&msg=update_success";

    }

    /**
     * Deletes an existing custom parameter.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        final CustomParameter customParameter = CustomParameter.findById(id);
        customParameter.remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

    /**
     * Custom Validation.
     *
     * @param customParameter the custom parameter
     * @param errors the errors
     * @author sujitas
     * @since v1.0.0
     */
    private void validate(final CustomParameter customParameter,
                          final Errors errors) {
        final CustomParameter duplicateParameter = CustomParameter
                .findByName(customParameter.getName());
        if (duplicateParameter != null
                && !duplicateParameter.getId().equals(customParameter.getId())) {
            // name attribute of CustomParameter object must be unique
            errors.rejectValue("name", "NonUnique");
        }
        // Check if the version matches
        if (customParameter.getId() != null && !customParameter.checkVersion()) {
            errors.rejectValue("version" , "Version_Mismatch");
        }
    }
}
