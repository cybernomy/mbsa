/*
 * BusinessAddinEngineService.java
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
package com.mg.merp.baiengine.support.jboss;

import java.util.Map;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.merp.baiengine.BusinessAddin;
import com.mg.merp.baiengine.BusinessAddinEngine;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.baiengine.support.BusinessAddinEngineImpl;

/**
 * Реализация сервиса машины бизнес расширений системы для JBoss
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddinEngineService.java,v 1.5 2007/11/15 13:13:09 safonov Exp $
 */
public class BusinessAddinEngineService extends ServiceMBeanSupport implements
		BusinessAddinEngineServiceMBean {
	private BusinessAddinEngine delegate = new BusinessAddinEngineImpl();

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.BusinessAddinEngine#perform(java.lang.String, java.util.Map, com.mg.merp.baiengine.BusinessAddinListener)
	 */
	public <T> void perform(String code, Map<String, ? extends Object> params, BusinessAddinListener<T> listener) throws BusinessException {
		delegate.perform(code, params, listener);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.BusinessAddinEngine#createBusinessAddin(com.mg.merp.baiengine.model.Repository, java.util.Map, com.mg.merp.baiengine.BusinessAddinListener)
	 */
	public <T> BusinessAddin<T> createBusinessAddin(PersistentObject repository) {
		return delegate.createBusinessAddin(repository);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.BusinessAddinEngine#createBusinessAddin(java.lang.String, java.util.Map, com.mg.merp.baiengine.BusinessAddinListener)
	 */
	public <T> BusinessAddin<T> createBusinessAddin(String code) {
		return delegate.createBusinessAddin(code);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.BusinessAddinEngine#perform(com.mg.merp.baiengine.model.Repository, java.util.Map, com.mg.merp.baiengine.BusinessAddinListener)
	 */
	public <T> void perform(PersistentObject repository, Map<String, ? extends Object> params, BusinessAddinListener<T> listener) throws BusinessException {
		delegate.perform(repository, params, listener);
	}

}
