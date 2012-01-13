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
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * The Class DatabaseDrivenMessageSource.
 *
 * @author vishals
 * @since v1.0.0
 */
public class DatabaseDrivenMessageSource extends
        ReloadableResourceBundleMessageSource {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory
            .getLogger(DatabaseDrivenMessageSource.class);

    /** The properties. */
    private final Map<String, String> properties = new HashMap<String, String>();

    /**
     * Instantiates a new database driven message source.
     *
     * public DatabaseDrivenMessageSource(){ //reload(); }
     *
     * @return the message format
     * @throws Exception the exception
     * @author sujitas
     * @since v1.0.0
     */

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
     * @see org.springframework.context.support.MessageSourceSupport
     * #createMessageFormat(java.lang.String, java.util.Locale)
     */
    @Override
    protected MessageFormat createMessageFormat(final String message,
                                                final Locale locale) {
        return new MessageFormat(message, locale);
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
        if (properties.size() == 0) {
            reload();
        }
        return getText(code, locale);
    }

    /**
     * Reload.
     *
     * @author sujitas
     * @since v1.0.0
     */
    public void reload() {
        logger.info("Caache reloaded");
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
        String textForCurrentLanguage = properties.get(key);
        if (textForCurrentLanguage == null) {
            textForCurrentLanguage = properties.get(Locale.ENGLISH.toString()
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
    protected Map<String, String> loadTexts() {
        Map<String, String> m = new HashMap<String, String>();
        List<MessageResource> messages = MessageResource.findAll();
        for (MessageResource message : messages) {
            if (message.getValue() != null) {
                m.put(
                        message.getLocale().toString() + "_"
                                + message.getCode(), message.getValue());
            }
        }
        return m;
    }

}
