/*
 * GNSCodeServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.GNSCodeServiceLocal;
import com.mg.merp.salary.model.GnsCode;

/**
 * Бизнес-компонент "Коды МНС" 
 * 
 * @author leonova
 * @version $Id: GNSCodeServiceBean.java,v 1.3 2006/08/31 11:37:58 leonova Exp $
 */
@Stateless(name="merp/salary/GNSCodeService")
public class GNSCodeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<GnsCode, Integer> implements GNSCodeServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, GnsCode entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
	}



}
