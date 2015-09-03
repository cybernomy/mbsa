/*
 * WarehouseDocumentBr.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.warehouse.support.ui;

import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm;

/**
 * Контроллер формы списка ордеров
 * 
 * @author leonova
 * @version $Id: WarehouseDocumentBr.java,v 1.7 2008/02/22 11:08:33 alikaev Exp $ 
 */
public class WarehouseDocumentBr extends GoodsDocumentBrowseForm {

	protected final String INIT_QUERY_TEXT = "select %s from StockDocumentHead d %s %s  order by d.DocDate, d.Id ";
	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocumentBrowseForm#createQueryText()
	 */
	@Override
	protected String createQueryText() {
		super.createQueryText();
		
		whereText.append(DatabaseUtils.formatEJBQLObjectRestriction("d.DstStock", restGoodDocument.getDstStockCode(), "dstStockCode", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLObjectRestriction("d.DstMol", restGoodDocument.getDstMolCode(), "dstMolCode", paramsName, paramsValue, false)).
		append(DatabaseUtils.formatEJBQLObjectRestriction("d.Through", restGoodDocument.getThroughCode(), "throughCode", paramsName, paramsValue, false));
		
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList, whereText);	

	}

}
