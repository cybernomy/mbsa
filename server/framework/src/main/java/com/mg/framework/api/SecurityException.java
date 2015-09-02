/*
 * SecurityException.java
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

package com.mg.framework.api;


/**
 * ����� �� ����������� ��� �������� ��������� ���� �������, ������������ � ����������
 * ���� � ������ ��������� ���� �������
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
@javax.ejb.ApplicationException
public class SecurityException extends ApplicationException {
	private static final long serialVersionUID = -7742196465936049775L;

	/**
	 * ������� �� �� �������� �� �������, ��� ������� ������ ��� ������� ��������� �� ������������,
	 * �������� {@link java.lang.SecurityException SecurityException}
	 * 
	 * @param s		���������
	 * @param cause	�� �������
	 */
	public SecurityException(String s, Throwable cause) {
		super(s, cause);
	}
	
	/**
	 * ������� �� � ����������, ����� ������������ ��������������� � ���������� ���� � ������
	 * ��������� ���� �������
	 * 
	 * @param s
	 */
	public SecurityException(String s) {
		super(s);
	}	

	/**
	 * ������� ��
	 *
	 */
	public SecurityException() {
		super();
	}	

}
