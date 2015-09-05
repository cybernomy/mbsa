/*
 * BusinessException.java
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
 * Базовый класс прикладных ИС
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessException.java,v 1.2 2006/08/25 11:26:10 safonov Exp $
 */
@javax.ejb.ApplicationException
public class BusinessException extends ApplicationException {

	public BusinessException(String s, Throwable cause) {
		super(s, cause);
	}
	
	public BusinessException(String s) {
		super(s);
	}	

	public BusinessException() {
		super();
	}	

}
