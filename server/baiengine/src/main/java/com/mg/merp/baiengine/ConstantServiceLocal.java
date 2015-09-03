/*
 * ConstantServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System..
 *
 */
package com.mg.merp.baiengine;

import com.mg.merp.baiengine.model.Constant;

/**
 * Бизнес-компонент "Константы"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: ConstantServiceLocal.java,v 1.2 2008/04/09 14:49:16 safonov Exp $
 */
public interface ConstantServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Constant, Integer> {
	/**
	 * Имя сервиса
	 */
	final static String SERVICE_NAME = "merp/baiengine/Constant";
	/**
	 * тип папки для констант
	 */
	final static short FOLDER_PART = 9510;
	
	/**
	 * Получить актуальное значение константы
	 * 
	 * @param code			код константы
	 * @param actualDate	дата актульности
	 * @return				значение константы или <code>null</code> если не найдено
	 */
	Object getActualValue(java.lang.String code, java.util.Date actualDate);
}
