/*
 * MpsServiceBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 */

package com.mg.merp.planning.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.planning.MPSServiceLocal;
import com.mg.merp.planning.model.Mps;

/**
 * Реализация бизнес-компонента "ОПП" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: MPSServiceBean.java,v 1.4 2007/01/16 11:15:07 sharapov Exp $
 */
@Stateless(name="merp/planning/MPSService") //$NON-NLS-1$
public class MPSServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Mps, Integer> implements MPSServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Mps entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "WeekCal")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "PlanningLevel")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "Description")); //$NON-NLS-1$
	}


}
