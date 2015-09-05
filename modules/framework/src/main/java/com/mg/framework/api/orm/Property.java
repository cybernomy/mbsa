/*
 * Property.java
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

import java.util.Collection;

/**
 * A factory for property-specific criterion and projection instances
 * <pre>
 * OrmTemplate.createCriteria(Cat.class)
 *     .add( Property.forName("name").like("Iz%") )
 *     .add( Property.forName("weight").gt(new Float(minWeight) ) )
 *     .addOrder( Order.asc("age") );
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: Property.java,v 1.2 2006/12/12 13:54:52 safonov Exp $
 */
public class Property {
	private String propertyName;

	private Property() {
	}

	/**
	 * create property
	 * 
	 * @param propertyName	property name
	 * @return	instance of property
	 */
	public static Property forName(String propertyName) {
		Property result = new Property();
		result.propertyName = propertyName;
		return result;
	}

	/**
	 * Get a component attribute of this property
	 */
	public Property getProperty(String propertyName) {
		return forName(this.propertyName + '.' + propertyName);
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Order asc() {
		return Order.asc(propertyName);
	}

	public Order desc() {
		return Order.desc(propertyName);
	}

	public Criterion between(Object min, Object max) {
		return Restrictions.between(propertyName, min, max);
	}

	public Projection count() {
		return Projections.count(propertyName);
	}

	public Criterion eq(Object value) {
		return Restrictions.eq(propertyName, value);
	}

	public Criterion eqProperty(String other) {
		return Restrictions.eqProperty(propertyName, other);
	}

	public Criterion eqProperty(Property other) {
		return Restrictions.eqProperty(propertyName, other.propertyName);
	}

	public Criterion ge(Object value) {
		return Restrictions.ge(propertyName, value);
	}

	public Criterion geProperty(String other) {
		return Restrictions.geProperty(propertyName, other);
	}

	public Criterion geProperty(Property other) {
		return Restrictions.geProperty(propertyName, other.propertyName);
	}

	public Projection group() {
		return Projections.groupProperty(propertyName);
	}

	public Criterion gt(Object value) {
		return Restrictions.gt(propertyName, value);
	}

	public Criterion gtProperty(String other) {
		return Restrictions.gtProperty(propertyName, other);
	}

	public Criterion gtProperty(Property other) {
		return Restrictions.gtProperty(propertyName, other.propertyName);
	}

	public Criterion in(Object[] values) {
		return Restrictions.in(propertyName, values);
	}

	public Criterion in(Collection values) {
		return Restrictions.in(propertyName, values);
	}

	public Criterion isEmpty() {
		return Restrictions.isEmpty(propertyName);
	}

	public Criterion isNotEmpty() {
		return Restrictions.isNotEmpty(propertyName);
	}

	public Criterion isNotNull() {
		return Restrictions.isNotNull(propertyName);
	}

	public Criterion isNull() {
		return Restrictions.isNull(propertyName);
	}

	public Criterion le(Object value) {
		return Restrictions.le(propertyName, value);
	}

	public Criterion leProperty(String other) {
		return Restrictions.leProperty(propertyName, other);
	}

	public Criterion leProperty(Property other) {
		return Restrictions.leProperty(propertyName, other.getPropertyName());
	}

	public Criterion like(Object value) {
		return Restrictions.like(propertyName, value);
	}

	public Criterion like(String value, MatchMode matchMode) {
		return Restrictions.like(propertyName, value, matchMode);
	}

	public Criterion lt(Object value) {
		return Restrictions.lt(propertyName, value);
	}

	public Criterion ltProperty(String other) {
		return Restrictions.ltProperty(propertyName, other);
	}

	public Criterion ltProperty(Property other) {
		return Restrictions.ltProperty(propertyName, other.getPropertyName());
	}

	public Projection max() {
		return Projections.max(propertyName);
	}

	public Projection min() {
		return Projections.min(propertyName);
	}

	public Criterion ne(Object value) {
		return Restrictions.ne(propertyName, value);
	}

	public Criterion neProperty(String other) {
		return Restrictions.neProperty(propertyName, other);
	}

	public Criterion neProperty(Property other) {
		return Restrictions.neProperty(propertyName, other.getPropertyName());
	}

}
