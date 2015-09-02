/*
 * PersistentObject.java
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
package com.mg.framework.api.orm;

import java.util.Collection;

import com.mg.framework.api.*;

/**
 * ������ ��������. ������������ ������ � ��������� �� �������� JavaBean.
 * 
 * @author Oleg V. Safonov
 * $Id: PersistentObject.java,v 1.5 2007/08/16 13:26:40 safonov Exp $
 */
public interface PersistentObject {
	
	/**
	 * �������� ��������� ����
	 * 
	 * @return	��������� ����
	 */
	Object getPrimaryKey();
	
	/**
	 * �������� ������������ ��������
	 * 
	 * @return
	 */
	String getEntityName();
	
	/**
	 * ����� �������� �������� ��������
	 * 
	 * @param name	������������ ��������
	 * @return		�������� ��������
	 */
	Object getAttribute(String name);
	
	/**
	 * ���������� �������� �������� ��������
	 * 
	 * @param name	������������ ��������
	 * @param value	�������� ��������
	 */
	void setAttribute(String name, Object value);
	
	/**
	 * ����� �������� ��������� ��������
	 * 
	 * @param keyOfAttributes	������ ������������ ���������
	 * @return	����� ������������/�������� ���������
	 */
	AttributeMap getAttributes(Collection<String> keyOfAttributes);
	
	/**
	 * ����� �������� ���� ��������� ��������
	 * 
	 * @return	����� ������������/�������� ���������
	 */
	AttributeMap getAllAttributes();
	
	/**
	 * ���������� �������� �������� ��������
	 * 
	 * @param values	����� ������������/�������� ���������
	 */
	void setAttributes(AttributeMap values);

	/**
	 * ����������� ������� ��������, ���� <code>attributes</code> �������� ��������, �� ����� �����������
	 * �������������� �������� ��������� ��������
	 * 
	 * @param attributes	����� ������������ ������� ������� � ��������, ����� ���� <code>null</code>
	 * @return	����� ������� �������� ��� <code>null</code> ���� ����������� �� ������� ��� �� �����������
	 */
	PersistentObject cloneEntity(AttributeMap attributes);
	
	/**
	 * ����������� ������� �������� � ��������
	 * 
	 * @param name	��� ��������
	 * @return		<code>true</code> ���� �������� ����� ������� � ����� ������
	 */
	boolean hasAttribute(String name);

}
