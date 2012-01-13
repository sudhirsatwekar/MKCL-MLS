/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.FieldRepository.java
 * Created On: Dec 28, 2011
 */
package org.mkcl.els.repository;

import java.util.List;

import org.mkcl.els.domain.Field;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class FieldRepository.
 *
 * @author sujitas
 * @since v1.0.0
 */
@Repository
public class FieldRepository extends BaseRepository<Field, Long> {

    /**
     * Find by form name sorted.
     *
     * @param formName the form name
     * @return the list
     * @author sujitas
     * @since v1.0.0
     */
    public List<Field> findByFormNameSorted(final String formName) {
        final Search search = new Search();
        search.addFilterEqual("form", formName);
        search.addSort("position", true);
        return this.search(search);
    }

    /**
     * Find by name and form.
     *
     * @param name the name
     * @param form the form
     * @return the field
     * @author sujitas
     * @since v1.0.0
     */
    public Field findByNameAndForm(final String name, final String form) {
        final Search search = new Search();
        search.addFilterEqual("name", name);
        search.addFilterEqual("form", form);
        return this.searchUnique(search);
    }
}
