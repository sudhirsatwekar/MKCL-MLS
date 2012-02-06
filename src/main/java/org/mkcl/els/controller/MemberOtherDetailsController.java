package org.mkcl.els.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.MemberDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The Class MemberOtherDetailsController.
 *
 * @author sujitas
 * @since v1.0.0
 */
@Controller
@RequestMapping("/member_other_details")
public class MemberOtherDetailsController {

    /**
     * Edits the.
     *
     * @param request the request
     * @param id the id
     * @param model the model
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(final HttpServletRequest request,
                       @PathVariable final Long id,
                       final ModelMap model) {
        final MemberDetails memberOtherDetails = MemberDetails.findById(id);
        model.addAttribute("memberOtherDetails", memberOtherDetails);
        return "member_details/other/edit";
    }

    /**
     * Edits the.
     *
     * @param memberOtherDetails the member other details
     * @param result the result
     * @param model the model
     * @param request the request
     * @param noOfPositions the no of positions
     * @return the string
     * @author sujitas
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String edit(@Valid @ModelAttribute("memberOtherDetails")
                       final MemberDetails memberOtherDetails,
                       final BindingResult result,
                       final ModelMap model,
                       final HttpServletRequest request,
                       final RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("memberOtherDetails", memberOtherDetails);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "member_details/other/edit";
        }
        memberOtherDetails.updateMemberOtherDetails();
        redirectAttributes.addFlashAttribute("type","success");
        redirectAttributes.addFlashAttribute("msg","update_success");
        String returnUrl = "redirect:member_other_details/"
                + memberOtherDetails.getId() + "/edit";
        if (CustomParameter.findByName("MIS_PROGRESSIVE_DISPLAY").getValue()
                .toLowerCase().equals("progressive")) {
            returnUrl = "redirect:/member_personal_details/"
                    + memberOtherDetails.getId() + "/edit";
        } 
        return returnUrl;
    }

}
