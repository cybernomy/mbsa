/*
 * AccountEntityInterceptorServiceMBean.java
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
package com.mg.merp.account.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис регистрации перехватчиков на сущности подсистемы "Бухгалтерия"
 * 
 * @author Oleg V. Safonov
 * @version $Id: AccountEntityInterceptorServiceMBean.java,v 1.1 2007/01/16 11:46:37 safonov Exp $
 */
public interface AccountEntityInterceptorServiceMBean extends ServiceMBean {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:account=EntityInterceptorService"; //$NON-NLS-1$

}
