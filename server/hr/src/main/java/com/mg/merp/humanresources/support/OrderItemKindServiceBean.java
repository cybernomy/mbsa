/*
 * OrderItemKindServiceBean.java
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
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.humanresources.OrderItemKindServiceLocal;
import com.mg.merp.humanresources.model.OrderItemKind;

/**
 * Реализация бизнес-компонента "Виды пунктов приказов"
 * 
 * @author Artem V. Sharapov
 * @version $Id: OrderItemKindServiceBean.java,v 1.5 2007/08/27 13:34:01 sharapov Exp $
 */
@Stateless(name="merp/humanresources/OrderItemKindService") //$NON-NLS-1$
public class OrderItemKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OrderItemKind, Integer> implements OrderItemKindServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, OrderItemKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
	}

}
