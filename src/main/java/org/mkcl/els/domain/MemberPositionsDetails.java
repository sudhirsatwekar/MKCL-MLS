/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.MemberPositionsDetails.java
 * Created On: Jan 6, 2012
 */
package org.mkcl.els.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.mkcl.els.repository.MemberPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MemberPositionsDetails.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "member_positions")
@JsonIgnoreProperties({ "member" })
public class MemberPositionsDetails implements Serializable {

    // ---------------------- Attributes --------------------------
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The member. */
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberDetails member;

    /** The period. */
    @Column(length = 30)
    private String period;

    /** The details. */
    @Column(length = 10000)
    private String details;

    /** The member position repository. */
    @Autowired
    private transient MemberPositionRepository memberPositionRepository;

    // ---------------------- Constructor --------------------------
    /**
     * Instantiates a new member positions details.
     */
    public MemberPositionsDetails() {
        super();
    }

    // ---------------------- domain methods --------------------------

    /**
     * Gets the member position repository.
     *
     * @return the member position repository
     */
    public static MemberPositionRepository getMemberPositionRepository() {
        MemberPositionRepository memberPositionRepository =
                new MemberPositionsDetails().memberPositionRepository;
        if (memberPositionRepository == null) {
            throw new IllegalStateException(
                    "Member position Repository has not been injected "
                            + "in Member position domain");
        }
        return memberPositionRepository;
    }


    /**
     * Find by id.
     *
     * @param id the id
     * @return the member positions details
     * @author nileshp
     * @since v1.0.0
     */
    @Transactional (readOnly = true)
    public static MemberPositionsDetails findById(final Long id) {
        return getMemberPositionRepository().find(id);
    }

    /**
     * Persist.
     *
     * @return the member positions details
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public MemberPositionsDetails persist() {
        memberPositionRepository.save(this);
        memberPositionRepository.flush();
        return this;
    }

    /**
     * Update.
     *
     * @return the member positions details
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public MemberPositionsDetails update() {
        memberPositionRepository.merge(this);
        memberPositionRepository.flush();
        return this;
    }

    /**
     * Removes the.
     *
     * @author nileshp
     * @since v1.0.0
     * Removes the.
     */
    @Transactional
    public void remove() {
        memberPositionRepository.remove(this);
        memberPositionRepository.flush();
    }

    //----------------------- getters & setters ----------------------
    /**
     * Gets the period.
     *
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the period.
     *
     * @param period the new period
     */
    public void setPeriod(final String period) {
        this.period = period;
    }

    /**
     * Gets the details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the details.
     *
     * @param details the new details
     */
    public void setDetails(final String details) {
        this.details = details;
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
     * Gets the member.
     *
     * @return the member
     */
    public MemberDetails getMember() {
        return member;
    }

    /**
     * Sets the member.
     *
     * @param member the new member
     */
    public void setMember(final MemberDetails member) {
        this.member = member;
    }



}
