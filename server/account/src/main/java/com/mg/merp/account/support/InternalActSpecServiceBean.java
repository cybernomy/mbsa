/*
 * InternalActSpecServiceBean.java
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

package com.mg.merp.account.support;

import javax.ejb.Stateless;

import com.mg.merp.account.InternalActHeadServiceLocal;
import com.mg.merp.account.InternalActSpecServiceLocal;
import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.document.model.DocSpec;

/**
 * Бизнес-компонент "Спецификация внутренних актов" 
 * 
 * @author leonova
 * @version $Id: InternalActSpecServiceBean.java,v 1.5 2007/02/06 16:57:17 safonov Exp $
 */
@Stateless(name="merp/account/InternalActSpecService")
public class InternalActSpecServiceBean extends GoodsDocumentSpecificationServiceBean<DocSpec, Integer> implements InternalActSpecServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InternalActHeadServiceLocal.DOCSECTION;
	}

}
