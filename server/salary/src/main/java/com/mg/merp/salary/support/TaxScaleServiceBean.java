/*
 * TaxScaleServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.TaxScaleServiceLocal;
import com.mg.merp.salary.model.TaxScale;

/**
 * Реализация бизнес-компонента "Налоговые шкалы" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: TaxScaleServiceBean.java,v 1.6 2007/07/17 08:35:50 sharapov Exp $
 */
@Stateless(name="merp/salary/TaxScaleService") //$NON-NLS-1$
public class TaxScaleServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TaxScale, Integer> implements TaxScaleServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, TaxScale entity) {
		context.addRule(new MandatoryStringAttribute(entity, "SNumber")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "BeginDate")); //$NON-NLS-1$
	}

}
