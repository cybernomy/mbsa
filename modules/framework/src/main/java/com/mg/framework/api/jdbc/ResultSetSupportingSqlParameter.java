/*
 * ResultSetSupportingSqlParameter.java
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
 * Common base class for ResultSet-supporting SqlParameters like
 * SqlOutParameter and SqlReturnResultSet.
 * 
 * @author Oleg V. Safonov
 * @author Juergen Hoeller
 * @version $Id: ResultSetSupportingSqlParameter.java,v 1.2 2005/06/01 12:43:18 safonov Exp $
 */
public class ResultSetSupportingSqlParameter<T> extends SqlParameter {

    private ResultSetExtractor resultSetExtractor = null;

    private RowCallbackHandler rowCallbackHandler = null;

    private RowMapper<T> rowMapper = null;

    private int rowsExpected = 0;

    /**
     * Create a new ResultSetSupportingSqlParameter, supplying name and SQL type.
     * @param name name of the parameter, as used in input and output maps
     * @param type SQL type of the parameter according to java.sql.Types
     */
    public ResultSetSupportingSqlParameter(String name, int type) {
        super(name, type);
    }

    public ResultSetSupportingSqlParameter(String name, int type, String typeName) {
        super(name, type, typeName);
    }

    public ResultSetSupportingSqlParameter(String name, int type, ResultSetExtractor resultSetExtractor) {
        super(name, type);
        this.resultSetExtractor = resultSetExtractor;
    }

    public ResultSetSupportingSqlParameter(String name, int type, RowCallbackHandler rch) {
        super(name, type);
        this.rowCallbackHandler = rch;
    }

    public ResultSetSupportingSqlParameter(String name, int type, RowMapper<T> rm) {
        super(name, type);
        this.rowMapper = rm;
    }

    public ResultSetSupportingSqlParameter(String name, int type, RowMapper<T> rm, int rowsExpected) {
        super(name, type);
        this.rowMapper = rm;
        this.rowsExpected = rowsExpected;
    }

    public boolean isResultSetSupported() {
        return (this.resultSetExtractor != null || this.rowCallbackHandler != null || this.rowMapper != null);
    }

    protected boolean isRowCallbackHandlerSupported() {
        return (this.rowCallbackHandler != null || this.rowMapper != null);
    }

    protected ResultSetExtractor getResultSetExtractor() {
        return resultSetExtractor;
    }

    /**
     * Return a new instance of the implementation of a RowCallbackHandler,
     * usable for returned ResultSets. This implementation invokes a given
     * RowMapper via the RowMapperResultReader adapter, respectively returns
     * a given RowCallbackHandler directly.
     * @see RowMapperResultReader
     */
    protected RowCallbackHandler getRowCallbackHandler() {
        if (this.rowMapper != null) {
            return new RowMapperResultReader<T>(this.rowMapper, this.rowsExpected);
        }
        else {
            return this.rowCallbackHandler;
        }
    }
    
}
