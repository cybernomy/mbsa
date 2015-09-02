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
 * �������� �������� ������
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
	 * �������� ������� �������� � ��������
	 * 
	 * @param rule
	 */
	public void addRule(Rule rule) {
		rules.add(rule);
	}

	/**
	 * ��������� �������� ���� ������ ������������ � ������ ���������
	 * 
	 * @throws ValidationException ���� ���� ��������� � �������� ������
	 */
	public void validate() throws ValidationException {
		for (Rule rule : rules)
			rule.validate(this);
		
		if (status.isError())
			throw new ValidationException(status);
	}
	
	/**
	 * �������� ������ �������� ������
	 * 
	 * @return Returns the status.
	 */
	public Status getStatus() {
		return status;
	}

}
