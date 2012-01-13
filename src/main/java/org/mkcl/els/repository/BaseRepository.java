/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.BaseRepository.java
 * Created On: Dec 16, 2011
 */
package org.mkcl.els.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.trg.dao.jpa.GenericDAOImpl;
import com.trg.search.Search;
import com.trg.search.jpa.JPASearchProcessor;

/**
 * The Class BaseRepository.
 *
 * @param <T> the generic type
 * @param <ID> the generic type
 * @author vishals
 * @version 1.0.0
 */
public class BaseRepository<T, ID extends Serializable> extends
        GenericDAOImpl<T, ID> {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.trg.dao.jpa.JPABaseDAO#setEntityManager(javax.persistence.EntityManager
     * )
     */
    @Override
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        super.setEntityManager(entityManager);
        entityManager.setFlushMode(FlushModeType.AUTO);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.trg.dao.jpa.JPABaseDAO#setSearchProcessor(com.trg.search.jpa.
     * JPASearchProcessor)
     */
    @Override
    @Autowired
    public void setSearchProcessor(final JPASearchProcessor searchProcessor) {
        super.setSearchProcessor(searchProcessor);
    }

    /**
     * Find all records.
     *
     * @return the list
     * @author sujitas
     * @since v1.0.0
     */
    @Override
    public List<T> findAll() {
        final Search search = new Search();
        search.addSort("id", true);
        return this.search(search);
    }

    /**
     * Find all records sorted by id in descending order.
     *
     * @param locale the locale
     * @return the list
     * @author sujitas
     * @since v1.0.0
     */
    public List<T> findAll(final String locale) {
        return findAllSorted("id", locale, true);
    }


    /**
     * Find all records sorted.
     *
     * @param property the property
     * @param locale the locale
     * @param desc the desc
     * @return the list
     * @author sujitas
     * @since v1.0.0
     */
    public List<T> findAllSorted(final String property,
                                 final String locale,
                                 final boolean desc) {
        final Search search = new Search();
        search.addSort(property, desc);
        search.addFilterEqual("locale", locale);
        final List<T> records = this.search(search);
        return records;
    }


    /**
     * Find by name.
     *
     * @param name the name
     * @return the t
     * @author sujitas
     * @since v1.0.0
     */
    @SuppressWarnings("unchecked")
    public T findByName(final String name) {
        final Search search = new Search();
        search.addFilterEqual("name", name);
        final T t = (T) this.searchUnique(search);
        return t;
    }
}
