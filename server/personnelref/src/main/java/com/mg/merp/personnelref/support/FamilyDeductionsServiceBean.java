/*
 * FamilyDeductionsServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.personnelref.FamilyDeductionsServiceLocal;
import com.mg.merp.personnelref.model.FamilyDeductions;

/**
 * Реализация бизнес-компонента "Вычеты на членов семьи" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FamilyDeductionsServiceBean.java,v 1.4 2007/08/22 06:42:20 sharapov Exp $
 */
@Stateless(name="merp/personnelref/FamilyDeductionsService") //$NON-NLS-1$
public class FamilyDeductionsServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FamilyDeductions, Integer> implements FamilyDeductionsServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, FamilyDeductions entity) {
		context.addRule(new MandatoryAttribute(entity, "FamilyMember")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "DeductionKind")); //$NON-NLS-1$
		context.addRule(new DateIntervalRule(entity));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(FamilyDeductions entity) {
		adjust(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(FamilyDeductions entity) {
		adjust(entity);
	}
	
	private void adjust(FamilyDeductions entity) {
		PersonnelrefUtils.checkDateInterval(entity);
	}
	
}
