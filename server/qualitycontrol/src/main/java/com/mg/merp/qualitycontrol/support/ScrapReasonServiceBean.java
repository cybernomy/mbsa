/*
 * ScrapReasonServiceBean.java
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
import com.mg.merp.qualitycontrol.ScrapReasonServiceLocal;
import com.mg.merp.qualitycontrol.model.ScrapReason;

/**
 * Бизнес-компонент "Причины потерь" 
 * 
 * @author leonova
 * @version $Id: ScrapReasonServiceBean.java,v 1.4 2007/08/24 09:11:28 alikaev Exp $
 */
@Stateless(name="merp/qualitycontrol/ScrapReasonService") //$NON-NLS-1$
public class ScrapReasonServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ScrapReason, Integer> implements ScrapReasonServiceLocal {

	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onCreate(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onCreate(ScrapReason entity) {
		adjustScrapReason(entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onStore(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onStore(ScrapReason entity) {
		adjustScrapReason(entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, ScrapReason entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code")); //$NON-NLS-1$
	}
	
	/**
	 * функция заполнения поля "Name"
	 * 
	 * @param entity
	 */
	protected void adjustScrapReason(ScrapReason entity){
		if (entity.getName() == null)
			entity.setName(StringUtils.EMPTY_STRING);		
	}

}
