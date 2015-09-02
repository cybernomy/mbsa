/*
 * PartnEmplEmailServiceBean.java
 *
 * Copyright (C) 1998 - 2004 Millennium Group. All rights reserved.
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.reference.support;

import com.mg.merp.reference.PartnEmplEmailServiceLocal;
import com.mg.merp.reference.model.PartnEmplEmail;

/**
 * @ejb.bean name = "PartnEmplEmailService"
 *           type = "Stateless"
 *           view-type = "local"
 *           local-jndi-name = "merp/reference/PartnEmplEmailService"
 *           description = "PartnEmplEmail"
 *           display-name = "PartnEmplEmail"
 *
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 *
 * @ejb.home      local-extends = "javax.ejb.EJBLocalHome"
 *
 * @ejb.env-entry name = "DomainClassName"
 *                type = "java.lang.String"
 *                value = "com.mg.merp.reference.support.PartnEmplEmailDomainImpl"
 *
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 *
 * @author java generator
 * @since 19.07.2004
 *
 */
 public class PartnEmplEmailServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean<PartnEmplEmail, Integer> implements PartnEmplEmailServiceLocal {

 	public void ejbCreate() throws javax.ejb.CreateException {
 	}

 }
