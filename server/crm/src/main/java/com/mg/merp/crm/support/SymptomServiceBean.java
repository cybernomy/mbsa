/*
 * SymptomServiceBean.java
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

package com.mg.merp.crm.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.crm.SymptomServiceLocal;
import com.mg.merp.crm.model.Symptom;

/**
 * Бизнес-компонент "Симптомы" 
 * 
 * @author leonova
 * @version $Id: SymptomServiceBean.java,v 1.3 2006/09/06 05:24:25 leonova Exp $
 */
@Stateless(name="merp/crm/SymptomService")
public class SymptomServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Symptom, Integer> implements SymptomServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Symptom entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name"));
		context.addRule(new MandatoryAttribute(entity, "Creator"));		
	}



}
