/*
 * WorkingConnection.java
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
package com.mg.framework.api;

import java.util.Map;

import javax.security.auth.login.LoginException;

import com.mg.framework.api.security.InvalidUserNameOrPassword;

/**
 * ������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: WorkingConnection.java,v 1.8 2008/04/09 14:34:54 safonov Exp $
 */
public interface WorkingConnection {
	/**
	 * ��� ��������� ������������
	 */
	final static String LOGIN_PARAM = "loginParam";
	/**
	 * ��� ��������� ������
	 */
	final static String PASSW_PARAM = "passwParam";
	/**
	 * ��� ��������� ������ � ������� ����������� ������������
	 */
	final static String LOCALE_PARAM = "localeParam";
	/**
	 * ��� ��������� ������ �� ���������
	 */
	final static String DEFAULT_LOCALE_PARAM = "defaultLocaleParam";
	/**
	 * ��� ��������� ������� ������������� �����-�����
	 */
	final static String SMARTCARD_PARAM = "smartcardParam";
	/**
	 * ��� ��������� ������� ��� ��������� ������
	 */
	final static String PRINTER_PARAM = "printerParam";
	/**
	 * Interbase
	 * @deprecated
	 */
	@Deprecated
	final static int DBMS_INTERBASE = 0;
	/**
	 * Oracle
	 * @deprecated
	 */
	@Deprecated
	final static int DBMS_ORACLE = 1;
	/**
	 * MSSQL
	 * @deprecated
	 */
	@Deprecated
	final static int DBMS_MSSQL = 2;
	/**
	 * Firebird
	 * @deprecated
	 */
	@Deprecated
	final static int DBMS_FIREBIRD = 3;

	/**
	 * ��������� �������������� ������������
	 * 
	 * @param name			����� ������������
	 * @param password		������
	 * @param locale		������
	 * @param defaultLocale	������ �� ���������
	 * @param smartCard		������������ �����-�����
	 * @throws InvalidUserNameOrPassword
	 * @throws LoginException
	 */
	void login(Map<String, Object> params) throws InvalidUserNameOrPassword, LoginException;
	
	/**
	 * ��������� ���������� ������������
	 *
	 */
	void logout();
	
	/**
	 * �������� ��� ����
	 * 
	 * @return	��� ����
	 * @see #DBMS_FIREBIRD
	 * @see #DBMS_INTERBASE
	 * @see #DBMS_MSSQL
	 * @see #DBMS_ORACLE
	 * 
	 * @deprecated
	 */
	@Deprecated
	int DBMSEngine();
	
	/**
	 * �������� ���������������� ������� ����������
	 * 
	 * @return
	 */
	UserProfile getUserProfile();
	
}
