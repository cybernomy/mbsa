/*
 * ConfigurationServiceMBean.java
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
package com.mg.merp.warehouse.support.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.merp.warehouse.ConfigurationService;

/**
 * Сервис конфигурации модуля "Управление запасами" для контейнера JBoss
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConfigurationServiceMBean.java,v 1.1 2006/12/12 15:31:10 safonov Exp $
 */
public interface ConfigurationServiceMBean extends ConfigurationService,
		ServiceMBean {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:warehouse=ConfigurationService";

}
