/*
 * DocFlowPluginFactoryManager.java
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
package com.mg.merp.docflow;

/**
 * Менеджер фабрик реализаций этапов ДО
 *
 * @author Oleg V. Safonov
 * @version $Id: DocFlowPluginFactoryManager.java,v 1.2 2007/01/29 13:55:40 safonov Exp $
 */
public interface DocFlowPluginFactoryManager {

  /**
   * регистрация фабрики
   *
   * @param pluginFactory фабрика
   */
  void registerPluginFactory(DocFlowPluginFactory pluginFactory);

  /**
   * удаление фабрики
   *
   * @param pluginFactory фабрика
   */
  void unregisterPluginFactory(DocFlowPluginFactory pluginFactory);

  /**
   * поиск фабрики по идентификатору
   *
   * @param identifier идентификатор
   * @return фабрика
   * @throws PluginNotImplementedException если фабрика не найдена
   */
  DocFlowPluginFactory findPluginFactory(int identifier) throws PluginNotImplementedException;
}
