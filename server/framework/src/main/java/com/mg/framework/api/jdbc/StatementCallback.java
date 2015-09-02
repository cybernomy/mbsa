/*
 * StatementCallback.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.jdbc;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Generic callback interface for code that operates on a JDBC Statement.
 * Allows to execute any number of operations on a single Statement,
 * for example a single executeUpdate call or repeated executeUpdate
 * calls with varying parameters.
 *
 * <p>Used internally by JdbcTemplate, but also useful for application code.
 * 
 * @author Oleg V. Safonov
 * @author Juergen Hoeller
 * @version $Id: StatementCallback.java,v 1.1 2005/04/01 08:11:35 safonov Exp $
 */
public interface StatementCallback {
    Object doInStatement(Statement stmt) throws SQLException, DataAccessException;
}
