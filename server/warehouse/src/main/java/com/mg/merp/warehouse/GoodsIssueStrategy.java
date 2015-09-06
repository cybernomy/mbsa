/**
 * GoodsIssueStrategy.java.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.StockBatch;

import java.math.BigDecimal;
import java.util.List;

/**
 * Стратегия отпуска товаров со склада
 *
 * @author Oleg V. Safonov
 * @version $Id: GoodsIssueStrategy.java,v 1.1 2007/09/04 13:07:02 safonov Exp $
 */
public interface GoodsIssueStrategy {

  /**
   * выполнить отпуск со склада
   */
  void doIssue();

  /**
   * получить список партий товара в порядке их списания, соответствующего стратегии
   *
   * @return список партий товара
   */
  List<StockBatch> getBatchesByOrder();

  /**
   * уведомить о выполнении отпуска со склада
   */
  void notifyComplete();

  /**
   * уведомить об отмене отпуска со склада
   */
  void notifyCancel();

  /**
   * получить количество доступное для отпуска
   *
   * @return количество доступное для отпуска
   */
  BigDecimal getAvailable();

}
