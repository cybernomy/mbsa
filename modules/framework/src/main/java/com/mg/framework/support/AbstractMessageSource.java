/*
 * AbstractMessageSource.java
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

import com.mg.framework.api.Logger;
import com.mg.framework.api.i18n.MessageSource;
import com.mg.framework.api.i18n.NoSuchMessageException;
import com.mg.framework.utils.ServerUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Abstract implementation of MessageSource interface, making it easy to implement a custom
 * MessageSource. Subclasses must implement the abstract resolveCode method.
 *
 * <p>Supports not only MessageSourceResolvables as primary messages but also resolution of message
 * arguments that are in turn MessageSourceResolvables themselves.
 *
 * <p>This class does not implement caching, thus subclasses can dynamically change messages over
 * time. Subclasses are encouraged to cache their messages in a modification-aware fashion.
 *
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @version $Id: AbstractMessageSource.java,v 1.1 2005/07/20 05:55:35 safonov Exp $
 * @see #resolveCode
 */
public abstract class AbstractMessageSource implements MessageSource {
  protected Logger logger = ServerUtils.getLogger(this.getClass());
  private boolean useCodeAsDefaultMessage = false;

  /**
   * Subclasses must implement this method to resolve a message. <p>Returns a MessageFormat instance
   * rather than a message String, to allow for appropriate caching of MessageFormats in subclasses.
   * <p><b>Subclasses are encouraged to provide optimized resolution for messages without arguments,
   * not involving MessageFormat.</b> See <code>resolveCodeWithoutArguments</code> javadoc for
   * details.
   *
   * @param code   the code of the message to resolve
   * @param locale the Locale to resolve the code for (subclasses are encouraged to support
   *               internationalization)
   * @return the MessageFormat for the message, or null if not found
   * @see #resolveCodeWithoutArguments
   */
  protected abstract MessageFormat resolveCode(String code, Locale locale);

  /**
   * Subclasses can override this method to resolve a message without arguments in an optimized
   * fashion, i.e. to resolve a message without involving a MessageFormat. <p>The default
   * implementation <i>does</i> use MessageFormat, through delegating to the
   * <code>resolveCode</code> method. Subclasses are encouraged to replace this with optimized
   * resolution. <p>Unfortunately, <code>java.text.MessageFormat</code> is not implemented in an
   * efficient fashion. In particular, it does not detect that a message pattern doesn't contain
   * argument placeholders in the first place. Therefore, it's advisable to circumvent MessageFormat
   * completely for messages without arguments.
   *
   * @param code   the code of the message to resolve
   * @param locale the Locale to resolve the code for (subclasses are encouraged to support
   *               internationalization)
   * @return the message String, or null if not found
   * @see #resolveCode
   * @see java.text.MessageFormat
   */
  protected String resolveCodeWithoutArguments(String code, Locale locale) {
    MessageFormat messageFormat = resolveCode(code, locale);
    if (messageFormat != null) {
      return messageFormat.format(new Object[0]);
    }
    return null;
  }

  /**
   * Search through the given array of objects, find any MessageSourceResolvable objects and resolve
   * them. <p>Allows for messages to have MessageSourceResolvables as arguments.
   *
   * @param args   array of arguments for a message
   * @param locale the locale to resolve through
   * @return an array of arguments with any MessageSourceResolvables resolved
   */
  protected Object[] resolveArguments(Object[] args, Locale locale) {
    if (args == null) {
      return new Object[0];
    }
    List<Object> resolvedArgs = new ArrayList<Object>(args.length);
    for (int i = 0; i < args.length; i++) {
//			if (args[i] instanceof MessageSourceResolvable) {
//				resolvedArgs.add(getMessage((MessageSourceResolvable) args[i], locale));
//			}
//			else {
      resolvedArgs.add(args[i]);
//			}
    }
    return resolvedArgs.toArray(new Object[resolvedArgs.size()]);
  }

  /**
   * Resolve the given code and arguments as message in the given Locale, returning null if not
   * found. Does <i>not</i> fall back to the code as default message. Invoked by getMessage
   * methods.
   *
   * @param code   the code to lookup up, such as 'calculator.noRateSet'. Users of this class are
   *               encouraged to base message names on the relevant fully qualified class name, thus
   *               avoiding conflict and ensuring maximum clarity.
   * @param args   array of arguments that will be filled in for params within the message (params
   *               look like "{0}", "{1,date}", "{2,time}" within a message), or null if none.
   * @param locale the Locale in which to do the lookup
   * @return the resolved message, or null if not found
   * @see #getMessage(String, Object[], String, Locale)
   * @see #getMessage(String, Object[], Locale)
   * @see #getMessage(MessageSourceResolvable, Locale)
   * @see #setUseCodeAsDefaultMessage
   */
  protected String getMessageInternal(String code, Object[] args, Locale locale) {
    if (code == null) {
      return null;
    }
    if (locale == null) {
      locale = Locale.getDefault();
    }

    if (args == null || args.length == 0) {
      // Optimized resolution: no arguments to apply,
      // therefore no MessageFormat needs to be involved.
      // Note that the default implementation still uses MessageFormat;
      // this can be overridden in specific subclasses.
      String message = resolveCodeWithoutArguments(code, locale);
      if (message != null) {
        return message;
      }
    } else {
      MessageFormat messageFormat = resolveCode(code, locale);
      if (messageFormat != null) {
        return messageFormat.format(resolveArguments(args, locale));
      }
    }

    // not found at all
    return null;
  }

  /**
   * Create a MessageFormat for the given message and Locale. <p>This implementation creates an
   * empty MessageFormat first, populating it with Locale and pattern afterwards, to stay compatible
   * with J2SE 1.3.
   *
   * @param msg    the message to create a MessageFormat for
   * @param locale the Locale to create a MessageFormat for
   * @return the MessageFormat instance
   */
  protected MessageFormat createMessageFormat(String msg, Locale locale) {
    if (logger.isDebugEnabled()) {
      logger.debug("Creating MessageFormat for pattern [" + msg + "] and locale '" + locale + "'");
    }
    MessageFormat messageFormat = new MessageFormat("");
    messageFormat.setLocale(locale);
    messageFormat.applyPattern(msg);
    return messageFormat;
  }

  /**
   * Set whether to use the message code as default message instead of throwing a
   * NoSuchMessageException. Useful for development and debugging. Default is false. <p>Note: In
   * case of a MessageSourceResolvable with multiple codes (like a FieldError) and a MessageSource
   * that has a parent MessageSource, do <i>not</i> activate "useCodeAsDefaultMessage" in the
   * <i>parent</i>: Else, you'll get the first code returned as message by the parent, without
   * attempts to check further codes. <p>To be able to work with "useCodeAsDefaultMessage" turned on
   * in the parent, AbstractMessageSource and AbstractApplicationContext contain special checks to
   * delegate to the internal <code>getMessageInternal</code> method if available. In general, it is
   * recommended to just use "useCodeAsDefaultMessage" during development and not rely on it in
   * production in the first place, though.
   *
   * @see #getMessage(String, Object[], Locale)
   * @see #getMessageInternal
   * @see org.springframework.validation.FieldError
   */
  public void setUseCodeAsDefaultMessage(boolean useCodeAsDefaultMessage) {
    this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.MessageSource#getMessage(java.lang.String, java.lang.Object[], java.lang.String, java.util.Locale)
   */
  public String getMessage(String code, Object[] args, String defaultMessage,
                           Locale locale) {
    String msg = getMessageInternal(code, args, locale);
    if (msg != null) {
      return msg;
    }
    if (defaultMessage == null && this.useCodeAsDefaultMessage) {
      return code;
    }
    return defaultMessage;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.MessageSource#getMessage(java.lang.String, java.lang.Object[], java.util.Locale)
   */
  public String getMessage(String code, Object[] args, Locale locale)
      throws NoSuchMessageException {
    String msg = getMessageInternal(code, args, locale);
    if (msg != null) {
      return msg;
    }
    if (this.useCodeAsDefaultMessage) {
      return code;
    }
    throw new NoSuchMessageException(code, locale);
  }

}
