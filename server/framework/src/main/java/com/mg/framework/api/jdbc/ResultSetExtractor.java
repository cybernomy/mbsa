/*
 * ResultSetExtractor.java
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

/** 
 * Callback interface used by JdbcTemplate's query methods.
 * Implementations of this interface perform the actual work of extracting
 * results, but don't need to worry about exception handling. SQLExceptions
 * will be caught and handled correctly by the JdbcTemplate class.
 *
 * <p>This interface is mainly used within the JDBC framework.
 * A RowCallbackHandler is usually a simpler choice for ResultSet processing,
 * in particular a RowMapperResultReader in combination with a RowMapper.
 *
 * <p>Note: In contrast to a RowCallbackHandler, a ResultSetExtractor object
 * is typically stateless and thus reusable, as long as it doesn't access
 * stateful resources (like output streams when streaming LOB contents)
 * or keep result state within the object.
 * 
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @version $Id: ResultSetExtractor.java,v 1.2 2005/04/01 08:10:41 safonov Exp $
 *
 */
public interface ResultSetExtractor {
    
    /** 
     * Implementations must implement this method to process
     * all rows in the ResultSet.
     * @param rs ResultSet to extract data from. Implementations should
     * not close this: it will be closed by the JdbcTemplate.
     * @return an arbitrary result object, or null if none
     * (the extractor will typically be stateful in the latter case).
     * @throws SQLException if a SQLException is encountered getting column
     * values or navigating (that is, there's no need to catch SQLException)
     * @throws DataAccessException in case of custom exceptions
     */
    Object extractData(ResultSet rs) throws SQLException, DataAccessException;
}
