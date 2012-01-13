/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.State.java
 * Created On: Dec 16, 2011
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
import org.mkcl.els.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class State.
 *
 * @author sujitas
 * @since v1.0.0
 */
@Configurable
@Entity
@Table(name = "states")
public class State implements Serializable {

    // ---------------------------------Attributes-------------------------------------------------
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @Column(length = 50, nullable = false)
    @NotEmpty
    private String name;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 5)
    private String locale;

    /**
     * Repository.
     */
    @Autowired
    private transient StateRepository stateRepository;

    // ---------------------------------Constructors----------------------------------------------
    /**
     * Instantiates a new state.
     */
    public State() {
        super();
    }

    /**
     * Instantiates a new state.
     *
     * @param name the name
     */
    public State(final String name) {
        super();
        this.name = name;
    }

    // -------------------------------Domain_Methods----------------------------------------------
    /**
     * Gets the state repository.
     *
     * @return org.mkcl.els.repository.StateRepository
     */
    public static StateRepository getStateRepository() {
        final StateRepository repository = new State().stateRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "StateRepository has not been injected");
        }
        return repository;
    }

    /**
     * Finds parameterized id record.
     *
     * @param id the id
     * @return the state
     */
    @Transactional(readOnly = true)
    public static State findById(final Long id) {
        return getStateRepository().find(id);
    }

    /**
     * Finds all the entity records.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public static List<State> findAll() {
        return getStateRepository().findAll();
    }

    /**
     * Find by name.
     *
     * @param name the name
     * @return the state
     */
    @Transactional(readOnly = true)
    public static State findByName(final String name) {
        return getStateRepository().findByName(name);
    }

    /**
     * Find all sorted.
     *
     * @param property the property
     * @param locale the locale
     * @param descOrder the desc order
     * @return the list
     */
    @Transactional(readOnly = true)
    public static List<State> findAllSorted(final String property,
                                            final String locale,
                                            final boolean descOrder) {
        return getStateRepository().findAllSorted(property, locale, descOrder);
    }

    /**
     * Creates a new recored for state.
     *
     * @return the role
     */
    @Transactional
    public State persist() {
        stateRepository.save(this);
        stateRepository.flush();
        return this;
    }

    /**
     * Updates the state details in the system.
     *
     * @return the role
     */
    @Transactional
    public State update() {
        stateRepository.merge(this);
        stateRepository.flush();
        return this;
    }

    /**
     * Removes the existing state from the system.
     */
    @Transactional
    public void remove() {
        stateRepository.remove(this);
        stateRepository.flush();
    }

    /**
     * Checks the version of the state entity.
     *
     * @return true, if successful
     */
    @Transactional(readOnly = true)
    public boolean checkVersion() {
        State state = stateRepository.find(this.id);
        return state.getVersion().equals(this.version);
    }

    // ------------------------------------------Getters/Setters-----------------------------------
    /**
     * Gets the id.
     *
     * @return the id
     */
    public final Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public final void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public final Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public final void setVersion(final Long version) {
        this.version = version;
    }

    /**
     * Gets the locale.
     *
     * @return the locale
     */
    public final String getLocale() {
        return locale;
    }

    /**
     * Sets the locale.
     *
     * @param locale the new locale
     */
    public final void setLocale(final String locale) {
        this.locale = locale;
    }
}
