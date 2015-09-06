/*
 * WarehouseTransactionDayServiceLocal.java
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

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.warehouse.model.WarehouseTransactionDay;

import java.io.Serializable;

/**
 * Сервис бизнес-компонента "Операционные дни"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseTransactionDayServiceLocal.java,v 1.1 2007/11/29 08:37:13 alikaev Exp $
 */
public interface WarehouseTransactionDayServiceLocal extends DataBusinessObjectService<WarehouseTransactionDay, Integer> {

  final static String SERVICE_NAME = "merp/warehouse/WarehouseTransactionDay";

  /**
   * Открыть операционные дни
   *
   * @param periods - набор операционных дней
   */
  void openTransactionDay(Serializable[] dayIds);

  /**
   * Закрыть операционные дни
   *
   * @param periods - набор операционных дней
   */
  void closeTransactionDay(Serializable[] dayIds);

}
