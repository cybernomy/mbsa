/*
 * FamilyStatusServiceBean.java
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
 *
 */

package com.mg.merp.reference.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.reference.FamilyStatusServiceLocal;
import com.mg.merp.reference.model.FamilyStatus;

/**
 * Реализация бизнес-копмонента "Семейное положение"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: FamilyStatusServiceBean.java,v 1.4 2007/01/16 06:09:40 sharapov Exp $
 */
@Stateless(name="merp/reference/FamilyStatusService")
public class FamilyStatusServiceBean extends AbstractPOJODataBusinessObjectServiceBean<FamilyStatus, Integer> implements FamilyStatusServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, FamilyStatus entity) {
		context.addRule(new MandatoryStringAttribute(entity, "FamilyStatusKind")); //$NON-NLS-1$
		context.addRule(new MandatoryAttribute(entity, "BeginDate")); //$NON-NLS-1$
	}

}
