/*
 * CostsAnlServiceLocal.java
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
package com.mg.merp.personnelref;

import com.mg.merp.personnelref.model.CostsAnl;

/**
 * Сервис бизнес-компонента "Аналитика состава затрат"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CostsAnlServiceLocal.java,v 1.2 2007/07/18 07:02:18 sharapov Exp $
 */
public interface CostsAnlServiceLocal extends com.mg.framework.api.DataBusinessObjectService<CostsAnl, Integer> {

	/**
	 * Имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/personnelref/CostsAnl"; //$NON-NLS-1$
	
	/**
	 * Минимальное значение уровня аналитики состава затрат
	 */
	static final short MIN_ANALITICS_LEVEL = 1;
	
	/**
	 * Максимальное значение уровня аналитики состава затрат
	 */
	static final short MAX_ANALITICS_LEVEL = 5;
	
}
