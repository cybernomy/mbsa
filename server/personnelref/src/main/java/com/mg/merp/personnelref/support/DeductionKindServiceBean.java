/*
 * DeductionKindServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.DeductionKindServiceLocal;
import com.mg.merp.personnelref.model.DeductionClass;
import com.mg.merp.personnelref.model.DeductionKind;

/**
 * Бизнес-компонент "Виды вычетов, предоставляемых налогоплательщикам" 
 * 
 * @author leonova
 * @version $Id: DeductionKindServiceBean.java,v 1.4 2006/09/29 08:43:21 leonova Exp $
 */
@Stateless(name="merp/personnelref/DeductionKindService")
public class DeductionKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<DeductionKind, Integer> implements DeductionKindServiceLocal {

	@Override
	protected void onInitialize(DeductionKind entity) {
		entity.setDeductionClass(DeductionClass.NONE);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, DeductionKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "DCode"));
		context.addRule(new MandatoryStringAttribute(entity, "DName"));
		context.addRule(new MandatoryAttribute(entity, "BeginDate"));
	}



}
