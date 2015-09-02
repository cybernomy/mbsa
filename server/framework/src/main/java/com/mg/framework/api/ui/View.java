/*
 * View.java
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


import com.mg.framework.api.Logger;

/**
 * ���
 * 
 * @author Oleg V. Safonov
 * @version $Id: View.java,v 1.10 2008/01/10 08:41:53 safonov Exp $
 */
public interface View {
	
	/**
	 * ��� ����� ����������������� ����������
	 * 
	 * @return	���
	 */
	String getName();
	
	/**
	 * �������� ����� ����
	 * 
	 * @param modal	���� <code>true</code>, �� ����� ����� ���������
	 */
	void show(boolean modal);
	
	/**
	 * ������� ����� ����
	 *
	 */
	void close();
	
	/**
	 * ������� ��������� �����
	 * 
	 * @return	<code>true</code> ���� ����� �������� � ����������
	 */
	boolean isVisible();
	
	/**
	 * ����� �������� ���������� ����� � ������
	 *
	 */
	void flushForm();
	
	/**
	 * ���������� �������� ���������� ����� �� ������
	 *
	 */
	void flushModel();

	/**
	 * ���������� ��������� ����� ����
	 * 
	 * @param title	���������
	 */
	void setTitle(String title);
	
	/**
	 * �������� ��������� ����� ����
	 * 
	 * @return	�������
	 */
	String getTitle();
	
	/**
	 * �������� �� ����� ������������ ������� ����� ����
	 * 
	 * @param name	��� ��������
	 * @return	������������ �������
	 */
	Widget getWidget(String name);
	
	/**
	 * �������� ������� "����" ������� ����
	 * 
	 * @return	����
	 */
	Window getWindow();
	
	/**
	 * �������� ������ ���� ������������ ��������� ����� ����
	 * 
	 * @return	������ ������������ ���������
	 */
	Widget[] getWidgets();
	
	/**
	 * �������� ���������� ������� ����
	 * 
	 * @return	���������
	 */
	Controller getController();
	
	/**
	 * ������� ���������� �������, ������������ ��� ���������� �����, �� ��������
	 * � ���������� ����
	 * 
	 * @param handlerName	��� �����������
	 * @param args			��������� ������
	 * @return				��������� ���������� �����������, ������ <code>null</code>
	 * @throws Throwable	� ������ ����� ��
	 */
	Object invokeHandler(String handlerName, Object ... args) throws Throwable;
	
	/**
	 * �������� <code>Logger</code>
	 * 
	 * @return
	 */
	Logger getLogger();
	
	/**
	 * ���������� ����
	 *
	 */
	void dispose();
	
	/**
	 * "�����������" ��������� � ���� ����������
	 *
	 */
	void pack();

	/**
	 * �������� ������������ ������ ������� ������ ��� ����
	 * 
	 * @return	������������ ������ ��� <code>null</code> ���� �� ����������
	 */
	String getHelpTopic();

	/**
	 * �������� ������� ����������������� ���������� ����� ������� ����
	 * 
	 * @return	�������, ����� ���� <code>null</code> ���� ���������� �������� �������
	 */
	UIProfile getUIProfile();

}
