/*
 * InvLocationServiceBean.java
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
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.InvLocationServiceLocal;
import com.mg.merp.account.model.InvLocation;

/**
 * Бизнес-компонент "Местонахождение объектов ОС" 
 * 
 * @author leonova
 * @version $Id: InvLocationServiceBean.java,v 1.3 2006/08/24 06:44:08 leonova Exp $
 */
@Stateless(name="merp/account/InvLocationService")
public class InvLocationServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InvLocation, Integer> implements InvLocationServiceLocal {

	private void adjustInvLocation(InvLocation entity) {
		entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
	 */
	@Override
	protected void onCreate(InvLocation entity) {
		adjustInvLocation(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
	 */
	@Override
	protected void onStore(InvLocation entity) {
		adjustInvLocation(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, InvLocation entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "IlName"));		
	}



}
