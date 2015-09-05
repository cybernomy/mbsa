/*
 * DataSet.java
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
package com.mg.framework.api.dataset;

import java.util.*;

import com.mg.framework.support.dataset.sohlman.GroupCalc;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: DataSet.java,v 1.1 2006/07/05 09:12:02 poroxnenko Exp $
 */
public interface DataSet {
	
	/**
	 * Устанавливает поля таблицы
	 * @param defFields
	 * @return
	 */
	public boolean defineColumns(ColumnDef[] defFields);
	
	/**
	 * 
	 * @return
	 */
	public ColumnDef[] getColumnsInfo();
	
	/**
	 * Вставляет строку в таблицу перед строкой номер before
	 * @param before
	 * @param row
	 * @return
	 */
	public int insertRow(int before, List<Object> row);
	
	/**
	 * Добавляет строку в конец таблицы
	 * @param row
	 * @return
	 */
	public int addRow(List<Object> row);
	
	/**
	 * Добавляет строку в конец таблицы
	 * @param row
	 * @return
	 */
	public int addRow(Object[] row);
	
	/**
	 * 
	 * @param keyFields
	 */
	public void createHashTable(ArrayList<Integer> keyFields);
	
	
	/**
	 * Делает поля с номерами keyFields индексными 
	 * @param keyFields
	 */
	public void setIndexFields(ArrayList<Integer> keyFields);
	
	/**
	 * 
	 * @param fields
	 * @return
	 */
	public int locate(LinkedList<Object> fields);
	
	/**
	 * 
	 * @param rowCount
	 * @return
	 */
	public LinkedList<Object> getRowIndex(int rowCount);
	
	/**
	 * Очищает таблицу
	 */
	public void clear();
	
	/**
	 * Возвращает данные в виде списка
	 * @return
	 */
	public List getData();
	
	/**
	 * Возвращает строку по текущей позиции курсора
	 * @return
	 */
	public Object[] getRow();
	
	/**
	 * Возвращает номер столбца по имени
	 * @param colName
	 * @return
	 */
	public int getColIDbyName(String colName);
	
	/**
	 * 
	 * @return
	 */
	public Object getFirst();
	
	/**
	 * 
	 * @return
	 */
	public Object getLast();
	
	/**
	 * 
	 * @param colNum
	 * @return
	 */
	public Object[] getGroupByColNum(int colNum);
	
	/**
	 * 
	 * @param colName
	 * @return
	 */
	public Object[] getGroupByColName(String colName);
	
	/**
	 * Возвращает текущую позицию курсора
	 * @return
	 */
	public int getCurPos();
	
	/**
	 * Устанавливает курсор в позицию pos
	 * @param pos
	 */
	public boolean setCurPos(int pos);
	
	/**
	 * Возвращает количество записей в таблице
	 * @return
	 */
	public int getRowCount();

	/**
	 * Возвращает количество полей таблицы
	 * @return
	 */
	public int getColumnCount();

	/**
	 * Переводит курсор в следующую позицию
	 */
	public boolean nextRow();

	/**
	 * Переводит курсор на первую запись
	 */
	public boolean firstRow();
	
	/**
	 * Устанавливает курсор перед началом набора данных
	 */
	public boolean beforeFirstRow();

	/**
	 * Устанавливает значение obj в поле с номером columnIndex
	 * @param obj
	 * @param columnIndex
	 * @return
	 */
	public boolean setValueAt(Object obj, int columnIndex);

	/**
	 * Устанавливает значение obj в поле с именем columnName
	 * @param obj
	 * @param columnName
	 * @return
	 */
	public boolean setValueAt(Object obj, String columnName);

	/**
	 * Возвращает значение поля с номером columnIndex
	 * @param columnIndex
	 * @return
	 */
	public Object getValueAt(int columnIndex);

	/**
	 * Возвращает значение поля с именем columnName
	 * @param columnName
	 * @return
	 */
	public Object getValueAt(String columnName);

	/**
	 * Возвращает true, если курсор установлен на последней записи
	 * @return
	 */
	public boolean isEndOfSet();

	/**
	 * Возвращает хэш-таблицу
	 * @return
	 */
	public Map<Integer, List<Integer>> getHashTable();
	
	/**
	 * Группировка
	 * @param liGroupColumns
	 * @param lGroupCalc
	 * @return
	 */
	public DataSet groupBy(int[] liGroupColumns, GroupCalc[] lGroupCalc);
}
