/*
 * ScheduleDirection.java
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
 * Направление планирования
 * 
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: ScheduleDirection.java,v 1.1 2007/07/30 10:25:11 safonov Exp $
 */
@DataItemName ("MfReference.ScheduleDirection")
public enum ScheduleDirection {
	
	/**
	 * Вперёд
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#SchedDirect.Forward")
	FORWARD,
	
	/**
	 * Назад
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#SchedDirect.Backward")
	BACKWARD

}
