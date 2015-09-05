/*
 * CriteriaHibernateImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.support.orm;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;

import com.mg.framework.api.orm.CacheMode;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.Criterion;
import com.mg.framework.api.orm.FetchMode;
import com.mg.framework.api.orm.FlushMode;
import com.mg.framework.api.orm.JoinType;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.ResultTransformer;

/**
 * Реализация API критериев, не использовать данный класс в прикладном коде, для создания
 * критериев использовать {@link com.mg.framework.api.orm.OrmTemplate#createCriteria}
 * 
 * @author Oleg V. Safonov
 * @version $Id: CriteriaHibernateImpl.java,v 1.2 2007/07/27 09:32:54 safonov Exp $
 */
public class CriteriaHibernateImpl implements Criteria {
	private org.hibernate.Criteria delegate;

	protected static int convertJoinModeToHibernate(JoinType joinType) {
		switch (joinType) {
		case FULL_JOIN: return CriteriaSpecification.FULL_JOIN;
		case INNER_JOIN: return CriteriaSpecification.INNER_JOIN;
		case LEFT_JOIN: return CriteriaSpecification.LEFT_JOIN;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	protected static org.hibernate.CacheMode convertCacheModeToHibernate(CacheMode cacheMode) {
		switch (cacheMode) {
		case GET: return org.hibernate.CacheMode.GET;
		case IGNORE: return org.hibernate.CacheMode.IGNORE;
		case NORMAL: return org.hibernate.CacheMode.NORMAL;
		case PUT: return org.hibernate.CacheMode.PUT;
		case REFRESH: return org.hibernate.CacheMode.REFRESH;
		default:
			throw new IllegalArgumentException();
		}
	}

	protected static org.hibernate.FlushMode convertFlushModeToHibernate(FlushMode flushMode) {
		switch (flushMode) {
		case ALWAYS: return org.hibernate.FlushMode.ALWAYS;
		case AUTO: return org.hibernate.FlushMode.AUTO;
		case COMMIT: return org.hibernate.FlushMode.COMMIT;
		case MANUAL: return org.hibernate.FlushMode.MANUAL;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	protected static org.hibernate.FetchMode convertFetchModeToHibernate(FetchMode fetchMode) {
		switch (fetchMode) {
		case DEFAULT: return org.hibernate.FetchMode.DEFAULT;
		case JOIN: return org.hibernate.FetchMode.JOIN;
		case SELECT: return org.hibernate.FetchMode.SELECT;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * constructor, do not call direct from business logic
	 * 
	 * @param delegate
	 */
	public CriteriaHibernateImpl(org.hibernate.Criteria delegate) {
		this.delegate = delegate;
	}

	/**
	 * @return the delegate
	 */
	public org.hibernate.Criteria getDelegate() {
		return delegate;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#add(org.hibernate.criterion.Criterion)
	 */
	public Criteria add(Criterion criterion) {
		if (criterion instanceof GenericCriterionImpl)
			delegate.add(((GenericCriterionImpl) criterion).getHibernateCriterion());
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#addOrder(org.hibernate.criterion.Order)
	 */
	public Criteria addOrder(Order order) {
		org.hibernate.criterion.Order orderHibernate;
		if (order.isAscending())
			orderHibernate = org.hibernate.criterion.Order.asc(order.getPropertyName());
		else
			orderHibernate = org.hibernate.criterion.Order.desc(order.getPropertyName());
		
		if (order.isIgnoreCase())
			orderHibernate.ignoreCase();
		
		delegate.addOrder(orderHibernate);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setProjection(com.mg.framework.api.orm.Projection)
	 */
	public Criteria setProjection(Projection projection) {
		if (projection instanceof GenericProjectionImpl)
			delegate.setProjection(((GenericProjectionImpl) projection).getHibernateProjection());
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#createAlias(java.lang.String, java.lang.String, com.mg.framework.api.orm.JoinType)
	 */
	public Criteria createAlias(String associationPath, String alias, JoinType joinType) {
		delegate.createAlias(associationPath, alias, convertJoinModeToHibernate(joinType));
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#createAlias(java.lang.String, java.lang.String)
	 */
	public Criteria createAlias(String associationPath, String alias) {
		return createAlias(associationPath, alias, JoinType.INNER_JOIN);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#createCriteria(java.lang.String, com.mg.framework.api.orm.JoinType)
	 */
	public Criteria createCriteria(String associationPath, JoinType joinType) {
		delegate.createCriteria(associationPath, convertJoinModeToHibernate(joinType));
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#createCriteria(java.lang.String, java.lang.String, com.mg.framework.api.orm.JoinType)
	 */
	public Criteria createCriteria(String associationPath, String alias, JoinType joinType) {
		delegate.createCriteria(associationPath, alias, convertJoinModeToHibernate(joinType));
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#createCriteria(java.lang.String, java.lang.String)
	 */
	public Criteria createCriteria(String associationPath, String alias) {
		return createCriteria(associationPath, alias, JoinType.INNER_JOIN);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#createCriteria(java.lang.String)
	 */
	public Criteria createCriteria(String associationPath) {
		return createCriteria(associationPath, JoinType.INNER_JOIN);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setCacheable(boolean)
	 */
	public Criteria setCacheable(boolean cacheable) {
		delegate.setCacheable(cacheable);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setCacheMode(com.mg.framework.api.orm.CacheMode)
	 */
	public Criteria setCacheMode(CacheMode cacheMode) {
		delegate.setCacheMode(convertCacheModeToHibernate(cacheMode));
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setCacheRegion(java.lang.String)
	 */
	public Criteria setCacheRegion(String cacheRegion) {
		delegate.setCacheRegion(cacheRegion);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setFetchSize(int)
	 */
	public Criteria setFetchSize(int fetchSize) {
		delegate.setFetchSize(fetchSize);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setFirstResult(int)
	 */
	public Criteria setFirstResult(int firstResult) {
		delegate.setFirstResult(firstResult);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setMaxResults(int)
	 */
	public Criteria setMaxResults(int maxResults) {
		delegate.setMaxResults(maxResults);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setTimeout(int)
	 */
	public Criteria setTimeout(int timeout) {
		delegate.setTimeout(timeout);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setFlushMode(com.mg.framework.api.orm.FlushMode)
	 */
	public Criteria setFlushMode(FlushMode flushMode) {
		delegate.setFlushMode(convertFlushModeToHibernate(flushMode));
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setFetchMode(java.lang.String, com.mg.framework.api.orm.FetchMode)
	 */
	public Criteria setFetchMode(String associationPath, FetchMode fetchMode) {
		delegate.setFetchMode(associationPath, convertFetchModeToHibernate(fetchMode));
		return this;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.orm.Criteria#setResultTransformer(com.mg.framework.api.orm.ResultTransformer)
	 */
	public Criteria setResultTransformer(final ResultTransformer resultTransformer) {
		delegate.setResultTransformer(new org.hibernate.transform.ResultTransformer() {

			public List transformList(List collection) {
				return collection;
			}

			public Object transformTuple(Object[] tuple, String[] aliases) {
				return resultTransformer.transformTuple(tuple, aliases);
			}
			
		});
		return this;
	}

}
