/*
 * OrderDueDateKind.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: OrderDueDateKind.java,v 1.1 2006/04/13 10:28:32 safonov Exp $
 */
@DataItemName("Warehouse.OrderHead.DueDateKind")
public enum OrderDueDateKind {
	/**
	 * ���
	 */
	@EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#DateKind.None")
	NONE,
	
	/**
	 * ���
	 */
	@EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#DateKind.Hour")
	HOUR,

	/**
	 * ����
	 */
	@EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#DateKind.Day")
	DAY,	

	/**
	 * �����
	 */
	@EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#DateKind.Month")
	MONTH,	
	
	/**
	 * ���
	 */
	@EnumConstantText("resource://com.mg.merp.warehouse.resources.dataitemlabels#DateKind.Year")
	YEAR		
}
