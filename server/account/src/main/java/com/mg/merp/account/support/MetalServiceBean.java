/*
 * MetalServiceBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.account.MetalServiceLocal;
import com.mg.merp.account.model.Metal;

/**
 * Бизнес-компонент "Драгоценные металы" 
 * 
 * @author leonova
 * @version $Id: MetalServiceBean.java,v 1.7 2008/03/04 07:02:00 alikaev Exp $
 */
@Stateless(name="merp/account/MetalService") //$NON-NLS-1$
public class MetalServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Metal, String> implements MetalServiceLocal {

	private void adjustMetal(Metal entity) {
		entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(T)
	 */
	@Override
	protected void onCreate(Metal entity) {
		adjustMetal(entity);
		if (findByCriteria(Restrictions.eq("UpCode", entity.getUpCode())).size() > 0) //$NON-NLS-1$
			throw new BusinessException(StringUtils.format(Messages.getInstance().getMessage(Messages.NOT_UNIQUE_ATTRIBUTE), "'Код'")); //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(T)
	 */
	@Override
	protected void onStore(Metal entity) {
		adjustMetal(entity);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final Metal entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "Name"));	 //$NON-NLS-1$
	}

}
