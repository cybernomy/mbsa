/*
 * ApplicationDictionaryLocator.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: ApplicationDictionaryLocator.java,v 1.1 2006/01/24 13:58:46 safonov Exp $
 */
public class ApplicationDictionaryLocator {
    private static volatile ApplicationDictionary instance = null;
    
    public static ApplicationDictionary locate() throws ApplicationException {
        if (instance == null)
            try {
                instance = (ApplicationDictionary) ServerUtils.createMBeanProxy(ApplicationDictionary.class, "merp:service=ApplicationDictionaryService");
            }
            catch (Exception e) {
                throw new ApplicationException(e);
            }
        return instance;
    }
}
