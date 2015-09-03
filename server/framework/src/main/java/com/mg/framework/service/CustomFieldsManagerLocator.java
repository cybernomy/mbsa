/*
 * CustomFieldsManagerLocator.java
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор менеджера пользовательских полей
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomFieldsManagerLocator.java,v 1.1 2007/01/25 15:25:33 safonov Exp $
 */
public class CustomFieldsManagerLocator {

	private static volatile CustomFieldsManager instance = null;

	/**
	 * найти сервис менеджера пользовательских полей
	 * 
	 * @return	сервис
	 * @throws ApplicationException	при любых ошибках
	 */
	public static CustomFieldsManager locate() {
		if (instance == null)
			try {
				instance = (CustomFieldsManager) ServerUtils.createMBeanProxy(CustomFieldsManager.class, CustomFieldsManager.SERVICE_NAME); //$NON-NLS-1$
			}
		catch (Exception e) {
			throw new ApplicationException(e);
		}
		return instance;
	}

}
