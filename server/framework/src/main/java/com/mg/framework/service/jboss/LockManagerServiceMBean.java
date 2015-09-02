/*
 * LockManagerServiceMBean.java
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
package com.mg.framework.service.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.framework.api.LockManager;

/**
 * ������ ��������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: LockManagerServiceMBean.java,v 1.1 2006/12/15 09:30:39 safonov Exp $
 */
public interface LockManagerServiceMBean extends ServiceMBean,
		LockManager {
	/**
	 * ������������ �������
	 */
	final static String SERVICE_NAME = "merp:service=LockManagerService";

	/**
	 * �������� ������� configFile
	 * 
	 * @return	������ ���� � ����� ������������
	 */
	public String getConfigFile();
	
	/**
	 * ���������� ������� configFile
	 * 
	 * @param configFile	������ ���� � ����� ������������
	 */
	public void setConfigFile(String configFile);

}
