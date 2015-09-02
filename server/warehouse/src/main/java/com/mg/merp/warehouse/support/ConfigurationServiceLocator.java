/*
 * ConfigurationServiceLocator.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.warehouse.ConfigurationService;
import com.mg.merp.warehouse.support.jboss.ConfigurationServiceMBean;

/**
 * Локатор сервиса реализации конфигурации модуля "Управление запасами"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationServiceLocator.java,v 1.1 2006/12/12 15:33:03 safonov Exp $
 */
public class ConfigurationServiceLocator {
	private static volatile ConfigurationService instance = null;

	/**
	 * найти сервис конфигурации
	 * 
	 * @return	сервис
	 * @throws ApplicationException	при любых ошибках
	 */
	public static ConfigurationService locate() throws ApplicationException {
		if (instance == null)
			try {
				instance = (ConfigurationService) ServerUtils.createMBeanProxy(ConfigurationService.class, ConfigurationServiceMBean.SERVICE_NAME); //$NON-NLS-1$
			}
		catch (Exception e) {
			throw new ApplicationException(e);
		}
		return instance;
	}

}
