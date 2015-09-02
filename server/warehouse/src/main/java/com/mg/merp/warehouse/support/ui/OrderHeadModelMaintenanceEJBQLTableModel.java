/*
 * BillHeadMaintenanceEJBQLTableModel.java
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

import java.util.Set;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.warehouse.model.OrderHeadModel;

/**
 * Вспомогательный класс для отображения формы списка образцов заказов
 * 
 * @author leonova
 * @version $Id: OrderHeadModelMaintenanceEJBQLTableModel.java,v 1.1 2006/09/12 10:55:51 leonova Exp $ 
 */
public class OrderHeadModelMaintenanceEJBQLTableModel extends
		WarehouseDocModelMaintenanceEJBQLTableModel {

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.support.ui.WarehouseDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(OrderHeadModel.class, "SummaCurWithDiscount", "dhm.SummaCurWithDiscount", false));
		result.add(new TableEJBQLFieldDef(OrderHeadModel.class, "SummaNatWithDiscount", "dhm.SummaNatWithDiscount", false));
		result.add(new TableEJBQLFieldDef(OrderHeadModel.class, "DiscountOnDoc", "dhm.DiscountOnDoc", false));
		result.add(new TableEJBQLFieldDef(OrderHeadModel.class, "DiscountOnLine", "dhm.DiscountOnLine", false));		
		return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, service);

	}

}
