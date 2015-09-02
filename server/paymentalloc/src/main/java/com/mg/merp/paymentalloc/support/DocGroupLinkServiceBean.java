/*
 * DocGroupLinkServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */

package com.mg.merp.paymentalloc.support;

import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.paymentalloc.DocGroupLinkServiceLocal;
import com.mg.merp.paymentalloc.model.DocGroupLink;

/**
 * Бизнес-компонент "Типы документов в группах" 
 * 
 * @author leonova
 * @version $Id: DocGroupLinkServiceBean.java,v 1.3 2006/08/29 13:36:50 leonova Exp $
 */
@Stateless(name="merp/paymentalloc/DocGroupLinkService")
public class DocGroupLinkServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DocGroupLink, Integer> implements DocGroupLinkServiceLocal {



}
