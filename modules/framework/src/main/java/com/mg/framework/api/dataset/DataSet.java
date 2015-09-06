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

import com.mg.framework.support.dataset.sohlman.GroupCalc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: DataSet.java,v 1.1 2006/07/05 09:12:02 poroxnenko Exp $
 */
public interface DataSet {

  /**
   * Устанавливает поля таблицы
   */
  public boolean defineColumns(ColumnDef[] defFields);

  /**
   *
   * @return
   */
  public ColumnDef[] getColumnsInfo();

  /**
   * Вставляет строку в таблицу перед строкой номер before
   */
  public int insertRow(int before, List<Object> row);

  /**
   * Добавляет строку в конец таблицы
   */
  public int addRow(List<Object> row);

  /**
   * Добавляет строку в конец таблицы
   */
  public int addRow(Object[] row);

  /**
   *
   * @param keyFields
   */
  public void createHashTable(ArrayList<Integer> keyFields);


  /**
   * Делает поля с номерами keyFields индексными
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
   */
  public List getData();

  /**
   * Возвращает строку по текущей позиции курсора
   */
  public Object[] getRow();

  /**
   * Возвращает номер столбца по имени
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
   */
  public int getCurPos();

  /**
   * Устанавливает курсор в позицию pos
   */
  public boolean setCurPos(int pos);

  /**
   * Возвращает количество записей в таблице
   */
  public int getRowCount();

  /**
   * Возвращает количество полей таблицы
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
   */
  public boolean setValueAt(Object obj, int columnIndex);

  /**
   * Устанавливает значение obj в поле с именем columnName
   */
  public boolean setValueAt(Object obj, String columnName);

  /**
   * Возвращает значение поля с номером columnIndex
   */
  public Object getValueAt(int columnIndex);

  /**
   * Возвращает значение поля с именем columnName
   */
  public Object getValueAt(String columnName);

  /**
   * Возвращает true, если курсор установлен на последней записи
   */
  public boolean isEndOfSet();

  /**
   * Возвращает хэш-таблицу
   */
  public Map<Integer, List<Integer>> getHashTable();

  /**
   * Группировка
   */
  public DataSet groupBy(int[] liGroupColumns, GroupCalc[] lGroupCalc);
}
