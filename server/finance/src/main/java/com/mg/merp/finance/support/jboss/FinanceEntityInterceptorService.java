/*
 * FinanceEntityInterceptorService.java
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
package com.mg.merp.finance.support.jboss;

import org.jboss.annotation.ejb.Depends;
import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.service.EntityInterceptorManagerLocator;
import com.mg.framework.service.jboss.EntityInterceptorManagerServiceMBean;
import com.mg.merp.finance.support.FinOperEntityInterceptorImpl;
import com.mg.merp.finance.support.FinSpecEntityInterceptorImpl;

/**
 * Реализация сервиса регистрации перхватчиков моделей подсистемы "Финансы"
 * 
 * @author Oleg V. Safonov
 * @version $Id: FinanceEntityInterceptorService.java,v 1.1 2007/01/16 14:44:05 safonov Exp $
 */
@Service(objectName=FinanceEntityInterceptorServiceMBean.SERVICE_NAME, name="merp/finance/FinanceEntityInterceptorService")
@Management(FinanceEntityInterceptorServiceMBean.class)
@Depends(EntityInterceptorManagerServiceMBean.SERVICE_NAME)
public class FinanceEntityInterceptorService extends ServiceMBeanSupport
		implements FinanceEntityInterceptorServiceMBean {
	private EntityInterceptor finOperInterceptor = null;
	private EntityInterceptor finSpecInterceptor = null;
	
	@Override
	protected void createService() throws Exception {
		finOperInterceptor = new FinOperEntityInterceptorImpl();
		finSpecInterceptor = new FinSpecEntityInterceptorImpl();
	}

	@Override
	protected void startService() throws Exception {
		EntityInterceptorManagerLocator.locate().registerPostUpdateInterceptor(finOperInterceptor);
		EntityInterceptorManagerLocator.locate().registerPostPersistInterceptor(finSpecInterceptor);
		EntityInterceptorManagerLocator.locate().registerPostUpdateInterceptor(finSpecInterceptor);
		EntityInterceptorManagerLocator.locate().registerPostRemoveInterceptor(finSpecInterceptor);
	}
	
	@Override
	protected void stopService() throws Exception {
		EntityInterceptorManagerLocator.locate().unregisterPostUpdateInterceptor(finOperInterceptor);
		EntityInterceptorManagerLocator.locate().unregisterPostPersistInterceptor(finSpecInterceptor);
		EntityInterceptorManagerLocator.locate().unregisterPostUpdateInterceptor(finSpecInterceptor);
		EntityInterceptorManagerLocator.locate().unregisterPostRemoveInterceptor(finSpecInterceptor);
	}
	
	@Override
	protected void destroyService() throws Exception {
		finOperInterceptor = null;
		finSpecInterceptor = null;
	}

}
