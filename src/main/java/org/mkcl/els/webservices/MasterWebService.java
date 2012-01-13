/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.webservices.MasterWebService.java
 * Created On: Jan 4, 2012
 */
package org.mkcl.els.webservices;

import java.util.List;

import org.mkcl.els.common.vo.MasterVO;
import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.domain.Party;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class MasterWebService.
 *
 * @author meenalw
 * @since v1.0.0
 */
@Controller
@RequestMapping("/ws/masters")
public class MasterWebService {

    /**
     * Gets the constituencies.
     *
     * @param locale the locale
     * @return the constituencies
     */
    @RequestMapping(value = "/constituencies/{locale}")
    public @ResponseBody
    List<MasterVO> getConstituencies(@PathVariable final String locale) {
        return Constituency.findAllSortedVO(locale);
    }

    /**
     * Gets the parties.
     *
     * @param locale the locale
     * @return the parties
     */
    @RequestMapping(value = "/parties/{locale}")
    public @ResponseBody
    List<MasterVO> getParties(@PathVariable final String locale) {
        return Party.findAllSortedVO(locale);
    }

    /**
     * Gets the no of terms.
     *
     * @param locale the locale
     * @return the no of terms
     */
    @RequestMapping(value = "/terms/{locale}")
    public @ResponseBody
    Integer getNoOfTerms(@PathVariable final String locale) {
        return MemberDetails.maxNoOfTerms(locale);
    }
}
