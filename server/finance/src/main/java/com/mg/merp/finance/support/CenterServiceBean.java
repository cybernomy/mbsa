/*
 * CenterServiceBean.java
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

package com.mg.merp.finance.support;

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.finance.CenterServiceLocal;
import com.mg.merp.finance.model.Center;
import com.mg.merp.finance.model.CenterKind;

/**
 * Бизнес-компонент "Центры финансового учета" 
 * 
 * @author leonova
 * @version $Id: CenterServiceBean.java,v 1.5 2007/08/16 14:06:05 safonov Exp $
 */
@Stateless(name="merp/finance/CenterService")
public class CenterServiceBean extends AbstractPOJODataBusinessObjectServiceBean<Center, Integer> implements CenterServiceLocal {

	private void adjustCenter(Center entity) {
		entity.setUpCode(StringUtils.toUpperCase(entity.getCode()));
	}
	
	@Override
	protected void onCreate(Center entity) {	
		adjustCenter(entity);
	}


	@Override
	protected void onStore(Center entity) {	
		adjustCenter(entity);
	}


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(T)
	 */
	@Override
	protected void onInitialize(Center entity) {
		entity.setKind(CenterKind.CFU);
	}


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, final Center entity) {
		context.addRule(new MandatoryAttribute(entity, "Code"));
		context.addRule(new MandatoryStringAttribute(entity, "Name"));	
		context.addRule(new EntityBeanRule(Messages.getInstance().getMessage(Messages.UNIQUE_VALIDATOR), entity, "Code") {
			@Override
			protected void doValidate(ValidationContext context) {
				if (OrmTemplate.getInstance().findUniqueByCriteria(Center.class, Restrictions.eq("UpCode", StringUtils.toUpperCase(entity.getCode())), Restrictions.ne("Id", entity.getId())) != null)
					context.getStatus().error(this);
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(java.util.List, java.lang.Object)
	 */
	@Override
	public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
		boolean result = false;
		for (Integer key : primaryKeys) {
			Center entity = load(key);
			Center targetCenter = (Center) targetEntity;
			if (entity.getParent() != null && entity.getId() > targetCenter.getId() && entity.getParent() != targetCenter.getId()) {
				entity.setParent(targetCenter.getId());
				result = true;
			}
		}
		return result;
	}
	
}
