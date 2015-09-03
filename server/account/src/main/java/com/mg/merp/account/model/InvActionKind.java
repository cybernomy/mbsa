/*
 * InvActionKind.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;

/**
 * Типы действий с инвентарной карточкой
 * 
 * @author leonova
 * @version $Id: InvActionKind.java,v 1.1 2006/08/28 08:28:02 leonova Exp $
 */
@DataItemName("Account.InvHistory.InvActionKind")
public enum InvActionKind {
	/**
	 * Начисление амортизации
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#InvActionKind.Amort")
	AMORT,
	
	/**
	 * Переоценка
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#InvActionKind.Reval")
	REVAL,
	
	/**
	 * Перемещение
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#InvActionKind.Move")
	MOVE,
	
	/**
	 * Списание
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#InvActionKind.Retire")
	RETIRE,
	
	/**
	 * Консервация
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#InvActionKind.Freez")
	FREEZ,
	
	/**
	 * Дооценка
	 */
	@EnumConstantText ("resource://com.mg.merp.account.resources.dataitemlabels#InvActionKind.Overestimation")
	OVERESTIMATION

}
