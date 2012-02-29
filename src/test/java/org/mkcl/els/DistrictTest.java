/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.DistrictTest.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.State;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class DistrictTest.
 *
 * @author nileshp
 */
public class DistrictTest extends AbstractTest {


    @PersistenceContext EntityManager em;
    /**
     * Test persist.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test persist.
     */
    @Test
    @Transactional
    public final void testPersist() {
        State state = new State("testState");
        state.persist();
        final District district = new District("Nasik", state);
        district.persist();
        Assert.assertNotNull("Saved District Data ", district);
    }


    /**
     * Test find all.
     */
    @Test
    @Transactional(readOnly = true)
    public final void testFindAll() {
        System.out.println(new Date());
        District.findAll();
        System.out.println(new Date());
        State state = new State("testState");
        state.persist();
        final District district = new District("Nasik", state);
        final District district2 = new District("Pune", state);
        district.persist();
        district2.persist();
        Query query = em.createQuery("SELECT d from District d left join fetch d.state s");
        System.out.println(new Date());
        List<District> districts = query.getResultList();
        System.out.println(new Date());
        Assert.assertTrue(3 == districts.size());
    }



    /**
     * Test find by name.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test find by name.
     */
    @Test
    @Transactional
    public final void testFindByName() {
        final State statePersist = new State("testState");
        statePersist.persist();
        final District districtPersist = new District("Nasik", statePersist);
        districtPersist.persist();

        final District district = District.findByName(districtPersist.getName());
        Assert.assertNotNull(district);
    }

    /**
     * Test find by id.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test find by id.
     */
    @Test
    @Transactional
    public final void testFindById() {
        final State statePersist = new State("testState");
        statePersist.persist();
        final District districtPersist = new District("Nasik", statePersist);
        districtPersist.persist();

        final District district = District.findById(districtPersist.getId());
        Assert.assertEquals("Nasik", district.getName());
    }

    /**
     * Test update state.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test update district.
     */
    @Test
    @Transactional
    public final void testUpdateDistrict() {
        final State statePersist = new State("testState");
        statePersist.persist();
        final District districtPersist = new District("Nasik", statePersist);
        districtPersist.persist();

        final District district = District.findById(districtPersist.getId());
        district.setName("Mumbaia");
        district.update();
        Assert.assertNotNull("updated District data is :-  ", District.findByName("Mumbaia"));
    }

    /**
     * Test remove state.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test remove district.
     */
    @Test
    @Transactional
    public final void testRemoveDistrict() {
        final State statePersist = new State("testState");
        statePersist.persist();
        final District districtPersist = new District("Nasik", statePersist);
        districtPersist.persist();

        District district = District.findByName(districtPersist.getName());
        district.remove();
        Assert.assertNotNull("removed district Data ", district);
    }


    /**
     * Test find district by state id.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test find district by state id.
     */
    @Test
    @Transactional
    public final void testFindDistrictByStateId() {
        final State statePersist = new State("testState");
        statePersist.persist();
        final District districtPersist = new District("Nasik", statePersist);
        districtPersist.persist();

        final List<District> districtList = District.findDistrictsByStateId(districtPersist.getState().getId());
        Assert.assertNotNull("District List is :- ", districtList);
    }


    /**
     * Test find sort districts by state id.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test find sort districts by state id.
     */
    @Test
    @Transactional
    public final void testFindSortDistrictsByStateId() {
        final State statePersist = new State("testState");
        statePersist.persist();
        final District districtPersist = new District("Nasik", statePersist);
        districtPersist.persist();

        final List<District> districtList = District.findDistrictsByStateId(districtPersist.getState().getId(), "name", false);
        Assert.assertNotNull("District List is :- ", districtList);
    }


    /**
     * Test find sort districts by state name.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test find sort districts by state name.
     */
    @Test
    @Transactional
    public final void testFindSortDistrictsByStateName() {
        final State statePersist = new State("testState");
        statePersist.persist();
        final District districtPersist = new District("Nasik", statePersist);
        districtPersist.persist();

        final List<District> districtList = District.findDistrictsByStateName(districtPersist.getState().getName());
        Assert.assertNotNull("District List is :- ", districtList);
    }

    /**
     * Test find districts by constituency id.
     *
     * @author nileshp
     * @since v1.0.0
     * * Test find districts by constituency id.
     */
    @Test
    @Transactional
    public final void testFindDistrictsByConstituencyId() {
        final State statePersist = new State("testState");
        statePersist.persist();
        final District districtPersist = new District("Nasik", statePersist);
        districtPersist.persist();

        List<District> list = new ArrayList<District>();
        list.add(districtPersist);

        Constituency c = new Constituency("Mumbai", "MH01", list, true, 2L , "en");
        c.persist();

        final List<District> districtList =
                District.findDistrictsByConstituencyId(c.getId(), "name", false);
        Assert.assertNotNull("District List is :- ", districtList);
    }
}
