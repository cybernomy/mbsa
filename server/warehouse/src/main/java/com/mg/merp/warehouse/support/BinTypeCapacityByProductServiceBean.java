/*
 * BinTypeCapacityByProductServiceBean.java
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

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.warehouse.BinTypeCapacityByProductServiceLocal;
import com.mg.merp.warehouse.model.BinTypeCapacityByProduct;

/**
 * Бизнес-компонент "Емкости по продукции" 
 * 
 * @author leonova
 * @version $Id: BinTypeCapacityByProductServiceBean.java,v 1.5 2007/08/13 10:43:42 safonov Exp $
 */
@Stateless(name="merp/warehouse/BinTypeCapacityByProductService")
@PermitAll
public class BinTypeCapacityByProductServiceBean extends AbstractPOJODataBusinessObjectServiceBean<BinTypeCapacityByProduct, Integer> implements BinTypeCapacityByProductServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, BinTypeCapacityByProduct entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Catalog"));
		context.addRule(new MandatoryStringAttribute(entity, "Measure"));
	}

}
