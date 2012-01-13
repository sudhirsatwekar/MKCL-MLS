/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.AssemblyTermRepository.java
 * Created On: Dec 21, 2011
 */
package org.mkcl.els.repository;

import org.mkcl.els.domain.AssemblyTerm;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class AssemblyTermRepository.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Repository
public class AssemblyTermRepository extends BaseRepository<AssemblyTerm, Long> {

    /**
     * Find by assembly term.
     *
     * @param term the term
     * @return the assembly term
     * @author samiksham
     * @since v1.0.0
     */
    public AssemblyTerm findByAssemblyTerm(final Integer term) {
        Search search = new Search();
        search.addFilterEqual("term", term);
        AssemblyTerm assemblyTerm = this.searchUnique(search);
        return assemblyTerm;
    }

}
