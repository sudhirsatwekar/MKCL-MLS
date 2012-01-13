/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.FieldController.java
 * Created On: Dec 29, 2011
 */
package org.mkcl.els.controller;

import javax.validation.Valid;

import org.mkcl.els.domain.Field;
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
 * The Class FieldController.
 *
 * @author sujitas
 * @since v1.0.0
 */
@Controller
@RequestMapping("/fields")
public class FieldController {

    /**
     * Index.
     *
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        final Grid grid = Grid.findByName("FIELD_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/fields/list";
    }

    /**
     * New form.
     *
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model) {
        final Field field = new Field();
        model.addAttribute("field", field);
        return "masters/fields/new";
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
        final Field field = Field.findById(id);
        model.addAttribute("field", field);
        return "masters/fields/edit";
    }

    /**
     * Creates the.
     *
     * @param field the field
     * @param result the result
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("field") final Field field,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(field, result);
        if (result.hasErrors()) {
            model.addAttribute("field", field);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/fields/new";
        }
        field.persist();
        return "redirect:fields/" + field.getId()
                + "/edit?type=success&msg=create_success";

    }

    /**
     * Update.
     *
     * @param field the field
     * @param result the result
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("field") final Field field,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(field, result);
        if (result.hasErrors()) {
            model.addAttribute("field", field);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/fields/edit";
        }
        field.update();
        return "redirect:fields/" + field.getId()
                + "/edit?type=success&msg=create_success";

    }

    /**
     * Delete.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        final Field field = Field.findById(id);
        field.remove();
        model.addAttribute("msg", "delete_success");
        model.addAttribute("type", "success");
        return "info";
    }

    /**
     * Validate.
     *
     * @param field the field
     * @param errors the errors
     * @author sujitas
     * @since v1.0.0
     */
    private void validate(final Field field, final Errors errors) {
        if (field.getMandatory() != null && field.getVisible() != null
                && (field.getMandatory().equals("MANDATORY"))
                && (field.getVisible().equals("HIDDEN"))) {
            errors.rejectValue("visible", "NonVisible");
        }

        Field duplicateField = Field.findByNameAndForm(
                field.getName(), field.getForm());
        if (null != duplicateField) {
            errors.rejectValue("name", "NonUnique");
        }
    }
}
