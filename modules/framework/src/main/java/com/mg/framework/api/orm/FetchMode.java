/*
 * FetchMode.java
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
 * Represents an association fetching strategy. This is used
 * together with the <tt>Criteria</tt> API to specify runtime
 * fetching strategies.<br>
 * <br>
 * For HQL queries, use the <tt>FETCH</tt> keyword instead.
 * 
 * @author Oleg V. Safonov
 * @version $Id: FetchMode.java,v 1.1 2006/12/12 13:54:52 safonov Exp $
 */
public enum FetchMode {
	/**
	 * Default to the setting configured in the mapping file.
	 */
	DEFAULT,
	
	/**
	 * Fetch using an outer join. Equivalent to <tt>fetch="join"</tt>.
	 */
	JOIN,

	/**
	 * Fetch eagerly, using a separate select. Equivalent to
	 * <tt>fetch="select"</tt>.
	 */
	SELECT
}
