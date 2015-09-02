/*
 * DocProcessInteractiveKind.java
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
package com.mg.merp.docprocess.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * ��� ��������������� ���������� ��
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocProcessInteractiveKind.java,v 1.1 2006/08/25 12:00:47 safonov Exp $
 */
@DataItemName("DocFlow.DocProcessInteractiveKind")
public enum DocProcessInteractiveKind {
	
	/**
	 * �����������, ����������� � ����������� �� �������� ��
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#InteractiveKind.Standart")
	STANDART,
	
	/**
	 * �����, �� ���������� �������������� �������������� � �������������
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#InteractiveKind.Silent")
	SILENT,
	
	/**
	 * ��������������, ��������� ����������� �� �� ��������
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#InteractiveKind.Inherited")
	INHERITED
}
