/*
 * LBScheduleManagerLocator.java
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
package com.mg.merp.document.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.LBScheduleManager;

/**
 * Локатор менеджера управления графиками исполнения обязательств
 * 
 * @author Artem V. Sharapov
 * @version $Id: LBScheduleManagerLocator.java,v 1.1 2007/04/21 11:46:07 sharapov Exp $
 */
public class LBScheduleManagerLocator {

	private static volatile LBScheduleManager instance = null;

	/**
	 * Найти сервис менеджера управления графиками исполнения обязательств
	 * @return сервис менеджера 
	 */
	public static  LBScheduleManager locate() {
		if (instance == null) {
			try {
				instance = (LBScheduleManager) ServerUtils.createMBeanProxy(LBScheduleManager.class, LBScheduleManager.SERVICE_NAME);
			}
			catch (Exception e) {
				throw new ApplicationException(e);
			}
		}
		return instance;
	}
	
}
