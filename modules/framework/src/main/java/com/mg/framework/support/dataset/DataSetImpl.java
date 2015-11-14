/*
 * DataSetImpl.java
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
package com.mg.framework.support.dataset;

import com.mg.framework.api.dataset.AutoInc;
import com.mg.framework.api.dataset.ColumnDef;
import com.mg.framework.api.dataset.DataSet;
import com.mg.framework.support.dataset.sohlman.ColumnInfo;
import com.mg.framework.support.dataset.sohlman.GroupCalc;
import com.mg.framework.support.dataset.sohlman.RowContainer;
import com.mg.framework.support.dataset.sohlman.RowInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: DataSetImpl.java,v 1.3 2006/07/05 09:21:07 poroxnenko Exp $
 */
public class DataSetImpl implements DataSet {
  private com.mg.framework.support.dataset.sohlman.DataSet dataSet = null;
  private RowInfo rowInfo = null;
  private ColumnInfo[] colInfo = null;
  private int colAutoInc;
  private AutoInc ai;
  private int currentRow;

  private Map<Integer, List<Integer>> hashTable;
  private ArrayList<Integer> indexFields;

  public DataSetImpl(ColumnDef[] defFields) {
    this.dataSet = new com.mg.framework.support.dataset.sohlman.DataSet();
    if (!defineColumns(defFields))
      throw new RuntimeException(ExcStrings.SROW_INFO_NOT_EXIST);
    this.currentRow = -1;
  }

  public DataSetImpl() {
    this.dataSet = new com.mg.framework.support.dataset.sohlman.DataSet();
    this.currentRow = -1;
  }

  public DataSetImpl(DataSetImpl params) {
    this.dataSet = new com.mg.framework.support.dataset.sohlman.DataSet(params.dataSet);
    this.rowInfo = params.rowInfo;
    this.colInfo = params.colInfo;
    this.colAutoInc = params.colAutoInc;
    this.ai = params.ai;
    this.currentRow = params.currentRow;
  }

  public boolean defineColumns(ColumnDef[] defFields) {
    if (defFields != null) {
      this.colInfo = convertCDef2CInf(defFields);
      this.rowInfo = new RowInfo(this.colInfo);
      this.dataSet.setRowInfo(this.rowInfo);
      return true;
    } else
      return false;
  }

  public ColumnDef[] getColumnsInfo() {
    return convertCInf2CDef(colInfo);
  }

  public int insertRow(int before, List<Object> row) {
    currentRow = dataSet.insertRow(before);
    fillRow(row);
    return currentRow;
  }

  public int addRow(List<Object> row) {
    if (currentRow == -1 && getRowCount() == 0)
      currentRow = 0;
      //if (currentRow > 0 && currentRow < getRowCount() || currentRow == -1)
    else currentRow = getRowCount();
    dataSet.addRow();
    fillRow(row);
    return dataSet.getRowCount();
  }

  public int addRow(Object[] row) {
    if (currentRow == -1 && getRowCount() == 0)
      currentRow = 0;
      //if (currentRow > 0 && currentRow < getRowCount() || currentRow == -1)
    else currentRow = getRowCount();
    dataSet.addRow();

    dataSet.setValue(row, dataSet.getRowCount());
    currentRow++;
    if (indexFields != null && !indexFields.isEmpty())
      setRowIndex(currentRow, indexFields);

    return dataSet.getRowCount();
  }

  @SuppressWarnings("unchecked")
  public void createHashTable(ArrayList<Integer> keyFields) {
    if (keyFields != null && !keyFields.isEmpty()) {
      this.hashTable = new HashMap<>();
      this.indexFields = keyFields;
      for (int rowCount = 1; rowCount <= getRowCount(); rowCount++) {
        setRowIndex(rowCount, keyFields);
      }
    }
  }

  @SuppressWarnings("unchecked")
  public void setIndexFields(ArrayList<Integer> keyFields) {
    if (keyFields != null && !keyFields.isEmpty()) {
      this.hashTable = new HashMap<>();
      this.indexFields = keyFields;
    } else
      throw new RuntimeException(ExcStrings.S_INDEX_NOT_EXIST);
  }

  public int locate(LinkedList<Object> fields) {
    if (fields != null && !fields.isEmpty() && hashTable != null) {
      int key = fields.hashCode();
      if (hashTable.containsKey(key)) {
        for (Iterator<Integer> it = hashTable.get(key).listIterator(); it.hasNext(); ) {
          int id = it.next();
          boolean flag = true;
          byte i = 0;
          while (flag && i < indexFields.size()) {
            if ((dataSet.getValueAt(id, indexFields.get(i))).equals(fields.get(i)))
              flag = true;
            else flag = false;
            i++;
          }
          if (flag) {
            currentRow = id;
            return id;
          }
        }
        return -1;
      } else
        return -1;
    } else
      return -1;
  }

  public void createHashTable(String[] keyFields) {

  }

  private void fillRow(List<Object> row) {
    if (this.colAutoInc > -1) {
      row.set(colAutoInc, ai.getValue());
      ai.inc();
    }

    dataSet.setValue(row.toArray(), dataSet.getRowCount());
    currentRow++;
    if (indexFields != null && !indexFields.isEmpty())
      setRowIndex(currentRow, indexFields);

  }

  public LinkedList<Object> getRowIndex(int rowCount) {
    LinkedList<Object> listRow = new LinkedList<Object>();
    for (byte i = 0; i < indexFields.size(); i++)
      listRow.add(dataSet.getValueAt(rowCount, indexFields.get(i)));
    return listRow;
  }

  private void setRowIndex(int rowCount, ArrayList<Integer> keyFields) {
    LinkedList<Object> listRow = getRowIndex(rowCount);
    Integer key = listRow.hashCode();
    List<Integer> value = new LinkedList<Integer>();
    if (this.hashTable.containsKey(key))
      value = this.hashTable.get(key);
    value.add(rowCount);
    this.hashTable.put(key, value);
  }

  public void clear() {
    dataSet.reset();
  }

  public List getData() {
    return dataSet.getAllRows();
  }

  public Object[] getRow() {
    return dataSet.getRow(currentRow).getColumns();
  }

  public int getColIDbyName(String colName) {
    RowInfo rowInf = dataSet.getRowInfo();
    if (rowInf != null) {
      for (int i = 1; i <= rowInf.getColumnCount(); i++) {
        if (rowInf.getColumnName(i).equalsIgnoreCase(colName))
          return i;
      }
    } else
      throw new RuntimeException(ExcStrings.SROW_INFO_NOT_EXIST);
    return 0;
  }

  public Object getFirst() {
    List rows = dataSet.getAllRows();
    if (rows != null)
      return dataSet.getAllRows().get(0);
    else
      return null;
  }

  public Object getLast() {
    List rows = dataSet.getAllRows();
    if (rows != null)
      return dataSet.getAllRows().get(dataSet.getRowCount());
    else
      return null;
  }

  public Object[] getGroupByColNum(int colNum) {
    if (dataSet.getRowCount() > 0) {
      Object[] objArr = new Object[dataSet.getRowCount()];
      try {
        for (int i = 1; i <= dataSet.getRowCount(); i++)
          objArr[i - 1] = dataSet.getValueAt(i, colNum);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      return objArr;
    } else
      return null;
  }

  public Object[] getGroupByColName(String colName) {
    return getGroupByColNum((int) getColIDbyName(colName));
  }

  public int getCurPos() {
    return currentRow;
  }

  public boolean setCurPos(int pos) {
    if (pos <= dataSet.getRowCount() && pos >= 0)
      currentRow = pos;
    else if (pos > dataSet.getRowCount())
      currentRow = -1;
    return isEndOfSet();
  }

  public int getRowCount() {
    return dataSet.getRowCount();
  }

  public int getColumnCount() {
    return dataSet.getRowInfo().getColumnCount();
  }

  public boolean nextRow() {
    return setCurPos(currentRow + 1);
  }

  public boolean firstRow() {
    return setCurPos(1);
  }

  public boolean beforeFirstRow() {
    return setCurPos(0);
  }

  public boolean setValueAt(Object obj, int columnIndex) {
    if (currentRow != -1) {
      if (colAutoInc == -1 || columnIndex != colAutoInc) {
        if (indexFields != null && !indexFields.isEmpty() && indexFields.contains(columnIndex)) {
          LinkedList<Object> ind = getRowIndex(currentRow);
          List<Integer> value = hashTable.get(ind.hashCode());
          value.remove(currentRow);
          dataSet.setValueAt(obj, currentRow, columnIndex);
          setRowIndex(currentRow, indexFields);
        }
        dataSet.setValueAt(obj, currentRow, columnIndex);
        return true;
      }
      return false;
    } else
      throw new IllegalStateException(ExcStrings.SEOF);
  }

  public boolean setValueAt(Object obj, String columnName) {
    int i = getColIDbyName(columnName);
    if (i > 0)
      return this.setValueAt(obj, i);
    return false;
  }

  public Object getValueAt(int columnIndex) {
    if (currentRow != -1) {
      return dataSet.getValueAt(this.currentRow, columnIndex);
    } else
      throw new IllegalStateException(ExcStrings.SEOF);
  }

  public Object getValueAt(String columnName) {
    int i = getColIDbyName(columnName);
    if (i > 0)
      return dataSet.getValueAt(this.currentRow, i);
    return null;
  }

  private ColumnInfo[] convertCDef2CInf(ColumnDef[] colDefArr) {
    this.colAutoInc = -1;
    ColumnInfo[] ci = new ColumnInfo[colDefArr.length];
    for (int i = 0; i < colDefArr.length; i++) {
      if (colDefArr[i].getFieldType().equals(AutoInc.class)) {
        this.colAutoInc = i;
        colDefArr[i] = new ColumnDef(colDefArr[i].getFieldName(),
            Integer.class);
      }
      ci[i] = colDefArr[i].getColumnInfo();
    }
    if (this.colAutoInc > -1)
      ai = new AutoInc();
    return ci;
  }

  private ColumnDef[] convertCInf2CDef(ColumnInfo[] colInfArr) {
    ColumnDef[] cd = new ColumnDef[colInfArr.length];
    for (int i = 0; i < colInfArr.length; i++) {
      cd[i] = new ColumnDef(colInfArr[i].getName(), colInfArr[i]
          .getColumnClass());
    }
    return cd;
  }

  public boolean isEndOfSet() {
    if (currentRow == -1)
      return true;
    else
      return false;
  }

  public Map<Integer, List<Integer>> getHashTable() {
    return hashTable;
  }

  public DataSet groupBy(int[] liGroupColumns, GroupCalc[] lGroupCalc) {
    DataSetImpl result = new DataSetImpl(this);
    Object[] rows = dataSet.getAllRows().toArray();
    Object[] oldCopy = new Object[rows.length];
    for (int i = 0; i < rows.length; i++)
      oldCopy[i] = ((RowContainer) rows[i]).getRow().getAllColumns().clone();
    for (int i = 0; i < rows.length; i++)
      result.addRow((Object[]) oldCopy[i]);
    result.dataSet.groupBy(liGroupColumns, lGroupCalc);
    return result;
  }
}
