/*
 * ValidationException.java
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
package com.mg.framework.api.validator;

import com.mg.framework.api.ApplicationException;

/**
 * ИС контроля данных, возбуждается в случае нарушения контроля данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: ValidationException.java,v 1.1 2006/08/14 14:07:42 safonov Exp $
 */
@javax.ejb.ApplicationException
public class ValidationException extends ApplicationException {
	private Status status;
	
	public ValidationException(Status status) {
		super(status.getMessage());
		this.status = status;
	}
}
