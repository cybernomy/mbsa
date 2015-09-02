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
package com.mg.merp.security.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.3 2008/06/10 06:17:51 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.merp.security.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	//message keys
	public static final String ROLE_PERMISSION_TITLE = "RolePermissionsForm.Title"; //$NON-NLS-1$
	public static final String SUBSYSTEM_PERMISSION_TITLE = "SubsystemPermissionsForm.Title"; //$NON-NLS-1$
	public static final String PASSWORD_NOT_MATCH = "PasswordNotMatch"; //$NON-NLS-1$
	public static final String PASSWORD_NOT_NULL = "PasswordNotNull"; //$NON-NLS-1$
	public static final String NAME_NOT_NULL = "NameNotNull"; //$NON-NLS-1$
	public static final String SELECTED = "Selected"; //$NON-NLS-1$
	public static final String METHOD_NAME = "MethodName"; //$NON-NLS-1$

	public static Messages getInstance() {
		return instance;
	}
	
	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
