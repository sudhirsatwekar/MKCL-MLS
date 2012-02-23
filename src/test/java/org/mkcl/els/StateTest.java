/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.StateTest.java
 * Created On: Dec 19, 2011
 */
package org.mkcl.els;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.State;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class StateTest.
 *
 * @author sujitas
 * @since v1.0.0
 */
public class StateTest extends AbstractTest {

    /**
     * Test persist.
     */
    @Test
    @Transactional
    public final void testPersist() {
        final State state = new State("Karnataka");
        state.persist();
        Assert.assertTrue(null != State.findByName("Karnataka"));
    }

    /**
     * Test find all.
     */
    @Test
    @Transactional
    public final void testFindAll() {
        State karnataka = new State("Karnataka");
        State maharashtra = new State("Maharashtra");
        State delhi = new State("Delhi");
        karnataka.persist();
        maharashtra.persist();
        delhi.persist();
        final List<State> lstState = State.findAll();
        Assert.assertEquals(3, lstState.size());
    }


    /**
     * Test find all with specific locale.
     */
    @Test
    @Transactional
    public final void testFindAllWithSpecificLocale() {
        State karnataka = new State("Karnataka", "en");
        State maharashtra = new State("Maharashtra", "mr_IN");
        State delhi = new State("Delhi", "mr_IN");
        karnataka.persist();
        maharashtra.persist();
        delhi.persist();
        final List<State> lstState = State.findAll("mr_IN");
        Assert.assertEquals(2, lstState.size());
    }

    /**
     * Test find by id.
     */
    @Test
    @Transactional
    public final void testFindById() {
        final State statePersist = new State("Karnataka");
        statePersist.persist();
        final State state = State.findById(statePersist.getId());
        Assert.assertEquals("Karnataka", state.getName());
    }

    /**
     * Test find by name.
     */
    @Test
    @Transactional
    public final void testFindByName() {
        final State statePersist = new State("Karnataka");
        statePersist.persist();
        final State state = State.findByName("Karnataka");
        Assert.assertEquals("Karnataka", state.getName());
    }

    /**
     * Test update state.
     */
    @Test
    @Transactional
    public final void testUpdateState() {
        final State statePersist = new State("Karnataka");
        statePersist.persist();
        final State state = State.findById(statePersist.getId());
        state.setName("KarnatakaState");
        state.update();
        Assert.assertTrue(null != State.findByName("KarnatakaState"));
    }

    /**
     * Test remove state.
     */
    @Test
    @Transactional
    public void testRemoveState() {
        State statePresist = new State("Karnataka");
        statePresist.persist();
        State state = State.findByName("Karnataka");
        state.remove();
        Assert.assertTrue(null == State.findByName("Karnataka"));
    }

}
