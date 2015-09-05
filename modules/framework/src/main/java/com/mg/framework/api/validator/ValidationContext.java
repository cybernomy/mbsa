/*
 * ValidationContext.java
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
package com.mg.framework.api.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Контекст контроля данных
 * 
 * @author Oleg V. Safonov
 * @version $Id: ValidationContext.java,v 1.1 2006/08/14 14:07:42 safonov Exp $
 */
public class ValidationContext {
	private List<Rule> rules = new ArrayList<Rule>();
	private Status status;
	
	public ValidationContext() {
		status = new Status();
	}

	/**
	 * добавить правило контроля в контекст
	 * 
	 * @param rule
	 */
	public void addRule(Rule rule) {
		rules.add(rule);
	}

	/**
	 * выполнить контроль всех правил содержащихся в данном контексте
	 * 
	 * @throws ValidationException если есть нарушения в контроле данных
	 */
	public void validate() throws ValidationException {
		for (Rule rule : rules)
			rule.validate(this);
		
		if (status.isError())
			throw new ValidationException(status);
	}
	
	/**
	 * получить статус контроля данных
	 * 
	 * @return Returns the status.
	 */
	public Status getStatus() {
		return status;
	}

}
