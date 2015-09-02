/*
 * CenterKind.java
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
package com.mg.merp.finance.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * @author leonova
 * @version $Id: CenterKind.java,v 1.1 2006/03/30 11:26:56 safonov Exp $
 */
@DataItemName ("Finance.Center.Kind")
public enum CenterKind {
	/**
	 * ����� ����������� �����
	 */
	@EnumConstantText ("resource://com.mg.merp.finance.resources.dataitemlabels#FinCenter.Kind.CFU")
	CFU,
	
	/**
	 * ����� ���������� ���������������
	 */
	@EnumConstantText ("resource://com.mg.merp.finance.resources.dataitemlabels#FinCenter.Kind.CFO")
	CFO,
	
	/**
	 * ������-�����
	 */
	@EnumConstantText ("resource://com.mg.merp.finance.resources.dataitemlabels#FinCenter.Kind.Venture")
	VENCHURE,
	
	/**
	 * ������-�����
	 */
	@EnumConstantText ("resource://com.mg.merp.finance.resources.dataitemlabels#FinCenter.Kind.Profit")
	PROFIT
}