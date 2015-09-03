/*
 * OrderMaintenanceEJBQLTableModel.java
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
import com.mg.merp.warehouse.model.OrderHead;

/**
 * Вспомогательный класс для отображения формы списка заказов
 * 
 * @author leonova
 * @version $Id: OrderMaintenanceEJBQLTableModel.java,v 1.3 2009/02/10 14:29:13 safonov Exp $
 */
public class OrderMaintenanceEJBQLTableModel extends
		WarehouseDocumentMaintenanceEJBQLTableModel {

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.support.ui.WarehouseDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();				
		result.add(new TableEJBQLFieldDef(OrderHead.class, "Responsible", "r.Code", "left join d.Responsible as r", false));
		return result;

	}
	
}
