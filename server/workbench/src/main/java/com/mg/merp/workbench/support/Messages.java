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
package com.mg.merp.workbench.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Сообщения модуля Workbench
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: Messages.java,v 1.1 2007/04/11 06:52:18 poroxnenko Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.merp.workbench.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	public static final String GET_SYSCLASSES_ERROR = "GetSysClassesError";
	public static final String GET_SECGROUPS_ERROR = "GetSecGroups";
			
	public static Messages getInstance() {
		return instance;
	}
	
	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
