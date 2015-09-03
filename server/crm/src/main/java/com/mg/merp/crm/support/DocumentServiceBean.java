/*
 * DocumentServiceBean.java
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

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.crm.DocumentServiceLocal;
import com.mg.merp.document.model.DocHead;

/**
 * @ejb.bean name = "DocumentService"
 *           type = "Stateless"
 *           view-type = "local"
 *           local-jndi-name = "merp/crm/DocumentService"
 *           description = "DocumentRest"
 *           display-name = "DocumentRest"
 *
 * @ejb.interface local-extends = "javax.ejb.EJBLocalObject, com.mg.framework.api.DataBusinessObjectService"
 *
 * @ejb.home      local-extends = "javax.ejb.EJBLocalHome"
 *
 * @ejb.env-entry name = "DomainClassName"
 *                type = "java.lang.String"
 *                value = "com.mg.merp.crm.support.DocumentDomainImpl"
 *
 * @jboss.container-configuration name = "MERP Standard Stateless Service"
 *
 * @author java generator
 * @since 12.10.2004
 *
 */
/**
 * Бизнес-компонент "Документы" 
 * 
 * @author leonova
 * @version $Id: DocumentServiceBean.java,v 1.3 2006/10/17 10:08:12 leonova Exp $
 */
@Stateless(name="merp/crm/DocumentService")
public class DocumentServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DocHead, Integer> implements DocumentServiceLocal {


}
