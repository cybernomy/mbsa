/*
 * JoblaborServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.manufacture.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.manufacture.JobLaborServiceLocal;
import com.mg.merp.manufacture.generic.AbstractJobResource;
import com.mg.merp.manufacture.model.JobLabor;
import com.mg.merp.mfreference.model.ManufactureConfig;
import com.mg.merp.mfreference.support.ConfigurationHelper;

/**
 * Бизнес-компонент "Рабочая сила в ЗНП" 
 * 
 * @author leonova
 * @version $Id: JobLaborServiceBean.java,v 1.6 2009/03/05 12:32:15 safonov Exp $
 */
@Stateless(name="merp/manufacture/JobLaborService")
public class JobLaborServiceBean extends AbstractJobResource<JobLabor, Integer> implements JobLaborServiceLocal{

	/* (non-Javadoc)
	 * @see com.mg.merp.manufacture.generic.AbstractJobResource#onInitialize(com.mg.merp.manufacture.model.JobRouteResource)
	 */
	@Override
	protected void onInitialize(JobLabor entity) {
		super.onInitialize(entity);
		ManufactureConfig config = ConfigurationHelper.getConfiguration();
		entity.setRunTimeLbrUm(config.getMainTimeUM());
		entity.setLbrRateCurrency(config.getBaseCurrency());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, JobLabor entity) {
		context.addRule(new MandatoryAttribute(entity, "RunTimeLbrUm"));
		context.addRule(new MandatoryAttribute(entity, "LbrRateCurrency"));
		context.addRule(new MandatoryAttribute(entity, "LbrOhRateCurrency"));
	}

}
