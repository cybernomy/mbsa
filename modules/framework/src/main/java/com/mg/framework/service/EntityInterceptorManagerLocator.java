/*
 * EntityInterceptorManagerLocator.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.EntityInterceptorManager;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор менеджера перехватчиков действий над объектами сущностями
 *
 * @author Oleg V. Safonov
 * @version $Id: EntityInterceptorManagerLocator.java,v 1.1 2006/08/04 12:33:51 safonov Exp $
 */
public class EntityInterceptorManagerLocator {
  private static volatile EntityInterceptorManager instance = null;

  /**
   * получить менеджер
   *
   * @return менеджер
   */
  public static EntityInterceptorManager locate() {
    if (instance == null)
      try {
        instance = (EntityInterceptorManager) ServerUtils.createMBeanProxy(EntityInterceptorManager.class, "merp:service=EntityInterceptorManagerService");
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    return instance;
  }
}
