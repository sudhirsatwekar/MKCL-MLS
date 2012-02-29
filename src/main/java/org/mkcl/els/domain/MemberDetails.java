/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.MemberDetails.java
 * Created On: Jan 6, 2012
 */

package org.mkcl.els.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.mkcl.els.common.vo.MemberBiographyVO;
import org.mkcl.els.common.vo.MemberSearchPage;
import org.mkcl.els.repository.MemberDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MemberPersonalDetails.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "member_details")
@JsonIgnoreProperties({ "partyName", "constituency" })
public class MemberDetails implements Serializable {

    // ---------------------------------Attributes-------------------------------------------------

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The photo. */
    @Column(length = 50)
    private String photo;

    /** The title. */
    @Column(length = 5)
    private String title;

    /** The first name. */
    @Column(length = 100)
    private String firstName;

    /** The middle name. */
    @Column(length = 100)
    private String middleName;

    /** The last name. */
    @Column(length = 100)
    private String lastName;

    /** The alias. */
    @Column(length = 100)
    private String alias;

    /** The enable aliasing. */
    private boolean enableAliasing = false;

    /** The gender. */
    @Column(length = 6)
    private String gender;

    /** The constituency. */
    @ManyToOne(fetch = FetchType.EAGER)
    private Constituency constituency;

    /** The party name. */
    @OneToOne(fetch = FetchType.EAGER)
    private Party partyName;

    /** The father title. */
    @Column(length = 10)
    private String fatherTitle;

    /** The father name. */
    @Column(length = 200)
    private String fatherName;

    /** The mother title. */
    @Column(length = 10)
    private String motherTitle;

    /** The mother name. */
    @Column(length = 200)
    private String motherName;

    /** The birth date. */
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    /** The place of birth. */
    @Column(length = 100)
    private String placeOfBirth;

    /** The marital status. */
    private boolean maritalStatus;

    /** The marriage date. */
    @Column(length = 50)
    private String marriageDate;

    /** The spouse name. */
    @Column(length = 200)
    private String spouseName;

    /** The no of sons. */
    private Integer noOfSons;

    /** The no of daughter. */
    private Integer noOfDaughter;

    /** The educational qualification. */
    @Column(length = 1000)
    private String educationalQualification;

    /** The profession. */
    @Column(length = 1000)
    private String profession;

    /** The email. */
    @Column(length = 200)
    private String email;

    /** The present address. */
    @Column(length = 1000)
    private String presentAddress;

    /** The present state. */
    @ManyToOne
    private State presentState;

    /** The present district. */
    @ManyToOne
    private District presentDistrict;

    /** The present tehsil. */
    @ManyToOne
    private Tehsil presentTehsil;

    /** The present city. */
    @Column(length = 100)
    private String presentCity;

    /** The present pin code. */
    @Column(length = 7)
    private String presentPinCode;

    /** The present telephone. */
    @Column(length = 500)
    private String presentTelephone;

    /** The present fax. */
    @Column(length = 500)
    private String presentFax;

    /** The present mobile. */
    @Column(length = 500)
    private String presentMobile;

    /** The address same as above. */
    private boolean addressSameAsAbove = false;

    /** The permanent address. */
    @Column(length = 1000)
    private String permanentAddress;

    /** The permanent state. */
    @ManyToOne
    private State permanentState;

    /** The permanent district. */
    @ManyToOne
    private District permanentDistrict;

    /** The permanent tehsil. */
    @ManyToOne
    private Tehsil permanentTehsil;

    /** The permanent city. */
    @Column(length = 100)
    private String permanentCity;

    /** The permanent pin code. */
    @Column(length = 7)
    private String permanentPinCode;

    /** The permanent telephone. */
    @Column(length = 500)
    private String permanentTelephone;

    /** The permanent fax. */
    @Column(length = 500)
    private String permanentFax;

    /** The permanent mobile. */
    @Column(length = 500)
    private String permanentMobile;

    /** The no of terms. */
    private Integer noOfTerms;

    @Column(length = 10000)
    private String positionDetails;
    
    /** The socio cultural activities. */
    @Column(length = 10000)
    private String socioCulturalActivities;

    /** The literary artistic sc accomplishment. */
    @Column(length = 10000)
    private String literaryArtisticScAccomplishment;

    /** The books published. */
    @Column(length = 10000)
    private String booksPublished;

    /** The special interests. */
    @Column(length = 10000)
    private String specialInterests;

    /** The pastime recreation. */
    @Column(length = 10000)
    private String pastimeRecreation;

    /** The sports clubs. */
    @Column(length = 10000)
    private String sportsClubs;

    /** The countries visited. */
    @Column(length = 10000)
    private String countriesVisited;

    /** The experience. */
    @Column(length = 10000)
    private String experience;

    /** The other info. */
    @Column(length = 10000)
    private String otherInfo;

    /** The version. */
    @Version
    private Long version;

    /** The locale. */
    @Column(length = 50)
    private String locale;

    /** The member details repository. */
    @Autowired
    private transient MemberDetailsRepository memberDetailsRepository;

    // ---------------------------------Constructors----------------------------------------------

    /**
     * Instantiates a new member personal details.
     */
    public MemberDetails() {
        super();
    }

    /**
     * Instantiates a new member details.
     *
     * @param photo the photo
     * @param title the title
     * @param firstName the first name
     * @param middleName the middle name
     * @param lastName the last name
     * @param alias the alias
     * @param enableAliasing the enable aliasing
     * @param gender the gender
     * @param constituency the constituency
     * @param partyName the party name
     * @param fatherTitle the father title
     * @param fatherName the father name
     * @param motherTitle the mother title
     * @param motherName the mother name
     * @param birthDate the birth date
     * @param placeOfBirth the place of birth
     * @param maritalStatus the marital status
     * @param marriageDate the marriage date
     * @param spouseName the spouse name
     * @param noOfSons the no of sons
     * @param noOfDaughter the no of daughter
     * @param educationalQualification the educational qualification
     * @param profession the profession
     * @param email the email
     * @param presentAddress the present address
     * @param presentState the present state
     * @param presentDistrict the present district
     * @param presentTehsil the present tehsil
     * @param presentCity the present city
     * @param presentPinCode the present pin code
     * @param presentTelephone the present telephone
     * @param presentFax the present fax
     * @param presentMobile the present mobile
     * @param addressSameAsAbove the address same as above
     * @param permanentAddress the permanent address
     * @param permanentState the permanent state
     * @param permanentDistrict the permanent district
     * @param permanentTehsil the permanent tehsil
     * @param permanentCity the permanent city
     * @param permanentPinCode the permanent pin code
     * @param permanentTelephone the permanent telephone
     * @param permanentFax the permanent fax
     * @param permanentMobile the permanent mobile
     * @param noOfTerms the no of terms
     * @param memberPositions the member positions
     * @param socioCulturalActivities the socio cultural activities
     * @param literaryArtisticScAccomplishment the literary artistic sc
     *        accomplishment
     * @param booksPublished the books published
     * @param specialInterests the special interests
     * @param pastimeRecreation the pastime recreation
     * @param sportsClubs the sports clubs
     * @param countriesVisited the countries visited
     * @param experience the experience
     * @param otherInfo the other info
     * @param version the version
     * @param locale the locale
     */
    public MemberDetails(final String photo,
            final String title,
            final String firstName,
            final String middleName,
            final String lastName,
            final String alias,
            final boolean enableAliasing,
            final String gender,
            final Constituency constituency,
            final Party partyName,
            final String fatherTitle,
            final String fatherName,
            final String motherTitle,
            final String motherName,
            final Date birthDate,
            final String placeOfBirth,
            final boolean maritalStatus,
            final String marriageDate,
            final String spouseName,
            final Integer noOfSons,
            final Integer noOfDaughter,
            final String educationalQualification,
            final String profession,
            final String email,
            final String presentAddress,
            final State presentState,
            final District presentDistrict,
            final Tehsil presentTehsil,
            final String presentCity,
            final String presentPinCode,
            final String presentTelephone,
            final String presentFax,
            final String presentMobile,
            final boolean addressSameAsAbove,
            final String permanentAddress,
            final State permanentState,
            final District permanentDistrict,
            final Tehsil permanentTehsil,
            final String permanentCity,
            final String permanentPinCode,
            final String permanentTelephone,
            final String permanentFax,
            final String permanentMobile,
            final Integer noOfTerms,
            final String positionDetails,
            final String socioCulturalActivities,
            final String literaryArtisticScAccomplishment,
            final String booksPublished,
            final String specialInterests,
            final String pastimeRecreation,
            final String sportsClubs,
            final String countriesVisited,
            final String experience,
            final String otherInfo,
            final Long version,
            final String locale) {
        super();
        this.photo = photo;
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.alias = alias;
        this.enableAliasing = enableAliasing;
        this.gender = gender;
        this.constituency = constituency;
        this.partyName = partyName;
        this.fatherTitle = fatherTitle;
        this.fatherName = fatherName;
        this.motherTitle = motherTitle;
        this.motherName = motherName;
        this.birthDate = birthDate;
        this.placeOfBirth = placeOfBirth;
        this.maritalStatus = maritalStatus;
        this.marriageDate = marriageDate;
        this.spouseName = spouseName;
        this.noOfSons = noOfSons;
        this.noOfDaughter = noOfDaughter;
        this.educationalQualification = educationalQualification;
        this.profession = profession;
        this.email = email;
        this.presentAddress = presentAddress;
        this.presentState = presentState;
        this.presentDistrict = presentDistrict;
        this.presentTehsil = presentTehsil;
        this.presentCity = presentCity;
        this.presentPinCode = presentPinCode;
        this.presentTelephone = presentTelephone;
        this.presentFax = presentFax;
        this.presentMobile = presentMobile;
        this.addressSameAsAbove = addressSameAsAbove;
        this.permanentAddress = permanentAddress;
        this.permanentState = permanentState;
        this.permanentDistrict = permanentDistrict;
        this.permanentTehsil = permanentTehsil;
        this.permanentCity = permanentCity;
        this.permanentPinCode = permanentPinCode;
        this.permanentTelephone = permanentTelephone;
        this.permanentFax = permanentFax;
        this.permanentMobile = permanentMobile;
        this.noOfTerms = noOfTerms;
        this.positionDetails = positionDetails;
        this.socioCulturalActivities = socioCulturalActivities;
        this.literaryArtisticScAccomplishment = literaryArtisticScAccomplishment;
        this.booksPublished = booksPublished;
        this.specialInterests = specialInterests;
        this.pastimeRecreation = pastimeRecreation;
        this.sportsClubs = sportsClubs;
        this.countriesVisited = countriesVisited;
        this.experience = experience;
        this.otherInfo = otherInfo;
        this.version = version;
        this.locale = locale;
    }

    // -------------------------------Domain_Methods----------------------------------------------
    /**
     * Gets the repository.
     *
     * @return the repository
     */
    public static MemberDetailsRepository getRepository() {
        final MemberDetailsRepository repository = new MemberDetails().memberDetailsRepository;
        if (repository == null) {
            throw new IllegalStateException(
                    "MemberDetailsRepository has not been injected");
        }
        return repository;
    }

    /**
     * Find by id.
     *
     * @param id the id
     * @return the member details
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional(readOnly = true)
    public static MemberDetails findById(final Long id) {
        return getRepository().find(id);
    }

    /**
     * Find by id and locale.
     *
     * @param memberId the member id
     * @param locale the locale
     * @return the member details
     * @author sujitas
     * @since v1.0.0
     */
    public MemberDetails findByIdAndLocale(final Long memberId,
                                           final String locale) {
        return memberDetailsRepository.findByIdAndLocale(memberId, locale);
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public static MemberSearchPage searchMemberDetails(final String criteria1,
                                                       final String locale) {
        return getRepository().searchMemberDetails(criteria1, locale);
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param criteria2 the criteria2
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public static MemberSearchPage searchMemberDetails(final String criteria1,
                                                       final String criteria2,
                                                       final String locale) {
        return getRepository()
                .searchMemberDetails(criteria1, criteria2, locale);
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param criteria2 the criteria2
     * @param criteria3 the criteria3
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public static MemberSearchPage searchMemberDetails(final String criteria1,
                                                       final String criteria2,
                                                       final String criteria3,
                                                       final String locale) {
        return getRepository().searchMemberDetails(
                criteria1, criteria2, criteria3, locale);
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param page the page
     * @param rows the rows
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public static MemberSearchPage searchMemberDetails(final String criteria1,
                                                       final int page,
                                                       final int rows,
                                                       final String locale) {
        return getRepository().searchMemberDetails(
                criteria1, page, rows, locale);
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param criteria2 the criteria2
     * @param page the page
     * @param rows the rows
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public static MemberSearchPage searchMemberDetails(final String criteria1,
                                                       final String criteria2,
                                                       final int page,
                                                       final int rows,
                                                       final String locale) {
        return getRepository().searchMemberDetails(
                criteria1, criteria2, page, rows, locale);
    }

    /**
     * Search member details.
     *
     * @param criteria1 the criteria1
     * @param criteria2 the criteria2
     * @param criteria3 the criteria3
     * @param page the page
     * @param rows the rows
     * @param locale the locale
     * @return the member search page
     * @author sujitas
     * @since v1.0.0
     */
    public static MemberSearchPage searchMemberDetails(final String criteria1,
                                                       final String criteria2,
                                                       final String criteria3,
                                                       final int page,
                                                       final int rows,
                                                       final String locale) {
        return getRepository().searchMemberDetails(
                criteria1, criteria2, criteria3, page, rows, locale);
    }

    /**
     * Max no of terms.
     *
     * @param locale the locale
     * @return the integer
     * @author sujitas
     * @since v1.0.0
     */
    public static Integer maxNoOfTerms(final String locale) {
        return getRepository().maxNoOfTerms(locale);
    }

    /**
     * Find biography.
     *
     * @param id the id
     * @param locale the locale
     * @return the member biography vo
     * @author sujitas
     * @since v1.0.0
     */
    public static MemberBiographyVO findBiography(final long id,
                                                  final String locale) {
        return getRepository().findBiography(id, locale);
    }

    /**
     * Persist.
     *
     * @return the member details
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public MemberDetails persist() {
        memberDetailsRepository.save(this);
        memberDetailsRepository.flush();
        return this;
    }

    /**
     * Creates the member and default role.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public void createMemberAndDefaultRole() {
        memberDetailsRepository.createMemberAndDefaultRole(this);
    }

    /**
     * Update.
     *
     * @return the field
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public MemberDetails update() {
        memberDetailsRepository.merge(this);
        memberDetailsRepository.flush();
        return this;
    }

    /**
     * Update member other details.
     *
     * @return the int
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public int updateMemberOtherDetails() {
        return memberDetailsRepository.updateMemberOtherDetails(this);
    }

    /**
     * Update member personal details.
     *
     * @return the int
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public int updateMemberPersonalDetails() {
        return memberDetailsRepository.updateMemberPersonalDetails(this);
    }

    /**
     * Update member contact details.
     *
     * @return the int
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public int updateMemberContactDetails() {
        return memberDetailsRepository.updateMemberContactDetails(this);
    }

    /**
     * Removes the.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Transactional
    public void remove() {
        memberDetailsRepository.remove(this);
        memberDetailsRepository.flush();
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
        final MemberDetails memberDetails = memberDetailsRepository
                .find(this.id);
        return memberDetails.getVersion().equals(this.version);
    }
    
    /**
     * Reset photo.
     *
     * @return true, if successful
     */
    @Transactional
    public boolean resetPhoto() {
        this.setPhoto(null);
        this.update();
        return true;
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

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the middle name.
     *
     * @param middleName the new middle name
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the alias.
     *
     * @param alias the new alias
     */
    public void setAlias(final String alias) {
        this.alias = alias;
    }

    /**
     * Checks if is enable aliasing.
     *
     * @return true, if is enable aliasing
     */
    public boolean isEnableAliasing() {
        return enableAliasing;
    }

    /**
     * Sets the enable aliasing.
     *
     * @param enableAliasing the new enable aliasing
     */
    public void setEnableAliasing(final boolean enableAliasing) {
        this.enableAliasing = enableAliasing;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * Gets the constituency.
     *
     * @return the constituency
     */
    public Constituency getConstituency() {
        return constituency;
    }

    /**
     * Sets the constituency.
     *
     * @param constituency the new constituency
     */
    public void setConstituency(final Constituency constituency) {
        this.constituency = constituency;
    }

    /**
     * Gets the party name.
     *
     * @return the party name
     */
    public Party getPartyName() {
        return partyName;
    }

    /**
     * Sets the party name.
     *
     * @param partyName the new party name
     */
    public void setPartyName(final Party partyName) {
        this.partyName = partyName;
    }

    /**
     * Gets the father title.
     *
     * @return the father title
     */
    public String getFatherTitle() {
        return fatherTitle;
    }

    /**
     * Sets the father title.
     *
     * @param fatherTitle the new father title
     */
    public void setFatherTitle(final String fatherTitle) {
        this.fatherTitle = fatherTitle;
    }

    /**
     * Gets the father name.
     *
     * @return the father name
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * Sets the father name.
     *
     * @param fatherName the new father name
     */
    public void setFatherName(final String fatherName) {
        this.fatherName = fatherName;
    }

    /**
     * Gets the mother title.
     *
     * @return the mother title
     */
    public String getMotherTitle() {
        return motherTitle;
    }

    /**
     * Sets the mother title.
     *
     * @param motherTitle the new mother title
     */
    public void setMotherTitle(final String motherTitle) {
        this.motherTitle = motherTitle;
    }

    /**
     * Gets the mother name.
     *
     * @return the mother name
     */
    public String getMotherName() {
        return motherName;
    }

    /**
     * Sets the mother name.
     *
     * @param motherName the new mother name
     */
    public void setMotherName(final String motherName) {
        this.motherName = motherName;
    }

    /**
     * Gets the birth date.
     *
     * @return the birth date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date.
     *
     * @param birthDate the new birth date
     */
    public void setBirthDate(final Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Gets the place of birth.
     *
     * @return the place of birth
     */
    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    /**
     * Sets the place of birth.
     *
     * @param placeOfBirth the new place of birth
     */
    public void setPlaceOfBirth(final String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    /**
     * Checks if is marital status.
     *
     * @return true, if is marital status
     */
    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the marital status.
     *
     * @param maritalStatus the new marital status
     */
    public void setMaritalStatus(final boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * Gets the marriage date.
     *
     * @return the marriage date
     */
    public String getMarriageDate() {
        return marriageDate;
    }

    /**
     * Sets the marriage date.
     *
     * @param marriageDate the new marriage date
     */
    public void setMarriageDate(final String marriageDate) {
        this.marriageDate = marriageDate;
    }

    /**
     * Gets the spouse name.
     *
     * @return the spouse name
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * Sets the spouse name.
     *
     * @param spouseName the new spouse name
     */
    public void setSpouseName(final String spouseName) {
        this.spouseName = spouseName;
    }

    /**
     * Gets the no of sons.
     *
     * @return the no of sons
     */
    public Integer getNoOfSons() {
        return noOfSons;
    }

    /**
     * Sets the no of sons.
     *
     * @param noOfSons the new no of sons
     */
    public void setNoOfSons(final Integer noOfSons) {
        this.noOfSons = noOfSons;
    }

    /**
     * Gets the no of daughter.
     *
     * @return the no of daughter
     */
    public Integer getNoOfDaughter() {
        return noOfDaughter;
    }

    /**
     * Sets the no of daughter.
     *
     * @param noOfDaughter the new no of daughter
     */
    public void setNoOfDaughter(final Integer noOfDaughter) {
        this.noOfDaughter = noOfDaughter;
    }

    /**
     * Gets the educational qualification.
     *
     * @return the educational qualification
     */
    public String getEducationalQualification() {
        return educationalQualification;
    }

    /**
     * Sets the educational qualification.
     *
     * @param educationalQualification the new educational qualification
     */
    public void setEducationalQualification(final String educationalQualification) {
        this.educationalQualification = educationalQualification;
    }

    /**
     * Gets the profession.
     *
     * @return the profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * Sets the profession.
     *
     * @param profession the new profession
     */
    public void setProfession(final String profession) {
        this.profession = profession;
    }

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * Gets the present address.
     *
     * @return the present address
     */
    public String getPresentAddress() {
        return presentAddress;
    }

    /**
     * Sets the present address.
     *
     * @param presentAddress the new present address
     */
    public void setPresentAddress(final String presentAddress) {
        this.presentAddress = presentAddress;
    }

    /**
     * Gets the present state.
     *
     * @return the present state
     */
    public State getPresentState() {
        return presentState;
    }

    /**
     * Sets the present state.
     *
     * @param presentState the new present state
     */
    public void setPresentState(final State presentState) {
        this.presentState = presentState;
    }

    /**
     * Gets the present district.
     *
     * @return the present district
     */
    public District getPresentDistrict() {
        return presentDistrict;
    }

    /**
     * Sets the present district.
     *
     * @param presentDistrict the new present district
     */
    public void setPresentDistrict(final District presentDistrict) {
        this.presentDistrict = presentDistrict;
    }

    /**
     * Gets the present tehsil.
     *
     * @return the present tehsil
     */
    public Tehsil getPresentTehsil() {
        return presentTehsil;
    }

    /**
     * Sets the present tehsil.
     *
     * @param presentTehsil the new present tehsil
     */
    public void setPresentTehsil(final Tehsil presentTehsil) {
        this.presentTehsil = presentTehsil;
    }

    /**
     * Gets the present city.
     *
     * @return the present city
     */
    public String getPresentCity() {
        return presentCity;
    }

    /**
     * Sets the present city.
     *
     * @param presentCity the new present city
     */
    public void setPresentCity(final String presentCity) {
        this.presentCity = presentCity;
    }

    /**
     * Gets the present pin code.
     *
     * @return the present pin code
     */
    public String getPresentPinCode() {
        return presentPinCode;
    }

    /**
     * Sets the present pin code.
     *
     * @param presentPinCode the new present pin code
     */
    public void setPresentPinCode(final String presentPinCode) {
        this.presentPinCode = presentPinCode;
    }

    /**
     * Gets the present telephone.
     *
     * @return the present telephone
     */
    public String getPresentTelephone() {
        return presentTelephone;
    }

    /**
     * Sets the present telephone.
     *
     * @param presentTelephone the new present telephone
     */
    public void setPresentTelephone(final String presentTelephone) {
        this.presentTelephone = presentTelephone;
    }

    /**
     * Gets the present fax.
     *
     * @return the present fax
     */
    public String getPresentFax() {
        return presentFax;
    }

    /**
     * Sets the present fax.
     *
     * @param presentFax the new present fax
     */
    public void setPresentFax(final String presentFax) {
        this.presentFax = presentFax;
    }

    /**
     * Gets the present mobile.
     *
     * @return the present mobile
     */
    public String getPresentMobile() {
        return presentMobile;
    }

    /**
     * Sets the present mobile.
     *
     * @param presentMobile the new present mobile
     */
    public void setPresentMobile(final String presentMobile) {
        this.presentMobile = presentMobile;
    }

    /**
     * Checks if is address same as above.
     *
     * @return true, if is address same as above
     */
    public boolean isAddressSameAsAbove() {
        return addressSameAsAbove;
    }

    /**
     * Sets the address same as above.
     *
     * @param addressSameAsAbove the new address same as above
     */
    public void setAddressSameAsAbove(final boolean addressSameAsAbove) {
        this.addressSameAsAbove = addressSameAsAbove;
    }

    /**
     * Gets the permanent address.
     *
     * @return the permanent address
     */
    public String getPermanentAddress() {
        return permanentAddress;
    }

    /**
     * Sets the permanent address.
     *
     * @param permanentAddress the new permanent address
     */
    public void setPermanentAddress(final String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    /**
     * Gets the permanent state.
     *
     * @return the permanent state
     */
    public State getPermanentState() {
        return permanentState;
    }

    /**
     * Sets the permanent state.
     *
     * @param permanentState the new permanent state
     */
    public void setPermanentState(final State permanentState) {
        this.permanentState = permanentState;
    }

    /**
     * Gets the permanent district.
     *
     * @return the permanent district
     */
    public District getPermanentDistrict() {
        return permanentDistrict;
    }

    /**
     * Sets the permanent district.
     *
     * @param permanentDistrict the new permanent district
     */
    public void setPermanentDistrict(final District permanentDistrict) {
        this.permanentDistrict = permanentDistrict;
    }

    /**
     * Gets the permanent tehsil.
     *
     * @return the permanent tehsil
     */
    public Tehsil getPermanentTehsil() {
        return permanentTehsil;
    }

    /**
     * Sets the permanent tehsil.
     *
     * @param permanentTehsil the new permanent tehsil
     */
    public void setPermanentTehsil(final Tehsil permanentTehsil) {
        this.permanentTehsil = permanentTehsil;
    }

    /**
     * Gets the permanent city.
     *
     * @return the permanent city
     */
    public String getPermanentCity() {
        return permanentCity;
    }

    /**
     * Sets the permanent city.
     *
     * @param permanentCity the new permanent city
     */
    public void setPermanentCity(final String permanentCity) {
        this.permanentCity = permanentCity;
    }

    /**
     * Gets the permanent pin code.
     *
     * @return the permanent pin code
     */
    public String getPermanentPinCode() {
        return permanentPinCode;
    }

    /**
     * Sets the permanent pin code.
     *
     * @param permanentPinCode the new permanent pin code
     */
    public void setPermanentPinCode(final String permanentPinCode) {
        this.permanentPinCode = permanentPinCode;
    }

    /**
     * Gets the permanent telephone.
     *
     * @return the permanent telephone
     */
    public String getPermanentTelephone() {
        return permanentTelephone;
    }

    /**
     * Sets the permanent telephone.
     *
     * @param permanentTelephone the new permanent telephone
     */
    public void setPermanentTelephone(final String permanentTelephone) {
        this.permanentTelephone = permanentTelephone;
    }

    /**
     * Gets the permanent fax.
     *
     * @return the permanent fax
     */
    public String getPermanentFax() {
        return permanentFax;
    }

    /**
     * Sets the permanent fax.
     *
     * @param permanentFax the new permanent fax
     */
    public void setPermanentFax(final String permanentFax) {
        this.permanentFax = permanentFax;
    }

    /**
     * Gets the permanent mobile.
     *
     * @return the permanent mobile
     */
    public String getPermanentMobile() {
        return permanentMobile;
    }

    /**
     * Sets the permanent mobile.
     *
     * @param permanentMobile the new permanent mobile
     */
    public void setPermanentMobile(final String permanentMobile) {
        this.permanentMobile = permanentMobile;
    }

    /**
     * Gets the no of terms.
     *
     * @return the no of terms
     */
    public Integer getNoOfTerms() {
        return noOfTerms;
    }

    /**
     * Sets the no of terms.
     *
     * @param noOfTerms the new no of terms
     */
    public void setNoOfTerms(final Integer noOfTerms) {
        this.noOfTerms = noOfTerms;
    }

    /**
     * Gets the socio cultural activities.
     *
     * @return the socio cultural activities
     */
    public String getSocioCulturalActivities() {
        return socioCulturalActivities;
    }

    /**
     * Sets the socio cultural activities.
     *
     * @param socioCulturalActivities the new socio cultural activities
     */
    public void setSocioCulturalActivities(final String socioCulturalActivities) {
        this.socioCulturalActivities = socioCulturalActivities;
    }

    /**
     * Gets the literary artistic sc accomplishment.
     *
     * @return the literary artistic sc accomplishment
     */
    public String getLiteraryArtisticScAccomplishment() {
        return literaryArtisticScAccomplishment;
    }

    /**
     * Sets the literary artistic sc accomplishment.
     *
     * @param literaryArtisticScAccomplishment the new literary artistic sc
     *        accomplishment
     */
    public void setLiteraryArtisticScAccomplishment(final String literaryArtisticScAccomplishment) {
        this.literaryArtisticScAccomplishment = literaryArtisticScAccomplishment;
    }

    /**
     * Gets the books published.
     *
     * @return the books published
     */
    public String getBooksPublished() {
        return booksPublished;
    }

    /**
     * Sets the books published.
     *
     * @param booksPublished the new books published
     */
    public void setBooksPublished(final String booksPublished) {
        this.booksPublished = booksPublished;
    }

    /**
     * Gets the special interests.
     *
     * @return the special interests
     */
    public String getSpecialInterests() {
        return specialInterests;
    }

    /**
     * Sets the special interests.
     *
     * @param specialInterests the new special interests
     */
    public void setSpecialInterests(final String specialInterests) {
        this.specialInterests = specialInterests;
    }

    /**
     * Gets the pastime recreation.
     *
     * @return the pastime recreation
     */
    public String getPastimeRecreation() {
        return pastimeRecreation;
    }

    /**
     * Sets the pastime recreation.
     *
     * @param pastimeRecreation the new pastime recreation
     */
    public void setPastimeRecreation(final String pastimeRecreation) {
        this.pastimeRecreation = pastimeRecreation;
    }

    /**
     * Gets the sports clubs.
     *
     * @return the sports clubs
     */
    public String getSportsClubs() {
        return sportsClubs;
    }

    /**
     * Sets the sports clubs.
     *
     * @param sportsClubs the new sports clubs
     */
    public void setSportsClubs(final String sportsClubs) {
        this.sportsClubs = sportsClubs;
    }

    /**
     * Gets the countries visited.
     *
     * @return the countries visited
     */
    public String getCountriesVisited() {
        return countriesVisited;
    }

    /**
     * Sets the countries visited.
     *
     * @param countriesVisited the new countries visited
     */
    public void setCountriesVisited(final String countriesVisited) {
        this.countriesVisited = countriesVisited;
    }

    /**
     * Gets the experience.
     *
     * @return the experience
     */
    public String getExperience() {
        return experience;
    }

    /**
     * Sets the experience.
     *
     * @param experience the new experience
     */
    public void setExperience(final String experience) {
        this.experience = experience;
    }

    /**
     * Gets the other info.
     *
     * @return the other info
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    /**
     * Sets the other info.
     *
     * @param otherInfo the new other info
     */
    public void setOtherInfo(final String otherInfo) {
        this.otherInfo = otherInfo;
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

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return this.firstName + " " + this.middleName + " " + this.lastName;
    }

    /**
     * Gets the member position details.
     *
     * @return the member positions
     */
    public String getPositionDetails() {
        return positionDetails;
    }

    /**
     * Sets the member position details.
     *
     * @param memberPositions the new member positions
     */
    public void setMemberPositions(final String positionDetails) {
        this.positionDetails = positionDetails;
    }
}
