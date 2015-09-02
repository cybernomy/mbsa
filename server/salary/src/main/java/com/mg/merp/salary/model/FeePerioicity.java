/*
 * FeePerioicity.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Периодичность начисления/удержания
 * 
 * @author leonova
 * @version $Id: FeePerioicity.java,v 1.1 2006/03/30 11:31:37 safonov Exp $
 */
@DataItemName ("Salary.FeeRef.FeePerioicity")
public enum FeePerioicity {
	/**
	 * Постоянное
	 */
	@EnumConstantText ("resource://com.mg.merp.salary.resources.dataitemlabels#FeePerioicity.Constant")
	CONSTANT,
	
	/**
	 * Ежеквартальное
	 */
	@EnumConstantText ("resource://com.mg.merp.salary.resources.dataitemlabels#FeePerioicity.Quarterly")
	QUARTERLY,
	
	/**
	 * Ежегодное
	 */
	@EnumConstantText ("resource://com.mg.merp.salary.resources.dataitemlabels#FeePerioicity.Yearly")
	YEARLY
	
}