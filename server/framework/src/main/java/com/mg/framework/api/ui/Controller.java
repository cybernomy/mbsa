/*
 * Controller.java
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
package com.mg.framework.api.ui;

import java.lang.reflect.InvocationTargetException;

import com.mg.framework.api.Logger;
import com.mg.framework.api.metadata.ui.FieldMetadata;

/**
 * ������� ��������� ���� ������������ ������������� ����. ������������ ���
 * �������������� � �����.
 * 
 * @author Oleg V. Safonov
 * @version $Id: Controller.java,v 1.4 2008/10/08 11:41:20 safonov Exp $
 */
public interface Controller {
	
	/**
	 * �������� �������� ���� �����������. ������������ ����� ��� ����������
	 * ��������� �����.
	 * 
	 * @param name	��� ����
	 * @return	�������� ����
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	Object getFieldValue(String name) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException;
	
	/**
	 * ���������� �������� ���� �����������. ������������ ����� ��� ����������
	 * ���� ��������� �� �������� �����.
	 * 
	 * @param name	��� ����
	 * @param value	��������������� �������� ����
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	void setFieldValue(String name, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException;
	
	/**
	 * �������� ���������� ���� �����������. ������������ ����� ��� �������������
	 * ����������� �������������� ������������ ��� �������� ��������� �����.
	 * 
	 * @param name	��� ����
	 * @return	���������� ��������� � �����
	 */
	FieldMetadata getFieldMetadata(String name);
	
	/**
	 * �������� ��� ���� �����������.
	 * 
	 * @param name	��� ����
	 * @return	��� ����
	 */
	Class<?> getFieldType(String name);

	/**
	 * ����� ����������� �����������. ������������ ����� ��� ������ �����������
	 * �������������� � �������� �����.
	 * 
	 * @param handlerName	��� �����������
	 * @param args	��������� ������ �����������
	 * @return	���������
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	Object invokeHandler(String handlerName, Object ... args) throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;

	/**
	 * ��������� ����
	 * 
	 * @param view	���
	 */
	void setView(View view);
	
	/**
	 * �������� <code>Logger</code>
	 * 
	 * @return	<code>Logger</code>
	 */
	Logger getLogger();

	/**
	 * �������� ��������� ��������� ������
	 */
	void undo();

	/**
	 * �������� ��� ��������� ������
	 */
	void undoAll();

	/**
	 * �������� ��� ��������� ������
	 */
	void resetUndo();

	/**
	 * �������� ������� ��������� ������
	 * 
	 * @return	<code>true</code> ���� ������ ����� ���������
	 */
	boolean hasModelChanges();

}
