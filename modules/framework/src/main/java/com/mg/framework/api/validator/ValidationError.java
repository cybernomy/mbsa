/*
 * ValidationError.java
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

/**
 * Ошибка контроля данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: ValidationError.java,v 1.1 2006/08/14 14:07:42 safonov Exp $
 */
public class ValidationError {
	private String message;
	
	public ValidationError(String message) {
		this.message = message;
	}

	/**
	 * получить сообщение об ошибки контроля данных
	 * 
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

}
