/* WarehousePlanHistoryServiceLocal.java
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
import com.mg.merp.warehouse.model.StockPlanHistory;

/**
 * Сервис бизнес-компонента "История планового движения и резервирования по партии"
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehousePlanHistoryServiceLocal.java,v 1.1 2007/03/07 10:51:09 poroxnenko Exp $
 */
public interface WarehousePlanHistoryServiceLocal extends
    DataBusinessObjectService<StockPlanHistory, Integer> {

  /**
   * Локальное имя сервиса
   */
  static final String LOCAL_SERVICE_NAME = "merp/warehouse/WarehousePlanHistory";
}
