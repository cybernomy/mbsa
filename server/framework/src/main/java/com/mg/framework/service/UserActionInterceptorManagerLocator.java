/*
 * UserActionInterceptorManagerLocator.java
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
package com.mg.framework.service;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.UserActionInterceptorManager;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор менеджера перехватчиков интерактивных действий сервиса бизнес-компонентов
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptorManagerLocator.java,v 1.3 2006/10/26 13:12:02 safonov Exp $
 */
public class UserActionInterceptorManagerLocator {
    private static volatile UserActionInterceptorManager instance = null;
    
    public static UserActionInterceptorManager locate() throws ApplicationException {
        if (instance == null)
            try {
                instance = (UserActionInterceptorManager) ServerUtils.createMBeanProxy(UserActionInterceptorManager.class, "merp:service=UserActionInterceptorManagerService"); //$NON-NLS-1$
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
    }
}
