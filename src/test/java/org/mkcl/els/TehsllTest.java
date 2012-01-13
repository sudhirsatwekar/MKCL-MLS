/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.TehsilTest.java
 * Created On: Dec 23, 2011
 */

package org.mkcl.els;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.Tehsil;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class TehsllTest.
 */
public class TehsllTest extends AbstractTest {

    /**
     * Test create new tehsil with all parameter.
     */
    @Test
    @Transactional
    public void testCreateNewTehsilWithAllParameter() {
        final Tehsil tehsil = new Tehsil("Test Tehsil", District.findByName("Thane"),
                "en", 1L);
        tehsil.persist();
        Assert.assertEquals(true, tehsil.getId() > 0);
    }

    /**
     * Test update existing tehsil.
     */
    @Test
    @Transactional
    public void testUpdateExistingTehsil() {
        final Tehsil tehsil = new Tehsil("Test Tehsil", District.findByName("Thane"),
                "en", 1L);
        tehsil.persist();
        final Long tid = tehsil.getId();
        final Tehsil updateTehsil = Tehsil.findById(tid);
        updateTehsil.setName("Update Tehsil");
        updateTehsil.update(updateTehsil);
        Assert.assertEquals(true, updateTehsil.getName()
                .equals("Update Tehsil"));
    }

    /**
     * Test remove tehsil by id.
     */
    @Test
    @Transactional
    public void testRemoveTehsilById() {
        final Tehsil tehsil = new Tehsil("Test Tehsil", District.findByName("Thane"),
                "en", 1L);
        tehsil.persist();
        final Long tid = tehsil.getId();
        final Tehsil removeTehsil = Tehsil.findById(tid);
        removeTehsil.remove();
        Assert.assertNotNull("removed tehsil Data ", removeTehsil);
    }

    /**
     * Test find tehsil by version.
     */
    @Test
    @Transactional
    public void testFindTehsilByVersion() {
        final Tehsil tehsil = new Tehsil("Test Tehsil", District.findByName("Thane"),
                "en", 1L);
        tehsil.persist();
        Assert.assertEquals(true, tehsil.getVersion().equals(1L));
    }

    /**
     * Test find tehsil by name.
     */
    @Test
    @Transactional
    public void testFindTehsilByName() {
        final Tehsil tehsil = new Tehsil("Test Tehsil", District.findByName("Thane"),
                "en", 1L);
        tehsil.persist();
        Assert.assertEquals(true, tehsil.getName().equals("Test Tehsil"));
    }

    /**
     * Test find all tehsil by district.
     */
    @Test
    @Transactional
    public void testFindAllTehsilByDistrict() {
        final Tehsil tehsil1 = new Tehsil("Test 1 Tehsil",
                District.findByName("Thane"), "en", 1L);
        final Tehsil tehsil2 = new Tehsil("Test 2 Tehsil",
                District.findByName("Thane"), "en", 2L);
        final Tehsil tehsil3 = new Tehsil("Test 3 Tehsil",
                District.findByName("Thane"), "en", 3L);
        tehsil1.persist();
        tehsil2.persist();
        tehsil3.persist();
        final List<Tehsil> allTehsil = Tehsil
                .findTehsilsByDistrictName("Thane");
        Assert.assertEquals(true, !allTehsil.isEmpty());
    }
}
