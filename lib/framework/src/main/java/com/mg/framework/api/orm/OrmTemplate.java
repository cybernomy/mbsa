/*
 * OrmTemplate.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.orm;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.Logger;
import com.mg.framework.service.PersistentManagerHibernateImpl;
import com.mg.framework.service.SQLExceptionTranslatorManagerLocator;
import com.mg.framework.support.orm.CriteriaHibernateImpl;
import com.mg.framework.support.orm.OrmTemplateHibernateImpl;
import com.mg.framework.utils.ServerUtils;

/**
 * Шаблон выполнения объектных запросов
 * 
 * @author Oleg V. Safonov
 * @version $Id: OrmTemplate.java,v 1.9 2007/07/27 09:32:16 safonov Exp $
 */
public abstract class OrmTemplate {
	private Logger logger = ServerUtils.getLogger(getClass());
	private Session session = null;
	private boolean cacheQueries;
	private String queryCacheRegion;
	private int fetchSize;
	private int maxResults;
	private FlushMode flushMode;

	/**
	 * получить экземпляр объекта
	 * 
	 * @return
	 */
	public static OrmTemplate getInstance() {
		return new OrmTemplateHibernateImpl();
	}

	/**
	 * создает экземпляр критериев для класса сущности или наследника класса сущности
	 * 
	 * @param persistentClass	класс сущности
	 * @return	критерии
	 */
	public static Criteria createCriteria(Class<?> persistentClass) {
		return createCriteria(persistentClass.getName());
	}

	/**
	 * создает экземпляр критериев для сущности
	 * 
	 * @param entityName	имя класса сущности
	 * @return	критерии
	 */
	public static Criteria createCriteria(String entityName) {
		return ServerUtils.getPersistentManager().createCriteria(entityName);
	}

	/**
	 * создает экземпляр критериев для класса сущности или наследника класса сущности с указанным псевдонимом
	 * 
	 * @param persistentClass	класс сущности
	 * @param alias		псевдоним
	 * @return	критерии
	 */
	public static Criteria createCriteria(Class<?> persistentClass, String alias) {
		return createCriteria(persistentClass.getName(), alias);
	}
	
	/**
	 * создает экземпляр критериев для сущности с указанным псевдонимом
	 * 
	 * @param entityName	имя класса сущности
	 * @param alias		псевдоним
	 * @return	критерии
	 */
	public static Criteria createCriteria(String entityName, String alias) {
		try {
			return new CriteriaHibernateImpl(PersistentManagerHibernateImpl.getFactory().getCurrentSession().createCriteria(entityName, alias));
		}
		catch (HibernateException e) {
			throw new DataAccessException(e);
		}
	}

	/**
	 * Set Hibernate session. Use for standalone test only. Do not use in application server environment.
	 * 
	 * @param session
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	private static DataAccessException convertHibernateAccessException(HibernateException ex) {
		if (ex instanceof JDBCException)
			return convertJdbcAccessException(((JDBCException) ex).getSQLException());
		else
			return new DataAccessException(ex);
	}

	/**
	 * Convert the given SQLException to an appropriate exception from the
	 * <code>com.mg.framework.api</code> hierarchy. Can be overridden in subclasses.
	 * <p>Note that a direct SQLException can just occur here when callback code
	 * performs direct JDBC access via <code>Session.connection()</code>.
	 * @param ex SQLException that occured
	 * @return the corresponding DataAccessException instance
	 * @see #setJdbcExceptionTranslator
	 * @see org.hibernate.Session#connection()
	 */
	private static DataAccessException convertJdbcAccessException(SQLException ex) {
		return SQLExceptionTranslatorManagerLocator.locate().translate(ex);
	}

	/**
	 * Apply the given name parameter to the given Query object.
	 * @param queryObject the Query object
	 * @param paramName the name of the parameter
	 * @param value the value of the parameter
	 * @throws HibernateException if thrown by the Query object
	 */
	protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
	throws HibernateException {
		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection) value);
		}
		else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		}
		else {
			queryObject.setParameter(paramName, value);
		}
	}

	protected Session getSession() {
		if (session != null)
			return session;
		else
			return PersistentManagerHibernateImpl.getFactory().getCurrentSession();
	}

	/**
	 * Execute the action specified by the given action object within a Session.
	 * @param action callback object that specifies the Hibernate action
	 * to callback code
	 * @return a result object returned by the action, or null
	 * @throws org.springframework.dao.DataAccessException in case of Hibernate errors
	 */
	private <T> T execute(HibernateCallback<T> action) throws DataAccessException {
		Session session = getSession();
		try {
			Session sessionToExpose = session;
			T result = action.doInHibernate(sessionToExpose);
			return result;
		}
		catch (HibernateException ex) {
			throw convertHibernateAccessException(ex);
		}
		catch (SQLException ex) {
			throw convertJdbcAccessException(ex);
		}
		catch (RuntimeException ex) {
			// callback code threw application exception
			throw ex;
		}
	}

	/**
	 * Prepare the given Query object, applying cache settings and/or
	 * a transaction timeout.
	 * @param queryObject the Query object to prepare
	 * @see #setCacheQueries
	 * @see #setQueryCacheRegion
	 */
	protected abstract void prepareQuery(Query queryObject);

	/**
	 * Prepare the given Criteria object, applying cache settings and/or
	 * a transaction timeout.
	 * @param criteria the Criteria object to prepare
	 * @see #setCacheQueries
	 * @see #setQueryCacheRegion
	 */
	protected abstract void prepareCriteria(Criteria criteria);

	/**
	 * Set whether to cache all queries executed by this template.
	 * If this is true, all Query and Criteria objects created by
	 * this template will be marked as cacheable (including all
	 * queries through find methods).
	 * <p>To specify the query region to be used for queries cached
	 * by this template, set the "queryCacheRegion" property.
	 * @see #setQueryCacheRegion
	 * @see org.hibernate.Query#setCacheable
	 * @see org.hibernate.Criteria#setCacheable
	 */
	public void setCacheQueries(boolean cacheQueries) {
		this.cacheQueries = cacheQueries;
	}

	/**
	 * Return whether to cache all queries executed by this template.
	 */
	public boolean isCacheQueries() {
		return cacheQueries;
	}

	/**
	 * Set the name of the cache region for queries executed by this template.
	 * If this is specified, it will be applied to all Query and Criteria objects
	 * created by this template (including all queries through find methods).
	 * <p>The cache region will not take effect unless queries created by this
	 * template are configured to be cached via the "cacheQueries" property.
	 * @see #setCacheQueries
	 * @see org.hibernate.Query#setCacheRegion
	 * @see org.hibernate.Criteria#setCacheRegion
	 */
	public void setQueryCacheRegion(String queryCacheRegion) {
		this.queryCacheRegion = queryCacheRegion;
	}

	/**
	 * Return the name of the cache region for queries executed by this template.
	 */
	public String getQueryCacheRegion() {
		return queryCacheRegion;
	}

	/**
	 * Set the fetch size for this OrmTemplate. This is important for processing
	 * large result sets: Setting this higher than the default value will increase
	 * processing speed at the cost of memory consumption; setting this lower can
	 * avoid transferring row data that will never be read by the application.
	 * <p>Default is 0, indicating to use the JDBC driver's default.
	 */
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	/**
	 * Return the fetch size specified for this OrmTemplate.
	 */
	public int getFetchSize() {
		return fetchSize;
	}

	/**
	 * Set the maximum number of rows for this OrmTemplate. This is important
	 * for processing subsets of large result sets, avoiding to read and hold
	 * the entire result set in the database or in the JDBC driver if we're
	 * never interested in the entire result in the first place (for example,
	 * when performing searches that might return a large number of matches).
	 * <p>Default is 0, indicating to use the JDBC driver's default.
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * Return the maximum number of rows specified for this OrmTemplate.
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * Set the flush mode for this OrmTemplate
	 * 
	 * @param flushMode flushMode for this OrmTemplate
	 */
	public void setFlushMode(FlushMode flushMode) {
		this.flushMode = flushMode;
	}
	
	/**
	 * Return the FlushMode specified for this OrmTemplate.
	 * 
	 * @return flushMode or <code>null<code> if not set
	 */
	public FlushMode getFlushMode() {
		return flushMode;
	}
	
	/**
	 * поиск по EJBQL запросу
	 * 
	 * @param <T>	тип результата
	 * @param persistentClass	класс результата
	 * @param queryString	текст запроса
	 * @return	результат запроса
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public <T> List<T> find(final Class<T> persistentClass, String queryString) {
		return find(queryString, (Object[]) null);
	}

	/**
	 * поиск по EJBQL запросу
	 * 
	 * @param queryString	текст запроса
	 * @return	результат запроса
	 */
	public List find(String queryString) {
		return find(queryString, (Object[]) null);
	}

	/**
	 * поиск по EJBQL запросу и параметру
	 * 
	 * @param queryString	текст запроса
	 * @param value	значение параметра
	 * @return	результат запроса
	 */
	public List find(String queryString, Object value) {
		return find(queryString, new Object[] {value});
	}

	/**
	 * поиск по EJBQL запросу и параметрам
	 * 
	 * @param queryString	текст запроса
	 * @param values	значения параметров
	 * @return	результат запроса
	 */
	public List find(final String queryString, final Object[] values) {
		return (List) execute(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	/**
	 * поиск по EJBQL запросу и именованому параметру
	 * 
	 * @param queryString	текст запроса
	 * @param paramName	имя параметра
	 * @param value	значение параметра
	 * @return	результат запроса
	 */
	public List findByNamedParam(String queryString, String paramName, Object value) {
		return findByNamedParam(queryString, new String[] { paramName },
				new Object[] { value });
	}

	/**
	 * поиск по EJBQL запросу и именованым параметрам
	 * 
	 * @param queryString	текст запроса
	 * @param paramNames	имена параметров
	 * @param values	значения парамтеров
	 * @return	результат запроса
	 */
	public List findByNamedParam(final String queryString,
			final String[] paramNames, final Object[] values) {
		return findByNamedParam(queryString, paramNames, values, null);
	}

	private void setResultTransformer(final Query queryObject, final ResultTransformer resultTransformer) {
		if (resultTransformer != null) {
			queryObject.setResultTransformer(new org.hibernate.transform.ResultTransformer() {

				public List transformList(List collection) {
					return collection;
				}

				public Object transformTuple(Object[] tuple, String[] aliases) {
					return resultTransformer.transformTuple(tuple, aliases);
				}
				
			});
		}
	}
	
	/**
	 * поиск по EJBQL запросу и именованым параметрам
	 * 
	 * @param queryString	текст запроса
	 * @param paramNames	имена параметров
	 * @param values	значения парамтеров
	 * @param resultTransformer стратегия для обработки результата запроса
	 * @return	результат запроса
	 */
	public <T> List<T> findByNamedParam(final String queryString,
			final String[] paramNames, final Object[] values, final ResultTransformer<T> resultTransformer) {
		if (paramNames.length != values.length) {
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of values array"); //$NON-NLS-1$
		}
		return (List<T>) execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				setResultTransformer(queryObject, resultTransformer);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	/**
	 * поиск по EJBQL запросу и именованым параметрам
	 * 
	 * @param queryString	текст запроса
	 * @param params		пары имя параметра - значение
	 * @return	результат запроса
	 */
	public List findByNamedParam(final String queryString, final Map<String, Object> params) {
		return (List) execute(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				queryObject.setProperties(params);
				return queryObject.list();
			}
		});
	}
	
	/**
	 * поиск по именованому EJBQL запросу
	 * 
	 * @param queryName	имя запроса
	 * @return	результат запроса
	 */
	public List findByNamedQuery(String queryName) {
		return findByNamedQuery(queryName, (Object[]) null);
	}

	/**
	 * поиск по именованому EJBQL запросу и параметру
	 * 
	 * @param queryName	имя запроса
	 * @param value	значение параметра
	 * @return	результат запроса
	 */
	public List findByNamedQuery(String queryName, Object value) {
		return findByNamedQuery(queryName, new Object[] {value});
	}

	/**
	 * поиск по именованому EJBQL запросу и параметрам
	 * 
	 * @param queryName	имя запроса
	 * @param values	значения параметров
	 * @return	результат запроса
	 */
	public List findByNamedQuery(final String queryName, final Object[] values) {
		return (List) execute(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.getNamedQuery(queryName);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	/**
	 * поиск по именованому EJBQL запросу и именованому параметру
	 * 
	 * @param queryName	имя запроса
	 * @param paramName	имя параметра
	 * @param value	значение параметра
	 * @return	результат запроса
	 */
	public List findByNamedQueryAndNamedParam(String queryName, String paramName, Object value) {
		return findByNamedQueryAndNamedParam(queryName, new String[] {paramName}, new Object[] {value});
	}

	public List findByNamedQueryAndNamedParam(
			final String queryName, final String[] paramNames, final Object[] values) {
		return findByNamedQueryAndNamedParam(queryName, paramNames, values, null);
	}

	/**
	 * поиск по именованому EJBQL запросу и именованым параметрам
	 * 
	 * @param queryName		имя запроса
	 * @param paramNames	имена параметров
	 * @param values		значения параметров
	 * @return	результат запроса
	 */
	public <T> List<T> findByNamedQueryAndNamedParam(
			final String queryName, final String[] paramNames, final Object[] values, final ResultTransformer<T> resultTransformer) {

		if (paramNames != null && values != null && paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		return (List<T>) execute(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.getNamedQuery(queryName);
				setResultTransformer(queryObject, resultTransformer);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	/**
	 * поиск по именованому EJBQL запросу и именованым параметрам
	 * 
	 * @param queryName		имя запроса
	 * @param params		пары имя параметра - значение
	 * @return	результат запроса
	 */
	public List findByNamedQueryAndNamedParam(final String queryName, final Map<String, Object> params) {
		return (List) execute(new HibernateCallback<List>() {
			public List doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.getNamedQuery(queryName);
				prepareQuery(queryObject);
				queryObject.setProperties(params);
				return queryObject.list();
			}
		});
	}
	
	/**
	 * поиск по критериям
	 * 
	 * @param <T>	тип объекта
	 * @param criteria	критерии
	 * @return	результат запроса
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public <T> List<T> findByCriteria(final Criteria criteria) {
		return execute(new HibernateCallback<List<T>>() {
			public List<T> doInHibernate(Session session) throws HibernateException {
				return ((CriteriaHibernateImpl) criteria).getDelegate().list();
			}
		});
	}

	/**
	 * поиск уникального результата по критериям
	 * 
	 * @param <T>	тип объекта
	 * @param criteria	критерии
	 * @return	результат или <code>null</code> если не найден
	 */
	@SuppressWarnings("unchecked") //$NON-NLS-1$
	public <T> T findUniqueByCriteria(final Criteria criteria) {
		return execute(new HibernateCallback<T>() {
			public T doInHibernate(Session session) throws HibernateException {
				return (T) ((CriteriaHibernateImpl) criteria).getDelegate().uniqueResult();
			}
		});
	}

	/**
	 * поиск по списку критериев
	 * 
	 * @param <T>				тип объекта
	 * @param persistentClass	класс объекта
	 * @param criteria			список критериев
	 * @return	результат запроса
	 */
	public <T> List<T> findByCriteria(final Class<T> persistentClass, final Criterion ... criteria) {
		if (criteria.length == 0)
			throw new IllegalArgumentException("List of criteria is empty"); //$NON-NLS-1$
		Criteria crit = OrmTemplate.createCriteria(persistentClass);
		for (Criterion c : criteria)
			crit.add(c);
		return findByCriteria(crit);
	}

	/**
	 * поиск уникального результата по списку критериев
	 * 
	 * @param <T>				тип объекта
	 * @param persistentClass	класс объекта
	 * @param criteria			список критериев
	 * @return	результат или <code>null</code> если не найден
	 */
	@SuppressWarnings("unchecked")
	public <T> T findUniqueByCriteria(final Class<T> persistentClass, final Criterion ... criteria) {
		if (criteria.length == 0)
			throw new IllegalArgumentException("List of criteria is empty"); //$NON-NLS-1$
		Criteria crit = OrmTemplate.createCriteria(persistentClass);
		for (Criterion c : criteria)
			crit.add(c);
		return (T) findUniqueByCriteria(crit);
	}

	/**
	 * Execute the update or delete statement.
	 * </p>
	 * The semantics are compliant with the ejb3 Query.executeUpdate()
	 * method.
	 * 
	 * @param queryString	query text
	 * @return	The number of entities updated or deleted.
	 */
	public int bulkUpdate(final String queryString) {
//		Integer deleteCount = execute(new HibernateCallback<Integer>() {
//			public Integer doInHibernate(Session session) throws HibernateException {
//				Query queryObject = session.createQuery(queryString);
//				prepareQuery(queryObject);
//				return queryObject.executeUpdate();
//			}
//		});
//		return deleteCount.intValue();
		return bulkUpdate(queryString, new String[] {}, new Object[] {});
	}

	/**
	 * Execute the update or delete statement.
	 * </p>
	 * The semantics are compliant with the ejb3 Query.executeUpdate()
	 * method.
	 * 
	 * @param queryString	query text
	 * @param paramName	the name of the parameter
	 * @param value	the value of the parameter
	 * @return	The number of entities updated or deleted.
	 */
	public int bulkUpdate(final String queryString, final String paramName, final Object value) {
		return bulkUpdate(queryString, new String[] {paramName}, new Object[] {value});
	}

	/**
	 * Execute the update or delete statement.
	 * </p>
	 * The semantics are compliant with the ejb3 Query.executeUpdate()
	 * method.
	 * 
	 * @param queryString	query text
	 * @param paramNames	the parameters' names
	 * @param values	the parameters' values
	 * @return	The number of entities updated or deleted.
	 */
	public int bulkUpdate(final String queryString, final String[] paramNames, final Object[] values) {
		if (paramNames.length != values.length) {
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of values array"); //$NON-NLS-1$
		}
		Integer deleteCount = execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
					}
				}
				return queryObject.executeUpdate();
			}
		});
		return deleteCount.intValue();
	}

	/**
	 * Execute the update or delete statement.
	 * </p>
	 * The semantics are compliant with the ejb3 Query.executeUpdate()
	 * method.
	 * 
	 * @param queryName	query name
	 * @return	The number of entities updated or deleted.
	 */
	public int bulkUpdateByNamedQuery(final String queryName) {
		return bulkUpdateByNamedQuery(queryName, new String[] {}, new Object[] {});
	}
	
	/**
	 * Execute the update or delete statement.
	 * </p>
	 * The semantics are compliant with the ejb3 Query.executeUpdate()
	 * method.
	 * 
	 * @param queryName	query name
	 * @param paramName	the name of the parameter
	 * @param value	the value of the parameter
	 * @return	The number of entities updated or deleted.
	 */
	public int bulkUpdateByNamedQuery(final String queryName, final String paramName, final Object value) {
		return bulkUpdateByNamedQuery(queryName, new String[] {paramName}, new Object[] {value});
	}

	/**
	 * Execute the update or delete statement.
	 * </p>
	 * The semantics are compliant with the ejb3 Query.executeUpdate()
	 * method.
	 * 
	 * @param queryName	query name
	 * @param paramNames	the parameters' names
	 * @param values	the parameters' values
	 * @return	The number of entities updated or deleted.
	 */
	public int bulkUpdateByNamedQuery(final String queryName, final String[] paramNames, final Object[] values) {
		if (paramNames.length != values.length) {
			throw new IllegalArgumentException(
					"Length of paramNames array must match length of values array"); //$NON-NLS-1$
		}
		Integer deleteCount = execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.getNamedQuery(queryName);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
					}
				}
				return queryObject.executeUpdate();
			}
		});
		return deleteCount.intValue();
	}

}
