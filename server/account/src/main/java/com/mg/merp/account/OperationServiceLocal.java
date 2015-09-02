/*
 * OperationServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.account;

import com.mg.merp.account.model.EconomicOper;
import com.mg.merp.account.model.EconomicOperModel;
import com.mg.merp.account.model.RemnDbKt;
import com.mg.merp.core.model.Folder;

/**
 * Сервис бизнес-компонента "Хозяйственные операции"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @author Konstantin S. Alikaev
 * @version $Id: OperationServiceLocal.java,v 1.5 2008/03/27 07:32:19 alikaev Exp $
 */
public interface OperationServiceLocal extends com.mg.framework.api.DataBusinessObjectService<EconomicOper, Integer> {
	
	/**
	 * тип папки для хозяйственных операций
	 */
	final static short FOLDER_PART = 6;

	/**
	 * Копирование со сторонирование
	 * 
	 * @param economicOper
	 * 				- хоз.операция
	 * @return
	 * 				- клон хоз. операции
	 */
	EconomicOper storno(EconomicOper economicOper);

	/**
	 * Создание хоз.операции по образцу
	 * @param pattern - образец  
	 * @param folder - папка назначения
	 * @return хоз.операция
	 */
	EconomicOper createByPattern(EconomicOperModel pattern, Folder folder);

	/**
	 * Создание хоз. операции по оборотке "Ведомость расчета с контрагентом"
	 * 
	 * @param remnDbKt
	 * 				- обротка "Ведомость расчета с контрагентом"
	 * @param folder
	 * 				- папка-приемник для создаваемой хоз. операции
	 */
	EconomicOper addFromRemnDbKt(RemnDbKt remnDbKt, Folder folder);

}
