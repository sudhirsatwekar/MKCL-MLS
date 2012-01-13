/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.AssemblyEditor.java
 * Created On: Jan 5, 2012
 */

package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.Assembly;

/**
 * The Class AssemblyEditor.
 *
 * @author nileshp
 * @since v1.0.0
 */
public class AssemblyEditor extends PropertyEditorSupport {

    /**
     * Sets the as text.
     *
     * @param text the new as text
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            Assembly assembly = Assembly.findById(Long.parseLong(text));
            this.setValue(assembly);
        }
    }
}
