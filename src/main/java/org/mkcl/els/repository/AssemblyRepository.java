/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.AssemblyRepository.java
 * Created On: Dec 30, 2011
 */

package org.mkcl.els.repository;

import org.mkcl.els.domain.Assembly;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class AssemblyRepository.
 *
 * @author amitd
 * @version v1.0.0
 */
@Repository
public class AssemblyRepository extends BaseRepository<Assembly, Long> {

    /**
     * Find by name.
     *
     * @param assembly the assembly
     * @return the assembly
     * @author nileshp
     * @since v1.0.0
     */

    public Assembly findByAssembly(final String assembly) {
        Search search = new Search();
        search.addFilterEqual("assembly", assembly);
        return this.searchUnique(search);

    }

    /**
     * Find current assembly.
     *
     * @param locale the locale
     * @return the assembly
     * @author nileshp
     * @since v1.0.0
     */
    public Assembly findCurrentAssembly(final String locale) {
        Search search = new Search();
        search.addFilterEqual("currentAssembly", true);
        search.addFilterEqual("locale", locale);
        return this.searchUnique(search);
    }

    /**
     * Update previous current assembly.
     *
     * @param locale the locale
     * @author nileshp
     * @since v1.0.0 Update previous current assembly.
     */
    public void updatePreviousCurrentAssembly(final String locale) {
        Search search = new Search();
        search.addFilterEqual("currentAssembly", true);
        search.addFilterEqual("locale", locale);
        Assembly assembly = this.searchUnique(search);
        if (!(assembly == null)) {
            assembly.setCurrentAssembly(false);
            this.merge(assembly);
        }
    }

}
