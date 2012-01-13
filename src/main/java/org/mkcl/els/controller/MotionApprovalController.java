/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MotionApprovalController.java
 * Created On: Jan 12, 2012
 */
package org.mkcl.els.controller;

import java.util.Locale;

import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.MotionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class MotionApprovalController.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Controller
@RequestMapping("/motion_approval")
public class MotionApprovalController extends BaseController {

    /**
     * Index.
     *
     * @param model the model
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        Grid grid = Grid.findByName("MOIS_APPROVAL_GRID");
        model.addAttribute("gridId", grid.getId());
        return "motion_information/approval/list";
    }

    /**
     * New form.
     *
     * @param model the model
     * @param errors the errors
     * @param locale the locale
     * @return the string
     * @author samiksham
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model,
                          final Error errors,
                          final Locale locale) {
        MotionInformation motionInformation = new MotionInformation();
        model.addAttribute("motionInformation", motionInformation);

        return "motion_information/approval/process";
    }

}
