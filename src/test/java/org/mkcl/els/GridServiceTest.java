/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.GridServiceTest.java
 * Created On: Jan 10, 2012
 */

package org.mkcl.els;

import org.junit.Assert;
import org.junit.Test;
import org.mkcl.els.domain.Grid;
import org.springframework.transaction.annotation.Transactional;


/**
 * The Class GridServiceTest.
 *
 * @author sandeeps
 * @version v1.0.0
 */
public class GridServiceTest extends AbstractTest {


     /*@Transactional
     @Test
     public void testGetData() {
     int expectedResult = 2;
     GridData vo = new Grid().getData(new Long(1), new Integer(2),
     new Integer(1), "id", "asc" , new Locale("en"));
     Assert.assertEquals(expectedResult, vo.getTotal());
     }*/

    /**
     * Test persist.
     *
     * @author nileshp
     * @since v1.0.0
     * Test persist.
     */
    @Transactional
    @Test
    public final void testPersist() {

        Grid grid = new Grid(
                "TEST GRID",
                "TEST GRID",
                "Id , Locale",
                "{name:'id', index:'id', width:'10'}, {name:'locale', index:'locale', width:'30'}",
                10, "m.id", "desc", "", "", 100, 100, "districts", false, 0L,
                false, false);
        grid.persist();
        Assert.assertNotNull("Saved Grid Data ", grid);
    }

    /**
     * Test find by name.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find by name.
     */
    @Transactional
    @Test
    public final void testFindByName() {

        Grid grid = new Grid(
                "TEST GRID",
                "TEST GRID",
                "Id , Locale",
                "{name:'id', index:'id', width:'10'}, {name:'locale', index:'locale', width:'30'}",
                10, "m.id", "desc", "", "", 100, 100, "districts", false, 0L,
                false, false);
        grid = grid.persist();
        Grid grid1 = Grid.findByName(grid.getName());
        Assert.assertNotNull("Find Grid Data ", grid1);

    }

    /**
     * Test find by id.
     *
     * @author nileshp
     * @since v1.0.0
     * Test find by id.
     */
    @Transactional
    @Test
    public final void testFindById() {

        Grid grid = new Grid(
                "TEST GRID",
                "TEST GRID",
                "Id , Locale",
                "{name:'id', index:'id', width:'10'}, {name:'locale', index:'locale', width:'30'}",
                10, "m.id", "desc", "", "", 100, 100, "districts", false, 0L,
                false, false);
        grid = grid.persist();
        Grid grid1 = Grid.findById(grid.getId());
        Assert.assertNotNull("Find Grid Data ", grid1);

    }

    /**
     * Test update.
     *
     * @author nileshp
     * @since v1.0.0
     * Test update.
     */
    @Transactional
    @Test
    public final void testUpdate() {

        Grid grid = new Grid(
                "TEST GRID",
                "TEST GRID",
                "Id , Locale",
                "{name:'id', index:'id', width:'10'}, {name:'locale', index:'locale', width:'30'}",
                10, "m.id", "desc", "", "", 100, 100, "districts", false, 0L,
                false, false);
        grid = grid.persist();
        grid.setName("Test Grid one");
        Grid grid1 = grid.update();
        Assert.assertNotNull("Update Grid Data ", grid1);
    }

    /**
     * Test remove.
     *
     * @author nileshp
     * @since v1.0.0
     * Test remove.
     */
    @Transactional
    @Test
    public final void testRemove() {

        Grid grid = new Grid(
                "TEST GRID",
                "TEST GRID",
                "Id , Locale",
                "{name:'id', index:'id', width:'10'}, {name:'locale', index:'locale', width:'30'}",
                10, "m.id", "desc", "", "", 100, 100, "districts", false, 0L,
                false, false);
        grid.remove();
        Assert.assertNotNull("Removed Grid Data ", grid);
    }

   /* @Transactional
    @Test
    public final void testGetData() {

        Grid grid = new Grid(
                "TEST GRID",
                "TEST GRID",
                "['Id','Tehsil']",
                "[{name:'id',index:'id',width:'10'},{name:'name',index:'name',width:'50'}]",
                10, "m.id", "desc",
                "SELECT m FROM Title  m   WHERE m.locale=:locale",
                "SELECT COUNT(m) FROM Title m WHERE m.locale=:locale", 100,
                100, "districts", false, 0L, false, false);
        grid = grid.persist();

        GridData gridData = grid.getData(grid.getId(), 10, 1, "m.id", "asc");
        Assert.assertNotNull("Find Grid Data ", gridData);

    }*/
}
