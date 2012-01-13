/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.ConstituencyEditor.java
 * Created On: Jan 4, 2012
 */
package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.Constituency;

/**
 * The Class ConstituencyEditor.
 *
 * @author meenalw
 * @since v1.0.0
 */
public class ConstituencyEditor extends PropertyEditorSupport {

    /*
     * (non-Javadoc)
     *
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            Constituency type = Constituency.findById(Long.parseLong(text));
            setValue(type);
        }
    }
}
