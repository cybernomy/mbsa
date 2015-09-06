/*
 * UserActionInterceptorManagerServiceMBean.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.service.jboss;

import com.mg.framework.api.UserActionInterceptorManager;

import org.jboss.system.ServiceMBean;

/**
 * Сервис менеджера перехватчиков действий интерактивной поддержки (добавление, изменение,
 * копирование, просмотр) бизнес-компонентов
 *
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptorManagerServiceMBean.java,v 1.2 2006/10/26 13:27:52 safonov Exp
 *          $
 */
public interface UserActionInterceptorManagerServiceMBean extends
    UserActionInterceptorManager, ServiceMBean {
  /**
   * наименование сервиса
   */
  final static String SERVICE_NAME = "merp:service=UserActionInterceptorManagerService";
}
