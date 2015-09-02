/*
 * PositiveAttribute.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.framework.support.validator;

import java.math.BigDecimal;

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;

/**
 * ѕравило гарантирующее, что атрибут сущности будет больше нул€
 * 
 * @author leonova
 * @version $Id: PositiveAttribute.java,v 1.1 2006/10/07 10:52:14 leonova Exp $
 */
public class PositiveAttribute extends EntityBeanRule {
	
	/**
	 * создает правило
	 * 
	 * @param entity		объект-сущность контрол€
	 * @param propertyName	наименование атрибута контрол€
	 */
	public PositiveAttribute(PersistentObject entity, String propertyName) {
		super(Messages.getInstance().getMessage(Messages.POSITIVE_ATTRIBUTE_VALIDATOR), entity, propertyName);
	}
	
	/* (non-Javadoc)
	 * @see com.mg.framework.generic.validator.AbstractRule#doValidate(com.mg.framework.api.validator.ValidationContext)
	 */
	@Override
	protected void doValidate(ValidationContext context) {
		Object toValidate = toValidate();
		//проверка на null, если BigDecimal, то проверим условие больше нул€
		if (toValidate == null)
			context.getStatus().error(this);
		 else if (toValidate instanceof BigDecimal && BigDecimal.ZERO.compareTo((BigDecimal) toValidate) >= 0)
			   context.getStatus().error(this);
	}
}
