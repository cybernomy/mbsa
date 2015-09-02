/*
 * SQLExceptionTranslatorServiceMBean.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.merp.account.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис транслятора SQL ИС модуля бухгалтерия
 * 
 * @author Artem V. Sharapov
 * @version $Id: SQLExceptionTranslatorServiceMBean.java,v 1.1 2009/03/04 12:38:48 sharapov Exp $
 */
public interface SQLExceptionTranslatorServiceMBean extends ServiceMBean {
	
	/**
	 * имя сервиса
	 */
	final static String SERVICE_NAME = "merp:account=SQLExceptionTranslator";
	
}
