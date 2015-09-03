/*
 * InvoiceSpecMaintenanceEJBQLTableModel.java
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
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.warehouse.model.InvoiceSpec;

/**
 * Вспомогательный класс для отображения формы спецификации накладных
 * 
 * @author leonova
 * @version $Id: InvoiceSpecMaintenanceEJBQLTableModel.java,v 1.4 2008/12/25 10:26:25 safonov Exp $ 
 */
public class InvoiceSpecMaintenanceEJBQLTableModel extends
		WarehouseDocSpecMaintenanceEJBQLTableModel {

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.support.ui.WarehouseDocSpecMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Price1", "ds.Price1", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa1", "ds.Summa1", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "CountryOfOrigin", "co.CName", "left join ds.CountryOfOrigin co", false));
		result.add(new TableEJBQLFieldDef(DocSpec.class, "CustomsDeclaration", "cd.Number", "left join ds.CustomsDeclaration cd", false));
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDocSpecModelName()
	 */
	@Override
	protected String getDocSpecModelName() {
		return InvoiceSpec.class.getName();
	}

}
