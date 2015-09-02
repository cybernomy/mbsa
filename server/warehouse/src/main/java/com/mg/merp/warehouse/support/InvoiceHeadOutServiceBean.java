/*
 * InvoiceHeadOutServiceBean.java
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

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.warehouse.InvoiceHeadModelOutServiceLocal;
import com.mg.merp.warehouse.InvoiceHeadOutServiceLocal;
import com.mg.merp.warehouse.InvoiceSpecOutServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.InvoiceHead;

/**
 * Бизнес-компонент "Исходящие накладные" 
 * 
 * @author leonova
 * @version $Id: InvoiceHeadOutServiceBean.java,v 1.6 2006/12/12 15:34:04 safonov Exp $
 */
@Stateless(name="merp/warehouse/InvoiceHeadOutService")
public class InvoiceHeadOutServiceBean extends AbstractWarehouseDocument<InvoiceHead, Integer, InvoiceHeadModelOutServiceLocal, InvoiceSpecOutServiceLocal> implements InvoiceHeadOutServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, InvoiceHead entity) {
		super.onValidate(context, entity);
		
		context.addRule(new MandatoryAttribute(entity, "To"));
		context.addRule(new MandatoryAttribute(entity, "From"));
	}

	@Override
	protected int getDocSectionIdentifier() {
		return InvoiceHeadOutServiceLocal.DOCSECTION;
	}

}
