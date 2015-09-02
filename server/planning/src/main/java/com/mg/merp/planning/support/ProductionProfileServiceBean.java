/*
 * ProductionProfileServiceBean.java
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

package com.mg.merp.planning.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.planning.ProductionProfileServiceLocal;
import com.mg.merp.planning.model.ProductionProfile;

/**
 * Бизнес-компонент "Профили производства"
 *  
 * @author leonova
 * @version $Id: ProductionProfileServiceBean.java,v 1.3 2006/08/25 10:18:33 leonova Exp $
 */
@Stateless(name="merp/planning/ProductionProfileService")
public class ProductionProfileServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ProductionProfile, Integer> implements ProductionProfileServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, ProductionProfile entity) {
		context.addRule(new MandatoryAttribute(entity, "GenericItem"));
		context.addRule(new MandatoryAttribute(entity, "StartBucketOffset"));
		context.addRule(new MandatoryAttribute(entity, "EndBucketOffset"));		
	}

 

}
