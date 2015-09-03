/*
 * PositionFillKindServiceBean.java
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
import com.mg.merp.personnelref.PositionFillKindServiceLocal;
import com.mg.merp.personnelref.model.PositionFillKind;

/**
 * Бизнес-компонент "Виды исполнения должностей" 
 * 
 * @author leonova
 * @version $Id: PositionFillKindServiceBean.java,v 1.4 2006/09/06 12:49:54 leonova Exp $
 */
@Stateless(name="merp/personnelref/PositionFillKindService")
public class PositionFillKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<PositionFillKind, Integer> implements PositionFillKindServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, PositionFillKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "KCode"));
	}



}
