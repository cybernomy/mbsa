/*
 * WarehouseServiceLocal.java
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
package com.mg.merp.warehouse;

import com.mg.merp.reference.model.Employees;
import com.mg.merp.reference.model.OrgUnit;
import com.mg.merp.warehouse.model.Warehouse;

import java.io.Serializable;
import java.util.Date;

/**
 * Бизнес-компонент "Склады"
 *
 * @author leonova
 * @version $Id: WarehouseServiceLocal.java,v 1.6 2007/11/29 08:37:13 alikaev Exp $
 */
public interface WarehouseServiceLocal extends
    com.mg.framework.api.DataBusinessObjectService<Warehouse, Integer> {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/warehouse/Warehouse";

  /**
   * Добавляет запись в таблицу WH_WAREHOUSE, соответствующую подразделению
   *
   * @param orgUnit подразделение
   * @return созданный склад
   */
  Warehouse addWarehouse(OrgUnit orgUnit);

  /**
   * Удаляет запись в таблице WH_WAREHOUSE, соответствующую подразделению с идентификатором id
   *
   * @param id идентификатор записи
   */
  void eraseWarehouse(int id);

  /**
   * Получить МОЛ по умолчанию для склада
   *
   * @param warehouse - склад
   * @return МОЛ(сотрудник) по умолчанию для склада, или <code>null</code> если не найден
   */
  Employees getWarehouseDefaultMOL(Warehouse warehouse);

  /**
   * Открыть склад
   *
   * @param warehouseIds - набор складов
   */
  void openWarehouse(Serializable[] warehouseIds);

  /**
   * Закрыть склад
   *
   * @param warehouseIds   - набор складов
   * @param closedDateTill - закрыть склад по число
   */
  void closeWarehouse(Serializable[] warehouseIds, Date closedDateTill);

}
