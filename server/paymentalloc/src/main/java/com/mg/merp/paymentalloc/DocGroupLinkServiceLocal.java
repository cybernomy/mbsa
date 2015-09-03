/*
 * DocGroupLinkServiceLocal.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.paymentalloc;

import com.mg.merp.paymentalloc.model.DocGroupLink;

/**
 * Бизнес-компонент "Типы документов в группах"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: DocGroupLinkServiceLocal.java,v 1.2 2007/11/08 06:49:11 sharapov Exp $
 */
public interface DocGroupLinkServiceLocal extends com.mg.framework.api.DataBusinessObjectService<DocGroupLink, Integer> {
	
	/**
	 * Имя сервиса
	 */
	static final String SERVICE_NAME = "merp/paymentalloc/DocGroupLink"; //$NON-NLS-1$
	
}
