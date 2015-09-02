/*
 * DefaultTableController.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.support.ui.widget;

import com.mg.framework.api.ui.MasterModelListener;
import com.mg.framework.api.ui.ModelChangeEvent;
import com.mg.framework.api.ui.widget.Table;

/**
 * Стандартная реализация адаптера таблицы
 * 
 * @author Oleg V. Safonov
 * @version $Id: DefaultTableController.java,v 1.4 2009/02/09 14:22:17 safonov Exp $
 */
public class DefaultTableController implements TableControllerAdapter, MasterModelListener {
	protected TableModel tableModel;
	protected Table tableWidget = null;

	public DefaultTableController(TableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	public void fireMasterChange(ModelChangeEvent event) {
		tableModel.load();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#getModel()
	 */
	public TableModel getModel() {
		return tableModel;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setCurrentRow(int)
	 */
	public void setSelectedRows(int[] rows) {
		tableModel.setSelectedRows(rows);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setColumns(com.mg.framework.support.ui.widget.TableColumnModel[])
	 */
	public void setColumns(TableColumnModel[] columns) {
		tableModel.setColumns(columns);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.ui.MasterModelListener#masterChange(com.mg.framework.api.ui.ModelChangeEvent)
	 */
	public void masterChange(ModelChangeEvent event) {
		fireMasterChange(event);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setTable(com.mg.framework.api.ui.widget.Table)
	 */
	public void setTable(Table table) {
		this.tableWidget = table;
	}

}
