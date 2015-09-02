/*
 * NoSuchMessageException.java
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

import com.mg.framework.api.InternalException;

/**
 * Exception thrown when a message can't be resolved.
 * 
 * @author Oleg V. Safonov
 * @version $Id: NoSuchMessageException.java,v 1.1 2005/07/20 05:53:35 safonov Exp $
 */
public class NoSuchMessageException extends InternalException {

	/**
	 * Create a new exception.
	 * @param code code that could not be resolved for given locale
	 * @param locale locale that was used to search for the code within
	 */
	public NoSuchMessageException(String code, Locale locale) {
		super("No message found under code '" + code + "' for locale '" + locale + "'."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	/**
	 * Create a new exception.
	 * @param code code that could not be resolved for given locale
	 */
	public NoSuchMessageException(String code) {
		super("No message found under code '" + code + "' for locale '" + Locale.getDefault() + "'."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
