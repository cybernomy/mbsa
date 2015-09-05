/*
 * RowCallbackHandler.java
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
 * <p>In contrast to a ResultSetExtractor, a RowCallbackHandler object is
 * typically stateful: It keeps the result state within the object, to be
 * available for later inspection. See RowCountCallbackHandler's javadoc
 * for a usage example with JdbcTemplate.
 *
 * <p>The ResultReader subinterface allows to make a results list available
 * in a uniform manner. JdbcTemplate's query methods will return the results
 * list in that case, else returning null (-> result state is solely
 * available from RowCallbackHandler object).
 *
 * <p>A convenient out-of-the-box implementation of RowCallbackHandler is the
 * RowMapperResultReader adapter which delegates row mapping to a RowMapper.
 * Note that a RowMapper object is typically stateless and thus reusable;
 * just the RowMapperResultReader adapter is stateful.
 * 
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @version $Id: RowCallbackHandler.java,v 1.2 2005/04/01 08:10:41 safonov Exp $
 *
 */
public interface RowCallbackHandler {
    
    /**
     * Implementations must implement this method to process each row of data
     * in the ResultSet. This method should not call next() on the ResultSet,
     * but extract the current values. Exactly what the implementation chooses
     * to do is up to it; a trivial implementation might simply count rows,
     * while another implementation might build an XML document.
     * @param rs the ResultSet to process
     * @throws SQLException if a SQLException is encountered getting
     * column values (that is, there's no need to catch SQLException)
     */
    void processRow(ResultSet rs) throws SQLException;
}
