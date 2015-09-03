/*
 * BankAccountServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.DataUtils;
import com.mg.merp.reference.BankAccountServiceLocal;
import com.mg.merp.reference.model.BankAccount;

/**
 * Бизнес-компонент "Банковские счета" 
 * 
 * @author leonova
 * @author Konstantin S. Alikaev
 * @version $Id: BankAccountServiceBean.java,v 1.7 2008/02/13 06:55:42 alikaev Exp $
 */
@Stateless(name="merp/reference/BankAccountService")
public class BankAccountServiceBean extends AbstractPOJODataBusinessObjectServiceBean<BankAccount, Integer> implements BankAccountServiceLocal  {
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(BankAccount entity) {
		entity.setUnid(DataUtils.generateUUID());
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final BankAccount entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Unid")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "BankAccountType")); //$NON-NLS-1$
		if (entity.getIsDefault()) {
			context.addRule(new EntityBeanRule(com.mg.merp.reference.support.Messages.getInstance().getMessage(com.mg.merp.reference.support.Messages.BANKACCOUNT_ISDEFAULT_UNIQUE), entity, "IsDefault") {  //$NON-NLS-1$
				
				/*
				 * (non-Javadoc)
				 * @see com.mg.framework.generic.validator.AbstractRule#doValidate(com.mg.framework.api.validator.ValidationContext)
				 */
				@Override
				protected void doValidate(ValidationContext context) {
					if (entity.getId() == null && !OrmTemplate.getInstance().findByCriteria(BankAccount.class, Restrictions.eq("Contractor", entity.getContractor()), Restrictions.eq("IsDefault", true)).isEmpty())
						context.getStatus().error(this);						
					if (!OrmTemplate.getInstance().findByCriteria(BankAccount.class, Restrictions.eq("Contractor", entity.getContractor()), Restrictions.eq("IsDefault", true), Restrictions.ne("Id", entity.getId())).isEmpty())  //$NON-NLS-1$ //$NON-NLS-2$
						context.getStatus().error(this);
				}
			});
		}
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onClone(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onClone(BankAccount entity) {
		entity.setUnid(DataUtils.generateUUID());
	}

}
