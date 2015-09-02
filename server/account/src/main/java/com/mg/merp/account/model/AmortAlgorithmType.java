/*
 * AmortAlgorithmType.java
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
 * Алгоритм расчета амортизации
 * 
 * @author leonova
 * @version $Id: AmortAlgorithmType.java,v 1.1 2006/03/30 11:22:12 safonov Exp $
 */
@DataItemName ("Account.Inventory.AmortAlgorithmType")
public enum AmortAlgorithmType {
	/**
	 * Линейный
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AmortAlgorithmType.Linear")
	LINEAR,
	
	/**
	 * Пропорционально остаточной стоимости
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AmortAlgorithmType.ByEndCost")
	BYENDCOST,
	
	/**
	 * По сроку полезного использования (кол-во лет)
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AmortAlgorithmType.ByExplPeriod")
	BYEXPLPERIOD,
	
	/**
	 * Пропорционально объему продукции
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#AmortAlgorithmType.ByProduction")
	BYPRODUCTION

}