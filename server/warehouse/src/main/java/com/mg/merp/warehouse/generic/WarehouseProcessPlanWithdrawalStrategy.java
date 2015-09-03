/* WarehouseProcessPlanWithdrawalStrategy.java
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
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.StockPlanHistoryDirection;
import com.mg.merp.warehouse.model.StockPlanHistoryKind;

/**
 * Стратегия реализации этапа ДО "Снятие с плана движения по складу"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseProcessPlanWithdrawalStrategy.java,v 1.5 2008/04/18 15:18:20 safonov Exp $
 */
public class WarehouseProcessPlanWithdrawalStrategy extends
		AbstractWarehouseProcessPlanReservStrategy {

	public WarehouseProcessPlanWithdrawalStrategy(
			DocFlowPluginInvokeParams params) {
		super(params);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.generic.AbstractWarehouseProcessPlanReservStrategy#doProcess(com.mg.merp.warehouse.WarehouseProcessDocumentLineData)
	 */
	@Override
	protected void doProcess(WarehouseProcessDocumentLineData docLineData) {
		//receipt
		if (docLineData.getDstStock() != null)
			makeHistory(docLineData, StockPlanHistoryKind.OUT_PLAN, MathUtils.compareToZero(docLineData.getQuantity1()) < 0 ? StockPlanHistoryDirection.OUT : StockPlanHistoryDirection.IN);
		
		//issue
		if (docLineData.getSrcStock() != null)
			makeHistory(docLineData, StockPlanHistoryKind.OUT_PLAN, MathUtils.compareToZero(docLineData.getQuantity1()) < 0 ? StockPlanHistoryDirection.IN : StockPlanHistoryDirection.OUT);
	}

	@Override
	protected void updateQuanAttribute(StockCard stockCard, WarehouseProcessDocumentLineData docLineData,
			StockPlanHistoryDirection direction) {
		switch (direction) {
		case IN:
			stockCard.setPlanIn(MathUtils.subtractNullable(stockCard.getPlanIn(), docLineData.getQuantity1(), Constants.QUANTITY_ROUND_CONTEXT)); 
			stockCard.setPlanIn2(MathUtils.subtractNullable(stockCard.getPlanIn2(), docLineData.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT));
			break;
		case OUT:
			stockCard.setPlanOut(MathUtils.subtractNullable(stockCard.getPlanOut(), docLineData.getQuantity1(), Constants.QUANTITY_ROUND_CONTEXT)); 
			stockCard.setPlanOut2(MathUtils.subtractNullable(stockCard.getPlanOut2(), docLineData.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT));
			break;
		}
	}

	@Override
	protected void createQuanAttribute(StockCard stockCard, WarehouseProcessDocumentLineData docLineData,
			StockPlanHistoryDirection direction) {
		switch (direction) {
		case IN:
			stockCard.setPlanIn(docLineData.getQuantity1());
			stockCard.setPlanIn2(docLineData.getQuantity2());
			break;
		case OUT:
			stockCard.setPlanOut(docLineData.getQuantity1());
			stockCard.setPlanOut2(docLineData.getQuantity2());
			break;
		}
	}

}
