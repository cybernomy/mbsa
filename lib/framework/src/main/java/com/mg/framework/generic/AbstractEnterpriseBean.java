/*
 * AbstractEnterpriseBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

import javax.ejb.EnterpriseBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mg.framework.utils.ServerUtils;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractEnterpriseBean.java,v 1.3 2006/03/01 15:26:36 safonov Exp $
 */
public abstract class AbstractEnterpriseBean implements EnterpriseBean {
	private transient com.mg.framework.api.Logger logger = null;
	private transient InitialContext jndiCtx;

	protected com.mg.framework.api.Logger getLogger() {
		if (logger == null)
			logger = ServerUtils.getLogger(getClass().getName());
		return logger;
	}

	protected InitialContext getInitialContext() throws NamingException {
		if (jndiCtx == null)
			jndiCtx = new InitialContext();
		return jndiCtx;
	}
	
	protected void doPostConstruct() {
		
	}

	protected void doPreDestroy() {
		if (jndiCtx != null) {
			try {
				jndiCtx.close();
				jndiCtx = null;				
			}
			catch (NamingException e) {
			}
		}
		logger = null;
	}

	protected void doDestroy() {
		/*if (jndiCtx != null) {
			try {
				jndiCtx.close();
				jndiCtx = null;				
			}
			catch (NamingException e) {
			}
		}
		logger = null;*/
	}
}
