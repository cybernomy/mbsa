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
package com.mg.merp.paymentalloc.support;

import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Класс пользовательских сообщений
 * 
 * @author Artem V. Sharapov
 * @version $Id: Messages.java,v 1.2 2007/05/31 14:11:43 sharapov Exp $
 */
public class Messages extends MessageSourceAccessor {
	
	private static final String BUNDLE_NAME = "com.mg.merp.paymentalloc.resources.messages"; //$NON-NLS-1$
	private static Messages instance;

	//message keys
	public static final String ORG_UNIT_NOT_FOUND = "OrgUnitNotFound"; //$NON-NLS-1$
	public static final String PAYMENT_DST_FOLDER_INVALID = "PaymentDstFolderInvalid"; //$NON-NLS-1$
	public static final String PAYMENT_CANT_BE_ALLOCATED = "PaymentCantBeAllocated"; //$NON-NLS-1$
	public static final String PAYMENT_FULL_ALLOCATED = "PaymentFullAllocated"; //$NON-NLS-1$
	
	public static final String INTERACTIVE_ALLOC_COLUMN_NAME_CAT_CODE = "InteractiveAllocColumnNameCatCode"; //$NON-NLS-1$
	public static final String INTERACTIVE_ALLOC_COLUMN_NAME_CAT_NAME = "InteractiveAllocColumnNameCatName"; //$NON-NLS-1$
	public static final String INTERACTIVE_ALLOC_COLUMN_NAME_MESURE = "InteractiveAllocColumnNameMesure"; //$NON-NLS-1$
	public static final String INTERACTIVE_ALLOC_COLUMN_NAME_QTY = "InteractiveAllocColumnNameQty"; //$NON-NLS-1$
	public static final String INTERACTIVE_ALLOC_COLUMN_NAME_ALLOC_QTY = "InteractiveAllocColumnNameAllocQty"; //$NON-NLS-1$
	public static final String INTERACTIVE_ALLOC_COLUMN_NAME_SUM = "InteractiveAllocColumnNameSum"; //$NON-NLS-1$
	public static final String INTERACTIVE_ALLOC_COLUMN_NAME_ALLOC_SUM = "InteractiveAllocColumnNameAllocSum"; //$NON-NLS-1$
	

	public static Messages getInstance() {
		return instance;
	}

	static {
		MessageSourceAccessor.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
