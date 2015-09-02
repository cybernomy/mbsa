/*
 * ServiceKindServiceBean.java
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

package com.mg.merp.personnelref.support;

import java.util.List;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.personnelref.ServiceKindServiceLocal;
import com.mg.merp.personnelref.model.ServiceKind;

/**
 * Бизнес-компонент "Виды стажа" 
 * 
 * @author leonova
 * @version $Id: ServiceKindServiceBean.java,v 1.5 2007/08/16 14:12:16 safonov Exp $
 */
@Stateless(name="merp/personnelref/ServiceKindService")
public class ServiceKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ServiceKind, Integer> implements ServiceKindServiceLocal {


	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, ServiceKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "KCode"));
		context.addRule(new MandatoryStringAttribute(entity, "KName"));		
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#doMove(java.util.List, java.lang.Object)
	 */
	@Override
	public boolean doMove(List<Integer> primaryKeys, Object targetEntity) {
		boolean result = false;
		for (Integer key : primaryKeys) {
			ServiceKind entity = load(key);
			ServiceKind targetKind = (ServiceKind) targetEntity;
			if (entity.getParentId() != null && entity.getId() > targetKind.getId() && entity.getParentId() != targetKind.getId()) {
				entity.setParentId(targetKind.getId());
				result = true;
			}
		}
		return result;
	}

}
