/*
 * PreparedStatementCreator.java
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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * One of the two central callback interfaces used by the JdbcTemplate class. This interface creates
 * a PreparedStatement given a connection, provided by the JdbcTemplate class. Implementations are
 * responsible for providing SQL and any necessary parameters.
 *
 * <p>Implementations <i>do not</i> need to concern themselves with SQLExceptions that may be thrown
 * from operations they attempt. The JdbcTemplate class will catch and handle SQLExceptions
 * appropriately.
 *
 * <p>A PreparedStatementCreator should also implement the SqlProvider interface if it is able to
 * provide the SQL it uses for PreparedStatement creation. This allows for better contextual
 * information in case of exceptions.
 *
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @version $Id: PreparedStatementCreator.java,v 1.2 2005/04/01 08:10:41 safonov Exp $
 */
public interface PreparedStatementCreator {

  /**
   * Create a statement in this connection. Allows implementations to use PreparedStatements. The
   * JdbcTemplate will close the created statement.
   *
   * @param con Connection to use to create statement
   * @return a prepared statement
   * @throws SQLException there is no need to catch SQLExceptions that may be thrown in the
   *                      implementation of this method. The JdbcTemplate class will handle them.
   */
  PreparedStatement createPreparedStatement(Connection con) throws SQLException;
}