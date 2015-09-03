/*
 * StreetServiceBean.java
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
import com.mg.merp.personnelref.StreetServiceLocal;
import com.mg.merp.personnelref.model.Street;

/**
 * Бизнес-компонент "Улицы" 
 * 
 * @author leonova
 * @version $Id: StreetServiceBean.java,v 1.3 2006/09/06 12:49:54 leonova Exp $
 */
@Stateless(name="merp/personnelref/StreetService")
public class StreetServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Street, Integer> implements StreetServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Street entity) {
		context.addRule(new MandatoryStringAttribute(entity, "SName"));
	}



}
