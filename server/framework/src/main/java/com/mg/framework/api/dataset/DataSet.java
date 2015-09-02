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
	 * ������������� ���� �������
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
	 * ��������� ������ � ������� ����� ������� ����� before
	 * @param before
	 * @param row
	 * @return
	 */
	public int insertRow(int before, List<Object> row);
	
	/**
	 * ��������� ������ � ����� �������
	 * @param row
	 * @return
	 */
	public int addRow(List<Object> row);
	
	/**
	 * ��������� ������ � ����� �������
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
	 * ������ ���� � �������� keyFields ���������� 
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
	 * ������� �������
	 */
	public void clear();
	
	/**
	 * ���������� ������ � ���� ������
	 * @return
	 */
	public List getData();
	
	/**
	 * ���������� ������ �� ������� ������� �������
	 * @return
	 */
	public Object[] getRow();
	
	/**
	 * ���������� ����� ������� �� �����
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
	 * ���������� ������� ������� �������
	 * @return
	 */
	public int getCurPos();
	
	/**
	 * ������������� ������ � ������� pos
	 * @param pos
	 */
	public boolean setCurPos(int pos);
	
	/**
	 * ���������� ���������� ������� � �������
	 * @return
	 */
	public int getRowCount();

	/**
	 * ���������� ���������� ����� �������
	 * @return
	 */
	public int getColumnCount();

	/**
	 * ��������� ������ � ��������� �������
	 */
	public boolean nextRow();

	/**
	 * ��������� ������ �� ������ ������
	 */
	public boolean firstRow();
	
	/**
	 * ������������� ������ ����� ������� ������ ������
	 */
	public boolean beforeFirstRow();

	/**
	 * ������������� �������� obj � ���� � ������� columnIndex
	 * @param obj
	 * @param columnIndex
	 * @return
	 */
	public boolean setValueAt(Object obj, int columnIndex);

	/**
	 * ������������� �������� obj � ���� � ������ columnName
	 * @param obj
	 * @param columnName
	 * @return
	 */
	public boolean setValueAt(Object obj, String columnName);

	/**
	 * ���������� �������� ���� � ������� columnIndex
	 * @param columnIndex
	 * @return
	 */
	public Object getValueAt(int columnIndex);

	/**
	 * ���������� �������� ���� � ������ columnName
	 * @param columnName
	 * @return
	 */
	public Object getValueAt(String columnName);

	/**
	 * ���������� true, ���� ������ ���������� �� ��������� ������
	 * @return
	 */
	public boolean isEndOfSet();

	/**
	 * ���������� ���-�������
	 * @return
	 */
	public Map<Integer, List<Integer>> getHashTable();
	
	/**
	 * �����������
	 * @param liGroupColumns
	 * @param lGroupCalc
	 * @return
	 */
	public DataSet groupBy(int[] liGroupColumns, GroupCalc[] lGroupCalc);
}
