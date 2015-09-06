/**
 * TaskInterceptorServiceMBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.scheduler.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * JMX сервис регистрация перехватчиков на сущность "задачи планировщика"
 *
 * @author Oleg V. Safonov
 * @version $Id: TaskInterceptorServiceMBean.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
public interface TaskInterceptorServiceMBean extends ServiceMBean {
  /**
   * имя сервиса
   */
  final static String SERVICE_NAME = "merp:scheduler=TaskInterceptorService";
}
