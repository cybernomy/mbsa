/*
 * FeeRefParamServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.FeeRefParamServiceLocal;
import com.mg.merp.salary.model.FeeRefParam;

/**
 * Бизнес-компонент "Параметры начислений / удержаний" 
 * 
 * @author leonova
 * @version $Id: FeeRefParamServiceBean.java,v 1.3 2006/08/31 11:37:58 leonova Exp $
 */
@Stateless(name="merp/salary/FeeRefParamService")
public class FeeRefParamServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FeeRefParam, Integer> implements FeeRefParamServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, FeeRefParam entity) {
		context.addRule(new MandatoryStringAttribute(entity, "PCode"));
		context.addRule(new MandatoryStringAttribute(entity, "PName"));		
	}


}
