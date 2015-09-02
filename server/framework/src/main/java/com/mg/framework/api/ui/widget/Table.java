/*
 * Table.java
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
package com.mg.framework.api.ui.widget;

import com.mg.framework.api.ui.ControllableWidget;
import com.mg.framework.api.ui.Widget;

/**
 * ������� "�������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Table.java,v 1.10 2009/02/09 13:16:57 safonov Exp $
 */
public interface Table extends Widget, ControllableWidget {
	
	/**
	 * ������� �������� ��������������� ��������� ������� �������, ����� ����� ��������� ��������:
	 * </br>off
	 * </br>next_column
	 * </br>subsequent_columns
	 * </br>last_column
	 * </br>all_column
	 * 
	 * @see #AUTO_RESIZE_OFF
	 * @see #AUTO_RESIZE_NEXT_COLUMN
	 * @see #AUTO_RESIZE_SUBSEQUENT_COLUMNS
	 * @see #AUTO_RESIZE_LAST_COLUMN
	 * @see #AUTO_RESIZE_ALL_COLUMNS
	 */
	final static String AUTO_RESIZE = "autoResize";
	
	/**
	 * ��� ���������� ������ �������
	 */
	final static String COLUMNS = "jfd:columns";
	
	/**
	 * ��� ����������� �������, ������������ ������ ���� <code>COLUMNS</code>
	 */
	final static String COLUMN = "jfd:column";
	
	/**
	 * ��� ���������� �������� ��������� ����� �������
	 */
	final static String CELL_RENDER = "jfd:cellRender";
	
	/**
	 * ������� ������������ ���� �������, ������������ � ���� <code>COLUMN</code>
	 */
	final static String COLUMN_FIELD_NAME = "fieldName";
	
	/**
	 * ������� ��������� �������, ������������ � ���� <code>COLUMN</code>
	 */
	final static String COLUMN_TITLE = "title";
	
	/**
	 * ������� �������� �������� �� ��������, ������������ �� ���������
	 */
	final static String AUTO_RESIZE_OFF = "off";
	
	/**
	 * ��� ��������� ������� ������� ������������ ������������������ ������ ����� ��� �
	 * ��������� �� ��� ��������
	 */
	final static String AUTO_RESIZE_NEXT_COLUMN = "next_column";
	
	/**
	 * ��� ��������� ������� ������-���� ������� ����������������� ������������
	 * ���������� ���������� ����� ����� ���������� �� ��� ���������
	 */
	final static String AUTO_RESIZE_SUBSEQUENT_COLUMNS = "subsequent_columns";
	
	/**
	 * ������� ����� ����������� ��� ��������� ������ �� ���� ���������� �������
	 */
	final static String AUTO_RESIZE_LAST_COLUMN = "last_column";
	
	/**
	 * ����������������� ������������ ��������������� ����� ��������� �������
	 */
	final static String AUTO_RESIZE_ALL_COLUMNS = "all_column";
	
	/**
	 * ������� �������� ������ ������, ����� ����� ��������� ��������:
	 * </br><code>singleSelection</code>
	 * </br><code>singleIntervalSelection</code>
	 * </br><code>multipleIntervalSelection</code> �������� �� ���������
	 * 
	 * @see #SINGLE_SELECTION
	 * @see #SINGLE_INTERVAL_SELECTION
	 * @see #MULTIPLE_INTERVAL_SELECTION
	 */
	final static String SELECTION_MODE = "selectionMode";
	
	/**
	 * ��������� ��������� ������ �������
	 */
	final static String SINGLE_SELECTION = "singleSelection";
	
	/**
	 * ��������� ���������� ��������� ����� �������
	 */
	final static String SINGLE_INTERVAL_SELECTION = "singleIntervalSelection";
	
	/**
	 * ��������� ���������� ���������� ����� �������
	 */
	final static String MULTIPLE_INTERVAL_SELECTION = "multipleIntervalSelection";
	
	/**
	 * ������� ������� ��������� ���������� �� ��������, ����� ��� <code>boolean</code>,
	 * �������� �� ��������� <code>true</code>.
	 */
	final static String SORTED_MODEL = "sortedModel";

	/**
	 * ������� ������� ��������� ��������� ����� �������, ����� ��� <code>boolean</code>,
	 * �������� �� ��������� <code>false</code>.
	 */
	final static String CELL_SELECTION_ENABLED = "cellSelectionEnabled";

	/**
	 * ������� ������� ��������� ��������� ������� �������, ����� ��� <code>boolean</code>,
	 * �������� �� ��������� <code>false</code>.
	 */
	final static String COLUMN_SELECTION_ALLOWED = "columnSelectionAllowed";
	
	/**
	 * ������� ������ ������, ����� ��� <code>Integer</code>
	 */
	final static String ROW_HEIGHT = "rowHeight";
	
	/**
	 * ������� ������������ ����� �������� � ������� �������, ����� ��� <code>Integer</code>,
	 * �������� �� ��������� 1
	 */
	final static String ROW_MARGIN = "rowMargin";
	
	/**
	 * ������� ������� ��������� ��������� ������ �������, ����� ��� <code>boolean</code>,
	 * �������� �� ��������� <code>true</code>.
	 */
	final static String ROW_SELECTION_ALLOWED = "rowSelectionAllowed";
	
	/**
	 * ������� ������� ��������� ����� �������, ����� ��� <code>boolean</code>
	 */
	final static String SHOW_GRID = "showGrid";
	
	/**
	 * ������� ������� ��������� �������������� ����� �������, ����� ��� <code>boolean</code>
	 */
	final static String SHOW_HORIZONTAL_LINES = "showHorizontalLines";
	
	/**
	 * ������� ������� ��������� ������������ ����� �������, ����� ��� <code>boolean</code>
	 */
	final static String SHOW_VERTICAL_LINES = "showVerticalLines";
	
	/**
	 * ������� ������� ��������� ������������ ������ � ������� ������� ������, ����� ��� <code>boolean</code>,
	 * �������� �� ��������� <code>true</code>.
	 */
	final static String LOOK_AHEAD_ENABLED = "lookAheadEnabled";

	/**
	 * ������� ������� ��������� ������������ ������, ����� ��� <code>boolean</code>,
	 * �������� �� ��������� <code>true</code>.
	 */
	final static String INCREMENTAL_SEARCH_ENABLED = "incrementalSearchEnabled";

	/**
	 * ������� ������� ������� �������� ����� �� ������� �������, ����� ��� <code>boolean</code>,
	 * �������� �� ��������� <code>true</code>. ���� ����������, �� ��� ������� ���������� Ctrl +
	 * ����� ���������� ����� �������� ����������� ������� � �������� �� ����������� ���������
	 */
	final static String COLUMN_SUM_ENABLED = "columnSumEnabled";

	/**
	 * Returns true if the cell at the specified position is selected.
	 *
	 * @param row 		 the row being queried
	 * @param column 		 the column being queried
	 * @return		true if the cell at index <code>(row, column)</code> is selected, where the first row and first column are at index 0
	 * @throws		IllegalArgumentException if <code>row</code> or <code>column</code> are not in the valid range
	 */
	boolean isCellSelected(int row, int column);

	/**
	 * Returns the indices of all selected rows.
	 * 
	 * @return	an array of integers containing the indices of all selected rows, or an empty array if no row is selected
	 */
	int[] getSelectedRows();

	/**
	 * Returns the indices of all selected columns.
	 * 
	 * @return	an array of integers containing the indices of all selected columns, or an empty array if no column is selected
	 */
	int[] getSelectedColumns();

	/**
	 * Selects the rows from <code>index0</code> to <code>index1</code>, inclusive.
	 *
	 * @param index0 		 one end of the interval
	 * @param index1 		 the other end of the interval
	 */
	void setRowSelectionInterval(int index0, int index1);

}
