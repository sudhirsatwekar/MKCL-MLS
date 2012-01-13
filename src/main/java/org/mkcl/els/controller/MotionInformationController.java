/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MotionInformationController.java
 * Created On: Jan 11, 2012
 */
package org.mkcl.els.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.MotionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class MotionInformationController.
 *
 * @author nileshp
 * @since v1.0.0
 */
@Controller
@RequestMapping("/motion_information")
public class MotionInformationController extends BaseController {



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
    public String editMotionForm( final ModelMap model,
                          final Error errors,
                          final Locale locale) {
        MotionInformation motionInformation = new MotionInformation();
        motionInformation.setId(1L);
        model.addAttribute("motionInformation", motionInformation);

       return "motion_information/motion/edit";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("motionInformation")
    final MotionInformation motionInformation,
                         final BindingResult result,
                         final ModelMap model,
                         final HttpServletRequest request) {
        motionInformation.setId(1L);
        model.addAttribute("motionInformation", motionInformation);

        if (CustomParameter.findByName("MOIS_PROGRESSIVE_DISPLAY").getValue()
                .toLowerCase().equals("progressive")) {
            return "redirect:/motion_assembly/"
                    + motionInformation.getId()
                    + "/edit?type=success&msg=update_success";
        } else {
            return "redirect:motion_information/"
                    + motionInformation.getId()
                    + "/edit?type=success&msg=update_success";
        }
    }
}
