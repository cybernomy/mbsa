/*
 * InvoiceSpecInServiceBean.java
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

package com.mg.merp.warehouse.support;

import javax.ejb.Stateless;

import com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean;
import com.mg.merp.warehouse.InvoiceHeadInServiceLocal;
import com.mg.merp.warehouse.InvoiceSpecInServiceLocal;
import com.mg.merp.warehouse.model.InvoiceSpec;

/**
 * Бизнес-компонент "Спецификация входящих накладных" 
 * 
 * @author leonova
 * @version $Id: InvoiceSpecInServiceBean.java,v 1.6 2008/12/01 09:37:57 safonov Exp $
 */
@Stateless(name="merp/warehouse/InvoiceSpecInService")
public class InvoiceSpecInServiceBean extends GoodsDocumentSpecificationServiceBean<InvoiceSpec, Integer> implements InvoiceSpecInServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#doAdjust(com.mg.merp.document.model.DocSpec)
	 */
	@Override
	protected void doAdjust(InvoiceSpec entity) {
		super.doAdjust(entity);
		entity.setSrcStock(null);
		entity.setSrcMol(null);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentSpecificationServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected int getDocSectionIdentifier() {
		return InvoiceHeadInServiceLocal.DOCSECTION;
	}

}
