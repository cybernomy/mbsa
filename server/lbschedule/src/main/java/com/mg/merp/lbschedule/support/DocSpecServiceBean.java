/*
 * DocSpecServiceBean.java
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

package com.mg.merp.lbschedule.support;

import com.mg.merp.document.model.DocSpec;
import com.mg.merp.lbschedule.DocSpecServiceLocal;

/**
 * @author java generator
 * @ejb.bean name = "DocSpecService" type = "Stateless" view-type = "local" local-jndi-name =
 * "merp/lbschedule/DocSpecService" description = "DocSpec" display-name = "DocSpec"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @ejb.env-entry name = "DomainClassName" type = "java.lang.String" value =
 * "com.mg.merp.lbschedule.support.DocSpecDomainImpl"
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 * @since 12.10.2004
 */
public class DocSpecServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean<DocSpec, Integer> implements DocSpecServiceLocal {

  public void ejbCreate() throws javax.ejb.CreateException {
  }

}
