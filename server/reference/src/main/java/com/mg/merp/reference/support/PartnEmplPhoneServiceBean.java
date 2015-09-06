/*
 * PartnEmplPhoneServiceBean.java
 *
 * Copyright (C) 1998 - 2004 Millennium Group. All rights reserved.
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.reference.support;

import com.mg.merp.reference.PartnEmplPhoneServiceLocal;
import com.mg.merp.reference.model.PartnEmplPhone;

/**
 * @author java generator
 * @ejb.bean name = "PartnEmplPhoneService" type = "Stateless" view-type = "local" local-jndi-name =
 * "merp/reference/PartnEmplPhoneService" description = "PartnEmplPhone" display-name =
 * "PartnEmplPhone"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @ejb.env-entry name = "DomainClassName" type = "java.lang.String" value =
 * "com.mg.merp.reference.support.PartnEmplPhoneDomainImpl"
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 * @since 19.07.2004
 */
public class PartnEmplPhoneServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean<PartnEmplPhone, Integer> implements PartnEmplPhoneServiceLocal {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

}
