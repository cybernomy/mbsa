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
 * Тип интерактивности выполнения ДО
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocProcessInteractiveKind.java,v 1.1 2006/08/25 12:00:47 safonov Exp $
 */
@DataItemName("DocFlow.DocProcessInteractiveKind")
public enum DocProcessInteractiveKind {
	
	/**
	 * стандартный, выполняется в зависимости от настроек ДО
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#InteractiveKind.Standart")
	STANDART,
	
	/**
	 * тихий, не производит интерактивного взаимодействия с пользователем
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#InteractiveKind.Silent")
	SILENT,
	
	/**
	 * унаследованный, параметры наследуются из ДО родителя
	 */
	@EnumConstantText("resource://com.mg.merp.docflow.resources.dataitemlabels#InteractiveKind.Inherited")
	INHERITED
}
