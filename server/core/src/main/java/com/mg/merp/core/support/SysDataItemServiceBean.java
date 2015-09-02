/**
 * SysDataItemServiceBean.java
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
package com.mg.merp.core.support;

import javax.ejb.Stateless;

import com.mg.framework.api.metadata.DataItemKind;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.core.SysDataItemServiceLocal;
import com.mg.merp.core.model.SysDataItem;

/**
 * –еализаци€ бизнес-компонента "Ёлементы данных системы"
 * 
 * @author Oleg V. Safonov
 * @version $Id: SysDataItemServiceBean.java,v 1.1 2008/03/03 12:57:32 safonov Exp $
 */
@Stateless(name="merp/core/SysDataItemService")
public class SysDataItemServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<SysDataItem, Integer> implements
		SysDataItemServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onInitialize(com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onInitialize(SysDataItem entity) {
		entity.setKind(DataItemKind.DOMAIN);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, com.mg.framework.api.orm.PersistentObject)
	 */
	@Override
	protected void onValidate(ValidationContext context, SysDataItem entity) {
		context.addRule(new MandatoryAttribute(entity, "ApplicationLayer")); //$NON-NLS-1$
		context.addRule(new MandatoryStringAttribute(entity, "Name")); //$NON-NLS-1$		
	}

}
