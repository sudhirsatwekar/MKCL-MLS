/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.AssemblyStructureTest.java
 * Created On: Dec 28, 2011
 */
package org.mkcl.els;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.AssemblyStructure;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class AssemblyStructureTest.
 *
 * @author nileshp
 */
public class AssemblyStructureTest extends AbstractTest {

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
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure.persist();
        Assert.assertNotNull("Saved state Data ", assemblyStructure);
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
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStructure", "en");
        assemblyStructure.persist();
        AssemblyStructure assemblyStructure2 =
                AssemblyStructure.findByName("testAssemblyStructure");
        assemblyStructure2.remove();
        Assert.assertNotNull("removed assemblyStructure Data ", assemblyStructure2);
    }

    /**
     * Test update state.
     *
     * @author nileshp
     * @since v1.0.0
     * Test update state.
     */
    @Test
    @Transactional
    public final void testUpdate() {
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStructure", "en");
        assemblyStructure = assemblyStructure.persist();
        assemblyStructure.setName("testAssemblyStructure2");
        assemblyStructure = assemblyStructure.update();
        Assert.assertNotNull("updated assemblyStructure Data ", assemblyStructure);
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
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStructure", "en");
        assemblyStructure = assemblyStructure.persist();
        AssemblyStructure assemblyStructure1 =
                AssemblyStructure.findByName(assemblyStructure.getName());
        Assert.assertNotNull(assemblyStructure1);
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
    public final void testFindById() {
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStructure", "en");
        assemblyStructure = assemblyStructure.persist();
        AssemblyStructure assemblyStructure1 =
                AssemblyStructure.findById(assemblyStructure.getId());
        Assert.assertNotNull(assemblyStructure1);
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
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStructure", "en");
        assemblyStructure = assemblyStructure.persist();
        List<AssemblyStructure> listAssemblyStructures =
                AssemblyStructure.findAllSorted("name", "en", false);
        Assert.assertNotNull(listAssemblyStructures);
    }

}
