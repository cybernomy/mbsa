/*
 * ReplacedFeeServiceBean.java
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

package com.mg.merp.salary.support;

import javax.ejb.Stateless;

import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.framework.support.validator.MandatoryAttribute;
import com.mg.merp.salary.ReplacedFeeServiceLocal;
import com.mg.merp.salary.model.ReplacedFee;

/**
 * Бизнес-компонент "Вытесняемые н/у" 
 * 
 * @author leonova
 * @version $Id: ReplacedFeeServiceBean.java,v 1.4 2006/08/31 11:37:58 leonova Exp $
 */
@Stateless(name="merp/salary/ReplacedFeeService")
public class ReplacedFeeServiceBean extends AbstractPOJODataBusinessObjectServiceBean<ReplacedFee, Integer> implements ReplacedFeeServiceLocal {

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean#onValidate(com.mg.framework.api.validator.ValidationContext, T)
	 */
	@Override
	protected void onValidate(ValidationContext context, ReplacedFee entity) {
		context.addRule(new MandatoryAttribute(entity, "ReplacedFee"));
	}



}
