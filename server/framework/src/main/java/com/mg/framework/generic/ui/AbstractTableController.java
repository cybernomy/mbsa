/*
 * AbstractTableController.java
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
package com.mg.framework.generic.ui;

import com.mg.framework.support.ui.widget.TableControllerAdapter;
import com.mg.framework.support.ui.widget.TableModelListener;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractTableController.java,v 1.3 2006/05/10 13:02:03 safonov Exp $
 */
public abstract class AbstractTableController implements TableControllerAdapter {
	private TableModelListener tableModelListener = null;

	public void fireModelChange() {
		if (tableModelListener != null)
			tableModelListener.tableChanged();
	}
	
	public void fireTableRowsDeleted(int firstRow, int lastRow) {
		if (tableModelListener != null)
			tableModelListener.tableRowsDeleted(firstRow, lastRow);
	}
	
	public void fireTableRowsUpdated(int firstRow, int lastRow) {
		if (tableModelListener != null)
			tableModelListener.tableRowsUpdated(firstRow, lastRow);
	}
	
	public void fireTableRowsInserted(int firstRow, int lastRow) {
		if (tableModelListener != null)
			tableModelListener.tableRowsInserted(firstRow, lastRow);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#addTableModelListener(com.mg.framework.support.ui.widget.TableModelListener)
	 */
	public void addTableModelListener(TableModelListener listener) {
		this.tableModelListener = listener;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableControllerAdapter#setCurrentRow(int)
	 */
	public void setSelectedRows(int[] rows) {
		// TODO Auto-generated method stub

	}

}
