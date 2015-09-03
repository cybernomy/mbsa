/*
 * WarehouseRollbackAssembleProductStrategy.java
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
package com.mg.merp.warehouse.generic;

import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.docflow.DocumentSpecItem;
import com.mg.merp.reference.model.CatalogType;
import com.mg.merp.warehouse.HistoryNotFoundException;
import com.mg.merp.warehouse.model.StockBatchHistory;

/**
 * Стратегия отката этапа ДО "Собрать комплект"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseRollbackAssembleProductStrategy.java,v 1.2 2008/04/18 15:18:20 safonov Exp $
 */
public class WarehouseRollbackAssembleProductStrategy extends WarehouseRollbackFactStrategy {

	/**
	 * выпонение отката
	 */
	public void doRollback(DocumentSpecItem dsi) {
		if (CatalogType.SET_OF_GOODS == dsi.getDocSpec().getCatalog().getGoodType()) {
			PersistentManager pm = ServerUtils.getPersistentManager();
			Integer[] historyIds = (Integer[]) dsi.getStateValue();
			if (historyIds != null)
				for (Integer historyId : historyIds) {
					StockBatchHistory stockBatchHist = pm.find(StockBatchHistory.class, historyId);
					performHistory(stockBatchHist);
				}
			
			Integer id = dsi.getData1();
			if (id != null)
				performHistory(pm.find(StockBatchHistory.class, id));
			id = dsi.getData2();
			if (id != null)
				performHistory(pm.find(StockBatchHistory.class, id));
		}
	}

	private void performHistory(StockBatchHistory stockBatchHist) {
		if (stockBatchHist == null)
			throw new HistoryNotFoundException();
		
		switch (stockBatchHist.getKind()) {
		case IN:
			rollbackReceipt(stockBatchHist);
			break;
		case OUT:
			rollbackIssue(stockBatchHist);
			break;
		}
	}
	
}
