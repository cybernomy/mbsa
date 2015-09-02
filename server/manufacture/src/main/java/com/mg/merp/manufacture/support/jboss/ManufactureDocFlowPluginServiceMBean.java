/*
 * ManufactureDocFlowPluginServiceMBean.java
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
package com.mg.merp.manufacture.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис регистрации этапов ДО подсистемы "Производство"
 * 
 * @author Oleg V. Safonov
 * @version $Id: ManufactureDocFlowPluginServiceMBean.java,v 1.1 2007/08/06 12:44:11 safonov Exp $
 */
public interface ManufactureDocFlowPluginServiceMBean extends ServiceMBean {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:manufacture=DocFlowPluginService"; //$NON-NLS-1$
}
