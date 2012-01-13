/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.AssemblyStructure.java
 * Created On: Dec 29, 2011
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
import org.mkcl.els.repository.AssemblyStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AssemblyStructure.
 *
 * @author amitd
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "assembly_structure")
public class AssemblyStructure implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // ===========Attributes==========
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @Column(length = 100, nullable = false)
    @NotEmpty
    private String name;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 50)
    private String locale;

    /** The assembly structure repository. */
    @Autowired
    private transient AssemblyStructureRepository assemblyStructureRepository;

    // ==========Constructors==========
    /**
     * Instantiates a new assembly structure.
     */
    public AssemblyStructure() {
        super();
    }

    /**
     * Instantiates a new assembly structure.
     *
     * @param name the name
     * @param locale the locale
     */
    public AssemblyStructure(final String name, final String locale) {
        super();
        this.name = name;
        this.locale = locale;
    }

    // ==========Domain Methods==========


    /**
     * Gets the assembly structure repository.
     *
     * @return the assembly structure repository
     */
    public static AssemblyStructureRepository getAssemblyStructureRepository() {
        AssemblyStructureRepository repository =
                new AssemblyStructure().assemblyStructureRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "Assembly Structure Repository has not been injected "
                            + "in Assembly Structure domain");
        }
        return repository;
    }

    /**
     * Find by name.
     *
     * @param name the name
     * @return the assembly structure
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static AssemblyStructure findByName(final String name) {
        return getAssemblyStructureRepository().findByName(name);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the assembly structure
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static AssemblyStructure findById(final Long id) {
        return getAssemblyStructureRepository().find(id);
    }

    /**
     * Find all sorted by name.
     *
     * @param property the property
     * @param locale the locale
     * @param descOrder the desc order
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<AssemblyStructure> findAllSorted(final String property,
                                                              final String locale,
                                                              final boolean descOrder) {
        List<AssemblyStructure> list = getAssemblyStructureRepository().findAllSorted(
                property, locale, descOrder);
        return list;
    }

    /**
     * Persist.
     *
     * @author nileshp
     * @since v1.0.0 Persist.
     */
    @Transactional
    public AssemblyStructure persist() {
        assemblyStructureRepository.save(this);
        assemblyStructureRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @author nileshp
     * @since v1.0.0 Update.
     */
    @Transactional
    public AssemblyStructure update() {
        assemblyStructureRepository.merge(this);
        assemblyStructureRepository.flush();
        return this;
    }

    /**
     * Removes the.
     *
     * @author nileshp
     * @since v1.0.0 Removes the.
     */
    @Transactional
    public void remove() {
        assemblyStructureRepository.remove(this);
        assemblyStructureRepository.flush();
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
        AssemblyStructure assemblyStructure = getAssemblyStructureRepository()
                .find(this.id);
        return assemblyStructure.getVersion().equals(this.version);
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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(final String name) {
        this.name = name;
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
