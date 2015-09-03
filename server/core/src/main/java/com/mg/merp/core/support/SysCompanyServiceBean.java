/**
 * SysCompanyServiceBean.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.core.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.core.SysCompanyServiceLocal;
import com.mg.merp.core.model.SysCompany;

/**
 * Реализация бизнес-компонента "Балансовая единица"
 * 
 * @author Oleg V. Safonov
 * @version $Id: SysCompanyServiceBean.java,v 1.2 2007/10/01 06:28:53 safonov Exp $
 */
@Stateless(name="merp/core/SysCompanyService")
public class SysCompanyServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<SysCompany, Integer> implements
		SysCompanyServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, SysCompany entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$
	}

}
