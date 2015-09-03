/*
 * SQLExceptionTranslatorManagerLocator.java
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.jdbc.SQLExceptionTranslatorManager;
import com.mg.framework.service.jboss.SQLExceptionTranslatorManagerServiceMBean;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор менеджера трансляторов SQL ИС в пользовательские исключения
 * 
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslatorManagerLocator.java,v 1.1 2006/11/17 14:28:55 safonov Exp $
 */
public class SQLExceptionTranslatorManagerLocator {
	private static volatile SQLExceptionTranslatorManager instance = null;

	/**
	 * найти сервис менеджера трансляторов
	 * 
	 * @return	сервис
	 * @throws ApplicationException	при любых ошибках
	 */
	public static SQLExceptionTranslatorManager locate() throws ApplicationException {
		if (instance == null)
			try {
				instance = (SQLExceptionTranslatorManager) ServerUtils.createMBeanProxy(SQLExceptionTranslatorManager.class, SQLExceptionTranslatorManagerServiceMBean.SERVICE_NAME); //$NON-NLS-1$
			}
		catch (Exception e) {
			throw new ApplicationException(e);
		}
		return instance;
	}

}
