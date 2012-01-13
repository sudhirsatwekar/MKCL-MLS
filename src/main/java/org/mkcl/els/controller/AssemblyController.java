/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.AssemblyController.java
 * Created On: Dec 26, 2011
 */

package org.mkcl.els.controller;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.common.editors.AssemblyStructureEditor;
import org.mkcl.els.domain.Assembly;
import org.mkcl.els.domain.AssemblyStructure;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.Grid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

/**
 * The Class AssemblyController.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Controller
@RequestMapping("/assemblies")
public class AssemblyController extends BaseController {

    /**
     * Index.
     *
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        Grid grid = Grid.findByName("ASSEMBLY_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/assemblies/list";
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
        Assembly assembly = new Assembly();
        assembly.setLocale(locale.toString());
        populateModel(model, assembly);
        return "masters/assemblies/new";
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
        Assembly assembly = Assembly.findById(id);
        populateModel(model, assembly);
        return "masters/assemblies/edit";
    }

    /**
     * Creates the.
     *
     * @param assembly the assembly
     * @param result the result
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(
            @Valid @ModelAttribute("assembly") final Assembly assembly,
            final BindingResult result, final ModelMap model,
            final Locale locale) {
        this.validate(assembly, result);
        if (result.hasErrors()) {
            populateModel(model, assembly);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/assemblies/new";
        }
        if (assembly.isCurrentAssembly()) {
            assembly.updatePreviousCurrentAssembly(locale.toString());
        }
        assembly.persist();
        return "redirect:assemblies/" + assembly.getId()
                + "/edit?type=success&msg=create_success";

    }

    /**
     * Update.
     *
     * @param assembly the assembly
     * @param result the result
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(
            @Valid @ModelAttribute("assembly") final Assembly assembly,
            final BindingResult result, final ModelMap model,
            final Locale locale) {
        this.validate(assembly, result);
        if (result.hasErrors()) {
            populateModel(model, assembly);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/assemblies/edit";
        }
        if (assembly.isCurrentAssembly()) {
            assembly.updatePreviousCurrentAssembly(locale.toString());
        }
        assembly.update();
        return "redirect:assemblies/" + assembly.getId()
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete.
     *
     * @param request the request
     * @param id the id
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(final HttpServletRequest request,
            @PathVariable final Long id, final ModelMap model) {
        Assembly assembly = Assembly.findById(id);
        assembly.remove();
        return "info";
    }

    /**
     * Validate.
     *
     * @param assembly the assembly
     * @param errors the errors
     * @author nileshp
     * @since v1.0.0 Validate.
     */
    private void validate(final Assembly assembly, final Errors errors) {
        if (assembly.getAssemblyStructure() == null) {
            errors.rejectValue("assemblyStructure", "NotEmpty");
        }
        Assembly duplicateParameter = Assembly.findByAssembly(assembly
                .getAssembly());
        if (duplicateParameter != null) {
            if (!duplicateParameter.getId().equals(assembly.getId())) {
                errors.rejectValue("assembly", "NonUnique");
            }
        }
        if (assembly.getId() != null) {
            if (!assembly.getVersion().equals(
                    Assembly.findById(assembly.getId()).getVersion())) {
                errors.reject("assembly", "Version_Mismatch");
            }
        }
    }

    /**
     * Inits the binder.
     *
     * @param binder the binder
     * @author nileshp
     * @since v1.0.0 Inits the binder.
     */
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                CustomParameter.findByName("SERVER_DATEFORMAT")
                        .getValue());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(AssemblyStructure.class,
                new AssemblyStructureEditor());
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
                dateFormat, true));

    }

    /**
     * Populate model.
     *
     * @param model the model
     * @param assembly the assembly
     * @author nileshp
     * @since v1.0.0 Populate model.
     */
    private void populateModel(final ModelMap model, final Assembly assembly) {
        model.addAttribute(
                "assemblyStructures",
                AssemblyStructure.findAllSorted("name",
                        assembly.getLocale(), false));
        model.addAttribute("assembly", assembly);
    }
}
