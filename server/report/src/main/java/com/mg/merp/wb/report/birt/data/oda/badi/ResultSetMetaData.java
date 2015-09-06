/*
 * ResultSetMetaData.java
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
package com.mg.merp.wb.report.birt.data.oda.badi;

import com.mg.merp.wb.report.birt.data.oda.badi.util.DataTypes;
import com.mg.merp.wb.report.birt.data.oda.badi.util.RelationInformation;

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Реализация интерфейса {@link IResultSetMetaData}
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: ResultSetMetaData.java,v 1.5 2006/12/05 13:43:33 poroxnenko Exp $
 */
public class ResultSetMetaData implements IResultSetMetaData {

  /**
   * Названия столбцов
   */
  private String[] columnNames;

  /**
   * Вспомогательная информация
   */
  private RelationInformation ri;

  /**
   * Конструктор
   */
  ResultSetMetaData(RelationInformation ri) {
    this.ri = ri;
    columnNames = ri.getTableColumnNames();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnCount()
   */
  public int getColumnCount() throws OdaException {
    return columnNames.length;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnName(int)
   */
  public String getColumnName(int index) throws OdaException {
    return columnNames[index - 1];
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnLabel(int)
   */
  public String getColumnLabel(int index) throws OdaException {
    return columnNames[index - 1];
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnType(int)
   */
  public int getColumnType(int index) throws OdaException {
    return DataTypes.getType(getColumnTypeName(index));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnTypeName(int)
   */
  public String getColumnTypeName(int index) throws OdaException {
    return ri.getTableColumnType(getColumnName(index));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnDisplayLength(int)
   */
  public int getColumnDisplayLength(int index) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getPrecision(int)
   */
  public int getPrecision(int index) throws OdaException {
    return -1;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getScale(int)
   */
  public int getScale(int index) throws OdaException {
    return -1;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#isNullable(int)
   */
  public int isNullable(int index) throws OdaException {
    return columnNullableUnknown;
  }
}
