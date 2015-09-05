/*
 * FlushMode.java
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

import javax.transaction.Transaction;

/**
 * Represents a flushing strategy. The flush process synchronizes
 * database state with session state by detecting state changes
 * and executing SQL statements.
 *
 * @author Oleg V. Safonov
 * @version $Id: FlushMode.java,v 1.1 2006/12/12 13:54:52 safonov Exp $
 */
public enum FlushMode {
	/**
	 * The {@link PersistentManager} is only ever flushed when {@link PersistentManager#flush}
	 * is explicitly called by the application. This mode is very
	 * efficient for read only transactions.
	 */
	MANUAL,
	
	/**
	 * The {@link PersistentManager} is flushed when {@link Transaction#commit}
	 * is called.
	 */
	COMMIT,
	
	/**
	 * The {@link PersistentManager} is sometimes flushed before query execution
	 * in order to ensure that queries never return stale state. This
	 * is the default flush mode.
	 */
	AUTO,
	
	/**
	 * The {@link PersistentManager} is flushed before every query. This is
	 * almost always unnecessary and inefficient.
	 */
	ALWAYS
}
