/*
 * InvoiceHeadInServiceBean.java
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
import com.mg.merp.warehouse.InvoiceHeadInServiceLocal;
import com.mg.merp.warehouse.InvoiceHeadModelInServiceLocal;
import com.mg.merp.warehouse.InvoiceSpecInServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.InvoiceHead;

/**
 * Бизнес-компонент "Входящие накладные" 
 * 
 * @author leonova
 * @version $Id: InvoiceHeadInServiceBean.java,v 1.7 2008/11/24 12:55:53 safonov Exp $
 */
@Stateless(name="merp/warehouse/InvoiceHeadInService")
public class InvoiceHeadInServiceBean extends AbstractWarehouseDocument<InvoiceHead, Integer, InvoiceHeadModelInServiceLocal, InvoiceSpecInServiceLocal> implements InvoiceHeadInServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
	 */
	@Override
	protected void doAdjust(InvoiceHead entity) {
		super.doAdjust(entity);
		entity.setSrcStock(null);
		entity.setSrcMol(null);
	}

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
		return InvoiceHeadInServiceLocal.DOCSECTION;
	}

}
