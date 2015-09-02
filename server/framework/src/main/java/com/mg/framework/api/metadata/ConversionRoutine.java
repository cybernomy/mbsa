/*
 * ConversionRoutine.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.metadata;

import java.util.Map;

/**
 * ����������� �������� ��������� ��� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConversionRoutine.java,v 1.3 2006/07/04 15:49:51 safonov Exp $
 */
public interface ConversionRoutine<S, U> {

	/**
     * ����������� �� �������� ����������������� ���������� � ��������
     * �������������� ��������
	 * 
	 * @param value	�������� ��������� � ���������������� ����������
	 * @return	�������� �������������� ��������
	 */
	S inputConverse(U value);
	
	/**
     * ����������� �� �������� ��������������� �������� � ��������
     * ��� ����������������� ����������
	 * 
	 * @param value	�������� �������������� ��������
	 * @return	�������� ��������� � ���������������� ����������
	 */
	U outputConverse(S value);
	
	/**
	 * �������� �������� ��������� �������
	 * 
	 * @return	������ ������������ ��������� ��� ������� �� ������
	 * 
	 * @see #setImportContext(Map)
	 */
	String[] getImportContextDefinition();
	
	/**
	 * ������������� �������� �������, �������� ������������� �� ������ �����������
	 * ����� ������� ��� ������� ��������������
	 * 
	 * @param context	��������
	 */
	void setImportContext(Map<String, Object> context);

}
