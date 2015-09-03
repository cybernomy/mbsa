/*
 * OrderHeadModelCusServiceBean.java
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

package com.mg.merp.warehouse.support;

import javax.ejb.Stateless;

import com.mg.merp.document.generic.DocumentModelServiceBean;
import com.mg.merp.warehouse.OrderHeadCusServiceLocal;
import com.mg.merp.warehouse.OrderHeadModelCusServiceLocal;
import com.mg.merp.warehouse.model.OrderDueDateKind;
import com.mg.merp.warehouse.model.OrderHeadModel;

/**
 * Бизнес-компонент "Образцы заказов покупателей" 
 * 
 * @author leonova
 * @version $Id: OrderHeadModelCusServiceBean.java,v 1.5 2007/08/15 08:57:25 safonov Exp $
 */
@Stateless(name="merp/warehouse/OrderHeadModelCusService") 
public class OrderHeadModelCusServiceBean extends DocumentModelServiceBean<OrderHeadModel, Integer> implements OrderHeadModelCusServiceLocal {

	private void setValue(OrderHeadModel entity) {
		if (entity.getDueDateKind() == null) {
			entity.setDueDateKind(OrderDueDateKind.NONE); 
		}
	}

	@Override
	protected void onStore(OrderHeadModel entity) {
		setValue(entity);
	}

	@Override
	protected void onCreate(OrderHeadModel entity) {
		setValue(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return OrderHeadCusServiceLocal.DOCSECTION;
	}

}
