/*
 * AccountDocFlowPluginServiceMBean.java
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
 * Сервис дополнительных модулей подсистемы "Бухгалтерия" 
 * 
 * @author Oleg V. Safonov
 * @version $Id: AccountDocFlowPluginServiceMBean.java,v 1.1 2006/10/21 11:07:04 safonov Exp $
 */
public interface AccountDocFlowPluginServiceMBean extends ServiceMBean {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:account=DocFlowPluginService"; //$NON-NLS-1$

}
