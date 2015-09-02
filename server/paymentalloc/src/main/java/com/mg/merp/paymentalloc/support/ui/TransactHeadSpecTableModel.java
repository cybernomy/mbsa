/*
 * TransactHeadSpecTableModel.java
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
package com.mg.merp.paymentalloc.support.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mg.framework.generic.ui.DefaultEJBQLTableModel;
import com.mg.framework.support.ui.widget.TableEJBQLFieldDef;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.merp.paymentalloc.SelectionRowListener;
import com.mg.merp.paymentalloc.model.TransactHead;
import com.mg.merp.paymentalloc.model.TransactSpec;

/**
 * Модель таблицы "Спецификация заголовков, связанных документов"
 * 
 * @author Artem V. Sharapov
 * @version $Id: TransactHeadSpecTableModel.java,v 1.1 2007/06/05 12:50:57 sharapov Exp $
 */
public class TransactHeadSpecTableModel extends DefaultEJBQLTableModel {

	private final String INIT_QUERY_TEXT = "select %s from TransactSpec th %s where th.TrHead = :trhead"; //$NON-NLS-1$
	private List<String> paramsName = new ArrayList<String>();
	private List<Object> paramsValue = new ArrayList<Object>();

	private SelectionRowListener selectionRowListener;

	private TransactHead transactHead;

	public TransactHeadSpecTableModel(SelectionRowListener selectionRowListener) {
		this.selectionRowListener = selectionRowListener;
	}

	protected String createQueryText() {
		Set<TableEJBQLFieldDef> fieldDefs = getFieldDefsSet();
		String fieldsList = DatabaseUtils.generateEJBQLSelectClause(fieldDefs);
		String fromList = DatabaseUtils.generateEJBQLFromClause(fieldDefs);
		paramsName.clear();
		paramsValue.clear();	
		paramsName.add("trhead"); //$NON-NLS-1$
		paramsValue.add(transactHead);	
		return String.format(INIT_QUERY_TEXT, fieldsList, fromList);		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.AbstractTableModel#doLoad()
	 */
	@Override
	protected Set<TableEJBQLFieldDef> getDefaultFieldDefsSet() {
		Set<TableEJBQLFieldDef> result = super.getDefaultFieldDefsSet();
		result.add(new TableEJBQLFieldDef(TransactSpec.class, "Id", "th.Id", true)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(TransactSpec.class, "TotalQty", "th.TotalQty", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(TransactSpec.class, "AllocQty", "th.AllocQty", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(TransactSpec.class, "TotalSum", "th.TotalSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(TransactSpec.class, "AllocSum", "th.AllocSum", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(TransactSpec.class, "DocSpec.Catalog.Code", "ds.Catalog.Code", "left join th.DocSpec as ds", true)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		result.add(new TableEJBQLFieldDef(TransactSpec.class, "DocSpec.Catalog.FullName", "ds.Catalog.FullName", false)); //$NON-NLS-1$ //$NON-NLS-2$
		result.add(new TableEJBQLFieldDef(TransactSpec.class, "DocSpec.Catalog.Measure1", "meas.Code", "left join ds.Catalog.Measure1 as meas", false)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return DatabaseUtils.embedAddinFieldsDefaultEJBQLFieldDefs(result, null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#getDefaultFieldDefsSet()
	 */
	@Override
	protected void doLoad() {
		setQuery(createQueryText(), paramsName.toArray(new String[paramsName.size()]), paramsValue.toArray(new Object[paramsValue.size()]));
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.ui.DefaultEJBQLTableModel#setSelectedRows(int[])
	 */
	@Override
	public void setSelectedRows(int[] rows) {
		if(selectionRowListener != null)
			if(rows.length != 0) 
				selectionRowListener.selectedRowChange((Integer) getRowList().get(rows[0])[0]);
	}

	public void refershTable(TransactHead transactHead) {
		this.transactHead = transactHead;
		load();
	}

	public void resetTable() {
		rowList.clear();
		fireModelChange();
	}

}
