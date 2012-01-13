/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MessageResourceController.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.MessageResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class MessageResourceController.
 *
 * @author vishals
 * @since v1.0.0
 */
@Controller
@RequestMapping("/messages")
public class MessageResourceController extends BaseController {

    /**
     * Lists all message resources.
     *
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        Grid grid = Grid.findByName("MSG_RESOURCE_GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/messages/list";
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
    public String newForm(final ModelMap model,
                          final Locale locale) {
        MessageResource resource = new MessageResource();
        resource.setLocale(locale.toString());
        model.addAttribute(resource);
        return "masters/messages/new";
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
    public String edit(@PathVariable final Long id,
                       final ModelMap model) {
        MessageResource messageResource = MessageResource.findById(id);
        model.addAttribute(messageResource);
        return "masters/messages/edit";
    }

    /**
     * Creates a new message resource.
     *
     * @param messageResource the message resource
     * @param result the result
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("messageResource")
                         final MessageResource messageResource,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(messageResource, result);
        if (result.hasErrors()) {
            model.addAttribute("messageResource", messageResource);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/messages/new";
        }
        messageResource.persist();
        return "redirect:messages/" + messageResource.getId()
                + "/edit?type=success&msg=create_success";
    }

    /**
     * Updates the message resource.
     *
     * @param messageResource the message resource
     * @param result the result
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("messageResource")
                         final MessageResource messageResource,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(messageResource, result);
        if (result.hasErrors()) {
            model.addAttribute("messageResource", messageResource);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/messages/edit";
        }
        messageResource.update();
        return "redirect:messages/" + messageResource.getId()
                + "/edit?type=success&msg=update_success";

    }

    /**
     * Deletes an existing message resource.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id,
                         final ModelMap model) {
        MessageResource messageResource = MessageResource.findById(id);
        messageResource.remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

    /**
     * Custom Validation.
     *
     * @param messageResource the message resource
     * @param errors the errors
     * @author sujitas
     * @since v1.0.0
     */
    private void validate(final MessageResource messageResource,
                          final Errors errors) {
        MessageResource duplicateResource = MessageResource
                .findByLocaleAndCode(
                        messageResource.getLocale(), messageResource.getCode());
        if (duplicateResource != null) {
            if (!duplicateResource.getId().equals(messageResource.getId())) {
                errors.rejectValue("code", "NonUnique");
            }
        }
        // Check if the version matches
        if (messageResource.getId() != null) {
            if (!messageResource.checkVersion()) {
                errors.reject("Version_Mismatch");
            }
        }
    }
}
