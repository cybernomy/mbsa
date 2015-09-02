/**
 * Messages.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.scheduler.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * I18n
 * 
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.merp.scheduler.resources.messages"; //$NON-NLS-1$
	private static Messages instance;
	
	//message keys
	public static final String INVALID_CRON_EXPRESSION = "InvalidCronExpression";
	
	public static Messages getInstance() {
		return instance;
	}
	
	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
