/*
 * BusinessAddinEngineImpl.java
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
package com.mg.merp.baiengine.support;

import java.util.List;
import java.util.Map;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.baiengine.BAiImplementationInstantiationException;
import com.mg.merp.baiengine.BAiNotFoundException;
import com.mg.merp.baiengine.BusinessAddin;
import com.mg.merp.baiengine.BusinessAddinEngine;
import com.mg.merp.baiengine.BusinessAddinException;
import com.mg.merp.baiengine.BusinessAddinListener;
import com.mg.merp.baiengine.UnsupportedBAiEngineException;
import com.mg.merp.baiengine.model.Repository;

/**
 * Реализация сервиса машины бизнес расширений системы
 * 
 * @author Oleg V. Safonov
 * @version $Id: BusinessAddinEngineImpl.java,v 1.4 2007/11/15 13:13:09 safonov Exp $
 */
public class BusinessAddinEngineImpl implements BusinessAddinEngine {

	/**
	 * реализация выполнения класса бизнес расширения
	 * 
	 * @param <T>
	 * @param name
	 * @param params
	 * @param listener
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private <T> void doPerform(Repository repository, Map<String, ? extends Object> params,
			BusinessAddinListener<T> listener) throws Exception {
		BusinessAddin<T> bai = doCreateBusinessAddin(repository);
//		switch (repository.getEngine()) {
//		case JAVA_ENGINE:
//			//XXX compiler sun javac 1.5.0_06 cann't compile this code with error message "incompatible types"
//	    	//but eclipse compiler compile successfully
//	        //Class<? extends AbstractBusinessAddin<T>> clazz = ServerUtils.loadClass(name).asSubclass(AbstractBusinessAddin.class);
//			Class<? extends AbstractBusinessAddin> clazz = ServerUtils.loadClass(repository.getJavaClassName()).asSubclass(AbstractBusinessAddin.class);
//	        bai = clazz.newInstance();
//	        break;
//		default:
//			throw new ApplicationException("Не поддерживается");
//		}
        bai.registerListener(listener);
        bai.perform(params);
	}
	
	private Repository loadRepositoryByCode(String code) {
		List<Repository> r = OrmTemplate.getInstance().findByCriteria(Repository.class, Restrictions.eq("Code", code));
		if (r.isEmpty())
			throw new BAiNotFoundException(code);
		return r.get(0);
	}
	
	@SuppressWarnings("unchecked")
	private <T> BusinessAddin<T> doCreateBusinessAddin(Repository repository) {
		BusinessAddin<T> bai = null;
		switch (repository.getEngine()) {
		case JAVA_ENGINE:
			//XXX compiler sun javac 1.5.0_06 cann't compile this code with error message "incompatible types"
	    	//but eclipse compiler compile successfully
	        //Class<? extends AbstractBusinessAddin<T>> clazz = ServerUtils.loadClass(name).asSubclass(AbstractBusinessAddin.class);
			try {
				Class<? extends BusinessAddin> clazz = ServerUtils.loadClass(repository.getImplementationName()).asSubclass(BusinessAddin.class);
		        bai = clazz.newInstance();
			} catch (ClassNotFoundException e) {
				throw new BAiImplementationInstantiationException(e);
			} catch (InstantiationException e) {
				throw new BAiImplementationInstantiationException(e);
			} catch (IllegalAccessException e) {
				throw new BAiImplementationInstantiationException(e);
			}
	        break;
		default:
			throw new UnsupportedBAiEngineException(repository.getEngine());
		}
		return bai;
	}
	
	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.BusinessAddinEngine#perform(java.lang.String, java.util.Map, com.mg.merp.baiengine.BusinessAddinListener)
	 */
	public <T> void perform(String code, Map<String, ? extends Object> params,
			BusinessAddinListener<T> listener) throws BusinessException {
		try {
			doPerform(loadRepositoryByCode(code), params, listener);
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			throw new BusinessAddinException(Messages.getInstance().getMessage(Messages.BAI_PERFORM_ERROR), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.BusinessAddinEngine#perform(com.mg.merp.baiengine.model.Repository, java.util.Map, com.mg.merp.baiengine.BusinessAddinListener)
	 */
	public <T> void perform(PersistentObject repository, Map<String, ? extends Object> params, BusinessAddinListener<T> listener) throws BusinessException {
		try {
			doPerform((Repository) repository, params, listener);
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			throw new BusinessAddinException(Messages.getInstance().getMessage(Messages.BAI_PERFORM_ERROR), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.BusinessAddinEngine#createBusinessAddin(com.mg.merp.baiengine.model.Repository, java.util.Map, com.mg.merp.baiengine.BusinessAddinListener)
	 */
	public <T> BusinessAddin<T> createBusinessAddin(PersistentObject repository) {
		return doCreateBusinessAddin((Repository) repository);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.baiengine.BusinessAddinEngine#createBusinessAddin(java.lang.String, java.util.Map, com.mg.merp.baiengine.BusinessAddinListener)
	 */
	public <T> BusinessAddin<T> createBusinessAddin(String code) {
		return doCreateBusinessAddin(loadRepositoryByCode(code));
	}

}
