/**
 * DataSetMetaData.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.jet.birt.report.data.oda.ejbql;


import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * This class implement IDataSetMetaData interface of Oda.
 *
 * @author Oleg V. Safonov
 * @version $Id: DataSetMetaData.java,v 1.1 2007/10/29 08:33:24 safonov Exp $
 */
public class DataSetMetaData implements IDataSetMetaData {
  private IConnection connection;

  public DataSetMetaData(IConnection conn) {
    connection = conn;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#getConnection()
   */
  public IConnection getConnection() throws OdaException {
    return connection;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#getDataSourceObjects(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  public IResultSet getDataSourceObjects(String catalog, String schema,
                                         String object, String version) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#getDataSourceMajorVersion()
   */
  public int getDataSourceMajorVersion() throws OdaException {
    return CommonConstant.DRIVER_MAJOR_VERSION;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#getDataSourceMinorVersion()
   */
  public int getDataSourceMinorVersion() throws OdaException {
    return CommonConstant.DRIVER_MINOR_VERSION;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#getDataSourceProductName()
   */
  public String getDataSourceProductName() throws OdaException {
    return CommonConstant.DRIVER_NAME;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#getDataSourceProductVersion()
   */
  public String getDataSourceProductVersion() throws OdaException {
    String pv = new Integer(CommonConstant.DRIVER_MAJOR_VERSION).toString() + "." + new Integer(CommonConstant.DRIVER_MINOR_VERSION).toString();
    return pv;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#getSQLStateType()
   */
  public int getSQLStateType() throws OdaException {
    throw new UnsupportedOperationException();
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#supportsMultipleOpenResults()
   */
  public boolean supportsMultipleOpenResults() throws OdaException {
    return false;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#supportsMultipleResultSets()
   */
  public boolean supportsMultipleResultSets() throws OdaException {
    return false;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#supportsNamedResultSets()
   */
  public boolean supportsNamedResultSets() throws OdaException {
    return false;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#supportsNamedParameters()
   */
  public boolean supportsNamedParameters() throws OdaException {
    return true;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#supportsInParameters()
   */
  public boolean supportsInParameters() throws OdaException {
    return true;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#supportsOutParameters()
   */
  public boolean supportsOutParameters() throws OdaException {
    return false;
  }

  /* (non-Javadoc)
   * @see org.eclipse.birt.data.oda.IDataSetMetaData#getSortMode()
   */
  public int getSortMode() {
    return (sortModeNone);
  }

}
