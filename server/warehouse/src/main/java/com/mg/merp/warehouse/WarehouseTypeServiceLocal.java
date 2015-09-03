/*
 * WarehouseTypeServiceLocal.java
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
package com.mg.merp.warehouse;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.warehouse.model.WarehouseType;

/**
 *  Сервис бизнес-компонент "Тип склада"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseTypeServiceLocal.java,v 1.1 2007/09/17 12:44:56 alikaev Exp $
 */
public interface WarehouseTypeServiceLocal extends DataBusinessObjectService<WarehouseType, Integer> {
	
	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/warehouse/WarehouseType";
	
}
