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

/** 
 * An interface used by JdbcTemplate for mapping returned result sets.
 * Implementations of this interface perform the actual work of mapping
 * rows, but don't need to worry about exception handling. SQLExceptions
 * will be caught and handled correctly by the JdbcTemplate class.
 *
 * <p>Typically used either for JdbcTemplate's query methods (with
 * RowMapperResultReader adapters) or for out parameters of stored procedures.
 * RowMapper objects are typically stateless and thus reusable; they are
 * ideal choices for implementing row-mapping logic in a single place.
 *
 * <p>Alternatively, consider subclassing MappingSqlQuery from the jdbc.object
 * package: Instead of working with separate JdbcTemplate and RowMapper objects,
 * you can have executable query objects (containing row-mapping logic) there.
 * 
 * @author Oleg V. Safonov
 * @author Thomas Risberg
 * @version $Id: RowMapper.java,v 1.3 2005/06/01 12:43:18 safonov Exp $
 *
 */
public interface RowMapper<T> {
    
    /** 
     * Implementations must implement this method to map each row of data
     * in the ResultSet. This method should not call next() on the ResultSet,
     * but extract the current values. 
     * @param rs the ResultSet to map
     * @param rowNum The number of the current row
     * @throws SQLException if a SQLException is encountered getting
     * column values (that is, there's no need to catch SQLException)
     */
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
