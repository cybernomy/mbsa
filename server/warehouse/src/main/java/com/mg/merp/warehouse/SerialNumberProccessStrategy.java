/*
 * SerialNumberProccessStrategy.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import java.util.List;

import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;

/**
 * Стратегия обработки/отката серийных номеров
 * 
 * @author Artem V. Sharapov
 * @version $Id: SerialNumberProccessStrategy.java,v 1.1 2008/05/30 12:39:54 sharapov Exp $
 */
public interface SerialNumberProccessStrategy {
	
	/**
	 * Обработка серийных номеров при приходовании товара на склад
	 * @param docLineData - данные по спецификации для отработки
	 * @param stockBatch - партия
	 * @param processListener - слушатель складского процессора
	 */
	void proccessOnReceipt(WarehouseProcessDocumentLineData docLineData, StockBatch stockBatch, WarehouseProcessListener processListener);
			
	/**
	 * Обработать серийные номера при списани товара со склада
	 * @param docLineData - данные по спецификации для отработки
	 * @param stockBatches - список партий подлежащих списанию
	 * @param processListener - слушатель складского процессора
	 */
	void proccessOnIssue(WarehouseProcessDocumentLineData docLineData, List<StockBatch> stockBatches, WarehouseProcessListener processListener);
	
	/**
	 * Откат обработки серийных номеров при приходовании товара на склад
	 * @param history - история
	 */
	void rollbackOnReceipt(StockBatchHistory history);
	
	/**
	 * Откат обработки серийных номеров при списании товара со склада
	 * @param history - история
	 */
	void rollbackOnIssue(StockBatchHistory history);
	
}
