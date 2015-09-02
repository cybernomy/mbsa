/*
 * MRPVersionControlServiceBean.java
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

package com.mg.merp.planning.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.planning.MRPVersionControlServiceLocal;
import com.mg.merp.planning.model.MrpVersionControl;

/**
 * Реализация бизнес-компонента "ППМ" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: MRPVersionControlServiceBean.java,v 1.4 2007/01/16 11:06:35 sharapov Exp $
 */
@Stateless(name="merp/planning/MRPVersionControlService") //$NON-NLS-1$
public class MRPVersionControlServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MrpVersionControl, Integer> implements MRPVersionControlServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, MrpVersionControl entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "Description")); //$NON-NLS-1$
	}

}
