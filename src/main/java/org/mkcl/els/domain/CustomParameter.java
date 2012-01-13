/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.CustomParameter.java
 * Created On: Dec 30, 2011
 */
package org.mkcl.els.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.validator.constraints.NotEmpty;
import org.mkcl.els.repository.CustomParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class CustomParameter.
 *
 * @author vishals
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "custom_parameters")
public class CustomParameter implements Serializable {

    // ---------------------------------Attributes-------------------------------------------------
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @Column(length = 100)
    @NotEmpty
    private String name;

    /** The value. */
    @Column(length = 500)
    @NotEmpty
    private String value;

    /** The updateable. */
    private Boolean updateable;

    /** The description. */
    @Column(length = 2000)
    private String description;

    /** The version. */
    @Version
    private Long version;

    /** The custom parameter repository. */
    @Autowired
    private transient CustomParameterRepository customParameterRepository;

    // ---------------------------------Constructors----------------------------------------------
    /**
     * Instantiates a new custom parameter.
     */
    public CustomParameter() {
        super();
    }

    /**
     * Instantiates a new custom parameter.
     *
     * @param name the name
     * @param value the value
     * @param updateable the updateable
     * @param description the description
     */
    public CustomParameter(final String name,
            final String value,
            final Boolean updateable,
            final String description) {
        super();
        this.name = name;
        this.value = value;
        this.updateable = updateable;
        this.description = description;
    }

    // -------------------------------Domain_Methods----------------------------------------------
    /**
     * Gets the repository.
     *
     * @return the repository
     */
    public static CustomParameterRepository getRepository() {
        final CustomParameterRepository repository =
                    new CustomParameter().customParameterRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "CustomParameterRepository has not been injected");
        }
        return repository;
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the custom parameter
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static CustomParameter findById(final Long id) {
        return getRepository().find(id);
    }

    /**
     * Find by name.
     *
     * @param name the name
     * @return the custom parameter
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static CustomParameter findByName(final String name) {
        return getRepository().findByName(name);
    }

    /**
     * Persist.
     *
     * @return the custom parameter
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public CustomParameter persist() {
        customParameterRepository.save(this);
        customParameterRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the field
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public CustomParameter update() {
        customParameterRepository.merge(this);
        customParameterRepository.flush();
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
        customParameterRepository.remove(this);
        customParameterRepository.flush();
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
        final CustomParameter field = customParameterRepository.find(this.id);
        return field.getVersion().equals(this.version);
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
     * Gets the updateable.
     *
     * @return the updateable
     */
    public Boolean getUpdateable() {
        return updateable;
    }

    /**
     * Sets the updateable.
     *
     * @param updateable the new updateable
     */
    public void setUpdateable(final Boolean updateable) {
        this.updateable = updateable;
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
