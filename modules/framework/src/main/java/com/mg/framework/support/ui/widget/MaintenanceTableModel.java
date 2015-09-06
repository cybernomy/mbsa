/*
 * AbstractMaintenanceTableModel.java
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
package com.mg.framework.support.ui.widget;

import com.mg.framework.api.DataBusinessObjectService;

import java.io.Serializable;
import java.util.List;

/**
 * Модель таблицы поддержки бизнес-компонента
 *
 * @author Oleg V. Safonov
 * @version $Id: MaintenanceTableModel.java,v 1.4 2009/02/09 14:10:24 safonov Exp $
 */
public interface MaintenanceTableModel extends TableModel {

  /**
   * Return primary key of current row
   *
   * @return primary key
   */
  Serializable[] getSelectedPrimaryKeys();

  /**
   * устновка текущего мастера в модели
   */
  void setCurrentMaster(Serializable masterKey);

  /**
   * установить бизнес-компонент обслуживающий данную модель
   *
   * @param service бизнес-компонент
   */
  void setService(DataBusinessObjectService<?, ?> service);

  /**
   * получить информацию о столбцах таблицы
   *
   * @return описатели информации о столбцах
   */
  List<TableColumnInfo> getColumnInfos();

  /**
   * установить список видимых столбцов таблицы
   *
   * @param visibleColumns содержит названия столбцов для отображения
   */
  void setVisibleColumns(List<String> visibleColumns);

  /**
   * получить значение первичного ключа из кортежа
   *
   * @param row порядковый номер кортежа
   * @return значение первичного ключа
   */
  Serializable getPrimaryKey(int row);

}
