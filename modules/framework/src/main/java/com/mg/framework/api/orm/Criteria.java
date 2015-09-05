/*
 * Criteria.java
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

import java.util.Map;

/**
 * <tt>Criteria</tt> is a simplified API for retrieving entities
 * by composing <tt>Criterion</tt> objects. This is a very
 * convenient approach for functionality like "search" screens
 * where there is a variable number of conditions to be placed
 * upon the result set.<br>
 * <br>
 * The <tt>Session</tt> is a factory for <tt>Criteria</tt>.
 * <tt>Criterion</tt> instances are usually obtained via
 * the factory methods on <tt>Restrictions</tt>. eg.
 * <pre>
 * OrmTemplate.createCriteria(Cat.class)
 *     .add( Restrictions.like("name", "Iz%") )
 *     .add( Restrictions.gt( "weight", new Float(minWeight) ) )
 *     .addOrder( Order.asc("age") );
 * </pre>
 * You may navigate associations using <tt>createAlias()</tt> or
 * <tt>createCriteria()</tt>.
 * <pre>
 * OrmTemplate.createCriteria(Cat.class)
 *     .createCriteria("kittens")
 *         .add( Restrictions.like("name", "Iz%") );
 * </pre>
 * <pre>
 * OrmTemplate.createCriteria(Cat.class)
 *     .createAlias("kittens", "kit")
 *     .add( Restrictions.like("kit.name", "Iz%") );
 * </pre>
 * You may specify projection and aggregation using <tt>Projection</tt>
 * instances obtained via the factory methods on <tt>Projections</tt>.
 * <pre>
 * OrmTemplate.createCriteria(Cat.class)
 *     .setProjection( Projections.projectionList()
 *         .add( Projections.rowCount() )
 *         .add( Projections.avg("weight") )
 *         .add( Projections.max("weight") )
 *         .add( Projections.min("weight") )
 *         .add( Projections.groupProperty("color") )
 *     )
 *     .addOrder( Order.asc("color") );
 * </pre>
 * 
 * @author Oleg V. Safonov
 * @version $Id: Criteria.java,v 1.3 2007/07/27 09:32:16 safonov Exp $
 */
public interface Criteria {

	/**
	 * Each row of results is a <tt>Map</tt> from alias to entity instance
	 */
	final static ResultTransformer<Map<String, Object>> ALIAS_TO_ENTITY_MAP = new AliasToEntityMapResultTransformer();
	
    /**
     * Add a <tt>Criterion</tt> to constrain the results to be
     * retrieved.
     *
     * @param criterion
     * @return	this (for method chaining)
     */
    Criteria add(Criterion criterion);
    
    /**
     * Add an <tt>Order</tt> to the result set.
     *
     * @param order
     * @return	this (for method chaining)
     */
    Criteria addOrder(Order order);
    
	/**
	 * Join an association, assigning an alias to the joined association.
	 * <p/>
	 * Functionally equivalent to {@link #createAlias(String, String, JoinType)} using
	 * {@link JoinType#INNER_JOIN} for the joinType.
	 * 
	 * @param associationPath	A dot-seperated property path
	 * @param alias	The alias to assign to the joined association (for later reference).
	 * @return	this (for method chaining)
	 */
    Criteria createAlias(String associationPath, String alias);
    
	/**
	 * Join an association using the specified join-type, assigning an alias
	 * to the joined association.
	 * <p/>
	 * The joinType is expected to be one of {@link JoinType#INNER_JOIN} (the default),
	 * {@link JoinType#FULL_JOIN}, or {@link JoinType#LEFT_JOIN}.
	 * 
	 * @param associationPath	A dot-seperated property path
	 * @param alias	The alias to assign to the joined association (for later reference).
	 * @param joinType	The type of join to use.
	 * @return	this (for method chaining)
	 */
    Criteria createAlias(String associationPath, String alias, JoinType joinType);
    
	/**
	 * Create a new <tt>Criteria</tt>, "rooted" at the associated entity.
	 * <p/>
	 * Functionally equivalent to {@link #createCriteria(String, int)} using
	 * {@link JoinType#INNER_JOIN} for the joinType.
	 *
	 * @param associationPath A dot-seperated property path
	 * @return the created "sub criteria"
	 */
    Criteria createCriteria(String associationPath);
    
	/**
	 * Create a new <tt>Criteria</tt>, "rooted" at the associated entity, using the
	 * specified join type.
	 *
	 * @param associationPath A dot-seperated property path
	 * @param joinType The type of join to use.
	 * @return the created "sub criteria"
	 */
    Criteria createCriteria(String associationPath, JoinType joinType);
    
	/**
	 * Create a new <tt>Criteria</tt>, "rooted" at the associated entity,
	 * assigning the given alias.
	 * <p/>
	 * Functionally equivalent to {@link #createCriteria(String, String, int)} using
	 * {@link JoinType#INNER_JOIN} for the joinType.
	 *
	 * @param associationPath A dot-seperated property path
	 * @param alias The alias to assign to the joined association (for later reference).
	 * @return the created "sub criteria"
	 */
    Criteria createCriteria(String associationPath, String alias);
    
	/**
	 * Create a new <tt>Criteria</tt>, "rooted" at the associated entity,
	 * assigning the given alias and using the specified join type.
	 * 
	 * @param associationPath	A dot-seperated property path
	 * @param alias	The alias to assign to the joined association (for later reference).
	 * @param joinType	The type of join to use.
	 * @return	the created "sub criteria"
	 */
    Criteria createCriteria(String associationPath, String alias, JoinType joinType);
    
	/**
	 * Enable caching of this query result, provided query caching is enabled
	 * for the underlying session factory.
	 *
	 * @param cacheable Should the result be considered cacheable; default is
	 * to not cache (false).
	 * @return	this (for method chaining)
	 */
    Criteria setCacheable(boolean cacheable);
    
	/**
	 * Override the cache mode for this particular query.
	 *
	 * @param cacheMode The cache mode to use.
	 * @return	this (for method chaining)
	 */
    Criteria setCacheMode(CacheMode cacheMode);
    
	/**
	 * Set the name of the cache region to use for query result caching.
	 * 
	 * @see #setCacheable(boolean)
	 *
	 * @param cacheRegion the name of a query cache region, or <tt>null</tt>
	 * for the default query cache
	 * @return	this (for method chaining)
	 */
    Criteria setCacheRegion(String cacheRegion);
    
	/**
	 * Used to specify that the query results will be a projection (scalar in
	 * nature).
	 * <p/>
	 * The individual components contained within the given
	 * {@link Projection projection} determines the overall "shape" of the
	 * query result.
	 *
	 * @param projection The projection representing the overall "shape" of the
	 * query results.
	 * @return this (for method chaining)
	 */
    Criteria setProjection(Projection projection);
    
	/**
	 * Set the first result to be retrieved.
	 *
	 * @param firstResult the first result to retrieve, numbered from <tt>0</tt>
	 * @return this (for method chaining)
	 */
    Criteria setFirstResult(int firstResult);
    
	/**
	 * Set a limit upon the number of objects to be retrieved.
	 *
	 * @param maxResults the maximum number of results
	 * @return this (for method chaining)
	 */
    Criteria setMaxResults(int maxResults);
    
	/**
	 * Set a fetch size for the underlying JDBC query.
	 *
	 * @param fetchSize the fetch size
	 * @return this (for method chaining)
	 *
	 * @see java.sql.Statement#setFetchSize
	 */
    Criteria setFetchSize(int fetchSize);
    
	/**
	 * Set a timeout for the underlying JDBC query.
	 *
	 * @param timeout The timeout value to apply.
	 * @return this (for method chaining)
	 *
	 * @see java.sql.Statement#setQueryTimeout
	 */
    Criteria setTimeout(int timeout);
    
	/**
	 * Override the flush mode for this particular query.
	 *
	 * @param flushMode The flush mode to use.
	 * @return this (for method chaining)
	 */
    Criteria setFlushMode(FlushMode flushMode);

	/**
	 * Specify an association fetching strategy for an association or a
	 * collection of values.
	 *
	 * @param associationPath a dot seperated property path
	 * @param mode The fetch mode for the referenced association
	 * @return this (for method chaining)
	 */
    Criteria setFetchMode(String associationPath, FetchMode fetchMode);
    
	/**
	 * Set a strategy for handling the query results. This determines the
	 * "shape" of the query result.
	 *
	 * @param resultTransformer The transformer to apply
	 * @return this (for method chaining)
	 */
    Criteria setResultTransformer(ResultTransformer resultTransformer);
    
}
