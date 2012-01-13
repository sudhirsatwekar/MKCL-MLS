/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.PartyEditor.java
 * Created On: Dec 20, 2011
 */
package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.Party;

/**
 * The Class PartyEditor.
 *
 * @author meenalw
 * @since v1.0.0
 */
public class PartyEditor extends PropertyEditorSupport {

    /*
     * (non-Javadoc)
     *
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            Party type = Party.findById(Long.parseLong(text));
            setValue(type);
        }
    }
}
