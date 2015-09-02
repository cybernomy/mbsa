/**
 * CustomActionDescriptor.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

/**
 * ������������� ��������, ������������ ��� �������� ����� ������ �������������� ����������������
 * � ����������� ������ � ��������� ����������������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: CustomActionDescriptor.java,v 1.1 2007/11/15 08:35:11 safonov Exp $
 */
public interface CustomActionDescriptor {

	/**
	 * ��� ��������
	 * 
	 * @return	���
	 */
	String getName();
	
	/**
	 * ���������
	 * 
	 * @return	���������
	 */
	String getCaption();
	
	/**
	 * ���������
	 * 
	 * @return	���������
	 */
	String getToolTip();
	
	/**
	 * �����������
	 * 
	 * @return	��� ������� ����������� �����������
	 */
	String getIcon();
	
	/**
	 * ���������� ������ �������� ������
	 * 
	 * @return
	 */
	String getKeyStroke();
	
	/**
	 * ������� ������������� � ����������� ����
	 * 
	 * @return	<code>true</code> ���� ������������ � ����������� ����
	 */
	boolean isPopupMenu();
	
	/**
	 * ������� ������������� � ������ ������������
	 * 
	 * @return	<code>true</code> ���� ������������ � ������ ������������
	 */
	boolean isToolBar();
	
	/**
	 * ������� ���������� ����������� �������� ����������������� ���������� �� ��������
	 * ���� ������� ��������
	 * 
	 * @return	<code>true</code> ���� ���������� �������� ����������
	 */
	boolean isForceRefresh();
	
	/**
	 * ������� �������� ����������� ����� ��������� ������ ��������
	 * 
	 * @return	<code>true</code> ���� ��������� �����������
	 */
	boolean isSeparatorBefore();
	
	/**
	 * ������� �������� ����������� ����� ��������� ������ ��������
	 * 
	 * @return	<code>true</code> ���� ��������� �����������
	 */
	boolean isSeparatorAfter();
	
}
