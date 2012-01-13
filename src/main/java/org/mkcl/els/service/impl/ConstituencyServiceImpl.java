/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.service.impl.ConstituencyServiceImpl.java
 * Created On: Jan 4, 2012
 */
package org.mkcl.els.service.impl;

import org.mkcl.els.domain.Constituency;
import org.mkcl.els.service.IConstituencyService;
import org.springframework.stereotype.Service;

/**
 * The Class ConstituencyServiceImpl.
 *
 * @author sandeeps
 * @version v1.0.0
 */
@Service
public class ConstituencyServiceImpl extends
GenericServiceImpl<Constituency, Long> implements IConstituencyService {

    /** The Constituency repository. */

    //private ConstituencyRepository constituencyRepository;

    /*@Autowired
    public void setConstituencyRepository(final ConstituencyRepository constituencyRepository)

    {
        this.dao = constituencyRepository;
        this.constituencyRepository = constituencyRepository;
    }*/

    /* (non-Javadoc)
     * @see org.mkcl.els.service.IConstituencyService#findByName(java.lang.String)
     */
    /*@Override
    public Constituency findByName(final String name) {
         return constituencyRepository.findByName(name);
    }*/

    /*
     * @Override public List<Constituency>
     * findConstituenciesByDistrictName(final String name) { return
     * constituencyRepository.findConstituenciesByDistrictName(name); }
     */

    /*
     * @Override public List<Constituency> findConstituenciesByDistrictId(final
     * Long districtId) { return
     * constituencyRepository.findConstituenciesByDistrictId(districtId);
     *
     * }
     */

    /*
     * @Override public List<Reference> findConstituenciesStartingWith(final
     * String param) { return
     * constituencyRepository.findConstituenciesStartingWith(param); }
     */

    /*
     * @Override public List<Constituency> findAllSorted() { return
     * constituencyRepository.findAllSorted(); }
     */

    /*
     * @Override public List<MasterVO> findAllSortedVO(final String locale) {
     * return constituencyRepository.findAllSortedVO(locale); }
     */


}
