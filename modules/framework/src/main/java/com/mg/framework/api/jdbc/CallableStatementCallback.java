/*
 * CallableStatementCallback.java
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

import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 * Generic callback interface for code that operates on a CallableStatement. Allows to execute any
 * number of operations on a single CallableStatement, for example a single execute call or repeated
 * execute calls with varying parameters.
 *
 * <p>Used internally by JdbcTemplate, but also useful for application code. Note that the passed-in
 * CallableStatement can have been created by the framework or by a custom CallableStatementCreator.
 * However, the latter is hardly ever necessary, as most custom callback actions will perform
 * updates in which case a standard CallableStatement is fine. Custom actions will always set
 * parameter values themselves, so that CallableStatementCreator capability is not needed either.
 *
 * @author Oleg V. Safonov
 * @author Juergen Hoeller
 * @version $Id: CallableStatementCallback.java,v 1.2 2006/09/22 10:42:13 safonov Exp $
 * @see JdbcTemplate#execute(String, CallableStatementCallback)
 * @see JdbcTemplate#execute(CallableStatementCreator, CallableStatementCallback)
 */
public interface CallableStatementCallback<T> {

  /**
   * Gets called by JdbcTemplate.execute with an active JDBC CallableStatement. Does not need to
   * care about activating or closing the Connection, or handling transactions.
   *
   * <p>If called without a thread-bound JDBC transaction (initiated by
   * DataSourceTransactionManager), the code will simply get executed on the JDBC connection with
   * its transactional semantics. If JdbcTemplate is configured to use a JTA-aware DataSource, the
   * JDBC connection and thus the callback code will be transactional if a JTA transaction is
   * active.
   *
   * <p>Allows for returning a result object created within the callback, i.e. a domain object or a
   * collection of domain objects. A thrown RuntimeException is treated as application exception, it
   * gets propagated to the caller of the template.
   *
   * @param cs active JDBC CallableStatement
   * @return a result object, or null if none
   * @throws SQLException        if thrown by a JDBC method, to be auto-converted into a
   *                             DataAccessException by a SQLExceptionTranslator
   * @throws DataAccessException in case of custom exceptions
   */
  T doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException;

}
