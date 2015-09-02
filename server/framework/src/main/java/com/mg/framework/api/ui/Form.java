/*
 * Form.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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

/**
 * ����� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: Form.java,v 1.5 2008/01/10 08:41:53 safonov Exp $
 */
public interface Form {
	/**
	 * ����������� �������� ����� ��������� �� ���������
	 */
	final static String DEFAULT_MAINTENANCE_NAME = "defaultMaintenance";
	
	/**
	 * ����������� �������� ����� ������ �� ���������
	 */
	final static String DEFAULT_BROWSE_NAME = "defaultBrowse";
	
	/**
	 * ������� ������������ ������� �� ��������� ����������������� ����������
	 */
	final static String ACTION_HANDLER_PREFIX = "onAction";
	
	/**
	 * �������� ����������� �� ������� �������� �����
	 */
	final static String CLOSE_HANDLER = "close";
	
	/**
	 * ������ ����� (�����)
	 *
	 */
	void run();
	
	/**
	 * ������ ����� (�����) � ���������� �����������
	 * 
	 * @param modal	�����������, ���� <code>true</code> �� ����� ����� �������� ��� ��������� ������
	 */
	void run(boolean modal);
	
	/**
	 * ������� �����
	 *
	 */
	void close();
	
	/**
	 * ���������� ��������� �����
	 * 
	 * @param title	���������
	 */
	void setTitle(String title);
	
	/**
	 * �������� ��������� �����
	 * 
	 * @return ���������
	 */
	String getTitle();
	
	/**
	 * ���������������� ��������� �� ������� �������� �����
	 * 
	 * @param listener	���������
	 */
	void addCloseActionListener(FormActionListener listener);
	
	/**
	 * ������� ��������� �� ������� �������� �����
	 * 
	 * @param listener	���������
	 */
	void removeCloseActionListener(FormActionListener listener);
	
	/**
	 * ���������� �����, ������ ����� ����������� ������������� ��� ��������
	 *
	 */
	void dispose();
}
