/*
 * Messages.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.contract.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Artem V. Sharapov
 * @version $Id: Messages.java,v 1.1 2007/03/07 12:31:28 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.merp.contract.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	//message keys
	public static final String MANUAL_DISTRIBUTION_TITLE = "ManualDistributionTitle"; //$NON-NLS-1$
	public static final String MANUAL_DISTRIBUTION_FIELD_NAME = "ManualDistributionFieldName"; //$NON-NLS-1$
	
	public static Messages getInstance() {
		return instance;
	}
	
	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
