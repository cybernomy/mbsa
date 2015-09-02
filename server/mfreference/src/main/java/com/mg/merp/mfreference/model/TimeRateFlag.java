/*
 * TimeRateFlag.java
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
 * ����� ��������� ������� �������
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: TimeRateFlag.java,v 1.1 2006/11/02 16:11:06 safonov Exp $
 */
@DataItemName("Manufacture.TimeRateFlag")
public enum TimeRateFlag {
	/**
	 * �� ������� ������� ���������
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#TimeRateFlag.Time")
	TIME,
	
	/**
	 * ������ ������� ��������� �� �����
	 */
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#TimeRateFlag.Rate")
	RATE,
		
	/**
	* ������������� ����� �� ������ ������� ���������
	*/
	@EnumConstantText("resource://com.mg.merp.manufacture.resources.dataitemlabels#TimeRateFlag.Fixed")
	FIXED
}
