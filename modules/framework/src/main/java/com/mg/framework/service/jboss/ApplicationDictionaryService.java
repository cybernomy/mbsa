/*
 * ApplicationDictionaryService.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.service.jboss;

import com.mg.framework.api.BusinessObjectService;
import com.mg.framework.api.DataBusinessObjectService;
import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.metadata.DataItem;
import com.mg.framework.api.metadata.Domain;
import com.mg.framework.api.metadata.ReflectionMetadata;
import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.ui.Form;
import com.mg.framework.service.ApplicationDictionaryImpl;

import org.jboss.system.ServiceMBeanSupport;

/**
 * @author Oleg V. Safonov
 * @version $Id: ApplicationDictionaryService.java,v 1.5 2008/03/03 13:12:23 safonov Exp $
 */
public class ApplicationDictionaryService extends ServiceMBeanSupport implements
    ApplicationDictionaryServiceMBean {
  private ApplicationDictionary delegate = new ApplicationDictionaryImpl();

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#getDomain(java.lang.String)
   */
  public Domain getDomain(String name) {
    return this.delegate.getDomain(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#getDataItem(java.lang.String)
   */
  public DataItem getDataItem(String name) {
    return this.delegate.getDataItem(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#getFieldMetadata(java.lang.Class, java.lang.String)
   */
  public FieldMetadata getFieldMetadata(Class<? extends PersistentObject> entityClazz, String propertyName) {
    return this.delegate.getFieldMetadata(entityClazz, propertyName);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#getFieldMetadata(java.lang.String)
   */
  public FieldMetadata getFieldMetadata(ReflectionMetadata propertyMetadata) {
    return this.delegate.getFieldMetadata(propertyMetadata);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#getMaintenaceForm(com.mg.framework.api.DataBusinessObjectService, java.lang.String)
   */
  public com.mg.framework.api.ui.Form getMaintenaceForm(DataBusinessObjectService<?, ?> service, String formName) {
    return this.delegate.getMaintenaceForm(service, formName);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#getBrowseForm(com.mg.framework.api.DataBusinessObjectService, java.lang.String)
   */
  public com.mg.framework.api.ui.Form getBrowseForm(DataBusinessObjectService<?, ?> service, String formName) {
    return this.delegate.getBrowseForm(service, formName);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#getBusinessService(java.lang.String)
   */
  public BusinessObjectService getBusinessService(String name) {
    return this.delegate.getBusinessService(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#getWindow(java.lang.String)
   */
  public Form getWindow(String windowName) {
    return this.delegate.getWindow(windowName);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.metadata.ApplicationDictionary#invalidateWindowCache()
   */
  public void invalidateWindowCache() {
    this.delegate.invalidateWindowCache();
  }

}
