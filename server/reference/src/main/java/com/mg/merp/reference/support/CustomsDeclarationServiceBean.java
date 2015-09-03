/*
 * CustomsDeclarationServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.CustomsDeclarationServiceLocal;
import com.mg.merp.reference.model.CustomsDeclaration;

/**
 * Реализация бизнес-компонента "Грузовые таможенные декларации"
 * 
 * @author Artem V. Sharapov
 * @version $Id: CustomsDeclarationServiceBean.java,v 1.1 2007/01/17 11:50:58 sharapov Exp $
 */
@Stateless(name="merp/reference/CustomsDeclarationService") //$NON-NLS-1$
public class CustomsDeclarationServiceBean extends
AbstractPOJODataBusinessObjectServiceBean<CustomsDeclaration, Integer> implements CustomsDeclarationServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, final CustomsDeclaration entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Number")); //$NON-NLS-1$
		context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Number") { //$NON-NLS-1$
			@Override
			protected void doValidate(ValidationContext context) {
				if (OrmTemplate.getInstance().findUniqueByCriteria(CustomsDeclaration.class, Restrictions.eq("Number", entity.getNumber()), Restrictions.ne("Id", entity.getId() == null ? 0 : entity.getId())) != null) //$NON-NLS-1$ //$NON-NLS-2$
					context.getStatus().error(this);

			}
		});
	}

}


