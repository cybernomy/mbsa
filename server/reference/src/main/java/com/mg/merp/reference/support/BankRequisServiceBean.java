/*
 * BankRequisServiceBean.java
 *
 * Copyright (C) 1998 - 2004 Millennium Group. All rights reserved.
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.reference.support;

import com.mg.merp.reference.BankRequisServiceLocal;
import com.mg.merp.reference.model.BankRequis;

/**
 * @author java generator
 * @ejb.bean name = "BankRequisService" type = "Stateless" view-type = "local" local-jndi-name =
 * "merp/reference/BankRequisService" description = "BankRequis" display-name = "BankRequis"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @ejb.env-entry name = "DomainClassName" type = "java.lang.String" value =
 * "com.mg.merp.reference.support.BankRequisDomainImpl"
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 * @since 19.07.2004
 */
public class BankRequisServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean<BankRequis, Integer> implements BankRequisServiceLocal {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

}
