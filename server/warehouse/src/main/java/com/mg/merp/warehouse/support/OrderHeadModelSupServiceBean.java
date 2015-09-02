/*
 * OrderHeadModelSupServiceBean.java
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
import com.mg.merp.warehouse.OrderHeadModelSupServiceLocal;
import com.mg.merp.warehouse.OrderHeadSupServiceLocal;
import com.mg.merp.warehouse.model.OrderDueDateKind;
import com.mg.merp.warehouse.model.OrderHeadModel;

/**
 * Бизнес-компонент "Образцы заказов постащикамх" 
 * 
 * @author leonova
 * @version $Id: OrderHeadModelSupServiceBean.java,v 1.4 2006/10/18 11:40:56 leonova Exp $
 */
@Stateless(name="merp/warehouse/OrderHeadModelSupService") 
public class OrderHeadModelSupServiceBean extends DocumentModelServiceBean<OrderHeadModel, Integer> implements OrderHeadModelSupServiceLocal {

	private void setValue(OrderHeadModel entity) {
		if (entity.getDueDateKind() == null) {
			entity.setDueDateKind(OrderDueDateKind.NONE); 
		}
	}
	
	@Override
	protected void onCreate(OrderHeadModel entity) {
		setValue(entity);
	}

	@Override
	protected void onStore(OrderHeadModel entity) {
		setValue(entity);
	}


	/* (non-Javadoc)
	 * @see com.mg.merp.document.generic.DocumentModelServiceBean#getDocSectionIdentifier()
	 */
	@Override
	protected short getDocSectionIdentifier() {
		return OrderHeadSupServiceLocal.DOCSECTION;
	}



}
