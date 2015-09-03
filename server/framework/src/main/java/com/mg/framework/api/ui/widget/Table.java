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
 * Элемент "Таблица"
 * 
 * @author Oleg V. Safonov
 * @version $Id: Table.java,v 1.10 2009/02/09 13:16:57 safonov Exp $
 */
public interface Table extends Widget, ControllableWidget {
	
	/**
	 * атрибут устновки автоматического изменения размера колонок, может иметь следующие значения:
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
	 * Тэг содержащий список колонок
	 */
	final static String COLUMNS = "jfd:columns";
	
	/**
	 * Тэг описывающий колонку, используется внутри тэга <code>COLUMNS</code>
	 */
	final static String COLUMN = "jfd:column";
	
	/**
	 * Тэг содержащий описание отрисовки ячеек таблицы
	 */
	final static String CELL_RENDER = "jfd:cellRender";
	
	/**
	 * атрибут наименования поля колонки, используется в тэге <code>COLUMN</code>
	 */
	final static String COLUMN_FIELD_NAME = "fieldName";
	
	/**
	 * атрибут заголовка колонки, используется в тэге <code>COLUMN</code>
	 */
	final static String COLUMN_TITLE = "title";
	
	/**
	 * размеры столбцов таблицей не меняются, используется по умолчанию
	 */
	final static String AUTO_RESIZE_OFF = "off";
	
	/**
	 * при изменении размера столбца пространство перераспределяется только между ним и
	 * следующим за ним столбцом
	 */
	final static String AUTO_RESIZE_NEXT_COLUMN = "next_column";
	
	/**
	 * при изменении размера какого-либо столбца перераспределение пространства
	 * происходит равномерно между всеми следующими за ним столбцами
	 */
	final static String AUTO_RESIZE_SUBSEQUENT_COLUMNS = "subsequent_columns";
	
	/**
	 * столбцы можно увеличивать или уменьшать только за счет последнего столбца
	 */
	final static String AUTO_RESIZE_LAST_COLUMN = "last_column";
	
	/**
	 * перераспределение пространства происходитмежду всеми столбцами таблицы
	 */
	final static String AUTO_RESIZE_ALL_COLUMNS = "all_column";
	
	/**
	 * атрибут устновки режима выбора, может иметь следующие значения:
	 * </br><code>singleSelection</code>
	 * </br><code>singleIntervalSelection</code>
	 * </br><code>multipleIntervalSelection</code> значение по умолчанию
	 * 
	 * @see #SINGLE_SELECTION
	 * @see #SINGLE_INTERVAL_SELECTION
	 * @see #MULTIPLE_INTERVAL_SELECTION
	 */
	final static String SELECTION_MODE = "selectionMode";
	
	/**
	 * одиночное выделение строки таблицы
	 */
	final static String SINGLE_SELECTION = "singleSelection";
	
	/**
	 * выделение одиночного интервала строк таблицы
	 */
	final static String SINGLE_INTERVAL_SELECTION = "singleIntervalSelection";
	
	/**
	 * выделение нескольких интервалов строк таблицы
	 */
	final static String MULTIPLE_INTERVAL_SELECTION = "multipleIntervalSelection";
	
	/**
	 * атрибут признак поддержки сортировки по столбцам, имеет тип <code>boolean</code>,
	 * значение по умолчанию <code>true</code>.
	 */
	final static String SORTED_MODEL = "sortedModel";

	/**
	 * атрибут признак поддержки выделения ячеек таблицы, имеет тип <code>boolean</code>,
	 * значение по умолчанию <code>false</code>.
	 */
	final static String CELL_SELECTION_ENABLED = "cellSelectionEnabled";

	/**
	 * атрибут признак поддержки выделения столбца таблицы, имеет тип <code>boolean</code>,
	 * значение по умолчанию <code>false</code>.
	 */
	final static String COLUMN_SELECTION_ALLOWED = "columnSelectionAllowed";
	
	/**
	 * атрибут высоты строки, имеет тип <code>Integer</code>
	 */
	final static String ROW_HEIGHT = "rowHeight";
	
	/**
	 * атрибут пространства между ячейками в смежных строках, имеет тип <code>Integer</code>,
	 * значение по умолчанию 1
	 */
	final static String ROW_MARGIN = "rowMargin";
	
	/**
	 * атрибут признак поддержки выделения строки таблицы, имеет тип <code>boolean</code>,
	 * значение по умолчанию <code>true</code>.
	 */
	final static String ROW_SELECTION_ALLOWED = "rowSelectionAllowed";
	
	/**
	 * атрибут признак отрисовки линий таблицы, имеет тип <code>boolean</code>
	 */
	final static String SHOW_GRID = "showGrid";
	
	/**
	 * атрибут признак отрисовки горизонтальных линий таблицы, имеет тип <code>boolean</code>
	 */
	final static String SHOW_HORIZONTAL_LINES = "showHorizontalLines";
	
	/**
	 * атрибут признак отрисовки вертикальных линий таблицы, имеет тип <code>boolean</code>
	 */
	final static String SHOW_VERTICAL_LINES = "showVerticalLines";
	
	/**
	 * атрибут признак поддержки контекстного поиска с помощью диалога поиска, имеет тип <code>boolean</code>,
	 * значение по умолчанию <code>true</code>.
	 */
	final static String LOOK_AHEAD_ENABLED = "lookAheadEnabled";

	/**
	 * атрибут признак поддержки контекстного поиска, имеет тип <code>boolean</code>,
	 * значение по умолчанию <code>true</code>.
	 */
	final static String INCREMENTAL_SEARCH_ENABLED = "incrementalSearchEnabled";

	/**
	 * атрибут признак функции подсчета суммы по столбцу таблицы, имеет тип <code>boolean</code>,
	 * значение по умолчанию <code>true</code>. Если установлен, то при нажатии комбинации Ctrl +
	 * будет подсчитана сумма значений выделенного столбца и показана во всплавающем сообщении
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
