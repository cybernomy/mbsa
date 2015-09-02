/*
 * LockManagerImpl.java
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
package com.mg.framework.service;

import org.jboss.cache.CacheException;
import org.jboss.cache.Fqn;
import org.jboss.cache.PropertyConfigurator;
import org.jboss.cache.TreeCache;
import org.jboss.cache.lock.TimeoutException;

import com.mg.framework.api.InternalException;
import com.mg.framework.api.LockManager;
import com.mg.framework.api.LockingException;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация менеджера управления блокировками объектов
 * 
 * @author Oleg V. Safonov
 * @version $Id: LockManagerImpl.java,v 1.1 2006/12/15 09:28:45 safonov Exp $
 */
public class LockManagerImpl implements LockManager {
	private static final String ENTITY = "entity";
	private TreeCache treeCache;
	private String configFile;

	public LockManagerImpl(String configFile) {
		this.configFile = configFile;
	}
	
	public void create() throws Exception {
		treeCache = new TreeCache();
		
		if (configFile == null)
			configFile = ServerUtils.MBSA_CUSTOM_LOCATION.concat("/conf/lock-manager-config.xml");
		PropertyConfigurator config = new PropertyConfigurator();
		config.configure(treeCache, configFile);
		treeCache.setLockAcquisitionTimeout(1); //without timeout
	}

	public void destroy() throws Exception {
		treeCache.destroy();
	}
	
	public void start() throws Exception {
		treeCache.start();
	}
	
	public void stop() throws Exception {
		treeCache.stop();
	}
	
	private static Fqn fqnFromEntity(PersistentObject entity) {
		return new Fqn(Fqn.fromString(entity.getEntityName().replace('.', '/')), entity.getPrimaryKey());
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.LockManager#lock(java.lang.String, java.lang.Object)
	 */
	public void lock(PersistentObject entity) {
		try {
			treeCache.put(fqnFromEntity(entity), ENTITY, entity);
		} catch (org.jboss.cache.lock.LockingException e) {
			throw new LockingException(e);
		} catch (TimeoutException e) {
			throw new LockingException(e);
		} catch (CacheException e) {
			throw new InternalException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LockManager#tryLock(com.mg.framework.api.orm.PersistentObject)
	 */
	public boolean tryLock(PersistentObject entity) {
		try {
			treeCache.put(fqnFromEntity(entity), ENTITY, entity);
			return true;
		} catch (org.jboss.cache.lock.LockingException e) {
			return false;
		} catch (TimeoutException e) {
			return false;
		} catch (CacheException e) {
			throw new InternalException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.LockManager#unlock()
	 */
	public void unlock(PersistentObject entity) {
		try {
			treeCache.remove(fqnFromEntity(entity));
		} catch (CacheException e) {
			throw new InternalException(e);
		}
	}

}
