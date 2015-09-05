/*
 * AbstractRule.java
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
package com.mg.framework.generic.validator;

import com.mg.framework.api.validator.Rule;
import com.mg.framework.api.validator.ValidationContext;
import com.mg.framework.generic.MessageSourceAccessor;

/**
 * Абстрактное правило контроля данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractRule.java,v 1.1 2006/08/14 14:08:09 safonov Exp $
 */
public abstract class AbstractRule implements Rule {
	private String message;
	private Object toValidate;

	/**
	 * создает правило
	 * 
	 * @param message		сообщение
	 * @param toValidate	объект контроля
	 */
	public AbstractRule(String message, Object toValidate) {
		this.message = message;
		this.toValidate = toValidate;
	}
	
	/**
	 * создает правило
	 * 
	 * @param messageSource	источник сообщения
	 * @param code			код сообщения
	 * @param toValidate	объект контроля
	 */
	public AbstractRule(MessageSourceAccessor messageSource, String code, Object toValidate) {
		this.message = messageSource.getMessage(code);
	}
	
	/**
	 * выполнение контроля данных, необходимо переопределить в реализациях конкретных правил
	 * <p> Например:
	 * <blockquote><pre>
	 *   Object toValidate = toValidate();
	 *   if (toValidate == null)
	 *   	context.getStatus().error(this);
	 * </pre></blockquote>
	 * 
	 * @param context	контекст контроля данных
	 */
	protected abstract void doValidate(ValidationContext context);
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.validator.Rule#validate(com.mg.framework.api.validator.ValidationContext)
	 */
	public void validate(ValidationContext context) {
		doValidate(context);
	}

	/**
	 * получить объект для контроля данных
	 * 
	 * @return	объект контроля
	 */
	protected Object toValidate() {
		return toValidate;
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.validator.Rule#getMessage()
	 */
	public String getMessage() {
		return message;
	}

}
