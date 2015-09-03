/*
 * InventoryForecastServiceBean.java
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
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.planning.InventoryForecastServiceLocal;
import com.mg.merp.planning.model.InventoryForecast;

/**
 * Бизнес-компонент "Прогнозы складских запасов" 
 * 
 * @author leonova
 * @version $Id: InventoryForecastServiceBean.java,v 1.4 2006/10/16 07:50:10 leonova Exp $
 */
@Stateless(name="merp/planning/InventoryForecastService")
public class InventoryForecastServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InventoryForecast, Integer> implements InventoryForecastServiceLocal {

	@Override
	protected void onInitialize(InventoryForecast entity) {
		entity.setDescription("");
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, InventoryForecast entity) {
		context.addRule(new MandatoryStringAttribute(entity, "InventoryForecastCode"));
	}

 

}
