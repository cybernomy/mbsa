/*
 * FinanceEntityInterceptorServiceMBean.java
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
 * Сервис регистрации перхватчиков моделей подсистемы "Финансы"
 *
 * @author Oleg V. Safonov
 * @version $Id: FinanceEntityInterceptorServiceMBean.java,v 1.1 2007/01/16 14:44:05 safonov Exp $
 */
public interface FinanceEntityInterceptorServiceMBean extends ServiceMBean {
  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:finance=EntityInterceptorService"; //$NON-NLS-1$

}
