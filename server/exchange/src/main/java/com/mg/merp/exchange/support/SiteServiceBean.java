/*
 * Created on 17.01.2005
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
 */
package com.mg.merp.exchange.support;

import com.mg.framework.generic.AbstractDataBusinessObjectServiceBean;
import com.mg.merp.exchange.SiteServiceLocal;
import com.mg.merp.exchange.model.Site;

/**
 * @author krivopoustov
 * @ejb.bean name = "SiteService" type = "Stateless" view-type = "local" local-jndi-name =
 * "merp/exchange/SiteService" description = "Site" display-name = "Site"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @ejb.env-entry name = "DomainClassName" type = "java.lang.String" value =
 * "com.mg.merp.exchange.support.SiteDomainImpl"
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 */
public class SiteServiceBean extends AbstractDataBusinessObjectServiceBean<Site, Integer> implements SiteServiceLocal {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

}
