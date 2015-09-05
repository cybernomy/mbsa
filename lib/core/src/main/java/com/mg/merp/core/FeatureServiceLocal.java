/*
 * FeatureServiceLocal.java
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
package com.mg.merp.core;

import com.mg.merp.core.model.Feature;

/**
 * Бизнес-компонент "Пользовательские поля"
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: FeatureServiceLocal.java,v 1.3 2007/01/25 15:40:10 safonov Exp $
 */
public interface FeatureServiceLocal
   extends  com.mg.framework.api.DataBusinessObjectService<Feature, Integer>
{
	/**
	 * поиск описателя пользовательского поля по коду
	 * 
	 * @param code	код
	 * @return	описатель пользовательского поля
	 */
	Feature findByCode(String code);
}
