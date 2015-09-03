/*
 * StageState.java
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
 * Состояние этапа документооборота
 * 
 * @author Oleg V. Safonov
 * @version $Id: StageState.java,v 1.2 2006/10/21 11:37:32 safonov Exp $
 */
@DataItemName("DocFlow.DocFlowStage.State")
public enum StageState {
	/**
	 * Не выполнен
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#DocFlowStage.State.None")
	NONE,
	
	/**
	 * Выполнен частично
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#DocFlowStage.State.Partition")
	PARTITION,
	
	/**
	 * Выполнен полностью
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#DocFlowStage.State.Complete")
	COMPLETE
}
