/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MenuItemController.java
 * Created On: Jan 6, 2012
 */
package org.mkcl.els.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.common.editors.MenuItemEditor;
import org.mkcl.els.domain.MenuItem;
import org.mkcl.els.service.IGridService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * The Class MenuItemController.
 *
 * @author amitd
 * @version v1.0.0
 */
@Controller
@RequestMapping("/menus")
public class MenuItemController extends BaseController {

    /** The grid service. */
    @Autowired
    IGridService gridService;

    /**
     * Lists all the menu items.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model, final Locale locale) {
        String menuxml = MenuItem.getMenuXml(locale);
        model.addAttribute("menu_xml", menuxml);
        return "masters/menus/list";
    }

    /**
     * String.
     *
     * @param model the model
     * @param request the request
     * @param locale the locale
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model,
                          final HttpServletRequest request,
                          final Locale locale) {
        MenuItem menuItem = new MenuItem();
        menuItem.setLocale(locale.toString());
        String parentId = request.getParameter("parentId");
        if (parentId != null) {
            MenuItem parent = MenuItem.findById(Long.parseLong(parentId));
            menuItem.setParent(parent);
        }
        model.addAttribute(menuItem);
        return "masters/menus/new";
    }

    /**
     * Shows an existing record in edit mode.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable final Long id, final ModelMap model) {
        MenuItem menuItem = MenuItem.findById(id);
        model.addAttribute(menuItem);
        return "masters/menus/edit";
    }

    /**
     * Creates a new menu item.
     *
     * @param menuItem the menu item
     * @param result the result
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("menuItem") final MenuItem menuItem,
                         final BindingResult result,
                         final ModelMap model) {
        // Enforce the UNIQUEness constraint
        this.validate(menuItem, result);

        // UNIQUEness constraint violated
        if (result.hasErrors()) {
            model.addAttribute("menuItem", menuItem);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/menus/new";
        }
        menuItem.persist();
        return "redirect:menus/" + menuItem.getId()
                + "/edit?type=success&msg=create_success";
    }

    /**
     * Updates an existing menu.
     *
     * @param menuItem the menu item
     * @param result the result
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("menuItem") final MenuItem menuItem,
                         final BindingResult result,
                         final ModelMap model) {
        // Enforce the UNIQUEness constraint
        this.validate(menuItem, result);

        // UNIQUEness constraint violated
        if (result.hasErrors()) {
            model.addAttribute("menuItem", menuItem);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/menus/edit";
        }

        // No violation of the UNIQUEness constraint
        menuItem.update();
        return "redirect:menus/" + menuItem.getId()
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Deletes an existing record.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        MenuItem.findById(id).remove();
        model.addAttribute("msg", "delete_success");
        model.addAttribute("type", "menu_success");
        return "info";
    }

    /**
     * Validates the menu item object.
     *
     * @param menuItem the menu item
     * @param errors the errors
     * @author meenalw
     * @since v1.0.0
     */
    private void validate(final MenuItem menuItem, final Errors errors) {
        MenuItem duplicateMenuItem = MenuItem.findByTextKey(menuItem
                .getTextKey(), menuItem.getLocale());
        if (duplicateMenuItem != null) {
            if (!duplicateMenuItem.getId().equals(menuItem.getId())) {
                errors.rejectValue("code", "NonUnique");
            }
        }
        // Check if the version matches
        if (menuItem.getId() != null) {
            if (!menuItem.getVersion().equals(
                    MenuItem.findById(menuItem.getId()).getVersion())) {
                errors.rejectValue("text", "Version_Mismatch");
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
        binder.registerCustomEditor(MenuItem.class, new MenuItemEditor());
    }
}
