/*
 * FeeModelParamServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.FeeModelParamServiceLocal;
import com.mg.merp.salary.model.FeeModelParam;

/**
 * Бизнес-компонент "Параметры в образцах начислений / удержаний" 
 * 
 * @author leonova
 * @version $Id: FeeModelParamServiceBean.java,v 1.3 2006/09/15 11:14:16 leonova Exp $
 */
@Stateless(name="merp/salary/FeeModelParamService")
public class FeeModelParamServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FeeModelParam, Integer> implements FeeModelParamServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, FeeModelParam entity) {
		context.addRule(new MandatoryStringAttribute(entity, "ParamValue"));
		context.addRule(new MandatoryAttribute(entity, "FeeRefParam"));		
	}



}
