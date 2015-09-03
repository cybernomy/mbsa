/*
 * InputOutputFlag.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Признак потребности/поступления
 * 
 * @author Oleg V. Safonov
 * @version $Id: InputOutputFlag.java,v 1.1 2007/07/30 10:37:30 safonov Exp $
 */
@DataItemName ("Planning.InputOutputFlag")
public enum InputOutputFlag {
	/**
	 * Поступление
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#InputOutputFlag.InputDriven")
	INPUT_DRIVEN,
	
	/**
	 * Потребность
	 */
	@EnumConstantText ("resource://com.mg.merp.planning.resources.dataitemlabels#InputOutputFlag.OutputDriven")
	OUTPUT_DRIVEN
}
