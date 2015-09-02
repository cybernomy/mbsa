/*
 * SQLExceptionTranslatorManager.java
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
package com.mg.framework.api.jdbc;

import java.sql.SQLException;

import com.mg.framework.api.DataAccessException;

/**
 * �������� ������������ SQL �� � ���������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslatorManager.java,v 1.1 2006/11/17 14:13:38 safonov Exp $
 */
public interface SQLExceptionTranslatorManager {

	/**
	 * ����������� ����������, ���������� ������ � ������ ������������, �.�. ��������
	 * �������������� ��������� ����������� ������� ���� ��������� ����� ���������� �����������������
	 * 
	 * @param translator	���������
	 */
	void registerTranslator(SQLExceptionTranslator translator);
	
	/**
	 * �������� ���������� �� ������ ������������
	 * 
	 * @param translator
	 */
	void unregisterTranslator(SQLExceptionTranslator translator);
	
	/**
	 * ��������� SQL �� � ���������������� ����������
	 * 
	 * @param sqlException	SQL ��
	 * @return	���������������� ����������
	 */
	DataAccessException translate(SQLException sqlException);
	
}
