/*
 * AbstractSessionBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.framework.generic;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Remove;
import javax.ejb.SessionContext;

/**
 * Базовая реализация сессионных EJB
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractSessionBean.java,v 1.6 2007/09/07 12:33:50 safonov Exp $
 */
public abstract class AbstractSessionBean
	extends AbstractEnterpriseBean {

	@Resource
	private SessionContext sessionContext;
	
	protected final SessionContext getSessionContext() {
		return sessionContext;
	}

	@PostConstruct
	public void postConstructCallback() {
		try {
			getLogger().debug("postConstructCallback");
			doPostConstruct();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PreDestroy
	public void preDestroyCallback() {
		try {
			getLogger().debug("preDestroyCallback");
			doPreDestroy();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.BusinessObjectService#destroy()
	 */
	@Remove
	@PermitAll
	public void destroy() {
		try {
			getLogger().debug("destoy EJB");
			doDestroy();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
