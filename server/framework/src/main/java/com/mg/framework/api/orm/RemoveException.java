/*
 * MetadataCacheLocator.java
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

import com.mg.framework.api.DataAccessException;

/**
 * @author Oleg V. Safonov
 *
 */
@javax.ejb.ApplicationException
public class RemoveException extends DataAccessException {
	public RemoveException(String s, Throwable cause) {
		super(s, cause);
	}
	
	public RemoveException(String s) {
		super(s);
	}	

	public RemoveException() {
		super();
	}	

	public RemoveException(Throwable cause) {
		super(cause);
	}
}
