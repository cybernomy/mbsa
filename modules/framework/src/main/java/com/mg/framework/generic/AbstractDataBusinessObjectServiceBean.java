/*
 * AbstractDataBusinessObjectServiceBean.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.BrowseCond;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.DataDomain;
import com.mg.framework.api.orm.Criterion;
import com.mg.framework.api.orm.Example;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.utils.BusinessObjectUtils;
import com.mg.framework.utils.ServerUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.security.PermitAll;

/**
 * @author Oleg V. Safonov
 * @version $Id: AbstractDataBusinessObjectServiceBean.java,v 1.16 2008/03/07 12:31:33 safonov Exp
 *          $
 */
@Deprecated
public abstract class AbstractDataBusinessObjectServiceBean<T extends PersistentObject, ID extends Serializable>
    extends AbstractBusinessObjectStatelessServiceBean implements DataBusinessObjectService<T, ID> {

  protected DataDomain getDataDomain() throws ApplicationException {
    return (DataDomain) getDomain();
  }

	/*public PersistentObject initialize(PersistentObject object) {
        AttributeMap attr = null;
		if (object != null)
			attr = object.getAllAttributes();
		PersistentObject result = ServerUtils.persistentManager().make(persistentName(), attr);
		result.setAttributes(getDomain().initialize(object.getAllAttributes()));
		return result;
	}*/

  @SuppressWarnings("unchecked")
  @PermitAll
  public T initialize() {
    return (T) getDataDomain().initialize();
  }

  @SuppressWarnings("unchecked")
  @PermitAll
  public T initialize(AttributeMap attributes) {
    return (T) getDataDomain().initialize(attributes);
  }

  @SuppressWarnings("unchecked")
  @PermitAll
  public T instantiateEntity() {
    PersistentObject result = getDataDomain().make(null);
    //TODO заполняем поле sys_client 1, необходимо заполнять текущим мандантом в который вошел пользователь
    try {
      result.setAttribute("SysClient", ServerUtils.getPersistentManager().find("com.mg.merp.core.model.SysClient", 1));
    } catch (Exception e) {
    }
    return (T) result;
  }

	/*public void create(PersistentObject object) throws javax.ejb.CreateException {
		ServerUtils.persistentManager().create(persistentName(), object);
	}*/

  public Object create(AttributeMap attributes) throws ApplicationException {
    //PersistentObject object = getDataDomain().make(attributes);
    //return this.create(object);
    return getDataDomain().create(attributes);
  }

  @SuppressWarnings("unchecked")
  public ID create(T entity) {
    return (ID) getDataDomain().create(entity);
  }

  public AttributeMap loadView(Object primaryKey, Collection<?> keyOfAttributes, String viewName) throws ApplicationException {
    return getDataDomain().loadView(primaryKey, keyOfAttributes, viewName);
  }

  @SuppressWarnings("unchecked")
  public T load(ID primaryKey) {
    return (T) getDataDomain().load(primaryKey);
    //return getDomain().load(key, fieldList);
  }

  public void store(AttributeMap attributes) throws ApplicationException {
    getDataDomain().store(attributes);
    //PersistentObject object = getDataDomain().make(attributes);
    //obj.setAttributes(attributes);
    //this.store(object);
    //ServerUtils.persistentManager().store(ServerUtils.persistentManager().make(persistentName(), attributes));
    //PersistentManager.INSTANCE.find(persistentName(), attributes.getPrimaryKey()).setValues(attributes);
    //getDomain().store(attributes);
  }

  /*
   *  (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#store(null)
   */
  public T store(T entity) {
    getDataDomain().store(entity);
    return entity;
  }

  public void erase(ID primaryKey) {
    getDataDomain().remove(primaryKey);
    getDataDomain().getPersistentManager().flush();
  }

  public void erase(T entity) throws com.mg.framework.api.orm.RemoveException {

  }

  public Object loadBrowse(BrowseCond cond) throws ApplicationException {
    //flush all caches in DB, because browse loading by native SQL
    getDataDomain().getPersistentManager().flush();
    return getDataDomain().loadBrowse(cond);
  }

  public Object clone(Object key, boolean cloneDetail) throws ApplicationException {
    return getDataDomain().clone(key, cloneDetail);
  }

  public void changeParent(Object key, Object newParent) throws ApplicationException {
    getDataDomain().changeParent(key, newParent);
  }

  public Object loadFolders() throws ApplicationException {
    throw new ApplicationException("Not implemented");
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#findByCriteria(com.mg.framework.api.orm.Criterion...)
   */
  public List<T> findByCriteria(Criterion... criteria) {
    return OrmTemplate.getInstance().findByCriteria(getEntityClass(), criteria);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#findByExample(T, java.lang.String[])
   */
  public List<T> findByExample(T exampleInstance, String[] excludeProperty) {
    return OrmTemplate.getInstance().findByCriteria(getEntityClass(), Example.create(exampleInstance, excludeProperty, null, null, false));
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#getEntityClass()
   */
  public Class<T> getEntityClass() {
    return BusinessObjectUtils.getBusinessServiceEntityClass(this);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#clone(com.mg.framework.api.orm.PersistentObject, boolean)
   */
  public T clone(T entity, boolean deepClone, AttributeMap attributes) {
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessObjectService#move(com.mg.framework.api.orm.PersistentObject, java.lang.Object)
   */
  public boolean move(List<ID> primaryKeys, Object targetEntity) {
    return false;
  }

}
