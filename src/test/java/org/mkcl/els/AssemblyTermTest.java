/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.AssemblyTermTest.java
 * Created On: Dec 27, 2011
 */
package org.mkcl.els;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mkcl.els.domain.AssemblyTerm;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AssemblyTermTest.
 *
 * @author samiksham
 */
public class AssemblyTermTest extends AbstractTest {

    /**
     * Testpersist.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    @Test
    public void testpersist() {
        AssemblyTerm assemblyTerm = new AssemblyTerm(3, 0L, "en");
        assemblyTerm.persist();
        Assert.assertNotNull("AssemblyTerm should not null", assemblyTerm);
    }

    /**
     * Testupdate.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    @Test
    public void testupdate() {
        AssemblyTerm assemblyTerm = new AssemblyTerm(3, 0L, "en");
        assemblyTerm.persist();
        AssemblyTerm assembly = AssemblyTerm.findById(assemblyTerm.getId());
        assembly.setTerm(23);
        assembly.update();
        Assert.assertNotNull("AssemblyTerm should not null", assembly);

    }

    /**
     * Testremove.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Transactional
    @Test
    public void testremove() {
        AssemblyTerm assemblyTerm = new AssemblyTerm(3, 0L, "en");
        assemblyTerm.persist();
        AssemblyTerm assembly = AssemblyTerm.findById(assemblyTerm.getId());
        assembly.remove();
    }

    /**
     * Testfind by id.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testfindById() {
        AssemblyTerm assemblyTerm = new AssemblyTerm(4, 0L, "en");
        assemblyTerm.persist();
        AssemblyTerm term = AssemblyTerm.findById(assemblyTerm.getId());
        Assert.assertNotNull(term);
    }

    /**
     * Testfind by assembly term.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Test
    public void testfindByAssemblyTerm() {
        AssemblyTerm term = new AssemblyTerm(2, 0L, "en");
        term.persist();
        AssemblyTerm assembly = AssemblyTerm.findByAssemblyTerm(term.getTerm());
        Assert.assertNotNull(assembly);
    }

    /**
     * Testfind all.
     *
     * @author samiksham
     * @since v1.0.0
     */
    @Test
    public void testfindAll() {
        AssemblyTerm term = new AssemblyTerm(2, 0L, "en");
        term.persist();
        AssemblyTerm term1 = new AssemblyTerm(3, 0L, "en");
        term1.persist();
        List<AssemblyTerm> list = AssemblyTerm.findAll();
        Assert.assertEquals(true, list.size() > 0);
    }
}
