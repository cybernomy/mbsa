/* AbstractWarehouseRollbackPlanReservStrategy.java
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
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.warehouse.HistoryNotFoundException;
import com.mg.merp.warehouse.WarehousePlanHistoryServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseRollbackStrategy;
import com.mg.merp.warehouse.model.StockPlanHistory;

/**
 * Абстрактный класс-предок для классов-стратегий откатов операций складского планирования и
 * резервирования
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: AbstractWarehouseRollbackPlanReservStrategy.java,v 1.7 2008/04/18 15:18:19 safonov Exp $ 
 */
public abstract class AbstractWarehouseRollbackPlanReservStrategy implements
		WarehouseRollbackStrategy {

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.generic.WarehouseRollbackStrategy#doRollback(com.mg.merp.document.model.DocSpec)
	 */
	public void rollback(WarehouseProcessDocumentLineData docLineData) {
		PersistentManager pm = ServerUtils.getPersistentManager();
		
		Integer id = docLineData.getDocumentSpecItem().getData2();
		if (id != null)
			doRollback(pm.find(StockPlanHistory.class, id));
		id = docLineData.getDocumentSpecItem().getData1();
		if (id != null)
			doRollback(pm.find(StockPlanHistory.class, id));
	}
	
	private void doRollback(StockPlanHistory history) {
		if (history == null)
			throw new HistoryNotFoundException();

		WarehousePlanHistoryServiceLocal wphServ = (WarehousePlanHistoryServiceLocal) ApplicationDictionaryLocator.locate()
				.getBusinessService(WarehousePlanHistoryServiceLocal.LOCAL_SERVICE_NAME);
		wphServ.erase(history);
		rollbackOperation(history);
	}

	/**
	 * реализация операции отката
	 * 
	 * @param history	история
	 */
	protected abstract void rollbackOperation(StockPlanHistory history);

}
