/*
 * TimePeriodKind.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: TimePeriodKind.java,v 1.1 2006/03/29 13:06:23 safonov Exp $
 */
@DataItemName ("Reference.Catalog.ShelfLifeMeas")
public enum TimePeriodKind {

	/**
	 * ���
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#TimePeriod.Kind.None")
	NONE,
	
	/**
	 * ���
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#TimePeriod.Kind.Hour")
	HOUR,

	/**
	 * ����
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#TimePeriod.Kind.Day")
	DAY,	

	/**
	 * �����
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#TimePeriod.Kind.Month")
	MONTH,	
	
	/**
	 * ���
	 */
	@EnumConstantText("resource://com.mg.merp.reference.resources.dataitemlabels#TimePeriod.Kind.Year")
	YEAR		
}




