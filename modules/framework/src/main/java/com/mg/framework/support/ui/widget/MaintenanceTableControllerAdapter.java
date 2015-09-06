/*
 * MaintenanceTableControllerAdapter.java
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
package com.mg.framework.support.ui.widget;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.ui.CustomActionDescriptor;


/**
 * Адаптер визуального элемента таблица с возможностью поддержки объектов сущностей
 *
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTableControllerAdapter.java,v 1.8 2008/12/23 09:19:54 safonov Exp $
 */
public interface MaintenanceTableControllerAdapter extends TableControllerAdapter {

  /**
   * добавление объекта сущности
   */
  void add();

  /**
   * изменение объекта сущности
   */
  void edit();

  /**
   * удаление объекта сущности
   */
  void erase();

  /**
   * просмотр объекта сущности
   */
  void view();

  /**
   * копирование сущности
   */
  void clone(boolean deepClone);

  /**
   * обновление модели таблицы
   */
  void refresh();

  /**
   * установка ограничений
   */
  void setRestriction();

  /**
   * получить список настраиваемых действий доступных для данной таблицы
   *
   * @return список настраиваемых действий
   */
  CustomActionDescriptor[] getCustomUserActions();

  /**
   * вызов сервиса печати
   */
  void print();

  /**
   * получить бизнес-компонент для данной таблицы
   *
   * @return бизнес-компонент
   */
  BusinessObjectService getService();

  /**
   * настроить внешний вид таблицы
   */
  void setup();

}
