/*
 * CalcListSectionServiceBean.java
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
import com.mg.merp.salary.CalcListSectionServiceLocal;
import com.mg.merp.salary.model.CalcListSection;

/**
 * Бизнес-компонент "Разделы расчетных листк" 
 * 
 * @author leonova
 * @version $Id: CalcListSectionServiceBean.java,v 1.3 2006/09/13 10:48:29 leonova Exp $
 */
@Stateless(name="merp/salary/CalcListSectionService")
public class CalcListSectionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CalcListSection, Integer> implements CalcListSectionServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, CalcListSection entity) {
		context.addRule(new MandatoryAttribute(entity, "CalcListSectionRef"));
	}



}
