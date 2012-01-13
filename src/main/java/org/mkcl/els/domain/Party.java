/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Party.java
 * Created On: Dec 20, 2011
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
import org.mkcl.els.common.vo.MasterVO;
import org.mkcl.els.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class Party.
 *
 * @author meenalw
 * @since v1.0.0
 */
@Configurable
@Entity
@Table(name = "parties")
public class Party implements Serializable {

    // ---------------------------------Attributes-------------------------------------------------
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /** The Id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @NotEmpty
    private String name;

    /** The abbreviation. */
    @Column(length = 30, nullable = false)
    @NotEmpty
    private String abbreviation;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 50)
    private String locale;

    /** The photo. */
    @Column(length = 50)
    private String photo;

    /**
     * Repository.
     */
    @Autowired
    private transient PartyRepository partyRepository;

    // ---------------------------------Constructors----------------------------------------------
    /**
     * Instantiates a new party.
     */
    public Party() {
        super();
    }

    /**
     * Instantiates a new party.
     *
     * @param name the name
     * @param abbreviation the abbreviation
     * @param version the version
     * @param locale the locale
     * @param photo the photo
     */
    public Party(final String name,
            final String abbreviation,
            final Long version,
            final String locale,
            final String photo) {
        super();
        this.name = name;
        this.abbreviation = abbreviation;
        this.version = version;
        this.locale = locale;
        this.photo = photo;
    }

    // -------------------------------Domain_Methods----------------------------------------------

    /**
     * Gets the party repository.
     *
     * @return the party repository
     */
    public static PartyRepository getPartyRepository() {
        final PartyRepository repository = new Party().partyRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "PartyRepository has not been injected");
        }
        return repository;
    }

    /**
     * Creates a new recored for party.
     *
     * @return the party
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional
    public final Party persist() {
        partyRepository.save(this);
        partyRepository.flush();
        return this;
    }

    /**
     * Updates the party details in the system.
     *
     * @return the party
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional
    public final Party update() {
        partyRepository.merge(this);
        partyRepository.flush();
        return this;
    }

    /**
     * Removes the.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional
    public final void remove() {
        partyRepository.remove(this);
        partyRepository.flush();
    }

    /**
     * Find by name.
     *
     * @param name the name
     * @return the party
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Party findByName(final String name) {
        return getPartyRepository().findByName(name);
    }

    /**
     * Find all sorted.
     *
     * @param property the property
     * @param locale the locale
     * @param desc the desc
     * @return the list
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Party> findAllSorted(final String property,
                                            final String locale,
                                            final boolean desc) {
        return getPartyRepository().findAllSorted(property, locale, desc);
    }

    /**
     * Find all sorted.
     *
     * @param locale new locale
     * @return list in decreasing order
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<MasterVO> findAllSortedVO(final String locale) {
        return getPartyRepository().findAllSortedVO(locale);
    }

    /**
     * Find By Id.
     *
     * @param id new Id
     * @return Id
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Party findById(final Long id) {
        return getPartyRepository().find(id);
    }

    /**
     * Check version.
     *
     * @return true, if successful
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public boolean checkVersion() {
        return Party.findById(this.id).getVersion() == this.version;
    }

    /**
     * Find all.
     *
     * @return the list< party>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Party> findAll() {
        return getPartyRepository().findAll();

    }

    /**
     * Find all.
     *
     * @param locale the locale
     * @return the list< party>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Party> findAll(final String locale) {
        return getPartyRepository().findAll(locale);
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
     * @param id new id
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
     * @param name new name
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the abbreviation.
     *
     * @return the abbreviation
     */
    public final String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets the abbreviation.
     *
     * @param abbreviation the new abbreviation
     */
    public final void setAbbreviation(final String abbreviation) {
        this.abbreviation = abbreviation;
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
     * Gets the photo.
     *
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * Sets the photo.
     *
     * @param photo the new photo
     */
    public void setPhoto(final String photo) {
        this.photo = photo;
    }

}
