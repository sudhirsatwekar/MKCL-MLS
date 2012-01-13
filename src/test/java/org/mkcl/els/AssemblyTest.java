/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.AssemblyTest.java
 * Created On: Jan 5, 2012
 */
package org.mkcl.els;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.Assembly;
import org.mkcl.els.domain.AssemblyStructure;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AssemblyTest.
 *
 * @author nileshp
 * @since v1.0.0
 */
public class AssemblyTest extends AbstractTest {

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
        assemblyStructure = assemblyStructure.persist();
        Assembly assembly = new Assembly(assemblyStructure, "testAssembly", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                false, 0L, "en", 1L);
        assembly.persist();
        Assert.assertNotNull("Saved assembly Data ", assembly);
    }

    /**
     * Test find by assembly.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find by assembly.
     */
    @Test
    @Transactional
    public final void testFindByAssembly() {
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure = assemblyStructure.persist();
        Assembly assembly = new Assembly(assemblyStructure, "testAssembly1", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                true, 0L, "en", 1L);
        assembly.persist();
        Assembly assembly2 = Assembly.findByAssembly("testAssembly1");
        Assert.assertNotNull(assembly2);
    }

    /**
     * Test find current assembly.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find current assembly.
     */
    @Test
    @Transactional
    public final void testFindCurrentAssembly() {
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure = assemblyStructure.persist();

        Assembly assembly = new Assembly(assemblyStructure, "testAssembly1", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                true, 0L, "en", 1L);
        assembly.persist();
        Assembly assembly2 = Assembly.findCurrentAssembly("en");
        Assert.assertNotNull(assembly2);
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
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure = assemblyStructure.persist();

        Assembly assembly = new Assembly(assemblyStructure, "testAssembly1", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                true, 0L, "en", 1L);
        assembly.persist();
        List<Assembly> assemblyList = Assembly.findAllSorted("assembly", "en", false);
        Assert.assertNotNull("assembly List is :- ", assemblyList);
    }

    /**
     * Test update previous current assembly.
     *
     * @author nileshp
     * @since v1.0.0
     * Test update previous current assembly.
     */
    @Test
    @Transactional
    public final void testUpdatePreviousCurrentAssembly() {
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure = assemblyStructure.persist();

        Assembly assembly = new Assembly(assemblyStructure, "testAssembly1", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                true, 0L, "en", 1L);
        assembly.persist();

        Assembly assembly1 = Assembly.findByAssembly("testAssembly1");
        assembly1.updatePreviousCurrentAssembly("en");
        Assert.assertNotNull("updated assembly data is :-  ", assembly1);
    }

    /**
     * Test find by id.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find by id.
     */
    @Test
    @Transactional
    public final void testFindByIdAndUpdateAndRemove() {
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure = assemblyStructure.persist();

        Assembly assembly = new Assembly(assemblyStructure, "testAssembly1", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                true, 0L, "en", 1L);
        assembly.persist();
        Assembly assembly4 = Assembly.findByAssembly("testAssembly1");
        Assembly assembly1 = Assembly.findById(assembly4.getId());
        Assert.assertNotNull("assembly data is :-  ", assembly1);
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
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure = assemblyStructure.persist();

        Assembly assembly = new Assembly(assemblyStructure, "testAssembly1", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                true, 0L, "en", 1L);
        assembly.persist();

        assembly.setAssembly("testAssembly2");
        Assembly assembly2 = assembly.update();
        Assert.assertNotNull("updated assembly data is :-  ", assembly2);
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
        AssemblyStructure assemblyStructure = new AssemblyStructure("testAssemblyStru", "en");
        assemblyStructure = assemblyStructure.persist();

        Assembly assembly = new Assembly(assemblyStructure, "testAssembly1", 11,
                "testTerm", true, false, false, false, "25/01/2012", "29/01/2012", "29/01/2012",
                true, 0L, "en", 1L);
        assembly.persist();
        Assert.assertNotNull("removed assembly data is :-  ", assembly);
    }


}
