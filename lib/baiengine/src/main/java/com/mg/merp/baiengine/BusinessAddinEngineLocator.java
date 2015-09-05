/*
 * BusinessAddinEngineLocator.java
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
package com.mg.merp.baiengine;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.utils.ServerUtils;


/**
 * Локатор сервиса машины бизнес расширений системы
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddinEngineLocator.java,v 1.1 2006/06/08 11:39:47 safonov Exp $
 */
public class BusinessAddinEngineLocator {
	private static volatile BusinessAddinEngine instance = null;
	
	/**
	 * получить сервис машины бизнес расширений системы
	 * 
	 * @return	сервис BAi
	 */
	public static BusinessAddinEngine locate() {
        if (instance == null)
            try {
                instance = (BusinessAddinEngine) ServerUtils.createMBeanProxy(BusinessAddinEngine.class, BusinessAddinEngine.SERVICE_NAME);
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
	}
}
