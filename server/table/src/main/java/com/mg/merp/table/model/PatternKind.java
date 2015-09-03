/*
 * PatternKind.java
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
package com.mg.merp.table.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Тип шаблона
 * 
 * @author leonova
 * @version $Id: PatternKind.java,v 1.1 2006/03/30 11:32:49 safonov Exp $
 */
@DataItemName ("Table.PatternHead.PatternKind")
public enum PatternKind {
	/**
	 * Недельный
	 */
	@EnumConstantText ("resource://com.mg.merp.table.resources.dataitemlabels#PatternKind.Weekly")
	WEEKLY,

	/**
	 * Сменный
	 */
	@EnumConstantText ("resource://com.mg.merp.table.resources.dataitemlabels#PatternKind.Shifting")
	SHIFTING

}

