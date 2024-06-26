/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MemberPersonalDetailsController.java
 * Created On: Jan 4, 2012
 */
package org.mkcl.els.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.common.editors.ConstituencyEditor;
import org.mkcl.els.common.editors.PartyEditor;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.Field;
import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.Party;
import org.mkcl.els.domain.Title;
import org.mkcl.els.service.IAssemblyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * The Class MemberPersonalDetailsController.
 *
 * @author meenalw
 * @since v1.0.0
 */
@Controller
@RequestMapping("/member_personal_details")
public class MemberPersonalDetailsController extends BaseController {

    /** The assembly role service. */
    @Autowired
    IAssemblyRoleService assemblyRoleService;

    /** The Constant FORM_NAME. */
    private static final String FORM_NAME = "MIS.PERSONAL";

    /**
     * Index.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping(value = "module", method = RequestMethod.GET)
    public String index(final ModelMap model) {
        return "member_details/module";
    }

    /**
     * String.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(final ModelMap model,
            final Locale locale) {
        Grid grid = Grid.findByName("MEMBER_DETAIL_GRID", locale.toString());
        model.addAttribute("gridId", grid.getId());
        return "member_details/personal/list";
    }

    /**
     * String.
     *
     * @param model the model
     * @param errors the errors
     * @param locale the locale
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String newForm(final ModelMap model,
            final Error errors,
            final Locale locale) {
        MemberDetails memberPersonalDetails = new MemberDetails();
        memberPersonalDetails.setLocale(locale.toString());
        populateModel(model, memberPersonalDetails);
        model.addAttribute(
                "photoExt", CustomParameter.findByName("PHOTO_EXTENSION")
                .getValue());
        model.addAttribute(
                "dateformat", CustomParameter.findByName("DATEPICKER_DATEFORMAT")
                .getValue());
        model.addAttribute("photoSize", Long.parseLong(CustomParameter
                .findByName("PHOTO_SIZE").getValue()) * 1024 * 1024);
        return "member_details/personal/new";
    }

    /**
     * String.
     *
     * @param request the request
     * @param id the id
     * @param model the model
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
    public String edit(final HttpServletRequest request,
            @PathVariable final Long id,
            final ModelMap model) {
        MemberDetails memberPersonalDetails = MemberDetails.findById(id);
        if (memberPersonalDetails.getConstituency() != null) {
            List<District> districts = memberPersonalDetails.getConstituency()
                    .getDistricts();
            StringBuffer buffer = new StringBuffer();
            String state = districts.iterator().next().getState().getName();
            for (District i : districts) {
                if (i != null) {
                    buffer.append(i.getName() + ",");
                }
            }
            buffer.deleteCharAt(buffer.length() - 1);
            model.addAttribute("district", buffer.toString());
            model.addAttribute("state", state);
        }
        populateModel(model, memberPersonalDetails);
        return "member_details/personal/edit";
    }

    /**
     * String.
     *
     * @param memberPersonalDetails the member personal details
     * @param result the result
     * @param model the model
     * @param request the request
     * @param redirectAttributes the redirect attributes
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("memberPersonalDetails")
    final MemberDetails memberPersonalDetails,
    final BindingResult result,
    final ModelMap model,
    final HttpServletRequest request,
    final RedirectAttributes redirectAttributes) {
        toggleMaritalDetails(memberPersonalDetails);
        this.validate(memberPersonalDetails, result);
        if (result.hasErrors()) {
            populateModel(model, memberPersonalDetails);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            return "member_details/personal/new";
        }
        if (CustomParameter.findByName("CREATE_DEFAULT_MEMBERROLE").getValue()
                .toLowerCase().equals("yes")) {
            memberPersonalDetails.createMemberAndDefaultRole();
        } else {
            memberPersonalDetails.persist();
        }
        request.getSession().setAttribute("refresh", "");
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("msg", "create_success");
        String returnUrl = "redirect:member_personal_details/"
                + memberPersonalDetails.getId() + "/edit";
        if (CustomParameter.findByName("MIS_PROGRESSIVE_DISPLAY").getValue()
                .toLowerCase().equals("progressive")) {
            returnUrl = "redirect:/member_contact_details/"
                    + memberPersonalDetails.getId() + "/edit";
        }
        return returnUrl;
    }


    /**
     * String.
     *
     * @param request the request
     * @param memberPersonalDetails the member personal details
     * @param result the result
     * @param model the model
     * @param redirectAttributes the redirect attributes
     * @return the string
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(method = RequestMethod.PUT)
    public String update(final HttpServletRequest request,
            @Valid @ModelAttribute("memberPersonalDetails")
    final MemberDetails memberPersonalDetails,
    final BindingResult result,
    final ModelMap model,
    final RedirectAttributes redirectAttributes) {
        toggleMaritalDetails(memberPersonalDetails);
        this.validate(memberPersonalDetails, result);
        if (result.hasErrors()) {
            populateModel(model, memberPersonalDetails);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "member_details/personal/edit";
        }
        memberPersonalDetails.updateMemberPersonalDetails();
        redirectAttributes.addFlashAttribute("type", "success");
        redirectAttributes.addFlashAttribute("msg", "update_success");
        String returnUrl = "redirect:member_personal_details/"
                + memberPersonalDetails.getId() + "/edit";
        if (CustomParameter.findByName("MIS_PROGRESSIVE_DISPLAY").getValue()
                .toLowerCase().equals("progressive")) {
            returnUrl = "redirect:/member_contact_details/"
                    + memberPersonalDetails.getId() + "/edit";
        }
        return returnUrl;
    }

    /**
     * String.
     *
     * @param id the id
     * @param model the model
     * @param request the request
     * @return true, if successful
     * @author meenalw
     * @since v1.0.0
     */
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public @ResponseBody boolean delete(@PathVariable final Long id,
            final ModelMap model,
            final HttpServletRequest request) {
        MemberDetails memberDetails = MemberDetails.findById(id);
        memberDetails.remove();
        return true;
    }

    /**
     * Delete photo.
     *
     * @param id the id
     * @param model the model
     * @param request the request
     * @return the string
     */
    @Transactional
    @RequestMapping(value = "{id}/photo", method = RequestMethod.PUT)
    public @ResponseBody boolean updatePhoto(@PathVariable final Long id,
            final ModelMap model,
            final HttpServletRequest request) {
        MemberDetails memberDetails = MemberDetails.findById(id);
        if (memberDetails.getPhoto() != null && !memberDetails.getPhoto().equals("")) {
            return memberDetails.resetPhoto();
        }
        return false;
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
        super.defaultBinders(binder);
        binder.registerCustomEditor(
                Constituency.class, new ConstituencyEditor());
        binder.registerCustomEditor(Party.class, new PartyEditor());
    }

    /**
     * Validate.
     *
     * @param memberPersonalDetails the member personal details
     * @param errors the errors
     * @author meenalw
     * @since v1.0.0
     */
    private void validate(final MemberDetails memberPersonalDetails,
            final Errors errors) {

    }

    /**
     * Populate model.
     *
     * @param model the model
     * @param memberPersonalDetails the member personal details
     * @author meenalw
     * @since v1.0.0
     */
    private void populateModel(final ModelMap model,
            final MemberDetails memberPersonalDetails) {
        List<Field> fieldsCollection = Field.findByFormNameSorted(FORM_NAME);
        // List<Integer> positionList=new ArrayList<Integer>();
        StringBuffer positionList = new StringBuffer();
        Map<String, Field> fields = new HashMap<String, Field>();
        for (Field i : fieldsCollection) {
            fields.put(i.getName(), i);
            positionList.append(i.getPosition() + "#");
        }
        model.addAttribute("positionList", positionList.toString());
        model.addAttribute("fields", fields);
        model.addAttribute("titles", Title.findAllSorted(
                "name", memberPersonalDetails.getLocale(), false));
        model.addAttribute("parties", Party.findAllSorted(
                "name", memberPersonalDetails.getLocale(), false));
        model.addAttribute("memberPersonalDetails", memberPersonalDetails);
    }

    /**
     * Toggle marital details.
     *
     * @param memberPersonalDetails the member personal details
     */
    private void toggleMaritalDetails(final MemberDetails memberPersonalDetails) {
        if (!memberPersonalDetails.isMaritalStatus()) {
            memberPersonalDetails.setMarriageDate(null);
            memberPersonalDetails.setSpouseName(null);
            memberPersonalDetails.setNoOfSons(null);
            memberPersonalDetails.setNoOfDaughter(null);
        }
    }

}
