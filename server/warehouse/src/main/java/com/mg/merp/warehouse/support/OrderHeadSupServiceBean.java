/*
 * OrderHeadSupServiceBean.java
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

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.warehouse.OrderHeadModelSupServiceLocal;
import com.mg.merp.warehouse.OrderHeadSupServiceLocal;
import com.mg.merp.warehouse.OrderSpecSupServiceLocal;
import com.mg.merp.warehouse.generic.AbstractWarehouseDocument;
import com.mg.merp.warehouse.model.OrderDueDateKind;
import com.mg.merp.warehouse.model.OrderHead;

/**
 * Бизнес-компонент "Заказы поставщикам" 
 * 
 * @author leonova
 * @version $Id: OrderHeadSupServiceBean.java,v 1.10 2008/11/24 12:55:53 safonov Exp $
 */
@Stateless(name="merp/warehouse/OrderHeadSupService")
public class OrderHeadSupServiceBean extends AbstractWarehouseDocument<OrderHead, Integer, OrderHeadModelSupServiceLocal, OrderSpecSupServiceLocal> implements OrderHeadSupServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentServiceBean#doAdjust(com.mg.merp.document.model.DocHead)
	 */
	@Override
	protected void doAdjust(OrderHead entity) {
		super.doAdjust(entity);
		if (entity.getDueDateKind() == null)
			entity.setDueDateKind(OrderDueDateKind.NONE); 
		entity.setSrcStock(null);
		entity.setSrcMol(null);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.GoodsDocumentServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, OrderHead entity) {
		super.onValidate(context, entity);

		context.addRule(new MandatoryAttribute(entity, "To"));
		context.addRule(new MandatoryAttribute(entity, "From"));
	}

	/**
	 * @ejb.interface-method view-type = "local"
	 * 
	 * @param orderList
	 * @param docsumentSection
	 * @param modelId
	 * @param folderId
	 * @return
	 * @throws ApplicationException
	 */
	public int createDocument(int[] orderList, int docsumentSection, int modelId, int folderId) throws ApplicationException {
		return 0;//((OrderHeadSupDomainImpl) getDataDomain()).createDocument(orderList, docsumentSection, modelId, folderId);
	}

	@Override
	protected int getDocSectionIdentifier() {
		return OrderHeadSupServiceLocal.DOCSECTION;
	}

}
