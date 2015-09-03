/*
 * ConfigurationServiceImpl.java
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
package com.mg.merp.warehouse.support;

import com.mg.framework.utils.ServerUtils;
import com.mg.merp.warehouse.ConfigurationService;
import com.mg.merp.warehouse.model.WarehouseConfig;

/**
 * Реализация конфигурации модуля "Управление запасами"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationServiceImpl.java,v 1.1 2006/12/12 15:33:03 safonov Exp $
 */
public class ConfigurationServiceImpl implements ConfigurationService {

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.ConfigurationService#load()
	 */
	public WarehouseConfig load() {
		return ServerUtils.getPersistentManager().find(WarehouseConfig.class, 1);
	}

}
