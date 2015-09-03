/*
 * LicenseController.java
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
package com.mg.framework.api.security;

import com.mg.framework.api.Session;

/**
 * Менеджер лицензий
 * 
 * @author Oleg V. Safonov
 * @version $Id: LicenseController.java,v 1.3 2007/04/13 14:14:48 safonov Exp $
 */
public interface LicenseController {
	
	/**
	 * запрос лицензий
	 * 
	 * @param session	сессия
	 */
    void occupyLicense(Session session);
    
    /**
     * освобождение лицензий
     * 
     * @param session	сессия
     */
    void freeLicense(Session session);
    
    /**
     * выполнить лицензионное действие
     * 
     * @param action	действие
     * @return	результат действия
     */
    <T> T doAsLicensed(LicensedAction<T> action);
    
}
