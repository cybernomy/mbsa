/* WarehouseRollbackReservWithdrawalStrategy.java
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

import com.mg.framework.api.math.Constants;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.StockPlanHistory;

/**
 * Стратегия отката этапа ДО "Снятие с резерва по складу"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseRollbackReservWithdrawalStrategy.java,v 1.4 2008/04/18 15:18:19 safonov Exp $ 
 */
public class WarehouseRollbackReservWithdrawalStrategy extends
		AbstractWarehouseRollbackPlanReservStrategy {

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.generic.AbstractWarehouseRollbackPlanReservStrategy#rollbackOperation(com.mg.merp.warehouse.model.StockPlanHistory)
	 */
	@Override
	public void rollbackOperation(StockPlanHistory history) {
		StockCard stockCard = history.getStockCard();
		stockCard.setReserve(MathUtils.addNullable(stockCard.getReserve(), history.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT));
		stockCard.setReserve2(MathUtils.addNullable(stockCard.getReserve2(), history.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT));
	}

}
