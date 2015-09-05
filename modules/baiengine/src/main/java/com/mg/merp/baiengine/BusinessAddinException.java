/*
 * BusinessAddinException.java
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
package com.mg.merp.baiengine;

import com.mg.framework.api.ApplicationException;

/**
 * Класс ИС машины бизнес расширений системы
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddinException.java,v 1.1 2006/10/12 12:02:05 safonov Exp $
 */
@javax.ejb.ApplicationException
public class BusinessAddinException extends ApplicationException {

	public BusinessAddinException(String s) {
		super(s);
	}

	public BusinessAddinException(String s, Throwable cause) {
		super(s, cause);
	}
	
}
