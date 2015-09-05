/*
 * CacheMode.java
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

/**
 * Controls how the session interacts with the second-level
 * cache and query cache.
 * 
 * @author Oleg V. Safonov
 * @version $Id: CacheMode.java,v 1.1 2006/12/12 13:54:52 safonov Exp $
 */
public enum CacheMode {
	/**
	 * The session may read items from the cache, and add items to the cache
	 */
	NORMAL,
	
	/**
	 * The session will never interact with the cache, except to invalidate
	 * cache items when updates occur
	 */
	IGNORE,
	
	/**
	 * The session may read items from the cache, but will not add items, 
	 * except to invalidate items when updates occur
	 */
	GET,

	/**
	 * The session will never read items from the cache, but will add items
	 * to the cache as it reads them from the database.
	 */
	PUT,

	/**
	 * The session will never read items from the cache, but will add items
	 * to the cache as it reads them from the database. In this mode, the
	 * effect of <tt>hibernate.cache.use_minimal_puts</tt> is bypassed, in
	 * order to <em>force</em> a cache refresh
	 */
	REFRESH
}
