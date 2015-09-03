/*
 * PositionFillServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.PositionFillServiceLocal;
import com.mg.merp.personnelref.model.PositionFill;
import com.mg.merp.personnelref.support.DateIntervalRule;
import com.mg.merp.personnelref.support.PersonnelrefUtils;

/**
 * Реализация бизнес-компонента "Занимаемые должности" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PositionFillServiceBean.java,v 1.7 2007/08/22 07:46:09 sharapov Exp $
 */
@Stateless(name="merp/salary/PositionFillService") //$NON-NLS-1$
public class PositionFillServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PositionFill, Integer> implements PositionFillServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PositionFill entity) {
		//context.addRule(new MandatoryAttribute(entity, "Position"));
		context.addRule(new MandatoryStringAttribute(entity, "SlPositionUnique")); //$NON-NLS-1$
		context.addRule(new DateIntervalRule(entity));
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(PositionFill entity) {
		adjust(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(PositionFill entity) {
		adjust(entity);
	}

	private void adjust(PositionFill entity) {
		setPosition(entity);
		PersonnelrefUtils.checkDateInterval(entity);
	}

	private void setPosition(PositionFill entity) {
		entity.setPosition(entity.getSlPositionUnique().getPosition());
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.salary.PositionFillServiceLocal#setBasicPosition(int, int)
	 */
	public void setBasicPosition(int personalAccountId, int positionFillId) throws ApplicationException {
		//TODO: implement
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.salary.PositionFillServiceLocal#getCurrentSlPositionId(int, int, java.util.Date)
	 */
	public int getCurrentSlPositionId(int staffListId, int positionFillId, java.util.Date actualDate) throws ApplicationException {
		return 0;
	}

}
