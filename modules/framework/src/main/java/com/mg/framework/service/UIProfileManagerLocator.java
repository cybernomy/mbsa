/*
 * UIProfileManagerLocator.java
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
import com.mg.framework.api.ui.UIProfileManager;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор менеджера профилей пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: UIProfileManagerLocator.java,v 1.1 2007/03/13 13:31:40 safonov Exp $
 */
public class UIProfileManagerLocator {
    private static volatile UIProfileManager instance = null;
    
    /**
     * поиск сервиса
     * 
     * @return	сервис
     * @throws ApplicationException в случае любой ИС
     */
    public static UIProfileManager locate() throws ApplicationException {
        if (instance == null)
            try {
                instance = (UIProfileManager) ServerUtils.createMBeanProxy(UIProfileManager.class, "merp:service=UIProfileManager"); //$NON-NLS-1$
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
    }

}
