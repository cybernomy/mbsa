/*
 * FacturaDocFlowPluginServiceMBean.java
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
package com.mg.merp.factura.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис дополнительных модулей подсистемы "Счета-фактуры" 
 * 
 * @author Artem V. Sharapov
 * @version $Id: FacturaDocFlowPluginServiceMBean.java,v 1.1 2009/03/16 14:30:34 sharapov Exp $
 */
public interface FacturaDocFlowPluginServiceMBean extends ServiceMBean {
	
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:factura=DocFlowPluginService"; //$NON-NLS-1$

}
