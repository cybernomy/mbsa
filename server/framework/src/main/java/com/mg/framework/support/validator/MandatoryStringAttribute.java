/*
 * MandatoryStringAttribute.java
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

import com.mg.framework.api.orm.PersistentObject;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.validator.EntityBeanRule;
import com.mg.framework.support.Messages;

/**
 * ѕравило гарантирующее, что текстовый атрибут сущности не будет <code>null</code> и не будет пустым
 * 
 * @author Oleg V. Safonov
 * @version $Id: MandatoryStringAttribute.java,v 1.1 2006/08/14 14:08:44 safonov Exp $
 */
public class MandatoryStringAttribute extends EntityBeanRule {

	/**
	 * создает правило
	 * 
	 * @param entity		объект-сущность контрол€
	 * @param propertyName	наименование атрибута контрол€
	 */
	public MandatoryStringAttribute(PersistentObject entity, String propertyName) {
		super(Messages.getInstance().getMessage(Messages.MANDATORY_VALIDATOR), entity, propertyName);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.generic.validator.AbstractRule#doValidate(com.mg.framework.api.validator.ValidationContext)
	 */
	@Override
	protected void doValidate(ValidationContext context) {
		Object toValidate = toValidate();
		//проверка на null, если строка, то проверим на пустую строку
		if (toValidate == null)
			context.getStatus().error(this);
		else if (toValidate instanceof String && "".equals(toValidate))
			context.getStatus().error(this);
	}

}
