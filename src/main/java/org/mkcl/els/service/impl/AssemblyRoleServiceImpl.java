/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.service.impl.AssemblyRoleServiceImpl.java
 * Created On: Jan 9, 2012
 */

package org.mkcl.els.service.impl;

import org.mkcl.els.domain.AssemblyRole;
import org.mkcl.els.service.IAssemblyRoleService;
import org.springframework.stereotype.Service;

/**
 * The Class AssemblyRoleServiceImpl.
 *
 * @author amitd
 * @version v1.0.0
 */
@Service
public class AssemblyRoleServiceImpl extends
        GenericServiceImpl<AssemblyRole, Long> implements IAssemblyRoleService {

/*    *//** The assembly role repository. *//*
    private AssemblyRoleRepository assemblyRoleRepository;

    *//**
     * Sets the assembly role repository.
     *
     * @param assemblyRoleRepository the new assembly role repository
     *//*
    @Autowired
    public void setAssemblyRoleRepository(final AssemblyRoleRepository assemblyRoleRepository) {
        this.dao = assemblyRoleRepository;
        this.assemblyRoleRepository = assemblyRoleRepository;
    }

    *//**
     * Find by name.
     *
     * @param name the name
     * @return the assembly role
     * @author nileshp
     * @since v1.0.0
     *//*
    @Override
    public AssemblyRole findByName(final String name) {
        return this.assemblyRoleRepository.findByName(name);
    }

     (non-Javadoc)
     * @see org.mkcl.els.service.IAssemblyRoleService#findAllSorted(java.lang.String)

    @Override
    public List<AssemblyRole> findAllSorted(final String locale) {
        return assemblyRoleRepository.findAllSorted(locale);
    }

     (non-Javadoc)
     * @see org.mkcl.els.service.IAssemblyRoleService#findUnassignedRoles(java.lang.String, java.lang.Long)

    @Override
    public List<AssemblyRole> findUnassignedRoles(final String locale, final Long memberId) {
        return assemblyRoleRepository.findUnassignedRoles(locale, memberId);

    }*/

}
