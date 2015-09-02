/*
 * Status.java
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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.mg.framework.support.Messages;

/**
 * ������ �������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: Status.java,v 1.1 2006/08/14 14:07:42 safonov Exp $
 */
public class Status {
	private List<ValidationError> errors = new ArrayList<ValidationError>();

	private void addError(ValidationError error) {
		errors.add(error);
	}
	
	/**
	 * ���������� ������ ��������
	 * 
	 * @param rule		�������
	 * @param message	���������
	 */
	public void error(Rule rule, String message) {
		addError(new ValidationError(message));
	}

	/**
	 * ���������� ������ ��������
	 * 
	 * @param rule	�������
	 */
	public void error(Rule rule) {
		addError(new ValidationError(rule.getMessage()));
	}

	/**
	 * �������� ������� ��������
	 * 
	 * @return	<code>true</code> ���� ������ �������� ������ �������� ������
	 */
	public boolean isError() {
		return errors.size() != 0;
	}

	/**
	 * �������� ��������� �������� ������, ������������ ��� ���������� �������������
	 * ������ ������������ � ������ �������
	 * 
	 * @return	���������
	 */
	public String getMessage() {
		StringWriter buf = new StringWriter();
		PrintWriter out = new PrintWriter(buf);
		out.println(Messages.getInstance().getMessage(Messages.VALIDATION_ERROR));
		for (ValidationError error : errors)
			out.println("\t " + error.getMessage());
		return buf.toString();
	}
}
