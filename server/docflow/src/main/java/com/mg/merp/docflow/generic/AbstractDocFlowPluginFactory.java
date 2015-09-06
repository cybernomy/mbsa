/*
 * AbstractDocFlowPluginFactory.java
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
package com.mg.merp.docflow.generic;

import com.mg.merp.docflow.DocFlowPlugin;
import com.mg.merp.docflow.DocFlowPluginFactory;

/**
 * Абстрактная реализация фабрики подключаемых модулей машины документооборота для выполнения
 * этапов
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractDocFlowPluginFactory.java,v 1.2 2006/10/21 12:29:15 safonov Exp $
 */
public abstract class AbstractDocFlowPluginFactory implements DocFlowPluginFactory {

  /**
   * реализация создания подключаемого модуля, должен быть переопределен в классах наследниках
   *
   * @return подключаемый модуль
   */
  protected abstract DocFlowPlugin doCreatePlugin();

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactory#createPlugin()
   */
  public final DocFlowPlugin createPlugin() {
    return doCreatePlugin();
  }

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactory#getIdentifier()
   */
  public abstract int getIdentifier();

  /* (non-Javadoc)
   * @see com.mg.merp.docflow.DocFlowPluginFactory#getName()
   */
  public abstract String getName();

}
