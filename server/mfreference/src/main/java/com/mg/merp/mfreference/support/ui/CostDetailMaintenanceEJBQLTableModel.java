/*
 * CostDetailMaintenanceEJBQLTableModel.java
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
package com.mg.merp.mfreference.support.ui;

import java.util.Set;

import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.mfreference.model.CostDetail;
import com.mg.merp.mfreference.model.CostDetailLine;

/**
 * @author leonova
 * @version $Id: CostDetailMaintenanceEJBQLTableModel.java,v 1.2 2007/07/30 10:24:19 safonov Exp $ 
 */
public class CostDetailMaintenanceEJBQLTableModel extends DefaultEJBQLTableModel {
	//private final String INIT_QUERY_TEXT = "select cdl.Id, cur.Code, cc.Code, cdl.Summa from CostDetailLine cdl left join cdl.Currency as cur left join cdl.CostCategories as cc where cdl.CostDetail = :costDetail";
	private final String INIT_QUERY_TEXT = "select %s from CostDetailLine cdl %s where cdl.CostDetail = :costDetail";
	private CostDetail costDetail;
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(CostDetailLine.class, "Id", "cdl.Id", true));
		result.add(new TableEJBQLFieldDef(CostDetailLine.class, "Currency", "cur.Code", "left join cdl.Currency as cur", false));
		result.add(new TableEJBQLFieldDef(CostDetailLine.class, "CostCategories", "cc.Code", "left join cdl.CostCategories as cc", false));
		result.add(new TableEJBQLFieldDef(CostDetailLine.class, "Summa", "cdl.Summa", false));
		//return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, costDetailLineService);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected void doLoad() {
		Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		setQuery(String.format(INIT_QUERY_TEXT, fieldsList, fromList), new String[] {"costDetail"}, new Object[] {costDetail});
	}

	public void setCostDetail(CostDetail costDetail) {
		this.costDetail = costDetail;
	}
	
}
