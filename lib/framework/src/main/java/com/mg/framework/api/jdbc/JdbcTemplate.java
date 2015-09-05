/*
 * JdbcTemplate.java
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
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mg.framework.api.Logger;
import com.mg.framework.service.SQLExceptionTranslatorManagerLocator;
import com.mg.framework.utils.JdbcUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * <b>This is the central class in the JDBC core package.</b>
 * It simplifies the use of JDBC and helps to avoid common errors.
 * It executes core JDBC workflow, leaving application code to provide SQL
 * and extract results. This class executes SQL queries or updates, initiating
 * iteration over ResultSets and catching JDBC exceptions and translating
 * them to the generic, more informative exception hierarchy defined in the
 * org.springframework.dao package.
 *
 * <p>Code using this class need only implement callback interfaces, giving
 * them a clearly defined contract. The PreparedStatementCreator callback
 * interface creates a prepared statement given a Connection provided by this
 * class, providing SQL and any necessary parameters. The RowCallbackHandler
 * interface extracts values from each row of a ResultSet.
 *
 * <p>Can be used within a service implementation via direct instantiation
 * with a DataSource reference, or get prepared in an application context
 * and given to services as bean reference. Note: The DataSource should
 * always be configured as a bean in the application context, in the first case
 * given to the service directly, in the second case to the prepared template.
 *
 * <p>The motivation and design of this class is discussed
 * in detail in
 * <a href="http://www.amazon.com/exec/obidos/tg/detail/-/0764543857/">Expert One-On-One J2EE Design and Development</a>
 * by Rod Johnson (Wrox, 2002).
 *
 * <p>Because this class is parameterizable by the callback interfaces and
 * the SQLExceptionTranslator interface, it isn't necessary to subclass it.
 * All SQL issued by this class is logged.
 * 
 * @see ResultSetExtractor
 * @see RowCallbackHandler
 * @see RowMapper
 * 
 * @author Oleg V. Safonov
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Yann Caroff
 * @author Thomas Risberg
 * @author Isabelle Muszynski
 * @version $Id: JdbcTemplate.java,v 1.7 2006/12/26 14:35:31 safonov Exp $
 */
public class JdbcTemplate implements JdbcOperations {
    private Logger logger = ServerUtils.getLogger(getClass().getName());
    private Connection connection = null;

    public static JdbcTemplate getInstance() {
        return new JdbcTemplate();
    }
    
    //-------------------------------------------------------------------------
    // Methods dealing with static SQL (java.sql.Statement)
    //-------------------------------------------------------------------------

    public Object execute(final StatementCallback action) throws DataAccessException {
        Connection con = getConnection();
        Statement stmt = null;
        try {
            Connection conToUse = con;
            /*if (this.nativeJdbcExtractor != null &&
                    this.nativeJdbcExtractor.isNativeConnectionNecessaryForNativeStatements()) {
                conToUse = this.nativeJdbcExtractor.getNativeConnection(con);
            }*/
            stmt = conToUse.createStatement();
            //DataSourceUtils.applyTransactionTimeout(stmt, getDataSource());
            Statement stmtToUse = stmt;
            /*if (this.nativeJdbcExtractor != null) {
                stmtToUse = this.nativeJdbcExtractor.getNativeStatement(stmt);
            }*/
            Object result = action.doInStatement(stmtToUse);
            SQLWarning warning = stmt.getWarnings();
            throwExceptionOnWarningIfNotIgnoringWarnings(warning);
            return result;
        }
        catch (SQLException ex) {
            throw SQLExceptionTranslatorManagerLocator.locate().translate(ex);
        }
        finally {
            JdbcUtils.closeStatement(stmt);
            JdbcUtils.closeConnection(con);
        }
    }

    public void execute(final String sql) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL statement [" + sql + "]"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        execute(new StatementCallback() {
            public Object doInStatement(Statement stmt) throws SQLException {
                stmt.execute(sql);
                return null;
            }
        });
    }

    public Object query(final String sql, final ResultSetExtractor rse) throws DataAccessException {
        if (sql == null) {
            throw new InvalidDataAccessApiUsageException("SQL must not be null"); //$NON-NLS-1$
        }
        if (sql.indexOf("?") != -1) { //$NON-NLS-1$
            throw new InvalidDataAccessApiUsageException(
                    "Cannot execute [" + sql + "] as a static query: it contains bind variables"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL query [" + sql + "]"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return execute(new StatementCallback() {
            public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
                ResultSet rs = null;
                try {
                    rs = stmt.executeQuery(sql);
                    ResultSet rsToUse = rs;
                    /*if (nativeJdbcExtractor != null) {
                        rsToUse = nativeJdbcExtractor.getNativeResultSet(rs);
                    }*/
                    return rse.extractData(rsToUse);
                }
                finally {
                    JdbcUtils.closeResultSet(rs);
                }
            }
        });
    }

    public List query(String sql, RowCallbackHandler rch) throws DataAccessException {
        return (List) query(sql, new RowCallbackHandlerResultSetExtractor(rch));
    }
    
    public List queryForList(String sql) throws DataAccessException {
        return (List) query(sql, new ListResultSetExtractor());
    }

    public int update(final String sql) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL update [" + sql + "]"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        Integer result = (Integer) execute(new StatementCallback() {
            public Object doInStatement(Statement stmt) throws SQLException {
                int rows = stmt.executeUpdate(sql);
                if (logger.isDebugEnabled()) {
                    logger.debug("SQL update affected " + rows + " rows"); //$NON-NLS-1$ //$NON-NLS-2$
                }
                return new Integer(rows);
            }
        });
        return result.intValue();
    }
    
	//-------------------------------------------------------------------------
	// Methods dealing with prepared statements
	//-------------------------------------------------------------------------
    
    public Object execute(PreparedStatementCreator psc, PreparedStatementCallback action) throws DataAccessException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = psc.createPreparedStatement(con);
			Object result = action.doInPreparedStatement(ps);
			SQLWarning warning = ps.getWarnings();
			throwExceptionOnWarningIfNotIgnoringWarnings(warning);
			return result;
		}
		catch (SQLException e) {
			throw SQLExceptionTranslatorManagerLocator.locate().translate(e);
		}
		finally {
			JdbcUtils.closeStatement(ps);
			JdbcUtils.closeConnection(con);
		}
    }
    
    public Object execute(final String sql, PreparedStatementCallback action) throws DataAccessException {
        return execute(new SimplePreparedStatementCreator(sql), action);
    }

	/**
	 * Query using a prepared statement, allowing for a PreparedStatementCreator
	 * and a PreparedStatementSetter. Most other query methods use this method,
	 * but application code will always work with either a creator or a setter.
	 * @param psc Callback handler that can create a PreparedStatement given a
	 * Connection
	 * @param pss object that knows how to set values on the prepared statement.
	 * If this is null, the SQL will be assumed to contain no bind parameters.
	 * @param rse object that will extract results.
	 * @return an arbitrary result object, as returned by the ResultSetExtractor
	 * @throws DataAccessException if there is any problem
	 */
    protected Object query(PreparedStatementCreator psc, final PreparedStatementSetter pss,
			 final ResultSetExtractor rse) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            String sql = getSql(psc);
            logger.debug("Executing SQL query" + (sql != null ? " [" + sql  + "]" : "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
		return execute(psc, new PreparedStatementCallback() {
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				if (pss != null) {
					pss.setValues(ps);
				}
				ResultSet rs = null;
				try {
					rs = ps.executeQuery();
					ResultSet rsToUse = rs;
					return rse.extractData(rsToUse);
				}
				finally {
					JdbcUtils.closeResultSet(rs);
				}
			}
		});
    }
    
    public Object query(PreparedStatementCreator psc, ResultSetExtractor rse) throws DataAccessException {
        return query(psc, null, rse);
    }
    
    public Object query(final String sql, final PreparedStatementSetter pss, final ResultSetExtractor rse)  throws DataAccessException {
		if (sql == null) {
			throw new InvalidDataAccessApiUsageException("SQL may not be null"); //$NON-NLS-1$
		}
		return query(new SimplePreparedStatementCreator(sql), pss, rse);
    }

    @SuppressWarnings("unchecked") //$NON-NLS-1$
	public <T> List<T> query(String sql, final PreparedStatementSetter pss, final RowCallbackHandler rch) throws DataAccessException {
	    return (List<T>) query(sql, pss, new RowCallbackHandlerResultSetExtractor(rch));
	}

	public <T> List<T> query(String sql, final Object[] args, RowCallbackHandler rch) throws DataAccessException {
	    return query(sql, new ArgPreparedStatementSetter(args), rch);
	}

    public <T> List<T> query(String sql, final Object[] args, RowMapper<T> rm) throws DataAccessException {
        return query(sql, args, new RowMapperResultReader<T>(rm));
    }
    
    protected int update(PreparedStatementCreator psc, final PreparedStatementSetter pss) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            String sql = getSql(psc);
            logger.debug("Executing SQL update" + (sql != null ? " [" + sql  + "]" : "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
        Integer result = (Integer) execute(psc, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException {
                if (pss != null) {
                    pss.setValues(ps);
                }
                int rows = ps.executeUpdate();
                if (logger.isDebugEnabled()) {
                    logger.debug("SQL update affected " + rows + " rows"); //$NON-NLS-1$ //$NON-NLS-2$
                }
                return new Integer(rows);
            }
        });
        return result.intValue();
    }
    
    public int update(PreparedStatementCreator psc) throws DataAccessException {
        return update(psc, null);
    }

    public int update(String sql, final PreparedStatementSetter pss) throws DataAccessException {
        return update(new SimplePreparedStatementCreator(sql), pss);
    }

    public int update(String sql, final Object[] args, final int[] argTypes) throws DataAccessException {
        return update(sql, new ArgTypePreparedStatementSetter(args, argTypes));
    }

    public int update(String sql, final Object[] args) throws DataAccessException {
        return update(sql, new ArgPreparedStatementSetter(args));
    }
    
    public int[] batchUpdate(String sql, final BatchPreparedStatementSetter pss) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing SQL batch update [" + sql + "]"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return (int[]) execute(sql, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement ps) throws SQLException {
                int batchSize = pss.getBatchSize();
                DatabaseMetaData dbmd = ps.getConnection().getMetaData();
                if (dbmd != null && dbmd.supportsBatchUpdates()) {
                    for (int i = 0; i < batchSize; i++) {
                        pss.setValues(ps, i);
                        ps.addBatch();
                    }
                    return ps.executeBatch();
                }
                else {
                    int[] rowsAffected = new int[batchSize];
                    for (int i = 0; i < batchSize; i++) {
                        pss.setValues(ps, i);
                        rowsAffected[i] = ps.executeUpdate();
                    }
                    return rowsAffected;
                }
            }
        });
    }
    
    //-------------------------------------------------------------------------
    // Methods dealing with callable statements
    //-------------------------------------------------------------------------

    public <T> T execute(CallableStatementCreator csc, CallableStatementCallback<T> action) throws DataAccessException {
        if (logger.isDebugEnabled()) {
            String sql = getSql(csc);
            logger.debug("Calling stored procedure" + (sql != null ? " [" + sql  + "]" : "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
        Connection con = getConnection();
        CallableStatement cs = null;
        try {
            Connection conToUse = con;
            /*if (this.nativeJdbcExtractor != null &&
                    this.nativeJdbcExtractor.isNativeConnectionNecessaryForNativeCallableStatements()) {
                conToUse = this.nativeJdbcExtractor.getNativeConnection(con);
            }*/
            cs = csc.createCallableStatement(conToUse);
            //DataSourceUtils.applyTransactionTimeout(cs, getDataSource());
            CallableStatement csToUse = cs;
            /*if (nativeJdbcExtractor != null) {
                csToUse = nativeJdbcExtractor.getNativeCallableStatement(cs);
            }*/
            T result = action.doInCallableStatement(csToUse);
            SQLWarning warning = cs.getWarnings();
            throwExceptionOnWarningIfNotIgnoringWarnings(warning);
            return result;
        }
        catch (SQLException ex) {
            throw SQLExceptionTranslatorManagerLocator.locate().translate(ex);
        }
        finally {
            JdbcUtils.closeStatement(cs);
            JdbcUtils.closeConnection(con);
        }
    }

    public <T> Object execute(final String callString, CallableStatementCallback<T> action) throws DataAccessException {
        return execute(new SimpleCallableStatementCreator(callString), action);
    }

    public Map<String, Object> call(CallableStatementCreator csc, final List<Object> declaredParameters) throws DataAccessException {
        return execute(csc, new CallableStatementCallback<Map<String, Object>>() {
            public Map<String, Object> doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
                boolean retVal = cs.execute();
                if (logger.isDebugEnabled()) {
                    logger.debug("CallableStatement.execute returned [" + retVal + "]"); //$NON-NLS-1$ //$NON-NLS-2$
                }
                Map<String, Object> returnedResults = new HashMap<String, Object>();
                if (retVal) {
                    returnedResults.putAll(extractReturnedResultSets(cs, declaredParameters));
                }
                returnedResults.putAll(extractOutputParameters(cs, declaredParameters));
                return returnedResults;
            }
        });
    }
    
    /**
     * Extract returned ResultSets from the completed stored procedure.
     * @param cs JDBC wrapper for the stored procedure
     * @param parameters Parameter list for the stored procedure
     * @return Map that contains returned results
     */
    protected Map<String, Object> extractReturnedResultSets(CallableStatement cs, List parameters) throws SQLException, DataAccessException {
        Map<String, Object> returnedResults = new HashMap<String, Object>();
        int rsIndex = 0;
        do {
            Object param = null;
            if (parameters != null && parameters.size() > rsIndex) {
                param = parameters.get(rsIndex);
            }
            if (param instanceof SqlReturnResultSet) {
                SqlReturnResultSet rsParam = (SqlReturnResultSet) param;
                returnedResults.putAll(processResultSet(cs.getResultSet(), rsParam));
            }
            else {
                logger.warn("ResultSet returned from stored procedure but a corresponding " + //$NON-NLS-1$
                                        "SqlReturnResultSet parameter was not declared"); //$NON-NLS-1$
            }
            rsIndex++;
        }
        while (cs.getMoreResults());
        return returnedResults;
    }
    
    /**
     * Extract output parameters from the completed stored procedure.
     * @param cs JDBC wrapper for the stored procedure
     * @param parameters parameter list for the stored procedure
     * @return parameters to the stored procedure
     * @return Map that contains returned results
     */
    protected Map<String, Object> extractOutputParameters(CallableStatement cs, List<Object> parameters) throws SQLException, DataAccessException {
        Map<String, Object> returnedResults = new HashMap<String, Object>();
        int sqlColIndex = 1;
        for (int i = 0; i < parameters.size(); i++) {
            Object param = parameters.get(i);
            if (param instanceof SqlOutParameter) {
                SqlOutParameter outParam = (SqlOutParameter) param;
                Object out = cs.getObject(sqlColIndex);
                if (out instanceof ResultSet) {
                    if (outParam.isResultSetSupported()) {
                        returnedResults.putAll(processResultSet((ResultSet) out, outParam));
                    }
                    else {
                        logger.warn("ResultSet returned from stored procedure but a corresponding " + //$NON-NLS-1$
                                                "SqlOutParameter with a RowCallbackHandler was not declared"); //$NON-NLS-1$
                        returnedResults.put(outParam.getName(), "ResultSet was returned but not processed."); //$NON-NLS-1$
                    }
                }
                else {
                    returnedResults.put(outParam.getName(), out);
                }
            }
            if (!(param instanceof SqlReturnResultSet)) {
                sqlColIndex++;
            }
        }
        return returnedResults;
    }
    
    /**
     * Process the given ResultSet from a stored procedure.
     * @param rs the ResultSet to process
     * @param param the corresponding stored procedure parameter
     * @return Map that contains returned results
     */
    protected Map<String, Object> processResultSet(ResultSet rs, ResultSetSupportingSqlParameter param) throws SQLException, DataAccessException {
        Map<String, Object> returnedResults = new HashMap<String, Object>();
        try {
            ResultSet rsToUse = rs;
            /*if (this.nativeJdbcExtractor != null) {
                rsToUse = this.nativeJdbcExtractor.getNativeResultSet(rs);
            }*/
            if (param.isRowCallbackHandlerSupported()) {
                // It's a RowCallbackHandler or RowMapper.
                // We'll get a RowCallbackHandler to use in both cases.
                RowCallbackHandler rch = param.getRowCallbackHandler();
                (new RowCallbackHandlerResultSetExtractor(rch)).extractData(rsToUse);
                if (rch instanceof ResultReader) {
                    returnedResults.put(param.getName(), ((ResultReader) rch).getResults());
                }
                else {
                    returnedResults.put(param.getName(), "ResultSet returned from stored procedure was processed."); //$NON-NLS-1$
                }
            }
            else {
                // It's a ResultSetExtractor - simply apply it.
                Object result = param.getResultSetExtractor().extractData(rsToUse);
                returnedResults.put(param.getName(), result);
            }
        }
        finally {
            JdbcUtils.closeResultSet(rs);
        }
        return returnedResults;
    }
    
    //-------------------------------------------------------------------------
    
    private Connection getConnection() {
    	if (connection == null)
    		return ServerUtils.getConnection();
    	else
    		return connection;
    }

    private void throwExceptionOnWarningIfNotIgnoringWarnings(SQLWarning warning) throws SQLWarningException {
		/*if (warning != null) {
			if (this.ignoreWarnings) {
				logger.warn("SQLWarning ignored: " + warning);
			}
			else {
				throw new SQLWarningException("Warning not ignored", warning);
			}
		}*/
    }
    
    private String getSql(Object sqlProvider) {
		if (sqlProvider instanceof SqlProvider) {
			return ((SqlProvider) sqlProvider).getSql();
		}
		else {
			return null;
		}
    }
    
	private static class SimplePreparedStatementCreator
		implements PreparedStatementCreator, SqlProvider {
	    private final String sql;

	    public SimplePreparedStatementCreator(String sql) {
	        this.sql = sql;
	    }

	    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
	        return con.prepareStatement(this.sql);
	    }

	    public String getSql() {
	        return sql;
	    }
	}

    private static class ArgPreparedStatementSetter implements PreparedStatementSetter {
        private final Object[] args;
        
        public ArgPreparedStatementSetter(Object[] args) {
            this.args = args;
        }
        
        public void setValues(PreparedStatement ps) throws SQLException {
			if (this.args != null) {
				for (int i = 0; i < this.args.length; i++) {
					ps.setObject(i + 1, this.args[i]);
				}
			}
        }
    }
    
    private static class RowCallbackHandlerResultSetExtractor implements ResultSetExtractor {
		private final RowCallbackHandler rch;

		public RowCallbackHandlerResultSetExtractor(RowCallbackHandler rch) {
			this.rch = rch;
		}

		public Object extractData(ResultSet rs) throws SQLException {
			while (rs.next()) {
				this.rch.processRow(rs);
			}
			if (this.rch instanceof ResultReader) {
				return ((ResultReader) this.rch).getResults();
			}
			else {
				return null;
			}
		}
    }
    
    /**
     * ResultSetExtractor implementation that returns an ArrayList of HashMaps.
     */
    private static class ListResultSetExtractor implements ResultSetExtractor {

        public Object extractData(ResultSet rs) throws SQLException {
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            List<Map<String, Object>> listOfRows = new ArrayList<Map<String, Object>>();
            while (rs.next()) {
                Map<String, Object> mapOfColValues = null;
                mapOfColValues = LinkedHashMapCreator.createLinkedHashMap(numberOfColumns);
                for (int i = 1; i <= numberOfColumns; i++) {
                    mapOfColValues.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                listOfRows.add(mapOfColValues);
            }
            return listOfRows;
        }
    }
    
    /**
     * Actual creation of a java.util.LinkedHashMap.
     * In separate inner class to avoid runtime dependency on JDK 1.4.
     */
    private static abstract class LinkedHashMapCreator {

        private static Map<String, Object> createLinkedHashMap(int capacity) {
            return new LinkedHashMap<String, Object>(capacity);
        }
    }
    
    /**
     * Simple adapter for CallableStatementCreator, allowing to use a plain SQL statement.
     */
    private static class SimpleCallableStatementCreator
            implements CallableStatementCreator, SqlProvider {

        private final String callString;

        public SimpleCallableStatementCreator(String callString) {
            this.callString = callString;
        }

        public CallableStatement createCallableStatement(Connection con) throws SQLException {
            return con.prepareCall(this.callString);
        }

        public String getSql() {
            return callString;
        }

    }
    
    /**
     * Simple adapter for PreparedStatementSetter that applies
     * given arrays of arguments and JDBC argument types.
     */
    private static class ArgTypePreparedStatementSetter implements PreparedStatementSetter {

        private final Object[] args;

        private final int[] argTypes;

        public ArgTypePreparedStatementSetter(Object[] args, int[] argTypes) throws DataAccessException {
            if ((args != null && argTypes == null) || (args == null && argTypes != null) ||
                    (args != null && args.length != argTypes.length)) {
                throw new InvalidDataAccessApiUsageException("args and argTypes parameters must match"); //$NON-NLS-1$
            }
            this.args = args;
            this.argTypes = argTypes;
        }

        public void setValues(PreparedStatement ps) throws SQLException {
            if (this.args != null) {
                for (int i = 0; i < this.args.length; i++) {
                    ps.setObject(i + 1, this.args[i], this.argTypes[i]);
                }
            }
        }
    }
    
}
