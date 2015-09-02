/**
 * TaskInterceptorService.java
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
package com.mg.merp.scheduler.support.jboss;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.service.EntityInterceptorManagerLocator;
import com.mg.merp.scheduler.support.TaskInterceptorImpl;

/**
 * Реализация JMX сервиса регистрация перехватчиков на сущность "задачи планировщика"
 * 
 * @author Oleg V. Safonov
 * @version $Id: TaskInterceptorService.java,v 1.1 2008/04/25 10:57:23 safonov Exp $
 */
public class TaskInterceptorService extends ServiceMBeanSupport implements
		TaskInterceptorServiceMBean {
	private EntityInterceptor interceptor = null;

	@Override
	protected void createService() throws Exception {
		interceptor = new TaskInterceptorImpl();
	}
	
	@Override
	protected void startService() throws Exception {
		EntityInterceptorManagerLocator.locate().registerPostCommitPersistInterceptor(interceptor);
		EntityInterceptorManagerLocator.locate().registerPostCommitUpdateInterceptor(interceptor);
		EntityInterceptorManagerLocator.locate().registerPostCommitRemoveInterceptor(interceptor);
	}
	
	@Override
	protected void stopService() throws Exception {
		EntityInterceptorManagerLocator.locate().unregisterPostCommitPersistInterceptor(interceptor);
		EntityInterceptorManagerLocator.locate().unregisterPostCommitUpdateInterceptor(interceptor);
		EntityInterceptorManagerLocator.locate().unregisterPostCommitRemoveInterceptor(interceptor);
	}
	
	@Override
	protected void destroyService() throws Exception {
		interceptor = null;
	}

}
