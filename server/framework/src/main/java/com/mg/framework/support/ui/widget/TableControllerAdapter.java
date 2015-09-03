/*
 * TableControllerAdapter.java
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
package com.mg.framework.support.ui.widget;

import java.io.Serializable;

import com.mg.framework.api.ui.widget.Table;

/**
 * Адаптер таблицы
 * 
 * @author Oleg V. Safonov
 * @version $Id: TableControllerAdapter.java,v 1.6 2009/02/09 13:43:19 safonov Exp $
 */
public interface TableControllerAdapter extends Serializable {
	
	/**
	 * Возвращает модель таблицы
	 * 
	 * @return	модель
	 */
	TableModel getModel();

	/**
	 * установка отмеченных рядов
	 * 
	 * @param rows отмеченные ряды
	 */
	void setSelectedRows(int[] rows);
	
	/**
	 * установка модели колонок
	 * 
	 * @param columns модель колонок
	 */
	void setColumns(TableColumnModel[] columns);

	/**
	 * установка элемента пользовательского интерфейса "Таблица" связанного с данным контроллером
	 * 
	 * @param table	элемент UI "Таблица"
	 */
	void setTable(Table table);

}
