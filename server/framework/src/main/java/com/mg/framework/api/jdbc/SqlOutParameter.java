/*
 * SqlOutParameter.java
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
 * Subclass of SqlParameter to represent an output parameter.
 * No additional properties: instanceof will be used to check
 * for such types.
 *
 * <p>Output parameters - like all stored procedure parameters -
 * must have names.
 * 
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @author Thomas Risberg
 * @author Juergen Hoeller
 * @version $Id: SqlOutParameter.java,v 1.2 2005/06/01 12:43:18 safonov Exp $
 */
public class SqlOutParameter<T> extends ResultSetSupportingSqlParameter<T> {

    /**
     * Create a new SqlOutParameter, supplying name and SQL type.
     * @param name name of the parameter, as used in input and output maps
     * @param type SQL type of the parameter according to java.sql.Types
     */
    public SqlOutParameter(String name, int type) {
        super(name, type);
    }

    public SqlOutParameter(String name, int type, String typeName) {
        super(name, type, typeName);
    }

    public SqlOutParameter(String name, int type, ResultSetExtractor rse) {
        super(name, type, rse);
    }

    public SqlOutParameter(String name, int type, RowCallbackHandler rch) {
        super(name, type, rch);
    }

    public SqlOutParameter(String name, int type, RowMapper<T> rm) {
        super(name, type, rm);
    }

    public SqlOutParameter(String name, int type, RowMapper<T> rm, int rowsExpected) {
        super(name, type, rm, rowsExpected);
    }
    
}
