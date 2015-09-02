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
package com.mg.merp.baiengine.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Сообщения модуля BAi
 * 
 * @author Oleg V. Safonov
 * @version $Id: Messages.java,v 1.5 2007/11/15 13:12:08 safonov Exp $
 */
public class Messages extends MessageSourceAccessor {
	private static final String BUNDLE_NAME = "com.mg.merp.baiengine.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	public static final String BAI_IMPLEMENTATION_INSTANTIATIAN_ERROR = "BAiImplementationInstantiationError";
	public static final String BAI_NOT_FOUND = "BAiNotFound";
	public static final String BAI_UNIQUE_CODE = "BAiUniqueCode";
	public static final String BAI_DELETE_ERROR = "BAiDeleteError";
	public static final String BAI_LOAD_ERROR = "BAiLoadError";
	public static final String BAI_EDIT_ERROR = "BAiEditError";
	public static final String BAI_ADD_ERROR = "BAiAddError";
	public static final String BAI_VERSION_ERROR = "BAiVersionError";
	public static final String BAI_PERFORM_ERROR = "BAiPerformError";
	public static final String BAI_UNSUPPORTED_ENGINE = "BAiUnsupportedEngine";
	public static final String CUSTOM_USER_ACTION_NOT_FOUND = "CustomUserActionNotFound";
			
	public static Messages getInstance() {
		return instance;
	}
	
	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}

}
