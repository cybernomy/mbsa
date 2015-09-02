/*
 * ConfigurationService.java
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
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.WarehouseConfig;

/**
 * Конфигурация модуля "Управление запасами"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationService.java,v 1.1 2006/12/12 15:29:33 safonov Exp $
 */
public interface ConfigurationService {
	WarehouseConfig load();
}
