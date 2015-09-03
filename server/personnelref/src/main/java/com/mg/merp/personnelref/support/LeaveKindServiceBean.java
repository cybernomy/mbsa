/*
 * LeaveKindServiceBean.java
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

package com.mg.merp.personnelref.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.LeaveKindServiceLocal;
import com.mg.merp.personnelref.model.LeaveKind;

/**
 * Реализация бизнес-компонента "Виды отпусков" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: LeaveKindServiceBean.java,v 1.4 2007/01/16 09:31:15 sharapov Exp $
 */
@Stateless(name="merp/personnelref/LeaveKindService") //$NON-NLS-1$
public class LeaveKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<LeaveKind, Integer> implements LeaveKindServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, LeaveKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
	}


}
