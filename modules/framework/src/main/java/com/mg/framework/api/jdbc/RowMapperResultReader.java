/*
 * RowMapper.java
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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Adapter implementation of the ResultReader interface that delegates to a RowMapper which is
 * supposed to create an object for each row. Each object is added to the results list of this
 * ResultReader.
 *
 * <p>Useful for the typical case of one object per row in the database table. The number of entries
 * in the results list will match the number of rows.
 *
 * <p>Note that a RowMapper object is typically stateless and thus reusable; just the
 * RowMapperResultReader adapter is stateful.
 *
 * <p>A usage example with JdbcTemplate:
 *
 * <pre>
 * JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);  // reusable object
 * RowMapper rowMapper = new UserRowMapper();  // reusable object
 *
 * List allUsers = jdbcTemplate.query("select * from user", new RowMapperResultReader(rowMapper,
 * 10));
 *
 * List userResults = jdbcTemplate.query("select * from user where id=?", new Object[] {id},
 *                                       new RowMapperResultReader(rowMapper, 1));
 * User user = (User) DataAccessUtils.uniqueResult(userResults);</pre>
 *
 * <p>Alternatively, consider subclassing MappingSqlQuery from the jdbc.object package: Instead of
 * working with separate JdbcTemplate and RowMapper objects, you can have executable query objects
 * (containing row-mapping logic) there.
 *
 * @author Juergen Hoeller
 * @see RowMapper
 * @see com.mg.framework.api.jdbc.MappingSqlQuery
 * @since 25.05.2004
 */
public class RowMapperResultReader<T> implements ResultReader {

  /**
   * List to save results in
   */
  private final List<T> results;

  /**
   * The RowMapper implementation that will be used to map rows
   */
  private final RowMapper<T> rowMapper;

  /**
   * The counter used to count rows
   */
  private int rowNum = 0;

  /**
   * Create a new RowMapperResultReader.
   *
   * @param rowMapper the RowMapper which creates an object for each row
   */
  public RowMapperResultReader(RowMapper<T> rowMapper) {
    this(rowMapper, 0);
  }

  /**
   * Create a new RowMapperResultReader.
   *
   * @param rowMapper    the RowMapper which creates an object for each row
   * @param rowsExpected the number of expected rows (just used for optimized collection handling)
   */
  public RowMapperResultReader(RowMapper<T> rowMapper, int rowsExpected) {
    // Use the more efficient collection if we know how many rows to expect:
    // ArrayList in case of a known row count, LinkedList if unknown
    this.results = (rowsExpected > 0) ? (List<T>) new ArrayList<T>(rowsExpected) : (List<T>) new LinkedList<T>();
    this.rowMapper = rowMapper;
  }

  public void processRow(ResultSet rs) throws SQLException {
    this.results.add(this.rowMapper.mapRow(rs, this.rowNum++));
  }

  public List getResults() {
    return this.results;
  }

}
