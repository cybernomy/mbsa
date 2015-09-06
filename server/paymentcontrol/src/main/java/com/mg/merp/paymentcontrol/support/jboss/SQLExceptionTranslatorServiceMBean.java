/*
 * SQLExceptionTranslatorServiceMBean.java
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
package com.mg.merp.paymentcontrol.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис транслятора SQL ИС модуля платежный календарь
 *
 * @author Artem V. Sharapov
 * @version $Id: SQLExceptionTranslatorServiceMBean.java,v 1.1 2007/05/23 06:02:59 sharapov Exp $
 */
public interface SQLExceptionTranslatorServiceMBean extends ServiceMBean {

  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp:paymentcontrol=SQLExceptionTranslator"; //$NON-NLS-1$

}
