/*
 * DiscountServiceBean.java
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

package com.mg.merp.discount.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.discount.DiscountServiceLocal;
import com.mg.merp.discount.model.Discount;

/**
 * Бизнес-компонент "Скидки / наценки" 
 * 
 * @author leonova
 * @version $Id: DiscountServiceBean.java,v 1.4 2007/09/07 12:02:18 safonov Exp $
 */
@Stateless(name="merp/discount/DiscountService")
public class DiscountServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Discount, Integer> implements DiscountServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Discount entity) {
		context.addRule(new MandatoryStringAttribute(entity, "DName"));
	}

}
