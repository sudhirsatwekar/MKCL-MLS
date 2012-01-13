/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.TitleRepository.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els.repository;

import org.mkcl.els.domain.Title;
import org.springframework.stereotype.Repository;

/**
 * The Class TitleRepository.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Repository
public class TitleRepository extends BaseRepository<Title, Long> {

    /*public Title findByName(String name) {
        Search search = new Search();
        search.addFilterEqual("name", name);
        Title title = this.searchUnique(search);
        return title;
    }

    public List<Title> findAllSorted(){
        Search search=new Search();
        search.addSort("name",false);
        return this.search(search);
    }*/

}
