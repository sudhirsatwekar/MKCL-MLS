/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.service.impl.PartyServiceImpl.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els.service.impl;

import org.mkcl.els.domain.Party;
import org.mkcl.els.service.IPartyService;
import org.springframework.stereotype.Service;

/**
 * The Class PartyServiceImpl.
 *
 * @author amitd
 * @version v1.0.0
 */
@Service
public class PartyServiceImpl extends GenericServiceImpl<Party, Long> implements
        IPartyService {

    /** The party repository. */
    // private PartyRepository partyRepository;

    /**
     * Sets the party repository.
     *
     * @param partyRepository the new party repository
     */
    /*
     * @Autowired public void setPartyRepository(PartyRepository
     * partyRepository) { this.dao = partyRepository; this.partyRepository =
     * partyRepository; }
     */

    /*
     * @Override public Party findByName(String name) { return
     * this.partyRepository.findByName(name); }
     */

    /*
     * @Override public List<Party> findAllSorted() { return
     * partyRepository.findAllSorted(); }
     */

    /*
     * @Override public List<MasterVO> findAllSortedVO(String locale) { return
     * partyRepository.findAllSortedVO(locale); }
     */
}
