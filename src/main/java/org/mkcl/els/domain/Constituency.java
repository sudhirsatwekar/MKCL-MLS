/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Constituency.java
 * Created On: Jan 4, 2012
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;
import org.mkcl.els.common.vo.MasterVO;
import org.mkcl.els.repository.ConstituencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class Constituency.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "constituencies")
public class Constituency implements Serializable {

    // ---------------------------------Attributes-------------------------------------------------
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

    /** The constituency number. */
    @Column(length = 100)
    private String number;

    /** The districts. */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "constituency_district", joinColumns = @JoinColumn(
            name = "constituency_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "district_id",
            referencedColumnName = "id"))
    @NotEmpty
    private List<District> districts;

    /** The reserved. */
    private boolean reserved = false;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 5)
    private String locale;

    /** The constituency repository. */
    @Autowired
    private transient ConstituencyRepository constituencyRepository;

    // ---------------------------------Constructors----------------------------------------------
    /**
     * Instantiates a new constituency.
     */
    public Constituency() {
        super();
    }

    /**
     * Instantiates a new constituency.
     *
     * @param name the name
     * @param number the number
     * @param districts the districts
     * @param reserved the reserved
     * @param version the version
     * @param locale the locale
     */
    public Constituency(final String name,
                        final String number,
                        final List<District> districts,
                        final boolean reserved,
                        final Long version,
                        final String locale) {
        super();
        this.name = name;
        this.number = number;
        this.districts = districts;
        this.reserved = reserved;
        this.version = version;
        this.locale = locale;
    }

    // -------------------------------Domain_Methods----------------------------------------------

    /**
     * Gets the constituency repository.
     *
     * @return the constituency repository
     */
    public static ConstituencyRepository getConstituencyRepository() {
        final ConstituencyRepository repository = new Constituency().constituencyRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "ConstituencyRepository has not been injected");
        }
        return repository;
    }

    /**
     * Constituency.
     *
     * @return the constituency
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional
    public Constituency persist() {
        constituencyRepository.save(this);
        constituencyRepository.flush();
        return this;
    }

    /**
     * Constituency.
     *
     * @return the constituency
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional
    public Constituency update() {
        constituencyRepository.merge(this);
        constituencyRepository.flush();
        return this;
    }

    /**
     * Removes the.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional
    public void remove() {
        constituencyRepository.remove(this);
        constituencyRepository.flush();
    }

    /**
     * Constituency.
     *
     * @param name the name
     * @return the constituency
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Constituency findByName(final String name) {
        return getConstituencyRepository().findByName(name);
    }

    /**
     * Constituency.
     *
     * @param id the id
     * @return the constituency
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Constituency findById(final Long id) {
        return getConstituencyRepository().find(id);
    }


    /**
     * List.
     *
     * @param locale the locale
     * @return the list< master v o>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<MasterVO> findAllSortedVO(final String locale) {
        return getConstituencyRepository().findAllSortedVO(locale);
    }

    /**
     * List.
     *
     * @param property the property
     * @param locale the locale
     * @param desc the desc
     * @return the list< constituency>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Constituency> findAllSorted(final String property,
                                                    final String locale,
                                                    final boolean desc) {
        return getConstituencyRepository()
                .findAllSorted(property, locale, desc);
    }

    /**
     * List.
     *
     * @param param the param
     * @return the list< reference>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Reference> findConstituenciesStartingWith(final String param) {
        return getConstituencyRepository()
                .findConstituenciesStartingWith(param);
    }

    /**
     * List.
     *
     * @param districtId the district id
     * @return the list< constituency>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Constituency> findConstituenciesByDistrictId(final Long districtId) {
        return getConstituencyRepository().findConstituenciesByDistrictId(
                districtId);
    }

    /**
     * List.
     *
     * @param name the name
     * @return the list< constituency>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Constituency> findConstituenciesByDistrictName(final String name) {
        return getConstituencyRepository().findConstituenciesByDistrictName(
                name);
    }

    /**
     * List.
     *
     * @return the list< constituency>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Constituency> findAll() {
        return getConstituencyRepository().findAll();
    }

    /**
     * List.
     *
     * @param locale the locale
     * @return the list< constituency>
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Constituency> findAll(final String locale) {
        return getConstituencyRepository().findAll(locale);
    }

    /**
     * Check version.
     *
     * @return true, if check version
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public boolean checkVersion() {
        return Constituency.findById(this.id).getVersion() == this.version;
    }

    // ------------------------------------------Getters/Setters-----------------------------------
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
     * Gets the number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number.
     *
     * @param number the new number
     */
    public void setNumber(final String number) {
        this.number = number;
    }

    /**
     * Gets the districts.
     *
     * @return the districts
     */
    public List<District> getDistricts() {
        return districts;
    }

    /**
     * Sets the districts.
     *
     * @param districts the new districts
     */
    public void setDistricts(final List<District> districts) {
        this.districts = districts;
    }

    /**
     * Checks if is reserved.
     *
     * @return true, if is reserved
     */
    public boolean isReserved() {
        return reserved;
    }

    /**
     * Sets the reserved.
     *
     * @param reserved the new reserved
     */
    public void setReserved(final boolean reserved) {
        this.reserved = reserved;
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

}
