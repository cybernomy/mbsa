/*
 * PreparedStatementSetter.java
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
 * Callback interface used by the JdbcTemplate class.
 *
 * <p>This interface sets values on a PreparedStatement provided by the JdbcTemplate class.
 * Implementations are responsible for setting any necessary parameters. SQL with placeholders will
 * already have been supplied.
 *
 * <p>It's easier to use this interface than PreparedStatementCreator, as the JdbcTemplate will
 * create the prepared statement.
 *
 * <p>Implementations <i>do not</i> need to concern themselves with SQLExceptions that may be thrown
 * from operations they attempt. The JdbcTemplate class will catch and handle SQLExceptions
 * appropriately.
 *
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @version $Id: PreparedStatementSetter.java,v 1.2 2005/04/01 08:10:41 safonov Exp $
 */
public interface PreparedStatementSetter {

  /**
   * Set values on the given PreparedStatement.
   *
   * @param ps PreparedStatement we'll invoke setter methods on
   * @throws SQLException there is no need to catch SQLExceptions that may be thrown in the
   *                      implementation of this method. The JdbcTemplate class will handle them.
   */
  void setValues(PreparedStatement ps) throws SQLException;
}
