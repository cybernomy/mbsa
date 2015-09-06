/*
 * MessageSourceAccessor.java
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
package com.mg.framework.generic;

import com.mg.framework.api.i18n.MessageSource;
import com.mg.framework.api.i18n.NoSuchMessageException;
import com.mg.framework.support.ResourceBundleMessageSource;
import com.mg.framework.utils.ServerUtils;

import java.lang.reflect.Field;
import java.util.Locale;

/**
 * Helper class for easy access to messages from a MessageSource, providing various overloaded
 * getMessage methods.
 *
 * @author Oleg V. Safonov
 * @version $Id: MessageSourceAccessor.java,v 1.2 2006/03/01 15:24:39 safonov Exp $
 */
public abstract class MessageSourceAccessor {
  private MessageSource messageSource;

  private static MessageSource createMessageSource(String bundleName) {
    ResourceBundleMessageSource result = new ResourceBundleMessageSource();
    result.setBasename(bundleName);
    return result;
  }

  public static void initializeMessages(String bundleName, Class<? extends MessageSourceAccessor> messageClass) {
    try {
      Field instanceFld = messageClass.getDeclaredField("instance"); //$NON-NLS-1$
      instanceFld.setAccessible(true);
      MessageSourceAccessor obj = messageClass.newInstance();
      obj.messageSource = createMessageSource(bundleName);
      instanceFld.set(null, obj);
    } catch (Exception e) {

    }
  }

  protected String getResourceName() {
    return null;
  }

  /**
   * Return the default locale to use if no explicit locale has been given. <p>The default
   * implementation returns the default locale passed into the corresponding constructor, or
   * LocaleContextHolder's locale as fallback. Can be overridden in subclasses.
   */
  protected Locale getDefaultLocale() {
    try {
      return ServerUtils.getUserLocale();
    } catch (Throwable th) {
      return Locale.getDefault();
    }
  }

  /**
   * Retrieve the message for the given code and the default Locale.
   *
   * @param code           code of the message
   * @param defaultMessage String to return if the lookup fails
   * @return the message
   */
  public String getMessage(String code, String defaultMessage) {
    return this.messageSource.getMessage(code, null, defaultMessage, getDefaultLocale());
  }

  /**
   * Retrieve the message for the given code and the given Locale.
   *
   * @param code           code of the message
   * @param defaultMessage String to return if the lookup fails
   * @param locale         Locale in which to do lookup
   * @return the message
   */
  public String getMessage(String code, String defaultMessage, Locale locale) {
    return this.messageSource.getMessage(code, null, defaultMessage, locale);
  }

  /**
   * Retrieve the message for the given code and the default Locale.
   *
   * @param code           code of the message
   * @param args           arguments for the message, or null if none
   * @param defaultMessage String to return if the lookup fails
   * @return the message
   */
  public String getMessage(String code, Object[] args, String defaultMessage) {
    return this.messageSource.getMessage(code, args, defaultMessage, getDefaultLocale());
  }

  /**
   * Retrieve the message for the given code and the given Locale.
   *
   * @param code           code of the message
   * @param args           arguments for the message, or null if none
   * @param defaultMessage String to return if the lookup fails
   * @param locale         Locale in which to do lookup
   * @return the message
   */
  public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
    return this.messageSource.getMessage(code, args, defaultMessage, locale);
  }

  /**
   * Retrieve the message for the given code and the default Locale.
   *
   * @param code code of the message
   * @return the message
   * @throws org.springframework.context.NoSuchMessageException if not found
   */
  public String getMessage(String code) throws NoSuchMessageException {
    return this.messageSource.getMessage(code, null, getDefaultLocale());
  }

  /**
   * Retrieve the message for the given code and the given Locale.
   *
   * @param code   code of the message
   * @param locale Locale in which to do lookup
   * @return the message
   * @throws org.springframework.context.NoSuchMessageException if not found
   */
  public String getMessage(String code, Locale locale) throws NoSuchMessageException {
    return this.messageSource.getMessage(code, null, locale);
  }

  /**
   * Retrieve the message for the given code and the default Locale.
   *
   * @param code code of the message
   * @param args arguments for the message, or null if none
   * @return the message
   * @throws org.springframework.context.NoSuchMessageException if not found
   */
  public String getMessage(String code, Object[] args) throws NoSuchMessageException {
    return this.messageSource.getMessage(code, args, getDefaultLocale());
  }

  /**
   * Retrieve the message for the given code and the given Locale.
   *
   * @param code   code of the message
   * @param args   arguments for the message, or null if none
   * @param locale Locale in which to do lookup
   * @return the message
   * @throws org.springframework.context.NoSuchMessageException if not found
   */
  public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
    return this.messageSource.getMessage(code, args, locale);
  }

}
