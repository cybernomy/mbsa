/*
 * DocFlowPluginFactoryManagerServiceLocator.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.docflow.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocFlowPluginFactoryManager;

/**
 * Локатор менеджера фабрик реализаций этапов ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginFactoryManagerServiceLocator.java,v 1.2 2007/01/29 14:09:17 safonov
 *          Exp $
 */
public class DocFlowPluginFactoryManagerServiceLocator {
  private static volatile DocFlowPluginFactoryManager instance = null;

  /**
   * поиск сервиса
   *
   * @return сервис
   */
  public static DocFlowPluginFactoryManager locate() {
    if (instance == null)
      try {
        instance = (DocFlowPluginFactoryManager) ServerUtils.createMBeanProxy(DocFlowPluginFactoryManager.class, "merp:service=DocFlowPluginFactoryManagerService"); //$NON-NLS-1$
      } catch (Exception e) {
        throw new ApplicationException(e);
      }
    return instance;
  }
}
