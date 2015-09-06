/*
 * DataSetMetaData.java
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

import com.mg.merp.wb.report.birt.data.oda.badi.util.Constants;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Реализация интерфейса {@link IDataSetMetaData}
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: DataSetMetaData.java,v 1.6 2006/12/05 13:43:33 poroxnenko Exp $
 */
public class DataSetMetaData implements IDataSetMetaData {
  private IConnection connection = null;

  /**
   *
   * @param connection
   */
  public DataSetMetaData(Connection connection) {
    this.connection = connection;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getConnection()
   */
  public IConnection getConnection() throws OdaException {
    return this.connection;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceObjects(java.lang.String,
   *      java.lang.String, java.lang.String, java.lang.String)
   */
  public IResultSet getDataSourceObjects(String catalog, String schema,
                                         String object, String version) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceMajorVersion()
   */
  public int getDataSourceMajorVersion() throws OdaException {
    return Constants.DATA_SOURCE_MAJOR_VERSION;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceMinorVersion()
   */
  public int getDataSourceMinorVersion() throws OdaException {
    return Constants.DATA_SOURCE_MINOR_VERSION;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceProductName()
   */
  public String getDataSourceProductName() throws OdaException {
    return Constants.DATA_SOURCE_PRODUCT_NAME;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getDataSourceProductVersion()
   */
  public String getDataSourceProductVersion() throws OdaException {
    return String.valueOf(this.getDataSourceMajorVersion()) + "."
        + String.valueOf(this.getDataSourceMinorVersion());
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getSQLStateType()
   */
  public int getSQLStateType() throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsMultipleResultSets()
   */
  public boolean supportsMultipleResultSets() throws OdaException {
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsMultipleOpenResults()
   */
  public boolean supportsMultipleOpenResults() throws OdaException {
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsNamedResultSets()
   */
  public boolean supportsNamedResultSets() throws OdaException {
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsNamedParameters()
   */
  public boolean supportsNamedParameters() throws OdaException {
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsInParameters()
   */
  public boolean supportsInParameters() throws OdaException {
    return true;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#supportsOutParameters()
   */
  public boolean supportsOutParameters() throws OdaException {
    return false;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDataSetMetaData#getSortMode()
   */
  public int getSortMode() {
    throw new UnsupportedOperationException();
  }
}
