/*
 * Example.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import com.mg.framework.support.orm.GenericCriterionImpl;

/**
 * @author Oleg V. Safonov
 * @version $Id: Example.java,v 1.1 2006/03/27 10:36:47 safonov Exp $
 */
public final class Example {

	/**
	 * A strategy for choosing property values for inclusion in the query
	 * criteria
	 */
	public enum PropertySelector {
		/**
		 * Includes all non-null properties, default
		 */
		NOT_NULL,
		
		/**
		 * Don't exclude null or zero-valued properties
		 */
		ALL,
		
		/**
		 * Exclude zero-valued properties
		 */
		NOT_NULL_OR_ZERO
	}
	
	/**
	 * Create a new instance, which includes all non-null properties
	 * by default
	 * 
	 * @param entity
	 * @return a new instance of <tt>Example</tt>
	 */
	public static Criterion create(PersistentObject entity) {
		return Example.create(entity, null, null, PropertySelector.NOT_NULL, false);
	}

	/**
	 * Create a new instance
	 * 
	 * @param entity			entity
	 * @param excludeProperty	exclude properties, maybe null
	 * @param likeMode			match mode for like operator, maybe null
	 * @param propertySelector	property selector, maybe null, use default NOT_NULL
	 * @param ignoreCase		ignore case
	 * @return					a new instance of <tt>Example</tt>
	 */
	public static Criterion create(PersistentObject entity, String[] excludeProperty, MatchMode likeMode, PropertySelector propertySelector, boolean ignoreCase) {
		org.hibernate.criterion.Example example = org.hibernate.criterion.Example.create(entity);
		if (excludeProperty != null)
			for (String exclude : excludeProperty)
				example.excludeProperty(exclude);
		if (likeMode != null)
			example.enableLike(Restrictions.matchModeToHibernateMatchMode(likeMode));
		if (ignoreCase)
			example.ignoreCase();
		if (propertySelector != null) {
			switch (propertySelector) {
			case ALL:
				example.excludeNone();
				break;
			case NOT_NULL_OR_ZERO:
				example.excludeZeroes();
				break;
			}
		}
		return new GenericCriterionImpl(example);
	}

}
