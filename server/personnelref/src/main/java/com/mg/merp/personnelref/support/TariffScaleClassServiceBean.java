/*
 * TariffScaleClassServiceBean.java
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
import com.mg.merp.personnelref.TariffScaleClassServiceLocal;
import com.mg.merp.personnelref.model.TariffScaleClass;

/**
 * Бизнес-компонент "Разряды тарифные сеток" 
 * 
 * @author leonova
 * @version $Id: TariffScaleClassServiceBean.java,v 1.3 2006/09/04 13:02:21 leonova Exp $
 */
@Stateless(name="merp/personnelref/TariffScaleClassService")
public class TariffScaleClassServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TariffScaleClass, Integer> implements TariffScaleClassServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, TariffScaleClass entity) {
		context.addRule(new MandatoryStringAttribute(entity, "ClassNumber"));
	}



}
