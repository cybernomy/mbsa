/*
 * TreePermissionInterceptorServiceMBean.java
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
package com.mg.merp.personnelref.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * ������ ������������ ��� ���������� ���� �� �������� �������� ��� ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: TreePermissionInterceptorServiceMBean.java,v 1.1 2006/12/29 07:43:06 safonov Exp $
 */
public interface TreePermissionInterceptorServiceMBean extends ServiceMBean {
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp:personnelref=TreePermissionInterceptorService";
}
