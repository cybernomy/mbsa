/*
 * AccType.java
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
 * ��� �����
 * 
 * @author leonova
 * @version $Id: AccType.java,v 1.1 2006/03/30 11:22:12 safonov Exp $
 */
@DataItemName ("Account.Plan.AccType")
public enum AccType {
	/**
	 * ��������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AccType.Active")
	ACTIVE,
	
	/**
	 * ���������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AccType.Passive")
	PASSIVE,
	
	/**
	 * �������/���������(����������� ������)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AccType.APUnwrap")
	APUNWRAP,
	
	/**
	 * �������/���������(��������� ������)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AccType.APRollUp")
	APROLLUP,
	
	/**
	 * �������
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AccType.Zero")
	ZERO
}