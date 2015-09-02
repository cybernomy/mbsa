/*
 * SalaryHelperServiceBean.java
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

package com.mg.merp.salary.support;

import com.mg.merp.salary.SalaryHelperServiceLocal;

/**
 * @ejb.bean name = "SalaryHelperService"
 *           type = "Stateless"
 *           view-type = "local"
 *           local-jndi-name = "merp/salary/SalaryHelperService"
 *           description = "SalaryHelper"
 *           display-name = "SalaryHelper"
 *
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.BusinessObjectService"
 *
 * @ejb.home      local-extends = "javax.ejb.EJBLocalHome"
 *
 * @ejb.env-entry name = "DomainClassName"
 *                type = "java.lang.String"
 *                value = "com.mg.merp.account.support.SalaryHelperDomainImpl"
 *
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 *
 * @author Tatyana V. Pashistova
 * @since 19.11.2004
 *
 */
 public class SalaryHelperServiceBean extends com.mg.framework.generic.AbstractBusinessObjectStatelessServiceBean implements SalaryHelperServiceLocal {

 	public void ejbCreate() throws javax.ejb.CreateException {
 	}

 }
