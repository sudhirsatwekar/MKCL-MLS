/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.TitleTest.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mkcl.els.domain.Title;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class TitleTest.
 *
 * @author samiksham
 */
public class TitleTest extends AbstractTest {

    /**
     * Test persist.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    @Test
    public final void testPersist() {
        final Title title = new Title("Mr", "en", 0L);
        title.persist();
    }

    /**
     * Test update.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    @Test
    public final void testUpdate() {
        final Title titlePersist = new Title("Mr", "en", 0L);
        titlePersist.persist();
        final Title title = Title.findById(titlePersist.getId());
        title.setName("Miss Test");
        Title updated = title.update();
        Assert.assertEquals("Miss Test", updated.getName());
    }

    /**
     * Test delete.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    @Test
    public final void testDelete() {
        final Title titlePersist = new Title("Mrs", "en", 0L);
        titlePersist.persist();
        final Title title = Title.findById(titlePersist.getId());
        title.remove();
        Assert.assertNull(Title.findById(titlePersist.getId()));
    }

    /**
     * Test findby id.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindbyId() {
        final Title titlePersist = new Title("Mrs", "en", 0L);
        titlePersist.persist();
        Title title = Title.findById(titlePersist.getId());
        Assert.assertEquals("Mrs", title.getName());
    }

    /**
     * Test findby name.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindbyName() {
        final Title titlePersist = new Title("Mrs", "en", 0L);
        titlePersist.persist();
        Title title = Title.findByName("Mrs");
        Assert.assertEquals("Mrs", title.getName());
    }

    /**
     * Test find all sorted.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Test
    public final void testFindAllSorted() {
        List<Title> lstTitles = Title.findAllSorted("name", "en", false);
        Assert.assertNotNull(lstTitles);
    }

    /**
     * Test find all.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Test
    public final void testFindAll() {
        List<Title> lstTitles = Title.findAll();
        Assert.assertNotNull(lstTitles);
    }

}
