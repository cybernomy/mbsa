/**
 * ContainerContextFactory.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.support.ui;

import com.mg.framework.api.ui.ContainerContext;
import com.mg.framework.support.ui.ulc.ULCContainerContext;

/**
 * Фабрика среды контейнера выполнения приложения
 *
 * @author Oleg V. Safonov
 * @version $Id: ContainerContextFactory.java,v 1.2 2007/11/09 12:31:23 safonov Exp $
 */
public class ContainerContextFactory {
  private static ContainerContextFactory instance = new ContainerContextFactory();
  private ContainerContext defaultContainerContext = new ULCContainerContext();

  /**
   * получить объект-одиночку
   *
   * @return экземпляр фабрики
   */
  public static ContainerContextFactory getInstance() {
    return instance;
  }

  /**
   * получить среду контейнера выполнения приложения
   *
   * @return среда контейнера выполнения приложения
   */
  public ContainerContext getDefaultContainerContext() {
    return defaultContainerContext;
  }

}
