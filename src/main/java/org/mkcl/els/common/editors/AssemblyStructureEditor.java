/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.AssemblyStructureEditor.java
 * Created On: Dec 26, 2011
 */
package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.AssemblyStructure;


/**
 * The Class AssemblyStructureEditor.
 *
 * @author amitd
 * @version v1.0.0
 */
public class AssemblyStructureEditor extends PropertyEditorSupport {


    /* (non-Javadoc)
     * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            AssemblyStructure structure = AssemblyStructure.findById(Long.parseLong(text));
            this.setValue(structure);
        }
    }
}
