/*
 * JdbcUtils.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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
package com.mg.framework.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC utilities
 *
 * @author Oleg V. Safonov
 */
public class JdbcUtils {
  private static com.mg.framework.api.Logger logger = ServerUtils.getLogger(JdbcUtils.class.getName());

  public static void closeResultSet(ResultSet rs) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException ex) {
        logger.warn("Could not close JDBC ResultSet", ex);
      }
    }
  }

  public static void closeStatement(Statement stmt) {
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException e) {
        logger.warn("Could not close JDBC Statement", e);
      }
    }
  }

  public static void closeConnection(Connection con) {
    if (con != null) {
      try {
        con.close();
      } catch (SQLException e) {
        logger.warn("Could not close JDBC connection", e);
      }
    }
  }

  /**
   * Retrieves the value of the designated column in the current row of this <code>ResultSet</code>
   * object as an <code>Integer</code> in the Java programming language.
   *
   * @param columnIndex the first column is 1, the second is 2, ...
   * @return the column value; if the value is SQL <code>NULL</code>, the value returned is
   * <code>null</code>
   * @throws SQLException if the columnIndex is not valid; if a database access error occurs or this
   *                      method is called on a closed result set
   */
  public static Integer getIntegerValue(ResultSet rs, int columnIndex) throws SQLException {
    return rs.getObject(columnIndex) == null ? null : rs.getInt(columnIndex);
  }

  /**
   * Retrieves the value of the designated column in the current row of this <code>ResultSet</code>
   * object as an <code>Integer</code> in the Java programming language.
   *
   * @param columnLabel the label for the column specified with the SQL AS clause.  If the SQL AS
   *                    clause was not specified, then the label is the name of the column
   * @return the column value; if the value is SQL <code>NULL</code>, the value returned is
   * <code>null</code>
   * @throws SQLException if the columnLabel is not valid; if a database access error occurs or this
   *                      method is called on a closed result set
   */
  public static Integer getIntegerValue(ResultSet rs, String columnName) throws SQLException {
    return rs.getObject(columnName) == null ? null : rs.getInt(columnName);
  }

}
