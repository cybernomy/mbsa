/*
 * InvProductionServiceBean.java
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
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.account.InvProductionServiceLocal;
import com.mg.merp.account.model.InvProduction;

/**
 * Бизнес-компонент "Продукция" 
 * 
 * @author leonova
 * @version $Id: InvProductionServiceBean.java,v 1.4 2006/10/11 06:15:42 leonova Exp $
 */
@Stateless(name="merp/account/InvProductionService")
public class InvProductionServiceBean extends AbstractPOJODataBusinessObjectServiceBean<InvProduction, Integer> implements InvProductionServiceLocal {

	@Override
	protected void onValidate(ValidationContext context, InvProduction entity) {
		context.addRule(new MandatoryAttribute(entity, "QMonth"));
		context.addRule(new MandatoryAttribute(entity, "QYear"));		
	}

}
