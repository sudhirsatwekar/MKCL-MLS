/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.PartyTest.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.Party;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class PartyTest.
 *
 * @author meenalw
 * @since v1.0.0
 */
public class PartyTest extends AbstractTest {

    /**
     * Test persist.
     */
    @Test
    @Transactional
    public final void testPersist() {
        Party party = new Party("Indian National Congress", "INC", 2L, "en",
                "DOC-80857458");
        party.persist();
        Assert.assertNotNull("Saved state Data ", party);
    }

    /**
     * Test update party.
     */
    @Test
    @Transactional
    public final void testUpdateParty() {
        Party list = new Party("Indian National", "IN", 2L, "en",
                "DOC-80857458");
        list.persist();
        Party party = Party.findById(list.getId());
        party.setName("Indian National Congress");
        party.setAbbreviation("INC");
        party.update();
        Assert.assertNotNull("Party data updated", party);
    }

    /**
     * Test remove party.
     */
    @Test
    @Transactional
    public final void testRemoveParty() {
        Party party = new Party("Indian National Congress", "INC", 2L, "en",
                "DOC-80857458");
        party.persist();
        Party id = Party.findById(party.getId());
        id.remove();
        Assert.assertNotNull(id);

    }

    /**
     * Test find by id.
     */
    @Test
    @Transactional
    public final void testFindById() {
        Party party = new Party("Indian National Congress", "INC", 2L, "en",
                "DOC-80857458");
        party.persist();
        final Party party1 = Party.findById(party.getId());
        Assert.assertNotNull("Indian National Congress", party1.getName());
    }

    /**
     * Test find by name.
     */
    @Test
    @Transactional
    public final void testFindByName() {
        Party party = new Party("Kandriye Samaj Mandal", "KSM", 2L, "en",
                "DOC-80857458");
        party.persist();
        Party lst = Party.findByName(party.getName());
        Assert.assertEquals("Kandriye Samaj Mandal", lst.getName());
    }

    /**
     * Test find all.
     */
    @Test
    @Transactional
    public final void testFindAll() {
        Party party = new Party("Kandriye Samaj Mandal", "KSM", 2L, "en",
                "DOC-80857458");
        Party party1 = new Party("Indian National Congress", "INC", 2L, "en",
                "DOC-80857458");
        party.persist();
        party1.persist();
        List<Party> list = Party.findAll();
        Assert.assertEquals(true, list.size() > 0);
    }

    /**
     * Test find all sorted.
     */
    @Test
    @Transactional
    public void testFindAllSorted() {
        Party party1 = new Party("Kandriye Samaj Mandal", "KSM", 2L, "en",
                "DOC-80857458");
        Party party2 = new Party("Indian National Congress", "INC", 2L, "en",
                "DOC-80857458");
        party1.persist();
        party2.persist();

        Assert.assertEquals(true, Party.findAll().size() > 0);
    }
}
