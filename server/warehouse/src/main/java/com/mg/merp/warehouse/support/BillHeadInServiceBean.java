/*
 * BillHeadInServiceBean.java
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
import com.mg.merp.warehouse.BillHeadInServiceLocal;
import com.mg.merp.warehouse.BillHeadModelInServiceLocal;
import com.mg.merp.warehouse.BillSpecInServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.BillHead;

/**
 * Бизнес-компонент "Входящие счета" 
 * 
 * @author leonova
 * @version $Id: BillHeadInServiceBean.java,v 1.7 2008/11/24 12:55:53 safonov Exp $
 */
@Stateless(name="merp/warehouse/BillHeadInService")
public class BillHeadInServiceBean extends AbstractWarehouseDocument<BillHead, Integer, BillHeadModelInServiceLocal, BillSpecInServiceLocal> implements BillHeadInServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
	 */
	@Override
	protected void doAdjust(BillHead entity) {
		super.doAdjust(entity);
		entity.setSrcStock(null);
		entity.setSrcMol(null);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final BillHead entity) {
		super.onValidate(context, entity);
		
		context.addRule(new MandatoryAttribute(entity, "To"));
		context.addRule(new MandatoryAttribute(entity, "From"));
	}

	@Override
	protected int getDocSectionIdentifier() {
		return BillHeadInServiceLocal.DOCSECTION;
	}

}
