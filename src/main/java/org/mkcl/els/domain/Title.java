/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Title.java
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
import org.mkcl.els.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class Title.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Configurable
@Entity
@Table(name = "titles")
public class Title implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    //---------------------------------Attributes------------------------//
    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @Column(length = 20)
    @NotEmpty
    private String name;

    /** The locale. */
    @Column(length = 50)
    private String locale;

    /** The version. */
    @Version
    private Long version;

    /** The title repository. */
    @Autowired
    private transient TitleRepository titleRepository;
    //---------------------------------Constructors----------------------//

    /**
     * Instantiates a new title.
     */
    public Title() {
        super();
    }

    /**
     * Instantiates a new title.
     *
     * @param name the name
     * @param locale the locale
     * @param version the version
     */
    public Title(final String name, final String locale, final Long version) {
        super();
        this.name = name;
        this.locale = locale;
        this.version = version;
    }

    //----------------------------Domain Methods-------------------------//

    /**
     * Gets the repository.
     *
     * @return the repository
     */
    public static TitleRepository getRepository() {
        TitleRepository repository = new Title().titleRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "TitleRepository has not been injected");
        }
        return repository;
    }

    /**
     * Persist.
     *
     * @return the title
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    public Title persist() {
        titleRepository.save(this);
        titleRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the title
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    public Title update() {
        titleRepository.merge(this);
        titleRepository.flush();
        return this;
    }


    /**
     * Removes the.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    public void remove() {
        titleRepository.remove(this);
        titleRepository.flush();
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the title
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Title findById(final Long id) {
        return getRepository().find(id);
    }


    /**
     * Find by name.
     *
     * @param name the name
     * @return the title
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Title findByName(final String name) {
        return getRepository().findByName(name);
    }

    /**
     * Find all sorted.
     *
     * @param property the property
     * @param locale the locale
     * @param descOrder the desc order
     * @return the list
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Title> findAllSorted(final String property,
                                            final String locale,
                                            final boolean descOrder) {
        return getRepository().findAllSorted(property, locale, descOrder);
    }

    /**
     * Find all.
     *
     * @return the list
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Title> findAll() {
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
        Title title = titleRepository.find(this.id);
        return title.getVersion().equals(this.version);
    }

    //----------------------------Getters/Setters------------------------//

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

}
