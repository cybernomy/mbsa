/**
 * ReportException.java.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.report;

import com.mg.framework.api.ApplicationException;

/**
 * ИС платформы MBIRT (Millennium BI and Report Tools)
 * 
 * @author Oleg V. Safonov
 * @version $Id: ReportException.java,v 1.2 2008/08/12 09:22:12 safonov Exp $
 */
public class ReportException extends ApplicationException {

	public ReportException(String message) {
		super(message);
	}
	
	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReportException(Throwable cause) {
		super(cause);
	}

}
