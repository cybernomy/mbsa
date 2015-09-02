/*
 * ItemKindServiceBean.java
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

package com.mg.merp.lbschedule.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.lbschedule.ItemKindServiceLocal;
import com.mg.merp.lbschedule.model.ItemKind;

/**
 * Бизнес-компонент "Типы пунктов ГИО" 
 * 
 * @author leonova
 * @version $Id: ItemKindServiceBean.java,v 1.3 2006/09/15 04:30:27 leonova Exp $
 */
@Stateless(name="merp/lbschedule/ItemKindService")
public class ItemKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ItemKind, Integer> implements ItemKindServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, ItemKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
	}


}
