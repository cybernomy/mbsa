/*
 * Restrictions.java
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
import java.util.Map;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;

import com.mg.framework.support.orm.GenericCriterionImpl;

/**
 * Фабрика создания критериев ограничений
 * 
 * @author Oleg V. Safonov
 * @version $Id: Restrictions.java,v 1.5 2007/10/17 11:56:09 safonov Exp $
 */
public class Restrictions {

    public static org.hibernate.criterion.MatchMode matchModeToHibernateMatchMode(MatchMode matchMode) {
        switch (matchMode) {
        case ANYWHERE: return org.hibernate.criterion.MatchMode.ANYWHERE;
        case EXACT: return org.hibernate.criterion.MatchMode.EXACT;
        case START: return org.hibernate.criterion.MatchMode.START;
        case END: return org.hibernate.criterion.MatchMode.END;
        default:
            throw new IllegalArgumentException("Invalid MatchMode"); //$NON-NLS-1$
        }
    }
    
    /**
     * Apply an "equal" constraint to the identifier property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion idEq(Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.idEq(value));
    }
    
    /**
     * Apply an "equal" constraint to the named property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion eq(String propertyName, Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.eq(propertyName, value));
    }
    /**
     * Apply a "not equal" constraint to the named property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion ne(String propertyName, Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.ne(propertyName, value));
    }
    
    /**
     * Apply a "greater than" constraint to the named property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion gt(String propertyName, Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.gt(propertyName, value));
    }
    /**
     * Apply a "less than" constraint to the named property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion lt(String propertyName, Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.lt(propertyName, value));
    }
    /**
     * Apply a "less than or equal" constraint to the named property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion le(String propertyName, Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.le(propertyName, value));
    }
    /**
     * Apply a "greater than or equal" constraint to the named property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion ge(String propertyName, Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.ge(propertyName, value));
    }
    
    /**
     * Apply a "like" constraint to the named property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion like(String propertyName, Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.like(propertyName, value));
    }
    /**
     * Apply a "like" constraint to the named property
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion like(String propertyName, String value, MatchMode matchMode) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.like(propertyName, value, matchModeToHibernateMatchMode(matchMode))); //$NON-NLS-1$
    }
    
    /**
     * A case-insensitive "like", similar to Postgres <tt>ilike</tt>
     * operator
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion ilike(String propertyName, String value, MatchMode matchMode) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.ilike(propertyName, value, matchModeToHibernateMatchMode(matchMode)));
    }
    /**
     * A case-insensitive "like", similar to Postgres <tt>ilike</tt>
     * operator
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static Criterion ilike(String propertyName, Object value) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.ilike(propertyName, value));
    }
    
    /**
     * Apply a "between" constraint to the named property
     * @param propertyName
     * @param lo value
     * @param hi value
     * @return Criterion
     */
    public static Criterion between(String propertyName, Object lo, Object hi) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.between(propertyName, lo, hi));
    }
    /**
     * Apply an "in" constraint to the named property
     * @param propertyName
     * @param values
     * @return Criterion
     */
    public static Criterion in(String propertyName, Object ... values) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.in(propertyName, values));
    }
    /**
     * Apply an "in" constraint to the named property
     * @param propertyName
     * @param values
     * @return Criterion
     */
    public static Criterion in(String propertyName, Collection<?> values) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.in(propertyName, values.toArray()));
    }
    /**
     * Apply an "is null" constraint to the named property
     * @return Criterion
     */
    public static Criterion isNull(String propertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.isNull(propertyName));
    }
    /**
     * Apply an "equal" constraint to two properties
     */
    public static Criterion eqProperty(String propertyName, String otherPropertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.eqProperty(propertyName, otherPropertyName));
    }
    /**
     * Apply a "not equal" constraint to two properties
     */
    public static Criterion neProperty(String propertyName, String otherPropertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.neProperty(propertyName, otherPropertyName));
    }
    /**
     * Apply a "less than" constraint to two properties
     */
    public static Criterion ltProperty(String propertyName, String otherPropertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.ltProperty(propertyName, otherPropertyName));
    }
    /**
     * Apply a "less than or equal" constraint to two properties
     */
    public static Criterion leProperty(String propertyName, String otherPropertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.leProperty(propertyName, otherPropertyName));
    }
    /**
     * Apply a "greater than" constraint to two properties
     */
    public static Criterion gtProperty(String propertyName, String otherPropertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.gtProperty(propertyName, otherPropertyName));
    }
    /**
     * Apply a "greater than or equal" constraint to two properties
     */
    public static Criterion geProperty(String propertyName, String otherPropertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.geProperty(propertyName, otherPropertyName));
    }
    /**
     * Apply an "is not null" constraint to the named property
     * @return Criterion
     */
    public static Criterion isNotNull(String propertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.isNotNull(propertyName));
    }
    /**
     * Return the conjuction of two expressions
     *
     * @param lhs
     * @param rhs
     * @return Criterion
     */
    public static Criterion and(Criterion lhs, Criterion rhs) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.and(((GenericCriterionImpl) lhs).getHibernateCriterion(), ((GenericCriterionImpl) rhs).getHibernateCriterion()));
    }
    /**
     * Return the disjuction of two expressions
     *
     * @param lhs
     * @param rhs
     * @return Criterion
     */
    public static Criterion or(Criterion lhs, Criterion rhs) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.or(((GenericCriterionImpl) lhs).getHibernateCriterion(), ((GenericCriterionImpl) rhs).getHibernateCriterion()));
    }
    /**
     * Return the negation of an expression
     *
     * @param expression
     * @return Criterion
     */
    public static Criterion not(Criterion expression) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.not(((GenericCriterionImpl) expression).getHibernateCriterion()));
    }
    
    /**
     * Constrain a collection valued property to be empty
     */
    public static Criterion isEmpty(String propertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.isEmpty(propertyName));
    }

    /**
     * Constrain a collection valued property to be non-empty
     */
    public static Criterion isNotEmpty(String propertyName) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.isNotEmpty(propertyName));
    }
    
    /**
     * Constrain a collection valued property by size
     */
    public static Criterion sizeEq(String propertyName, int size) {
        return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.sizeEq(propertyName, size));
    }
    
    /**
	 * Apply a constraint expressed in SQL. Any occurrences of <tt>{alias}</tt>
	 * will be replaced by the table alias.
     * 
     * @param sql	sql text
     * @return	criterion
     */
    public static Criterion sqlRestriction(String sql) {
    	return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.sqlRestriction(sql));
    }

    /**
     * Group expressions together in a single conjunction (A and B and C...)
     * 
     * @param criteria	criteria
     * @return	conjunction criterion
     */
    public static Criterion conjunction(Criterion ... criteria) {
    	Conjunction conjunction = org.hibernate.criterion.Restrictions.conjunction();
    	for (Criterion criterion : criteria)
    		conjunction.add(((GenericCriterionImpl) criterion).getHibernateCriterion());
    	return new GenericCriterionImpl(conjunction);
    }
    
    /**
     * Group expressions together in a single disjunction (A or B or C...)
     * 
     * @param criteria	criteria
     * @return	disjunction criterion
     */
    public static Criterion disjunction(Criterion ... criteria) {
    	Disjunction disjunction = org.hibernate.criterion.Restrictions.disjunction();
    	for (Criterion criterion : criteria)
    		disjunction.add(((GenericCriterionImpl) criterion).getHibernateCriterion());
    	return new GenericCriterionImpl(disjunction);
    }
    
    /**
	 * Apply an "equals" constraint to each property in the
	 * key set of a <tt>Map</tt>
     * 
     * @param propertyNameValues	a map from property names to values
     * @return	Criterion
     */
    public static Criterion allEq(Map<String, Object> propertyNameValues) {
    	return new GenericCriterionImpl(org.hibernate.criterion.Restrictions.allEq(propertyNameValues));
    }
    
}
