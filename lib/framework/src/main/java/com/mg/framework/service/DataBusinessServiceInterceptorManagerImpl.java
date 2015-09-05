/**
 * DataBusinessServiceInterceptorManagerImpl.java
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
package com.mg.framework.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.DataBusinessServiceInterceptor;
import com.mg.framework.api.DataBusinessServiceInterceptorManager;
import com.mg.framework.api.Logger;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация менеджера перехватчиков действий бизнес-компонентов управляющих данными
 * 
 * @author Oleg V. Safonov
 * @version $Id: DataBusinessServiceInterceptorManagerImpl.java,v 1.1 2007/12/13 13:06:56 safonov Exp $
 */
public class DataBusinessServiceInterceptorManagerImpl implements
		DataBusinessServiceInterceptorManager {
	private Logger log = ServerUtils.getLogger(DataBusinessServiceInterceptorManagerImpl.class);
	private Map<String, List<DataBusinessServiceInterceptor>> interceptors = null;
	private ReadWriteLock interceptorsLock = null;

	public DataBusinessServiceInterceptorManagerImpl() {
		interceptorsLock = new ReentrantReadWriteLock();
		interceptors = new HashMap<String, List<DataBusinessServiceInterceptor>>();
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnClone(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnClone(DataBusinessObjectService<T, ID> service, T entity) {
		if (log.isDebugEnabled())
			log.debug(String.format("invoke onClone interceptors for service: %s", service.getBusinessServiceMetadata().getName()));
		
		boolean result = false;
		Lock lock = interceptorsLock.readLock();
		lock.lock();
		try {
			List<DataBusinessServiceInterceptor> list = interceptors.get(service.getBusinessServiceMetadata().getName().toUpperCase());
			if (list != null)
				for (DataBusinessServiceInterceptor interceptor : list)
					result = result || interceptor.onClone(service, entity);
		} finally {
			lock.unlock();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnCreate(DataBusinessObjectService<T, ID> service, T entity) {
		if (log.isDebugEnabled())
			log.debug(String.format("invoke onCreate interceptors for service: %s", service.getBusinessServiceMetadata().getName()));
		
		boolean result = false;
		Lock lock = interceptorsLock.readLock();
		lock.lock();
		try {
			List<DataBusinessServiceInterceptor> list = interceptors.get(service.getBusinessServiceMetadata().getName().toUpperCase());
			if (list != null)
				for (DataBusinessServiceInterceptor interceptor : list)
					result = result || interceptor.onCreate(service, entity);
		} finally {
			lock.unlock();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnErase(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnErase(DataBusinessObjectService<T, ID> service, T entity) {
		if (log.isDebugEnabled())
			log.debug(String.format("invoke onErase interceptors for service: %s", service.getBusinessServiceMetadata().getName()));
		
		boolean result = false;
		Lock lock = interceptorsLock.readLock();
		lock.lock();
		try {
			List<DataBusinessServiceInterceptor> list = interceptors.get(service.getBusinessServiceMetadata().getName().toUpperCase());
			if (list != null)
				for (DataBusinessServiceInterceptor interceptor : list)
					result = result || interceptor.onErase(service, entity);
		} finally {
			lock.unlock();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnInitialize(DataBusinessObjectService<T, ID> service, T entity) {
		if (log.isDebugEnabled())
			log.debug(String.format("invoke onInitialize interceptors for service: %s", service.getBusinessServiceMetadata().getName()));
		
		boolean result = false;
		Lock lock = interceptorsLock.readLock();
		lock.lock();
		try {
			List<DataBusinessServiceInterceptor> list = interceptors.get(service.getBusinessServiceMetadata().getName().toUpperCase());
			if (list != null)
				for (DataBusinessServiceInterceptor interceptor : list)
					result = result || interceptor.onInitialize(service, entity);
		} finally {
			lock.unlock();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnPostCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnPostCreate(DataBusinessObjectService<T, ID> service, T entity) {
		if (log.isDebugEnabled())
			log.debug(String.format("invoke onPostCreate interceptors for service: %s", service.getBusinessServiceMetadata().getName()));
		
		boolean result = false;
		Lock lock = interceptorsLock.readLock();
		lock.lock();
		try {
			List<DataBusinessServiceInterceptor> list = interceptors.get(service.getBusinessServiceMetadata().getName().toUpperCase());
			if (list != null)
				for (DataBusinessServiceInterceptor interceptor : list)
					result = result || interceptor.onPostCreate(service, entity);
		} finally {
			lock.unlock();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnStore(com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnStore(DataBusinessObjectService<T, ID> service, T entity) {
		if (log.isDebugEnabled())
			log.debug(String.format("invoke onStore interceptors for service: %s", service.getBusinessServiceMetadata().getName()));
		
		boolean result = false;
		Lock lock = interceptorsLock.readLock();
		lock.lock();
		try {
			List<DataBusinessServiceInterceptor> list = interceptors.get(service.getBusinessServiceMetadata().getName().toUpperCase());
			if (list != null)
				for (DataBusinessServiceInterceptor interceptor : list)
					result = result || interceptor.onStore(service, entity);
		} finally {
			lock.unlock();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#invokeOnValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	public <T extends PersistentObject, ID extends Serializable> boolean invokeOnValidate(DataBusinessObjectService<T, ID> service, ValidationContext context,
			T entity) {
		if (log.isDebugEnabled())
			log.debug(String.format("invoke onValidate interceptors for service: %s", service.getBusinessServiceMetadata().getName()));
		
		boolean result = false;
		Lock lock = interceptorsLock.readLock();
		lock.lock();
		try {
			List<DataBusinessServiceInterceptor> list = interceptors.get(service.getBusinessServiceMetadata().getName().toUpperCase());
			if (list != null)
				for (DataBusinessServiceInterceptor interceptor : list)
					result = result || interceptor.onValidate(service, context, entity);
		} finally {
			lock.unlock();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#registerInterceptor(com.mg.framework.api.DataBusinessServiceInterceptor)
	 */
	public void registerInterceptor(DataBusinessServiceInterceptor interceptor) {
		if (interceptor == null)
			return;
		
		String[] serviceNames = interceptor.getHandledServices();
		if (serviceNames == null) {
			log.debug(String.format("Try register interceptor with empty services list", interceptor.getName()));
			return;
		}
		
		Lock lock = interceptorsLock.writeLock();
		lock.lock();
		try {
			for (String serviceName : serviceNames) {
				List<DataBusinessServiceInterceptor> list = interceptors.get(serviceName);
				if (list == null) {
					list = new ArrayList<DataBusinessServiceInterceptor>();
					interceptors.put(serviceName.toUpperCase(), list);
				}
				log.debug(String.format("Register interceptor '%s' for service '%s'", interceptor.getName(), serviceName));
				list.add(interceptor);
			}
		} finally {
			lock.unlock();
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessServiceInterceptorManager#unregisterInterceptor(com.mg.framework.api.DataBusinessServiceInterceptor)
	 */
	public void unregisterInterceptor(DataBusinessServiceInterceptor interceptor) {
		if (interceptor == null)
			return;
		
		String[] serviceNames = interceptor.getHandledServices();
		if (serviceNames == null) {
			log.debug(String.format("Try unregister interceptor with empty services list", interceptor.getName()));
			return;
		}
		
		Lock lock = interceptorsLock.writeLock();
		lock.lock();
		try {
			for (String serviceName : serviceNames) {
				List<DataBusinessServiceInterceptor> list = interceptors.get(serviceName);
				if (list != null) {
					log.debug(String.format("Unregister interceptor '%s' for service '%s'", interceptor.getName(), serviceName));
					list.remove(interceptor);
				}
			}
		} finally {
			lock.unlock();
		}
	}

}
