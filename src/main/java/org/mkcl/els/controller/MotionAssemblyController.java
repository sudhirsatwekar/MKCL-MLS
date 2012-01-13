/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MotionAssemblyController.java
 * Created On: Jan 12, 2012
 */
package org.mkcl.els.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.MotionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class MotionAssemblyController.
 *
 * @author sujitas
 * @since v1.0.0
 */
@Controller
@RequestMapping("/motion_assembly")
public class MotionAssemblyController extends BaseController {

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
        Grid grid = Grid.findByName("MOIS_GRID");
        model.addAttribute("gridId", grid.getId());
        return "motion_information/assembly/list";
    }

    /**
     * New form.
     *
     * @param model the model
     * @param errors the errors
     * @param locale the locale
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model,
                          final Error errors,
                          final Locale locale) {
        MotionInformation motionInformation = new MotionInformation();
        model.addAttribute("motionInformation", motionInformation);

        return "motion_information/assembly/new";
    }

    /**
     * Edits the form.
     *
     * @param model the model
     * @param errors the errors
     * @param locale the locale
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String editForm(final ModelMap model,
                           final Error errors,
                           final Locale locale) {
        MotionInformation motionInformation = new MotionInformation();
        motionInformation.setId(1L);
        model.addAttribute("motionInformation", motionInformation);

        return "motion_information/assembly/edit";
    }

    /**
     * Creates the.
     *
     * @param motionInformation the motion information
     * @param result the result
     * @param model the model
     * @param request the request
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("motionInformation") final MotionInformation motionInformation,
                         final BindingResult result,
                         final ModelMap model,
                         final HttpServletRequest request) {
        motionInformation.setId(1L);
        model.addAttribute("motionInformation", motionInformation);

        if (CustomParameter.findByName("MOIS_PROGRESSIVE_DISPLAY").getValue()
                .toLowerCase().equals("progressive")) {
            return "redirect:/motion_information/" + motionInformation.getId()
                    + "/edit?type=success&msg=create_success";
        }
        else {
            return "redirect:motion_assembly/" + motionInformation.getId()
                    + "/edit?type=success&msg=create_success";
        }
    }

    /**
     * Update.
     *
     * @param motionInformation the motion information
     * @param result the result
     * @param model the model
     * @param request the request
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("motionInformation")
    final MotionInformation motionInformation,
                         final BindingResult result,
                         final ModelMap model,
                         final HttpServletRequest request) {
        motionInformation.setId(1L);
        model.addAttribute("motionInformation", motionInformation);

        if (CustomParameter.findByName("MOIS_PROGRESSIVE_DISPLAY").getValue()
                .toLowerCase().equals("progressive")) {
            return "redirect:/motion_information/" + motionInformation.getId()
                    + "/edit?type=success&msg=create_success";
        } else {
            return "redirect:motion_assembly/" + motionInformation.getId()
                    + "/edit?type=success&msg=create_success";
        }
    }

    /**
     * Prints the form.
     *
     * @param model the model
     * @param errors the errors
     * @param locale the locale
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "print", method = RequestMethod.GET)
    public String printForm(final ModelMap model,
                            final Error errors,
                            final Locale locale) {
        MotionInformation motionInformation = new MotionInformation();
        model.addAttribute("motionInformation", motionInformation);

        return "motion_information/motion/print";
    }
}
