/*
 * FinanceDocFlowPluginServiceMBean.java
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
package com.mg.merp.finance.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис дополнительных модулей подсистемы "Финансы"
 *
 * @author Oleg V. Safonov
 * @version $Id: FinanceDocFlowPluginServiceMBean.java,v 1.1 2006/10/21 10:59:21 safonov Exp $
 */
public interface FinanceDocFlowPluginServiceMBean extends ServiceMBean {
  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:finance=DocFlowPluginService"; //$NON-NLS-1$

}
