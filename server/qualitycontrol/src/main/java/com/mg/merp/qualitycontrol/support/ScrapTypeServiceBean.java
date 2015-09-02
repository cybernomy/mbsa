/*
 * ScrapTypeServiceBean.java
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

package com.mg.merp.qualitycontrol.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.qualitycontrol.ScrapTypeServiceLocal;
import com.mg.merp.qualitycontrol.model.ScrapType;

/**
 * Бизнес-компонент "Типы потерь" 
 * 
 * @author leonova
 * @version $Id: ScrapTypeServiceBean.java,v 1.4 2007/08/24 08:47:51 alikaev Exp $
 */
@Stateless(name="merp/qualitycontrol/ScrapTypeService") //$NON-NLS-1$
public class ScrapTypeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ScrapType, Integer> implements ScrapTypeServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(ScrapType entity) {
		adjustScrapType(entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(ScrapType entity) {
		adjustScrapType(entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, ScrapType entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
	}
	
	/**
	 * функция заполнения поля "Name" 
	 * 
	 * @param entity
	 */
	protected void adjustScrapType(ScrapType entity) {
		if (entity.getName() == null)
			entity.setName(StringUtils.EMPTY_STRING);
	}
	
}
