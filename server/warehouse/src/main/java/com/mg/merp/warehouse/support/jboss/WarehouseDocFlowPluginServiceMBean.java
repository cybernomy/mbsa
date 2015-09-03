/* WarehouseTransactionPluginServiceMBean.java
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
package com.mg.merp.warehouse.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис дополнительных модулей подсистемы "Склад"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseDocFlowPluginServiceMBean.java,v 1.1 2007/02/22 10:18:23 poroxnenko Exp $ 
 */
public interface WarehouseDocFlowPluginServiceMBean extends ServiceMBean {
	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:warehouse=DocFlowPluginService"; //$NON-NLS-1$
}
