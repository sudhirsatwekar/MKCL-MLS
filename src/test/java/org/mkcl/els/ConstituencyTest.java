/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.ConstituencyTest.java
 * Created On: Jan 4, 2012
 */
package org.mkcl.els;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.Reference;
import org.mkcl.els.domain.State;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class ConstituencyTest.
 *
 * @author meenalw
 * @since v1.0.0
 */
public class ConstituencyTest extends AbstractTest {



    /**
     * Test persist.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testPersist() {
        List<District> list = new ArrayList<District>();

        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);

        Constituency c = new Constituency("Mumbai", "MH01", list, true, 2L,
                "en");
        c.persist();
        Assert.assertNotNull("Save data", c);
    }

    /**
     * Test update.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testUpdate() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);

        Constituency c = new Constituency("Mumbai", "MH01", list, true, 2L,
                "en");
        c.persist();
        Constituency c1 = Constituency.findById(c.getId());
        c1.setName("Mumbai Bombay");
        c1.update();
        Assert.assertNotNull("Data updated", c1);
    }

    /**
     * Test remove.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testRemove() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);
        Constituency c = new Constituency("Mumbai", "MH01", list, true, 2L,
                "en");
        c.persist();
        Constituency c1 = Constituency.findById(c.getId());
        c1.remove();
        Assert.assertNotNull("Removed the data", c1);
    }

    /**
     * Test find by name.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindByName() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);
        Constituency c = new Constituency("Mumbai", "MH01", list, true, 2L,
                "en");
        c.persist();
        Constituency c2 = Constituency.findByName("Mumbai");
        Assert.assertEquals("Mumbai", c2.getName());
    }

    /**
     * Test find by id.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindById() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);
        Constituency c = new Constituency("Mumbai", "MH01", list, true, 2L,
                "en");
        c.persist();
        Constituency c3 = Constituency.findById(c.getId());
        Assert.assertNotNull("Amehdabad", c3.getName());
    }

    /**
     * Test find all.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindAll() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);
        Constituency c = new Constituency("Mumbai", "MH01", list, true, 2L,
                "en");
        c.persist();
        Constituency.findAll();
        Assert.assertEquals(true, Constituency.findAll().size() > 0);
    }

    /**
     * Test find all sorted.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindAllSorted() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);
        Constituency c = new Constituency("Nagpur", "MH31", list, true, 2L,
                "en");
        c.persist();
        Assert.assertEquals(true, Constituency.findAll().size() > 0);
    }

    /**
     * Test find constituencies starting with.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindConstituenciesStartingWith() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);
        Constituency c = new Constituency("Nagpur", "MH31", list, true, 2L,
                "en");
        c.persist();
        List<Reference> c2 = Constituency.findConstituenciesStartingWith("N");
        Assert.assertEquals(true, c2.size() > 0);
    }

    /**
     * Test find constituencies by district id.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindConstituenciesByDistrictId() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);
        Constituency c = new Constituency("Nagpur", "MH31", list, true, 2L,
                "en");
        c.persist();
        List<Constituency> dList = Constituency
                .findConstituenciesByDistrictId(c.getDistricts().listIterator()
                        .next().getId());
        Assert.assertEquals(true, dList.size() > 0);
    }

    /**
     * Test find constituencies by district name.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindConstituenciesByDistrictName() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District d = new District("testDistrict", state);
        d.persist();
        list.add(d);
        Constituency c = new Constituency("Nagpur", "MH31", list, true, 2L,
                "en");
        c.persist();
        List<Constituency> cList = Constituency
                .findConstituenciesByDistrictName(c.getDistricts()
                        .listIterator().next().getName());
        Assert.assertEquals(true, cList.size() > 0);
    }

    /**
     * Test find all with locale.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindAllWithLocale() {
        List<District> list = new ArrayList<District>();
        // persist State object
        final State state = new State("testState");
        state.persist();

        // persist District object
        District district = new District("testDistrict", state);
        district.persist();
        list.add(district);
        Constituency c = new Constituency("Nagpur", "MH31", list, true, 2L,
                "en");
        Constituency c2 = new Constituency("Mumbai", "MH01", list, true, 2L,
                "en");
        c.persist();
        c2.persist();
        List<Constituency> cList = Constituency.findAll("en");
        Assert.assertEquals(true, cList.size() > 0);
    }

}
