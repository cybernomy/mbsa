/*
 * SettlementDocFlowPluginServiceMBean.java
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
package com.mg.merp.settlement.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис дополнительных модулей подсистемы "Расчеты с партнерами"
 *
 * @author Artem V. Sharapov
 * @version $Id: SettlementDocFlowPluginServiceMBean.java,v 1.1 2007/03/19 15:05:29 sharapov Exp $
 */
public interface SettlementDocFlowPluginServiceMBean extends ServiceMBean {

  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:settlement=DocFlowPluginService"; //$NON-NLS-1$

}
