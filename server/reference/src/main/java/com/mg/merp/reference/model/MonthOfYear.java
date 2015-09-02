/*
 * MonthOfYear.java
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
 * ������ ����
 * 
 * @author leonova
 * @version $Id: MonthOfYear.java,v 1.1 2006/10/10 06:29:36 leonova Exp $
 */
@DataItemName("Reference.QMonth")
public enum MonthOfYear {
	/**
	 * ������
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.January")
	JANUARY,
	
	/**
	 * �������
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.February")
	FEBRUARY,
	
	/**
	 * ����
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.March")
	MARCH,
	
	/**
	 * ������
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.April")
	APRIL,
	
	/**
	 * ���
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.May")
	MAY,
	
	/**
	 * ����
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.June")
	JUNE,
	
	/**
	 * ����
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.July")
	JULY,
	
	/**
	 * ������
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.August")
	AUGUST,
	
	/**
	 * ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.September")
	SEPTEMBER,
	
	/**
	 * �������
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.October")
	OCTORER,
	
	/**
	 * ������
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.November")
	NOVEMBER,
	
	/**
	 * �������
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#MonthOfYear.December")
	DECEMBER
	
}
