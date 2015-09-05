/*
 * UserActionInterceptorManagerService.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.UserActionInterceptor;
import com.mg.framework.api.UserActionInterceptorManager;
import com.mg.framework.api.ui.MaintenanceConversationSession;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.service.UserActionInterceptorManagerImpl;

/**
 * Реализация сервиса менеджера перехватчиков действий интерактивной поддержки (добавление, изменение, копирование, просмотр)
 * бизнес-компонентов
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserActionInterceptorManagerService.java,v 1.3 2006/10/26 13:27:52 safonov Exp $
 */
public class UserActionInterceptorManagerService extends ServiceMBeanSupport
        implements UserActionInterceptorManagerServiceMBean {

	private UserActionInterceptorManager delegate = new UserActionInterceptorManagerImpl();

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserActionInterceptorManager#registerInterceptor(com.mg.framework.api.UserActionInterceptor)
	 */
	public void registerInterceptor(UserActionInterceptor interceptor) {
		delegate.registerInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserActionInterceptorManager#unregisterInterceptor(com.mg.framework.api.UserActionInterceptor)
	 */
	public void unregisterInterceptor(UserActionInterceptor interceptor) {
		delegate.unregisterInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserActionInterceptorManager#invokeBeforeOutputInterceptor(com.mg.framework.api.ui.MaintenanceConversationSession)
	 */
	public void invokeBeforeOutputInterceptor(MaintenanceConversationSession dialogSession) {
		delegate.invokeBeforeOutputInterceptor(dialogSession);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.UserActionInterceptorManager#invokeAfterInputInterceptor(com.mg.framework.api.ui.MaintenanceConversationSession, com.mg.framework.api.validator.ValidationContext, boolean, boolean)
	 */
	public void invokeAfterInputInterceptor(MaintenanceConversationSession dialogSession, ValidationContext validationContext, boolean isSaveAction, boolean isCloseAction) {
		delegate.invokeAfterInputInterceptor(dialogSession, validationContext, isSaveAction, isCloseAction);
	}

}
