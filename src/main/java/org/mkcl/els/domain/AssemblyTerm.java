/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.AssemblyTerm.java
 * Created On: Dec 26, 2011
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
import javax.validation.constraints.NotNull;

import org.mkcl.els.repository.AssemblyTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AssemblyTerm.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Configurable
@Entity
@Table(name = "assembly_terms")
public class AssemblyTerm implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    // ---------------------------------Attributes------------------------//
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The term. */
    @NotNull
    private Integer term;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 50)
    private String locale;

    /** The assembly term repository. */
    @Autowired
    private transient AssemblyTermRepository assemblyTermRepository;

    // ---------------------------------Constructors----------------------//
    /**
     * Instantiates a new assembly term.
     */
    public AssemblyTerm() {
        super();
    }

    /**
     * Instantiates a new assembly term.
     *
     * @param term the term
     * @param version the version
     * @param locale the locale
     */
    public AssemblyTerm(final Integer term,
            final Long version,
            final String locale) {
        super();
        this.term = term;
        this.version = version;
        this.locale = locale;
    }

    // ----------------------------Domain Methods-------------------------//

    /**
     * Gets the repository.
     *
     * @return the repository
     */
    public static AssemblyTermRepository getRepository() {
        AssemblyTermRepository repository = new AssemblyTerm().assemblyTermRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "AssemblyTermRepository has not been injected");
        }
        return repository;
    }

    /**
     * Persist.
     *
     * @return the assembly term
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    public AssemblyTerm persist() {
        assemblyTermRepository.save(this);
        assemblyTermRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the assembly term
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    public AssemblyTerm update() {
        assemblyTermRepository.merge(this);
        assemblyTermRepository.flush();
        return this;
    }

    /**
     * Removes the.
     *
     * @return the assembly term
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    public AssemblyTerm remove() {
        assemblyTermRepository.remove(this);
        assemblyTermRepository.flush();
        return this;
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the assembly term
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static AssemblyTerm findById(final Long id) {
        return getRepository().find(id);
    }

    /**
     * Find by assembly term.
     *
     * @param term the term
     * @return the assembly term
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static AssemblyTerm findByAssemblyTerm(final Integer term) {
        return getRepository().findByAssemblyTerm(term);
    }

    /**
     * Find all.
     *
     * @return the list
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<AssemblyTerm> findAll() {
        return getRepository().findAll();
    }

    /**
     * Check version.
     *
     * @return true, if successful
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public boolean checkVersion() {
        AssemblyTerm assemblyTerm = getRepository().find(this.id);
        return assemblyTerm.getVersion().equals(this.version);
    }

    // ----------------------------Getters/Setters------------------------//
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
     * Gets the term.
     *
     * @return the term
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * Sets the term.
     *
     * @param term the new term
     */
    public void setTerm(final Integer term) {
        this.term = term;
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
