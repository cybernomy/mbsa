/*
 * OrderItemServiceBean.java
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

package com.mg.merp.humanresources.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.humanresources.OrderItemServiceLocal;
import com.mg.merp.humanresources.model.OrderItem;

/**
 * Реализация бизнес-компонента "Пункты приказа"
 * 
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: OrderItemServiceBean.java,v 1.7 2007/08/27 13:34:01 sharapov Exp $
 */
@Stateless(name="merp/humanresources/OrderItemService") //$NON-NLS-1$
public class OrderItemServiceBean extends com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean<OrderItem, Integer> implements OrderItemServiceLocal {
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, OrderItem entity) {
		context.addRule(new MandatoryAttribute(entity, "Order")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "OrderItemKind")); //$NON-NLS-1$
	}

}
