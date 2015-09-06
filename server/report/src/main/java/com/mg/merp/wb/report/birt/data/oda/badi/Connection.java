/*
 * Connections.java
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

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;

import java.util.Map;
import java.util.Properties;

/**
 * Реализация интерфейса {@link IConnection}
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: Connection.java,v 1.7 2007/08/30 14:47:46 safonov Exp $
 */
public class Connection implements IConnection {

  // The boolean indicate whether the connection is open.
  private boolean isOpen;

  private Map<String, Object> appContext;

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#open(java.util.Properties)
   */
  public void open(Properties connProperties)
      throws org.eclipse.datatools.connectivity.oda.OdaException {
    isOpen = true;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#close()
   */
  public void close()
      throws org.eclipse.datatools.connectivity.oda.OdaException {
    isOpen = false;
    this.appContext = null;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#isOpen()
   */
  public boolean isOpen() throws OdaException {
    return isOpen;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#setAppContext(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public void setAppContext(Object context) throws OdaException {
    if (!(context instanceof Map))
      throw new OdaException("Connection.InvalidAppContext");
    this.appContext = (Map) context;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#getMetaData(java.lang.String)
   */
  public IDataSetMetaData getMetaData(String dataSetType) throws OdaException {
    return new DataSetMetaData(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#newQuery(java.lang.String)
   */
  public IQuery newQuery(String dataSetType) throws OdaException {
    return new Query(appContext);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#getMaxQueries()
   */
  public int getMaxQueries() throws OdaException {
    return 0;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#commit()
   */
  public void commit() throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IConnection#rollback()
   */
  public void rollback() throws OdaException {
    throw new UnsupportedOperationException();

  }
}
