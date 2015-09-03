/*
 * LockManagerService.java
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

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.LockingException;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.LockManagerImpl;

/**
 * Реализация сервиса менеджера блокировок
 * 
 * @author Oleg V. Safonov
 * @version $Id: LockManagerService.java,v 1.1 2006/12/15 09:30:39 safonov Exp $
 */
public class LockManagerService extends ServiceMBeanSupport implements LockManagerServiceMBean {
	private String configFile;
	private LockManagerImpl delegate;

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#createService()
	 */
	@Override
	protected void createService() throws Exception {
		delegate = new LockManagerImpl(configFile);
		delegate.create();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#destroyService()
	 */
	@Override
	protected void destroyService() throws Exception {
		delegate.destroy();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#startService()
	 */
	@Override
	protected void startService() throws Exception {
		delegate.start();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#stopService()
	 */
	@Override
	protected void stopService() throws Exception {
		delegate.stop();
	}

	/**
	 * @return the configFile
	 */
	public String getConfigFile() {
		return configFile;
	}

	/**
	 * @param configFile the configFile to set
	 */
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LockManager#lock(java.lang.String, java.lang.Object)
	 */
	public void lock(PersistentObject entity) throws LockingException {
		delegate.lock(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LockManager#tryLock(com.mg.framework.api.orm.PersistentObject)
	 */
	public boolean tryLock(PersistentObject entity) {
		return delegate.tryLock(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LockManager#unlock()
	 */
	public void unlock(PersistentObject entity) {
		delegate.unlock(entity);
	}

}
