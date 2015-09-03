/*
 * SpecFeatureServiceBean.java
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

package com.mg.merp.finance.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.merp.finance.PeriodServiceLocal;
import com.mg.merp.finance.SpecFeatureServiceLocal;
import com.mg.merp.finance.model.FinOperation;
import com.mg.merp.finance.model.Specification;

/**
 * Бизнес-компонент "Спецификация финансовых операций - признаки" 
 * 
 * @author leonova
 * @version $Id: SpecFeatureServiceBean.java,v 1.7 2007/11/27 11:55:33 alikaev Exp $
 */
@Stateless(name="merp/finance/SpecFeatureService")
public class SpecFeatureServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Specification, Integer> implements SpecFeatureServiceLocal {

	private void checkPeriod(Specification spec) {
		((PeriodServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/finance/Period")).checkPeriod(getPersistentManager().find(FinOperation.class, spec.getFinOper().getId()).getKeepDate());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(Specification entity) {
		FinOperation fo = entity.getFinOper();
		if (fo != null)
			entity.setPlanned(fo.getPlanned());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(Specification entity) {
		checkPeriod(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onErase(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onErase(Specification entity) {
		checkPeriod(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(Specification entity) {
		checkPeriod(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, final Specification entity) {
		context.addRule(new Rule() {

			public String getMessage() {
				return Messages.getInstance().getMessage(Messages.FEATURE_NOT_NULL);
			}

			public void validate(ValidationContext context) {
				if (entity.getSrcAcc() == null)
					context.getStatus().error(this);
			}

		});
		context.addRule(new Rule() {

			public String getMessage() {
				return Messages.getInstance().getMessage(Messages.PARENT_NOT_NULL);
			}

			public void validate(ValidationContext context) {
				if (entity.getParent() == null)
					context.getStatus().error(this);
			}

		});
	}

}
