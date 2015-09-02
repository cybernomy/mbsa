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
import com.mg.merp.warehouse.model.BillHead;

/**
 * Вспомогательный класс для отображения формы списка счетов
 * 
 * @author leonova
 * @version $Id: BillHeadMaintenanceEJBQLTableModel.java,v 1.2 2009/02/10 14:29:13 safonov Exp $ 
 */
public class BillHeadMaintenanceEJBQLTableModel extends
		WarehouseDocumentMaintenanceEJBQLTableModel {

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.support.ui.WarehouseDocumentMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(BillHead.class, "Comment", "d.Comment", false));
		result.add(new TableEJBQLFieldDef(BillHead.class, "PaymentDate", "d.PaymentDate", false));
		result.add(new TableEJBQLFieldDef(BillHead.class, "PaymentTerm", "d.PaymentTerm", false));		
		result.add(new TableEJBQLFieldDef(BillHead.class, "PaymentSum", "d.PaymentSum", false));			
		result.add(new TableEJBQLFieldDef(BillHead.class, "PlanPaymentDateDoc", "d.PlanPaymentDateDoc", false));
		result.add(new TableEJBQLFieldDef(BillHead.class, "ToPayDocNumber", "d.ToPayDocNumber", false));
		result.add(new TableEJBQLFieldDef(BillHead.class, "ToPayDocDate", "d.ToPayDocDate", false));		
		result.add(new TableEJBQLFieldDef(BillHead.class, "Shipper", "sh.Code", "left join d.Shipper as sh", false));
		result.add(new TableEJBQLFieldDef(BillHead.class, "Consignee", "con.Code", "left join d.Consignee as con", false));
		result.add(new TableEJBQLFieldDef(BillHead.class, "Responsible", "res.Code", "left join d.Responsible as res", false));		
		return result;
	}

}
