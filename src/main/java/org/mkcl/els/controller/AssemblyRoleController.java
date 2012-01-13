/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.AssemblyRoleController.java
 * Created On: Jan 9, 2012
 */

package org.mkcl.els.controller;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.mkcl.els.domain.AssemblyRole;
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
 * The Class AssemblyRoleController.
 *
 * @author amitd
 * @version v1.0.0
 */
@Controller
@RequestMapping("/assembly_roles")
public class AssemblyRoleController extends BaseController {

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
        Grid grid = Grid.findByName("ASSEMBLY_ROLE_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/assembly_roles/list";
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
        AssemblyRole assemblyRole = new AssemblyRole();
        assemblyRole.setLocale(locale.toString());
        model.addAttribute(assemblyRole);
        return "masters/assembly_roles/new";
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
        AssemblyRole assemblyRole = AssemblyRole.findById(id);
        model.addAttribute("assemblyRole", assemblyRole);
        return "masters/assembly_roles/edit";
    }

    /**
     * Creates the.
     *
     * @param assemblyRole the assembly role
     * @param result the result
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("assemblyRole") final AssemblyRole assemblyRole,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(assemblyRole, result);
        if (result.hasErrors()) {
            model.addAttribute("assemblyRole", assemblyRole);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/assembly_roles/new";

        }

        assemblyRole.persist();
        return "redirect:assembly_roles/" + assemblyRole.getId()
                + "/edit?type=success&msg=create_success";

    }

    /**
     * Update.
     *
     * @param assemblyRole the assembly role
     * @param result the result
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("assemblyRole") final AssemblyRole assemblyRole,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(assemblyRole, result);
        if (result.hasErrors()) {
            model.addAttribute("assemblyRole", assemblyRole);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/assembly_roles/edit";
        }
        assemblyRole.update();
        return "redirect:assembly_roles/" + assemblyRole.getId()
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
        AssemblyRole assemblyRole = AssemblyRole.findById(id);
        assemblyRole.remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

    /**
     * Validate.
     *
     * @param assemblyRole the assembly role
     * @param errors the errors
     * @author nileshp
     * @since v1.0.0 Validate.
     */
    private void validate(final AssemblyRole assemblyRole, final Errors errors) {
        if (assemblyRole.getName() != null) {
            if (assemblyRole.getLocale().equals("en")) {
                String name = assemblyRole.getName();
                Pattern pattern = Pattern.compile("[A-Za-z ]{1,100}");
                Matcher matcher = pattern.matcher(name);
                if (!matcher.matches()) {
                    errors.rejectValue("name", "Pattern");
                }
                if (name.length() > 100 || name.length() < 1) {
                    errors.rejectValue("name", "Size");

                }
            }
            AssemblyRole duplicateRole = AssemblyRole.findByName(assemblyRole.getName());
            if (duplicateRole != null) {
                if (!duplicateRole.getId().equals(assemblyRole.getId())) {
                    errors.rejectValue("name", "NonUnique");
                }
            }
            if (assemblyRole.getId() != null) {
                if (!assemblyRole.checkVersion()) {
                    errors.rejectValue("name", "Version_Mismatch");
                }
            }
        }
    }
}
