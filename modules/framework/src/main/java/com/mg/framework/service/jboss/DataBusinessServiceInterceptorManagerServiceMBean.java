/**
 * DataBusinessServiceInterceptorManagerServiceMBean.java
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
package com.mg.framework.service.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.framework.api.DataBusinessServiceInterceptorManager;

/**
 * Сервис менеджера перехватчиков действий бизнес-компонентов управляющих данными
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessServiceInterceptorManagerServiceMBean.java,v 1.1 2007/12/13 13:07:20 safonov Exp $
 */
public interface DataBusinessServiceInterceptorManagerServiceMBean extends
		DataBusinessServiceInterceptorManager, ServiceMBean {
	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp:service=DataBusinessServiceInterceptorManagerService";
}
