/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 ${company_name}.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.TehsilEditor.java
 * Created On: Jan 9, 2012
 */

package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.Tehsil;

/**
 * The Class TehsilEditor.
 */
public class TehsilEditor extends PropertyEditorSupport {

    /**
     * Set as Text.
     * java.lang.String text
     *
     * @param text the text
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            final Tehsil type = Tehsil.findById(Long.parseLong(text));
            setValue(type);
        }
    }
}
