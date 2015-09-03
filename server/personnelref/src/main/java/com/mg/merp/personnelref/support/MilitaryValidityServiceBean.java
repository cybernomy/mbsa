/*
 * MilitaryValidityServiceBean.java
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
import com.mg.merp.personnelref.MilitaryValidityServiceLocal;
import com.mg.merp.personnelref.model.MilitaryValidity;

/**
 * Реализация бизнес-компонента "Категории годности к воинской службе" 
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: MilitaryValidityServiceBean.java,v 1.4 2007/01/16 09:28:17 sharapov Exp $
 */
@Stateless(name="merp/personnelref/MilitaryValidityService") //$NON-NLS-1$
public class MilitaryValidityServiceBean extends AbstractPOJODataBusinessObjectServiceBean<MilitaryValidity, Integer> implements MilitaryValidityServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, MilitaryValidity entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
	}


}
