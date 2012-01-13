/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Assembly.java
 * Created On: Jan 5, 2012
 */

package org.mkcl.els.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.mkcl.els.repository.AssemblyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class Assembly.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "assemblies")
public class Assembly implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The assembly structure. */
    @ManyToOne(fetch = FetchType.EAGER)
    private AssemblyStructure assemblyStructure;

    /** The assembly. */
    @NotEmpty
    private String assembly;

    /** The strength. */
    @NotNull
    private Integer strength;

    /** The term. */
    @Column(length = 20)
    @NotEmpty
    private String term;

    /** The budget session. */
    private boolean budgetSession = false;

    /** The monsoonsession. */
    private boolean monsoonSession = false;

    /** The winter session. */
    private boolean winterSession = false;

    /** The special session. */
    private boolean specialSession = false;

    /** The assembly number. */
    private Long assemblyNumber;


    /** The assembly start date. */

    @Column(length = 50)
    private String assemblyStartDate;

    /** The assembly end date. */
    @Column(length = 50)
    private String assemblyEndDate;

    /** The assembly dissolved on. */
    @Column(length = 50)
    private String assemblyDissolvedOn;

    /** The current assembly. */
    private boolean currentAssembly = false;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 50)
    @NotEmpty
    private String locale;

    /** The assembly repository. */
    @Autowired
    private transient AssemblyRepository assemblyRepository;

    /**
     * Instantiates a new assembly.
     */
    public Assembly() {
        super();
    }

    /**
     * Instantiates a new assembly.
     *
     * @param assemblyStructure the assembly structure
     * @param assembly the assembly
     * @param strength the strength
     * @param term the term
     * @param budgetSession the budget session
     * @param monsoonSession the monsoon session
     * @param winterSession the winter session
     * @param specialSession the special session
     * @param assemblyStartDate the assembly start date
     * @param assemblyEndDate the assembly end date
     * @param assemblyDissolvedOn the assembly dissolved on
     * @param currentAssembly the current assembly
     * @param version the version
     * @param locale the locale
     * @param assemblyNumber the assembly number
     */
    public Assembly(final AssemblyStructure assemblyStructure,
            final String assembly,
            final Integer strength,
            final String term,
            final boolean budgetSession,
            final boolean monsoonSession,
            final boolean winterSession,
            final boolean specialSession,
            final String assemblyStartDate,
            final String assemblyEndDate,
            final String assemblyDissolvedOn,
            final boolean currentAssembly,
            final Long version,
            final String locale,
            final Long assemblyNumber) {
        super();
        this.assemblyStructure = assemblyStructure;
        this.assembly = assembly;
        this.strength = strength;
        this.term = term;
        this.budgetSession = budgetSession;
        this.monsoonSession = monsoonSession;
        this.winterSession = winterSession;
        this.specialSession = specialSession;
        this.assemblyStartDate = assemblyStartDate;
        this.assemblyEndDate = assemblyEndDate;
        this.assemblyDissolvedOn = assemblyDissolvedOn;
        this.currentAssembly = currentAssembly;
        this.version = version;
        this.locale = locale;
        this.assemblyNumber = assemblyNumber;
    }
 // -------------------------------Domain_Methods----------------------------------------------

    /**
      * Gets the assembly repository.
      *
      * @return the assembly repository
      */
 public static AssemblyRepository getAssemblyRepository() {
        AssemblyRepository assemblyRepository = new Assembly().assemblyRepository;
        if (assemblyRepository == null) {
            throw new IllegalStateException(
                    "AssemblyRepository has not been injected in Assembly Domain");
        }
        return assemblyRepository;
    }

    /**
     * Find by assembly.
     *
     * @param assembly the assembly
     * @return the assembly
     * @author nileshp
     * @since v1.0.0
     */
 @Transactional(readOnly = true)
    public static Assembly findByAssembly(final String assembly) {
        return getAssemblyRepository().findByAssembly(assembly);
    }

    /**
     * Find current assembly.
     *
     * @param locale the locale
     * @return the assembly
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Assembly findCurrentAssembly(final String locale) {
        return getAssemblyRepository().findCurrentAssembly(locale);
    }

    /**
     * Find all sorted.
     *
     * @param property the property
     * @param locale the locale
     * @param descOrder the desc order
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Assembly> findAllSorted(final String property,
                                               final String locale,
                                               final boolean descOrder) {
        return getAssemblyRepository().findAllSorted(property, locale, descOrder);
    }

    /**
     * Update previous current assembly.
     *
     * @param locale the locale
     * @author nileshp
     * @since v1.0.0
     * Update previous current assembly.
     */
    @Transactional
    public void updatePreviousCurrentAssembly(final String locale) {
        getAssemblyRepository().updatePreviousCurrentAssembly(locale);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the assembly
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Assembly findById(final Long id) {
        return getAssemblyRepository().find(id);
    }

    /**
     * Persist.
     *
     * @return the assembly
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional
    public Assembly persist() {
        assemblyRepository.save(this);
        assemblyRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the assembly
     * @author nileshp
     * @since v1.0.0
     * Update.
     */
    @Transactional
    public Assembly update() {
        assemblyRepository.merge(this);
        assemblyRepository.flush();
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
        assemblyRepository.remove(this);
        assemblyRepository.flush();
    }
 // -------------------------------Getters and Setters-----------------------------------------
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
     * Gets the assembly structure.
     *
     * @return the assembly structure
     */
    public AssemblyStructure getAssemblyStructure() {
        return assemblyStructure;
    }

    /**
     * Sets the assembly structure.
     *
     * @param assemblyStructure the new assembly structure
     */
    public void setAssemblyStructure(final AssemblyStructure assemblyStructure) {
        this.assemblyStructure = assemblyStructure;
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
     * Gets the strength.
     *
     * @return the strength
     */
    public Integer getStrength() {
        return strength;
    }

    /**
     * Sets the strength.
     *
     * @param strength the new strength
     */
    public void setStrength(final Integer strength) {
        this.strength = strength;
    }

    /**
     * Gets the term.
     *
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the new term
     */
    public void setTerm(final String term) {
        this.term = term;
    }

    /**
     * Checks if is budget session.
     *
     * @return true, if is budget session
     */
    public boolean isBudgetSession() {
        return budgetSession;
    }

    /**
     * Sets the budget session.
     *
     * @param budgetSession the new budget session
     */
    public void setBudgetSession(final boolean budgetSession) {
        this.budgetSession = budgetSession;
    }

    /**
     * Checks if is monsoon session.
     *
     * @return true, if is monsoon session
     */
    public boolean isMonsoonSession() {
        return monsoonSession;
    }

    /**
     * Sets the monsoon session.
     *
     * @param monsoonSession the new monsoon session
     */
    public void setMonsoonSession(final boolean monsoonSession) {
        this.monsoonSession = monsoonSession;
    }

    /**
     * Checks if is winter session.
     *
     * @return true, if is winter session
     */
    public boolean isWinterSession() {
        return winterSession;
    }

    /**
     * Sets the winter session.
     *
     * @param winterSession the new winter session
     */
    public void setWinterSession(final boolean winterSession) {
        this.winterSession = winterSession;
    }

    /**
     * Gets the assembly start date.
     *
     * @return the assembly start date
     */
    public String getAssemblyStartDate() {
        return assemblyStartDate;
    }

    /**
     * Sets the assembly start date.
     *
     * @param assemblyStartDate the new assembly start date
     */
    public void setAssemblyStartDate(final String assemblyStartDate) {
        this.assemblyStartDate = assemblyStartDate;
    }

    /**
     * Gets the assembly end date.
     *
     * @return the assembly end date
     */
    public String getAssemblyEndDate() {
        return assemblyEndDate;
    }

    /**
     * Sets the assembly end date.
     *
     * @param assemblyEndDate the new assembly end date
     */
    public void setAssemblyEndDate(final String assemblyEndDate) {
        this.assemblyEndDate = assemblyEndDate;
    }

    /**
     * Gets the assembly dissolved on.
     *
     * @return the assembly dissolved on
     */
    public String getAssemblyDissolvedOn() {
        return assemblyDissolvedOn;
    }

    /**
     * Sets the assembly dissolved on.
     *
     * @param assemblyDissolvedOn the new assembly dissolved on
     */
    public void setAssemblyDissolvedOn(final String assemblyDissolvedOn) {
        this.assemblyDissolvedOn = assemblyDissolvedOn;
    }

    /**
     * Checks if is current assembly.
     *
     * @return true, if is current assembly
     */
    public boolean isCurrentAssembly() {
        return currentAssembly;
    }

    /**
     * Sets the current assembly.
     *
     * @param currentAssembly the new current assembly
     */
    public void setCurrentAssembly(final boolean currentAssembly) {
        this.currentAssembly = currentAssembly;
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
     * Gets the serialversionuid.
     *
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    /**
     * Gets the assembly number.
     *
     * @return the assembly number
     */
    public Long getAssemblyNumber() {
        return assemblyNumber;
    }



    /**
     * Sets the assembly number.
     *
     * @param assemblyNumber the new assembly number
     */
    public void setAssemblyNumber(final Long assemblyNumber) {
        this.assemblyNumber = assemblyNumber;
    }


    /**
     * Checks if is special session.
     *
     * @return true, if is special session
     */
    public boolean isSpecialSession() {
        return specialSession;
    }


    /**
     * Sets the special session.
     *
     * @param specialSession the new special session
     */
    public void setSpecialSession(final boolean specialSession) {
        this.specialSession = specialSession;
    }
}
