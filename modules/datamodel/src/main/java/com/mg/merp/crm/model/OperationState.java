/*
 * OperationState.java
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
 * Состояние действия
 * 
 * @author leonova
 * @version $Id: OperationState.java,v 1.1 2006/03/30 11:24:41 safonov Exp $
 */
@DataItemName("CRM.Operation.OperationState")
public enum OperationState {
	/**
	 * <>
	 */
	@EnumConstantText("resource://com.mg.merp.crm.resources.dataitemlabels#OperationState.OpStateNone")
	OPSTATENONO,
	
	/**
	 * Принято
	 */
	@EnumConstantText("resource://com.mg.merp.crm.resources.dataitemlabels#OperationState.OpStateAccepted")
	OPSTATEACCEPTED,
	
	/**
	 * Отклонено
	 */
	@EnumConstantText("resource://com.mg.merp.crm.resources.dataitemlabels#OperationState.OpStateDenied")
	OPSTATEDENIED,
	
	/**
	 * Перенесено
	 */
	@EnumConstantText("resource://com.mg.merp.crm.resources.dataitemlabels#OperationState.OpStateMoved")
	OPSTATEMOVED

}
