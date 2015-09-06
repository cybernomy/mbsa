/*
 * SysModuleServiceBean.java
 *
 * Copyright (C) 1998 - 2004 Millennium Group. All rights reserved.
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.reference.support;

import com.mg.merp.core.model.SysModule;
import com.mg.merp.reference.SysModuleServiceLocal;

/**
 * @author java generator
 * @ejb.bean name = "SysModuleService" type = "Stateless" view-type = "local" local-jndi-name =
 * "merp/reference/SysModuleService" description = "SysModule" display-name = "SysModule"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @ejb.env-entry name = "DomainClassName" type = "java.lang.String" value =
 * "com.mg.merp.reference.support.SysModuleDomainImpl"
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 * @since 19.07.2004
 */
public class SysModuleServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean<SysModule, Integer> implements SysModuleServiceLocal {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

}
