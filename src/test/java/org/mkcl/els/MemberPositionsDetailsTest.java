/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.MemberPositionsDetailsTest.java
 * Created On: Jan 10, 2012
 */
package org.mkcl.els;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.MemberPositionsDetails;
import org.mkcl.els.domain.Party;
import org.mkcl.els.domain.State;
import org.mkcl.els.domain.Tehsil;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class MemberPositionsDetailsTest.
 *
 * @author nileshp
 */
public class MemberPositionsDetailsTest extends AbstractTest {



    /**
     * Persist.
     *
     * @author nileshp
     * @since v1.0.0
     * Persist.
     */
    @Test
    @Transactional
    public final void testPersist() {

        final State state = new State("testState");
        state.persist();

        // persist District object
        final District district = new District("testDistrict", state);
        district.persist();

        final List<District> lstDistricts = new ArrayList<District>();
        lstDistricts.add(district);

        // persist Tehsil object
        final Tehsil tehsil = new Tehsil("testTehsil", district, "en", 1L);
        tehsil.persist();

        // persist Constituency object
        final Constituency constituency = new Constituency("testConstituency",
                "1", lstDistricts, false, 1L, "en");
        constituency.persist();

        // persist Party object
        final Party party = new Party("testParty", "PA1", 1L, "en", null);
        party.persist();

        MemberDetails memberDetails = new MemberDetails("Photo", "Mr.", "firstName", "middleName",
                "lastName", "alias", true, "Male", constituency, party, "Mr.",
                "fatherName", "Mrs", "motherName", "birthDate", "placeOfBirth",
                false, "marriageDate", "spouseName", 1, 1,
                "educationalQualification", "profession", "test@test.org",
                "presentAddress", state, district, tehsil, "presentCity",
                "400703", "022-23456757", "022-23456757", "9003455434", false,
                "permanentAddress", state, district, tehsil, "permanentCity",
                "400001", "022-23456758", "022-23456758", "9889767876", 5,
                null, "socioCulturalActivities",
                "literaryArtisticScAccomplishment", "booksPublished",
                "specialInterests", "pastimeRecreation", "sportsClubs",
                "countriesVisited", "experience", "otherInfo", 1L, "en");

        memberDetails = memberDetails.persist();
        MemberPositionsDetails memberPositionsDetails = new MemberPositionsDetails();
        memberPositionsDetails.setDetails("testDetails");
        memberPositionsDetails.setMember(memberDetails);
        memberPositionsDetails.persist();
        Assert.assertNotNull("Saved memberPositionsDetails Data ", memberPositionsDetails);
    }

    /**
     * Update.
     *
     * @author nileshp
     * @since v1.0.0
     * Update.
     */
    @Test
    @Transactional
    public final void testUpdate() {

        final State state = new State("testState");
        state.persist();

        // persist District object
        final District district = new District("testDistrict", state);
        district.persist();

        final List<District> lstDistricts = new ArrayList<District>();
        lstDistricts.add(district);

        // persist Tehsil object
        final Tehsil tehsil = new Tehsil("testTehsil", district, "en", 1L);
        tehsil.persist();

        // persist Constituency object
        final Constituency constituency = new Constituency("testConstituency",
                "1", lstDistricts, false, 1L, "en");
        constituency.persist();

        // persist Party object
        final Party party = new Party("testParty", "PA1", 1L, "en", null);
        party.persist();

        MemberDetails memberDetails = new MemberDetails("Photo", "Mr.", "firstName", "middleName",
                "lastName", "alias", true, "Male", constituency, party, "Mr.",
                "fatherName", "Mrs", "motherName", "birthDate", "placeOfBirth",
                false, "marriageDate", "spouseName", 1, 1,
                "educationalQualification", "profession", "test@test.org",
                "presentAddress", state, district, tehsil, "presentCity",
                "400703", "022-23456757", "022-23456757", "9003455434", false,
                "permanentAddress", state, district, tehsil, "permanentCity",
                "400001", "022-23456758", "022-23456758", "9889767876", 5,
                null, "socioCulturalActivities",
                "literaryArtisticScAccomplishment", "booksPublished",
                "specialInterests", "pastimeRecreation", "sportsClubs",
                "countriesVisited", "experience", "otherInfo", 1L, "en");
        memberDetails = memberDetails.persist();
        MemberPositionsDetails memberPositionsDetails = new MemberPositionsDetails();
        memberPositionsDetails.setDetails("testDetails");
        memberPositionsDetails.setMember(memberDetails);
        memberPositionsDetails.persist();

        memberPositionsDetails.setDetails("testDetails1");
        MemberPositionsDetails memberPositionsDetails1 = memberPositionsDetails.update();
        Assert.assertNotNull("Update memberPositionsDetails Data ", memberPositionsDetails1);
    }

    /**
     * Removes the.
     *
     * @author nileshp
     * @since v1.0.0
     * Removes the.
     */
    @Test
    @Transactional
    public final void testRemove() {

        final State state = new State("testState");
        state.persist();

        // persist District object
        final District district = new District("testDistrict", state);
        district.persist();

        final List<District> lstDistricts = new ArrayList<District>();
        lstDistricts.add(district);

        // persist Tehsil object
        final Tehsil tehsil = new Tehsil("testTehsil", district, "en", 1L);
        tehsil.persist();

        // persist Constituency object
        final Constituency constituency = new Constituency("testConstituency",
                "1", lstDistricts, false, 1L, "en");
        constituency.persist();

        // persist Party object
        final Party party = new Party("testParty", "PA1", 1L, "en", null);
        party.persist();

        MemberDetails memberDetails = new MemberDetails("Photo", "Mr.", "firstName", "middleName",
                "lastName", "alias", true, "Male", constituency, party, "Mr.",
                "fatherName", "Mrs", "motherName", "birthDate", "placeOfBirth",
                false, "marriageDate", "spouseName", 1, 1,
                "educationalQualification", "profession", "test@test.org",
                "presentAddress", state, district, tehsil, "presentCity",
                "400703", "022-23456757", "022-23456757", "9003455434", false,
                "permanentAddress", state, district, tehsil, "permanentCity",
                "400001", "022-23456758", "022-23456758", "9889767876", 5,
                null, "socioCulturalActivities",
                "literaryArtisticScAccomplishment", "booksPublished",
                "specialInterests", "pastimeRecreation", "sportsClubs",
                "countriesVisited", "experience", "otherInfo", 1L, "en");
        memberDetails = memberDetails.persist();
        MemberPositionsDetails memberPositionsDetails = new MemberPositionsDetails();
        memberPositionsDetails.setDetails("testDetails");
        memberPositionsDetails.setMember(memberDetails);
        memberPositionsDetails.persist();

        memberPositionsDetails.remove();
        Assert.assertNotNull("Remove memberPositionsDetails Data ", memberPositionsDetails);
    }

    /**
     * Test find by id.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find by id.
     */
    @Test
    @Transactional
    public final void testFindById() {

        final State state = new State("testState");
        state.persist();

        // persist District object
        final District district = new District("testDistrict", state);
        district.persist();

        final List<District> lstDistricts = new ArrayList<District>();
        lstDistricts.add(district);

        // persist Tehsil object
        final Tehsil tehsil = new Tehsil("testTehsil", district, "en", 1L);
        tehsil.persist();

        // persist Constituency object
        final Constituency constituency = new Constituency("testConstituency",
                "1", lstDistricts, false, 1L, "en");
        constituency.persist();

        // persist Party object
        final Party party = new Party("testParty", "PA1", 1L, "en", null);
        party.persist();

        MemberDetails memberDetails = new MemberDetails("Photo", "Mr.", "firstName", "middleName",
                "lastName", "alias", true, "Male", constituency, party, "Mr.",
                "fatherName", "Mrs", "motherName", "birthDate", "placeOfBirth",
                false, "marriageDate", "spouseName", 1, 1,
                "educationalQualification", "profession", "test@test.org",
                "presentAddress", state, district, tehsil, "presentCity",
                "400703", "022-23456757", "022-23456757", "9003455434", false,
                "permanentAddress", state, district, tehsil, "permanentCity",
                "400001", "022-23456758", "022-23456758", "9889767876", 5,
                null, "socioCulturalActivities",
                "literaryArtisticScAccomplishment", "booksPublished",
                "specialInterests", "pastimeRecreation", "sportsClubs",
                "countriesVisited", "experience", "otherInfo", 1L, "en");

        memberDetails = memberDetails.persist();
        MemberPositionsDetails memberPositionsDetails = new MemberPositionsDetails();
        memberPositionsDetails.setDetails("testDetails");
        memberPositionsDetails.setMember(memberDetails);
        memberPositionsDetails = memberPositionsDetails.persist();
        memberPositionsDetails = MemberPositionsDetails.findById(memberPositionsDetails.getId());
        Assert.assertNotNull("Find memberPositionsDetails Data ", memberPositionsDetails);
    }

}
