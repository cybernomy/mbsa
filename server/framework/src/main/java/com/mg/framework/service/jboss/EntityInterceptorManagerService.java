/*
 * EntityInterceptorManagerService.java
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

import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.EntityInterceptor;
import com.mg.framework.api.EntityInterceptorManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.service.EntityInterceptorManagerImpl;

/**
 * Реализация сервиса менеджера перехватчиков действий над объектами сущностями
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityInterceptorManagerService.java,v 1.4 2007/12/17 09:12:07 safonov Exp $
 */
public class EntityInterceptorManagerService extends ServiceMBeanSupport
        implements EntityInterceptorManagerServiceMBean {
    private EntityInterceptorManager delegate = new EntityInterceptorManagerImpl();

    /* (non-Javadoc)
     * @see com.mg.framework.api.EntityInterceptorManager#registerInterceptor(com.mg.framework.api.EntityInterceptor)
     */
    public void registerInterceptor(EntityInterceptor interceptor) {
        this.delegate.registerInterceptor(interceptor);
    }

    /* (non-Javadoc)
     * @see com.mg.framework.api.EntityInterceptorManager#unregisterInterceptor(com.mg.framework.api.EntityInterceptor)
     */
    public void unregisterInterceptor(EntityInterceptor interceptor) {
        this.delegate.unregisterInterceptor(interceptor);
    }

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostInsertInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostPersistInterceptor(PersistentObject entity) {
		this.delegate.invokeOnPostPersistInterceptor(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostLoadInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostLoadInterceptor(PersistentObject entity) {
		this.delegate.invokeOnPostLoadInterceptor(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostRemoveInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostRemoveInterceptor(PersistentObject entity) {
		this.delegate.invokeOnPostRemoveInterceptor(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostUpdateInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostUpdateInterceptor(PersistentObject entity, AttributeMap oldState) {
		this.delegate.invokeOnPostUpdateInterceptor(entity, oldState);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPrePersistInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPrePersistInterceptor(PersistentObject entity) {
		this.delegate.invokeOnPrePersistInterceptor(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPreRemoveInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPreRemoveInterceptor(PersistentObject entity) {
		this.delegate.invokeOnPreRemoveInterceptor(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPreUpdateInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPreUpdateInterceptor(PersistentObject entity, AttributeMap oldState) {
		this.delegate.invokeOnPreUpdateInterceptor(entity, oldState);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostCommitPersistInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostCommitPersistInterceptor(PersistentObject entity) {
		this.delegate.invokeOnPostCommitPersistInterceptor(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostCommitRemoveInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostCommitRemoveInterceptor(PersistentObject entity) {
		this.delegate.invokeOnPostCommitRemoveInterceptor(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#invokeOnPostCommitUpdateInterceptor(com.mg.framework.api.orm.PersistentObject)
	 */
	public void invokeOnPostCommitUpdateInterceptor(PersistentObject entity, AttributeMap oldState) {
		this.delegate.invokeOnPostCommitUpdateInterceptor(entity, oldState);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostCommitPersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostCommitPersistInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPostCommitPersistInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostCommitRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostCommitRemoveInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPostCommitRemoveInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostCommitUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostCommitUpdateInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPostCommitUpdateInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostLoadInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostLoadInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPostLoadInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostPersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostPersistInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPostPersistInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostRemoveInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPostRemoveInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPostUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPostUpdateInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPostUpdateInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPrePersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPrePersistInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPrePersistInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPreRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPreRemoveInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPreRemoveInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#registerPreUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void registerPreUpdateInterceptor(EntityInterceptor interceptor) {
		this.delegate.registerPreUpdateInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostCommitPersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostCommitPersistInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPostCommitPersistInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostCommitRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostCommitRemoveInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPostCommitRemoveInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostCommitUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostCommitUpdateInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPostCommitUpdateInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostLoadInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostLoadInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPostLoadInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostPersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostPersistInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPostPersistInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostRemoveInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPostRemoveInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPostUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPostUpdateInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPostUpdateInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPrePersistInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPrePersistInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPrePersistInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPreRemoveInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPreRemoveInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPreRemoveInterceptor(interceptor);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.EntityInterceptorManager#unregisterPreUpdateInterceptor(com.mg.framework.api.EntityInterceptor)
	 */
	public void unregisterPreUpdateInterceptor(EntityInterceptor interceptor) {
		this.delegate.unregisterPreUpdateInterceptor(interceptor);
	}

}
