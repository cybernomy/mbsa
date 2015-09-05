/*
 * AbstractRule.java
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
 * Правило контроля данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: Rule.java,v 1.1 2006/08/14 14:07:42 safonov Exp $
 */
public interface Rule {
	
	/**
	 * выполнить проверку
	 * 
	 * @param context	контекст контроля данных
	 */
	void validate(ValidationContext context);
	
	/**
	 * получить сообщение правила
	 * 
	 * @return	сообщение
	 */
	String getMessage();
}
