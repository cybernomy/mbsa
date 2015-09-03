/*
 * RollKindServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryStringAttribute;
import com.mg.merp.salary.RollKindServiceLocal;
import com.mg.merp.salary.model.RollKind;

/**
 * Бизнес-компонент "Типы расчетных ведомостей" 
 * 
 * @author leonova
 * @version $Id: RollKindServiceBean.java,v 1.3 2006/08/28 12:44:53 leonova Exp $
 */
@Stateless(name="merp/salary/RollKindService")
public class RollKindServiceBean extends AbstractPOJODataBusinessObjectServiceBean<RollKind, Integer> implements RollKindServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, RollKind entity) {
		context.addRule(new MandatoryStringAttribute(entity, "Name"));
	}


}
