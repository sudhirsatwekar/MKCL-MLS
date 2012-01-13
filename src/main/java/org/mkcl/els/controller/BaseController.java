/*
 * 
 */
package org.mkcl.els.controller;

import java.util.Locale;

import org.mkcl.els.domain.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;

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

}
