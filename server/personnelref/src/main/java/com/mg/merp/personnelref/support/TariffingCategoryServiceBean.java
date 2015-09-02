/*
 * TariffingCategoryServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */

package com.mg.merp.personnelref.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.TariffingCategoryServiceLocal;
import com.mg.merp.personnelref.model.TariffingCategory;

/**
 * Бизнес-компонент "Категории тарификации должности" 
 * 
 * @author leonova
 * @version $Id: TariffingCategoryServiceBean.java,v 1.4 2006/09/04 13:02:21 leonova Exp $
 */
@Stateless(name="merp/personnelref/TariffingCategoryService")
public class TariffingCategoryServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TariffingCategory, Integer> implements TariffingCategoryServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, TariffingCategory entity) {
		context.addRule(new MandatoryStringAttribute(entity, "CCode"));
	}


}
