/*
 * Projections.java
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
package com.mg.framework.api.orm;

import org.hibernate.criterion.ProjectionList;

import com.mg.framework.support.orm.GenericProjectionImpl;

/**
 * Фабрика создания отображений и агрегатов
 * 
 * @author Oleg V. Safonov
 * @version $Id: Projections.java,v 1.2 2006/12/12 13:54:52 safonov Exp $
 */
public final class Projections {

	/**
	 * Assign an alias to a projection, by wrapping it
	 */
	public static Projection alias(Projection projection, String alias) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.alias(((GenericProjectionImpl) projection).getHibernateProjection(), alias));
	}
	
	/**
	 * A projected property value
	 */
	public static Projection property(String propertyName) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.property(propertyName));
	}

	/**
	 * Create a distinct projection from a projection
	 */
	public static Projection distinct(Projection proj) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections
				.distinct(((GenericProjectionImpl) proj)
						.getHibernateProjection()));
	}

	/**
	 * A property maximum value
	 */
	public static Projection max(String propertyName) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.max(propertyName));
	}

	/**
	 * A property minimum value
	 */
	public static Projection min(String propertyName) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.min(propertyName));
	}

	/**
	 * A property average value
	 */
	public static Projection avg(String propertyName) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.avg(propertyName));
	}

	/**
	 * A property value sum
	 */
	public static Projection sum(String propertyName) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.sum(propertyName));
	}

	/**
	 * A grouping property value
	 */
	public static Projection groupProperty(String propertyName) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.groupProperty(propertyName));
	}

	/**
	 * A property value count
	 */
	public static Projection count(String propertyName) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.count(propertyName));
	}

	/**
	 * A distinct property value count
	 */
	public static Projection countDistinct(String propertyName) {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.countDistinct(propertyName));
	}

	/**
	 * The query row count, ie. <tt>count(*)</tt>
	 */
	public static Projection rowCount() {
		return new GenericProjectionImpl(org.hibernate.criterion.Projections.rowCount());
	}

	/**
	 * Create a new projection list
	 */
	public static Projection projectionList(Projection ... projections) {
		ProjectionList list = org.hibernate.criterion.Projections.projectionList();
		for (Projection projection : projections)
			list.add(((GenericProjectionImpl) projection).getHibernateProjection());
		return new GenericProjectionImpl(list);
	}
	
}
