/*
 * ResourceBundleMessageSource.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.support;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.mg.framework.utils.StringUtils;

/**
 * @author Oleg V. Safonov
 * @author Valentin A. Poroxnenko
 * @version $Id: ResourceBundleMessageSource.java,v 1.1 2005/07/20 05:55:53 safonov Exp $
 */
public class ResourceBundleMessageSource extends AbstractMessageSource {

  private final Map<String, Map<Locale, ResourceBundle>> cachedResourceBundles = new HashMap<String, Map<Locale, ResourceBundle>>();
  /**
   * Cache to hold already generated MessageFormats per message code. Note that this Map contains
   * the actual code Map, keyed with the Locale.
   *
   * @see #getMessageFormat
   */
  private final Map<ResourceBundle, Map<String, Map<Locale, MessageFormat>>> cachedMessageFormats = new HashMap<ResourceBundle, Map<String, Map<Locale, MessageFormat>>>();
  private String[] basenames;

  /**
   * Set a single basename, following ResourceBundle conventions: It is a fully-qualified classname.
   * If it doesn't contain a package qualifier (such as org.mypackage), it will be resolved from the
   * classpath root. <p>Messages will normally be held in the /lib or /classes directory of a WAR.
   * They can also be held in Jars on the class path. For example, a Jar in an application's
   * manifest classpath could contain messages for the application.
   *
   * @param basename the single basename
   * @see #setBasenames
   * @see java.util.ResourceBundle
   */
  public void setBasename(String basename) {
    setBasenames(new String[]{basename});
  }

  /**
   * Set an array of basenames, each following ResourceBundle conventions. The associated resource
   * bundles will be checked sequentially when resolving a message code. <p>Note that message
   * definitions in a <i>previous</i> resource bundle will override ones in a later bundle, due to
   * the sequential lookup.
   *
   * @param basenames an array of basenames
   * @see #setBasename
   * @see java.util.ResourceBundle
   */
  public void setBasenames(String[] basenames) {
    this.basenames = basenames;
  }

  protected String resolveCodeWithoutArguments(String code, Locale locale) {
    String result = null;
    for (int i = 0; result == null && i < this.basenames.length; i++) {
      ResourceBundle bundle = getResourceBundle(this.basenames[i], locale);
      if (bundle != null) {
        result = getStringOrNull(bundle, code);
      }
    }
    return result;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.support.AbstractMessageSource#resolveCode(java.lang.String, java.util.Locale)
   */
  @Override
  protected MessageFormat resolveCode(String code, Locale locale) {
    MessageFormat messageFormat = null;
    for (int i = 0; messageFormat == null && i < this.basenames.length; i++) {
      ResourceBundle bundle = getResourceBundle(this.basenames[i], locale);
      if (bundle != null) {
        messageFormat = getMessageFormat(bundle, code, locale);
      }
    }
    return messageFormat;
  }

  /**
   * Return a ResourceBundle for the given basename and code, fetching already generated
   * MessageFormats from the cache.
   *
   * @param basename the basename of the ResourceBundle
   * @param locale   the Locale to find the ResourceBundle for
   * @return the resulting ResourceBundle, or null if none found for the given basename and Locale
   */
  protected ResourceBundle getResourceBundle(String basename, Locale locale) {
    synchronized (this.cachedResourceBundles) {
      Map<Locale, ResourceBundle> localeMap = this.cachedResourceBundles.get(basename);
      if (localeMap != null) {
        ResourceBundle bundle = (ResourceBundle) localeMap.get(locale);
        if (bundle != null) {
          return bundle;
        }
      }
      try {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        ResourceBundle bundle = ResourceBundle.getBundle(basename, locale, cl);
        if (localeMap == null) {
          localeMap = new HashMap<Locale, ResourceBundle>();
          this.cachedResourceBundles.put(basename, localeMap);
        }
        localeMap.put(locale, bundle);
        return bundle;
      } catch (MissingResourceException ex) {
        logger.warn("ResourceBundle [" + basename + "] not found for MessageSource: " + ex.getMessage());
        // assume bundle not found
        // -> do NOT throw the exception to allow for checking parent message source
        return null;
      }
    }
  }

  /**
   * Return a MessageFormat for the given bundle and code, fetching already generated MessageFormats
   * from the cache.
   *
   * @param bundle the ResourceBundle to work on
   * @param code   the message code to retrieve
   * @param locale the Locale to use to build the MessageFormat
   * @return the resulting MessageFormat, or null if no message defined for the given code
   */
  protected MessageFormat getMessageFormat(ResourceBundle bundle, String code, Locale locale)
      throws MissingResourceException {

    synchronized (this.cachedMessageFormats) {
      Map<String, Map<Locale, MessageFormat>> codeMap = this.cachedMessageFormats.get(bundle);
      Map<Locale, MessageFormat> localeMap = null;
      if (codeMap != null) {
        localeMap = codeMap.get(code);
        if (localeMap != null) {
          MessageFormat result = localeMap.get(locale);
          if (result != null) {
            return result;
          }
        }
      }

      String msg = getStringOrNull(bundle, code);
      if (msg != null) {
        if (codeMap == null) {
          codeMap = new HashMap<String, Map<Locale, MessageFormat>>();
          this.cachedMessageFormats.put(bundle, codeMap);
        }
        if (localeMap == null) {
          localeMap = new HashMap<Locale, MessageFormat>();
          codeMap.put(code, localeMap);
        }
        MessageFormat result = createMessageFormat(msg, locale);
        localeMap.put(locale, result);
        return result;

      }
      return null;
    }
  }

  private String getStringOrNull(ResourceBundle bundle, String key) {
    try {
      return StringUtils.iso2utf8(bundle.getString(key));
    } catch (MissingResourceException ex) {
      // assume key not found
      // -> do NOT throw the exception to allow for checking parent message source
      return null;
    }
  }

  /**
   * Show the configuration of this MessageSource.
   */
//	public String toString() {
//		return getClass().getName() + ": basenames=[" + StringUtils.arrayToCommaDelimitedString(this.basenames) + "]";
//	}

}
