/*
 * ResourceGroupType.java
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
 * ��� ������ ��������
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: ResourceGroupType.java,v 1.1 2006/04/13 10:20:42 safonov Exp $
 */
@DataItemName("MfReference.ResourGroup.ResType")
public enum ResourceGroupType {
	/**
	 * ���������
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.Materials")
	MATERIALS,
	
	/**
	 * ������������
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.Machine")
	MACHINE,
	
	/**
	 * ������� ����
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.Labor")
	LABOR,
	
	/**
	 * ������� �����
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.WorkCenter")
	WORKCENTER,
	
	/**
	 * �������������
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.OrgUnit")
	ORGUNIT
}
