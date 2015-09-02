/*
 * EntityCustomFieldsStorage.java
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
package com.mg.framework.api.metadata;

import java.util.Map;

/**
 * ����������� �������� ���������������� �����
 * 
 * @author Oleg V. Safonov
 * @version $Id: EntityCustomFieldsStorage.java,v 1.1 2007/01/25 15:17:13 safonov Exp $
 */
public interface EntityCustomFieldsStorage {

	/**
	 * �������� ��������
	 * 
	 * @param name	��� ��������
	 * @return	��������
	 */
	Object getValue(String name);
	
	/**
	 * ���������� ��������
	 * 
	 * @param name	��� ��������
	 * @param value	��������
	 */
	void setValue(String name, Object value);
	
	/**
	 * �������� �������� ���� ���������
	 * 
	 * @return	��������
	 */
	Map<String, Object> getValues();
	
	/**
	 * ���������� ��������
	 * 
	 * @param values	��������
	 */
	void setValues(Map<String, Object> values);
	
}
