/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2012 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.editors.MenuItemEditor.java
 * Created On: Jan 6, 2012
 */
package org.mkcl.els.common.editors;

import java.beans.PropertyEditorSupport;

import org.mkcl.els.domain.MenuItem;

/**
 * The Class MenuItemEditor.
 *
 * @author meenalw
 * @since v1.0.0
 */
public class MenuItemEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(final String text) {
        if (!text.equals("")) {
            MenuItem type = MenuItem.findById(Long.parseLong(text));
            setValue(type);
        }
    }

}
