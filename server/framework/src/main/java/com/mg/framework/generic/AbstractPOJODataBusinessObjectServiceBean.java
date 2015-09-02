/*
 * AbstractPOJODataBusinessObjectServiceBean.java
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
package com.mg.framework.generic;

import java.io.Serializable;
import java.util.List;

import javax.annotation.security.PermitAll;

import org.hibernate.PersistentObjectException;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.CustomFieldsManager;
import com.mg.framework.api.metadata.EntityCustomFieldsStorageAccessor;
import com.mg.framework.api.orm.Criterion;
import com.mg.framework.api.orm.Example;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.service.CustomFieldsManagerLocator;
import com.mg.framework.service.DataBusinessServiceInterceptorManagerLocator;
import com.mg.framework.support.Messages;
import com.mg.framework.utils.BusinessObjectUtils;
import com.mg.framework.utils.SecurityUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * ����������� ���������� ������-���������� �������������� �������� ���������������� �������������� � ���������� ����������.
 * ��� �������� ������-����������� ���������� ������� ��� <code><T></code> ������� �������� � ��� <code><ID></code> ���������� �����.
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractPOJODataBusinessObjectServiceBean.java,v 1.22 2008/07/02 14:21:27 safonov Exp $
 */
public abstract class AbstractPOJODataBusinessObjectServiceBean<T extends PersistentObject, ID extends Serializable> extends
		AbstractPOJOBusinessObjectStatelessServiceBean implements
		DataBusinessObjectService<T, ID> {
	/**
	 * ��� ������� ��������
	 */
	private Class<T> persistentClass = null;

	/**
	 * ��������� ������� ������������� �������
	 * 
	 * @param entity	������
	 */
	private void fireInitialize(final T entity) {
		if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnInitialize(this, entity))
			onInitialize(entity);
	}
	
	/**
	 * ��������� ������� ���������� � ������� ������������ ����������
	 * 
	 * @param entity	������
	 */
	private void fireCreate(final T entity) {
		if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnCreate(this, entity))
			onCreate(entity);
	}
	
	/**
	 * ��������� ������� ����������� ������� �������� � ������� ������������ ����������
	 * 
	 * @param entity	������ ��������
	 */
	private void fireStore(final T entity) {
		if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnStore(this, entity))
			onStore(entity);
	}
	
	/**
	 * ��������� ������� �������� ������� ��������
	 * 
	 * @param entity	������ ��������
	 */
	private void fireErase(T entity) {
		if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnErase(this, entity))
			onErase(entity);
	}
	
	/**
	 * ���������� �������������
	 * 
	 * @return	������
	 */
	private T internalInitialize() {
		T result = instantiateEntity();
		fireInitialize(result);
		return result;
	}
	
	/**
	 * ���������� ������������� � ��������������� �������
	 * 
	 * @param attributes	�������� ������� �������
	 * @return	������
	 */
	private T internalInitialize(AttributeMap attributes) {
		T result = instantiateEntity();
		result.setAttributes(attributes);
		fireInitialize(result);
		return result;
	}
	
	/**
	 * ���������� ���������� �������
	 * 
	 * @param entity	������
	 * @return	��������� ����
	 */
	@SuppressWarnings("unchecked")
	private ID internalCreate(T entity) {
		if (entity == null)
			return null;

		internalValidate(entity);
		fireCreate(entity);
		try {
			getPersistentManager().persist(entity);
		}
		catch (PersistentObjectException e) {
			getPersistentManager().merge(entity);
		}		
		ID result = (ID) entity.getPrimaryKey();
		if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnPostCreate(this, entity))
			onPostCreate(entity);
		try {
			if (entity instanceof EntityCustomFieldsStorageAccessor)
				CustomFieldsManagerLocator.locate().storeValues(((EntityCustomFieldsStorageAccessor) entity).getStorage(), this, result);
		} catch (Exception e) {
			getLogger().error("Error during custom fields storing", e);
		}
		return result;
	}

	/**
	 * ���������� �����������
	 * 
	 * @param entity	������ ��������
	 * @return	������ �������� ��������� � ����������
	 */
	private T internalStore(T entity) {
		if (entity == null)
			return null;

		internalValidate(entity);
		fireStore(entity);
		T result = getPersistentManager().merge(entity);
		try {
			if (entity instanceof EntityCustomFieldsStorageAccessor) {
				//���������� ������ �������, ���� entity ��� � ������, �� ��� � ������, ���� ���, �� � ����� ������� ��������� ���������
				if (result != entity)
					((EntityCustomFieldsStorageAccessor) result).setStorage(((EntityCustomFieldsStorageAccessor) entity).getStorage());
				CustomFieldsManagerLocator.locate().storeValues(((EntityCustomFieldsStorageAccessor) result).getStorage(), this, result.getPrimaryKey());
			}
		} catch (Exception e) {
			getLogger().error("Error during custom fields storing", e);
		}
		return result;
	}

	/**
	 * ���������� ��������
	 * 
	 * @param entity	������ ��������
	 */
	private void internalErase(T entity) {
		if (entity == null)
			return;
		
		fireErase(entity);
		getPersistentManager().remove(entity);
		try {
			if (entity instanceof EntityCustomFieldsStorageAccessor)
				CustomFieldsManagerLocator.locate().removeValues(this, entity.getPrimaryKey());
		} catch (Exception e) {
			getLogger().error("Error during custom fields removing", e);
		}
	}
	
	/**
	 * ���������� �������� �� ���������� �����
	 * 
	 * @param primaryKey	��������� ����
	 */
	private void internalErase(ID primaryKey) {
		internalErase(internalLoad(primaryKey));
	}
	
	/**
	 * ���������� �������� ������� ��������
	 * 
	 * @param primaryKey	��������� ����
	 * @return	������ ��������
	 */
	private T internalLoad(ID primaryKey) {
		T entity = getPersistentManager().find(getEntityClass(), primaryKey);
		try {
			if (entity instanceof EntityCustomFieldsStorageAccessor)
				((EntityCustomFieldsStorageAccessor) entity).setStorage(CustomFieldsManagerLocator.locate().createStorage(this, primaryKey));
		} catch (Exception e) {
			getLogger().error("Error during custom fields loading", e);
		}
		return entity;
	}

	/**
	 * ���������� �������� ������
	 * 
	 * @param entity	������ ��� ������ ��������
	 */
	private void internalValidate(T entity) {
		ValidationContext context = new ValidationContext();
		if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnValidate(this, context, entity))
			onValidate(context, entity);
		context.validate();
	}
	
	/**
	 * ���������� �����������
	 * 
	 * @param entity		������ ��� ������ ��������
	 * @param deepClone	���������� ������� ������
	 * @return				������ ��� ������ ��������
	 */
	@SuppressWarnings("unchecked")
	private T internalClone(T entity, boolean deepClone, AttributeMap attributes) {
		if (entity == null)
			return null;
		
		T entityClone = (T) entity.cloneEntity(attributes);
		if (entityClone != null) {
			//�������� ���������������� ����
			if (entity instanceof EntityCustomFieldsStorageAccessor)
				CustomFieldsManagerLocator.locate().cloneValues(this, entity, this, entityClone);
			
			if (!DataBusinessServiceInterceptorManagerLocator.locate().invokeOnClone(this, entityClone))
				onClone(entityClone);
			create(entityClone);
			if (deepClone)
				doDeepClone(entity, entityClone);			
		}
		return entityClone;		
	}
	
	/**
	 * �������� ���������������� �����
	 * 
	 * @param entities	������ ���������
	 * @return	������ ��������� � ����������������� ������
	 */
	private List<T> loadCustomFields(List<T> entities) {
		try {
			if (!entities.isEmpty()) {
				CustomFieldsManager customFieldsManager = CustomFieldsManagerLocator.locate();
				for (T entity : entities)
					if (entity instanceof EntityCustomFieldsStorageAccessor)
						((EntityCustomFieldsStorageAccessor) entity).setStorage(customFieldsManager.createStorage(this, entity.getPrimaryKey()));
			}
		} catch (Exception e) {
			getLogger().error("Error during custom fields loading", e);
		}
		return entities;
	}
	
	/**
	 * �������� ��������� ������������� ���������
	 * 
	 * @return	��������
	 */
	protected PersistentManager getPersistentManager() {
        return ServerUtils.getPersistentManager();
    }

	/**
	 * ���������� ������� �����������, ���������� ������ �������������� ������ ����� ��� �����������
	 * ����� "�������"
	 * 
	 * @param entity		��������
	 * @param entityClone	�������� ����
	 */
	protected void doDeepClone(T entity, T entityClone) {
		
	}
	
	/**
	 * ���������� ����������� ��������, � ���������� ���������� ������� ����������� �� �������� Folder,
	 * ���� ������ ����� �������� ����������, �� ���������� �������������� ������ �����
	 * 
	 * @param primaryKeys	������ �������� ��� ��������
	 * @param targetEntity	������ ����������
	 */
	protected boolean doMove(List<ID> primaryKeys, Object targetEntity) {
		boolean result = false;
		for (ID key : primaryKeys) {
			T entity = load(key);
			if (entity.hasAttribute("Folder")) {
				entity.setAttribute("Folder", targetEntity);
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * ������� ������ � ������� ������������ ����������. ����� ������ ������ ������ ����� �����������,
	 * ������ ����� �� ���������� � ���������� ��������� �� ������������� ��������� � ����������.
	 * 
	 * @param entity ������
	 * @return	��������� ���� ������� ��������
	 */
	protected ID doCreate(T entity) {
		return internalCreate(entity);
	}
	
	/**
	 * ������� ������ �������� �� �������� ������������� ��������� � ����������� ��������� �� ���������� �����. ������ ����� ��������������
	 * � ���������� ���������  �� ������������� ��������� � ����������.
	 * 
	 * @param primaryKey ��������� ����
	 */
	protected void doErase(ID primaryKey) {
		internalErase(primaryKey);
	}
	
	/**
	 * ������� ������ �������� �� �������� ������������� ��������� � ����������� ���������. ������ ����� ��������������
	 * � ���������� ���������  �� ������������� ��������� � ����������.
	 * 
	 * @param entity ������ ��������
	 */
	protected void doErase(T entity) {
		internalErase(entity);
	}
	
	/**
	 * ������������� �������, ������ ����� ��������������� ������� ���������� �� ���������� ����������
	 * ������-����������. � ����������� ������� ��������� ���� �� ����������.
	 * 
	 * @return	������
	 */
	protected T doInitialize() {
		return internalInitialize();
	}
	
	/**
	 * ������������� �������, ������ ����� ��������������� ������� ���������� �� ���������� ����������
	 * ������-���������� � ������� �� ��������� <code>attributes</code>. � ����������� ������� ��������� ���� �� ����������.
	 * 
	 * @param attributes ����� ������������ ������� ������� � ��������
	 * @return	������
	 */
	protected T doInitialize(AttributeMap attributes) {
		return internalInitialize(attributes);
	}
	
	/**
	 * �������� ������� �������� �� ���������� �����, �������� �������� ����� ��������� �� �������� ��������� ���
	 * ����.
	 * 
	 * @param primaryKey
	 * @return	������ �������� ��� <code>null</code> ���� �� ������� � ���������
	 */
	protected T doLoad(ID primaryKey) {
		return internalLoad(primaryKey);
	}
	
	/**
	 * ����������� ������� �������� � ������� ������������ ����������. ����� ������ ������ ������������ ������ ����� �����������,
	 * ������ ����� ��������� ������� ����� �� ���������� � ���������� ��������� �� ������������� ��������� � ����������.
	 * � ������ ���� <code>entity</code> ��� ���������� �� ���������, �� ����� ������ ����� ������ �������� ������� �����
	 * �������� ������ <code>entity</code>.
	 * 
	 * @param entity	������ ��������
	 * @return	������ �������� ��������� � ������� ������������ ����������
	 */
	protected T doStore(T entity) {
		return internalStore(entity);
	}
	
	/**
	 * ����������� ������� ��������, ���������� ����� ����� ������� � ������� ������������ ����������
	 * @param entity - �������� ��� �����������
	 * @param deepClone - ������� ����������� ��������� ����������� �� <code>entity</code>
	 * @param attributes  - ����� ������������ ������� ������� � ��������, ����� ���� <code>null</code>
	 * @return �������� ��� <code>null</code> ���� ����������� �� �������������� ��� ����������
	 */
	protected T doClone(T entity, boolean deepClone, AttributeMap attributes) {
		return internalClone(entity, deepClone, attributes);
	}
	
	//default events
	
	//callbacks
	
	/**
	 * ���������� ������� ������������� �������, � ���������������� ������� ���������� ������������� ��������
	 * ������� �������, �������� ��������� �������� ��� ����� ����������� ���� ������������� ����������
	 * ����� ����� {@link #initialize(AttributeMap)}
	 * 
	 * @param entity	������
	 */
	protected void onInitialize(final T entity) {
		
	}
	
	/**
	 * ���������� ������� ���������� ������� � ������� ������������ ����������, ���������� ���������������
	 * ����� ���������� � ��������, � ���������������� ������� �������� �������� �� ������������� �
	 * �������������� �������� �� ��������� �������
	 * 
	 * @param entity	������
	 */
	protected void onCreate(final T entity) {
		
	}

	/**
	 * ���������� ������� ���������� ������� � ������� ������������ ����������, ����������
	 * ����� ���������� � ��������, � ���������������� ������� �������� �������������� �������� �� ��������� �������
	 * 
	 * @param entity	������
	 */
	protected void onPostCreate(final T entity) {
		
	}

	/**
	 * ���������� ������� ����������� ������� �������� � ������� ������������ ����������, ���������� ���������������
	 * ����� ������������ � ����������, � ���������������� ������� �������� �������� �� ������������� �
	 * �������������� �������� �� ��������� �������
	 * 
	 * @param entity	������ ��������
	 */
	protected void onStore(final T entity) {
		
	}
	
	/**
	 * ���������� ������� �� �������� ������� ��������, ���������� ���������������
	 * ����� ��������� �� ���������, � ���������������� ������� �������� �������� �� �������� �����������
	 * �������� �������.
	 * 
	 * @param entity	������ ��������
	 */
	protected void onErase(T entity) {
		
	}
	
	/**
	 * ���������� ������� �������� ������, ���������� ����� ��������� ���������� � ����������� � ������� ������������ ����������,
	 * �.�. ����� ������� ������������ {@link #onCreate(PersistentObject)} � {@link #onStore(PersistentObject)}
	 * 
	 * @param context	�������� �������� ������
	 * @param entity	������ ��� ������ ��������
	 */
	protected void onValidate(ValidationContext context, T entity) {
		
	}
	
	/**
	 * ���������� ������� ����������� ������� ��������, ���������� ���������������
	 * ����� ������������ � ����������, � ���������������� ������� �������� �������� �� ������������� �
	 * �������������� �������� �� ��������� �������
	 * 
	 * @param entity
	 */
	protected void onClone(final T entity) {
		
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#initialize()
	 */
	@PermitAll
	public T initialize() {
		return doInitialize();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#create(T)
	 */
	public ID create(T entity) {
		return doCreate(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#store(T)
	 */
	public T store(T entity) {
		return doStore(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#erase(T)
	 */
	public void erase(T entity) {
		doErase(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#load(ID)
	 */
	public T load(ID primaryKey) {
		return doLoad(primaryKey);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#instantiateEntity()
	 */
	@PermitAll
	public T instantiateEntity() {
		try {
			T result = getEntityClass().newInstance();
			try {
				if (result instanceof EntityCustomFieldsStorageAccessor)
					((EntityCustomFieldsStorageAccessor) result).setStorage(CustomFieldsManagerLocator.locate().createStorage(this, null));
			} catch (Exception e) {
				getLogger().error("Error during custom fields descriptor loading", e);
			}
			return result;
		} catch (InstantiationException e) {
			throw new ApplicationException(e);
		} catch (IllegalAccessException e) {
			throw new ApplicationException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#initialize(com.mg.framework.api.AttributeMap)
	 */
	@PermitAll
	public T initialize(AttributeMap attributes) {
		return doInitialize(attributes);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#erase(java.lang.Object)
	 */
	public void erase(ID primaryKey) {
		doErase(primaryKey);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#findByCriteria(com.mg.framework.api.orm.Criterion...)
	 */
	@PermitAll
	public List<T> findByCriteria(Criterion ... criteria) {
		return loadCustomFields(OrmTemplate.getInstance().findByCriteria(getEntityClass(), criteria));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#findByExample(T, java.lang.String[])
	 */
	@PermitAll
	public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
		List<T> result = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(getEntityClass()).add(Example.create(exampleInstance, excludeProperty, null, null, false)));
		return loadCustomFields(result);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#getEntityClass()
	 */
	@PermitAll
	public Class<T> getEntityClass() {
		if (persistentClass == null)
			persistentClass = BusinessObjectUtils.getBusinessServiceEntityClass(this);
		return persistentClass;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.DataBusinessObjectService#clone(com.mg.framework.api.orm.PersistentObject, boolean, com.mg.framework.api.AttributeMap)
	 */
	@PermitAll
	public T clone(T entity, boolean deepClone, AttributeMap attributes) {
		//���������� ��� ����������� ������ �� ��������
		try {
			SecurityUtils.checkPermission(new BusinessMethodPermission(getBusinessServiceMetadata().getName(), BusinessMethodPermission.CREATE_METHOD));
		} catch (SecurityException e) {
			//������� ����� �� �������� ����� ���������� � ����������
			throw new com.mg.framework.api.SecurityException(Messages.getInstance().getMessage(Messages.NO_PERMISSION), e);
		}
		return doClone(entity, deepClone, attributes);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.MovableDataBusinessObjectService#move(com.mg.framework.api.orm.PersistentObject, java.lang.Object)
	 */
	@PermitAll
	public boolean move(List<ID> primaryKeys, Object targetEntity) {
		return doMove(primaryKeys, targetEntity);
	}

}
