/**
 * DataBusinessServiceInterceptorManagerLocator.java
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
import com.mg.framework.api.DataBusinessServiceInterceptorManager;
import com.mg.framework.service.jboss.DataBusinessServiceInterceptorManagerServiceMBean;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор менеджера перехватчиков действий бизнес-компонентов управляющих данными
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessServiceInterceptorManagerLocator.java,v 1.1 2007/12/13 13:06:56 safonov Exp $
 */
public class DataBusinessServiceInterceptorManagerLocator {
    private static volatile DataBusinessServiceInterceptorManager instance = null;
    
    /**
     * получить менеджер
     * 
     * @return	менеджер
     */
    public static DataBusinessServiceInterceptorManager locate() {
        if (instance == null)
            try {
                instance = (DataBusinessServiceInterceptorManager) ServerUtils.createMBeanProxy(DataBusinessServiceInterceptorManager.class, DataBusinessServiceInterceptorManagerServiceMBean.SERVICE_NAME);
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
    }

}
