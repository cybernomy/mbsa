/*
 * MRPVersionForecastServiceBean.java
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

package com.mg.merp.planning.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.planning.MRPVersionForecastServiceLocal;
import com.mg.merp.planning.model.MrpVersionForecast;

/**
 * Бизнес-компонент "Версии прогнозов в ППМ" 
 * 
 * @author leonova
 * @version $Id: MRPVersionForecastServiceBean.java,v 1.5 2007/08/27 05:05:03 alikaev Exp $
 */
@Stateless(name="merp/planning/MRPVersionForecastService")
@PermitAll
public class MRPVersionForecastServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MrpVersionForecast, Integer> implements MRPVersionForecastServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, MrpVersionForecast entity) {
		context.addRule(new MandatoryAttribute(entity, "ForecastVersion"));
		context.addRule(new MandatoryAttribute(entity, "ForecastType"));
	}
	
}
