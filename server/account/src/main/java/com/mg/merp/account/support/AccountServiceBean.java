/*
 * AccountServiceBean.java
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

package com.mg.merp.account.support;

import javax.ejb.Stateless;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.AttributeMap;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.AccountServiceLocal;
import com.mg.merp.account.model.AccPlan;
import com.mg.merp.account.model.AccType;
import com.mg.merp.account.model.AnlForm;

/**
 * Бизнес-компонент "План счетов" 
 * 
 * @author leonova
 * @version $Id: AccountServiceBean.java,v 1.6 2008/03/13 06:20:53 alikaev Exp $
 */
@Stateless(name="merp/account/AccountService")
public class AccountServiceBean extends AbstractPOJODataBusinessObjectServiceBean<AccPlan, Integer> implements AccountServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
	 */
	@Override
	protected void onInitialize(AccPlan entity) {
		entity.setAnlForm(AnlForm.NONE);
		entity.setAccType(AccType.ACTIVE);
	}

	private void adjustAccount(AccPlan entity) {
		entity.setUpAcc(StringUtils.toUpperCase(entity.getAcc()));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
	 */
	@Override
	protected void onCreate(AccPlan entity) {
		adjustAccount(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
	 */
	@Override
	protected void onStore(AccPlan entity) {
		adjustAccount(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final AccPlan entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Acc"));
		context.addRule(new MandatoryStringAttribute(entity, "AccName"));
		context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Acc") {
			@Override
			protected void doValidate(ValidationContext context) {
				if (OrmTemplate.getInstance().findUniqueByCriteria(AccPlan.class, Restrictions.eq("UpAcc", StringUtils.toUpperCase(entity.getAcc())), Restrictions.ne("Id", entity.getId())) != null)
					context.getStatus().error(this);
			}
		});
	}

	/**
 	 * @ejb.interface-method view-type = "local"
 	 * 
 	 * @param code
 	 * @return
 	 * @throws ApplicationException
 	 */
 	public AttributeMap getFullRecord(String code) throws ApplicationException {
		return null;//((AccountDomainImpl) getDomain()).getFullRecord(code);
	}
 	
}
