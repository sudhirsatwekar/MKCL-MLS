/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MemberContactDetailsController.java
 * Created On: Dec 19, 2011
 */
package org.mkcl.els.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.common.editors.DistrictEditor;
import org.mkcl.els.common.editors.StateEditor;
import org.mkcl.els.common.editors.TehsilEditor;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.State;
import org.mkcl.els.domain.Tehsil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The Class MemberContactDetailsController.
 *
 * @author nileshp
 * @since v1.0.0
 */
@Controller
@RequestMapping("/member_contact_details")
public class MemberContactDetailsController {

    /**
     * Edits the.
     *
     * @param request the request
     * @param id the id
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(final HttpServletRequest request,
            @PathVariable final Long id, final ModelMap model) {
        final MemberDetails memberContactDetails = MemberDetails.findById(id);
        populateModel(model, memberContactDetails);
        request.getSession().setAttribute("refresh", "");
        return "member_details/contact/edit";
    }

    /**
     * Edits the.
     *
     * @param memberContactDetails the member contact details
     * @param result the result
     * @param model the model
     * @param state the state
     * @return the string
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(
            @Valid @ModelAttribute("memberContactDetails") final MemberDetails memberContactDetails,
            final BindingResult result, 
            final ModelMap model, 
            final RedirectAttributes redirectAttributes) {
        this.validate(memberContactDetails, result);
        if (result.hasErrors()) {
            populateModel(model, memberContactDetails);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "member_details/contact/edit";
        }
        
        memberContactDetails.updateMemberContactDetails();
        redirectAttributes.addFlashAttribute("type","success");
        redirectAttributes.addFlashAttribute("msg","update_success");
        String returnUrl = "redirect:member_contact_details/"
                + memberContactDetails.getId() + "/edit";
        if (CustomParameter.findByName("MIS_PROGRESSIVE_DISPLAY")
                .getValue().toLowerCase().equals("progressive")) {
            returnUrl = "redirect:/member_other_details/"
                    + memberContactDetails.getId() + "/edit";
        } 
        return returnUrl;
    }

    /**
     * Validate.
     *
     * @param memberContactDetails the member contact details
     * @param result the result
     */
    private void validate(final MemberDetails memberContactDetails,
            final BindingResult result) {

    }

    /**
     * Inits the binder.
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(State.class, new StateEditor());
        binder.registerCustomEditor(District.class, new DistrictEditor());
        binder.registerCustomEditor(Tehsil.class, new TehsilEditor());

    }

    /**
     * Populate model.
     *
     * @param model the model
     * @param memberContactDetails the member contact details
     */
    private void populateModel(final ModelMap model,
            final MemberDetails memberContactDetails) {
        final List<State> states = State.findAllSorted("name"
                    , memberContactDetails.getLocale() , false);
        model.addAttribute("presentStates", states);
        model.addAttribute("permanentStates", states);
        model.addAttribute("memberContactDetails", memberContactDetails);

    }
}
