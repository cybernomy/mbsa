/*
 * ReturnPriceServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.account.support;

/**
 * @author java generator
 * @ejb.bean name = "ReturnPriceService" type = "Stateless" view-type = "local" local-jndi-name =
 * "merp/account/ReturnPriceService" description = "ReturnPrice" display-name = "ReturnPrice"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @ejb.env-entry name = "DomainClassName" type = "java.lang.String" value =
 * "com.mg.merp.account.support.ReturnPriceDomainImpl"
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 * @since 12.10.2004
 */
public class ReturnPriceServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

}
