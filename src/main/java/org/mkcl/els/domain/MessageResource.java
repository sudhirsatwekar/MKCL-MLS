/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.MessageResource.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;
import org.mkcl.els.repository.MessageResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MessageResource.
 *
 * @author vishals
 * @since v1.0.0
 */
@Configurable
@Entity
@Table(name = "message_resources")
public class MessageResource implements Serializable {

    // ---------------------------------Attributes-------------------------------------------------
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The code. */
    @NotEmpty
    private String code;

    /** The value. */
    @NotEmpty
    private String value;

    /** The description. */
    private String description;

    /** The locale. */
    @NotEmpty
    private String locale;

    /** The version. */
    @Version
    private Long version;

    /** The message resource repository. */
    @Autowired
    private transient MessageResourceRepository messageResourceRepository;

    // ---------------------------------Constructors----------------------------------------------
    /**
     * Instantiates a new message resource.
     */
    public MessageResource() {
        super();
    }

    /**
     * Instantiates a new message resource.
     *
     * @param locale the locale
     * @param code the code
     * @param value the value
     * @param description the description
     */
    public MessageResource(final String locale,
            final String code,
            final String value,
            final String description) {
        super();
        this.locale = locale;
        this.code = code;
        this.value = value;
        this.description = description;
    }

    // -------------------------------Domain_Methods----------------------------------------------

    /**
     * Gets the repository.
     *
     * @return the repository
     */
    public static MessageResourceRepository getRepository() {
        final MessageResourceRepository repository =
                                new MessageResource().messageResourceRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "MessageResourceRepository has not been injected");
        }
        return repository;
    }

    /**
     * Find all.
     *
     * @return the list
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<MessageResource> findAll() {
        return getRepository().findAll();
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the message resource
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static MessageResource findById(final Long id) {
        return getRepository().find(id);
    }

    /**
     * Find all sorted.
     *
     * @param property the property
     * @param locale the locale
     * @param descOrder the desc order
     * @return the list
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<MessageResource> findAllSorted(final String property,
                                                      final String locale,
                                                      final boolean descOrder) {
        return getRepository().findAllSorted(property, locale, descOrder);
    }

    /**
     * Find by locale and code.
     *
     * @param locale the locale
     * @param code the code
     * @return the message resource
     * @author sujitas
     * @since v1.0.0
     */
    public static MessageResource findByLocaleAndCode(final String locale,
                                                      final String code) {
        return getRepository().findByLocaleAndCode(locale, code);
    }

    /**
     * Persist.
     *
     * @return the message resource
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public MessageResource persist() {
        messageResourceRepository.save(this);
        messageResourceRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the message resource
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public MessageResource update() {
        messageResourceRepository.merge(this);
        messageResourceRepository.flush();
        return this;
    }

    /**
     * Removes the.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public void remove() {
        messageResourceRepository.remove(this);
        messageResourceRepository.flush();
    }

    /**
     * Check version.
     *
     * @return true, if successful
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public boolean checkVersion() {
        MessageResource messageResource = messageResourceRepository
                .find(this.id);
        return messageResource.getVersion().equals(this.version);
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
     * Gets the message code.
     *
     * @return the message code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the message code.
     *
     * @param code the new code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(final String value) {
        this.value = value;
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
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(final String description) {
        this.description = description;
    }
}
