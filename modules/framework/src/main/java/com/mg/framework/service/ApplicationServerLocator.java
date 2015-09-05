/**
 * ApplicationServerLocator.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
import com.mg.framework.api.ApplicationServer;
import com.mg.framework.service.jboss.ApplicationServerServiceMBean;
import com.mg.framework.utils.ServerUtils;

/**
 * Локатор сервиса управления прикладными функциями сервера приложения
 * 
 * @author Oleg V. Safonov
 * @version $Id: ApplicationServerLocator.java,v 1.1 2008/07/14 14:11:27 safonov Exp $
 */
public class ApplicationServerLocator {
    private static volatile ApplicationServer instance = null;
    
    /**
     * получить сервис
     * 
     * @return	сервис
     * @throws ApplicationException	в случае любой ошибки
     */
    public static ApplicationServer locate() throws ApplicationException {
        if (instance == null)
            try {
                instance = (ApplicationServer) ServerUtils.createMBeanProxy(ApplicationServer.class, ApplicationServerServiceMBean.SERVICE_NAME);
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
    }

}
