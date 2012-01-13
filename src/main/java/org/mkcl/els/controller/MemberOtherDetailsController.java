package org.mkcl.els.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.MemberPositionsDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        final List<MemberPositionsDetails> memberPositionsDetails = memberOtherDetails
                .getMemberPositions();
        if (!(memberPositionsDetails.isEmpty())) {
            model.addAttribute("positions", memberPositionsDetails);
            model.addAttribute("noOfPositions", memberPositionsDetails.size());
        }
        model.addAttribute("memberOtherDetails", memberOtherDetails);
        request.getSession().setAttribute("refresh", "");
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
                       @RequestParam final int noOfPositions) {
        this.validate(memberOtherDetails, result);
        if (result.hasErrors()) {
            List<MemberPositionsDetails> positions = new ArrayList<MemberPositionsDetails>();
            for (int i = 1; i <= noOfPositions; i++) {
                MemberPositionsDetails position = new MemberPositionsDetails();
                if (request.getParameter("positionPeriod" + i) != null
                        && request.getParameter("positionDetail" + i) != null) {
                    position.setPeriod(request.getParameter("positionPeriod"
                            + i));
                    position.setDetails(request.getParameter("positionDetail"
                            + i));
                    positions.add(position);
                }
            }
            model.addAttribute("positions", positions);
            model.addAttribute("noOfPositions", positions.size());
            model.addAttribute("memberOtherDetails", memberOtherDetails);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "member_details/other/edit";
        }
        for (int i = 1; i <= noOfPositions; i++) {
            MemberPositionsDetails position = new MemberPositionsDetails();
            if (request.getParameter("positionPeriod" + i) != null
                    && request.getParameter("positionDetail" + i) != null) {
                position.setPeriod(request.getParameter("positionPeriod" + i));
                position.setDetails(request.getParameter("positionDetail" + i));
                position.setMember(memberOtherDetails);
                if (request.getParameter("positionId" + i) == null
                        || request.getParameter("positionId" + i).isEmpty()) {
                    position.persist();
                } else {
                    position.setId(Long.parseLong(request
                            .getParameter("positionId" + i)));
                    position.update();
                }
            }
        }
        memberOtherDetails.updateMemberOtherDetails();
        if (CustomParameter.findByName("MIS_PROGRESSIVE_DISPLAY").getValue()
                .toLowerCase().equals("progressive")) {
            return "redirect:/member_personal_details/"
                    + memberOtherDetails.getId()
                    + "/edit?type=success&msg=update_success";
        } else {
            return "redirect:member_other_details/"
                    + memberOtherDetails.getId()
                    + "/edit?type=success&msg=update_success";
        }
    }

    /**
     * Validate.
     *
     * @param memberOtherDetails the member other details
     * @param errors the errors
     * @author sujitas
     * @since v1.0.0
     */
    private void validate(final MemberDetails memberOtherDetails,
                          final Errors errors) {

    }
}
