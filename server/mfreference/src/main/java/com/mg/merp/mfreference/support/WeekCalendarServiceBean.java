/*
 * WeekcalendarServiceBean.java
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

package com.mg.merp.mfreference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.mfreference.WeekCalendarServiceLocal;
import com.mg.merp.mfreference.model.WeekCalendar;

/**
 * Бизнес-компонент "Недельный календарь" 
 * 
 * @author leonova
 * @version $Id: WeekCalendarServiceBean.java,v 1.4 2007/07/30 10:24:41 safonov Exp $
 */
@Stateless(name="merp/mfreference/WeekCalendarService")
public class WeekCalendarServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WeekCalendar, Integer> implements WeekCalendarServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, WeekCalendar entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
	}

}
