/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.MessageResourceTest.java
 * Created On: Dec 27, 2011
 */
package org.mkcl.els;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.MessageResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class MessageResourceTest.
 *
 * @author sujitas
 * @since v1.0.0
 */
public class MessageResourceTest extends AbstractTest {

    /**
     * Test persist.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testPersist() {
        final MessageResource messageResource = new MessageResource("en",
                "test.messageResource.one", "Testing purpose one",
                "Testing purpose message resource string one");
        messageResource.persist();
        Assert.assertNotNull("Saved Data ", messageResource);
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
        final List<MessageResource> lstMessageResources = MessageResource
                .findAll();
        Assert.assertNotNull(lstMessageResources);
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
        final MessageResource messageResourcePersist = new MessageResource(
                "en", "test.messageResource.two", "Testing purpose two",
                "Testing purpose message resource string two");
        messageResourcePersist.persist();
        final MessageResource messageResource = MessageResource
                .findById(messageResourcePersist.getId());
        Assert.assertEquals("Testing purpose two", messageResource.getValue());
    }

    /**
     * Test find by locale and code.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testFindByLocaleAndCode() {
        final MessageResource messageResourcePersist = new MessageResource(
                "en", "test.messageResource.three", "Testing purpose three",
                "Testing purpose message resource string three");
        messageResourcePersist.persist();
        final MessageResource messageResource = MessageResource
                .findByLocaleAndCode("en", "test.messageResource.three");
        Assert.assertEquals("Testing purpose three", messageResource.getValue());
    }

    /**
     * Test update message resource.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testUpdateMessageResource() {
        final MessageResource messageResourcePersist = new MessageResource(
                "en", "test.messageResource.for", "Testing purpose four",
                "Testing purpose message resource string four");
        messageResourcePersist.persist();
        final MessageResource messageResource = MessageResource
                .findById(messageResourcePersist.getId());
        messageResource.setCode("test.messageResource.four");
        messageResource.update();
        Assert.assertEquals("Testing purpose four", messageResource.getValue());
    }

    /**
     * Test remove message resource.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testRemoveMessageResource() {
        final MessageResource messageResourcePersist = new MessageResource(
                "en", "test.messageResource.five", "Testing purpose five",
                "Testing purpose message resource string five");
        messageResourcePersist.persist();
        final MessageResource messageResource = MessageResource
                .findById(messageResourcePersist.getId());
        messageResource.remove();
        Assert.assertEquals(true, messageResource.getId() > 0);
    }

}
