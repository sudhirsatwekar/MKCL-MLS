/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Document.java
 * Created On: Jan 5, 2012
 */

package org.mkcl.els.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.mkcl.els.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class Document.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "documents")
public class Document implements Serializable {

    // ---------------------------------Attributes-------------------------------------------------

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The tag. */
    @Column(length = 100)
    private String tag;

    /** The type. */
    @Column(length = 500)
    private String type;

    /** The original file name. */
    @Column(length = 255)
    private String originalFileName;

    /** The created on. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    /** The file data. */
    @Lob
    private byte[] fileData;

    /** The file size. */
    private long fileSize;

    /** The document repository. */
    @Autowired
    private transient DocumentRepository documentRepository;

    // ---------------------------------Constructors-----------------------------------------------

    /**
     * Instantiates a new document.
     */
    public Document() {
        super();
    }

    /**
     * Instantiates a new document.
     *
     * @param tag the tag
     * @param type the type
     * @param originalFileName the original file name
     * @param createdOn the created on
     * @param fileData the file data
     * @param fileSize the file size
     */
    public Document(final String tag,
            final String type,
            final String originalFileName,
            final Date createdOn,
            final byte[] fileData,
            final long fileSize) {
        super();
        this.tag = tag;
        this.type = type;
        this.originalFileName = originalFileName;
        this.createdOn = createdOn;
        this.fileData = fileData;
        this.fileSize = fileSize;
    }

    // ---------------------------------------Domain_Methods---------------------------------------

    /**
     * Gets the document repository.
     *
     * @return the document repository
     */
    public static DocumentRepository getDocumentRepository() {
        final DocumentRepository repository = new Document().documentRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "DocumentRepository has not been injected");
        }
        return repository;
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the document
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Document findById(final Long id) {
        return getDocumentRepository().find(id);
    }

    /**
     * Find by tag.
     *
     * @param tag the tag
     * @return the document
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static Document findByTag(final String tag) {
        return getDocumentRepository().findByTag(tag);
    }

    /**
     * Persist.
     *
     * @return the document
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public final Document persist() {
        documentRepository.save(this);
        documentRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the document
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public final Document update() {
        documentRepository.merge(this);
        documentRepository.flush();
        return this;
    }

    /**
     * Removes the.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public final void remove() {
        documentRepository.remove(this);
        documentRepository.flush();
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
     * Gets the tag.
     *
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets the tag.
     *
     * @param tag the new tag
     */
    public void setTag(final String tag) {
        this.tag = tag;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Gets the original file name.
     *
     * @return the original file name
     */
    public String getOriginalFileName() {
        return originalFileName;
    }

    /**
     * Sets the original file name.
     *
     * @param originalFileName the new original file name
     */
    public void setOriginalFileName(final String originalFileName) {
        this.originalFileName = originalFileName;
    }

    /**
     * Gets the created on.
     *
     * @return the created on
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the created on.
     *
     * @param createdOn the new created on
     */
    public void setCreatedOn(final Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets the file data.
     *
     * @return the file data
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * Sets the file data.
     *
     * @param fileData the new file data
     */
    public void setFileData(final byte[] fileData) {
        this.fileData = fileData;
    }

    /**
     * Gets the file size.
     *
     * @return the file size
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * Sets the file size.
     *
     * @param fileSize the new file size
     */
    public void setFileSize(final long fileSize) {
        this.fileSize = fileSize;
    }
}
