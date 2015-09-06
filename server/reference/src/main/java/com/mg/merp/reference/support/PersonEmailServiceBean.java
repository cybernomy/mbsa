/*
 * PersonEmailServiceBean.java
 *
 * Copyright (C) 1998 - 2004 Millennium Group. All rights reserved.
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.reference.support;

/**
 * @author java generator
 * @ejb.bean name = "PersonEmailService" type = "Stateless" view-type = "local" local-jndi-name =
 * "merp/reference/PersonEmailService" description = "PersonEmail" display-name = "PersonEmail"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @ejb.env-entry name = "DomainClassName" type = "java.lang.String" value =
 * "com.mg.merp.reference.support.PersonEmailDomainImpl"
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 * @since 19.07.2004
 */
public class PersonEmailServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

}
