/*
 * FeatureServiceBean.java
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

package com.mg.merp.finance.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.FeatureServiceLocal;
import com.mg.merp.finance.model.Account;

/**
 * Бизнес-компонент "Признаки финансового учета" 
 * 
 * @author leonova
 * @version $Id: FeatureServiceBean.java,v 1.4 2006/10/26 07:05:14 leonova Exp $
 */
@Stateless(name="merp/finance/FeatureService")
public class FeatureServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Account, Integer> implements FeatureServiceLocal {

	@Override
	protected void onInitialize(Account entity) {
		entity.setKind((short)1);
	}

	private void adjustFeature(Account entity) {
		entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
	 */
	@Override
	protected void onCreate(Account entity) {
		adjustFeature(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
	 */
	@Override
	protected void onStore(Account entity) {
		adjustFeature(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, Account entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "AccName"));		
	}

}
