/*
 * StaffListPositionFillServiceBean.java
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

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.personnelref.StaffListPositionFillServiceLocal;
import com.mg.merp.personnelref.model.PositionFill;

/**
 * Реализация бизнес-компонента "Занимаимые должности в штатном расписании" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: StaffListPositionFillServiceBean.java,v 1.5 2007/08/22 06:52:00 sharapov Exp $
 */
@Stateless(name="merp/personnelref/StaffListPositionFillService")
public class StaffListPositionFillServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PositionFill, Integer> implements StaffListPositionFillServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, PositionFill entity) {
		context.addRule(new DateIntervalRule(entity));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(PositionFill entity) {
		adjust(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(PositionFill entity) {
		adjust(entity);
	}
	
	private void adjust(PositionFill entity) {
		PersonnelrefUtils.checkDateInterval(entity);
	}

}
