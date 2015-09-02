/*
 * DetachedCriteria.java
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
package com.mg.framework.api.orm;

import com.mg.framework.support.orm.DetachedCriteriaHibernateImpl;

/**
 * Some applications need to create criteria queries in "detached
 * mode". All methods have the
 * same semantics and behavior as the corresponding methods of the
 * <literal>Criteria</literal> interface.
 * 
 * <p>For example:
 * <pre>
 * DetachedCriteria dc = DetachedCriteria.forClass(Student.class)
 * 			.add( Property.forName("studentNumber").eq( new Long(232) ) )
 * 			.setProjection( Property.forName("name") );
 * 
 * Criteria criteria = OrmTemplate.createCriteria(Student.class).add(Subqueries.exists(dc));
 * </pre>
 * 
 * @see Criteria
 * 
 * @author Oleg V. Safonov
 * @version $Id: DetachedCriteria.java,v 1.2 2007/03/23 11:56:29 safonov Exp $
 */
public abstract class DetachedCriteria {
	Criteria criteria;

	protected DetachedCriteria() {
		
	}
	
	private DetachedCriteria(String entityName) {
		criteria = OrmTemplate.createCriteria(entityName);
	}
	
	public static DetachedCriteria forEntityName(String entityName) {
		return new DetachedCriteriaHibernateImpl(entityName);
	}
	
	public static DetachedCriteria forEntityName(String entityName, String alias) {
		return new DetachedCriteriaHibernateImpl(entityName, alias);
	}
	
	public static DetachedCriteria forClass(Class<?> entityClass) {
		return new DetachedCriteriaHibernateImpl(entityClass);
	}

	public static DetachedCriteria forClass(Class<?> entityClass, String alias) {
		return new DetachedCriteriaHibernateImpl(entityClass, alias);
	}

	public abstract DetachedCriteria add(Criterion criterion);

	public abstract DetachedCriteria addOrder(Order order);
	
	public abstract DetachedCriteria createAlias(String associationPath, String alias);
	
	public abstract DetachedCriteria createCriteria(String associationPath);
	
	public abstract DetachedCriteria createCriteria(String associationPath, String alias);
	
	//public abstract DetachedCriteria setFetchMode(String associationPath, FetchMode fetchMode);
	
	public abstract DetachedCriteria setProjection(Projection projection);
	
	public abstract DetachedCriteria setResultTransformer(ResultTransformer resultTransformer);
	
	public abstract Object getDelegate();
	
}
