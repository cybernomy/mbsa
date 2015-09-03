/*
 * DefaultSQLExceptionTranslatorService.java
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
package com.mg.framework.service.jboss;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.jdbc.SQLExceptionTranslator;
import com.mg.framework.service.DefaultSQLExceptionTranslatorImpl;
import com.mg.framework.service.SQLExceptionTranslatorManagerLocator;

/**
 * Реализация сервиса регистрации стандартного обработчика SQL ИС
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultSQLExceptionTranslatorService.java,v 1.1 2006/11/17 14:31:44 safonov Exp $
 */
public class DefaultSQLExceptionTranslatorService extends ServiceMBeanSupport
		implements DefaultSQLExceptionTranslatorServiceMBean {
	private SQLExceptionTranslator defaultTranslator = new DefaultSQLExceptionTranslatorImpl();

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#startService()
	 */
	@Override
	protected void startService() throws Exception {
		super.startService();
		SQLExceptionTranslatorManagerLocator.locate().registerTranslator(defaultTranslator);
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#stopService()
	 */
	@Override
	protected void stopService() throws Exception {
		SQLExceptionTranslatorManagerLocator.locate().unregisterTranslator(defaultTranslator);
		super.stopService();
	}

}
