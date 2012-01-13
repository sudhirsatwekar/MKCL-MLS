/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.service.impl.AssemblyTermServiceImpl.java
 * Created On: Dec 26, 2011
 */
package org.mkcl.els.service.impl;

import org.mkcl.els.domain.AssemblyTerm;
import org.mkcl.els.service.IAssemblyTermService;
import org.springframework.stereotype.Service;

/**
 * The Class AssemblyTermServiceImpl.
 *
 * @author samiksham
 * @since v1.0.0
 */
@Service
public class AssemblyTermServiceImpl extends GenericServiceImpl<AssemblyTerm, Long>
implements IAssemblyTermService {

    /*@Autowired
    AssemblyTermRepository assemblyTermRepository;

    @Autowired
    public void setAssemblyTermRepository(AssemblyTermRepository assemblyTermRepository) {
        this.dao = assemblyTermRepository;
        this.assemblyTermRepository = assemblyTermRepository;
    }

    @Override
    public AssemblyTerm findByAssemblyTerm(Integer term) {
        return assemblyTermRepository.findByAssemblyTerm(term);
    }*/

}
