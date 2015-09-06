/*
 * DetachedCriteriaHibernateImpl.java
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

import com.mg.framework.api.orm.Criterion;
import com.mg.framework.api.orm.DetachedCriteria;
import com.mg.framework.api.orm.Order;
import com.mg.framework.api.orm.Projection;
import com.mg.framework.api.orm.ResultTransformer;

import java.util.List;

/**
 * Реализация критериев не связанных с текущим контекстом, не использовать данный класс в прикладном
 * коде, для создания критериев использовать класс фабрику {@link com.mg.framework.api.orm.DetachedCriteria}
 *
 * @author Oleg V. Safonov
 * @version $Id: DetachedCriteriaHibernateImpl.java,v 1.2 2007/03/23 12:00:32 safonov Exp $
 */
public class DetachedCriteriaHibernateImpl extends DetachedCriteria {
  private org.hibernate.criterion.DetachedCriteria delegate;

  public DetachedCriteriaHibernateImpl(Class<?> entityClass) {
    delegate = org.hibernate.criterion.DetachedCriteria.forClass(entityClass);
  }

  public DetachedCriteriaHibernateImpl(Class<?> entityClass, String alias) {
    delegate = org.hibernate.criterion.DetachedCriteria.forClass(entityClass, alias);
  }

  public DetachedCriteriaHibernateImpl(String entityName) {
    delegate = org.hibernate.criterion.DetachedCriteria.forEntityName(entityName);
  }

  public DetachedCriteriaHibernateImpl(String entityName, String alias) {
    delegate = org.hibernate.criterion.DetachedCriteria.forEntityName(entityName, alias);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.DetachedCriteria#getDelegate()
   */
  @Override
  public Object getDelegate() {
    return delegate;
  }

  @Override
  public DetachedCriteria add(Criterion criterion) {
    delegate.add(((GenericCriterionImpl) criterion).getHibernateCriterion());
    return this;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.DetachedCriteria#addOrder(com.mg.framework.api.orm.Order)
   */
  @Override
  public DetachedCriteria addOrder(Order order) {
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
   * @see com.mg.framework.api.orm.DetachedCriteria#createAlias(java.lang.String, java.lang.String)
   */
  @Override
  public DetachedCriteria createAlias(String associationPath, String alias) {
    delegate.createAlias(associationPath, alias);
    return this;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.DetachedCriteria#createCriteria(java.lang.String, java.lang.String)
   */
  @Override
  public DetachedCriteria createCriteria(String associationPath, String alias) {
    delegate.createCriteria(associationPath, alias);
    return this;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.DetachedCriteria#createCriteria(java.lang.String)
   */
  @Override
  public DetachedCriteria createCriteria(String associationPath) {
    delegate.createCriteria(associationPath);
    return this;
  }

	/* (non-Javadoc)
     * @see com.mg.framework.api.orm.DetachedCriteria#setFetchMode(java.lang.String, com.mg.framework.api.orm.FetchMode)
	 */
//	@Override
//	public DetachedCriteria setFetchMode(String associationPath, FetchMode fetchMode) {
//		delegate.setFetchMode(associationPath, CriteriaHibernateImpl.convertFetchModeToHibernate(fetchMode));
//		return this;
//	}

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.DetachedCriteria#setProjection(com.mg.framework.api.orm.Projection)
   */
  @Override
  public DetachedCriteria setProjection(Projection projection) {
    delegate.setProjection(((GenericProjectionImpl) projection).getHibernateProjection());
    return this;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.orm.DetachedCriteria#setResultTransformer(com.mg.framework.api.orm.ResultTransformer)
   */
  @Override
  public DetachedCriteria setResultTransformer(final ResultTransformer resultTransformer) {
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
