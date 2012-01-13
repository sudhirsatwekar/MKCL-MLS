/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.controller.ReferenceController.java
 * Created On: Jan 3, 2012
 */
package org.mkcl.els.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mkcl.els.domain.Constituency;
import org.mkcl.els.domain.District;
import org.mkcl.els.domain.Reference;
import org.mkcl.els.domain.Tehsil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The Class ReferenceController.
 *
 * @author nileshp
 * @since v1.0.0
 */
@Controller
@RequestMapping("/ref")
public class ReferenceController extends BaseController {

    /**
     * Gets the districts by state id.
     *
     * @param stateId the state id
     * @param map the map
     * @return the districts by state id
     */
    @RequestMapping(value = "/{state_id}/districts", method = RequestMethod.GET)
    public @ResponseBody
    List<District> getDistrictsByStateId(@PathVariable("state_id") final Long stateId,
                                         final ModelMap map) {
        return District.findDistrictsByStateId(stateId);
    }

    /**
     * Gets the constituencies by district id.
     *
     * @param districtId the district id
     * @param map the map
     * @return the constituencies by district id
     */
    @RequestMapping(value = "/{district_id}/constituencies",
            method = RequestMethod.GET)
    public @ResponseBody
    List<Constituency> getConstituenciesByDistrictId(@PathVariable("district_id")
                                                     final Long districtId,
                                                     final ModelMap map) {
        return Constituency.findConstituenciesByDistrictId(districtId);
    }

    /**
     * Gets the tehsils by district id.
     *
     * @param districtId the district id
     * @param map the map
     * @param request the request
     * @return the tehsils by district id
     */
    @RequestMapping(value = "/{district_id}/tehsils",
            method = RequestMethod.GET)
    public @ResponseBody
    List<Tehsil> getTehsilsByDistrictId(@PathVariable("district_id") final Long districtId,
                                        final ModelMap map,
                                        final HttpServletRequest request) {
        return Tehsil.findTehsilsByDistrictId(districtId);
    }

    /**
     * Gets the constituencies starting with.
     *
     * @param map the map
     * @param request the request
     * @return the constituencies starting with
     */
    @RequestMapping(value = "/constituencies", method = RequestMethod.GET)
    public String getConstituenciesStartingWith(final ModelMap map,
                                                final HttpServletRequest request) {
        List<Reference> constituencies = Constituency
                .findConstituenciesStartingWith(request.getParameter("q"));
        map.addAttribute("results", constituencies);
        return "constituencies";
    }

    /**
     * Gets the districts by constituency id.
     *
     * @param constituencyName the constituency name
     * @param map the map
     * @param request the request
     * @return the districts by constituency id
     */
    @RequestMapping(value = "/data/{constituency_name}/districts",
            method = RequestMethod.GET)
    public @ResponseBody
    List<District> getDistrictsByConstituencyId(@PathVariable("constituency_name")
                                                final String constituencyName,
                                                final ModelMap map,
                                                final HttpServletRequest request) {
        List<District> districts = District.findDistrictsByConstituencyId(
                Constituency.findByName(constituencyName).getId(), "name",
                false);
        return districts;
    }
}
