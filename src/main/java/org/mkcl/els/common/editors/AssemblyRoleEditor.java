/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.AssemblyRoleEditor.java
 * Created On: Jan 9, 2012
 */
package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.AssemblyRole;

/**
 * The Class AssemblyRoleEditor.
 *
 * @author nileshp
 * @since v1.0.0
 */
public class AssemblyRoleEditor extends PropertyEditorSupport {

    /*
     * (non-Javadoc)
     *
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            AssemblyRole assemblyRole = AssemblyRole.findById(Long
                    .parseLong(text));
            this.setValue(assemblyRole);
        }
    }

}
