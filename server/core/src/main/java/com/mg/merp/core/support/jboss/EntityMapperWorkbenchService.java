/* EntityMapperWorkbenchService.java
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
package com.mg.merp.core.support.jboss;

import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

import com.mg.merp.core.model.EntityTransformerMapping;
import com.mg.merp.core.support.EntityMapperWorkbenchImpl;

/**
 * @author Valentin A. Poroxnenko
 * @version $Id: EntityMapperWorkbenchService.java,v 1.1 2007/05/10 05:41:12 poroxnenko Exp $
 */
@Service(objectName=EntityMapperWorkbenchServiceMBean.SERVICE_NAME, name="merp/core/EntityMapperWorkbenchService")
@Management(EntityMapperWorkbenchServiceMBean.class)
public class EntityMapperWorkbenchService extends ServiceMBeanSupport
		implements EntityMapperWorkbenchServiceMBean {

	private EntityMapperWorkbenchImpl delegate = new EntityMapperWorkbenchImpl();

	public EntityTransformerMapping[] getEntityMappers(String query) throws Exception {
		return delegate.getEntityMappers(query);
	}

	public EntityTransformerMapping addEntityMapper(EntityTransformerMapping mapping) throws Exception {
		return delegate.addEntityMapper(mapping);
	}

	public void deleteEntityMappersList(Integer[] ids) throws Exception {
		delegate.deleteEntityMappersList(ids);
	}

	public EntityTransformerMapping editEntityMapper(EntityTransformerMapping mapping) throws Exception {
		return delegate.editEntityMapper(mapping);
	}

}
