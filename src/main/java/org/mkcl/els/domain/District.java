/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.District.java
 * Created On: Dec 19, 2011
 */

package org.mkcl.els.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;
import org.mkcl.els.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class District.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "districts")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="org.mkcl.els.domain.District")
public class District implements Serializable {

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

    /** The state. */
    @ManyToOne
    private State state;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 5)
    private String locale;

    /** The district repository. */
    @Autowired
    private transient DistrictRepository districtRepository;

    /**
     * Instantiates a new district.
     */
    public District() {
        super();
    }

    /**
     * Instantiates a new district.
     *
     * @param name the name
     * @param state the state
     */
    public District(final String name, final State state) {
        super();
        this.name = name;
        this.state = state;
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
     * Gets the state.
     *
     * @return the state
     */
    public State getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state the new state
     */
    public void setState(final State state) {
        this.state = state;
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

    /**
     * Gets the district repository.
     *
     * @return the district repository
     */
    public static DistrictRepository getDistrictRepository() {
        DistrictRepository repository = new District().districtRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "District Repository has not been injected "
                            + "in District domain");
        }
        return repository;
    }

    /**
     * Find by name.
     *
     * @param name the name
     * @param locale the locale
     * @return the district
     */
    @Transactional(readOnly = true)
    public static District findByName(final String name, final String locale) {
        return getDistrictRepository().findByName(name, locale);
    }

    /**
     * Find districts by state id.
     *
     * @param stateId the state id
     * @return the list
     */
    @Transactional(readOnly = true)
    public static List<District> findDistrictsByStateId(final Long stateId) {
        return getDistrictRepository().findDistrictsByStateId(stateId);
    }

    /**
     * Find districts by state id.
     *
     * @param id the id
     * @param property the property
     * @param descOrder the desc order
     * @return the list
     */
    @Transactional(readOnly = true)
    public static List<District> findDistrictsByStateId(final Long id,
            final String property,
            final boolean descOrder) {
        return getDistrictRepository().findDistrictsByStateId(
                id, property, descOrder);
    }

    /**
     * Find districts by state name.
     *
     * @param stateName the state name
     * @return the list
     */
    @Transactional(readOnly = true)
    public static List<District> findDistrictsByStateName(final String stateName) {
        return getDistrictRepository().findDistrictsByStateName(stateName);
    }

    /**
     * Find districts by constituency id.
     *
     * @param constituencyId the constituency id
     * @param propertySortBy the property sort by
     * @param descOrder the desc order
     * @return the sets the
     */
    @Transactional(readOnly = true)
    public static List<District> findDistrictsByConstituencyId(final Long constituencyId,
            final String propertySortBy,
            final boolean descOrder) {
        return getDistrictRepository().findDistrictsByConstituencyId(
                constituencyId, propertySortBy, descOrder);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the district
     */
    @Transactional(readOnly = true)
    public static District findById(final Long id) {
        return getDistrictRepository().find(id);
    }

    /**
     * Persist.
     *
     * @return the district
     */
    @Transactional
    public District persist() {
        return getDistrictRepository().save(this);
    }

    /**
     * Updates the user details in the system.
     *
     * @return the user
     */
    @Transactional
    public District update() {
        return getDistrictRepository().merge(this);
    }

    /**
     * Removes the existing user in the system.
     */
    @Transactional
    public void remove() {
        getDistrictRepository().remove(this);
    }

    /**
     * Check version.
     *
     * @return true, if check version
     */
    @Transactional(readOnly = true)
    public boolean checkVersion() {
        District district = District.findById(this.id);
        return district.getVersion().equals(this.version);
    }

    /**
     * Find all.
     *
     * @return the list
     */
    @Transactional(readOnly = true)
    public static List<District> findAll() {
        return getDistrictRepository().findAll();
    }

    /**
     * Find all.
     *
     * @param locale the locale
     * @return the list
     */
    @Transactional(readOnly = true)
    public static List<District> findAll(final String locale) {
        return getDistrictRepository().findAll(locale);
    }

}
