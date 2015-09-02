/*
 * DocFlowPluginFactoryServiceMBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.docflow.support.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.merp.docflow.DocFlowPluginFactoryManager;

/**
 * Сервис менеджера фабрик реализаций этапов ДО
 * 
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginFactoryServiceMBean.java,v 1.3 2007/01/29 14:10:02 safonov Exp $
 */
public interface DocFlowPluginFactoryServiceMBean extends DocFlowPluginFactoryManager,
		ServiceMBean {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:service=DocFlowPluginFactoryManagerService"; //$NON-NLS-1$

}
