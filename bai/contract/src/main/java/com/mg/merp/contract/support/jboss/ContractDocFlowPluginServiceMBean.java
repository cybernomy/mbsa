/*
 * ContractDocFlowPluginServiceMBean.java
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
package com.mg.merp.contract.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис дополнительных модулей подсистемы "Контракты"
 *
 * @author Artem V. Sharapov
 * @version $Id: ContractDocFlowPluginServiceMBean.java,v 1.1 2007/03/07 12:26:05 sharapov Exp $
 */
public interface ContractDocFlowPluginServiceMBean extends ServiceMBean {

  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:contract=DocFlowPluginService"; //$NON-NLS-1$

}
