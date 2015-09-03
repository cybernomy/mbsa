/*
 * CurrencyRateTypeServiceBean.java
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

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.CurrencyRateTypeServiceLocal;
import com.mg.merp.reference.model.CurrencyRateType;

/**
 * Бизнес-компонент "Типы курсов валют" 
 * 
 * @author leonova
 * @version $Id: CurrencyRateTypeServiceBean.java,v 1.4 2006/10/20 05:53:02 leonova Exp $
 */
@Stateless(name="merp/reference/CurrencyRateTypeService")
public class CurrencyRateTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CurrencyRateType, Integer> implements CurrencyRateTypeServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, CurrencyRateType entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "Name"));		
	}

}
