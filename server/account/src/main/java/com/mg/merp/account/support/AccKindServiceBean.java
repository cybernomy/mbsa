/*
 * AccKindServiceBean.java
 *
 * Copyright (c) 1998 - 2004 BusinessTechnology, Ltd.
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

package com.mg.merp.account.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.account.AccKindServiceLocal;
import com.mg.merp.account.model.AccKind;

/**
 * Бизнес-компонент "Виды учета" 
 * 
 * @author leonova
 * @version $Id: AccKindServiceBean.java,v 1.3 2006/08/24 06:44:08 leonova Exp $
 */
@Stateless(name="merp/account/AccKindService")
public class AccKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<AccKind, Integer> implements AccKindServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, AccKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name"));
	}


}
