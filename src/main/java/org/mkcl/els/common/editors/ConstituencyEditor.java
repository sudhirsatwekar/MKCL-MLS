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
        Constituency type = null;
        if (isNumeric(text)) {
            type = Constituency.findById(Long.parseLong(text));
        } else {
            type = Constituency.findByName(text);
        }
        setValue(type);
    }

    /**
     * Checks if is numeric.
     *
     * @param str the str
     * @return true, if is numeric
     */
    private boolean isNumeric(final String str) {
        try {
            Long digit = Long.parseLong(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
