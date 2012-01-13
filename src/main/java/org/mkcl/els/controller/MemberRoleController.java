/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.MemberRoleController.java
 * Created On: Jan 5, 2012
 */
package org.mkcl.els.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.mkcl.els.common.editors.AssemblyEditor;
import org.mkcl.els.common.editors.AssemblyRoleEditor;
import org.mkcl.els.common.editors.MemberEditor;
import org.mkcl.els.common.vo.AssemblyRolesVo;
import org.mkcl.els.common.vo.Filter;
import org.mkcl.els.common.vo.GridData;
import org.mkcl.els.domain.Assembly;
import org.mkcl.els.domain.AssemblyRole;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.Grid;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.MemberRole;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class MemberRoleController.
 *
 * @author nileshp
 * @since v1.0.0
 */
@Controller
@RequestMapping("/member_role")
public class MemberRoleController extends BaseController {

    /**
     * Index members.
     *
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/list", method = RequestMethod.GET)
    public String indexMembers(final Model model) {
        Grid grid = Grid.findByName("MMS_ASSIGNROLE");
        model.addAttribute("gridId", grid.getId());
        return "member_mgmt/roles/assignroles/list";
    }

    /**
     * _no members.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/new", method = RequestMethod.GET)
    public String noMembers(final Model model, final Locale locale) {
        return "member_mgmt/roles/assignroles/nomembers";
    }

    /**
     * _assignroles.
     *
     * @param model the model
     * @param locale the locale
     * @param memberId the member id
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/{member_id}/new",
            method = RequestMethod.GET)
    public String assignroles(final Model model,
                               final Locale locale,
                               @PathVariable("member_id") final Long memberId) {
        MemberRole memberRole = new MemberRole();
        MemberDetails memberDetails = MemberDetails.findById(memberId);
        memberRole.setMember(memberDetails);
        Assembly assembly = Assembly.findCurrentAssembly(locale.toString());
        memberRole.setAssembly(assembly);
        memberRole.setFromDate(assembly.getAssemblyStartDate());
        if (assembly.getAssemblyDissolvedOn() != null) {
            memberRole
                    .setToDate(!assembly.getAssemblyDissolvedOn().isEmpty() ? assembly
                            .getAssemblyDissolvedOn() : assembly
                            .getAssemblyEndDate());
        } else {
            memberRole.setToDate(assembly.getAssemblyEndDate());
        }
        memberRole.setLocale(locale.toString());
        model.addAttribute("memberRole", memberRole);
        model.addAttribute("roles", MemberRole.getUnassignedRoles(
                memberDetails, assembly, locale.toString()));
        populateModel(model, locale.toString());
        model.addAttribute("assignmentDate", new SimpleDateFormat(
                CustomParameter.findByName("SERVER_DATEFORMAT").getValue())
                .format(new Date()));
        return "member_mgmt/roles/assignroles/new";
    }

    /**
     * _editroles.
     *
     * @param model the model
     * @param locale the locale
     * @param memberId the member id
     * @param request the request
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/{member_id}/edit",
            method = RequestMethod.GET)
    public String editroles(final Model model,
                             final Locale locale,
                             @PathVariable("member_id") final Long memberId,
                             final HttpServletRequest request) {
        List<MemberRole> memberRoles = MemberRole.findByMemberId(memberId);
        model.addAttribute("memberRoles", memberRoles);
        populateModel(model, locale.toString());
        model.addAttribute("memberId", memberId);
        MemberDetails memberDetails = MemberDetails.findById(memberId);
        model.addAttribute(
                "memberName",
                memberDetails.getFirstName() + " "
                        + memberDetails.getMiddleName() + " "
                        + memberDetails.getLastName());
        model.addAttribute("noOfRecords", memberRoles.size());
        request.getSession().setAttribute("refresh", "");
        return "member_mgmt/roles/assignroles/edit";
    }

    /**
     * _edit member role.
     *
     * @param model the model
     * @param locale the locale
     * @param memberRoleId the member role id
     * @param request the request
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/memberrole/{memberrole_id}/edit",
            method = RequestMethod.GET)
    public String editMemberRole(final Model model,
                                  final Locale locale,
                                  @PathVariable("memberrole_id") final Long memberRoleId,
                                  final HttpServletRequest request) {
        MemberRole memberRole = MemberRole.findById(memberRoleId);
        List<String> allStatus = new ArrayList<String>();
        allStatus.add(CustomParameter.findByName("MEMBERROLE_ASSIGNED")
                .getValue());
        allStatus.add(CustomParameter.findByName("MEMBERROLE_UNASSIGNED")
                .getValue());
        model.addAttribute("allStatus", allStatus);
        model.addAttribute("memberRole", memberRole);
        model.addAttribute("assignmentDate", new SimpleDateFormat(
                CustomParameter.findByName("SERVER_DATEFORMAT").getValue())
                .format(new Date()));
        return "member_mgmt/roles/assignroles/edit_memberrole";
    }

    /**
     * Creates the roles.
     *
     * @param locale the locale
     * @param assignmentDate the assignment date
     * @param memberRole the member role
     * @param result the result
     * @param model the model
     * @param request the request
     * @param member the member
     * @param roles the roles
     * @param roles_check the roles_check
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/createMemberRoles",
            method = RequestMethod.POST)
    public String createRoles(final Locale locale,
                              @RequestParam final String assignmentDate,
                              @Valid @ModelAttribute("memberRole") final MemberRole memberRole,
                              final BindingResult result,
                              final Model model,
                              final HttpServletRequest request,
                              @RequestParam final Long member,
                              @RequestParam(required = false) final String[] roles,
                              @RequestParam(required = false) final String[] roles_check) {
        StringBuffer selectedRoles = new StringBuffer();
        new HashMap<String, MemberRole>();
        /*
         * Check:Selected user is not a member and selected roles is null then
         * role not null is displayed.
         */
        if (!MemberRole.isMember(
                memberRole.getMember(), memberRole.getAssembly(),
                memberRole.getFromDate(), memberRole.getToDate())) {
            /*
             * The selected user is not a member and is tried to assign a role
             * for a given assembly and period.
             */
            int count = 0;
            if (roles_check != null) {
                for (String i : roles_check) {
                    if (i.equals(CustomParameter.findByName(
                            "DEFAULT_MEMBERROLE").getValue())) {
                        count++;
                    }
                }
                if (count == 0) {
                    result.rejectValue("role", "NotMember");
                }
                for (String i : roles) {
                    memberRole.setRole(AssemblyRole.findById(Long.parseLong(i)));
                    MemberRole duplicateMemberRole =
                            MemberRole.checkForDuplicateMemberRole(memberRole);
                    if (duplicateMemberRole.getId() != null) {
                        this.validate(
                                memberRole, result, assignmentDate,
                                duplicateMemberRole);
                    } else {
                        this.validate(memberRole, result, assignmentDate);
                    }
                    selectedRoles.append(i + ",");
                }
            } else {
                result.rejectValue("role", "NotNull");
                }
            }
        /*
         * Check:Selected user is a member and selected roles is null then role
         * not null is displayed.
         */
        else {
            if (roles != null) {
                for (String i : roles) {
                    memberRole.setRole(AssemblyRole.findById(Long.parseLong(i)));
                    MemberRole duplicateMemberRole =
                            MemberRole.checkForDuplicateMemberRole(memberRole);
                    if (duplicateMemberRole.getId() != null) {
                        this.validate(
                                memberRole, result, assignmentDate,
                                duplicateMemberRole);
                    } else {
                        this.validate(memberRole, result, assignmentDate);
                    }
                    selectedRoles.append(i + ",");
                }
            } else {
                result.rejectValue("role", "NotNull");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("memberRole", memberRole);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            model.addAttribute("selectedroles", selectedRoles.toString());
            populateModel(model, locale.toString());
            model.addAttribute("roles", MemberRole.getUnassignedRoles(
                    memberRole.getMember(), memberRole.getAssembly(),
                    locale.toString()));
            model.addAttribute("assignmentDate", assignmentDate);
            return "member_mgmt/roles/assignroles/new";
        }
        /*
         * A member role is updated only if an entry already exists for the
         * given assembly,member,from date,to date but has been previously
         * unassigned. A new member role is created if there is no entry for the
         * given assembly,member,from date and to date.
         */
        SimpleDateFormat format = new SimpleDateFormat(CustomParameter
                .findByName("SERVER_DATEFORMAT").getValue());
        for (String i : roles) {
            MemberRole memberRoleToInsert = MemberRole.newInstance(memberRole);

            try {
                if (format.parse(memberRole.getToDate()).before(
                        format.parse(assignmentDate))) {
                    memberRoleToInsert.setStatus(CustomParameter.findByName(
                            "MEMBERROLE_UNASSIGNED").getValue());
                } else {
                    memberRoleToInsert.setStatus(CustomParameter.findByName(
                            "MEMBERROLE_ASSIGNED").getValue());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            memberRoleToInsert.persist();
        }
        return "redirect:/member_role/assignroles/"
                + memberRole.getMember().getId()
                + "/edit?type=success&msg=create_success";

    }

    /**
     * Unassign member roles.
     *
     * @param memberRolesToUnassign the member roles to unassign
     * @param memberId the member id
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/unassignMemberRoles",
            method = RequestMethod.POST)
    public String unassignMemberRoles(@RequestParam final String memberRolesToUnassign,
                                      @RequestParam final Long memberId) {
        String[] temp = memberRolesToUnassign.split(",");
        String assignmentDate = new SimpleDateFormat(CustomParameter
                .findByName("SERVER_DATEFORMAT").getValue()).format(new Date());
        for (String i : temp) {
            if (!i.equals("")) {
                MemberRole memberRole = MemberRole.findById(Long
                        .parseLong(i));
                memberRole.setToDate(assignmentDate);
                memberRole.setStatus(CustomParameter.findByName(
                        "MEMBERROLE_UNASSIGNED").getValue());
                memberRole.update();
            }
        }
        return "redirect:/member_role/assignroles/" + memberId
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete member roles.
     *
     * @param memberRolesToDelete the member roles to delete
     * @param memberId the member id
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/deleteMemberRoles",
            method = RequestMethod.POST)
    public String deleteMemberRoles(@RequestParam final String memberRolesToDelete,
                                    @RequestParam final String memberId) {
        String[] temp = memberRolesToDelete.split(",");
        for (String i : temp) {
            if (!i.equals("")) {
                MemberRole memberRole = MemberRole.findById(Long.parseLong(i));
                memberRole.remove();
            }
        }
        return "redirect:/member_role/assignroles/" + memberId
                + "/edit?type=success&msg=delete_success";
    }

    /**
     * Update roles.
     *
     * @param model the model
     * @param request the request
     * @param member the member
     * @param assignmentDate the assignment date
     * @param memberRole the member role
     * @param result the result
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignroles/updateMemberRole",
            method = RequestMethod.POST)
    public String updateRoles(final Model model,
                              final HttpServletRequest request,
                              @RequestParam final Long member,
                              @RequestParam final String assignmentDate,
                              @Valid @ModelAttribute("memberRole") final MemberRole memberRole,
                              final BindingResult result) {
        this.validate(memberRole, result, assignmentDate);
        if (result.hasErrors()) {
            model.addAttribute("memberRole", memberRole);
            model.addAttribute("assignmentDate", assignmentDate);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "member_mgmt/roles/assignroles/edit_memberrole";
        }
        memberRole.update();
        return "redirect:/member_role/assignroles/" + member
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Gets the assigned roles.
     *
     * @param memberId the member id
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
     * @return the assigned roles
     * @throws ClassNotFoundException the class not found exception
     */
    @RequestMapping(value = "/assignroles/assigned/{memberId}",
            method = RequestMethod.GET)
    public @ResponseBody
    GridData getAssignedRoles(@PathVariable final Long memberId,
                              @RequestParam(value = "page", required = false) final Integer page,
                              @RequestParam(value = "rows", required = false) final Integer rows,
                              @RequestParam(value = "sidx", required = false) final String sidx,
                              @RequestParam(value = "sord", required = false) final String order,
                              @RequestParam(value = "_search", required = false)
    final Boolean search,
                              @RequestParam(value = "searchField",
                                      required = false) final String searchField,
                              @RequestParam(value = "searchString",
                                      required = false) final String searchString,
                              @RequestParam(value = "searchOper",
                                      required = false) final String searchOper,
                              @RequestParam(value = "filters", required = false)
final String filtersData,
                              @RequestParam(value = "baseFilters",
                                      required = false) final String baseFilters,
                              final Model model,
                              final HttpServletRequest request,
                              final Locale locale)
            throws ClassNotFoundException {

        Filter filter = Filter.create(filtersData);
        if (search) {
            return MemberRole.getAssignedUnassignedRoles(
                    memberId, rows, page, sidx, order, filter.toSQl(), locale);
        } else {
            return MemberRole.getAssignedUnassignedRoles(
                    memberId, rows, page, sidx, order, locale);
        }
    }

    /**
     * Gets the unassigned roles.
     *
     * @param member the member
     * @param assembly the assembly
     * @param locale the locale
     * @return the unassigned roles
     */
    @RequestMapping(value = "unassignedroles/{member}/{assembly}")
    public @ResponseBody
    AssemblyRolesVo getUnassignedRoles(@PathVariable("member") final Long member,
                                       @PathVariable("assembly") final Long assembly,
                                       final Locale locale) {
        AssemblyRolesVo assemblyRolesVo = new AssemblyRolesVo();
        assemblyRolesVo.setRoles(MemberRole.getUnassignedRoles(
                MemberDetails.findById(member),
                Assembly.findById(assembly), locale.toString()));
        Assembly tempAssembly = Assembly.findById(assembly);
        assemblyRolesVo.setFromDate(tempAssembly.getAssemblyStartDate());
        if (tempAssembly.getAssemblyDissolvedOn() != null) {
            if (!tempAssembly.getAssemblyDissolvedOn().isEmpty()) {
                assemblyRolesVo
                        .setToDate(tempAssembly.getAssemblyDissolvedOn());
            } else {
                assemblyRolesVo.setToDate(tempAssembly.getAssemblyEndDate());
            }
        } else {
            assemblyRolesVo.setToDate(tempAssembly.getAssemblyEndDate());
        }
        return assemblyRolesVo;
    }

    /**
     * Populate model.
     *
     * @param model the model
     * @param locale the locale
     * @author nileshp
     * @since v1.0.0 Populate model.
     */
    private void populateModel(final Model model, final String locale) {
        model.addAttribute(
                "assemblies", Assembly.findAllSorted("assembly", locale, false));
        // model.addAttribute("roles",
        // assemblyRoleService.findAllSorted(locale.toString()));
    }

    /*
     * ##########################################################################
     * ################# ASSIGN MEMBERS MODULE
     * ##################################
     * #########################################################
     */

    /**
     * Index roles.
     *
     * @param model the model
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/list", method = RequestMethod.GET)
    public String indexRoles(final Model model) {
        Grid grid = Grid.findByName("MMS_ASSIGNMEMBER");
        model.addAttribute("gridId", grid.getId());
        return "member_mgmt/roles/assignmembers/list";
    }

    /**
     * _assignmembers.
     *
     * @param model the model
     * @param locale the locale
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/new", method = RequestMethod.GET)
    public String assignmembers(final Model model, final Locale locale) {
        return "member_mgmt/roles/assignmembers/noroles";
    }

    /**
     * _assignmembers.
     *
     * @param model the model
     * @param locale the locale
     * @param roleId the role id
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/{role_id}/new",
            method = RequestMethod.GET)
    public String assignmembers(final Model model,
                                 final Locale locale,
                                 @PathVariable("role_id") final Long roleId) {
        MemberRole memberRole = new MemberRole();
        Assembly assembly = Assembly.findCurrentAssembly(locale.toString());
        memberRole.setAssembly(assembly);
        memberRole.setFromDate(assembly.getAssemblyStartDate());
        if (assembly.getAssemblyDissolvedOn() != null) {
            memberRole
                    .setToDate(!assembly.getAssemblyDissolvedOn().isEmpty() ? assembly
                            .getAssemblyDissolvedOn() : assembly
                            .getAssemblyEndDate());
        } else {
            memberRole.setToDate(assembly.getAssemblyEndDate());
        }
        memberRole.setRole(AssemblyRole.findById(roleId));
        memberRole.setLocale(locale.toString());
        model.addAttribute("memberRole", memberRole);
        model.addAttribute("assignmentDate", new SimpleDateFormat(
                CustomParameter.findByName("SERVER_DATEFORMAT").getValue())
                .format(new Date()));
        populateModelMemberNew(model, locale.toString());
        return "member_mgmt/roles/assignmembers/new";
    }

    /**
     * _updatemembers.
     *
     * @param model the model
     * @param locale the locale
     * @param roleId the role id
     * @param request the request
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/{role_id}/edit",
            method = RequestMethod.GET)
    public String updatemembers(final Model model,
                                 final Locale locale,
                                 @PathVariable("role_id") final Long roleId,
                                 final HttpServletRequest request) {
        List<MemberRole> memberRoles = MemberRole.findByRoleId(roleId);
        model.addAttribute("memberRoles", memberRoles);
        model.addAttribute("noOfRecords", memberRoles.size());
        request.getSession().setAttribute("refresh", "");
        model.addAttribute("role", AssemblyRole.findById(roleId));
        populateModelMemberEdit(model, locale.toString(), roleId);
        return "member_mgmt/roles/assignmembers/edit";
    }

    /**
     * _edit member role members.
     *
     * @param model the model
     * @param locale the locale
     * @param memberRoleId the member role id
     * @param request the request
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/memberrole/{memberrole_id}/edit",
            method = RequestMethod.GET)
    public String editMemberRoleMembers(final Model model,
                                         final Locale locale,
                                         @PathVariable("memberrole_id") final Long memberRoleId,
                                         final HttpServletRequest request) {
        MemberRole memberRole = MemberRole.findById(memberRoleId);
        List<String> allStatus = new ArrayList<String>();
        allStatus.add(CustomParameter.findByName("MEMBERROLE_ASSIGNED")
                .getValue());
        allStatus.add(CustomParameter.findByName("MEMBERROLE_UNASSIGNED")
                .getValue());
        model.addAttribute("allStatus", allStatus);
        model.addAttribute("memberRole", memberRole);
        model.addAttribute("assignmentDate", new SimpleDateFormat(
                CustomParameter.findByName("SERVER_DATEFORMAT").getValue())
                .format(new Date()));
        return "member_mgmt/roles/assignmembers/edit_memberrole";
    }

    /**
     * Creates the member roles.
     *
     * @param request the request
     * @param membersToAssign the members to assign
     * @param memberRole the member role
     * @param result the result
     * @param model the model
     * @param assignmentDate the assignment date
     * @param locale the locale
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/createMemberRoles",
            method = RequestMethod.POST)
    public String createMemberRoles(final HttpServletRequest request,
                                    @RequestParam final String membersToAssign,
                                    @Valid @ModelAttribute("memberRole")
    final MemberRole memberRole,
                                    final BindingResult result,
                                    final Model model,
                                    @RequestParam final String assignmentDate,
                                    final Locale locale) {
        this.validate(memberRole, result, assignmentDate);
        /*
         * Check:Atleast one member is selected
         */
        if (membersToAssign.isEmpty()) {
            result.rejectValue("member", "NotNull");
        }
        if (result.hasErrors()) {
            model.addAttribute("memberRole", memberRole);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "create_failed");
            model.addAttribute("membersToAssign", membersToAssign);
            model.addAttribute("assignmentDate", new SimpleDateFormat(
                    CustomParameter.findByName("SERVER_DATEFORMAT").getValue())
                    .format(new Date()));
            populateModelMemberNew(model, locale.toString());
            return "member_mgmt/roles/assignmembers/new";
        }
        String[] temp = membersToAssign.split(",");
        for (String i : temp) {
            memberRole.setMember(MemberDetails.findById(Long
                    .parseLong(i)));
            MemberRole memberRoleToUpdate = MemberRole.checkForDuplicateMemberRole(memberRole);
            String assigned = CustomParameter.findByName("MEMBERROLE_ASSIGNED")
                    .getValue();
            if (memberRoleToUpdate != null) {
                memberRoleToUpdate.setStatus(assigned);
                memberRoleToUpdate.update();
            } else {
                MemberRole memberRoleToInsert = MemberRole
                        .newInstance(memberRole);
                memberRoleToInsert.setStatus(assigned);
                memberRoleToInsert.persist();
            }
        }
        return "redirect:/member_role/assignmembers/"
                + memberRole.getRole().getId()
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Update member.
     *
     * @param model the model
     * @param request the request
     * @param role the role
     * @param assignmentDate the assignment date
     * @param memberRole the member role
     * @param result the result
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/updateMemberRole",
            method = RequestMethod.POST)
    public String updateMember(final Model model,
                               final HttpServletRequest request,
                               @RequestParam final Long role,
                               @RequestParam final String assignmentDate,
                               @Valid @ModelAttribute("memberRole") final MemberRole memberRole,
                               final BindingResult result) {
        this.validate(memberRole, result, assignmentDate);
        if (result.hasErrors()) {
            model.addAttribute("memberRole", memberRole);
            model.addAttribute("assignmentDate", assignmentDate);
            model.addAttribute("type", "error");
            model.addAttribute("msg", "update_failed");
            return "member_mgmt/roles/assignmembers/edit_memberrole";
        }
        memberRole.update();
        return "redirect:/member_role/assignmembers/" + role
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Unassign member roles member.
     *
     * @param memberRolesToUnassign the member roles to unassign
     * @param roleId the role id
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/unassignMemberRoles",
            method = RequestMethod.POST)
    public String unassignMemberRolesMember(@RequestParam final String memberRolesToUnassign,
                                            @RequestParam final Long roleId) {
        String[] temp = memberRolesToUnassign.split(",");
        for (String i : temp) {
            if (!i.equals("")) {
                MemberRole memberRole = MemberRole.findById(Long
                        .parseLong(i));
                memberRole.setStatus(CustomParameter.findByName(
                        "MEMBERROLE_UNASSIGNED").getValue());
                memberRole.update();
            }
        }
        return "redirect:/member_role/assignmembers/" + roleId
                + "/edit?type=success&msg=update_success";
    }

    /**
     * Delete member roles member.
     *
     * @param memberRolesToDelete the member roles to delete
     * @param roleId the role id
     * @return the string
     * @author nileshp
     * @since v1.0.0
     */
    @RequestMapping(value = "assignmembers/deleteMemberRoles",
            method = RequestMethod.POST)
    public String deleteMemberRolesMember(@RequestParam final String memberRolesToDelete,
                                          @RequestParam final String roleId) {
        String[] temp = memberRolesToDelete.split(",");
        for (String i : temp) {
            if (!i.equals("")) {
                MemberRole memberRole = MemberRole.findById(Long.parseLong(i));
                memberRole.remove();
            }
        }

        return "redirect:/member_role/assignmembers/" + roleId
                + "/edit?type=success&msg=delete_success";
    }

    /**
     * Gets the assigned members.
     *
     * @param roleId the role id
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
     * @return the assigned members
     * @throws ClassNotFoundException the class not found exception
     */
    @RequestMapping(value = "/assignmembers/assigned/{roleId}",
            method = RequestMethod.GET)
    public @ResponseBody
    GridData getAssignedMembers(@PathVariable final Long roleId,
                                @RequestParam(value = "page", required = false) final Integer page,
                                @RequestParam(value = "rows", required = false) final Integer rows,
                                @RequestParam(value = "sidx", required = false) final String sidx,
                                @RequestParam(value = "sord", required = false) final String order,
                                @RequestParam(value = "_search",
                                        required = false) final Boolean search,
                                @RequestParam(value = "searchField",
                                        required = false) final String searchField,
                                @RequestParam(value = "searchString",
                                        required = false) final String searchString,
                                @RequestParam(value = "searchOper",
                                        required = false) final String searchOper,
                                @RequestParam(value = "filters",
                                        required = false) final String filtersData,
                                @RequestParam(value = "baseFilters",
                                        required = false) final String baseFilters,
                                final Model model,
                                final HttpServletRequest request,
                                final Locale locale)
            throws ClassNotFoundException {

        Filter filter = Filter.create(filtersData);
        if (search) {
            return MemberRole.getAssignedUnassignedMembers(
                    roleId, rows, page, sidx, order, filter.toSQl(), locale);
        } else {
            return MemberRole.getAssignedUnassignedMembers(
                    roleId, rows, page, sidx, order, locale);
        }
    }

    /**
     * Gets the un assigned roles.
     *
     * @param memberId the member id
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
     * @return the un assigned roles
     * @throws ClassNotFoundException the class not found exception
     */
    @RequestMapping(value = "/assignmembers/unassigned/{memberId}",
            method = RequestMethod.GET)
    public @ResponseBody
    GridData getUnAssignedRoles(@PathVariable final Long memberId,
                                @RequestParam(value = "page", required = false) final Integer page,
                                @RequestParam(value = "rows", required = false) final Integer rows,
                                @RequestParam(value = "sidx", required = false) final String sidx,
                                @RequestParam(value = "sord", required = false) final String order,
                                @RequestParam(value = "_search",
                                        required = false) final Boolean search,
                                @RequestParam(value = "searchField",
                                        required = false) final String searchField,
                                @RequestParam(value = "searchString",
                                        required = false) final String searchString,
                                @RequestParam(value = "searchOper",
                                        required = false) final String searchOper,
                                @RequestParam(value = "filters",
                                        required = false) final String filtersData,
                                @RequestParam(value = "baseFilters",
                                        required = false) final String baseFilters,
                                final Model model,
                                final HttpServletRequest request,
                                final Locale locale)
            throws ClassNotFoundException {

        Filter filter = Filter.create(filtersData);
        if (search) {
            return MemberRole.getUnAssignedMembers(
                    memberId, rows, page, sidx, order, filter.toSQl(), locale);
        } else {
            return MemberRole.getUnAssignedMembers(
                    memberId, rows, page, sidx, order, locale);
        }
    }

    /**
     * Populate model member new.
     *
     * @param model the model
     * @param locale the locale
     * @author nileshp
     * @since v1.0.0 Populate model member new.
     */
    private void populateModelMemberNew(final Model model, final String locale) {
        model.addAttribute(
                "assemblies", Assembly.findAllSorted("assembly", locale, false));
        model.addAttribute("roles", AssemblyRole.findAllSorted("name", locale, false));
    }

    /**
     * Populate model member edit.
     *
     * @param model the model
     * @param locale the locale
     * @param roleId the role id
     * @author nileshp
     * @since v1.0.0 Populate model member edit.
     */
    private void populateModelMemberEdit(final Model model,
                                         final String locale,
                                         final Long roleId) {
        model.addAttribute(
                "assemblies", Assembly.findAllSorted("assembly", locale, false));
    }

    /**
     * Inits the binder.
     *
     * @param binder the binder
     * @author nileshp
     * @since v1.0.0 Inits the binder.
     */
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CustomParameter
                .findByName("SERVER_DATEFORMAT").getValue());
        dateFormat.setLenient(true);
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
                dateFormat, true));
        binder.registerCustomEditor(Assembly.class, new AssemblyEditor());
        binder.registerCustomEditor(AssemblyRole.class, new AssemblyRoleEditor());
        binder.registerCustomEditor(MemberDetails.class, new MemberEditor());
    }

    /**
     * Validate.
     *
     * @param memberRole the member role
     * @param errors the errors
     * @param assignmentDate the assignment date
     * @author nileshp
     * @since v1.0.0 Validate.
     */
    private void validate(final MemberRole memberRole,
                          final Errors errors,
                          final String assignmentDate) {
        try {
            if (memberRole.getAssembly() != null) {
                SimpleDateFormat format = new SimpleDateFormat(CustomParameter
                        .findByName("SERVER_DATEFORMAT").getValue());
                Date assemblyStartDate = null;
                Date assemblyEndDate = null;
                Date assemblyDissolveDate = null;
                Date roleIsAssignedOn = null;
                Date fromDate = null;
                Date toDate = null;
                String strAssemblyStartDate = memberRole.getAssembly()
                        .getAssemblyStartDate();
                String strAssemblyEndDate = memberRole.getAssembly()
                        .getAssemblyEndDate();
                String strAssemblyDissolveDate = memberRole.getAssembly()
                        .getAssemblyDissolvedOn();
                String strFromDate = memberRole.getFromDate();
                String strToDate = memberRole.getToDate();
                String[] errorArgs = new String[6];
                if (strAssemblyStartDate != null) {
                    if (!strAssemblyStartDate.isEmpty()) {
                        errorArgs[0] = strAssemblyStartDate;
                        assemblyStartDate = format.parse(strAssemblyStartDate);
                    }
                }
                if (strAssemblyEndDate != null) {
                    if (!strAssemblyEndDate.isEmpty()) {
                        errorArgs[1] = strAssemblyEndDate;
                        assemblyEndDate = format.parse(strAssemblyEndDate);
                    }
                }
                if (strAssemblyDissolveDate != null) {
                    if (!strAssemblyDissolveDate.isEmpty()) {
                        errorArgs[2] = strAssemblyDissolveDate;
                        assemblyDissolveDate = format
                                .parse(strAssemblyDissolveDate);
                    }
                }
                if (assignmentDate != null) {
                    if (!assignmentDate.isEmpty()) {
                        errorArgs[3] = assignmentDate;
                        roleIsAssignedOn = format.parse(assignmentDate);
                    }
                }
                if (strFromDate != null) {
                    if (!strFromDate.isEmpty()) {
                        errorArgs[4] = strFromDate;
                        fromDate = format.parse(strFromDate);
                    }
                }
                if (strToDate != null) {
                    if (!strToDate.isEmpty()) {
                        errorArgs[5] = strToDate;
                        toDate = format.parse(strToDate);
                    }
                }

                /*
                 * Check:For current assembly member roles cannot be assigned
                 * after assembly has ended or dissolved.
                 */
                if (memberRole.getAssembly().isCurrentAssembly()) {
                    if (assemblyEndDate != null) {
                        if (roleIsAssignedOn.after(assemblyEndDate)) {
                            errors.rejectValue(
                                    "assembly",
                                    "RoleAssignment_AfterAssemblyEnded",
                                    errorArgs,
                                    "Role cannot be assigned after assembly end date:{1}");
                        }
                    }
                    if (assemblyDissolveDate != null) {
                        if (roleIsAssignedOn.after(assemblyDissolveDate)) {
                            errors.rejectValue(
                                    "assembly",
                                    "RoleAssignment_AfterAssemblyDissolved",
                                    errorArgs,
                                    "Role cannot be assigned after assembly dissolution date:{2}");
                        }
                    }
                }
                /*
                 * Check:from date and to date must be between assembly start
                 * date and end date or start date and dissolved date if
                 * assembly dissolved date is not null.
                 */
                if (fromDate != null && assemblyStartDate != null
                        & assemblyEndDate != null) {
                    if (assemblyDissolveDate != null) {
                        if (fromDate.before(assemblyStartDate)
                                || fromDate.after(assemblyDissolveDate)) {
                            errors.rejectValue(
                                    "fromDate",
                                    "FromDate_NotBetween_AssemblyStartDissolveDate",
                                    errorArgs,
                                    "From Date ({4}) must be between assembly start date ({0}) "
                                    + "and dissolution date ({2})");
                        }
                    } else if (fromDate.before(assemblyStartDate)
                            || fromDate.after(assemblyEndDate)) {
                        errors.rejectValue(
                                "fromDate",
                                "FromDate_NotBetween_AssemblyStartEndDate",
                                errorArgs,
                                "From Date ({4}) must be between assembly "
                                + "start date ({0}) and end date ({1})");
                    }
                }
                if (toDate != null && assemblyStartDate != null
                        & assemblyEndDate != null) {
                    if (assemblyDissolveDate != null) {
                        if (toDate.before(assemblyStartDate)
                                || toDate.after(assemblyDissolveDate)) {
                            errors.rejectValue(
                                    "toDate",
                                    "ToDate_NotBetween_AssemblyStartDissolveDate",
                                    errorArgs,
                                    "To Date ({5}) must be between assembly start date ({0}) "
                                    + "and dissolution date ({2})");
                        }
                    } else if (toDate.before(assemblyStartDate)
                            || toDate.after(assemblyEndDate)) {
                        errors.rejectValue(
                                "toDate",
                                "ToDate_NotBetween_AssemblyStartEndDate",
                                errorArgs,
                                "To Date ({5}) must be between assembly start date ({0})"
                                + "and end date ({1})");
                    }
                }

                if (toDate != null && fromDate != null) {
                    if (toDate.before(fromDate)) {
                        errors.rejectValue(
                                "toDate", "ToDate_LTFromDate", errorArgs,
                                "To Date must be greater than from date({4})");
                    }
                    if (fromDate.after(toDate)) {
                        errors.rejectValue(
                                "fromDate", "FromDate_GTToDate", errorArgs,
                                "From Date must be less than to date({5})");
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validate.
     *
     * @param memberRole the member role
     * @param errors the errors
     * @param assignmentDate the assignment date
     * @param duplicateMemberRole the duplicate member role
     * @author nileshp
     * @since v1.0.0 Validate.
     */
    private void validate(final MemberRole memberRole,
                          final Errors errors,
                          final String assignmentDate,
                          final MemberRole duplicateMemberRole) {
        try {
            if (memberRole.getAssembly() != null) {
                SimpleDateFormat format = new SimpleDateFormat(CustomParameter
                        .findByName("SERVER_DATEFORMAT").getValue());
                Date assemblyStartDate = null;
                Date assemblyEndDate = null;
                Date assemblyDissolveDate = null;
                Date roleIsAssignedOn = null;
                Date fromDate = null;
                Date toDate = null;
                Date duplicateFromDate = null;
                Date duplicateToDate = null;
                String strAssemblyStartDate = memberRole.getAssembly()
                        .getAssemblyStartDate();
                String strAssemblyEndDate = memberRole.getAssembly()
                        .getAssemblyEndDate();
                String strAssemblyDissolveDate = memberRole.getAssembly()
                        .getAssemblyDissolvedOn();
                String strFromDate = memberRole.getFromDate();
                String strToDate = memberRole.getToDate();
                String strDuplicateFromDate = duplicateMemberRole.getFromDate();
                String strDuplicateToDate = duplicateMemberRole.getToDate();
                String[] errorArgs = new String[11];
                if (strAssemblyStartDate != null) {
                    if (!strAssemblyStartDate.isEmpty()) {
                        errorArgs[0] = strAssemblyStartDate;
                        assemblyStartDate = format.parse(strAssemblyStartDate);
                    }
                }
                if (strAssemblyEndDate != null) {
                    if (!strAssemblyEndDate.isEmpty()) {
                        errorArgs[1] = strAssemblyEndDate;
                        assemblyEndDate = format.parse(strAssemblyEndDate);
                    }
                }
                if (strAssemblyDissolveDate != null) {
                    if (!strAssemblyDissolveDate.isEmpty()) {
                        errorArgs[2] = strAssemblyDissolveDate;
                        assemblyDissolveDate = format
                                .parse(strAssemblyDissolveDate);
                    }
                }
                if (assignmentDate != null) {
                    if (!assignmentDate.isEmpty()) {
                        errorArgs[3] = assignmentDate;
                        roleIsAssignedOn = format.parse(assignmentDate);
                    }
                }
                if (strFromDate != null) {
                    if (!strFromDate.isEmpty()) {
                        errorArgs[4] = strFromDate;
                        fromDate = format.parse(strFromDate);
                    }
                }
                if (strToDate != null) {
                    if (!strToDate.isEmpty()) {
                        errorArgs[5] = strToDate;
                        toDate = format.parse(strToDate);
                    }
                }
                if (strDuplicateFromDate != null) {
                    if (!strDuplicateFromDate.isEmpty()) {
                        errorArgs[6] = strDuplicateFromDate;
                        duplicateFromDate = format.parse(strDuplicateFromDate);
                    }
                }
                if (strDuplicateToDate != null) {
                    if (!strDuplicateToDate.isEmpty()) {
                        errorArgs[7] = strDuplicateToDate;
                        duplicateToDate = format.parse(strDuplicateToDate);
                    }
                }
                errorArgs[8] = memberRole.getMember().getFullName();
                errorArgs[9] = memberRole.getAssembly().getAssembly();
                errorArgs[10] = memberRole.getRole().getName();

                /*
                 * Check:For current assembly member roles cannot be assigned
                 * after assembly has ended or dissolved.
                 */
                if (memberRole.getAssembly().isCurrentAssembly()) {
                    if (assemblyEndDate != null) {
                        if (roleIsAssignedOn.after(assemblyEndDate)) {
                            errors.rejectValue(
                                    "assembly",
                                    "RoleAssignment_AfterAssemblyEnded",
                                    errorArgs,
                                    "Role cannot be assigned after assembly end date:{1}");
                        }
                    }
                    if (assemblyDissolveDate != null) {
                        if (roleIsAssignedOn.after(assemblyDissolveDate)) {
                            errors.rejectValue(
                                    "assembly",
                                    "RoleAssignment_AfterAssemblyDissolved",
                                    errorArgs,
                                    "Role cannot be assigned after assembly dissolution date:{2}");
                        }
                    }
                }
                /*
                 * Check:from date and to date must be between assembly start
                 * date and end date or start date and dissolved date if
                 * assembly dissolved date is not null.
                 */
                if (fromDate != null && assemblyStartDate != null
                        & assemblyEndDate != null) {
                    if (assemblyDissolveDate != null) {
                        if (fromDate.before(assemblyStartDate)
                                || fromDate.after(assemblyDissolveDate)) {
                            errors.rejectValue(
                                    "fromDate",
                                    "FromDate_NotBetween_AssemblyStartDissolveDate",
                                    errorArgs,
                                    "From Date ({4}) must be between assembly start date ({0}) "
                                    + "and dissolution date ({2})");
                        }
                    } else if (fromDate.before(assemblyStartDate)
                            || fromDate.after(assemblyEndDate)) {
                        errors.rejectValue(
                                "fromDate",
                                "FromDate_NotBetween_AssemblyStartEndDate",
                                errorArgs,
                                "From Date ({4}) must be between assembly start date ({0})"
                                + "and end date ({1})");
                    }
                }
                if (toDate != null && assemblyStartDate != null
                        & assemblyEndDate != null) {
                    if (assemblyDissolveDate != null) {
                        if (toDate.before(assemblyStartDate)
                                || toDate.after(assemblyDissolveDate)) {
                            errors.rejectValue(
                                    "toDate",
                                    "ToDate_NotBetween_AssemblyStartDissolveDate",
                                    errorArgs,
                                    "To Date ({5}) must be between assembly start date ({0}) "
                                    + "and dissolution date ({2})");
                        }
                    } else if (toDate.before(assemblyStartDate)
                            || toDate.after(assemblyEndDate)) {
                        errors.rejectValue(
                                "toDate",
                                "ToDate_NotBetween_AssemblyStartEndDate",
                                errorArgs,
                                "To Date ({5}) must be between assembly start date ({0}) "
                                + "and end date ({1})");
                    }
                }

                if (toDate != null && fromDate != null) {
                    if (toDate.before(fromDate)) {
                        errors.rejectValue(
                                "toDate", "ToDate_LTFromDate", errorArgs,
                                "To Date must be greater than from date({4})");
                    }
                    if (fromDate.after(toDate)) {
                        errors.rejectValue(
                                "fromDate", "FromDate_GTToDate", errorArgs,
                                "From Date must be less than to date({5})");
                    }
                    /*
                     * From date and To date is same as that of duplicate entry.
                     * From date and To date is between the from date and to
                     * date of duplicate entry. From date is between the from
                     * date and to date of duplicate entry and to date > to date
                     * of duplicate entry. To date is between the from and to
                     * date of duplicate entry and from date < from date of
                     * duplicate entry.
                     */
                    if (duplicateFromDate != null & duplicateToDate != null) {

                        if (fromDate.equals(duplicateFromDate)
                                && toDate.equals(duplicateToDate)) {
                            errors.rejectValue(
                                    "assembly",
                                    "DuplicateMemberRoleEntry",
                                    errorArgs,
                                    "Entry already exists for member({8}) having role ({10}),"
                                    + "assembly({9}) between periods ({6} and {7})");
                        }

                        if ((fromDate.after(duplicateFromDate) && toDate
                                .before(duplicateToDate))
                                || (fromDate.equals(duplicateFromDate) && toDate
                                        .before(duplicateToDate))
                                || (fromDate.after(duplicateFromDate) && toDate
                                        .equals(duplicateToDate))) {
                            errors.rejectValue(
                                    "assembly",
                                    "DuplicateMemberRoleEntryFromToBetween",
                                    errorArgs,
                                    "Entry already exists for member({8}) having role ({10}),"
                                    + "assembly({9}) between periods ({6} and {7})."
                                    + "Kindly change from and"
                                    + " to dates");
                        }

                        if (((fromDate.after(duplicateFromDate) && fromDate
                                .before(duplicateToDate))
                                || (fromDate.equals(duplicateFromDate)) || (fromDate
                                    .equals(duplicateToDate)))
                                && toDate.after(duplicateToDate)) {
                            errors.rejectValue(
                                    "assembly",
                                    "DuplicateMemberRoleEntryFromBetween",
                                    errorArgs,
                                    "Entry already exists for member({8}) having role ({10}),"
                                            + "assembly({9}) between periods ({6} and {7})."
                                            + "Kindly change "
                                            + "from date to something greater than {7}");
                        }

                        if (((toDate.after(duplicateFromDate) && toDate
                                .before(duplicateToDate))
                                || (toDate.equals(duplicateFromDate)) || (toDate
                                    .equals(duplicateToDate)))
                                && fromDate.before(duplicateFromDate)) {
                            errors.rejectValue(
                                    "assembly",
                                    "DuplicateMemberRoleEntryToBetween",
                                    errorArgs,
                                    "Entry already exists for member({8}) having role ({10})"
                                            + ",assembly({9}) between periods ({6} and {7})."
                                            + "Kindly change "
                                            + "to date to something less than {6}");
                        }
                        if (toDate.after(duplicateToDate)
                                && fromDate.before(duplicateFromDate)) {
                            errors.rejectValue(
                                    "assembly",
                                    "DuplicateMemberRoleEntryToFromNotBetween",
                                    errorArgs,
                                    "Entry already exists for member({8}) having role ({10}),"
                                            + "assembly({9}) between periods ({6} and {7})."
                                            + "Kindly change both "
                                            + "from and to date to less than {6} "
                                            + "or greater than {7}");
                        }
                    }
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
