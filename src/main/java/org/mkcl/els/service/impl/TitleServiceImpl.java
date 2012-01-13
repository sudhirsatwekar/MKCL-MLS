/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.service.impl.TitleServiceImpl.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els.service.impl;

import org.mkcl.els.domain.Title;
import org.mkcl.els.service.ITitleService;
import org.springframework.stereotype.Service;

/**
 * The Class TitleServiceImpl.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Service
public class TitleServiceImpl extends GenericServiceImpl<Title, Long>
implements ITitleService {

    /*private TitleRepository titleRepository;

    @Autowired
    public void setTitleRepository(TitleRepository titleRepository) {
    this.dao = titleRepository;
    this.titleRepository = titleRepository;
    }
    @Override
    public Title findByName(String name) {
    return titleRepository.findByName(name);
    }
    @Override
    public List<Title> findAllSorted() {
        return titleRepository.findAllSorted();
	}*/

}
