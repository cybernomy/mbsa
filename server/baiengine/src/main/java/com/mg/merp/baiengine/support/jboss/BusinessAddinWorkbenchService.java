/* BusinessAddinWorkbenchService.java
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
package com.mg.merp.baiengine.support.jboss;

import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.merp.baiengine.support.BusinessAddinWorkbenchImpl;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: BusinessAddinWorkbenchService.java,v 1.5 2007/05/08 08:46:09 poroxnenko Exp $
 */
public class BusinessAddinWorkbenchService extends ServiceMBeanSupport
		implements BusinessAddinWorkbenchServiceMBean {

	private BusinessAddinWorkbenchImpl delegate = new BusinessAddinWorkbenchImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mg.merp.baiengine.BusinessAddinWorkbench#getBais(java.lang.String)
	 */
	public PersistentObject[] getBais(String query) throws Exception {
		return delegate.getBais(query);
	}

	public PersistentObject addBai(PersistentObject repository) throws Exception {
		return delegate.addBai(repository);
	}

	public void deleteBaiList(Integer[] ids) throws Exception {
		delegate.deleteBaiList(ids);
	}

	public PersistentObject editBai(PersistentObject repository) throws Exception {
		return delegate.editBai(repository);
	}

}
