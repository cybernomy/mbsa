/*
 * ResultSet.java
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
package com.mg.merp.wb.report.birt.data.oda.badi;

import com.mg.framework.api.dataset.DataSet;
import com.mg.merp.wb.report.birt.data.oda.badi.util.RelationInformation;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Реализация интерфейса {@link IResultSet}
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: ResultSet.java,v 1.9 2007/12/27 15:01:04 safonov Exp $
 */
public class ResultSet implements IResultSet {

  /**
   * Метаданные результирующего набора данных
   */
  private ResultSetMetaData rsMetaData;

  /**
   * Индикатор того, что последний вызов getX() вернул null
   */
  private boolean wasNull;

  /**
   * флаг, показывающий, закрыт набор данных, или нет
   */
  private boolean isClosed;

  /**
   * Результирующий набор данных
   */
  private DataSet rs;

  /**
   * the max number of rows can be fetched from this result set.
   */
  private int maxRows;

  /**
   * Конструктор
   *
   * @param ri          Метаданные результата выполнения запроса {@link Query}
   * @param merpDataSet результат выполнения запроса {@link Query}
   */
  public ResultSet(RelationInformation ri, DataSet merpDataSet, int maxRows)
      throws OdaException {
    this.rsMetaData = new ResultSetMetaData(ri);
    isClosed = false;
    rs = merpDataSet;
    rs.beforeFirstRow();
    this.maxRows = maxRows;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getMetaData()
   */
  public IResultSetMetaData getMetaData() throws OdaException {
    testClosed();
    return rsMetaData;
  }

  /**
   * @throws OdaException
   */
  private void testClosed() throws OdaException {
    if (isClosed)
      throw new OdaException("ResultSet.ResultSetClosed"); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#close()
   */
  public void close() throws OdaException {
    this.rsMetaData = null;
    this.isClosed = true;
    this.rs = null;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#setMaxRows(int)
   */
  public void setMaxRows(int maxRows) throws OdaException {
    testClosed();
    this.maxRows = maxRows;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#next()
   */
  public boolean next() throws OdaException {
    testClosed();
    if (maxRows != 0 && rs.getCurPos() >= maxRows)
      return false;

    rs.nextRow();
    if (!rs.isEndOfSet())
      return true;
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getRow()
   */
  public int getRow() throws OdaException {
    testClosed();
    return rs.getCurPos();

  }

  /**
   * Возвращает значение из набора данных по текущему курсору и имени столбца
   *
   * @param name имя поля
   * @return значени поля
   */
  private Object getValue(String name) throws OdaException {
    testClosed();
    Object result = rs.getValueAt(name);
    this.wasNull = result == null;
    return result;
  }

  /**
   * Возвращает значение из набора данных по текущему курсору и индексу столбца
   *
   * @param index индекс поля
   * @return значени поля
   */
  private Object getValue(int index) throws OdaException {
    return getValue(rsMetaData.getColumnName(index));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getString(int)
   */
  public String getString(int index) throws OdaException {
    return (String) getValue(index);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getString(java.lang.String)
   */
  public String getString(String name) throws OdaException {
    return (String) getValue(name);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getInt(int)
   */
  public int getInt(int index) throws OdaException {
    Object value = getValue(index);
    return this.wasNull ? 0 : ((Integer) value).intValue();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getInt(java.lang.String)
   */
  public int getInt(String name) throws OdaException {
    Object value = getValue(name);
    return this.wasNull ? 0 : ((Integer) value).intValue();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDouble(int)
   */
  public double getDouble(int index) throws OdaException {
    Object value = getValue(index);
    return this.wasNull ? 0 : ((Double) value).doubleValue();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDouble(java.lang.String)
   */
  public double getDouble(String name) throws OdaException {
    Object value = getValue(name);
    return this.wasNull ? 0 : ((Double) value).doubleValue();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBigDecimal(int)
   */
  public BigDecimal getBigDecimal(int index) throws OdaException {
    return (BigDecimal) getValue(index);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBigDecimal(java.lang.String)
   */
  public BigDecimal getBigDecimal(String name) throws OdaException {
    return (BigDecimal) getValue(name);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDate(int)
   */
  public Date getDate(int index) throws OdaException {
    //MBSA работает с java.util.Date
    Object v = getValue(index);
    if (v == null)
      return null;
    if (v instanceof Date)
      return (Date) v;
    else
      return new Date(((java.util.Date) v).getTime());
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getDate(java.lang.String)
   */
  public Date getDate(String columnName) throws OdaException {
    // MBSA работает с java.util.Date
    Object v = getValue(columnName);
    if (v == null)
      return null;
    if (v instanceof java.sql.Date)
      return (Date) v;
    else
      return new Date(((java.util.Date) v).getTime());
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTime(int)
   */
  public Time getTime(int index) throws OdaException {
    return (Time) getValue(index);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTime(java.lang.String)
   */
  public Time getTime(String columnName) throws OdaException {
    return (Time) getValue(columnName);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTimestamp(int)
   */
  public Timestamp getTimestamp(int index) throws OdaException {
    return (Timestamp) getValue(index);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getTimestamp(java.lang.String)
   */
  public Timestamp getTimestamp(String columnName) throws OdaException {
    return (Timestamp) getValue(columnName);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#wasNull()
   */
  public boolean wasNull() throws OdaException {
    testClosed();
    return this.wasNull;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#findColumn(java.lang.String)
   */
  public int findColumn(String columnName) throws OdaException {
    testClosed();
    return this.getColumnIndex(columnName);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(int)
   */
  public IBlob getBlob(int index) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBlob(java.lang.String)
   */
  public IBlob getBlob(String columnName) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(int)
   */
  public IClob getClob(int index) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getClob(java.lang.String)
   */
  public IClob getClob(String columnName) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /* (non-Javadoc)
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBoolean(int)
   */
  public boolean getBoolean(int index) throws OdaException {
    Object value = getValue(index);
    return this.wasNull ? false : (Boolean) value;
  }

  /* (non-Javadoc)
   * @see org.eclipse.datatools.connectivity.oda.IResultSet#getBoolean(java.lang.String)
   */
  public boolean getBoolean(String columnName) throws OdaException {
    Object value = getValue(columnName);
    return this.wasNull ? false : (Boolean) value;
  }

  /**
   * Return the index of a column
   */
  private int getColumnIndex(String columnName) throws OdaException {
    for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
      if (rsMetaData.getColumnName(i).equals(columnName))
        return i;
    }
    throw new OdaException("Invalid column name");
  }
}
