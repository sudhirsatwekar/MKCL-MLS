/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.AssemblyRoleTest.java
 * Created On: Jan 9, 2012
 */
package org.mkcl.els;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.AssemblyRole;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.CustomParameter;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.Party;
import org.mkcl.els.domain.State;
import org.mkcl.els.domain.Tehsil;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AssemblyRoleTest.
 *
 * @author nileshp
 */
public class AssemblyRoleTest extends AbstractTest {

    /**
     * Test persist.
     *
     * @author nileshp
     * @since v1.0.0
     * Test persist.
     */
    @Test
    @Transactional
    public final void testPersist() {
        AssemblyRole assemblyRole = new AssemblyRole("testAssemblyRole", "en");
        assemblyRole.persist();
        Assert.assertNotNull("Saved assemblyRole Data ", assemblyRole);
    }

    /**
     * Test update.
     *
     * @author nileshp
     * @since v1.0.0
     * Test update.
     */
    @Test
    @Transactional
    public final void testUpdate() {
        AssemblyRole assemblyRole = new AssemblyRole("testAssemblyRole", "en");
        assemblyRole.persist();
        assemblyRole.setName("testAssemblyRole1");
        assemblyRole.update();
        Assert.assertNotNull("Saved assemblyRole Data ", assemblyRole);
    }

    /**
     * Test remove.
     *
     * @author nileshp
     * @since v1.0.0
     * Test remove.
     */
    @Test
    @Transactional
    public final void testRemove() {
        AssemblyRole assemblyRole = new AssemblyRole("testAssemblyRole", "en");
        assemblyRole.persist();
        assemblyRole.remove();
        Assert.assertNotNull("Removed assemblyRole Data ", assemblyRole);
    }

    /**
     * Test find by name.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find by name.
     */
    @Test
    @Transactional
    public final void testFindByName() {
        AssemblyRole assemblyRole = new AssemblyRole("testAssemblyRole", "en");
        assemblyRole.persist();
        assemblyRole = AssemblyRole.findByName("testAssemblyRole");
        Assert.assertNotNull("testFindByName assemblyRole Data ", assemblyRole);
    }

    /**
     * Test find all sorted.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find all sorted.
     */
    @Test
    @Transactional
    public final void testFindAllSorted() {
        AssemblyRole assemblyRole = new AssemblyRole("testAssemblyRole", "en");
        assemblyRole.persist();
        List<AssemblyRole> listAssemblyRole = AssemblyRole.findAllSorted("name", "en", false);
        Assert.assertNotNull("testFindAllSorted AssemblyRole Data ", listAssemblyRole);
    }

    /**
     * Find unassigned roles.
     *
     * @author nileshp
     * @since v1.0.0
     * Find unassigned roles.
     */
    @Test
    @Transactional
    public final void findUnassignedRoles() {
        MemberDetails memberDetails = intializeData();
        CustomParameter parameter = new CustomParameter("MEMBERROLE_ASSIGNED",
                "ASSIGNED", true, "");
        parameter.persist();

        List<AssemblyRole> listAssemblyRole = AssemblyRole.findUnassignedRoles(
                "en", memberDetails.getId());
        Assert.assertNotNull("findUnassignedRoles AssemblyRole Data ", listAssemblyRole);
    }

    /**
     * Find by name and locale.
     *
     * @author nileshp
     * @since v1.0.0
     * Find by name and locale.
     */
    @Test
    @Transactional
    public final void findByNameAndLocale() {
        AssemblyRole assemblyRole = new AssemblyRole("testAssemblyRole", "en");
        assemblyRole.persist();
        AssemblyRole assemblyRole1 = AssemblyRole.findByNameAndLocale(assemblyRole.getName(), "en");
        Assert.assertNotNull("findByNameAndLocale AssemblyRole Data ", assemblyRole1);
    }

    /**
     * Find by id.
     *
     * @author nileshp
     * @since v1.0.0
     * Find by id.
     */
    @Test
    @Transactional
    public final void findById() {
        AssemblyRole assemblyRole = new AssemblyRole("testAssemblyRole", "en");
        assemblyRole.persist();
        AssemblyRole assemblyRole1 = AssemblyRole.findById(assemblyRole.getId());
        Assert.assertNotNull("findById AssemblyRole Data ", assemblyRole1);
    }
    

    /**
     * Intialize data.
     *
     * @return the member details
     * @author meenalw
     * @since v1.0.0
     */
    @Transactional
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

        return new MemberDetails("Photo", "Mr.", "firstName", "middleName",
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
    }
}
