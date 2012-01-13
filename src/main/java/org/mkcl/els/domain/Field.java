/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Field.java
 * Created On: Dec 28, 2011
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

import org.mkcl.els.repository.FieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class Field.
 *
 * @author sandeeps
 * @since v1.0.0
 */
@Configurable
@Entity
@Table(name = "fields")
public class Field implements Serializable {

    // ---------------------------------Attributes-------------------------------------------------
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @Column(length = 50)
    private String name;

    /** The detail. */
    @Column(length = 100)
    private String detail;

    /** The mandatory. */
    @Column(length = 50)
    private String mandatory = "OPTIONAL";

    /** The visible. */
    @Column(length = 50)
    private String visible = "HIDDEN";

    /** The position. */
    private Integer position;

    /** The hint. */
    @Column(length = 100)
    private String hint;

    /** The form. */
    @Column(length = 50)
    private String form;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 5)
    private String locale;

    /** The field repository. */
    @Autowired
    private transient FieldRepository fieldRepository;

    // ---------------------------------Constructors----------------------------------------------
    /**
     * Instantiates a new field.
     */
    public Field() {
        super();
    }

    /**
     * Instantiates a new field.
     *
     * @param name the name
     * @param detail the detail
     * @param mandatory the mandatory
     * @param visible the visible
     * @param position the position
     * @param hint the hint
     * @param form the form
     * @param version the version
     * @param locale the locale
     */
    public Field(final String name,
            final String detail,
            final String mandatory,
            final String visible,
            final Integer position,
            final String hint,
            final String form,
            final Long version,
            final String locale) {
        super();
        this.name = name;
        this.detail = detail;
        this.mandatory = mandatory;
        this.visible = visible;
        this.position = position;
        this.hint = hint;
        this.form = form;
        this.version = version;
        this.locale = locale;
    }

    // -------------------------------Domain_Methods----------------------------------------------
    /**
     * Gets the field repository.
     *
     * @return the field repository
     */
    public static FieldRepository getFieldRepository() {
        final FieldRepository repository = new Field().fieldRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "FieldRepository has not been injected");
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
    public static List<Field> findAll() {
        return getFieldRepository().findAll();
    }

    /**
     * Find by form name sorted.
     *
     * @param formName the form name
     * @return the list
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static List<Field> findByFormNameSorted(final String formName) {
        return getFieldRepository().findByFormNameSorted(formName);
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the field
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Field findById(final Long id) {
        return getFieldRepository().find(id);
    }

    /**
     * Find by name and form.
     *
     * @param name the name
     * @param form the form
     * @return the field
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Field findByNameAndForm(final String name, final String form) {
        return getFieldRepository().findByNameAndForm(name, form);
    }

    /**
     * Persist.
     *
     * @return the field
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public Field persist() {
        fieldRepository.save(this);
        fieldRepository.flush();
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
    public Field update() {
        fieldRepository.merge(this);
        fieldRepository.flush();
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
        fieldRepository.remove(this);
        fieldRepository.flush();
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
        final Field field = fieldRepository.find(this.id);
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
     * Gets the detail.
     *
     * @return the detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets the detail.
     *
     * @param detail the new detail
     */
    public void setDetail(final String detail) {
        this.detail = detail;
    }

    /**
     * Gets the mandatory.
     *
     * @return the mandatory
     */
    public String getMandatory() {
        return mandatory;
    }

    /**
     * Sets the mandatory.
     *
     * @param mandatory the new mandatory
     */
    public void setMandatory(final String mandatory) {
        this.mandatory = mandatory;
    }

    /**
     * Gets the visible.
     *
     * @return the visible
     */
    public String getVisible() {
        return visible;
    }

    /**
     * Sets the visible.
     *
     * @param visible the new visible
     */
    public void setVisible(final String visible) {
        this.visible = visible;
    }

    /**
     * Gets the position.
     *
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Sets the position.
     *
     * @param position the new position
     */
    public void setPosition(final Integer position) {
        this.position = position;
    }

    /**
     * Gets the hint.
     *
     * @return the hint
     */
    public String getHint() {
        return hint;
    }

    /**
     * Sets the hint.
     *
     * @param hint the new hint
     */
    public void setHint(final String hint) {
        this.hint = hint;
    }

    /**
     * Gets the form.
     *
     * @return the form
     */
    public String getForm() {
        return form;
    }

    /**
     * Sets the form.
     *
     * @param form the new form
     */
    public void setForm(final String form) {
        this.form = form;
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
