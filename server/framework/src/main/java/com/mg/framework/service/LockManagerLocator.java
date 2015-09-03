/*
 * LockManagerLocator.java
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
import com.mg.framework.api.LockManager;
import com.mg.framework.service.jboss.LockManagerServiceMBean;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор сервиса менеджера блокировок
 * 
 * @author Oleg V. Safonov
 * @version $Id: LockManagerLocator.java,v 1.1 2006/12/15 09:28:45 safonov Exp $
 */
public class LockManagerLocator {
    private static volatile LockManager instance = null;
    
    /**
     * получить менеджер
     * 
     * @return	менеджер
     */
    public static LockManager locate() {
        if (instance == null)
            try {
                instance = (LockManager) ServerUtils.createMBeanProxy(LockManager.class, LockManagerServiceMBean.SERVICE_NAME);
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
    }

}
