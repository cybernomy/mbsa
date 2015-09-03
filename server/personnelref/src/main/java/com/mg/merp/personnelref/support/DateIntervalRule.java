/*
 * DateIntervalRule.java
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
package com.mg.merp.personnelref.support;

import java.util.Date;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.AbstractRule;

/**
 * Правило проверки интервала дат. 
 * Дата начала интервала не может позже даты окончания интервала. 
 * 
 * @author Artem V. Sharapov
 * @version $Id: DateIntervalRule.java,v 1.1 2007/08/22 06:38:18 sharapov Exp $
 */
public class DateIntervalRule extends AbstractRule {

	private static final String BEGIN_DATE = "BeginDate"; //$NON-NLS-1$
	private static final String END_DATE = "EndDate"; //$NON-NLS-1$


	public DateIntervalRule(PersistentObject entity) {
		super(Messages.getInstance().getMessage(Messages.CALC_PERIOD_END_DATE_AFTER_BEGIN_DATE), entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.validator.AbstractRule#doValidate(com.mg.framework.api.validator.ValidationContext)
	 */
	@Override
	protected void doValidate(ValidationContext context) {
		PersistentObject entity = (PersistentObject) toValidate();
		if(entity.hasAttribute(BEGIN_DATE) && entity.hasAttribute(END_DATE)) {
			Date beginDate = (Date) entity.getAttribute(BEGIN_DATE);
			Date endDate = (Date) entity.getAttribute(END_DATE);
			
			if(beginDate != null && endDate != null) {
				if(beginDate.compareTo(endDate) > 0)
					context.getStatus().error(this);
			}
		}
	}

}
