/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.ConstituencyRepository.java
 * Created On: Jan 3, 2012
 */
package org.mkcl.els.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.mkcl.els.common.vo.MasterVO;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.Reference;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;


/**
 * The Class ConstituencyRepository.
 *
 * @author amitd
 * @version v1.0.0
 */
@Repository
public class ConstituencyRepository extends BaseRepository<Constituency, Long> {

    /**
     * List.
     *
     * @param name the name
     * @return the list< constituency>
     * @author meenalw
     * @since v1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<Constituency> findConstituenciesByDistrictName(final String name) {
        District district = District.findByName(name);
        String constituencyQuery = "SELECT c FROM Constituency c "
                + "WHERE :district MEMBER OF c.districts";
        Query query = this.em().createQuery(constituencyQuery);
        query.setParameter("district", district);
        return query.getResultList();
    }

    /**
     * List.
     *
     * @param districtId the district id
     * @return the list< constituency>
     * @author meenalw
     * @since v1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<Constituency> findConstituenciesByDistrictId(final Long districtId) {
        District district = District.findById(districtId);
        String constituencyQuery = "SELECT c FROM Constituency c "
                + "WHERE :district MEMBER OF c.districts";
        Query query = this.em().createQuery(constituencyQuery);
        query.setParameter("district", district);
        return query.getResultList();
    }

    /**
     * List.
     *
     * @param param the param
     * @return the list< reference>
     * @author meenalw
     * @since v1.0.0
     */
    public List<Reference> findConstituenciesStartingWith(final String param) {
        List<Reference> constituencies = new ArrayList<Reference>();
        Search search = new Search().addField("name").addField("name", "id")
                .addFilterILike("name", param + "%");
        search.setResultMode(Search.RESULT_MAP);
        constituencies = this.search(search);
        return constituencies;
    }

    /**
     * List.
     *
     * @param locale the locale
     * @return the list< master v o>
     * @author meenalw
     * @since v1.0.0
     */
    public List<MasterVO> findAllSortedVO(final String locale) {
        List<Constituency> constituencies = findAllSorted("name", locale, false);
        List<MasterVO> masterVO = new ArrayList<MasterVO>();
        for (Constituency c : constituencies) {
            MasterVO vo = new MasterVO();
            vo.setName(c.getName());
            masterVO.add(vo);
        }
        return masterVO;
    }

}
