/*
 * SecurityException.java
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

package com.mg.framework.api;


/**
 * Класс ИС применяемый для указания нарушений прав доступа, возбуждается в прикладном
 * коде в случае нарушения прав доступа
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
@javax.ejb.ApplicationException
public class SecurityException extends ApplicationException {
	private static final long serialVersionUID = -7742196465936049775L;

	/**
	 * создает ИС на оновании ИС причины, как правило служит для обертки системных ИС безопасности,
	 * например {@link java.lang.SecurityException SecurityException}
	 * 
	 * @param s		сообщение
	 * @param cause	ИС причины
	 */
	public SecurityException(String s, Throwable cause) {
		super(s, cause);
	}
	
	/**
	 * создает ИС с сообщением, может возбуждаться непосредственно в прикладном коде в случае
	 * нарушения прав доступа
	 * 
	 * @param s
	 */
	public SecurityException(String s) {
		super(s);
	}	

	/**
	 * создает ИС
	 *
	 */
	public SecurityException() {
		super();
	}	

}
