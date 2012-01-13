/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.MenuItemServiceTest.java
 * Created On: Jan 9, 2012
 */
package org.mkcl.els;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.mkcl.els.domain.MenuItem;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class UserTest.
 *
 * @author vishals
 * @version v1.0.0
 */
public class MenuItemServiceTest extends AbstractTest {


    /**
     * Test persist.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testPersist() {
        MenuItem menu = new MenuItem("mnu_admin_masters_msgresources",
                "Message Resources", "messages/list", " ", 0);
        menu.persist();
        Assert.assertNotNull("Data inserted into table", menu);
    }


    /**
     * Test update.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testUpdate() {
        MenuItem menu = new MenuItem("mnu_admin_masters_msgresources",
                "Message Resources", "messages/list", " ", 0);
        menu.persist();
        MenuItem menu1 = MenuItem.findById(menu.getId());
        menu1.setText("Menu Updated");
        Assert.assertNotNull("Data Updated", menu1);
    }


    /**
     * Test remove.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testRemove() {
        MenuItem menu = new MenuItem("mnu_admin_masters_msgresources",
                "Message Resources", "messages/list", " ", 0);
        menu.persist();
        MenuItem item = MenuItem.findById(menu.getId());
        item.remove();
        Assert.assertNotNull(item);
    }


    /**
     * Test find by id.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindById() {
        MenuItem menu = new MenuItem("mnu_admin_masters_msgresources",
                "Message Resources", "messages/list", " ", 0);
        menu.persist();
        MenuItem item = MenuItem.findById(menu.getId());
        Assert.assertEquals("Message Resources", item.getText());
    }


    /**
     * Test find by text key.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindByTextKey() {
        MenuItem menu = new MenuItem("mnu_admin_masters_msgresources",
                "Message Resources", "messages/list", " ", 0);
        menu.persist();
        MenuItem item = MenuItem.findByTextKey(menu.getTextKey(), menu.getLocale());
        Assert.assertEquals("Message Resources", item.getText());
    }


    /**
     * Test find all.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    @Transactional
    public final void testFindAll() {
        MenuItem menu = new MenuItem("mnu_admin_masters_msgresources",
                "Message Resources", "messages/list", " ", 0);
        MenuItem menu1 = new MenuItem("mnu_admin_masters_menus",
                "Menu", "menu/list", " ", 0);
        menu.persist();
        menu1.persist();
        List<MenuItem> menuItem = MenuItem.findAll();
        Assert.assertEquals(true, menuItem.size() > 0);
    }

    /**
     * Test menu xml.
     *
     * @author meenalw
     * @since v1.0.0
     */
    @Test
    public final void testMenuXML() {
        String xml =  MenuItem.getMenuXml();
        Assert.assertNotNull(xml);
    }

}
