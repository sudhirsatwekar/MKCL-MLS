/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.MemberRole.java
 * Created On: Jan 6, 2012
 */

package org.mkcl.els.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;
import org.mkcl.els.common.vo.GridData;
import org.mkcl.els.common.vo.MemberInRoleVO;
import org.mkcl.els.repository.MemberRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MemberRole.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "member_roles")
// @JsonIgnoreProperties({"member","assembly","role"})
public class MemberRole implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The member. */
    @ManyToOne(fetch = FetchType.EAGER)
    private MemberDetails member;

    /** The assembly. */
    @ManyToOne(fetch = FetchType.EAGER)
    private Assembly assembly;

    /** The role. */
    @ManyToOne(fetch = FetchType.EAGER)
    private AssemblyRole role;

    /** The fromdate. */
    @Column(length = 20)
    @NotEmpty
    private String fromDate;

    /** The to date. */
    @Column(length = 20)
    @NotEmpty
    private String toDate;

    /** The remarks. */
    @Column(length = 1000)
    private String remarks;

    /** The status. */
    @Column(length = 20)
    private String status;

    /** The locale. */
    @Column(length = 10)
    private String locale;

    /** The version. */
    @Version
    private Long version;

    /** The member role repository. */
    @Autowired
    private transient MemberRoleRepository memberRoleRepository;

    // ----------------------- constructor ------------------------
    /**
     * Instantiates a new member role.
     */
    public MemberRole() {
        super();
    }

    /**
     * Instantiates a new member role.
     *
     * @param member the member
     * @param assembly the assembly
     * @param role the role
     * @param fromDate the from date
     * @param toDate the to date
     * @param remarks the remarks
     * @param status the status
     * @param locale the locale
     * @param version the version
     */
    public MemberRole(final MemberDetails member,
            final Assembly assembly,
            final AssemblyRole role,
            final String fromDate,
            final String toDate,
            final String remarks,
            final String status,
            final String locale,
            final Long version) {
        super();
        this.member = member;
        this.assembly = assembly;
        this.role = role;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.remarks = remarks;
        this.status = status;
        this.locale = locale;
        this.version = version;
    }

    // ---------------------------------- domain methods
    // ----------------------------

    /**
     * Gets the member role repository.
     *
     * @return the member role repository
     */
    public static MemberRoleRepository getMemberRoleRepository() {
        MemberRoleRepository memberRoleRepository = new MemberRole().memberRoleRepository;
        if (memberRoleRepository == null) {
            throw new IllegalStateException(
                    "memberRole Repository has not been injected "
                            + "in memberRole domain");
        }
        return memberRoleRepository;
    }

    /**
     * New instance.
     *
     * @param memberRole the member role
     * @return the member role
     * @author nileshp
     * @since v1.0.0
     */
    public static MemberRole newInstance(final MemberRole memberRole) {
        return new MemberRole(memberRole.getMember(), memberRole.getAssembly(),
                memberRole.getRole(), memberRole.getFromDate(),
                memberRole.getToDate(), memberRole.getRemarks(),
                memberRole.getStatus(), memberRole.getLocale(),
                memberRole.getVersion());
    }

    /**
     * Creates the member role.
     *
     * @param role the role
     * @param memberId the member id
     * @param memberRole the member role
     * @author nileshp
     * @since v1.0.0 Creates the member role.
     */
    @Transactional
    public void createMemberRole(final long role,
                                 final Long memberId,
                                 final MemberRole memberRole) {
        memberRoleRepository.createMemberRole(role, memberId, memberRole);
    }

    /**
     * Find by member id.
     *
     * @param memberId the member id
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<MemberRole> findByMemberId(final Long memberId) {
        return getMemberRoleRepository().findByMemberId(memberId);
    }

    /**
     * Find unassigned members.
     *
     * @param roleId the role id
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<MemberInRoleVO> findUnassignedMembers(final Long roleId) {
        return getMemberRoleRepository().findUnassignedMembers(roleId);
    }

    /**
     * Find assigned members.
     *
     * @param roleId the role id
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<MemberInRoleVO> findAssignedMembers(final Long roleId) {
        return getMemberRoleRepository().findAssignedMembers(roleId);
    }

    /**
     * Find by role id.
     *
     * @param roleId the role id
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<MemberRole> findByRoleId(final Long roleId) {
        return getMemberRoleRepository().findByRoleId(roleId);
    }

    /**
     * Gets the assigned unassigned members.
     *
     * @param roleId the role id
     * @param rows the rows
     * @param page the page
     * @param sidx the sidx
     * @param order the order
     * @param sQl the s ql
     * @param locale the locale
     * @return the assigned unassigned members
     */
    @Transactional
    public static GridData getAssignedUnassignedMembers(final Long roleId,
                                                 final Integer rows,
                                                 final Integer page,
                                                 final String sidx,
                                                 final String order,
                                                 final String sQl,
                                                 final Locale locale) {
        return getMemberRoleRepository().getAssignedUnassignedMembers(
                roleId, rows, page, sidx, order, sQl, locale);
    }

    /**
     * Gets the assigned unassigned members.
     *
     * @param roleId the role id
     * @param rows the rows
     * @param page the page
     * @param sidx the sidx
     * @param order the order
     * @param locale the locale
     * @return the assigned unassigned members
     */
    @Transactional
    public static GridData getAssignedUnassignedMembers(final Long roleId,
                                                 final Integer rows,
                                                 final Integer page,
                                                 final String sidx,
                                                 final String order,
                                                 final Locale locale) {
        return getMemberRoleRepository().getAssignedUnassignedMembers(
                roleId, rows, page, sidx, order, locale);
    }

    /**
     * Gets the un assigned members.
     *
     * @param roleId the role id
     * @param rows the rows
     * @param page the page
     * @param sidx the sidx
     * @param order the order
     * @param sQl the s ql
     * @param locale the locale
     * @return the un assigned members
     */
    @Transactional
    public static GridData getUnAssignedMembers(final Long roleId,
                                         final Integer rows,
                                         final Integer page,
                                         final String sidx,
                                         final String order,
                                         final String sQl,
                                         final Locale locale) {
        return getMemberRoleRepository().getUnAssignedMembers(
                roleId, rows, page, sidx, order, sQl, locale);
    }

    /**
     * Gets the un assigned members.
     *
     * @param roleId the role id
     * @param rows the rows
     * @param page the page
     * @param sidx the sidx
     * @param order the order
     * @param locale the locale
     * @return the un assigned members
     */
    @Transactional
    public static GridData getUnAssignedMembers(final Long roleId,
                                         final Integer rows,
                                         final Integer page,
                                         final String sidx,
                                         final String order,
                                         final Locale locale) {
        return getMemberRoleRepository().getUnAssignedMembers(
                roleId, rows, page, sidx, order, locale);
    }

    /**
     * Gets the assigned unassigned roles.
     *
     * @param memberId the member id
     * @param rows the rows
     * @param page the page
     * @param sidx the sidx
     * @param order the order
     * @param sQl the s ql
     * @param locale the locale
     * @return the assigned unassigned roles
     */
    @Transactional
    public static GridData getAssignedUnassignedRoles(final Long memberId,
                                               final Integer rows,
                                               final Integer page,
                                               final String sidx,
                                               final String order,
                                               final String sQl,
                                               final Locale locale) {
        return getMemberRoleRepository().getAssignedUnassignedRoles(
                memberId, rows, page, sidx, order, sQl, locale);
    }

    /**
     * Gets the assigned unassigned roles.
     *
     * @param memberId the member id
     * @param rows the rows
     * @param page the page
     * @param sidx the sidx
     * @param order the order
     * @param locale the locale
     * @return the assigned unassigned roles
     */
    @Transactional
    public static GridData getAssignedUnassignedRoles(final Long memberId,
                                               final Integer rows,
                                               final Integer page,
                                               final String sidx,
                                               final String order,
                                               final Locale locale) {
        return getMemberRoleRepository().getAssignedUnassignedRoles(
                memberId, rows, page, sidx, order, locale);
    }

    /**
     * Check for duplicate member role.
     *
     * @param memberRole the member role
     * @return the member role
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static MemberRole checkForDuplicateMemberRole(final MemberRole memberRole) {
        return getMemberRoleRepository()
                .checkForDuplicateMemberRole(memberRole);
    }

    /**
     * Checks if is member.
     *
     * @param memberDetails the member details
     * @param assembly the assembly
     * @param fromdate the fromdate
     * @param todate the todate
     * @return true, if is member
     */
    @Transactional(readOnly = true)
    public static boolean isMember(final MemberDetails memberDetails,
                                   final Assembly assembly,
                                   final String fromdate,
                                   final String todate) {
        return getMemberRoleRepository().isMember(
                memberDetails, assembly, fromdate, todate);
    }

    /**
     * Gets the unassigned roles.
     *
     * @param memberDetails the member details
     * @param assembly the assembly
     * @param locale the locale
     * @return the unassigned roles
     */
    @Transactional(readOnly = true)
    public static List<AssemblyRole> getUnassignedRoles(final MemberDetails memberDetails,
                                                 final Assembly assembly,
                                                 final String locale) {
        // for current assembly
        return getMemberRoleRepository().getUnassignedRoles(memberDetails, assembly, locale);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the member role
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional (readOnly = true)
    public static MemberRole findById(final Long id) {
        return getMemberRoleRepository().find(id);
    }

    /**
     * Persist.
     *
     * @return the member role
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional
    public MemberRole persist() {
        memberRoleRepository.save(this);
        memberRoleRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the member role
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional
    public MemberRole update() {
        memberRoleRepository.merge(this);
        memberRoleRepository.flush();
        return this;
    }

    /**
     * Removes the.
     *
     * @author nileshp
     * @since v1.0.0
     * Removes the.
     */
    @Transactional
    public void remove() {
        memberRoleRepository.remove(this);
        memberRoleRepository.flush();
    }
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the member.
     *
     * @return the member
     */
    public MemberDetails getMember() {
        return member;
    }

    /**
     * Sets the member.
     *
     * @param member the new member
     */
    public void setMember(final MemberDetails member) {
        this.member = member;
    }

    /**
     * Gets the assembly.
     *
     * @return the assembly
     */
    public Assembly getAssembly() {
        return assembly;
    }

    /**
     * Sets the assembly.
     *
     * @param assembly the new assembly
     */
    public void setAssembly(final Assembly assembly) {
        this.assembly = assembly;
    }

    /**
     * Gets the role.
     *
     * @return the role
     */
    public AssemblyRole getRole() {
        return role;
    }

    /**
     * Sets the role.
     *
     * @param role the new role
     */
    public void setRole(final AssemblyRole role) {
        this.role = role;
    }

    /**
     * Gets the from date.
     *
     * @return the from date
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Sets the from date.
     *
     * @param fromDate the new from date
     */
    public void setFromDate(final String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Gets the to date.
     *
     * @return the to date
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * Sets the to date.
     *
     * @param toDate the new to date
     */
    public void setToDate(final String toDate) {
        this.toDate = toDate;
    }

    /**
     * Gets the remarks.
     *
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the remarks.
     *
     * @param remarks the new remarks
     */
    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Gets the locale.
     *
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the locale.
     *
     * @param locale the new locale
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(final Long version) {
        this.version = version;
    }

    /**
     * Gets the serialversionuid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
