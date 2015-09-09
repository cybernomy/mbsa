/*
 * Driver.java
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

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * Реализация интерфейса {@link IDriver}
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: Driver.java,v 1.5 2006/12/05 13:43:33 poroxnenko Exp $
 */
public class Driver implements IDriver {
  /**
   *
   */
  public IConnection getConnection(String dataSourceId) throws OdaException {
    return new Connection();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDriver#setLogConfiguration(org.eclipse.datatools.connectivity.oda.LogConfiguration)
   */
  public void setLogConfiguration(LogConfiguration logConfig)
      throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDriver#getMaxConnections()
   */
  public int getMaxConnections() throws OdaException {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IDriver#setAppContext(java.lang.Object)
   */
  public void setAppContext(Object context) throws OdaException {
    throw new UnsupportedOperationException();
  }

}
