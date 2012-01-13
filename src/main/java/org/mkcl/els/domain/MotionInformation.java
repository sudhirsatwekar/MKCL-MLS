/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.MotionInformation.java
 * Created On: Jan 11, 2012
 */
package org.mkcl.els.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * The Class MotionInformation.
 *
 * @author nileshp
 */
@Configurable
@Entity
@Table(name = "motion_information")
public class MotionInformation {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The year. */
    private String year;

    /** The motion type. */
    private String motionType;

    /** The assembly. */
    private String assembly;

    /** The assembly date. */
    private String assemblyDate;

    /** The submission date. */
    private String submissionDate;

    /** The department. */
    private String department;

    /** The submission time. */
    private String submissionTime;

    /** The motion subject. */
    private String motionSubject;

    /** The motion text. */
    private String motionText;

    /** The supporting members. */
    private String supportingMembers;

    /** The is admitted. */
    private boolean isAdmitted;

    /** The is discussed. */
    private boolean isDiscussed;

    /** The date of discussion. */
    private String dateOfDiscussion;

    /** The locale. */
    @Column(length = 50)
    @NotEmpty
    private String locale;

    /** The version. */
    @Version
    private Long version;

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
     * Gets the assembly.
     *
     * @return the assembly
     */
    public String getAssembly() {
        return assembly;
    }

    /**
     * Sets the assembly.
     *
     * @param assembly the new assembly
     */
    public void setAssembly(final String assembly) {
        this.assembly = assembly;
    }

    /**
     * Gets the assembly date.
     *
     * @return the assembly date
     */
    public String getAssemblyDate() {
        return assemblyDate;
    }

    /**
     * Sets the assembly date.
     *
     * @param assemblyDate the new assembly date
     */
    public void setAssemblyDate(final String assemblyDate) {
        this.assemblyDate = assemblyDate;
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
     * Gets the year.
     *
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the year.
     *
     * @param year the new year
     */
    public void setYear(final String year) {
        this.year = year;
    }

    /**
     * Gets the motion type.
     *
     * @return the motion type
     */
    public String getMotionType() {
        return motionType;
    }

    /**
     * Sets the motion type.
     *
     * @param motionType the new motion type
     */
    public void setMotionType(final String motionType) {
        this.motionType = motionType;
    }


    /**
     * Gets the submission date.
     *
     * @return the submission date
     */
    public String getSubmissionDate() {
        return submissionDate;
    }


    /**
     * Sets the submission date.
     *
     * @param submissionDate the new submission date
     */
    public void setSubmissionDate(final String submissionDate) {
        this.submissionDate = submissionDate;
    }


    /**
     * Gets the department.
     *
     * @return the department
     */
    public String getDepartment() {
        return department;
    }


    /**
     * Sets the department.
     *
     * @param department the new department
     */
    public void setDepartment(final String department) {
        this.department = department;
    }


    /**
     * Gets the submission time.
     *
     * @return the submission time
     */
    public String getSubmissionTime() {
        return submissionTime;
    }


    /**
     * Sets the submission time.
     *
     * @param submissionTime the new submission time
     */
    public void setSubmissionTime(final String submissionTime) {
        this.submissionTime = submissionTime;
    }


    /**
     * Gets the motion subject.
     *
     * @return the motion subject
     */
    public String getMotionSubject() {
        return motionSubject;
    }


    /**
     * Sets the motion subject.
     *
     * @param motionSubject the new motion subject
     */
    public void setMotionSubject(final String motionSubject) {
        this.motionSubject = motionSubject;
    }


    /**
     * Gets the motion text.
     *
     * @return the motion text
     */
    public String getMotionText() {
        return motionText;
    }


    /**
     * Sets the motion text.
     *
     * @param motionText the new motion text
     */
    public void setMotionText(final String motionText) {
        this.motionText = motionText;
    }


    /**
     * Gets the supporting members.
     *
     * @return the supporting members
     */
    public String getSupportingMembers() {
        return supportingMembers;
    }


    /**
     * Sets the supporting members.
     *
     * @param supportingMembers the new supporting members
     */
    public void setSupportingMembers(final String supportingMembers) {
        this.supportingMembers = supportingMembers;
    }


    /**
     * Checks if is admitted.
     *
     * @return true, if is admitted
     */
    public boolean getIsAdmitted() {
        return isAdmitted;
    }


    /**
     * Sets the admitted.
     *
     * @param isAdmitted the new admitted
     */
    public void setIsAdmitted(final boolean isAdmitted) {
        this.isAdmitted = isAdmitted;
    }


    /**
     * Checks if is discussed.
     *
     * @return true, if is discussed
     */
    public boolean getIsDiscussed() {
        return isDiscussed;
    }


    /**
     * Sets the discussed.
     *
     * @param isDiscussed the new discussed
     */
    public void setIsDiscussed(final boolean isDiscussed) {
        this.isDiscussed = isDiscussed;
    }


    /**
     * Gets the date of discussion.
     *
     * @return the date of discussion
     */
    public String getDateOfDiscussion() {
        return dateOfDiscussion;
    }


    /**
     * Sets the date of discussion.
     *
     * @param dateOfDiscussion the new date of discussion
     */
    public void setDateOfDiscussion(final String dateOfDiscussion) {
        this.dateOfDiscussion = dateOfDiscussion;
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

}
