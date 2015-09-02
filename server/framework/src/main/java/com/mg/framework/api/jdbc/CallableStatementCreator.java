/*
 * CallableStatementCreator.java
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
import java.sql.Connection;
import java.sql.SQLException;

/**
 * One of the three central callback interfaces used by the JdbcTemplate class.
 * This interface creates a CallableStatement given a connection, provided
 * by the JdbcTemplate class. Implementations are responsible for providing
 * SQL and any necessary parameters.
 *
 * <p>Implementations <i>do not</i> need to concern themselves with
 * SQLExceptions that may be thrown from operations they attempt.
 * The JdbcTemplate class will catch and handle SQLExceptions appropriately.
 *
 * <p>A PreparedStatementCreator should also implement the SqlProvider interface
 * if it is able to provide the SQL it uses for PreparedStatement creation.
 * This allows for better contextual information in case of exceptions.
 * 
 * @see JdbcTemplate#execute(CallableStatementCreator, CallableStatementCallback)
 * @see JdbcTemplate#call
 * @see SqlProvider
 * 
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @author Thomas Risberg
 * @version $Id: CallableStatementCreator.java,v 1.1 2005/04/01 08:11:35 safonov Exp $
 */
public interface CallableStatementCreator {

    /** 
     * Create a callable statement in this connection. Allows implementations to use
     * CallableStatements. 
     * @param con Connection to use to create statement
     * @return a callable statement
     * @throws SQLException there is no need to catch SQLExceptions
     * that may be thrown in the implementation of this method.
     * The JdbcTemplate class will handle them.
     */
    CallableStatement createCallableStatement(Connection con) throws SQLException;
    
}
