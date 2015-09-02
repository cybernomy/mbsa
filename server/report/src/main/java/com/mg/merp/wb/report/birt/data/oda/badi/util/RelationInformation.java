/*
 * RelationInformation.java
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
package com.mg.merp.wb.report.birt.data.oda.badi.util;

import java.util.HashMap;

import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * ��������������� ����������, ����������� ��� ���������� ��������������� ������
 * ������ �������
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: RelationInformation.java,v 1.4 2006/11/07 08:04:27 poroxnenko
 *          Exp $
 */
public class RelationInformation {
	public static final String CONST_COLUMN_METAINFO_DELIMITER = ";";

	public static final String CONST_COLUMN_DELIMITER = ",";

	/**
	 * ���������� � �������
	 */
	private TableInfo tableInfo;

	/**
	 * �����������
	 * 
	 * @param relationString
	 *            ������, ���������� ����������, ����������� ��� ����������
	 *            �������
	 * @throws OdaException
	 */
	public RelationInformation(String relationString) throws OdaException {
		tableInfo = new TableInfo();
		initialize(relationString.trim());
	}

	/**
	 * ������������� tableInfos �� ������ ����������.
	 * 
	 * @param relationString
	 * @throws OdaException
	 */
	private void initialize(String relationString) throws OdaException {
		if (relationString == null || relationString.length() == 0)
			throw new OdaException(
					"RelationInformation.InputStringCannotBeNull");

		// ////////////////////////////////
		String[] columns = relationString.trim().split(CONST_COLUMN_DELIMITER);

		for (int j = 0; j < columns.length; j++) {
			String trimedColumn = columns[j].trim();
			// remove column info delimiter "{" and "}"

			String[] columnInfos = trimedColumn.substring(1,
					trimedColumn.length() - 1).split(
					CONST_COLUMN_METAINFO_DELIMITER);

			for (int m = 0; m < columnInfos.length; m++)
				columnInfos[m] = columnInfos[m].trim();
			tableInfo.addColumn(new ColumnInfo(j + 1, columnInfos[0],
					columnInfos[1]));
		}
	}

	/**
	 * @param columnName
	 *            ��� �������
	 * @return ��� �������
	 */
	public String getTableColumnType(String columnName) {
		return tableInfo.getType(columnName);
	}

	/**
	 * @return ������ �������� ��������
	 */
	public String[] getTableColumnNames() {
		return tableInfo.getColumnNames();
	}

}

/**
 * �����, ����������� �������
 * 
 */
class TableInfo {
	/**
	 * ��������� ���������� � ��������
	 */
	private HashMap<String, ColumnInfo> columnInfos;

	/**
	 * �����������
	 * 
	 */
	public TableInfo() {
		this.columnInfos = new HashMap<String, ColumnInfo>();
	}

	/**
	 * @param columnName
	 * @return ��� ������� �� ��� �����
	 */
	public String getType(String columnName) {
		return this.columnInfos.get(columnName).getColumnType();
	}

	/**
	 * ��������� ������� � �������
	 * 
	 * @param ci
	 *            ���������� � �������
	 */
	public void addColumn(ColumnInfo ci) {
		this.columnInfos.put(ci.getColumnName(), ci);
	}

	/**
	 * @return ������ ��� ��������
	 */
	public String[] getColumnNames() {
		Object[] names = this.columnInfos.keySet().toArray();
		String[] result = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			result[((ColumnInfo) columnInfos.get(names[i])).getColumnIndex() - 1] = names[i]
					.toString();
		}
		return result;
	}
}

/**
 * ���������� ������ �������
 * 
 */
class ColumnInfo {
	/**
	 * ������
	 */
	private int index;

	/**
	 * ���
	 */
	private String name;

	/**
	 * ���
	 */
	private String type;

	/**
	 * �����������
	 * 
	 * @param index
	 *            ������
	 * @param name
	 *            ���
	 * @param type
	 *            ���
	 * @throws OdaException
	 */
	public ColumnInfo(int index, String name, String type) throws OdaException {
		this.index = index;
		this.name = name;
		this.type = type;
		if (!DataTypes.isValidType(type))
			throw new OdaException("RelationInformation.InvalidDataTypeName");
	}

	/**
	 * @return ��� �������
	 */
	public String getColumnName() {
		return this.name;
	}

	/**
	 * @return ��� �������
	 */
	public String getColumnType() {
		return this.type;
	}

	/**
	 * @return ������ �������
	 */
	public int getColumnIndex() {
		return this.index;
	}
}