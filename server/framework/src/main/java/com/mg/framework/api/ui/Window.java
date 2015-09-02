/*
 * Window.java
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
package com.mg.framework.api.ui;

import com.mg.framework.api.ui.widget.Button;

/**
 * ����, �������� �������-��������� ���������� ������ ��������
 * 
 * @author Oleg V. Safonov
 * @version $Id: Window.java,v 1.4 2008/01/10 08:40:14 safonov Exp $
 */
public interface Window {

	/**
	 * ������� ��������� ����
	 */
	final static String TITLE = "title";
	
	/**
	 * ������� ����������� ������ ���� � ��������
	 */
	final static String WIDTH = "width";
	
	/**
	 * ������� ����������� ������ ���� � ��������
	 */
	final static String HEIGHT = "height";
	
	/**
	 * ��������� � ���� �������
	 * 
	 * @param widget	�������
	 */
	void add(Widget widget);
	
	/**
	 * ��������� ���������
	 * 
	 * @param title	���������
	 */
	void setTitle(String title);
	
	/**
	 * �������� ����
	 *
	 */
	void show();

	/**
	 * ������� ����
	 *
	 */
	void close();

	/**
	 * ��������� ����
	 *
	 */
	void dispose();
	
	/**
	 * "�����������" ��������� � ���� ����������
	 *
	 */
	void pack();

	/**
	 * ���������� ������� �� ��������� ��� ������ �����, ��� ������� <code>ENTER</code>
	 * �� ����� ����� ������ ���������� ��������
	 * 
	 * @param button	������� �� ��������� ��� <code>null</code> ��� ������
	 */
	void setDefaultButton(Button button);
	
	/**
	 * �������� ������� �� ��������� ������ �����
	 * 
	 * @return	������� ��� <code>null</code> ���� �� ����������
	 */
	Button getDefaultButton();

}
