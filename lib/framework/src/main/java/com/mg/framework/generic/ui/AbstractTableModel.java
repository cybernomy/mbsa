/*
 * AbstractTableModel.java
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.ui.widget.TableCellRenderParameters;
import com.mg.framework.support.ui.widget.TableColumnModel;
import com.mg.framework.support.ui.widget.TableModel;
import com.mg.framework.support.ui.widget.TableModelListener;

/**
 * The <code>AbstractTableModel</code> class provides a default implementation
 * formost of the methods in the <code>TableModel</code> interface. In most
 * cases developer will subclass <code>AbstractTableModel</code> to implement
 * his own table model.
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractTableModel.java,v 1.7 2008/07/28 14:40:44 safonov Exp $
 */
public abstract class AbstractTableModel implements TableModel, Serializable {
	private List<TableModelListener> listeners = new ArrayList<TableModelListener>();

	protected void doLoad() {
		//do nothing
	}

	protected void doSetColumns(TableColumnModel[] columns) {
		
	}

	/**
	 * Notifies all listeners that all cell values in the table's rows may have
	 * changed. The number of rows may also have changed and the
	 * <code>Table</code> should redraw the table from scratch. The structure of
	 * the table (as in the order of the columns) is assumed to be the same.
	 *
	 */
	public void fireModelChange() {
		for (TableModelListener listener : listeners)
			listener.tableChanged();
	}

	/**
	 * Notifies all listeners that rows in the range <code>firstRow</code> to
	 * <code>lastRow</code>, inclusive, have been deleted.
	 * 
	 * @param firstRow	the first row
	 * @param lastRow	the last row
	 */
	public void fireTableRowsDeleted(int firstRow, int lastRow) {
		for (TableModelListener listener : listeners)
			listener.tableRowsDeleted(firstRow, lastRow);
	}
	
	/**
	 * Notifies all listeners that rows in the range <code>firstRow</code> to
	 * <code>lastRow</code>, inclusive, have been updated.
	 * 
	 * @param firstRow	the first row
	 * @param lastRow	the last row
	 */
	public void fireTableRowsUpdated(int firstRow, int lastRow) {
		for (TableModelListener listener : listeners)
			listener.tableRowsUpdated(firstRow, lastRow);
	}
	
	/**
	 * Notifies all listeners that rows in the range <code>firstRow</code> to
	 * <code>lastRow</code>, inclusive, have been inserted.
	 * 
	 * @param firstRow	the first row
	 * @param lastRow	the last row
	 */
	public void fireTableRowsInserted(int firstRow, int lastRow) {
		for (TableModelListener listener : listeners)
			listener.tableRowsInserted(firstRow, lastRow);
	}

	/**
	 * Notifies all listeners that the table's structure has changed. The number of
	 * columns in the table, and the names and types of the new columns may be
	 * different from the previous state.
	 *
	 */
	public void fireTableStructureChanged() {
		for (TableModelListener listener : listeners)
			listener.tableStructureChanged();
	}
	
	/**
	 * Notifies all listeners that the value of the cell at row <code>row</code> and
	 * column <code>column</code> has been updated.
	 * 
	 * @param row		row of cell which has been updated
	 * @param column	column of cell which has been updated
	 */
	public void fireTableCellUpdated(int row, int column) {
		for (TableModelListener listener : listeners)
			listener.tableCellUpdated(row, column);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#addTableModelListener(com.mg.framework.support.ui.widget.TableModelListener)
	 */
	public void addTableModelListener(TableModelListener listener) {
		if (listener != null)
			listeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getColumnClass(int)
	 */
	public Class<?> getColumnClass(int column) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#removeTableModelListener(com.mg.framework.support.ui.widget.TableModelListener)
	 */
	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#setValueAt(java.lang.Object, int, int)
	 */
	public void setValueAt(Object value, int row, int column) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#setSelectedRows(int[])
	 */
	public void setSelectedRows(int[] rows) {
		//do nothing
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getCurrentPrimaryKey()
	 */
	public Serializable[] getSelectedPrimaryKeys() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#load()
	 */
	public void load() {
		doLoad();
	}

	public void setColumns(TableColumnModel[] columns) {
		doSetColumns(columns);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getDecoratorValueAt(int, int)
	 */
	public Object getDecoratorValueAt(int row, int column) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getCellRenderParameters(com.mg.framework.api.ui.widget.TableCellRenderParameters)
	 */
	public boolean getCellRenderParameters(TableCellRenderParameters params, int column, int row, boolean isSelected, boolean hasFocus) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getCustomRenderColumns()
	 */
	public int[] getCustomRenderColumns() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.support.ui.widget.TableModel#getFieldMetadata(int)
	 */
	public FieldMetadata getColumnMetadata(int column) {
		return null;
	}

}
