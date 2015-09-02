/*
 * HelpSystemLocator.java
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
import com.mg.framework.api.help.HelpSystem;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор сервиса системы помощи
 * 
 * @author Oleg V. Safonov
 * @version $Id: HelpSystemLocator.java,v 1.1 2006/11/11 10:34:32 safonov Exp $
 */
public class HelpSystemLocator {
    private static volatile HelpSystem instance = null;
    
    /**
     * поиск сервиса
     * 
     * @return	сервис
     * @throws ApplicationException	при возникновении любых ошибок
     */
    public static HelpSystem locate() throws ApplicationException {
        if (instance == null)
            try {
                instance = (HelpSystem) ServerUtils.createMBeanProxy(HelpSystem.class, HelpSystem.SERVICE_NAME);
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
    }
}
