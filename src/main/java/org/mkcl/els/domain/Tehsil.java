/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 ${company_name}.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Tehsil.java
 * Created On: Jan 7, 2012
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
import org.mkcl.els.repository.TehsilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class Tehsil.
 *
 * @author amitb
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "tehsils")
public class Tehsil implements Serializable {

    //--------------------Attributes-------------------------------------------

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

    /** The district. */
    @ManyToOne
    private District district;

    /** The locale. */
    @Column(length = 5)
    private String locale;

    /** The version. */
    @Version
    private Long version;

    //--------------------Constructors-------------------------------------------
    /**
     * Instantiates a new tehsil.
     */
    public Tehsil() {
        super();
    }

    /**
     * The Constructor.
     *
     * @param name the name
     * @param district the district
     * @param locale the locale
     * @param version the version
     */
    public Tehsil(final String name, final District district,
            final String locale, final Long version) {
        super();
        this.name = name;
        this.district = district;
        this.locale = locale;
        this.version = version;
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
     * Gets the district.
     *
     * @return the district
     */
    public District getDistrict() {
        return district;
    }

    /**
     * Sets the district.
     *
     * @param district the new district
     */
    public void setDistrict(final District district) {
        this.district = district;
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
     * @param locale the locale
     */
    public void setLocale(final String locale) {
        this.locale = locale;
    }

    /** The tehsil repository. */
    @Autowired
    private transient TehsilRepository tehsilRepository;

    /**
     * Gets the repository.
     *
     * @return the repository
     */
    public static TehsilRepository getTehsilRepository() {
        TehsilRepository tehsilRepository = new Tehsil().tehsilRepository;
        if (tehsilRepository == null) {
            throw new IllegalStateException(
                    "TehsilRepository has not been injected in Tehsil Domail");
        }
        return tehsilRepository;
    }

    /**
     * Find by name.
     *
     * @param name the name
     * @return the tehsil
     */
    @Transactional
    public static Tehsil findByName(final String name) {
        return getTehsilRepository().findByName(name);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the tehsil
     */
    @Transactional
    public static Tehsil findById(final Long id) {
        return getTehsilRepository().find(id);
    }

    /**
     * Find tehsils by district name.
     *
     * @param name the name
     * @return the list< tehsil>
     */
    @Transactional
    public static List<Tehsil> findTehsilsByDistrictName(final String name) {
        return getTehsilRepository().findTehsilsByDistrictName(name);
    }

    /**
     * Find tehsils by district id.
     *
     * @param districtId the district id
     * @return the list< tehsil>
     */
    @Transactional
    public static List<Tehsil> findTehsilsByDistrictId(final Long districtId) {
        return getTehsilRepository().findTehsilsByDistrictId(districtId);
    }

    /**
     * Persist.
     *
     * @return the tehsil
     */
    @Transactional
    public Tehsil persist() {
        tehsilRepository.save(this);
        tehsilRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @param tehsil the tehsil
     */
    @Transactional
    public void update(final Tehsil tehsil) {
        tehsilRepository.merge(this);
        tehsilRepository.flush();
    }

    /**
     * Removes the.
     */
    @Transactional
    public void remove() {
        tehsilRepository.remove(this);
        tehsilRepository.flush();
    }
    /**
     * Gets the tehsil locale.
     *
     * @return the tehsil locale
     */
    @Transactional
    public String getTehsilLocale() {
        return new Tehsil().getLocale();
    }
}
