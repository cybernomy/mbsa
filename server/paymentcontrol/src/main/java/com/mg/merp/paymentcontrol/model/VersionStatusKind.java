/*
 * VersionStatusKind.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.paymentcontrol.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ������ ������ ������������ ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: VersionStatusKind.java,v 1.1 2007/02/06 09:28:31 safonov Exp $
 */
@DataItemName("PaymentControl.VersionStatus.Kind")
public enum VersionStatusKind {
	/**
	 * �� ����������
	 */
	@EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#VersionStatusKind.None")
	NONE,
	
	/**
	 * � ������
	 */
	@EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#VersionStatusKind.InWork")
	IN_WORK,
	
	/**
	 * ������
	 */
	@EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#VersionStatusKind.Ready")
	READY,
	
	/**
	 * ����������
	 */
	@EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#VersionStatusKind.Execute")
	EXECUTE,
	
	/**
	 * ������� ���������
	 */
	@EnumConstantText("resource://com.mg.merp.paymentcontrol.resources.dataitemlabels#VersionStatusKind.Documents")
	DOCUMENTS
}
