/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.AssemblyNumberRepository.java
 * Created On: Dec 27, 2011
 */

package org.mkcl.els.repository;

import org.mkcl.els.domain.AssemblyNumber;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class AssemblyNumberRepository.
 *
 * @author amitd
 * @version v1.0.0
 */
@Repository
public class AssemblyNumberRepository extends
        BaseRepository<AssemblyNumber, Long> {

    /**
     * Find by assembly number.
     *
     * @param assemblyNo the assembly no
     * @return the assembly number
     * @author nileshp
     * @since v1.0.0
     */
    public AssemblyNumber findByAssemblyNo(final String assemblyNo) {
        Search search = new Search();
        search.addFilterEqual("assemblyNo", assemblyNo);
        AssemblyNumber assemblyNumber = this.searchUnique(search);
        return assemblyNumber;
    }

}
