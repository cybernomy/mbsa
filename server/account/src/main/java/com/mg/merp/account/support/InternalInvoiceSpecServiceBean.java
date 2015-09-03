/*
 * InternalInvoiceSpecServiceBean.java
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

import com.mg.merp.account.InternalInvoiceHeadServiceLocal;
import com.mg.merp.account.InternalInvoiceSpecServiceLocal;
import com.mg.merp.account.model.InternalInvoiceSpec;
import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;

/**
 * Бизнес-компонент "Спецификация внутренних накладных" 
 * 
 * @author leonova
 * @version $Id: InternalInvoiceSpecServiceBean.java,v 1.5 2007/02/06 16:59:09 safonov Exp $
 */
@Stateless(name="merp/account/InternalInvoiceSpecService")
public class InternalInvoiceSpecServiceBean extends GoodsDocumentSpecificationServiceBean<InternalInvoiceSpec, Integer> implements InternalInvoiceSpecServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InternalInvoiceHeadServiceLocal.DOCSECTION;
	}

}
