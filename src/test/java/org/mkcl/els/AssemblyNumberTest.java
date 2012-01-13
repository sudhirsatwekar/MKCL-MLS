/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.AssemblyNumberTest.java
 * Created On: Dec 28, 2011
 */
package org.mkcl.els;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.AssemblyNumber;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class AssemblyNumberTest.
 *
 * @author nileshp
 * @since v1.0.0
 */
public class AssemblyNumberTest extends AbstractTest {

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
        AssemblyNumber assemblyNumber = new AssemblyNumber("10", 0L, "en");
        assemblyNumber.persist();
        Assert.assertNotNull("Saved assemblyNumber Data ", assemblyNumber);
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
        AssemblyNumber assemblyNumber = new AssemblyNumber("11", 0L, "en");
        assemblyNumber.persist();
        AssemblyNumber assemblyNumber1 = AssemblyNumber.findByAssemblyNumber("11");
        assemblyNumber1.setAssemblyNo("12");
        assemblyNumber1.update();
        Assert.assertNotNull("Updated assemblyNumber Data ", assemblyNumber1);
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
        AssemblyNumber assemblyNumber = new AssemblyNumber("11", 0L, "en");
        assemblyNumber.persist();
        AssemblyNumber assemblyNumber1 = AssemblyNumber.findByAssemblyNumber("11");
        assemblyNumber1.remove();
        Assert.assertNotNull("Removed assemblyNumber Data ", assemblyNumber1);
    }

    /**
     * Test find by id.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find by id.
     */
    @Test
    public final void testFindById() {
        AssemblyNumber assemblyNumber = new AssemblyNumber("11", 0L, "en");
        assemblyNumber = assemblyNumber.persist();
        AssemblyNumber assemblyNumber1 = AssemblyNumber.findById(assemblyNumber.getId());
        Assert.assertNotNull("assemblyNumber is :- ", assemblyNumber1);
    }
}
