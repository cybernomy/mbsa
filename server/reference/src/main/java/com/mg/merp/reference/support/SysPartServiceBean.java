/*
 * SysPartServiceBean.java
 *
 * Copyright (C) 1998 - 2004 Millennium Group. All rights reserved.
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.reference.support;

/**
 * @ejb.bean name = "SysPartService"
 *           type = "Stateless"
 *           view-type = "local"
 *           local-jndi-name = "merp/reference/SysPartService"
 *           description = "SysPart"
 *           display-name = "SysPart"
 *
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 *
 * @ejb.home      local-extends = "javax.ejb.EJBLocalHome"
 *
 * @ejb.env-entry name = "DomainClassName"
 *                type = "java.lang.String"
 *                value = "com.mg.merp.reference.support.SysPartDomainImpl"
 *
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 *
 * @author java generator
 * @since 19.07.2004
 *
 */
 public class SysPartServiceBean extends com.mg.framework.generic.AbstractDataBusinessObjectServiceBean {

 	public void ejbCreate() throws javax.ejb.CreateException {
 	}

 }
