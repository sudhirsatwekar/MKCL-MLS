/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.MemberEditor.java
 * Created On: Jan 6, 2012
 */
package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.MemberDetails;

/**
 * The Class MemberEditor.
 *
 * @author sujitas
 * @since v1.0.0
 */
public class MemberEditor extends PropertyEditorSupport {

    /*
     * (non-Javadoc)
     *
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            final MemberDetails type = MemberDetails.findById(Long.parseLong(text));
            setValue(type);
        }
    }
}
