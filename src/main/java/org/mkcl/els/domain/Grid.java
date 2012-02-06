/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.domain.Grid.java
 * Created On: Jan 6, 2012
 */

package org.mkcl.els.domain;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.mkcl.els.common.vo.GridConfig;
import org.mkcl.els.common.vo.GridData;
import org.mkcl.els.repository.GridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class Grid.
 *
 * @author vishals
 * @version v1.0.0
 */
@Configurable
@Entity
@Table(name = "grids")
public class Grid implements Serializable {

    // Attributes -------------------

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** The name. */
    @Column(length = 100)
    private String name;

    /** The title. */
    @Column(length = 100)
    private String title;

    /** The col names. */
    @Column(length = 4000)
    private String colNames;

    /** The col model. */
    @Column(length = 4000)
    private String colModel;

    /** The page size. */
    private int pageSize;

    /** The sort field. */
    @Column(length = 100)
    private String sortField;

    /** The sort order. */
    @Column(length = 10)
    private String sortOrder;

    /** The sql. */
    @Column(length = 4000)
    private String query;

    /** The sql. */
    @Column(length = 4000)
    private String countQuery;

    /** The width. */
    private int width;

    /** The height. */
    private int height;

    /** The detail view. */
    @Column(length = 200)
    private String detailView;

    /** The Localized. */
    private Boolean localized;
    
    /** The locale. */
    @Column(length = 200)
    private String locale;

    /** The version. */
    @Version
    private Long version;

    /** The multi select. */
    private boolean multiSelect = false;

    /** The native query. */
    private boolean nativeQuery = false;

    /** The grid repository. */
    @Autowired
    private transient GridRepository gridRepository;

    // Constructors
    /**
     * Instantiates a new grid.
     */
    public Grid() {
    }
// -------------- constructor ------------------------------
    /**
     * Instantiates a new grid.
     *
     * @param name the name
     * @param title the title
     * @param colNames the col names
     * @param colModel the col model
     * @param pageSize the page size
     * @param sortField the sort field
     * @param sortOrder the sort order
     * @param query the query
     * @param countQuery the count query
     * @param width the width
     * @param height the height
     * @param detailView the detail view
     * @param localized the localized
     * @param version the version
     * @param multiSelect the multi select
     * @param nativeQuery the native query
     */
    public Grid(final String name,
            final String title,
            final String colNames,
            final String colModel,
            final int pageSize,
            final String sortField,
            final String sortOrder,
            final String query,
            final String countQuery,
            final int width,
            final int height,
            final String detailView,
            final Boolean localized,
            final Long version,
            final boolean multiSelect,
            final boolean nativeQuery) {
        super();
        this.name = name;
        this.title = title;
        this.colNames = colNames;
        this.colModel = colModel;
        this.pageSize = pageSize;
        this.sortField = sortField;
        this.sortOrder = sortOrder;
        this.query = query;
        this.countQuery = countQuery;
        this.width = width;
        this.height = height;
        this.detailView = detailView;
        this.localized = localized;
        this.version = version;
        this.multiSelect = multiSelect;
        this.nativeQuery = nativeQuery;
    }

 // -------------- Domain Methods ------------------------------

      /**
      * Gets the grid repository.
      *
      * @return the grid repository
      */
      public static GridRepository getGridRepository() {
            GridRepository gridRepository = new Grid().gridRepository;
            if (gridRepository == null) {
                throw new IllegalStateException(
                        "Grid Repository has not been injected "
                                + "in Grid domain");
            }
            return gridRepository;
      }

      /**
       * Find by name.
       *
       * @param name the name
       * @param locale the locale
       * @return the grid
       * @author nileshp
       * @since v1.0.0
       */
      @Transactional(readOnly = true)
      public static Grid findByName(final String name, 
                                    final String locale) {
          return getGridRepository().findByName(name, locale);
      }
      
      /**
       * Find by name.
       *
       * @param name the name
       * @return the grid
       */
      @Transactional(readOnly = true)
      public static Grid findByName(final String name) {
          return getGridRepository().findByName(name, "en");
      }

      /**
       * Gets the data.
       *
       * @param gridId the grid id
       * @param rows the rows
       * @param page the page
       * @param sidx the sidx
       * @param order the order
       * @return the data
       */
      @Transactional
      public GridData getData(final Long gridId,
                              final Integer rows,
                              final Integer page,
                              final String sidx,
                              final String order) {
          return gridRepository.getData(gridId, rows, page, sidx, order);
      }

      /**
       * Gets the config.
       *
       * @param gridId the grid id
       * @return the config
       */
      @Transactional
      public GridConfig getConfig(final Long gridId) {
          Grid grid = Grid.findById(gridId);
          GridConfig gridConfig = new GridConfig(grid.getTitle(),
                  grid.getColNames(), grid.getColModel(), grid.getHeight(),
                  grid.getWidth(), grid.getPageSize(), grid.getSortOrder(),
                  grid.getDetailView(), grid.isMultiSelect());
          return gridConfig;
      }

      /**
       * Find by id.
       *
       * @param id the id
       * @return the grid
       * @author nileshp
       * @since v1.0.0
       */
      @Transactional(readOnly = true)
      public static Grid findById(final Long id) {
          return getGridRepository().find(id);
      }

      /**
       * Gets the data.
       *
       * @param gridId the grid id
       * @param limit the limit
       * @param page the page
       * @param sidx the sidx
       * @param order the order
       * @param locale the locale
       * @return the data
       */
      @Transactional
      public GridData getData(final Long gridId,
                              final Integer limit,
                              final Integer page,
                              final String sidx,
                              final String order,
                              final Locale locale) {
          return gridRepository.getData(gridId, limit, page, sidx, order, locale);
      }

      /**
       * Gets the data.
       *
       * @param gridId the grid id
       * @param limit the limit
       * @param page the page
       * @param sidx the sidx
       * @param order the order
       * @param filterSql the filter sql
       * @param locale the locale
       * @return the data
       */
      @Transactional
      public GridData getData(final Long gridId,
                              final Integer limit,
                              final Integer page,
                              final String sidx,
                              final String order,
                              final String filterSql,
                              final Locale locale) {
          return gridRepository.getData(
                  gridId, limit, page, sidx, order, filterSql, locale);
      }

      /**
       * Persist.
       *
       * @return the grid
       * @author nileshp
       * @since v1.0.0
       */
      @Transactional
      public Grid persist() {
          gridRepository.save(this);
          gridRepository.flush();
          return this;
      }

      /**
       * Update.
       *
       * @return the grid
       * @author nileshp
       * @since v1.0.0
       */
      @Transactional
      public Grid update() {
          gridRepository.merge(this);
          gridRepository.flush();
          return this;
      }

      /**
       * Removes the.
       *
       * @author nileshp
       * @since v1.0.0
       * Removes the.
       */
      @Transactional
      public void remove() {
          gridRepository.remove(this);
          gridRepository.flush();
      }
 // -------------- Getters & setters ------------------------------
    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(final String name) {
        this.name = name;
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
     * Gets the sort field.
     *
     * @return the sort field
     */
    public String getSortField() {
        return sortField;
    }

    /**
     * Sets the sort field.
     *
     * @param sortField the new sort field
     */
    public void setSortField(final String sortField) {
        this.sortField = sortField;
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
     * Gets the query.
     *
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the query.
     *
     * @param query the new query
     */
    public void setQuery(final String query) {
        this.query = query;
    }

    /**
     * Gets the count query.
     *
     * @return the count query
     */
    public String getCountQuery() {
        return countQuery;
    }

    /**
     * Sets the count query.
     *
     * @param countQuery the new count query
     */
    public void setCountQuery(final String countQuery) {
        this.countQuery = countQuery;
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
     * Gets the localized.
     *
     * @return the localized
     */
    public Boolean getLocalized() {
        return localized;
    }

    /**
     * Sets the localized.
     *
     * @param localized the new localized
     */
    public void setLocalized(final Boolean localized) {
        this.localized = localized;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public Long getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(final Long version) {
        this.version = version;
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

    /**
     * Checks if is native query.
     *
     * @return true, if is native query
     */
    public boolean isNativeQuery() {
        return nativeQuery;
    }

    /**
     * Sets the native query.
     *
     * @param nativeQuery the new native query
     */
    public void setNativeQuery(final boolean nativeQuery) {
        this.nativeQuery = nativeQuery;
    }

    
    
    public String getLocale() {
        return locale;
    }
    
    
    public void setLocale(String locale) {
        this.locale = locale;
    }
}
