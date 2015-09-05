/*
 * CustomFieldsManagerService.java
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
package com.mg.merp.core.support.jboss;

import java.util.Map;

import org.jboss.annotation.ejb.Management;
import org.jboss.annotation.ejb.Service;
import org.jboss.system.ServiceMBeanSupport;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.metadata.EntityCustomFieldsStorage;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.ServerUtils;

/**
 * Реализация сервиса менеджера управления пользовательскими полями
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomFieldsManagerService.java,v 1.3 2008/12/23 09:43:37 safonov Exp $
 */
@Service(objectName=CustomFieldsManagerServiceMBean.SERVICE_NAME, name="merp/core/CustomFieldsManagerService")
@Management(CustomFieldsManagerServiceMBean.class)
public class CustomFieldsManagerService extends ServiceMBeanSupport implements
		CustomFieldsManagerServiceMBean {
	private CustomFieldsManager delegate = null;

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#createService()
	 */
	@Override
	protected void createService() throws Exception {
		delegate = (CustomFieldsManager) ServerUtils.loadClass("com.mg.merp.core.support.CustomFieldsManagerImpl").newInstance();
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#destroyService()
	 */
	@Override
	protected void destroyService() throws Exception {
		delegate = null;
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#startService()
	 */
	@Override
	protected void startService() throws Exception {
	}

	/* (non-Javadoc)
	 * @see org.jboss.system.ServiceMBeanSupport#stopService()
	 */
	@Override
	protected void stopService() throws Exception {
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#loadFieldMetadatas(com.mg.framework.api.DataBusinessObjectService)
	 */
	public FieldMetadata[] loadFieldsMetadata(DataBusinessObjectService<?, ?> service) {
		return delegate.loadFieldsMetadata(service);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#generateMaintenanceArea(com.mg.framework.api.DataBusinessObjectService)
	 */
	public String generateMaintenanceArea(DataBusinessObjectService<?, ?> service) {
		return delegate.generateMaintenanceArea(service);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#loadValues(com.mg.framework.api.DataBusinessObjectService, java.lang.Object)
	 */
	public Map<String, Object> loadValues(DataBusinessObjectService<?, ?> service, Object key) {
		return delegate.loadValues(service, key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#storeValues(java.util.Map, com.mg.framework.api.DataBusinessObjectService, java.lang.Object)
	 */
	public void storeValues(Map<String, Object> fieldsValues, DataBusinessObjectService<?, ?> service, Object key) {
		delegate.storeValues(fieldsValues, service, key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#createStorage(com.mg.framework.api.DataBusinessObjectService, java.lang.Object)
	 */
	public EntityCustomFieldsStorage createStorage(DataBusinessObjectService<?, ?> service, Object key) {
		return delegate.createStorage(service, key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#storeValues(com.mg.framework.api.metadata.EntityCustomFieldsStorage, com.mg.framework.api.DataBusinessObjectService, java.lang.Object)
	 */
	public void storeValues(EntityCustomFieldsStorage storage, DataBusinessObjectService<?, ?> service, Object key) {
		delegate.storeValues(storage, service, key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#removeValues(com.mg.framework.api.DataBusinessObjectService, java.lang.Object)
	 */
	public void removeValues(DataBusinessObjectService<?, ?> service, Object key) {
		delegate.removeValues(service, key);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.CustomFieldsManager#cloneStorage(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject, com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject)
	 */
	public void cloneValues(DataBusinessObjectService<?, ?> serviceSrc, PersistentObject entitySrc, DataBusinessObjectService<?, ?> serviceDst, PersistentObject entityDst) {
		delegate.cloneValues(serviceSrc, entitySrc, serviceDst, entityDst);
	}

}
