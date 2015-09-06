/*
 * Created on 21.12.2004
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
package com.mg.merp.crm.support;

import com.mg.framework.generic.AbstractDataBusinessObjectServiceBean;
import com.mg.merp.crm.ContractorCardServiceLocal;
import com.mg.merp.settlement.model.ContractorCard;

import javax.ejb.CreateException;

/**
 * @author krivopoustov
 * @ejb.bean name = "ContractorCardService" type = "Stateless" view-type = "local" local-jndi-name =
 * "merp/crm/ContractorCardService" description = "ContractorCard" display-name = "ContractorCard"
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 * @ejb.home local-extends = "javax.ejb.EJBLocalHome"
 * @ejb.env-entry name = "DomainClassName" type = "java.lang.String" value =
 * "com.mg.merp.crm.support.ContractorCardDomainImpl"
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 */
public class ContractorCardServiceBean extends
    AbstractDataBusinessObjectServiceBean<ContractorCard, Integer> implements ContractorCardServiceLocal {

  /* (non-Javadoc)
   * @see com.mg.framework.generic.AbstractStatelessSessionBean#ejbCreate()
   */
  public void ejbCreate() throws CreateException {
    // TODO Auto-generated method stub

  }

}
