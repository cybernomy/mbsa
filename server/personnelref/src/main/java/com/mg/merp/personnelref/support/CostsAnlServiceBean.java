/*
 * CostsAnlServiceBean.java
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

import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.CostsAnlServiceLocal;
import com.mg.merp.personnelref.model.CostsAnl;

/**
 * Реализация бизнес-компонента "Аналитика состава затрат" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: CostsAnlServiceBean.java,v 1.5 2007/07/18 07:02:18 sharapov Exp $
 */
@Stateless(name="merp/personnelref/CostsAnlService") //$NON-NLS-1$
public class CostsAnlServiceBean extends AbstractPOJODataBusinessObjectServiceBean<CostsAnl, Integer> implements CostsAnlServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final CostsAnl entity) {
		context.addRule(new MandatoryStringAttribute(entity, "ACode")); //$NON-NLS-1$

		context.addRule(new Rule() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.validator.Rule#getMessage()
			 */
			public String getMessage() {
				return String.format(Messages.getInstance().getMessage(Messages.ANALITICS_LEVEL_INVALID), MIN_ANALITICS_LEVEL, MAX_ANALITICS_LEVEL);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
			 */
			public void validate(ValidationContext context) {
				if(!isAnaliticsLevelValid(entity))
					context.getStatus().error(this);
			}
		});

		context.addRule(new Rule() {

			/* (non-Javadoc)
			 * @see com.mg.framework.api.validator.Rule#getMessage()
			 */
			public String getMessage() {
				return Messages.getInstance().getMessage(Messages.PARENT_ANALITICS_INVALID);
			}

			/* (non-Javadoc)
			 * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
			 */
			public void validate(ValidationContext context) {
				if(!isParentAnaliticsValid(entity))
					context.getStatus().error(this);
			}
		});
	}

	/**
	 * Проверить корректность аналитики верхнего уровня
	 * @param costsAnl - текущая аналитика состава затрат
	 * @return true - если аналитика верхнего уровня соответсвует(т.е разница между текущим и родительским уровнем равна 1) текущей аналитике состава затрат
	 */
	protected boolean isParentAnaliticsValid(CostsAnl costsAnl) {
		CostsAnl parentCostsAnl = costsAnl.getParent();
		if(parentCostsAnl == null)
			return true;
		if(costsAnl.getId() != null && costsAnl.getId().compareTo(parentCostsAnl.getId()) == 0)
			return false;
		else 
			return costsAnl.getAnlLevel() - parentCostsAnl.getAnlLevel() == 1;
	}

	/**
	 * Проверить корректность уровня аналитики состава затрат
	 * @param costsAnl - аналитика состава затрат
	 * @return true - если уровень ноходится в допустимом диапазоне
	 */
	protected boolean isAnaliticsLevelValid(CostsAnl costsAnl) {
		if(costsAnl.getAnlLevel() >= MIN_ANALITICS_LEVEL && costsAnl.getAnlLevel() <= MAX_ANALITICS_LEVEL)
			return true;
		else
			return false;
	}

}
