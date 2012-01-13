/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.vo.GridConfig.java
 * Created On: Jan 6, 2012
 */

package org.mkcl.els.common.vo;

/**
 * The Class GridConfigVO.
 *
 * @author vishals
 * @version v1.0.0
 */
public class GridConfig {

    /** The title. */
    private String title;

    /** The col names. */
    private String colNames;

    /** The col model. */
    private String colModel;

    /** The height. */
    private int height;

    /** The width. */
    private int width;

    /** The page size. */
    private int pageSize;

    /** The sort order. */
    private String sortOrder;

    /** The detail view. */
    private String detailView;

    /** The multi select. */
    private boolean multiSelect;

    /**
     * Instantiates a new grid config vo.
     */
    public GridConfig() {
    }

    /**
     * Instantiates a new grid config vo.
     *
     * @param title the title
     * @param colNames the col names
     * @param colModel the col model
     * @param height the height
     * @param width the width
     * @param pageSize the page size
     * @param sortOrder the sort order
     * @param detailView the detail view
     * @param multiSelect the multi select
     */
    public GridConfig(final String title,
            final String colNames,
            final String colModel,
            final int height,
            final int width,
            final int pageSize,
            final String sortOrder,
            final String detailView,
            final boolean multiSelect) {
        super();
        this.title = title;
        this.colNames = colNames;
        this.colModel = colModel;
        this.height = height;
        this.width = width;
        this.pageSize = pageSize;
        this.sortOrder = sortOrder;
        this.detailView = detailView;
        this.multiSelect = multiSelect;

    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Gets the col names.
     *
     * @return the col names
     */
    public String getColNames() {
        return colNames;
    }

    /**
     * Sets the col names.
     *
     * @param colNames the new col names
     */
    public void setColNames(final String colNames) {
        this.colNames = colNames;
    }

    /**
     * Gets the col model.
     *
     * @return the col model
     */
    public String getColModel() {
        return colModel;
    }

    /**
     * Sets the col model.
     *
     * @param colModel the new col model
     */
    public void setColModel(final String colModel) {
        this.colModel = colModel;
    }

    /**
     * Gets the height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height.
     *
     * @param height the new height
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Gets the width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width.
     *
     * @param width the new width
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Gets the page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize the new page size
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Gets the sort order.
     *
     * @return the sort order
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the sort order.
     *
     * @param sortOrder the new sort order
     */
    public void setSortOrder(final String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Gets the detail view.
     *
     * @return the detail view
     */
    public String getDetailView() {
        return detailView;
    }

    /**
     * Sets the detail view.
     *
     * @param detailView the new detail view
     */
    public void setDetailView(final String detailView) {
        this.detailView = detailView;
    }

    /**
     * Checks if is multi select.
     *
     * @return true, if is multi select
     */
    public boolean isMultiSelect() {
        return multiSelect;
    }

    /**
     * Sets the multi select.
     *
     * @param multiSelect the new multi select
     */
    public void setMultiSelect(final boolean multiSelect) {
        this.multiSelect = multiSelect;
    }
}
