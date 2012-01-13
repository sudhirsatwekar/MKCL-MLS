/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.AssemblyStructureController.java
 * Created On: Dec 26, 2011
 */

package org.mkcl.els.controller;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.mkcl.els.domain.AssemblyStructure;
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
 * The Class AssemblyStructureController.
 *
 * @author amitd
 * @version v1.0.0
 */
@Controller
@RequestMapping("/assembly_struct")
public class AssemblyStructureController extends BaseController {

    /**
     * List.
     *
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        Grid grid = Grid.findByName("ASSEMBLY_STRUCTURE_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/assembly_struct/list";
    }

    /**
     * _new.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model, final Locale locale) {
        AssemblyStructure structure = new AssemblyStructure();
        structure.setLocale(locale.toString());
        model.addAttribute(structure);
        return "masters/assembly_struct/new";
    }

    /**
     * Edits the.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable final Long id, final ModelMap model) {
        AssemblyStructure structure = AssemblyStructure.findById(id);
        model.addAttribute(structure);
        return "masters/assembly_struct/edit";
    }

    /**
     * Creates the.
     *
     * @param assemblyStructure the assembly structure
     * @param result the result
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("assemblyStructure") final AssemblyStructure assemblyStructure,
            final BindingResult result, final ModelMap model) {
        this.validate(assemblyStructure, result);
        if (result.hasErrors()) {
            model.addAttribute("assemblyStructure", assemblyStructure);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/assembly_struct/new";
        }

        assemblyStructure.persist();
        return "redirect:assembly_struct/" + assemblyStructure.getId()
                + "/edit?type=success&msg=create_success";
    }

    /**
     * Update.
     *
     * @param assemblyStructure the assembly structure
     * @param result the result
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(
            @Valid @ModelAttribute("assemblyStructure") final AssemblyStructure assemblyStructure,
            final BindingResult result, final ModelMap model) {
        this.validate(assemblyStructure, result);

        if (result.hasErrors()) {
            model.addAttribute("assemblyStructure", assemblyStructure);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/assembly_struct/edit";
        }

        assemblyStructure.update();
        return "redirect:assembly_struct/" + assemblyStructure.getId()
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        AssemblyStructure assemblyStructure = AssemblyStructure.findById(id);
        assemblyStructure.remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

    /**
     * Validate.
     *
     * @param assemblyStructure the assembly structure
     * @param errors the errors
     * @author nileshp
     * @since v1.0.0
     * Validate.
     */
    private void validate(final AssemblyStructure assemblyStructure,
            final Errors errors) {
        if (assemblyStructure.getName() != null) {
            if (assemblyStructure.getLocale().equals("en")) {
                String name = assemblyStructure.getName();
                Pattern pattern = Pattern.compile("[A-Za-z ]{1,100}");
                Matcher matcher = pattern.matcher(name);
                if (!matcher.matches()) {
                    errors.rejectValue("name", "Pattern");
                }
                if (name.length() > 100 || name.length() < 1) {
                    errors.rejectValue("name", "Size");

                }
            }
        }

        AssemblyStructure duplicateStructure =
                AssemblyStructure.findByName(assemblyStructure.getName());

        if (duplicateStructure != null) {
            if (!duplicateStructure.getId().equals(assemblyStructure.getId())) {
                // name attribute of AssemblyStructure object must be unique
                errors.rejectValue("name", "NonUnique");
            }
        }
        // Check if the version matches
        if (assemblyStructure.getId() != null) {
            System.out.println(assemblyStructure.checkVersion());
            if (!assemblyStructure.checkVersion()) {
                errors.reject("version", "Version_Mismatch");
            }
        }
    }
}
