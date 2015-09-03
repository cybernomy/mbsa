/* WarehouseRollbackPlanWithdrawalStrategy.java
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
 * Стратегия отката этапа ДО "Снятие с плана движения по складу"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseRollbackPlanWithdrawalStrategy.java,v 1.3 2008/04/18 15:18:20 safonov Exp $
 */
public class WarehouseRollbackPlanWithdrawalStrategy extends
		AbstractWarehouseRollbackPlanReservStrategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.warehouse.generic.AbstractWarehouseRollbackPlanReservStrategy#rollbackOperation(com.mg.merp.warehouse.model.StockPlanHistory)
	 */
	@Override
	public void rollbackOperation(StockPlanHistory history) {
		StockCard stockCard = history.getStockCard();
		switch (history.getDirection()) {
		case IN:
			stockCard.setPlanIn(MathUtils.addNullable(stockCard.getPlanIn(), history.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT));
			stockCard.setPlanIn2(MathUtils.addNullable(stockCard.getPlanIn2(), history.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT));
			break;
		case OUT:
			stockCard.setPlanOut(MathUtils.addNullable(stockCard.getPlanOut(), history.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT));
			stockCard.setPlanOut2(MathUtils.addNullable(stockCard.getPlanOut2(), history.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT));
			break;
		}				
	}

}
