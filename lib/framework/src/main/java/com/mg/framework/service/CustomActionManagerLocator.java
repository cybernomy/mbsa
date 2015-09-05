/**
 * CustomActionManagerLocator.java
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.ui.CustomActionManager;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор менеджера настраиваемых действий
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionManagerLocator.java,v 1.1 2007/11/15 08:37:31 safonov Exp $
 */
public class CustomActionManagerLocator {

	private static volatile CustomActionManager instance = null;

	/**
	 * найти сервис менеджера настраиваемых действий
	 * 
	 * @return	сервис
	 * @throws ApplicationException	при любых ошибках
	 */
	public static CustomActionManager locate() {
		if (instance == null)
			try {
				instance = (CustomActionManager) ServerUtils.createMBeanProxy(CustomActionManager.class, CustomActionManager.SERVICE_NAME); //$NON-NLS-1$
			}
		catch (Exception e) {
			throw new ApplicationException(e);
		}
		return instance;
	}

}
