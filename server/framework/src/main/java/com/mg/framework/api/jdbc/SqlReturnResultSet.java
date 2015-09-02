/*
 * SqlReturnResultSet.java
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

/**
 * Subclass of SqlOutParameter to represent a returned ResultSet
 * from a stored procedure call.
 *
 * <p>Must declare a RowCallbackHandler to handle any returned rows.
 * No additional properties: instanceof will be used to check
 * for such types.
 *
 * <p>Returned ResultSets - like all stored procedure parameters -
 * must have names.
 * 
 * @author Oleg V. Safonov
 * @author Thomas Risberg
 * @version $Id: SqlReturnResultSet.java,v 1.2 2005/06/01 12:43:18 safonov Exp $
 */
public class SqlReturnResultSet<T> extends ResultSetSupportingSqlParameter<T> {

    public SqlReturnResultSet(String name, RowCallbackHandler rch) {
        super(name, 0, rch);
    }

    public SqlReturnResultSet(String name, RowMapper<T> rm) {
        super(name, 0, rm);
    }

    public SqlReturnResultSet(String name, RowMapper<T> rm, int rowsExpected) {
        super(name, 0, rm, rowsExpected);
    }
    
}
