/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.MemberDetailsTest.java
 * Created On: Jan 9, 2012
 */
package org.mkcl.els;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.Assembly;
import org.mkcl.els.domain.AssemblyStructure;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.Party;
import org.mkcl.els.domain.State;
import org.mkcl.els.domain.Tehsil;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MemberDetailsTest.
 *
 * @author sujitas
 * @since v1.0.0
 */
public class MemberDetailsTest extends AbstractTest {

    /**
     * Test persist.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testPersist() {
        //MemberDetails memberDetails = intializeData();
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


        memberDetails.persist();

        Assert.assertNotNull("Saved Data ", memberDetails);

    }

    /**
     * Test update.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testUpdate() {
        // persist State object
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
        MemberDetails memPersist = memberDetails.persist();

        MemberDetails memUpdate = MemberDetails.findById(memPersist.getId());
        memUpdate.setBirthDate("23/10/1980");

        MemberDetails updated = memberDetails.update();

        Assert.assertEquals(
                "23/10/1980", MemberDetails.findById(updated.getId())
                        .getBirthDate());

    }

    /**
     * Test max no of terms.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testMaxNoOfTerms() {
        // persist State object
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
        memberDetails.persist();

        Integer integer = MemberDetails.maxNoOfTerms("en");

        Assert.assertEquals(true, integer > 0);

    }

    /**
     * Test create member and default role.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testCreateMemberAndDefaultRole() {
        // persist State object
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

        //persist assembly object
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure = assemblyStructure.persist();
        Assembly assembly = new Assembly(assemblyStructure, "testAssembly", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                true, 0L, "en", 1L);
        assembly.persist();

        // persist custom paramaeter
        final CustomParameter customParameter = new CustomParameter("DEFAULT_MEMBERROLE",
                "Member", true, "Testing value for custom parameter");
        customParameter.persist();

        final CustomParameter customParameter1 = new CustomParameter("MEMBERROLE_ASSIGNED",
                "Applicable", true, "Testing value for custom parameter");
        customParameter1.persist();


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
        memberDetails.createMemberAndDefaultRole();
        Assert.assertNotNull("Saved Data ", memberDetails);
    }

    /**
     * Test update member other details.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testUpdateMemberOtherDetails() {
        // persist State object
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
        MemberDetails memPersist = memberDetails.persist();

        MemberDetails memUpdate = MemberDetails.findById(memPersist.getId());
        memUpdate.setNoOfTerms(10);

        memberDetails.updateMemberOtherDetails();

        int noOfTerms = MemberDetails.findById(memPersist.getId())
                .getNoOfTerms();
        Assert.assertEquals(10, noOfTerms);

    }

    /**
     * Test update member personal details.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testUpdateMemberPersonalDetails() {
        // persist State object
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
        MemberDetails memPersist = memberDetails.persist();

        MemberDetails memUpdate = MemberDetails.findById(memPersist.getId());
        memUpdate.setFirstName("UpdatedFirstName");

        memberDetails.updateMemberPersonalDetails();

        Assert.assertEquals(
                "UpdatedFirstName", MemberDetails.findById(memPersist.getId())
                        .getFirstName());

    }

    /**
     * Test update member contact details.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testUpdateMemberContactDetails() {
        // persist State object
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
        MemberDetails memPersist = memberDetails.persist();

        MemberDetails memUpdate = MemberDetails.findById(memPersist.getId());
        memUpdate.setEmail("updatedemail@test.org");

        memberDetails.updateMemberContactDetails();

        Assert.assertEquals(
                "updatedemail@test.org",
                MemberDetails.findById(memPersist.getId()).getEmail());

    }

    /**
     * Test remove.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testRemove() {
        // persist State object
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
        MemberDetails memPersist = memberDetails.persist();

        MemberDetails memUpdate = MemberDetails.findById(memPersist.getId());

        memUpdate.remove();

        Assert.assertNull(
                "Not Removed", MemberDetails.findById(memPersist.getId()));

    }

    /**
     * Intialize data.
     *
     * @return the member details
     * @author sujitas
     * @since v1.0.0
     */
    /*@Transactional
    public MemberDetails intializeData() {
        // persist State object
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
        return memberDetails;
    }*/

}
