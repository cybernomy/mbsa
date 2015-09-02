/*
 * OrderItemKindParamServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.humanresources.OrderItemKindParamServiceLocal;
import com.mg.merp.humanresources.model.OrderItemKindParam;

/**
 * Реализация бизнес-компонента "Параметр вида пункта приказа" 
 * 
 * @author Artem V. Sharapov
 * @version $Id: OrderItemKindParamServiceBean.java,v 1.5 2007/08/27 13:34:01 sharapov Exp $
 */
@Stateless(name="merp/humanresources/OrderItemKindParamService") //$NON-NLS-1$
public class OrderItemKindParamServiceBean extends AbstractPOJODataBusinessObjectServiceBean<OrderItemKindParam, Integer> implements OrderItemKindParamServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, OrderItemKindParam entity) {
		context.addRule(new MandatoryAttribute(entity, "Name")); //$NON-NLS-1$
	}

}
