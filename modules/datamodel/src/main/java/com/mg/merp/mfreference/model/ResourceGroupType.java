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
 * Тип группы ресурсов
 * @author Julia 'Jetta' Konyashkina
 * @version $Id: ResourceGroupType.java,v 1.1 2006/04/13 10:20:42 safonov Exp $
 */
@DataItemName("MfReference.ResourGroup.ResType")
public enum ResourceGroupType {
	/**
	 * Материалы
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.Materials")
	MATERIALS,
	
	/**
	 * Оборудование
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.Machine")
	MACHINE,
	
	/**
	 * Рабочая сила
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.Labor")
	LABOR,
	
	/**
	 * Рабочий центр
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.WorkCenter")
	WORKCENTER,
	
	/**
	 * Подразделение
	 */
	@EnumConstantText("resource://com.mg.merp.mfreference.resources.dataitemlabels#ResGrType.OrgUnit")
	ORGUNIT
}
