/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.TitleController.java
 * Created On: Dec 19, 2011
 */
package org.mkcl.els.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.Title;
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
 * The Class TitleController.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Controller
@RequestMapping("/titles")
public class TitleController {

    /**
     * Index.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "module", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        return "masters/titles/module";
    }

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
        Grid grid = Grid.findByName("TITLE_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/titles/list";
    }

    /**
     * New form.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model, final Locale locale) {
        Title title = new Title();
        title.setLocale(locale.toString());
        model.addAttribute("title", title);
        return "masters/titles/new";
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
        Title title = Title.findById(id);
        model.addAttribute(title);
        return "masters/titles/edit";
    }

    /**
     * Creates the.
     *
     * @param title the title
     * @param result the result
     * @param model the model
     * @param redirectAttributes the redirect attributes
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("title") final Title title,
            final BindingResult result,
            final ModelMap model,
            final RedirectAttributes redirectAttributes) {
        this.validate(title, result);
        if (result.hasErrors()) {
            model.addAttribute("title", title);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/titles/new";
        }

        title.persist();
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("msg", "create_success");
        return "redirect:titles/" + title.getId() + "/edit";
    }

    /**
     * Update.
     *
     * @param title the title
     * @param result the result
     * @param model the model
     * @param redirectAttributes the redirect attributes
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("title") final Title title,
            final BindingResult result,
            final ModelMap model,
            final RedirectAttributes redirectAttributes) {
        this.validate(title, result);
        if (result.hasErrors()) {
            model.addAttribute("title", title);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/titles/edit";
        }

        title.persist();
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("msg", "update_success");
        return "redirect:titles/" + title.getId() + "/edit";
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
    public @ResponseBody boolean delete(@PathVariable final Long id) {
        Title title = Title.findById(id);
        title.remove();
        return true;
    }

    /**
     * Validate.
     *
     * @param title the title
     * @param errors the errors
     * @author samiksham
     * @since v1.0.0
     */
    private void validate(final Title title, final Errors errors) {
        Title duplicateNumber = Title.findByName(title.getName(), title.getLocale());

        if (duplicateNumber != null) {
            if (!duplicateNumber.getId().equals(title.getId())) {
                errors.rejectValue("name", "NonUnique");
            }
        }
        /*if (title.getName() != null) {
            final String name = title.getName();
            final Pattern pattern = Pattern.compile("[A-Za-z ]{1,20}");
            final Matcher matcher = pattern.matcher(name);
            if (!matcher.matches()) {
                errors.rejectValue("name", "Pattern");
            }
            if (name.length() > 20 || name.length() < 1) {
                errors.rejectValue("name", "Size");
            }
        }*/
        // Check if the version matches
        if (title.getId() != null && !title.checkVersion()) {
            errors.rejectValue("name", "Version_Mismatch");
        }

    }
}
