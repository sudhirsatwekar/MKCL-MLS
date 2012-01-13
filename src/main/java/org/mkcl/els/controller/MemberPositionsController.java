/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MemberPositionsController.java
 * Created On: Jan 6, 2012
 */
package org.mkcl.els.controller;

import org.mkcl.els.domain.MemberPositionsDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class MemberPositionsController.
 *
 * @author nileshp
 * @since v1.0.0
 */
@Controller
@RequestMapping("/member_position_details")
public class MemberPositionsController extends BaseController {

    /**
     * Delete.
     *
     * @param id the id
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable final Long id, final ModelMap model) {
        MemberPositionsDetails memberPositionsDetails = MemberPositionsDetails.findById(id);
        memberPositionsDetails.remove();
        model.addAttribute("type", "success");
        model.addAttribute("msg", "delete_success");
        return "info";
    }

}
