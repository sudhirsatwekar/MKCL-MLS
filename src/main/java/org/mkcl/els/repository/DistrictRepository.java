/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.repository.DistrictRepository.java
 * Created On: Dec 23, 2011
 */

package org.mkcl.els.repository;

import java.util.List;

import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.District;
import org.springframework.stereotype.Repository;

import com.trg.search.Search;

/**
 * The Class DistrictRepository.
 *
 * @author nileshp
 * @since v1.0.0
 */
@Repository
public class DistrictRepository extends BaseRepository<District, Long> {

    /** The Constant ASC. */
    private static final String ASC = "asc";

    /** The Constant DESC. */
    private static final String DESC = "desc";

    /**
     * Find by name.
     *
     * @param name the name
     * @return the district
     * @author nileshp
     * @since v1.0.0
     */
    @Override
    public District findByName(final String name) {
        Search search = new Search();
        search.addFilterEqual("name", name);
        District district = this.searchUnique(search);
        return district;
    }

    /**
     * Find districts by state id.
     *
     * @param stateId the state id
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    public List<District> findDistrictsByStateId(final Long stateId) {
        Search search = new Search();
        search.addFilterEqual("state.id", stateId + "");
        search.addSort("name", false);
        List<District> districts = this.search(search);
        return districts;

    }

    /**
     * Find districts by state id.
     *
     * @param stateId the state id
     * @param propertySortBy the property sort by
     * @param descOrder the desc order
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    public List<District> findDistrictsByStateId(final Long stateId,
                                                 final String propertySortBy,
                                                 final boolean descOrder) {
        Search search = new Search();
        search.addFilterEqual("state.id", stateId + "");
        search.addSort(propertySortBy, descOrder);
        List<District> districts = this.search(search);
        return districts;

    }

    /**
     * Find districts by state name.
     *
     * @param stateName the state name
     * @return the list
     * @author nileshp
     * @since v1.0.0
     */
    public List<District> findDistrictsByStateName(final String stateName) {
        Search search = new Search();
        search.addFilterEqual("state.name", stateName);
        search.addSort("name", false);
        List<District> districts = this.search(search);
        return districts;

    }

    /**
     * Find districts by constituency id.
     *
     * @param constituencyId the constituency id
     * @param propertySortBy the property sort by
     * @param descOrder the desc order
     * @return the sets the
     * @author nileshp
     * @since v1.0.0
     */
    public List<District> findDistrictsByConstituencyId(final Long constituencyId,
                                                        final String propertySortBy,
                                                        final boolean descOrder) {
        String order = null;
        if (descOrder) {
            order = DESC;
        } else {
            order = ASC;
        }
        String select = "SELECT c FROM Constituency c JOIN FETCH c.districts d "
                + "WHERE c.id = "
                + constituencyId
                + " ORDER BY d."
                + propertySortBy + " " + order;
        Constituency constituency;
        constituency = (Constituency) this.em().createQuery(select)
                .getSingleResult();
        return constituency.getDistricts();
    }

}
