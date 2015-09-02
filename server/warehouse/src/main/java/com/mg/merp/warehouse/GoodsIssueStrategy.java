/**
 * GoodsIssueStrategy.java.java
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

import java.math.BigDecimal;
import java.util.List;

import com.mg.merp.warehouse.model.StockBatch;

/**
 * —тратеги€ отпуска товаров со склада
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
	 * получить список партий товара в пор€дке их списани€, соответствующего стратегии
	 * 
	 * @return	список партий товара
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
	 * получить количество доступное дл€ отпуска
	 * 
	 * @return	количество доступное дл€ отпуска
	 */
	BigDecimal getAvailable();
	
}
