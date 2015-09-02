/*
 * TreePermissionInterceptorService.java
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

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.service.EntityInterceptorManagerLocator;
import com.mg.framework.service.jboss.EntityInterceptorManagerServiceMBean;
import com.mg.merp.reference.support.TreePermissionInterceptorImpl;

/**
 * Реализация сервиса перехватчика для добавления прав на элементы иерархии при создании
 * 
 * @author Oleg V. Safonov
 * @version $Id: TreePermissionInterceptorService.java,v 1.3 2007/01/12 11:39:50 safonov Exp $
 */
@Service(objectName=TreePermissionInterceptorServiceMBean.SERVICE_NAME, name="merp/reference/TreePermissionInterceptorService")
@Management(TreePermissionInterceptorServiceMBean.class)
@Depends(EntityInterceptorManagerServiceMBean.SERVICE_NAME)
public class TreePermissionInterceptorService extends ServiceMBeanSupport
		implements TreePermissionInterceptorServiceMBean {
	private EntityInterceptor interceptor = null;

	@Override
	protected void createService() throws Exception {
		interceptor = new TreePermissionInterceptorImpl();
	}
	
	@Override
	protected void startService() throws Exception {
		EntityInterceptorManagerLocator.locate().registerPostPersistInterceptor(interceptor);
	}
	
	@Override
	protected void stopService() throws Exception {
		EntityInterceptorManagerLocator.locate().unregisterPostPersistInterceptor(interceptor);
	}
	
	@Override
	protected void destroyService() throws Exception {
		interceptor = null;
	}
}
