/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.service.IAssemblyRoleService.java
 * Created On: Jan 9, 2012
 */

package org.mkcl.els.service;

import org.mkcl.els.domain.AssemblyRole;

/**
 * The Interface IAssemblyRoleService.
 *
 * @author amitd
 * @version v1.0.0
 */
public interface IAssemblyRoleService extends
        IGenericService<AssemblyRole, Long> {

 /*   *//**
     * Find by name.
     *
     * @param name the name
     * @return the assembly role
     * @author nileshp
     * @since v1.0.0
     *//*
    public AssemblyRole findByName(String name);

    *//**
     * Find all sorted.
     *
     * @param locale the locale
     * @return the list
     * @author nileshp
     * @since v1.0.0
     *//*
    public List<AssemblyRole> findAllSorted(String locale);

    *//**
     * Find unassigned roles.
     *
     * @param locale the locale
     * @param memberId the member id
     * @return the list
     * @author nileshp
     * @since v1.0.0
     *//*
    public List<AssemblyRole> findUnassignedRoles(String locale, Long memberId);*/
}
