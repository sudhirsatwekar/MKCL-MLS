/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.StateEditor.java
 * Created On: Dec 19, 2011
 */
package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.State;


/**
 * The Class StateEditor.
 *
 * @author sandeeps
 * @since v1.0.0
 */
public class StateEditor extends PropertyEditorSupport {

    /**
     * Set as Text. java.lang.String text
     *
     * @param text the new as text
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(final String text) {
        if (!("").equals(text)) {
            final State type = State.findById(Long.parseLong(text));
            setValue(type);
        }
    }
}
