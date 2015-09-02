/*
 * UnrecoverableException.java
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
package com.mg.framework.api;

/**
 * Exceptions that are not expected to be recoverable by a client.
 * 
 * @author Oleg V. Safonov
 * @version $Id: UnrecoverableException.java,v 1.2 2007/04/13 13:36:02 safonov Exp $
 */
public class UnrecoverableException extends RuntimeException {
	private static final long serialVersionUID = -8110370893228861537L;

	public UnrecoverableException(String s, Throwable cause) {
		super(s, cause);
	}

	public UnrecoverableException(String s) {
		super(s);
	}

	public UnrecoverableException() {
		super();
	}

	public UnrecoverableException(Throwable cause) {
		super(cause);
	}

}
