/**
 * DataBusinessServiceInterceptorManagerService.java
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
package com.mg.framework.service.jboss;

import java.io.Serializable;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.DataBusinessServiceInterceptor;
import com.mg.framework.api.DataBusinessServiceInterceptorManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.service.DataBusinessServiceInterceptorManagerImpl;

/**
 * Реализация сервиса менеджера перехватчиков действий бизнес-компонентов управляющих данными
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessServiceInterceptorManagerService.java,v 1.1 2007/12/13 13:07:20 safonov Exp $
 */
public class DataBusinessServiceInterceptorManagerService extends
		ServiceMBeanSupport implements
		DataBusinessServiceInterceptorManagerServiceMBean {
	private DataBusinessServiceInterceptorManager delegate = null;

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnClone(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnClone(DataBusinessObjectService<T, ID> service, T entity) {
		return delegate.invokeOnClone(service, entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnCreate(DataBusinessObjectService<T, ID> service, T entity) {
		return delegate.invokeOnCreate(service, entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnErase(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnErase(DataBusinessObjectService<T, ID> service, T entity) {
		return delegate.invokeOnErase(service, entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnInitialize(DataBusinessObjectService<T, ID> service, T entity) {
		return delegate.invokeOnInitialize(service, entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnPostCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnPostCreate(DataBusinessObjectService<T, ID> service, T entity) {
		return delegate.invokeOnPostCreate(service, entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnStore(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnStore(DataBusinessObjectService<T, ID> service, T entity) {
		return delegate.invokeOnStore(service, entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnValidate(DataBusinessObjectService<T, ID> service, ValidationContext context,
			T entity) {
		return delegate.invokeOnValidate(service, context, entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#registerInterceptor(com.mg.framework.api.DataBusinessServiceInterceptor)
	 */
	public void registerInterceptor(DataBusinessServiceInterceptor interceptor) {
		delegate.registerInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#unregisterInterceptor(com.mg.framework.api.DataBusinessServiceInterceptor)
	 */
	public void unregisterInterceptor(DataBusinessServiceInterceptor interceptor) {
		delegate.unregisterInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#createService()
	 */
	@Override
	protected void createService() throws Exception {
		delegate = new DataBusinessServiceInterceptorManagerImpl();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#destroyService()
	 */
	@Override
	protected void destroyService() throws Exception {
		delegate = null;
	}

}
