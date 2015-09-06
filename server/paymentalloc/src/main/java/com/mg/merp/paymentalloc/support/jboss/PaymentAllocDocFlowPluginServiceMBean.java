/*
 * PaymentAllocDocFlowPluginServiceMBean.java
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
package com.mg.merp.paymentalloc.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис дополнительных модулей подсистемы "Журнал платежей"
 *
 * @author Denis V. Arychkov
 * @version $Id: PaymentAllocDocFlowPluginServiceMBean.java,v 1.1 2007/05/04 06:57:37 arychkov Exp
 *          $
 */
public interface PaymentAllocDocFlowPluginServiceMBean extends ServiceMBean {

  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:paymentalloc=DocFlowPluginService"; //$NON-NLS-1$

}
