/*
 * AnlStdForm.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ������� ����� ����� ��� ������������� ������
 * 
 * @author leonova
 * @version $Id: AnlStdForm.java,v 1.1 2006/03/30 11:22:12 safonov Exp $
 */
@DataItemName ("Account.Plan.AnlStdForm")
public enum AnlStdForm {
	/**
	 * �������� �������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlStdForm.Through")
	THROUGH,
	
	/**
	 * �������� ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlStdForm.Money")
	MONEY,
	
	/**
	 * ���������, ������ ������� ����
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlStdForm.Material")
	MATERIAL,
	
	/**
	 * �����; ������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AnlStdForm.ORDER")
	ORDER
}