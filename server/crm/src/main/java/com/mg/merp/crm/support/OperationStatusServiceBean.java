/*
 * OperationstatusServiceBean.java
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

package com.mg.merp.crm.support;

import com.mg.merp.crm.OperationStatusServiceLocal;
import com.mg.merp.crm.model.OperationStatus;

/**
 * @ejb.bean name = "OperationStatusService"
 *           type = "Stateless"
 *           view-type = "local"
 *           local-jndi-name = "merp/crm/OperationStatusService"
 *           description = "OperationStatus"
 *           display-name = "OperationStatus"
 *
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 *
 * @ejb.home      local-extends = "javax.ejb.EJBLocalHome"
 *
 * @ejb.env-entry name = "DomainClassName"
 *                type = "java.lang.String"
 *                value = "com.mg.merp.crm.support.OperationStatusDomainImpl"
 *
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 *
 * @author java generator
 * @since 12.10.2004
 *
 */
 public class OperationStatusServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean<OperationStatus, Integer> implements OperationStatusServiceLocal {

 	public void ejbCreate() throws javax.ejb.CreateException {
 	}

 }
