/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.AssemblyNumber.java
 * Created On: Dec 27, 2011
 */

package org.mkcl.els.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;
import org.mkcl.els.repository.AssemblyNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AssemblyNumber.
 *
 * @author amitd
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "assembly_number")
public class AssemblyNumber implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // ===========Attributes==========
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The assembly number. */
    @Column(length = 100, nullable = false)
    @NotEmpty
    private String assemblyNo;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 50)
    private String locale;

    /** The assembly number repository. */
    @Autowired
    private transient AssemblyNumberRepository assemblyNumberRepository;

    // ==========Constructors==========
    /**
     * Instantiates a new assembly number.
     */
    public AssemblyNumber() {
        super();
    }

    /**
     * Instantiates a new assembly number.
     *
     * @param assemblyNo the assembly no
     * @param version the version
     * @param locale the locale
     */
    public AssemblyNumber(final String assemblyNo,
            final Long version,
            final String locale) {
        super();
        this.assemblyNo = assemblyNo;
        this.version = version;
        this.locale = locale;
    }

    // ========== domain methods ===========

    /**
     * Gets the assembly number repository.
     *
     * @return the assembly number repository
     */
    public static AssemblyNumberRepository getAssemblyNumberRepository() {
        AssemblyNumberRepository assemblyNumberRepository =
                new AssemblyNumber().assemblyNumberRepository;

        if (assemblyNumberRepository == null) {
            throw new IllegalStateException(
                    "Assembly Number Repository has not been injected "
                            + "in Assembly Number domain");
        }

        return assemblyNumberRepository;
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
    public static List<AssemblyNumber> findAllSorted(final String property,
                                                     final String locale,
                                                     final boolean descOrder) {
        return getAssemblyNumberRepository().findAllSorted(
                property, locale, descOrder);
    }


    /**
     * Find by assembly number.
     *
     * @param assemblyNo the assembly no
     * @return the assembly number
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static AssemblyNumber findByAssemblyNumber(final String assemblyNo) {
        return getAssemblyNumberRepository().findByAssemblyNo(assemblyNo);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the assembly number
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static AssemblyNumber findById(final Long id) {
        return getAssemblyNumberRepository().find(id);
    }

    /**
     * Check version.
     *
     * @return true, if successful
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public boolean checkVersion() {
        AssemblyNumber assemblyNumber = getAssemblyNumberRepository().find(this.id);
        return assemblyNumber.getVersion().equals(this.version);
    }

    /**
     * Persist.
     *
     * @author nileshp
     * @return
     * @since v1.0.0
     * Persist.
     */
    @Transactional
    public AssemblyNumber persist() {
        assemblyNumberRepository.save(this);
        assemblyNumberRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @author nileshp
     * @since v1.0.0
     * Update.
     */
    @Transactional
    public AssemblyNumber update() {
        assemblyNumberRepository.merge(this);
        assemblyNumberRepository.flush();
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
        assemblyNumberRepository.remove(this);
        assemblyNumberRepository.flush();
    }

    // ==========Getters & Setters==========
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
     * Gets the assembly no.
     *
     * @return the assembly no
     */
    public String getAssemblyNo() {
        return assemblyNo;
    }

    /**
     * Sets the assembly no.
     *
     * @param assemblyNo the new assembly no
     */
    public void setAssemblyNo(final String assemblyNo) {
        this.assemblyNo = assemblyNo;
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
}
