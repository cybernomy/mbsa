/*
 * BinLocationTypeServiceBean.java
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

package com.mg.merp.warehouse.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.warehouse.BinLocationTypeServiceLocal;
import com.mg.merp.warehouse.model.BinLocationType;

/**
 * Бизнес-компонент "Типы секций хранения" 
 * 
 * @author leonova
 * @version $Id: BinLocationTypeServiceBean.java,v 1.5 2007/07/10 07:49:39 poroxnenko Exp $
 */
@Stateless(name="merp/warehouse/BinLocationTypeService")
public class BinLocationTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<BinLocationType, Integer> implements BinLocationTypeServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, BinLocationType entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "Name"));
	}

}
