/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.AssemblyNumberEditor.java
 * Created On: Dec 27, 2011
 */

package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.AssemblyNumber;

/**
 * The Class AssemblyNumberEditor.
 *
 * @author amitd
 * @version v1.0.0
 */
public class AssemblyNumberEditor extends PropertyEditorSupport {

    /**
     * Map the id of an object to the object.
     *
     * @param text the new as text
     */
    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            AssemblyNumber assemblyNo = AssemblyNumber.findById(Long.parseLong(text));
            this.setValue(assemblyNo);
        }
    }
}
