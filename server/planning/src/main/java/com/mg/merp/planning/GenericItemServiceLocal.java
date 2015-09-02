/*
 * GenericItemServiceLocal.java
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
package com.mg.merp.planning;

import com.mg.merp.planning.model.GenericItem;

/**
 * Ѕизнес-компонент "ќбобщенный товар"
 * 
 * @author leonova
 * @version $Id: GenericItemServiceLocal.java,v 1.2 2007/07/30 10:37:51 safonov Exp $
 */
public interface GenericItemServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<GenericItem, Integer> {

	/**
	 * им€ сервиса
	 */
	static final String SERVICE_NAME = "merp/planning/GenericItem";
	
	/**
	 * расчет кода нижнего уровн€ (Low level code)
	 *
	 */
	void buildLowLevelCodes();

	/**
	 * поиск обобщенного товара по позиции каталога
	 * 
	 * @param catalogId	идентификатор позиции каталога
	 * @return	обощенный товар или <code>null</code> если не найден
	 */
	GenericItem findByCatalog(int catalogId);
	
}
