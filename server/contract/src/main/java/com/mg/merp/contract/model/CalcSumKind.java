/*
 * CalcSumKind.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Расчет суммы
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CalcSumKind.java,v 1.2 2007/03/23 15:20:52 sharapov Exp $
 */
@DataItemName ("Contract.CalcSumKind") //$NON-NLS-1$
public enum CalcSumKind {
	/**
	 * Не расчитывается
	 */
	@EnumConstantText ("resource://com.mg.merp.contract.resources.dataitemlabels#CalcSumKind.Manual") //$NON-NLS-1$
	MANUAL,

	/**
	 * Автоматически (по этапам/пунктам)
	 */
	@EnumConstantText ("resource://com.mg.merp.contract.resources.dataitemlabels#CalcSumKind.PhasesAggregate") //$NON-NLS-1$
	PHASESAGGREGATE
}