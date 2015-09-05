/*
 * ReasonException.java
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
 */
package com.mg.framework.api;

/**
 * @author krivopoustov
 * @author Oleg V. Safonov
 * @version $Id: ReasonException.java,v 1.3 2006/06/28 11:41:03 safonov Exp $
 */
@javax.ejb.ApplicationException
public class ReasonException extends ApplicationException {
	private String reason;
	
	public ReasonException(String msg, String reason) {
		super(msg);
		this.reason = reason;
	}

    public ReasonException(String msg, String reason, Throwable cause) {
        super(msg, cause);
        this.reason = reason;
    }
    
	public String getReason() {
		return this.reason;
	}
}
