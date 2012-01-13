/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Reference.java
 * Created On: Jan 6, 2012
 */
package org.mkcl.els.domain;

/**
 * The Class Reference.
 *
 * @author sandeeps
 * @version v1.0.0
 */
public class Reference {

    // ---------------------------------Attributes-------------------------------------------------
    /** The id. */
    private String id;

    /** The name. */
    private String name;

    // ---------------------------------Constructors-----------------------------------------------
    /**
     * Instantiates a new reference.
     */
    public Reference() {
        super();
    }

    /**
     * Instantiates a new reference.
     *
     * @param id the id
     * @param name the name
     */
    public Reference(final String id, final String name) {
        super();
        this.id = id;
        this.name = name;
    }

    // ------------------------------------------Getters/Setters-----------------------------------
    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final String id) {
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

}
