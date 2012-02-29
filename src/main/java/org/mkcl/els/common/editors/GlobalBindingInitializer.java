package org.mkcl.els.common.editors;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

@Component
public class GlobalBindingInitializer implements WebBindingInitializer{

    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        NumberFormat numFormatter = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat)numFormatter; 
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols(); 
   
        // set the beginning of the range to Arabic digits 
        dfs.setZeroDigit('\u0966'); 
        df.setDecimalFormatSymbols(dfs); 

        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class,numFormatter,true));
    }

}
