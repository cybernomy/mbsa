/*
 * SQLExceptionTranslatorServiceMBean.java
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
package com.mg.merp.reference.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * ������ ����������� SQL �� ������ �����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslatorServiceMBean.java,v 1.1 2006/11/17 16:28:19 safonov Exp $
 */
public interface SQLExceptionTranslatorServiceMBean extends ServiceMBean {
	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp:reference=SQLExceptionTranslator";
}
