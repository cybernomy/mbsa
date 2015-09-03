/*
 * DocumentDocFlowPluginServiceMBean.java
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
package com.mg.merp.document.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис дополнительных модулей подсистемы "Документы"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocumentDocFlowPluginServiceMBean.java,v 1.1 2006/10/21 10:56:26 safonov Exp $
 */
public interface DocumentDocFlowPluginServiceMBean extends ServiceMBean {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:document=DocFlowPluginService"; //$NON-NLS-1$

}
