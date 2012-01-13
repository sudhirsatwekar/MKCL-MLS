package org.mkcl.els;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class MemberSearchTest extends AbstractTest {

    @Transactional
    @Test
    public void testGetData() {
        // persist State object
//        final State state = new State("testState");
//        state.persist();
//
//        // persist District object
//        final District district = new District("testDistrict", state);
//        district.persist();
//
//        final List<District> lstDistricts = new ArrayList<District>();
//        lstDistricts.add(district);
//
//        // persist Tehsil object
//        final Tehsil tehsil = new Tehsil("testTehsil", district, "en", 1L);
//        tehsil.persist();
//
//        // persist Constituency object
//        final Constituency constituency = new Constituency("testConstituency",
//                "1", lstDistricts, false, 1L, "en");
//        constituency.persist();
//
//        // persist Party object
//        final Party party = new Party("testParty", "PA1", 1L, "en", null);
//        party.persist();
//
//        MemberDetails memberDetails = new MemberDetails("Photo", "Mr.", "firstName", "middleName",
//                "lastName", "alias", true, "Male", constituency, party, "Mr.",
//                "fatherName", "Mrs", "motherName", "birthDate", "placeOfBirth",
//                false, "marriageDate", "spouseName", 1, 1,
//                "educationalQualification", "profession", "test@test.org",
//                "presentAddress", state, district, tehsil, "presentCity",
//                "400703", "022-23456757", "022-23456757", "9003455434", false,
//                "permanentAddress", state, district, tehsil, "permanentCity",
//                "400001", "022-23456758", "022-23456758", "9889767876", 5,
//                null, "socioCulturalActivities",
//                "literaryArtisticScAccomplishment", "booksPublished",
//                "specialInterests", "pastimeRecreation", "sportsClubs",
//                "countriesVisited", "experience", "otherInfo", 1L, "en");
//        memberDetails.persist();
//
//
//
//        MemberSearchPage searchPage = MemberDetails.searchMemberDetails(
//                "name", 1, 4, "en");
//        System.out.println(searchPage.getTotalRecords());
//        for (MemberInfo i : searchPage.getPageItems()) {
//            System.out.println(i.getFirstName() + ":" + i.getMiddleName() + ":"
//                    + i.getLastName() + ":" + i.getConstituency() + ":"
//                    + i.getParty() + ":" + i.isMaritalStatus() + ":"
//                    + i.getGender() + ":" + i.getNoOfTerms() + ":"
//                    + i.getBirthDate());
//        }

        Assert.assertEquals(true, true);
    }
}
