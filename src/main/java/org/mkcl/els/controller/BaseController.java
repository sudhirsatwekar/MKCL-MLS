/*
 * 
 */
package org.mkcl.els.controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.mkcl.els.domain.AuthUser;
import org.mkcl.els.domain.CustomParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseController.
 *
 * @author sandeeps
 * @version v1.0.0
 */
public abstract class BaseController {
	
	/** The logger. */
	protected Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * Gets the current user.
     *
     * @return the current user
     */
    protected AuthUser getCurrentUser(){
       return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    
    /**
     * Gets the user locale.
     *
     * @return the user locale
     */
    protected Locale getUserLocale(){
    	Locale locale = LocaleContextHolder.getLocale();
    	return locale;
    }

    /**
     * Checks if is session valid.
     *
     * @return true, if is session valid
     */
    protected boolean isSessionValid(){
       if(null==SecurityContextHolder.getContext().getAuthentication().getPrincipal())
           return false;
        else
           return true;
    }
    
    
    protected void defaultBinders(final WebDataBinder binder){
        NumberFormat numFormatter = NumberFormat.getInstance();
        DecimalFormat df = (DecimalFormat)numFormatter; 
        DecimalFormatSymbols dfs = df.getDecimalFormatSymbols(); 
   
        // set the beginning of the range to Arabic digits 
        dfs.setZeroDigit('\u0966'); 
        df.setDecimalFormatSymbols(dfs); 

        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class,numFormatter,true));
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(CustomParameter
                .findByName("SERVER_DATEFORMAT").getValue());
        dateFormat.setLenient(true);
        dateFormat.setNumberFormat(numFormatter);
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(
                dateFormat, true));
    }

}
