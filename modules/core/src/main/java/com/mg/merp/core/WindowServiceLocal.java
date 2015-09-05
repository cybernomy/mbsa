/**
 * WindowServiceLocal.java
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
import com.mg.merp.core.model.Window;

/**
 * Бизнес-компонент "Окно системы"
 * 
 * @author Oleg V. Safonov
 * @version $Id: WindowServiceLocal.java,v 1.1 2008/03/03 12:55:18 safonov Exp $
 */
public interface WindowServiceLocal extends DataBusinessObjectService<Window, Integer> {
	/**
	 * Имя сервиса
	 */
	final static String SERVICE_NAME = "merp/core/SysWindow";
}
