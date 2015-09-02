/**
 * SysClassImplementationServiceLocal.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.core;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.core.model.SysClassImplementation;

/**
 * Бизнес-компонент "Реализация бизнес-компонента системы"
 * 
 * @author Oleg V. Safonov
 * @version $Id: SysClassImplementationServiceLocal.java,v 1.1 2008/03/03 12:55:18 safonov Exp $
 */
public interface SysClassImplementationServiceLocal extends
		DataBusinessObjectService<SysClassImplementation, Integer> {
	/**
	 * Имя сервиса
	 */
	final static String SERVICE_NAME = "merp/core/SysClassImpl";
}
