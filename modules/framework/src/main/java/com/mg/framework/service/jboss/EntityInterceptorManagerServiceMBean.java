/*
 * EntityInterceptorManagerServiceMBean.java
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

import com.mg.framework.api.EntityInterceptorManager;

import org.jboss.system.ServiceMBean;

/**
 * Сервис менеджера перехватчиков действий над объектами сущностями
 *
 * @author Oleg V. Safonov
 * @version $Id: EntityInterceptorManagerServiceMBean.java,v 1.2 2006/08/04 13:27:52 safonov Exp $
 */
public interface EntityInterceptorManagerServiceMBean extends
    EntityInterceptorManager, ServiceMBean {
  final static String SERVICE_NAME = "merp:service=EntityInterceptorManagerService";
}
