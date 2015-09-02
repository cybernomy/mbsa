/*
 * BalanceFormsServiceBean.java
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

import com.mg.merp.account.BalanceFormsServiceLocal;

/**
 * @ejb.bean name = "BalanceFormsService"
 *           type = "Stateless"
 *           view-type = "local"
 *           local-jndi-name = "merp/account/BalanceFormsService"
 *           description = "BalanceForms"
 *           display-name = "BalanceForms"
 *
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 *
 * @ejb.home      local-extends = "javax.ejb.EJBLocalHome"
 *
 * @ejb.env-entry name = "DomainClassName"
 *                type = "java.lang.String"
 *                value = "com.mg.merp.account.support.BalanceFormsDomainImpl"
 *
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 *
 * @author java generator
 * @since 12.10.2004
 *
 */
 public class BalanceFormsServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean implements BalanceFormsServiceLocal {

 	public void ejbCreate() throws javax.ejb.CreateException {
 	}

 }
