/**
 * SchedulerManagerServiceLocator.java
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
package com.mg.merp.scheduler.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.scheduler.support.jboss.SchedulerManagerServiceMBean;

/**
 * Локатор сервиса менеджера планировщика
 * 
 * @author Oleg V. Safonov
 * @version $Id: SchedulerManagerServiceLocator.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
public class SchedulerManagerServiceLocator {
	private static volatile SchedulerManagerServiceMBean instance = null;

	/**
	 * найти сервис менеджера планировщика
	 * 
	 * @return	сервис
	 * @throws ApplicationException	при любых ошибках
	 */
	public static SchedulerManagerServiceMBean locate() {
		if (instance == null)
			try {
				instance = (SchedulerManagerServiceMBean) ServerUtils.createMBeanProxy(SchedulerManagerServiceMBean.class, SchedulerManagerServiceMBean.SERVICE_NAME);
			}
		catch (Exception e) {
			throw new ApplicationException(e);
		}
		return instance;
	}

}
