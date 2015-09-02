/*
 * CardActivityKind.java
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
package com.mg.merp.discount.support.ui;

import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Активность дисконтной карты
 * 
 * @author Artem V. Sharapov
 * @version $Id: CardActivityKind.java,v 1.2 2008/02/13 15:06:32 sharapov Exp $
 */
public enum CardActivityKind {
	
	/**
	 * Активна
	 */
	@EnumConstantText("resource://com.mg.merp.discount.resources.formelements#ActivityKind.Active") //$NON-NLS-1$
	ACTIVE,
	
	/**
	 * Неактивна
	 */
	@EnumConstantText("resource://com.mg.merp.discount.resources.formelements#ActivityKind.NotActive") //$NON-NLS-1$
	NOT_ACTIVE
	
}