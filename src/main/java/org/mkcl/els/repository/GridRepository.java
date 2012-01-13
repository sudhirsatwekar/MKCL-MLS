/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.GridRepository.java
 * Created On: Jan 6, 2012
 */

package org.mkcl.els.repository;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.Query;

import org.mkcl.els.common.vo.GridData;
import org.mkcl.els.domain.Grid;
import org.springframework.stereotype.Repository;

/**
 * The Class GridRepository.
 *
 * @author vishals
 * @version v1.0.0
 */
@Repository
public class GridRepository extends BaseRepository<Grid, Long> {

    /** The Constant DEFAULT_LOCALE. */
    private static final String DEFAULT_LOCALE = "en";


    /**
     * Gets the data.
     *
     * @param gridId the grid id
     * @param limit the limit
     * @param page the page
     * @param sidx the sidx
     * @param order the order
     * @return the data
     */
    public GridData getData(final Long gridId,
                            final Integer limit,
                            Integer page,
                            final String sidx,
                            final String order) {
       /*return getData(gridId, limit, page, sidx, order,
               new Locale(CustomParameter.findByName("DEFAULT_LOCALE").getValue()));*/


        Grid grid = this.find(gridId);

        String countSelect = grid.getCountQuery() + " ORDER BY " + sidx + " "
                + order;
        Query countQuery = this.em().createQuery(countSelect);
        Long count = (Long) countQuery.getSingleResult();

        Integer total_pages = 0;
        if (count > 0) {
            total_pages = (int) Math.ceil((float) count / limit);
        }

        if (page > total_pages) {
            page = total_pages;
        }

        int start = (limit * page - limit);
        if (start < 0) {
            start = 0;
        }

        String select = grid.getQuery() + " ORDER BY " + sidx + " " + order;
        Query query = this.em().createQuery(select);
        query.setFirstResult(start);
        query.setMaxResults(limit);
        List<Map<String, Object>> records = query.getResultList();

        GridData gridVO = new GridData(page, limit, count, records);
        return gridVO;
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
    @SuppressWarnings("unchecked")
    public GridData getData(final Long gridId,
                            final Integer limit,
                            Integer page,
                            final String sidx,
                            final String order,
                            final Locale locale) {

        //Grid grid = this.find(gridId);
        Grid grid = Grid.findById(gridId);
        String countSelect = null;
        Query countQuery = null;
        String select = null;
        Query query = null;

        if (!sidx.contains(".")) {
            countSelect = grid.getCountQuery() + " ORDER BY m." + sidx + " "
                    + order;
            select = grid.getQuery() + " ORDER BY m." + sidx + " " + order;
        } else {
            countSelect = grid.getCountQuery() + " ORDER BY " + sidx + " "
                    + order;
            select = grid.getQuery() + " ORDER BY " + sidx + " " + order;
        }
        countQuery = this.em().createQuery(countSelect);
        if (grid.isNativeQuery()) {
            query = this.em().createNativeQuery(select);

        } else {
            query = this.em().createQuery(select);
        }
        if (countSelect.contains("=:locale")) {
            if (grid.getLocalized()) {
                countQuery.setParameter("locale", locale.toString());
            } else {
                countQuery.setParameter("locale", DEFAULT_LOCALE);
            }
        }

        if (select.contains("=:locale")) {
            if (grid.getLocalized()) {
                query.setParameter("locale", locale.toString());
            } else {
                query.setParameter("locale", DEFAULT_LOCALE);
            }
        }

        Long count = (Long) countQuery.getSingleResult();
        Integer totalPages = 0;
        if (count > 0) {
            totalPages = (int) Math.ceil((float) count / limit);
        }
        if (page > totalPages) {
            page = totalPages;
        }
        int start = (limit * page - limit);
        if (start < 0) {
            start = 0;
        }
        query.setFirstResult(start);
        query.setMaxResults((int) (count > limit ? count : limit));

        List<Map<String, Object>> records = query.getResultList();
        GridData gridVO = new GridData(page, totalPages, count, records);
        return gridVO;
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
    @SuppressWarnings("unchecked")
    public GridData getData(final Long gridId,
                            final Integer limit,
                            Integer page,
                            final String sidx,
                            final String order,
                            final String filterSql,
                            final Locale locale) {
        //Grid grid = this.find(gridId);
        Grid grid = Grid.findById(gridId);

        String countSelect = null;
        if (!sidx.contains(".")) {
            countSelect = grid.getCountQuery() + filterSql + " ORDER BY m."
                    + sidx + " " + order;
        } else {
            countSelect = grid.getCountQuery() + filterSql + " ORDER BY "
                    + sidx + " " + order;
        }
        Query countQuery = this.em().createQuery(countSelect);
        if (grid.getLocalized()) {
            countQuery.setParameter("locale", locale.toString());
        }
        Long count = (Long) countQuery.getSingleResult();

        Integer totalPages = 0;
        if (count > 0) {
            totalPages = (int) Math.ceil((float) count / limit);
        }

        if (page > totalPages) {
            page = totalPages;
        }

        int start = (limit * page - limit);
        if (start < 0) {
            start = 0;
        }

        String select = null;
        if (!sidx.contains(".")) {
            select = grid.getQuery() + filterSql + " ORDER BY m." + sidx + " "
                    + order;
        } else {
            select = grid.getQuery() + filterSql + " ORDER BY " + sidx + " "
                    + order;
        }
        Query query = this.em().createQuery(select);
        if (grid.getLocalized()) {
            query.setParameter("locale", locale.toString());
        }
        query.setFirstResult(start);
        query.setMaxResults(limit);
        List<Map<String, Object>> records = query.getResultList();
        GridData gridVO = new GridData(page, totalPages, count, records);
        return gridVO;
    }

}
