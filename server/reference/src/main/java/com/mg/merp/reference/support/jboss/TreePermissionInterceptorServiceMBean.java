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
package com.mg.merp.reference.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис перехватчика для добавления прав на элементы иерархии при создании
 * 
 * @author Oleg V. Safonov
 * @version $Id: TreePermissionInterceptorServiceMBean.java,v 1.2 2006/12/29 07:41:34 safonov Exp $
 */
public interface TreePermissionInterceptorServiceMBean extends ServiceMBean {
	final static String SERVICE_NAME = "merp:reference=TreePermissionInterceptorService";
}
