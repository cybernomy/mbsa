/* StockBatchUniqueStrategy.java
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

import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.warehouse.model.StockCard;

/**
 * Интерфейс стратегии создания складской партии
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: StockBatchCreateStrategy.java,v 1.4 2008/04/18 15:15:53 safonov Exp $
 */
public interface StockBatchCreateStrategy {

  /**
   * Создание складской партии
   *
   * @param docLineData данные о спецификации
   * @param stockCard   КСУ
   * @param params      параметры этапа документо-оборота
   */
  void createStockBatch(WarehouseProcessDocumentLineData docLineData, StockCard stockCard, DocFlowPluginInvokeParams params);

}
