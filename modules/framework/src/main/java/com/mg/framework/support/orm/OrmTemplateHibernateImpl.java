/*
 * OrmTemplateHibernateImpl.java
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
package com.mg.framework.support.orm;

import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Реализация шаблона выполнения объектных запросов на базе Hibernate
 *
 * @author Oleg V. Safonov
 * @version $Id: OrmTemplateHibernateImpl.java,v 1.1 2007/07/27 09:32:54 safonov Exp $
 */
public class OrmTemplateHibernateImpl extends OrmTemplate {

  private org.hibernate.Criteria createCrireria(Session session, Class<?> persistentClass, Object[] criteria) {
    org.hibernate.Criteria hibernateCriteria = session.createCriteria(persistentClass);
    for (Object crit : criteria) {
      if (crit instanceof GenericCriterionImpl)
        hibernateCriteria = hibernateCriteria.add(((GenericCriterionImpl) crit).getHibernateCriterion());
      else if (crit instanceof OrderImpl)
        hibernateCriteria = hibernateCriteria.addOrder(((OrderImpl) crit).getHibernateOrder());
      else if (crit instanceof GenericProjectionImpl)
        hibernateCriteria = hibernateCriteria.setProjection(((GenericProjectionImpl) crit).getHibernateProjection());
      else
        throw new IllegalArgumentException("Invalid criterion type: " + crit.getClass()); //$NON-NLS-1$
    }
    return hibernateCriteria;
  }

  @Override
  protected void prepareQuery(Query queryObject) {
    if (isCacheQueries()) {
      queryObject.setCacheable(true);
      if (getQueryCacheRegion() != null) {
        queryObject.setCacheRegion(getQueryCacheRegion());
      }
    }
    if (getFetchSize() > 0) {
      queryObject.setFetchSize(getFetchSize());
    }
    if (getMaxResults() > 0) {
      queryObject.setMaxResults(getMaxResults());
    }
    if (getFlushMode() != null) {
      queryObject.setFlushMode(CriteriaHibernateImpl.convertFlushModeToHibernate(getFlushMode()));
    }
    //SessionFactoryUtils.applyTransactionTimeout(queryObject, getSessionFactory());
  }

  @Override
  protected void prepareCriteria(Criteria criteria) {
    if (isCacheQueries()) {
      criteria.setCacheable(true);
      if (getQueryCacheRegion() != null) {
        criteria.setCacheRegion(getQueryCacheRegion());
      }
    }
    if (getFetchSize() > 0) {
      criteria.setFetchSize(getFetchSize());
    }
    if (getMaxResults() > 0) {
      criteria.setMaxResults(getMaxResults());
    }
    if (getFlushMode() != null) {
      criteria.setFlushMode(getFlushMode());
    }
    //SessionFactoryUtils.applyTransactionTimeout(criteria, getSessionFactory());
  }

}
