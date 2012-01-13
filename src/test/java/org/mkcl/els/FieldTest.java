/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.FieldTest.java
 * Created On: Dec 29, 2011
 */
package org.mkcl.els;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.Field;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class FieldTest.
 *
 * @author sujitas
 * @since v1.0.0
 */
public class FieldTest extends AbstractTest {

    /**
     * Test persist.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testPersist() {
        final Field field = new Field("testField", "testing purpose field",
                "MANDATORY", "VISIBLE", 10, "", "MIS.PERSONAL", 0L, "en");
        field.persist();
        Assert.assertNotNull("Saved Data ", field);
    }

    /**
     * Test find all.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional(readOnly = true)
    public void testFindAll() {
        final List<Field> lstField = Field.findAll();
        Assert.assertNotNull(lstField);
    }

    /**
     * Test find by id.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testFindById() {
        final Field fieldPersist = new Field("testField", "testing purpose field",
                "MANDATORY", "VISIBLE", 10, "", "MIS.PERSONAL", 0L, "en");
        fieldPersist.persist();
        final Field field = Field.findById(fieldPersist.getId());
        Assert.assertEquals("testField", field.getName());
    }


    /**
     * Test find by name and form.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testFindByNameAndForm() {
        final Field fieldPersist = new Field("testField", "testing purpose field",
                "MANDATORY", "VISIBLE", 10, "", "MIS.PERSONAL", 0L, "en");
        fieldPersist.persist();
        final Field field = Field.findByNameAndForm(fieldPersist.getName(), fieldPersist.getForm());
        Assert.assertEquals("testField", field.getName());
    }

    /**
     * Test update field.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testUpdateField() {
        final Field fieldPersist = new Field("testField", "testing purpose field",
                "MANDATORY", "VISIBLE", 10, "", "MIS.PERSONAL", 0L, "en");
        fieldPersist.persist();
        final Field field = Field.findById(fieldPersist.getId());
        field.setHint("Testing Hint....");
        field.update();
        Assert.assertEquals("Testing Hint....", field.getHint());
    }

    /**
     * Test remove field.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testRemoveField() {
        final Field fieldPersist = new Field("testField", "testing purpose field",
                "MANDATORY", "VISIBLE", 10, "", "MIS.PERSONAL", 0L, "en");
        fieldPersist.persist();
        final Field field = Field.findById(fieldPersist.getId());
        field.remove();
        Assert.assertEquals(true, field.getId() > 0);
    }

}
