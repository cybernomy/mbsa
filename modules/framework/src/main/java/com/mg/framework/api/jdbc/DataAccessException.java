/*
 * DataAccessException.java
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
package com.mg.framework.api.jdbc;

/**
 * @author Oleg V. Safonov
 *
 */
public class DataAccessException extends
        com.mg.framework.api.DataAccessException {
	public DataAccessException(String s, Throwable cause) {
		super(s, cause);
	}
	
	public DataAccessException(String s) {
		super(s);
	}	

	public DataAccessException() {
		super();
	}	

	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
