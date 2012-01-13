/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.PartyRepository.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els.repository;

import java.util.ArrayList;
import java.util.List;

import org.mkcl.els.common.vo.MasterVO;
import org.mkcl.els.domain.Party;
import org.springframework.stereotype.Repository;

/**
 * The Class PartyRepository.
 *
 * @author meenalw
 * @since v1.0.0
 */
@Repository
public class PartyRepository extends BaseRepository<Party, Long> {

    /**
     * Find all sorted vo.
     *
     * @param locale the locale
     * @return the list< master v o>
     * @author meenalw
     * @since v1.0.0
     */
    public List<MasterVO> findAllSortedVO(final String locale) {
        List<Party> parties = findAllSorted("name", locale, false);
        List<MasterVO> vos = new ArrayList<MasterVO>();
        for (Party i : parties) {
            MasterVO vo = new MasterVO();
            vo.setName(i.getName());
            vos.add(vo);
        }
        return vos;
    }
}
