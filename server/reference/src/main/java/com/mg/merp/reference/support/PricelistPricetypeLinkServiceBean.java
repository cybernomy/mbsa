/*
 * Created on 24.02.2005
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 */
package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.reference.PricelistPricetypeLinkServiceLocal;
import com.mg.merp.reference.model.PriceListPriceTypeLink;

/**
 * Бизнес-компонент "Связи с типами цен"
 * 
 * @author leonova
 * @version $Id: PricelistPricetypeLinkServiceBean.java,v 1.3 2006/09/13 07:00:35 leonova Exp $
 */
@Stateless(name="merp/reference/PricelistPricetypeLinkService")
public class PricelistPricetypeLinkServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<PriceListPriceTypeLink, Integer> implements PricelistPricetypeLinkServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PriceListPriceTypeLink entity) {
		context.addRule(new MandatoryAttribute(entity, "PriceType"));
	}

}
