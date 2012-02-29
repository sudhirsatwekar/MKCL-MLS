package org.mkcl.els.common.editors;

import java.text.DateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;

public class ELSDateEditor extends CustomDateEditor{

    public ELSDateEditor(DateFormat dateFormat, boolean allowEmpty) {
        super(dateFormat, allowEmpty);
    }

}
