/*
 * TimeKindServiceBean.java
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

package com.mg.merp.table.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.table.TimeKindServiceLocal;
import com.mg.merp.table.model.TimeKind;

/**
 * Бизнес-компонент "Типы времени" 
 * 
 * @author leonova
 * @version $Id: TimeKindServiceBean.java,v 1.5 2008/01/31 09:31:59 safonov Exp $
 */
@Stateless(name="merp/table/TimeKindService")
public class TimeKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<TimeKind, Integer> implements TimeKindServiceLocal {

 	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, TimeKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Code"));
		if (entity.getIsWholeDay()) {
			context.addRule(new MandatoryStringAttribute(entity, "MnemoCode"));	
		}
	}

}
