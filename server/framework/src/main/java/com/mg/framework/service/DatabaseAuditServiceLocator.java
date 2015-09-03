/**
 * DatabaseAuditServiceLocator.java
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.service.jboss.DatabaseAuditServiceMBean;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор сервиса аудита хранилища данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: DatabaseAuditServiceLocator.java,v 1.1 2007/10/19 06:29:22 safonov Exp $
 */
public class DatabaseAuditServiceLocator {
	private static volatile DatabaseAuditServiceMBean instance = null;

	/**
	 * найти сервис менеджера пользовательских полей
	 * 
	 * @return	сервис
	 * @throws ApplicationException	при любых ошибках
	 */
	public static DatabaseAuditServiceMBean locate() {
		if (instance == null)
			try {
				instance = (DatabaseAuditServiceMBean) ServerUtils.createMBeanProxy(DatabaseAuditServiceMBean.class, DatabaseAuditServiceMBean.SERVICE_NAME);
			}
		catch (Exception e) {
			throw new ApplicationException(e);
		}
		return instance;
	}

}
