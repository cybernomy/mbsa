/*
 * TableModel.java
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

import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.ui.widget.TableCellRenderParameters;

/**
 * Table model
 * 
 * @author Oleg V. Safonov
 * @version $Id: TableModel.java,v 1.6 2008/07/28 14:37:43 safonov Exp $
 */
public interface TableModel {

	/**
	 * Returns the number of rows in the model. A <code>Table</code> uses this
	 * method to determine how many rows it should display.
	 * 
	 * @return	the number of rows in the model
	 * @see	#getColumnCount
	 */
	int getRowCount();
	
	/**
	 * Returns the number of columns in the model. A <code>Table</code> uses this
	 * method to determine how many columns it should create and display by default.
	 * 
	 * @return	the number of columns in the model
	 * @see #getRowCount
	 */
	int getColumnCount();
	
	/**
	 * Returns the most specific superclass for all the cell values in the column.
	 * This is used by the <code>Table</code> to set up a default renderer and
	 * editor for the column.
	 * 
	 * @param column	the index of the column
	 * @return	the common ancestor class of the object values in the model
	 */
	String getColumnName(int column);
	
	/**
	 * Returns the most specific superclass for all the cell values in the column.
	 * This is used by the <code>Table</code> to set up a default renderer and
	 * editor for the column.
	 * 
	 * @param column	the index of the column
	 * @return	the common ancestor class of the object values in the model
	 */
	Class<?> getColumnClass(int column);
	
	/**
	 * Returns true if the cell at <code>row</code> and <code>column</code> is
	 * editable.
	 * 
	 * @param row		the row whose value to be queried
	 * @param column	the column whose value to be queried
	 * @return			true if the cell is editable
	 * @see	#setValueAt
	 */
	boolean isCellEditable(int row, int column);
	
	/**
	 * Returns the value for the cell at <code>column</code> and <code>row</code>.
	 * 
	 * @param row		the row whose value is to be queried
	 * @param column	the column whose value is to be queried
	 * @return			the value Object at the specified cell
	 */
	Object getValueAt(int row, int column);
	
	/**
	 * Returns the decorator value for the cell at <code>column</code> and <code>row</code>.
	 * 
	 * @param row		the row whose value is to be queried
	 * @param column	the column whose value is to be queried
	 * @return			the value Object at the specified cell
	 */
	Object getDecoratorValueAt(int row, int column);
	
	/**
	 * Sets the value in the cell at <code>column</code> and <code>row</code> to
	 * <code>value</code>.
	 * 
	 * @param value		the new value
	 * @param row		the row whose value is to be changed
	 * @param column	the column whose value is to be changed
	 * @see		#getValueAt
	 * @see		#isCellEditable
	 */
	void setValueAt(Object value, int row, int column);

	/**
	 * получить метаданные колонки таблицы 
	 * 
	 * @param column	номер колонки
	 * @return	метаданные или <code>null</code>
	 */
	FieldMetadata getColumnMetadata(int column);
	
	/**
	 * получить параметры отрисовки ячейки таблицы, данный метод будет вызван для конкретной колонки
	 * в случае если номер колонки будет передан методом {@link #getCustomRenderColumns}
	 * 
	 * 
	 * @param params	параметры отрисовки
	 * @param column	номер колонки
	 * @param row		номер ряда
	 * @param isSelected	признак того, что ячейка выделена в таблице
	 * @param hasFocus	признак того, что на ячейке находится фокус 
	 * @return	необходимо вернуть <code>true</code> для обработки установленных значений, в противном
	 * 	случае отрисовка будет проводиться по стандартным правилам
	 */
	boolean getCellRenderParameters(TableCellRenderParameters params, int column, int row, boolean isSelected, boolean hasFocus);
	
	/**
	 * получить список колонок для которых будет обрабатываться установка параметров отрисовки
	 * с помощью метода {@link #getCellRenderParameters}
	 * 
	 * @return	список колонок, если <code>null</code> или пустой массив то все колонки будут
	 * 	отрисовываться по стандартным правилам
	 */
	int[] getCustomRenderColumns();
	
	/**
	 * Set the indices of all selected rows.
	 * 
	 * @param rows	an array of integers containing the indices of all selected rows, or an empty array if no row is selected
	 */
	void setSelectedRows(int[] rows);
	
	/**
	 * Load model
	 *
	 */
	void load();

	/**
	 * Set columns model
	 * 
	 * @param columns	columns model
	 */
	void setColumns(TableColumnModel[] columns);

	/**
	 * Adds a listener to the list that is notified each time a change to the data
	 * model occurs.
	 * 
	 * @param listener	the TableModelListener
	 */
	void addTableModelListener(TableModelListener listener);
	
	/**
	 * Removes a listener from the list that is notified each time a change to the
	 * data model occurs.
	 * 
	 * @param listener	the TableModelListener
	 */
	void removeTableModelListener(TableModelListener listener);
}
