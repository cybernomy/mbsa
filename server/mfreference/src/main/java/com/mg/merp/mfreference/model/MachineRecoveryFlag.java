/*
 * MachineRecoveryFlag.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: MachineRecoveryFlag.java,v 1.1 2006/11/02 16:11:06 safonov Exp $
 */
@DataItemName("Manufacture.MchRecoveryFlag")
public enum MachineRecoveryFlag {
	/**
	 * На один час операции
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MchRecoveryFlag.Time")
	TIME,
	
	/**
	 * На единицу готовой продукции
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MchRecoveryFlag.Unit")
	UNIT,
	
	/**
	 * Фиксированная стоимость на партию готовой продукции
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#MchRecoveryFlag.Fixed")
	FIXED
}
