/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.AssemblyTermController.java
 * Created On: Dec 26, 2011
 */
package org.mkcl.els.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.mkcl.els.domain.AssemblyTerm;
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
 * The Class AssemblyTermController.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Controller
@RequestMapping("/assembly_terms")
public class AssemblyTermController {

    /**
     * List.
     *
     * @param model the model
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        Grid grid = Grid.findByName("ASSEMBLY_TERM_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/assembly_terms/list";
    }

    /**
     * _new.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model, final Locale locale) {
        AssemblyTerm assemblyTerm = new AssemblyTerm();
        assemblyTerm.setLocale(locale.toString());
        model.addAttribute("assemblyTerm", assemblyTerm);
        return "masters/assembly_terms/new";
    }

    /**
     * Edits the.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable final Long id, final ModelMap model) {
        AssemblyTerm assemblyTerm = AssemblyTerm.findById(id);
        model.addAttribute(assemblyTerm);
        return "masters/assembly_terms/edit";
    }

    /**
     * Creates the.
     *
     * @param assemblyTerm the assembly number
     * @param result the result
     * @param model the model
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("assemblyTerm") final AssemblyTerm assemblyTerm,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(assemblyTerm, result);
        if (result.hasErrors()) {
            model.addAttribute("assemblyTerm", assemblyTerm);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/assembly_terms/new";
        }

        assemblyTerm.persist();
        return "redirect:assembly_terms/" + assemblyTerm.getId()
                + "/edit?type=success&msg=create_success";
    }

    /**
     * Update.
     *
     * @param assemblyTerm the assembly number
     * @param result the result
     * @param model the model
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("assemblyTerm") final AssemblyTerm assemblyTerm,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(assemblyTerm, result);

        if (result.hasErrors()) {
            model.addAttribute("assemblyTerm", assemblyTerm);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/assembly_terms/edit";
        }
        assemblyTerm.update();
        return "redirect:assembly_terms/" + assemblyTerm.getId()
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        AssemblyTerm assemblyTerm = AssemblyTerm.findById(id);
        assemblyTerm.remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

    /**
     * Validate.
     *
     * @param assemblyTerm the assembly number
     * @param errors the errors
     * @author samiksham
     * @since v1.0.0
     */
    private void validate(final AssemblyTerm assemblyTerm, final Errors errors) {

        AssemblyTerm duplicateNumber = AssemblyTerm
                .findByAssemblyTerm(assemblyTerm.getTerm() == null ? -1 : assemblyTerm.getTerm());

        if (duplicateNumber != null) {
            if (!duplicateNumber.getId().equals(assemblyTerm.getId())) {
                // assemblyNo attribute of assemblyTerm object must be unique
                errors.rejectValue("term", "NonUnique");
            }
        }

        // Check if the version matches
        if (assemblyTerm.getId() != null && !assemblyTerm.checkVersion()) {
            errors.rejectValue("term", "Version_Mismatch");

        }
    }
}
