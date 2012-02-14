/**
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2011 MKCL.  All rights reserved.
 *
 * Project: e-Legislature
 * File: org.mkcl.els.common.util.DatabaseDrivenMessageSource.java
 * Created On: Dec 26, 2011
 */
package org.mkcl.els.common.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.mkcl.els.domain.MessageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractMessageSource;

/**
 * The Class DatabaseDrivenMessageSource.
 *
 * @author vishals
 * @since v1.0.0
 */
public class DatabaseDrivenMessageSource extends AbstractMessageSource {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DatabaseDrivenMessageSource.class);

    /** The properties. */
    private final Map<String, Object> properties = new HashMap<String, Object>();
    
    private long cacheMillis = -1;

    /**
     * Set the number of seconds to cache loaded properties files.
     * <ul>
     * <li>Default is "-1", indicating to cache forever (just like
     * java.util.ResourceBundle).
     * <li>A positive number will cache loaded properties files for the given
     * number of seconds. This is essentially the interval between refresh attempts.
     * Note that a refresh attempt will first check the last-modified timestamp
     * of the file before actually reloading it; so if files don't change, this
     * interval can be set rather low, as refresh attempts will not actually reload.
     * <li>A value of "0" will check the last-modified timestamp of the file on
     * every message access. <b>Do not use this in a production environment!</b>
     * </ul>
     */
    public void setCacheSeconds(int cacheSeconds) {
        this.cacheMillis = cacheSeconds * 1000;
    }


    /**
     * After properties set.
     *
     * @throws Exception the exception
     */
    public void afterPropertiesSet() throws Exception {

    }

    
    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.context.support.ReloadableResourceBundleMessageSource
     * #resolveCode(java.lang.String, java.util.Locale)
     */
    @Override
    protected MessageFormat resolveCode(final String code, final Locale locale) {
        String message = getText(code, locale);
        MessageFormat format = createMessageFormat(message, locale);
        return format;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.context.support.ReloadableResourceBundleMessageSource
     * #resolveCodeWithoutArguments(java.lang.String, java.util.Locale)
     */
    @Override
    protected String resolveCodeWithoutArguments(final String code,
                                                 final Locale locale) {
        if (properties.size() == 0 || (System.currentTimeMillis() - (Long)properties.get("current_timestamp") > this.cacheMillis)) {
            reload();
        }
        return getText(code, locale);
    }
    
    /*
     * (non-Javadoc)
     *
     * @see org.springframework.context.support.MessageSourceSupport
     * #createMessageFormat(java.lang.String, java.util.Locale)
     */
    @Override
    protected MessageFormat createMessageFormat(final String message,
                                                final Locale locale) {
        return new MessageFormat(message, locale);
    }


    /**
     * Reload.
     *
     * @author sujitas
     * @since v1.0.0
     */
    public void reload() {
        logger.info("MessageSource Cache reloaded");
        properties.clear();
        properties.putAll(loadTexts());
    }

    /**
     * Gets the text.
     *
     * @param code the code
     * @param locale the locale
     * @return the text
     */
    private String getText(final String code, final Locale locale) {
        String key = locale.toString() + "_" + code;
        String textForCurrentLanguage = (String) properties.get(key);
        if (textForCurrentLanguage == null) {
            textForCurrentLanguage = (String) properties.get(Locale.ENGLISH.toString()
                    + "_" + code);
        }
        // return textForCurrentLanguage != null ? textForCurrentLanguage :
        // code;
        return textForCurrentLanguage;
    }

    /**
     * Load texts.
     *
     * @return the map
     * @author sujitas
     * @since v1.0.0
     */
    protected Map<String, Object> loadTexts() {
        Map<String, Object> m = new HashMap<String, Object>();
        List<MessageResource> messages = MessageResource.findAll();
        for (MessageResource message : messages) {
            if (message.getValue() != null) {
                m.put(
                        message.getLocale().toString() + "_"
                                + message.getCode(), message.getValue());
            }
        }
        m.put("current_timestamp", System.currentTimeMillis());
        return m;
    }

}
