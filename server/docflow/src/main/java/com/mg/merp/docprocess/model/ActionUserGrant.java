/*
 * ActionUserGrant.java
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
package com.mg.merp.docprocess.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ����� ������������ �� ���������� � ����� ����������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ActionUserGrant.java,v 1.2 2006/08/25 12:00:47 safonov Exp $
 */
@DataItemName("DocFlow.ActionUserGrant")
public enum ActionUserGrant {
	/**
	 * ��� ����
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#ActionUserGrant.None")
	NONE,
	
	/**
	 * ���������� ��
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#ActionUserGrant.Execute")
	EXECUTE,
	
	/**
	 * ����� ��
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#ActionUserGrant.Rollback")
	ROLLBACK,
	
	/**
	 * ���������� � ����� ��
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#ActionUserGrant.Both")
	BOTH
}