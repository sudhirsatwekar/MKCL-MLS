/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.MessageResourceRepository.java
 * Created On: Dec 20, 2011
 */

package org.mkcl.els.repository;

import java.util.Locale;

import org.mkcl.els.domain.MessageResource;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class MessageResourceRepository.
 *
 * @author vishals
 * @since v1.0.0
 */
@Repository
public class MessageResourceRepository extends
        BaseRepository<MessageResource, Long> {

    /**
     * Find by code and locale.
     *
     * @param locale the locale
     * @return the message resource
     */
    public MessageResource findByLocale(final Locale locale) {
        final Search search = new Search();
        search.addFilterEqual("locale", locale.getCountry());
        MessageResource message = this.searchUnique(search);
        return message;
    }

    /**
     * Find by locale and code.
     *
     * @param locale the locale
     * @param code the code
     * @return the message resource
     */
    public MessageResource findByLocaleAndCode(final String locale,
                                               final String code) {
        Search search = new Search();
        search.addFilterEqual("locale", locale);
        search.addFilterEqual("code", code);
        return this.searchUnique(search);
    }
}

