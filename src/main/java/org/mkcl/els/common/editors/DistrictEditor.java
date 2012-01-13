/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.DistrictEditor.java
 * Created On: Jan 5, 2012
 */
package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.District;


/**
 * The Class DistrictEditor.
 */
public class DistrictEditor extends PropertyEditorSupport {

    /**
     * Sets the as text.
     *
     * @param text the new as text
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public final void setAsText(final String text) {
        if (!text.equals("")) {
            final District type = District.findById(Long.parseLong(text));
            setValue(type);
        }
    }
}
