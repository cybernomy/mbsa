/* WarehouseProcessReservStrategy.java
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
 * ��������� ���������� ����� �� "�������������� �� ������"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseProcessReservStrategy.java,v 1.6 2008/04/18 15:18:20 safonov Exp $
 */
public class WarehouseProcessReservStrategy extends
		AbstractWarehouseProcessPlanReservStrategy {

	public WarehouseProcessReservStrategy(DocFlowPluginInvokeParams params) {
		super(params);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.generic.AbstractWarehouseProcessPlanReservStrategy#doProcess(com.mg.merp.warehouse.WarehouseProcessDocumentLineData)
	 */
	@Override
	protected void doProcess(WarehouseProcessDocumentLineData docLineData) {
		if (docLineData.getSrcStock() != null)
			makeHistory(docLineData, StockPlanHistoryKind.IN_RESERVE, StockPlanHistoryDirection.IN);
	}

	@Override
	protected void updateQuanAttribute(StockCard stockCard, WarehouseProcessDocumentLineData docLineData,
			StockPlanHistoryDirection direction) {
		stockCard.setReserve(MathUtils.addNullable(stockCard.getReserve(), docLineData.getQuantity1(), Constants.QUANTITY_ROUND_CONTEXT));
		stockCard.setReserve2(MathUtils.addNullable(stockCard.getReserve2(), docLineData.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT));
	}

	@Override
	protected void createQuanAttribute(StockCard stockCard, WarehouseProcessDocumentLineData docLineData,
			StockPlanHistoryDirection direction) {
		stockCard.setReserve(docLineData.getQuantity1());
		stockCard.setReserve2(docLineData.getQuantity2());
	}

}
