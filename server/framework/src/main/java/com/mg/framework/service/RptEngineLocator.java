/*
 * RptEngineLocator.java
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
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.api.report.RptEngine;

/**
 * Локатор сервиса платформы MBIRT (Millennium BI and Report Tools)
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptEngineLocator.java,v 1.3 2007/08/30 14:13:42 safonov Exp $
 */
public class RptEngineLocator {
    private static volatile RptEngine instance = null;

    /**
     * поиск сервиса платформы MBIRT (Millennium BI and Report Tools)
     * 
     * @return	сервис
     * @throws	ApplicationException	при любых ошибках
     */
    public static RptEngine locate() throws ApplicationException {
        if (instance == null)
            try {
                instance = (RptEngine) ServerUtils.createMBeanProxy(RptEngine.class, RptEngine.SERVICE_NAME);
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
    }
}
