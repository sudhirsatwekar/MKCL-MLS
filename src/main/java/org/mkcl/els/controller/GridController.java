/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.GridController.java
 * Created On: Jan 6, 2012
 */

package org.mkcl.els.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.common.vo.Filter;
import org.mkcl.els.common.vo.GridConfig;
import org.mkcl.els.common.vo.GridData;
import org.mkcl.els.domain.Grid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class GridController.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Controller
@RequestMapping("/grid")
public class GridController extends BaseController {

    /**
     * Gets the metadata configuration.
     *
     * @param gridId the grid id
     * @param model the model
     * @param request the request
     * @return the meta
     * @throws ClassNotFoundException the class not found exception
     */
    @RequestMapping(value = "/{gridId}/meta", method = RequestMethod.GET)
    public @ResponseBody
    GridConfig getConfig(@PathVariable final Long gridId,
                         final ModelMap model,
                         final HttpServletRequest request)
            throws ClassNotFoundException {
        // Get the entity class based on the grid id
        return new Grid().getConfig(gridId);
    }

    /**
     * Gets the.
     *
     * @param gridId the grid id
     * @param page the page
     * @param rows the rows
     * @param sidx the sidx
     * @param order the order
     * @param search the search
     * @param searchField the search field
     * @param searchString the search string
     * @param searchOper the search oper
     * @param filtersData the filters data
     * @param baseFilters the base filters
     * @param model the model
     * @param request the request
     * @param locale the locale
     * @return the grid data
     * @throws ClassNotFoundException the class not found exception
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "/data/{gridId}", method = RequestMethod.GET)
    public @ResponseBody
    GridData get(@PathVariable final Long gridId,
                 @RequestParam(value = "page", required = false) final Integer page,
                 @RequestParam(value = "rows", required = false) final Integer rows,
                 @RequestParam(value = "sidx", required = false) final String sidx,
                 @RequestParam(value = "sord", required = false) final String order,
                 @RequestParam(value = "_search", required = false) final Boolean search,
                 @RequestParam(value = "searchField", required = false) final String searchField,
                 @RequestParam(value = "searchString", required = false) final String searchString,
                 @RequestParam(value = "searchOper", required = false) final String searchOper,
                 @RequestParam(value = "filters", required = false) final String filtersData,
                 @RequestParam(value = "baseFilters", required = false) final String baseFilters,
                 final ModelMap model,
                 final HttpServletRequest request,
                 final Locale locale) throws ClassNotFoundException {

        Filter filter = Filter.create(filtersData);
        if (search) {
            return new Grid().getData(
                    gridId, rows, page, sidx, order, filter.toSQl(), locale);
        } else {
            return new Grid().getData(gridId, rows, page, sidx, order, locale);
        }
    }

    /**
     * Lists all grids.
     *
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model) {
        Grid grid = Grid.findByName("GRID");
        model.addAttribute("gridId", grid.getId());
        return "masters/grids/list";
    }

    /**
     * _new.
     *
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model) {
        Grid grid = new Grid();
        model.addAttribute(grid);
        return "masters/grids/new";
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
        Grid grid = Grid.findById(id);
        model.addAttribute(grid);
        return "masters/grids/edit";
    }

    /**
     * Creates a new grid.
     *
     * @param grid the grid
     * @param result the result
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("grid") final Grid grid,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(grid, result);
        if (result.hasErrors()) {
            model.addAttribute("grid", grid);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "masters/grids/new";
        }

        grid.persist();
        return "redirect:grid/" + grid.getId()
                + "/edit?type=success&msg=create_success";

    }

    /**
     * Updates the grid.
     *
     * @param grid the grid
     * @param result the result
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("grid") final Grid grid,
                         final BindingResult result,
                         final ModelMap model) {
        this.validate(grid, result);
        if (result.hasErrors()) {
            model.addAttribute("grid", grid);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "masters/grids/edit";
        }

        grid.update();
        return "redirect:grid/" + grid.getId()
                + "/edit?type=success&msg=update_success";

    }

    /**
     * Deletes an existing grid.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        Grid grid = Grid.findById(id);
        grid.remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

    /**
     * Custom Validation.
     *
     * @param grid the grid
     * @param errors the errors
     * @author nileshp
     * @since v1.0.0
     * Validate.
     */
    private void validate(final Grid grid, final Errors errors) {
        Grid duplicateGrid = Grid.findByName(grid.getName());
        if (duplicateGrid != null) {
            if (!duplicateGrid.getId().equals(grid.getId())) {
                errors.rejectValue("code", "NonUnique");
            }
        }
        // Check if the version matches
        if (grid.getId() != null) {
            if (!grid.getVersion().equals(
                    Grid.findById(grid.getId()).getVersion())) {
                errors.reject("Version_Mismatch");
            }
        }
    }
}
