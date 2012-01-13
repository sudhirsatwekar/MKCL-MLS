/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.service.impl.MemberDetailsServiceImpl.java
 * Created On: Jan 6, 2012
 */
package org.mkcl.els.service.impl;

import org.mkcl.els.domain.MemberDetails;
import org.mkcl.els.service.IMemberDetailsService;
import org.springframework.stereotype.Service;

/**
 * The Class MemberDetailsServiceImpl.
 *
 * @author sujitas
 * @since v1.0.0
 */
@Service
public class MemberDetailsServiceImpl extends
        GenericServiceImpl<MemberDetails, Long> implements
        IMemberDetailsService {
}
