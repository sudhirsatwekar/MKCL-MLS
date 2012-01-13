/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.vo.GridData.java
 * Created On: Jan 6, 2012
 */
package org.mkcl.els.common.vo;

import java.util.List;
import java.util.Map;

/**
 * The Class GridVO.
 *
 * @author vishals
 * @version v1.0.0
 */
public class GridData {

    /** The page. */
    private int page;

    /** The total. */
    private int total;

    /** The records. */
    private long records;

    /** The rows. */
    private List<Map<String, Object>> rows;

    /**
     * Instantiates a new grid data.
     */
    public GridData() {
    }

    /**
     * Instantiates a new grid vo.
     *
     * @param page the page
     * @param total the total
     * @param records the records
     * @param rows the rows
     */
    public GridData(final int page,
            final int total,
            final long records,
            final List<Map<String, Object>> rows) {
        super();
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    /**
     * Gets the page.
     *
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the page.
     *
     * @param page the new page
     */
    public void setPage(final int page) {
        this.page = page;
    }

    /**
     * Gets the total.
     *
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the total.
     *
     * @param total the new total
     */
    public void setTotal(final int total) {
        this.total = total;
    }

    /**
     * Gets the records.
     *
     * @return the records
     */
    public long getRecords() {
        return records;
    }

    /**
     * Sets the records.
     *
     * @param records the new records
     */
    public void setRecords(final long records) {
        this.records = records;
    }

    /**
     * Gets the rows.
     *
     * @return the rows
     */
    public List<Map<String, Object>> getRows() {
        return rows;
    }

    /**
     * Sets the rows.
     *
     * @param rows the rows
     * @author nileshp
     * @since v1.0.0
     * Sets the rows.
     */
    public void setRows(final List<Map<String, Object>> rows) {
        this.rows = rows;
    }

}
