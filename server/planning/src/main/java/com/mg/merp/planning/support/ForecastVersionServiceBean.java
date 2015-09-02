/*
 * ForecastVersionServiceBean.java
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

package com.mg.merp.planning.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.planning.ForecastVersionServiceLocal;
import com.mg.merp.planning.model.ForecastVersion;

/**
 * ������-��������� "������ ���������" 
 * 
 * @author leonova
 * @version $Id: ForecastVersionServiceBean.java,v 1.4 2006/10/16 07:41:44 leonova Exp $
 */
@Stateless(name="merp/planning/ForecastVersionService")
public class ForecastVersionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ForecastVersion, Integer> implements ForecastVersionServiceLocal {

	@Override
	protected void onInitialize(ForecastVersion entity) {
		entity.setDescription("");
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, ForecastVersion entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));				
	}

 

}
