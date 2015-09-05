/*
 * SQLExceptionTranslator.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.api.jdbc;

import java.sql.SQLException;

import com.mg.framework.api.DataAccessException;

/**
 * Interface to be implemented by classes that can translate
 * between SQLExceptions and our data access strategy-agnostic
 * {@link com.mg.framework.api.DataAccessException DataAccessException}.
 *
 * <p>Implementations can be generic (for example, using SQLState
 * codes for JDBC) or proprietary (for example, using Oracle
 * error codes) for greater precision.
 * 
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslator.java,v 1.4 2006/11/17 16:24:09 safonov Exp $
 */
public interface SQLExceptionTranslator {

	/**
	 * Translate the given SQL exception into a generic data access exception.
	 * 
	 * @param sqlException	SQLException encountered by JDBC implementation
	 * @return	generic data access exception or <code>null</code> if not handled
	 */
	DataAccessException translate(SQLException sqlException);

}
