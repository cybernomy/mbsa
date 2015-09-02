/*
 * PersonalAccountServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.salary.PersonalAccountServiceLocal;
import com.mg.merp.personnelref.model.PersonalAccount;
import com.mg.merp.personnelref.support.DateIntervalRule;
import com.mg.merp.personnelref.support.PersonnelrefUtils;

/**
 * Реализация бизнес-компонента "Лицевые счета сотрудников - плоский список" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: PersonalAccountServiceBean.java,v 1.6 2007/08/22 07:40:37 sharapov Exp $
 */
@Stateless(name="merp/salary/PersonalAccountService") //$NON-NLS-1$
public class PersonalAccountServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PersonalAccount, Integer> implements PersonalAccountServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PersonalAccount entity) {
		context.addRule(new MandatoryAttribute(entity, "Personnel")); //$NON-NLS-1$
		context.addRule(new DateIntervalRule(entity));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(PersonalAccount entity) {
		adjust(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(PersonalAccount entity) {
		adjust(entity);
	}

	private void adjust(PersonalAccount entity) {
		PersonnelrefUtils.checkDateInterval(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.salary.PersonalAccountServiceLocal#copyFee(int, int[])
	 */
	public void copyFee(int sourceFeeModelId, int[] destPersonalAccountId) throws ApplicationException {
		//TODO: implement
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.salary.PersonalAccountServiceLocal#removeFee(int, java.util.Date, java.util.Date, int[])
	 */
	public void removeFee(int feeRefId, java.util.Date beginDate, java.util.Date endDate, int[] personalAccountId) throws ApplicationException {
		//TODO: implement
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.merp.salary.PersonalAccountServiceLocal#closeFee(int, java.util.Date, int[])
	 */
	public void closeFee(int feeRefId, java.util.Date closeDate, int[] personalAccountId) throws ApplicationException {
		//TODO: implement
	}

}
