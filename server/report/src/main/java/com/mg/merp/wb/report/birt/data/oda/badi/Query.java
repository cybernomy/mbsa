/*
 * Query.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.wb.report.birt.data.oda.badi;

import com.mg.framework.api.dataset.DataSet;
import com.mg.merp.report.RptBAiStarter;
import com.mg.merp.wb.report.birt.data.oda.badi.util.Constants;
import com.mg.merp.wb.report.birt.data.oda.badi.util.RelationInformation;

import org.eclipse.datatools.connectivity.oda.IParameterMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.datatools.connectivity.oda.SortSpec;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.APP_REPORT_CONTEXT;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.BAI_CODE;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.BAI_ENGINE_PARAM;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.BUSINESS_SERVICE;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.CURRENT_SESSION_PARAM;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.DATASET_PARAMS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.ENTITY_IDS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.REPORT_CONTEXT_PARAMS;
import static com.mg.merp.wb.report.birt.data.oda.badi.util.Constants.REPORT_PARAMS;

/**
 * Реализация интерфейса {@link IQuery}
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: Query.java,v 1.14 2008/10/08 11:30:47 safonov Exp $
 */
public class Query implements IQuery {
  /**
   * logger
   */
  private static Logger logger = Logger.getLogger(Connection.class.getName());
  /**
   * Вспомогательная информация. Необходима для построения таблицы результирующего набора данных
   */
  private RelationInformation relationInformation;

  /**
   * Код алгоритма, который обеспечивает получение результирующего набора данных запроса
   */
  private String baiCode;

  // indicate whether the result set has been closed.
  private boolean isClosed;

  /**
   * Результат выполнения запроса
   */
  //private DataSet resultSet;

  private Map<String, Object> appContext;

  /**
   * The max rows of result set created by this query that might returned
   */
  private int maxRows;

  /**
   * Параметры, передаваемые в бизнес-расширение(алгоритм), для получения конкретного набора данных
   */
  private Map<String, Object> dataSetParams = new HashMap<String, Object>();

  /**
   * Массив имён параметров результирующего набора. Необходим в текущей реализации, т.к. сама
   * платформа BIRT не позволяет устанавливать параметры по именам (Ошибка там)
   */
  private List<String> dataSetParamsNames;

  /**
   * Конструктор экземпляра запроса
   *
   * @param appContext контекст запроса
   */
  public Query(Map<String, Object> appContext) {
    isClosed = false;
    this.appContext = appContext;
    maxRows = 0;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#prepare(java.lang.String)
   */
  public void prepare(String queryText) throws OdaException {
    logger.logp(java.util.logging.Level.FINE, Query.class.getName(), "prepare", "Query.prepare( \"" + queryText + "\" )");
    testClosed();
    if (queryText == null) {
      logger.logp(java.util.logging.Level.FINE, Query.class.getName(), "prepare", "Query text can not be null.");
      throw new org.eclipse.datatools.connectivity.oda.OdaException("Query text can not be null."); //$NON-NLS-1$
    }
    this.relationInformation = new RelationInformation(queryText);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setProperty(java.lang.String,
   *      java.lang.String)
   */
  @SuppressWarnings("unchecked")
  public void setProperty(String name, String value) throws OdaException {
    if (BAI_CODE.equals(name)) {
      this.baiCode = value;
      // проверка, т.к. в на данный момент в designTime контекст
      // недоступен
      if (appContext != null) {
        Map<String, Object> rptContext = (Map<String, Object>) appContext.get(APP_REPORT_CONTEXT);
        if (rptContext != null) {
          Map<String, List<String>> dataSetParamMap = (Map<String, List<String>>) rptContext.get(DATASET_PARAMS);
          if (dataSetParamMap != null)
            this.dataSetParamsNames = dataSetParamMap.get(this.baiCode);
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#close()
   */
  public void close() throws OdaException {
    logger.logp(java.util.logging.Level.FINE, Query.class.getName(), "close", "Query.close( )");
    this.isClosed = true;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#getMaxRows()
   */
  public int getMaxRows() throws OdaException {
    testClosed();
    return this.maxRows;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setMaxRows(int)
   */
  public void setMaxRows(int max) throws OdaException {
    testClosed();
    this.maxRows = max > 0 ? max : 0;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#getMetaData()
   */
  public IResultSetMetaData getMetaData() throws OdaException {
    logger.logp(java.util.logging.Level.FINE, Query.class.getName(), "getMetaData", "Query.getMetaData( )");
    testClosed();
    return new ResultSetMetaData(relationInformation);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#executeQuery()
   */
  @SuppressWarnings("unchecked")
  public IResultSet executeQuery() throws OdaException {
    logger.logp(java.util.logging.Level.FINE, Query.class.getName(), "executeQuery", "Query.executeQuery( )");
    testClosed();
    if (this.baiCode == null || "".equals(this.baiCode.trim()))
      throw new OdaException("Query.QueryHasNotBeenPrepared"); //$NON-NLS-1$

    if (appContext != null) {
      Map<String, Object> rptContext = (Map<String, Object>) appContext.get(APP_REPORT_CONTEXT);
      if (rptContext == null)
        throw new OdaException("Query.QueryHasNotBeenPrepared"); //$NON-NLS-1$
      RptBAiStarter rbs = (RptBAiStarter) rptContext.get(BAI_ENGINE_PARAM);
      if (rbs == null) {
        //если не установлен, то находимся в студии разработки,
        //пытаемся удаленно подключиться к серверу и выполнить BAi для предварительного просмотра
        //rbs = new RemoteRptBAiStarterImpl();
        //установим реализацию для последующей обработки
        //appContext.put(BAI_ENGINE_PARAM, rbs);
        throw new OdaException("Query.QueryHasNotBeenPrepared"); //$NON-NLS-1$
      }

      Map<String, Object> params = new HashMap<String, Object>();
      params.put(ENTITY_IDS, rptContext.get(ENTITY_IDS));
      params.put(BUSINESS_SERVICE, rptContext.get(BUSINESS_SERVICE));
      //params.put(DATASET_METADATA, this.relationInformation);
      String[] columnNames = this.relationInformation.getTableColumnNames();
      params.put(Constants.DATASET_COLUMN_NAMES, columnNames);
      String[] columnTypes = new String[columnNames.length];
      for (int i = 0; i < columnNames.length; i++)
        columnTypes[i] = this.relationInformation.getTableColumnType(columnNames[i]);
      params.put(Constants.DATASET_COLUMN_TYPES, columnTypes);
      params.put(REPORT_PARAMS, rptContext.get(REPORT_PARAMS));
      params.put(DATASET_PARAMS, dataSetParams);
      params.put(CURRENT_SESSION_PARAM, rptContext.get(CURRENT_SESSION_PARAM));
      Object reportContext = rptContext.get(REPORT_CONTEXT_PARAMS);
      //если контекст выполнения не был создан, то создадим и внесем в appContext
      if (reportContext == null) {
        reportContext = new HashMap<String, Object>();
        rptContext.put(REPORT_CONTEXT_PARAMS, reportContext);
      }
      params.put(REPORT_CONTEXT_PARAMS, reportContext);

      try {
        rbs.perform(this.baiCode, params);

        DataSet resultSet = rbs.getDataSet();
        ResultSet result = new ResultSet(relationInformation, resultSet, maxRows);
        return result;
      } catch (Throwable e) {
        //создадим обертку для ИС сгенерированных в BAi
        OdaException odaE = new OdaException(e.getLocalizedMessage());
        odaE.initCause(e);
        throw odaE;
      }
    } else
      throw new OdaException("Query.QueryHasNotBeenPrepared"); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#clearInParameters()
   */
  public void clearInParameters() throws OdaException {
    dataSetParams.clear();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(java.lang.String,
   *      int)
   */
  public void setInt(String parameterName, int value) throws OdaException {
    dataSetParams.put(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setInt(int, int)
   */
  public void setInt(int parameterId, int value) throws OdaException {
    setDataSetParam(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(java.lang.String,
   *      double)
   */
  public void setDouble(String parameterName, double value)
      throws OdaException {
    dataSetParams.put(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setDouble(int, double)
   */
  public void setDouble(int parameterId, double value) throws OdaException {
    setDataSetParam(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(java.lang.String,
   *      java.math.BigDecimal)
   */
  public void setBigDecimal(String parameterName, BigDecimal value)
      throws OdaException {
    dataSetParams.put(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setBigDecimal(int,
   *      java.math.BigDecimal)
   */
  public void setBigDecimal(int parameterId, BigDecimal value)
      throws OdaException {
    setDataSetParam(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(java.lang.String,
   *      java.lang.String)
   */
  public void setString(String parameterName, String value)
      throws OdaException {
    dataSetParams.put(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setString(int,
   *      java.lang.String)
   */
  public void setString(int parameterId, String value) throws OdaException {
    setDataSetParam(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(java.lang.String,
   *      java.sql.Date)
   */
  public void setDate(String parameterName, Date value) throws OdaException {
    dataSetParams.put(parameterName, new java.util.Date(value.getTime()));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setDate(int,
   *      java.sql.Date)
   */
  public void setDate(int parameterId, Date value) throws OdaException {
    setDataSetParam(parameterId, new java.util.Date(value.getTime()));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(java.lang.String,
   *      java.sql.Time)
   */
  public void setTime(String parameterName, Time value) throws OdaException {
    dataSetParams.put(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setTime(int,
   *      java.sql.Time)
   */
  public void setTime(int parameterId, Time value) throws OdaException {
    setDataSetParam(parameterId, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(java.lang.String,
   *      java.sql.Timestamp)
   */
  public void setTimestamp(String parameterName, Timestamp value)
      throws OdaException {
    dataSetParams.put(parameterName, value);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setTimestamp(int,
   *      java.sql.Timestamp)
   */
  public void setTimestamp(int parameterId, Timestamp value)
      throws OdaException {
    setDataSetParam(parameterId, value);
  }

  public void setBoolean(int parameterId, boolean value) throws OdaException {
    setDataSetParam(parameterId, value);
  }

  public void setBoolean(String parameterName, boolean value) throws OdaException {
    dataSetParams.put(parameterName, value);
  }

  public void setNull(int parameterId) throws OdaException {
    setDataSetParam(parameterId, null);
  }

  public void setNull(String parameterName) throws OdaException {
    dataSetParams.put(parameterName, null);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#findInParameter(java.lang.String)
   */
  public int findInParameter(String parameterName) throws OdaException {
    // нумерация параметров идёт с 1
    if (appContext != null && dataSetParamsNames != null)
      return dataSetParamsNames.indexOf(parameterName) + 1;
    else
      return 0;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#getParameterMetaData()
   */
  public IParameterMetaData getParameterMetaData() throws OdaException {
    // TODO: заглушка
    return new IParameterMetaData() {

      public int getParameterCount() throws OdaException {
        if (appContext != null && dataSetParamsNames != null)
          return dataSetParamsNames.size();
        else
          return 0;
      }

      public int getParameterMode(int param) throws OdaException {
        // TODO Auto-generated method stub
        return 0;
      }

      public int getParameterType(int param) throws OdaException {
        // TODO Auto-generated method stub
        return 0;
      }

      public String getParameterTypeName(int param) throws OdaException {
        // TODO Auto-generated method stub
        return null;
      }

      public int getPrecision(int param) throws OdaException {
        // TODO Auto-generated method stub
        return 0;
      }

      public int getScale(int param) throws OdaException {
        // TODO Auto-generated method stub
        return 0;
      }

      public int isNullable(int param) throws OdaException {
        // TODO Auto-generated method stub
        return 0;
      }

      public String getParameterName(int param) throws OdaException {
        // TODO Auto-generated method stub
        return null;
      }

    };
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#getSortSpec()
   */
  public SortSpec getSortSpec() throws OdaException {
    throw new UnsupportedOperationException();
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setSortSpec(org.eclipse.datatools.connectivity.oda.SortSpec)
   */
  public void setSortSpec(SortSpec sortBy) throws OdaException {
    throw new UnsupportedOperationException();
  }

  /**
   * Get the RelationInformation of this Query.
   */
  RelationInformation getRelationInformation() {
    return this.relationInformation;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.datatools.connectivity.oda.IQuery#setAppContext(java.lang.Object
   *      context)
   */
  @SuppressWarnings("unchecked")
  public void setAppContext(Object context) throws OdaException {
    this.appContext = (Map) context;
  }

  private void setDataSetParam(int parameterId, Object value) {
    // нумерация параметров идёт с 1
    if (appContext != null && dataSetParamsNames != null)
      dataSetParams.put(dataSetParamsNames.get(parameterId - 1), value);

  }

  /**
   * Если результирующий набор закрыт, то выбрасывается исключение.
   */
  private void testClosed() throws OdaException {
    if (isClosed)
      throw new OdaException("Query.ResultSetClosed"); //$NON-NLS-1$
  }

}
