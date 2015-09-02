/*
 * JoinType.java
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
 * Join type
 * 
 * @author Oleg V. Safonov
 * @version $Id: JoinType.java,v 1.1 2006/12/12 13:54:52 safonov Exp $
 */
public enum JoinType {
	/**
	 * Specifies joining to an entity based on an inner join.
	 */
	INNER_JOIN,
	
	/**
	 * Specifies joining to an entity based on a full join.
	 */
	FULL_JOIN,
	
	/**
	 * Specifies joining to an entity based on a left outer join.
	 */
	LEFT_JOIN
}
