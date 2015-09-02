/*
 * FeatAnalyticsServiceBean.java
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

package com.mg.merp.finance.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.FeatAnalyticsServiceLocal;
import com.mg.merp.finance.model.Analytics;

/**
 * Бизнес-компонент "Спецификация признаков финансового учета" 
 * 
 * @author leonova
 * @version $Id: FeatAnalyticsServiceBean.java,v 1.3 2006/08/28 12:48:45 leonova Exp $
 */
@Stateless(name="merp/finance/FeatAnalyticsService")
public class FeatAnalyticsServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Analytics, Integer> implements FeatAnalyticsServiceLocal{

	private void adjustFeatAnalytics(Analytics entity) {
		entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
	 */
	@Override
	protected void onCreate(Analytics entity) {
		adjustFeatAnalytics(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
	 */
	@Override
	protected void onStore(Analytics entity) {
		adjustFeatAnalytics(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Analytics entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "AnlName"));	
	}

}
