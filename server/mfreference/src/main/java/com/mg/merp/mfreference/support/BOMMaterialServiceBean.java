/*
 * BommaterialServiceBean.java
 *
 * Copyright (c) 1998 - 2009 BusinessTechnology, Ltd.
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

package com.mg.merp.mfreference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.mfreference.BOMMaterialServiceLocal;
import com.mg.merp.mfreference.generic.AbstractBOMResource;
import com.mg.merp.mfreference.model.BomMaterial;
import com.mg.merp.mfreference.model.ManufactureConfig;

/**
 * Бизнес-компонент "Операции / Материалы" 
 * 
 * @author leonova
 * @version $Id: BOMMaterialServiceBean.java,v 1.7 2009/03/05 12:36:48 safonov Exp $
 */
@Stateless(name="merp/mfreference/BOMMaterialService")
public class BOMMaterialServiceBean extends AbstractBOMResource<BomMaterial> implements BOMMaterialServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.merp.mfreference.generic.AbstractBOMResource#onInitialize(com.mg.merp.mfreference.model.BomRouteResource)
	 */
	@Override
	protected void onInitialize(BomMaterial entity) {
		super.onInitialize(entity);
		ManufactureConfig config = ConfigurationHelper.getConfiguration();
		entity.setCurrency(config.getBaseCurrency());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, BomMaterial entity) {
		super.onValidate(context, entity);
		context.addRule(new MandatoryAttribute(entity, "Measure"));
		context.addRule(new MandatoryAttribute(entity, "QuantityRateFlag"));
		context.addRule(new MandatoryAttribute(entity, "Catalog"));
		context.addRule(new MandatoryAttribute(entity, "Currency"));			
	}

}
