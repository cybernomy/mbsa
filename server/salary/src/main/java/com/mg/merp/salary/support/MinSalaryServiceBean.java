/*
 * MinSalaryServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.salary.MinSalaryServiceLocal;
import com.mg.merp.salary.model.MinSalary;

/**
 * Бизнес-компонент "Размеры минимальных заработных плат" 
 * 
 * @author leonova
 * @version $Id: MinSalaryServiceBean.java,v 1.3 2006/08/28 12:44:53 leonova Exp $
 */
@Stateless(name="merp/salary/MinSalaryService")
public class MinSalaryServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MinSalary, Integer> implements MinSalaryServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, MinSalary entity) {
		context.addRule(new MandatoryAttribute(entity, "BeginDate"));
		context.addRule(new MandatoryAttribute(entity, "MinSalary"));		
	}
	
}
