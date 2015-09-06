/**
 * Statement.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.jet.birt.report.data.oda.ejbql;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This class implements IQuery interface of ODA. It does not support queries
 * returning multiple resultsets
 *
 * @author Oleg V. Safonov
 * @version $Id: Statement.java,v 1.5 2007/11/07 13:38:59 safonov Exp $
 */
public class Statement implements IQuery {

  // The Connection which hosts the Statement.
  private IConnection connection = null;

  // The meta data of the result set to be produced.
  // It is only available after a statement being prepared
  private IResultSetMetaData resultSetMetaData = null;

  private String query;

  private List<String> paramNames = new ArrayList<String>();

  private Object[] paramValues = null;

  private int maxRows = 0;
  private Method convertParamValueMethod = null;

  /**
   * Constructor
   *
   */
  Statement(IConnection conn) throws OdaException {
    this.connection = conn;

  }

  private static String[] extractColumns(final String query) {
    int fromPosition = query.toLowerCase().indexOf("from");
    int selectPosition = query.toLowerCase().indexOf("select");
    if (selectPosition >= 0) {
      String columns = query.substring(selectPosition + 6, fromPosition);
      StringTokenizer st = new StringTokenizer(columns, ",");
      List<String> columnList = new ArrayList<String>();
      while (st.hasMoreTokens()) {
        columnList.add(st.nextToken().trim());
      }
      return (String[]) columnList.toArray(new String[0]);
    } else {
      return null;
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#prepare(java.lang.String)
   */
  public void prepare(String query) throws OdaException {
    Query qry = null;
    // Test the connection
    testConnection();
    // holds the column types
    List<String> arColsType = new ArrayList<String>();
    List<String> arCols = new ArrayList<String>();
    List<String> arColClass = new ArrayList<String>();

    // holds the column names, also used for labels
    String[] props = null;
    try {

      Session hibsession = HibernateUtil.currentSession();
      // Create a Hibernate Query
      query = query.replaceAll("[\\n\\r]+", " ");
      query = query.trim();
      qry = hibsession.createQuery(query);

      // Get the list of return types from the query
      Type[] qryReturnTypes = qry.getReturnTypes();
      paramNames = Arrays.asList(qry.getNamedParameters());
      paramValues = new Object[paramNames.size()];

      // When specifing the HQL "from object" the returned type is a
      // Hibernate EntityType
      // When the columns are specified the returned values are normal
      // data types
      // The first half of this if statment handles the EntityType, the
      // else handles the
      // other case.
      // We are not handling multipe result sets.
      if (qryReturnTypes.length > 0 && qryReturnTypes[0].isEntityType()) {
        for (int j = 0; j < qryReturnTypes.length; j++) {
          // Get the classname and use utility function to retrieve
          // data types
          String clsName = qryReturnTypes[j].getName();
          // props holds the column names
          props = HibernateUtil.getHibernateProp(clsName);
          for (int x = 0; x < props.length; x++) {
            String propType = HibernateUtil.getHibernatePropTypes(clsName, props[x]);
            // Verify that the data type is valid
            if (DataTypes.isValidType(propType)) {
              arColsType.add(propType);
              // Only do this on Entity Types so we dont have a
              // name collision
              arCols.add(props[x]);
              arColClass.add(clsName);
            } else {
              throw new OdaException(Messages.getString("Statement.SOURCE_DATA_ERROR") + " " + propType);
            }
          }
        }
      } else {
        // Extract the column names from the query
        props = extractColumns(qry.getQueryString());
        // Get the return types from the Type[]
        for (int t = 0; t < qryReturnTypes.length; t++) {
          // Verify that the data type is valid
          String propType = qryReturnTypes[t].getName();
          if (DataTypes.isValidType(propType)) {
            arColsType.add(qryReturnTypes[t].getName());
            arCols.add(props[t]);
          } else {
            throw new OdaException(Messages.getString("Statement.SOURCE_DATA_ERROR") + " " + propType);
          }
        }

      }
    } catch (OdaException e) {
      throw e;
    } catch (Exception e) {
      throw new OdaException(e);
    }
    // this example does not enforce unique column names
    // Create a new ResultSetMetaData object passing in the column names and
    // data types

    // Have to remove . which BIRT does not allow
    String[] arLabels = (String[]) arCols.toArray(new String[arCols.size()]);
    for (int j = 0; j < arLabels.length; j++) {
      arLabels[j] = arLabels[j].replace('.', ':');
    }

    this.resultSetMetaData = new ResultSetMetaData(arLabels,
        (String[]) arColsType.toArray(new String[arColsType.size()]),
        arLabels, (String[]) arColClass.toArray(new String[arColClass.size()]));
    // query is saved for execution
    this.query = query;

  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setProperty(java.lang.String,
   *      java.lang.String)
   */
  public void setProperty(String name, String value) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#close()
   */
  public void close() throws OdaException {
    connection = null;
    resultSetMetaData = null;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#getMaxRows()
   */
  public int getMaxRows() throws OdaException {
    return maxRows;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setMaxRows(int)
   */
  public void setMaxRows(int max) throws OdaException {
    maxRows = max;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#getMetaData()
   */
  public IResultSetMetaData getMetaData() throws OdaException {
    return this.resultSetMetaData;
  }

  private Object convertParamValue(Session hibsession, String name, Object value) throws Exception {
    //используем reflection для исключения статичной ссылки на класс и зависимости данного плагина от других
    //т.к. этот плагин используется и в design и в runtime режиме то зависимость от плагина shared
    //не позволит работать в runtime
    if (convertParamValueMethod == null) {
      Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("com.mg.framework.support.report.ReportUtils");
      convertParamValueMethod = clazz.getMethod("convertParamValue", new Class[]{String.class, Object.class, Object.class});
    }
    return convertParamValueMethod.invoke(null, new Object[]{name, value, hibsession});
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#executeQuery()
   */
  @SuppressWarnings("unchecked")
  public IResultSet executeQuery() throws OdaException {
    Type[] qryReturnTypes = null;
    List<Object> rst = null;

    try {
      Session hibsession = HibernateUtil.currentSession();

      String qryStr = this.query;
      qryStr = qryStr.replaceAll("[\\n\\r]+", " ");
      qryStr.trim();
      // Create the Hibernate query, notice that we are using the query
      // prepared
      Query qry = hibsession.createQuery(qryStr);
      for (int i = 0; i < paramNames.size(); i++) {
        Object paramValue = convertParamValue(hibsession, paramNames.get(i), paramValues[i]);
        if (paramValue instanceof Object[])
          qry.setParameterList(paramNames.get(i), (Object[]) paramValue);
        else
          qry.setParameter(paramNames.get(i), paramValue);
      }
      if (maxRows != 0)
        qry.setMaxResults(maxRows);
      // use the query list method to return the resuls in a List object
      rst = qry.list();
      qryReturnTypes = qry.getReturnTypes();

    } catch (Exception e) {
      throw new OdaException(e);
    }

    // create a new ResultSet Ojbect passing in the row set and the meta
    // data and the
    // Hibernate query return types
    return new ResultSet(rst, getMetaData(), qryReturnTypes);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setInt(java.lang.String, int)
   */
  public void setInt(String parameterName, int value) throws OdaException {
    setParameterValue(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setInt(int, int)
   */
  public void setInt(int parameterId, int value) throws OdaException {
    setParameterValue(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setDouble(java.lang.String, double)
   */
  public void setDouble(String parameterName, double value)
      throws OdaException {
    setParameterValue(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setDouble(int, double)
   */
  public void setDouble(int parameterId, double value) throws OdaException {
    setParameterValue(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setBigDecimal(java.lang.String,
   *      java.math.BigDecimal)
   */
  public void setBigDecimal(String parameterName, BigDecimal value) throws OdaException {
    setParameterValue(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setBigDecimal(int,
   *      java.math.BigDecimal)
   */
  public void setBigDecimal(int parameterId, BigDecimal value)
      throws OdaException {
    setParameterValue(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setString(java.lang.String,
   *      java.lang.String)
   */
  public void setString(String parameterName, String value)
      throws OdaException {
    setParameterValue(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setString(int, java.lang.String)
   */
  public void setString(int parameterId, String value) throws OdaException {
    setParameterValue(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setDate(java.lang.String,
   *      java.sql.Date)
   */
  public void setDate(String parameterName, Date value) throws OdaException {
    setParameterValue(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setDate(int, java.sql.Date)
   */
  public void setDate(int parameterId, Date value) throws OdaException {
    setParameterValue(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setTime(java.lang.String,
   *      java.sql.Time)
   */
  public void setTime(String parameterName, Time value) throws OdaException {
    setParameterValue(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setTime(int, java.sql.Time)
   */
  public void setTime(int parameterId, Time value) throws OdaException {
    setParameterValue(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setTimestamp(java.lang.String,
   *      java.sql.Timestamp)
   */
  public void setTimestamp(String parameterName, Timestamp value) throws OdaException {
    setParameterValue(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(int,
   *      boolean)
   */
  public void setBoolean(int parameterId, boolean value) throws OdaException {
    setParameterValue(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setBoolean(java.lang.String,
   *      boolean)
   */
  public void setBoolean(String parameterName, boolean value) throws OdaException {
    setParameterValue(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(int)
   */
  public void setNull(int parameterId) throws OdaException {
    setParameterValue(parameterId, null);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setNull(java.lang.String)
   */
  public void setNull(String parameterName) throws OdaException {
    setParameterValue(parameterName, null);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setTimestamp(int,
   *      java.sql.Timestamp)
   */
  public void setTimestamp(int parameterId, Timestamp value)
      throws OdaException {
    setParameterValue(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#findInParameter(java.lang.String)
   */
  public int findInParameter(String parameterName) throws OdaException {
    int result = paramNames.indexOf(parameterName);
    if (result != -1)
      return result + 1;
    else
      throw new OdaException("Parameter not found: " + parameterName);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#getParameterMetaData()
   */
  public IParameterMetaData getParameterMetaData() throws OdaException {
    // try
    // {
    // return new ParameterMetaData();
    // }
    // catch ( OdaException e )
    // {
    // throw new OdaException( e.getLocalizedMessage() );
    // }
    // throw new UnsupportedOperationException ();
    return (null);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#clearInParameters()
   */
  public void clearInParameters() throws OdaException {
    // throw new UnsupportedOperationException ();
    if (paramNames != null)
      paramNames.clear();
    paramNames = null;
    paramValues = null;
  }

  private void testConnection() throws OdaException {
    if (connection.isOpen() == false)
      throw new OdaException(Messages.getString("Common.CONNECTION_HAS_NOT_OPEN")); //$NON-NLS-1$
  }

  private void setParameterValue(String parameterName, Object value) throws OdaException {
    int idx = paramNames.indexOf(parameterName);
    if (idx == -1)
      throw new OdaException("Parameter is not found: " + parameterName);
    if (paramValues == null)
      throw new OdaException("Parameters is not initialized");
    if (idx >= 0 && idx < paramNames.size()) {
      paramValues[idx] = value;
    }
  }

  private void setParameterValue(int parameterId, Object value) throws OdaException {
    if (paramValues == null)
      throw new OdaException("Parameters is not initialized");

    if (parameterId > 0 && parameterId <= paramValues.length)
      paramValues[parameterId - 1] = value;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#getSortSpec()
   */
  public SortSpec getSortSpec() throws OdaException {
    // Sorting will be handled by BIRT
    return (null);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.birt.data.oda.IQuery#setSortSpec(org.eclipse.birt.data.oda.SortSpec)
   */
  public void setSortSpec(SortSpec sortBy) throws OdaException {
    throw new UnsupportedOperationException();

  }

  public void setAppContext(Object obj) throws OdaException {
    // do nothing; no support for pass-through application context
  }

}
