/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.DocumentTest.java
 * Created On: Jan 5, 2012
 */
package org.mkcl.els;

import java.util.Date;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.Document;
import org.springframework.transaction.annotation.Transactional;



/**
 * The Class DocumentTest.
 *
 * @author sujitas
 * @since v1.0.0
 */
public class DocumentTest extends AbstractTest {

    /**
     * Test persist.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testPersist() {
        final Document document = new Document("DOC" + String.valueOf(UUID.randomUUID().hashCode()),
                "application/octet-stream",
                "abc.txt" , new Date() , "document upload test String".getBytes() , 30);

        document.persist();
        Assert.assertNotNull("Saved Data ", document);
    }

    /**
     * Test remove.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testRemove() {
        final Document document = new Document("DOC" + (UUID.randomUUID().hashCode()),
                "application/octet-stream",
                "abc.txt" , new Date() , "document upload test String".getBytes() , 30);

        document.persist();
        final Document doc = Document.findByTag(document.getTag());
        doc.remove();
        Assert.assertEquals(null, Document.findByTag(document.getTag()));
    }

    /**
     * Test remove.
     *
     * @author sujitas
     * @since v1.0.0
     */
    @Test
    @Transactional
    public void testFindByTag() {
        final Document document = new Document("DOC" + (UUID.randomUUID().hashCode()),
                "application/octet-stream",
                "abc.txt" , new Date() , "document upload test String".getBytes() , 30);

        document.persist();
        final Document doc = Document.findByTag(document.getTag());

        Assert.assertEquals(true, doc.getId() > 0);
    }

}
