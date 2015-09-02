/**
 * Connection.java
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
package com.mg.jet.birt.report.data.oda.ejbql;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;

/**
 * This class implements IConnection interface of ODA.
 * 
 * @author Oleg V. Safonov
 * @version $Id: Connection.java,v 1.2 2007/10/29 13:23:12 safonov Exp $
 */
public class Connection implements IConnection {

	private boolean isOpen = false;
	private String configfile = null;
	private String mapdir = null;
	private String jndiName = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.data.oda.IConnection#open(java.util.Properties)
	 */
	public void open(Properties connProperties) throws OdaException {

		// If the data source properties are changed the SessionFactory will
		// be rebuilt which is expensive. This was implemented this way as
		// as an example of connecting data source properties to the open
		// method.

		try {

			configfile = connProperties.getProperty("HIBCONFIG");
			mapdir = connProperties.getProperty("MAPDIR");
			jndiName = connProperties.getProperty("JNDI_NAME");

			HibernateUtil.constructSessionFactory(configfile, mapdir, jndiName);

			@SuppressWarnings("unused")
			Object testSession = HibernateUtil.currentSession();

			this.isOpen = true;
		} catch (Exception e) {
			throw new OdaException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.data.oda.IConnection#close()
	 */
	public void close() throws OdaException {
		this.isOpen = false;
		try {
			HibernateUtil.closeSession();
		} catch (Exception e) {
			throw new OdaException(e.getLocalizedMessage());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.data.oda.IConnection#isOpen()
	 */
	public boolean isOpen() throws OdaException {
		return this.isOpen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.data.oda.IConnection#getMetaData(java.lang.String)
	 */
	public IDataSetMetaData getMetaData(String dataSetType) throws OdaException {
		return new DataSetMetaData(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.data.oda.IConnection#createStatement(java.lang.String)
	 */
	public IQuery newQuery(String dataSetType) throws OdaException {
		if (!isOpen())
			throw new OdaException(Messages.getString("Common.CONNECTION_HAS_NOT_OPEN")); //$NON-NLS-1$

		return new Statement(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.data.oda.IConnection#commit()
	 */
	public void commit() throws OdaException {
		throw new UnsupportedOperationException();
	}

	public void setAppContext(Object obj) throws OdaException {
		// do nothing; no support for pass-through application context 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.data.oda.IConnection#rollback()
	 */
	public void rollback() throws OdaException {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.birt.data.oda.IConnection#getMaxQueries()
	 */
	public int getMaxQueries() throws OdaException {
		return 0;
	}

}