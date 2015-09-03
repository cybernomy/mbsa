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
 * Вспомогательная информация, необходимая для построения ресультирующего набора
 * данных запроса
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: RelationInformation.java,v 1.4 2006/11/07 08:04:27 poroxnenko
 *          Exp $
 */
public class RelationInformation {
	public static final String CONST_COLUMN_METAINFO_DELIMITER = ";";

	public static final String CONST_COLUMN_DELIMITER = ",";

	/**
	 * Информация о таблице
	 */
	private TableInfo tableInfo;

	/**
	 * Конструктор
	 * 
	 * @param relationString
	 *            строка, содержащая метаданные, необходимые для построения
	 *            таблицы
	 * @throws OdaException
	 */
	public RelationInformation(String relationString) throws OdaException {
		tableInfo = new TableInfo();
		initialize(relationString.trim());
	}

	/**
	 * Инициализация tableInfos по строке метаданных.
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
	 *            имя столбца
	 * @return тип столбца
	 */
	public String getTableColumnType(String columnName) {
		return tableInfo.getType(columnName);
	}

	/**
	 * @return массив названий столбцов
	 */
	public String[] getTableColumnNames() {
		return tableInfo.getColumnNames();
	}

}

/**
 * Класс, описывающий таблицу
 * 
 */
class TableInfo {
	/**
	 * Хранилище информации о столбцах
	 */
	private HashMap<String, ColumnInfo> columnInfos;

	/**
	 * Конструктор
	 * 
	 */
	public TableInfo() {
		this.columnInfos = new HashMap<String, ColumnInfo>();
	}

	/**
	 * @param columnName
	 * @return тип столбца по его имени
	 */
	public String getType(String columnName) {
		return this.columnInfos.get(columnName).getColumnType();
	}

	/**
	 * Добавляет столбец в таблицу
	 * 
	 * @param ci
	 *            информация о столбце
	 */
	public void addColumn(ColumnInfo ci) {
		this.columnInfos.put(ci.getColumnName(), ci);
	}

	/**
	 * @return массив имён столбцов
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
 * Метаданные одного столбца
 * 
 */
class ColumnInfo {
	/**
	 * Индекс
	 */
	private int index;

	/**
	 * Имя
	 */
	private String name;

	/**
	 * Тип
	 */
	private String type;

	/**
	 * Конструктор
	 * 
	 * @param index
	 *            индекс
	 * @param name
	 *            имя
	 * @param type
	 *            тип
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
	 * @return имя столбца
	 */
	public String getColumnName() {
		return this.name;
	}

	/**
	 * @return тип столбца
	 */
	public String getColumnType() {
		return this.type;
	}

	/**
	 * @return индекс столбца
	 */
	public int getColumnIndex() {
		return this.index;
	}
}