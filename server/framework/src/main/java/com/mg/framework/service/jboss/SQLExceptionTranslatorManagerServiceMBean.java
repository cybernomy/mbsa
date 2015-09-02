/*
 * SQLExceptionTranslatorManagerServiceMBean.java
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

import com.mg.framework.api.jdbc.SQLExceptionTranslatorManager;

/**
 * ������ ��������� ������������ SQL �� � ���������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslatorManagerServiceMBean.java,v 1.1 2006/11/17 14:31:44 safonov Exp $
 */
public interface SQLExceptionTranslatorManagerServiceMBean extends
		SQLExceptionTranslatorManager, ServiceMBean {
	/**
	 * ������������ �������
	 */
	final static String SERVICE_NAME = "merp:service=SQLExceptionTranslatorManagerService";

}
