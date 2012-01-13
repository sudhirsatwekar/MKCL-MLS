/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.AssemblyRole.java
 * Created On: Jan 9, 2012
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
import org.mkcl.els.repository.AssemblyRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AssemblyRole.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "assembly_roles")
public class AssemblyRole implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

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

    /** The assembly role repository. */
    @Autowired
    private transient AssemblyRoleRepository assemblyRoleRepository;
// -------------------------- constructors ----------------------------------
    /**
     * Instantiates a new assembly role.
     */
    public AssemblyRole() {
        super();
    }

    /**
     * Instantiates a new assembly role.
     *
     * @param name the name
     * @param locale the locale
     */
    public AssemblyRole(final String name, final String locale) {
        super();
        this.name = name;
        this.locale = locale;
    }

// -------------------- Domain methods -------------------------------

    /**
     * Gets the assembly role repository.
     *
     * @return the assembly role repository
     */
     public static AssemblyRoleRepository getAssemblyRoleRepository() {
        AssemblyRoleRepository assemblyRoleRepository = new AssemblyRole().assemblyRoleRepository;
        if (assemblyRoleRepository == null) {
            throw new IllegalStateException(
                    "AssemblyRole Repository has not been injected "
                            + "in AssemblyRole domain");
        }
        return assemblyRoleRepository;
    }

     /**
      * Find by name.
      *
      * @param name the name
      * @return the assembly role
      * @author nileshp
      * @since v1.0.0
      */
     @Transactional (readOnly = true)
     public static AssemblyRole findByName(final String name) {
         return getAssemblyRoleRepository().findByName(name);
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
    public static List<AssemblyRole> findAllSorted(final String property,
                                                   final String locale,
                                                   final boolean descOrder) {
        return getAssemblyRoleRepository().findAllSorted(
                property, locale, descOrder);
    }

    /**
     * Find unassigned roles.
     *
     * @param locale the locale
     * @param memberId the member id
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<AssemblyRole> findUnassignedRoles(final String locale, final Long memberId) {
        return getAssemblyRoleRepository().findUnassignedRoles(locale, memberId);
    }

    /**
     * Find by name and locale.
     *
     * @param name the name
     * @param locale the locale
     * @return the assembly role
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static AssemblyRole findByNameAndLocale(final String name, final String locale) {
        return getAssemblyRoleRepository().findByNameAndLocale(name, locale);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the assembly role
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static AssemblyRole findById(final Long id) {
        return getAssemblyRoleRepository().find(id);
    }

    /**
     * Persist.
     *
     * @return the assembly role
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional
    public AssemblyRole persist() {
        assemblyRoleRepository.save(this);
        assemblyRoleRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the assembly role
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional
    public AssemblyRole update() {
        assemblyRoleRepository.merge(this);
        assemblyRoleRepository.flush();
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
        assemblyRoleRepository.remove(this);
        assemblyRoleRepository.flush();
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
        AssemblyRole assemblyRole = AssemblyRole.findById(this.id);
        return assemblyRole.getVersion().equals(this.version);
    }
// -------------------- getters and setters ----------------------------
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
