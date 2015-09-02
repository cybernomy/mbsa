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
 * �������� ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: LicenseController.java,v 1.3 2007/04/13 14:14:48 safonov Exp $
 */
public interface LicenseController {
	
	/**
	 * ������ ��������
	 * 
	 * @param session	������
	 */
    void occupyLicense(Session session);
    
    /**
     * ������������ ��������
     * 
     * @param session	������
     */
    void freeLicense(Session session);
    
    /**
     * ��������� ������������ ��������
     * 
     * @param action	��������
     * @return	��������� ��������
     */
    <T> T doAsLicensed(LicensedAction<T> action);
    
}
