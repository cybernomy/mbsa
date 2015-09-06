/**
 * AbstractDataBusinessServiceInterceptor.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd. All rights reserved
 *
 * This program is the proprietary and confidential information of BusinessTechnology, Ltd. and may
 * be used and disclosed only as authorized in a license agreement authorizing and controlling such
 * use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */
package com.mg.framework.generic;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.DataBusinessServiceInterceptor;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;

import java.io.Serializable;

/**
 * Базовый абстрактный класс перехватчиков действий бизнес-компонентов управляющих данными,
 * рекомендуется использовать при создании собственных перехватчиков
 *
 * @author Oleg V. Safonov
 * @version $Id: AbstractDataBusinessServiceInterceptor.java,v 1.1 2007/12/13 13:02:20 safonov Exp
 *          $
 */
public abstract class AbstractDataBusinessServiceInterceptor implements
    DataBusinessServiceInterceptor {

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#getHandledServices()
   */
  public abstract String[] getHandledServices();

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#getName()
   */
  public abstract String getName();

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#onClone(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject)
   */
  public <T extends PersistentObject, ID extends Serializable> boolean onClone(
      DataBusinessObjectService<T, ID> service, T entity) {
    return false;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#onCreate(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject)
   */
  public <T extends PersistentObject, ID extends Serializable> boolean onCreate(
      DataBusinessObjectService<T, ID> service, T entity) {
    return false;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#onErase(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject)
   */
  public <T extends PersistentObject, ID extends Serializable> boolean onErase(
      DataBusinessObjectService<T, ID> service, T entity) {
    return false;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#onInitialize(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject)
   */
  public <T extends PersistentObject, ID extends Serializable> boolean onInitialize(
      DataBusinessObjectService<T, ID> service, T entity) {
    return false;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#onPostCreate(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject)
   */
  public <T extends PersistentObject, ID extends Serializable> boolean onPostCreate(
      DataBusinessObjectService<T, ID> service, T entity) {
    return false;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#onStore(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.orm.PersistentObject)
   */
  public <T extends PersistentObject, ID extends Serializable> boolean onStore(
      DataBusinessObjectService<T, ID> service, T entity) {
    return false;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.DataBusinessServiceInterceptor#onValidate(com.mg.framework.api.DataBusinessObjectService, com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
   */
  public <T extends PersistentObject, ID extends Serializable> boolean onValidate(
      DataBusinessObjectService<T, ID> service,
      ValidationContext context, T entity) {
    return false;
  }

}
