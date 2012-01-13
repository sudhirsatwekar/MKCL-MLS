/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.AssemblyNumberController.java
 * Created On: Dec 27, 2011
 */

package org.mkcl.els.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.domain.AssemblyNumber;
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
 * The Class AssemblyNumberController.
 *
 * @author amitd
 * @version v1.0.0
 */
@Controller
@RequestMapping("/assembly_number")
public class AssemblyNumberController extends BaseController {

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
        Grid grid = Grid.findByName("ASSEMBLY_NUMBER_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/assembly_number/list";
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
        AssemblyNumber assemblyNumber = new AssemblyNumber();
        assemblyNumber.setLocale(locale.toString());
        model.addAttribute(assemblyNumber);
        return "masters/assembly_number/new";
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
        AssemblyNumber assemblyNumber = AssemblyNumber.findById(id);
        model.addAttribute(assemblyNumber);
        return "masters/assembly_number/edit";
    }

    /**
     * Creates the.
     *
     * @param assemblyNumber the assembly number
     * @param result the result
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("assemblyNumber")
    final AssemblyNumber assemblyNumber,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(assemblyNumber, result);
        if (result.hasErrors()) {
            model.addAttribute("assemblyNumber", assemblyNumber);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/assembly_number/new";
        }
        assemblyNumber.persist();
        return "redirect:assembly_number/" + assemblyNumber.getId()
                + "/edit?type=success&msg=create_success";

    }

    /**
     * Update.
     *
     * @param assemblyNumber the assembly number
     * @param result the result
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("assemblyNumber")
    final AssemblyNumber assemblyNumber,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(assemblyNumber, result);
        if (result.hasErrors()) {
            model.addAttribute("assemblyNumber", assemblyNumber);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/assembly_number/edit";
        }

        assemblyNumber.update();
        return "redirect:assembly_number/" + assemblyNumber.getId()
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete.
     *
     * @param id the id
     * @param model the model
     * @param request the request
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id,
                         final ModelMap model,
                         final HttpServletRequest request) {
        AssemblyNumber assemblyNumber = AssemblyNumber.findById(id);
        assemblyNumber.remove();
        return "info";
    }

    /**
     * Validate.
     *
     * @param assemblyNumber the assembly number
     * @param errors the errors
     * @author nileshp
     * @since v1.0.0
     * Validate.
     */
    private void validate(final AssemblyNumber assemblyNumber, final Errors errors) {
        if (assemblyNumber.getAssemblyNo() != null) {
            if (assemblyNumber.getLocale().equals("en")) {
                String number = assemblyNumber.getAssemblyNo();
                if (number.length() > 100 || number.length() < 1) {
                    errors.rejectValue("assemblyNo", "Size");

                }
            }
        }
        //used generic method findByname to find assembly number as it is in string
        AssemblyNumber duplicateNumber =
                AssemblyNumber.findByAssemblyNumber(assemblyNumber.getAssemblyNo());

        if (duplicateNumber != null) {
            if (!duplicateNumber.getId().equals(assemblyNumber.getId())) {
                // assemblyNo attribute of AssemblyNumber object must be unique
                errors.rejectValue("assemblyNo", "NonUnique");
            }
        }
        // Check if the version matches
        if (assemblyNumber.getId() != null) {
            /*if (!assemblyNumber.getVersion().equals(
                    assemblyNumberService.findById(assemblyNumber.getId())
                            .getVersion())) {
                errors.rejectValue("assemblyNo", "Version_Mismatch");

            }*/
            if (!assemblyNumber.checkVersion()) {
                errors.rejectValue("assemblyNo", "Version_Mismatch");
            }
        }
    }
}
