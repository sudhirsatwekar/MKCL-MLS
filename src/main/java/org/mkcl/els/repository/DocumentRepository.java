/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.DocumentRepository.java
 * Created On: Jan 5, 2012
 */
package org.mkcl.els.repository;

import org.mkcl.els.domain.Document;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class DocumentRepository.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Repository
public class DocumentRepository extends BaseRepository<Document, Long> {

    /**
     * Find by tag.
     *
     * @param tag the tag
     * @return the document
     * @author sujitas
     * @since v1.0.0
     */
    public Document findByTag(final String tag) {
        return this.searchUnique(new Search().addFilterEqual("tag", tag));
    }

}
