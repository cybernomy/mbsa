/*
 * MessageSource.java
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
package com.mg.framework.api.i18n;

import java.util.Locale;


/**
 * Interface to be implemented by objects that can resolve messages.
 * This enables parameterization and internationalization of messages.
 *
 * @see com.mg.framework.support.ResourceBundleMessageSource
 * @author Oleg V. Safonov
 * @version $Id: MessageSource.java,v 1.1 2005/07/20 05:53:35 safonov Exp $
 */
public interface MessageSource {

	/**
	 * Try to resolve the message. Return default message if no message was found.
	 * @param code the code to lookup up, such as 'calculator.noRateSet'. Users of
	 * this class are encouraged to base message names on the relevant fully
	 * qualified class name, thus avoiding conflict and ensuring maximum clarity.
	 * @param args array of arguments that will be filled in for params within
	 * the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
	 * or null if none.
	 * @param locale the Locale in which to do the lookup
	 * @param defaultMessage String to return if the lookup fails
	 * @return the resolved message if the lookup was successful;
	 * otherwise the default message passed as a parameter
	 * @see <a href="http://java.sun.com/j2se/1.3/docs/api/java/text/MessageFormat.html">java.text.MessageFormat</a>
	 */
	String getMessage(String code, Object[] args, String defaultMessage, Locale locale);

	/**
	 * Try to resolve the message. Treat as an error if the message can't be found.
	 * @param code the code to lookup up, such as 'calculator.noRateSet'
	 * @param args Array of arguments that will be filled in for params within
	 * the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
	 * or null if none.
	 * @param locale the Locale in which to do the lookup
	 * @return the resolved message
	 * @throws NoSuchMessageException if the message wasn't found
	 * @see <a href="http://java.sun.com/j2se/1.3/docs/api/java/text/MessageFormat.html">java.text.MessageFormat</a>
	 */
	String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException;

	/**
	 * Try to resolve the message using all the attributes contained within the
	 * <code>MessageSourceResolvable</code> argument that was passed in.
	 * <p>NOTE: We must throw a <code>NoSuchMessageException</code> on this method
	 * since at the time of calling this method we aren't able to determine if the
	 * <code>defaultMessage</code> property of the resolvable is null or not.
	 * @param resolvable value object storing attributes required to properly resolve a message
	 * @param locale the Locale in which to do the lookup
	 * @return the resolved message
	 * @throws NoSuchMessageException if the message wasn't found
	 * @see <a href="http://java.sun.com/j2se/1.3/docs/api/java/text/MessageFormat.html">java.text.MessageFormat</a>
	 */
	//String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException;

}
