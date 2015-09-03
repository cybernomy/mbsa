/*
 * Messages.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.retail.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Класс пользовательских сообщений
 * 
 * @author Artem V. Sharapov
 * @version $Id: Messages.java,v 1.1 2007/10/05 07:35:57 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.merp.retail.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	//message keys
	public static final String CUSTOM_SELECT_DIS_CARD_OWNER = "CustomSelectDisCardOwner"; //$NON-NLS-1$
	public static final String CUSTOM_SELECT_DIS_CARD_USER = "CustomSelectDisCardUser"; //$NON-NLS-1$
	public static final String CUSTOM_SELECT_PARTNER = "CustomSelectPartner"; //$NON-NLS-1$
	public static final String CUSTOM_SELECT_STATUS = "CustomSelectStatus"; //$NON-NLS-1$
	
	public static final String DIS_CARD_SELECT_NUMBER = "DisCardSelectNumber"; //$NON-NLS-1$
	public static final String DIS_CARD_SELECT_OWNER = "DisCardSelectOwner"; //$NON-NLS-1$
	


	public static Messages getInstance() {
		return instance;
	}

	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
}
