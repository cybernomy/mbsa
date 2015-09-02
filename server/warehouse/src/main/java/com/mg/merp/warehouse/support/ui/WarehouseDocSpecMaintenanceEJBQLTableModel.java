/*
 * WarehouseDocSpecMaintenanceEJBQLTableModel.java
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
package com.mg.merp.warehouse.support.ui;

import java.util.Set;

import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.merp.document.generic.ui.GoodsDocSpecMaintenanceEJBQLTableModel;
import com.mg.merp.document.model.DocSpec;

/**
 * ¬спомогательный класс дл€ отображени€ формы спецификации складских документов
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: WarehouseDocSpecMaintenanceEJBQLTableModel.java,v 1.4 2008/12/25 10:26:25 safonov Exp $ 
 */
public class WarehouseDocSpecMaintenanceEJBQLTableModel extends GoodsDocSpecMaintenanceEJBQLTableModel {

	/* (non-Javadoc)
	 * @see com.mg.merp.warehouse.support.ui.GoodsDocSpecMaintenanceEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Price", "ds.Price", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(DocSpec.class, "Summa", "ds.Summa", false)); //$NON-NLS-1$ //$NON-NLS-2$
		if(isAddSrcStockFieldDef())
			result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcStock", "ss.Code", "left join ds.SrcStock as ss", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if(isAddSrcMolFieldDef())
			result.add(new TableEJBQLFieldDef(DocSpec.class, "SrcMol", "sm.Code", "left join ds.SrcMol as sm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		result.add(new TableEJBQLFieldDef(DocSpec.class, "DstStock", "dst.Code", "left join ds.DstStock as dst", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		result.add(new TableEJBQLFieldDef(DocSpec.class, "DstMol", "dm.Code", "left join ds.DstMol as dm", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		result.add(new TableEJBQLFieldDef(DocSpec.class, "BestBefore", "ds.BestBefore", false)); //$NON-NLS-1$ //$NON-NLS-2$
		return result;
	}

	/**
	 * ѕризнак добавлени€ описател€ пол€ "склад-источник" в список описателей полей, отображаемых в таблице
	 * @return <code>true</code> - описатель пол€ "склад-источник" будет добавлен в список описателей полей, отображаемых в таблице
	 */
	protected boolean isAddSrcStockFieldDef() {
		return true;
	}

	/**
	 * ѕризнак добавлени€ описател€ пол€ "ћќЋ склада-источника" в список описателей полей, отображаемых в таблице
	 * @return <code>true</code> - описатель пол€ "ћќЋ склада-источника" будет добавлен в список описателей полей, отображаемых в таблице
	 */
	protected boolean isAddSrcMolFieldDef() {
		return true;
	}

}
