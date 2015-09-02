/*
 * WarehouseTypeServiceBean.java
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
package com.mg.merp.warehouse.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.warehouse.WarehouseTypeServiceLocal;
import com.mg.merp.warehouse.model.WarehouseType;

/**
 * Реализация бизнес-компонента "Тип склада"
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: WarehouseTypeServiceBean.java,v 1.1 2007/09/17 12:46:54 alikaev Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseTypeService") //$NON-NLS-1$
public class WarehouseTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<WarehouseType, Integer> implements WarehouseTypeServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, WarehouseType entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
	}

}
