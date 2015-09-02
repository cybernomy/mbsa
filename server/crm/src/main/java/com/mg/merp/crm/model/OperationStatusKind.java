/*
 * OperationStatusKind.java
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
package com.mg.merp.crm.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ������� ��������� ��������
 * 
 * @author leonova
 * @version $Id: OperationStatusKind.java,v 1.1 2006/03/30 11:24:41 safonov Exp $
 */
@DataItemName("CRM.Operation.OperationStatusKind")
public enum OperationStatusKind {
	/**
	 * �����
	 */
	@EnumConstantText("resource://com.mg.merp.crm.resources.dataitemlabels#OperationStatusKind.OpStatusNew")
	OPSTATUSNEW,
	
	/**
	 * � ������
	 */
	@EnumConstantText("resource://com.mg.merp.crm.resources.dataitemlabels#OperationStatusKind.OpStatusInWork")
	OPSTATUSINWORK,
	
	/**
	 * ���������
	 */
	@EnumConstantText("resource://com.mg.merp.crm.resources.dataitemlabels#OperationStatusKind.OpStatusDone")
	OPSTATUSDONE,
	
	/**
	 * ��������
	 */
	@EnumConstantText("resource://com.mg.merp.crm.resources.dataitemlabels#OperationStatusKind.OpStatusCancel")
	OPSTATUSCANCEL
}
