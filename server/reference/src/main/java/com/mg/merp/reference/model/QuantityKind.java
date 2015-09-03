/*
 * QuantityKind.java
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
 * Количество на складах
 * 
 * @author leonova
 * @version $Id: QuantityKind.java,v 1.1 2006/07/03 12:51:13 leonova Exp $ 
 */
@DataItemName("Reference.QuantityKind")
public enum QuantityKind {
	/**
	 * 
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#QuantityKind.Empty")
	EMPTY,
	
	/**
	 * Фактическое количество
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#QuantityKind.FactQuan")
	FACTQUAN,
	
	/**
	 * Планируемый приход
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#QuantityKind.PlanQuanIn")
	PLANQUANIN,
	
	/**
	 * Планируемый приход
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#QuantityKind.PlanQuanOut")
	PLANQUANOUT,
	
	/**
	 * Зарезервировано
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#QuantityKind.Reserve")
	RESERVE,
	
	/**
	 * Доступное количество
	 */
	@EnumConstantText ("resource://com.mg.merp.reference.resources.dataitemlabels#QuantityKind.AccessQuan")
	ACCESSQUAN
}
