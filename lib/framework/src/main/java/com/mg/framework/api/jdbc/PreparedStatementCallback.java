/*
 * PreparedStatementCallback.java
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

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Generic callback interface for code that operates on a PreparedStatement.
 * Allows to execute any number of operations on a single PreparedStatement,
 * for example a single executeUpdate call or repeated executeUpdate calls
 * with varying parameters.
 *
 * <p>Used internally by JdbcTemplate, but also useful for application code.
 * Note that the passed-in PreparedStatement can have been created by the
 * framework or by a custom PreparedStatementCreator. However, the latter is
 * hardly ever necessary, as most custom callback actions will perform updates
 * in which case a standard PreparedStatement is fine. Custom actions will
 * always set parameter values themselves, so that PreparedStatementCreator
 * capability is not needed either.
 * 
 * @author Oleg V. Safonov
 * @author Juergen Hoeller 
 * @version $Id: PreparedStatementCallback.java,v 1.2 2005/04/01 08:10:41 safonov Exp $
 *
 */
public interface PreparedStatementCallback {
    
    /**
     * Gets called by JdbcTemplate.execute with an active JDBC PreparedStatement.
     * Does not need to care about activating or closing the Connection,
     * or handling transactions.
     *
     * <p>If called without a thread-bound JDBC transaction (initiated by
     * DataSourceTransactionManager), the code will simply get executed on the
     * JDBC connection with its transactional semantics. If JdbcTemplate is
     * configured to use a JTA-aware DataSource, the JDBC connection and thus
     * the callback code will be transactional if a JTA transaction is active.
     *
     * <p>Allows for returning a result object created within the callback, i.e.
     * a domain object or a collection of domain objects. Note that there's
     * special support for single step actions: see JdbcTemplate.queryForObject etc.
     * A thrown RuntimeException is treated as application exception, it gets
     * propagated to the caller of the template.
     *
     * @param ps active JDBC PreparedStatement
     * @return a result object, or null if none
     * @throws SQLException if thrown by a JDBC method, to be auto-converted
     * into a DataAccessException by a SQLExceptionTranslator
     * @throws DataAccessException in case of custom exceptions
     * @see JdbcTemplate#queryForObject(String, Object[], Class)
     * @see JdbcTemplate#queryForList(String, Object[])
     */
    Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException;
}
