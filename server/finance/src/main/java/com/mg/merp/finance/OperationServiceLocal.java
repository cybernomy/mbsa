/*
 * OperationServiceLocal.java
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
package com.mg.merp.finance;

import com.mg.merp.core.model.Folder;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.OperationModel;

/**
 * Сервис бизнес-компонента "Финансовые операции"
 * 
 * @author Artem V. Sharapov
 * @version $ID$
 */
public interface OperationServiceLocal
extends com.mg.framework.api.DataBusinessObjectService<FinOperation, Integer>  {
	/**
	 * тип папки для финансовых операций
	 */
	final static short FOLDER_PART = 40;

	/**
	 * Создание финансовой операции по образцу
	 * @param pattern - образец
	 * @param folder - папка назначения
	 * @return финансовая операция
	 */
	FinOperation createByPattern(OperationModel pattern, Folder folder);
	
}
