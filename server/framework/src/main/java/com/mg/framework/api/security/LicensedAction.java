/*
 * LicensedAction.java
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
package com.mg.framework.api.security;

/**
 * Лицензированное действие, действие которое будет выполнено если на него существует лицензия
 * 
 * @author Oleg V. Safonov
 * @version $Id: LicensedAction.java,v 1.1 2007/04/13 14:14:48 safonov Exp $
 */
public interface LicensedAction<T> {
	
	/**
	 * выполнение лицензионного действия
	 * 
	 * @return	результат действия
	 */
	T run();

}
