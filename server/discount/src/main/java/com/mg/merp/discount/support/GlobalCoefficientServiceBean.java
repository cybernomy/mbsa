/*
 * GlobalcoefficientServiceBean.java
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

package com.mg.merp.discount.support;

import javax.ejb.Stateless;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.discount.GlobalCoefficientServiceLocal;
import com.mg.merp.discount.model.Coefficient;

/**
 * Бизнес-компонент "Коэффициенты каталога" 
 * 
 * @author leonova
 * @version $Id: GlobalCoefficientServiceBean.java,v 1.5 2008/05/22 09:44:35 alikaev Exp $
 */
@Stateless(name="merp/discount/GlobalCoefficientService")
public class GlobalCoefficientServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Coefficient, Integer> implements GlobalCoefficientServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, Coefficient entity) {
		if ((entity.getCatalog() != null && entity.getCatalogFolder() != null) || (entity.getCatalog() == null && entity.getCatalogFolder() == null))
			throw new BusinessException(Messages.getInstance().getMessage(Messages.SEARCHED_FILD_CATALOG_OR_CATALOGFOLDER));
	}

}
