/*
 * DnDTableData.java
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
package com.mg.framework.api.ui.dnd;

import com.mg.framework.api.ui.widget.Table;

/**
 * Данные операции DnD с элементов "таблица"
 * 
 * @author Oleg V. Safonov
 * @version $Id: DnDTableData.java,v 1.1 2007/08/16 13:48:06 safonov Exp $
 */
public class DnDTableData extends DnDData {
	private int[] selectedColumns;
	private int[] selectedRows;
	private Table table;

	public DnDTableData(Table table, int[] selectedColumns, int[] selectedRows) {
		this.selectedColumns = selectedColumns;
		this.selectedRows = selectedRows;
		this.table = table;
	}
	
	/**
	 * получить отмеченные столбцы
	 * 
	 * @return	список отмеченных столбцов
	 */
	public int[] getSelectedColumns() {
		return selectedColumns;
	}

	/**
	 * получить список отмеченных рядов
	 * 
	 * @return	отмеченные ряды
	 */
	public int[] getSelectedRows() {
		return selectedRows;
	}

	/**
	 * элемент таблица
	 * 
	 * @return	таблица
	 */
	public Table getTable() {
		return table;
	}

}
