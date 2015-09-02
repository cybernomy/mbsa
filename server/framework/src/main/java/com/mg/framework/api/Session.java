/*
 * Session.java
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
package com.mg.framework.api;


/**
 * ������� ������, ��� ��������� ������� ������ ����������� <code>ServerUtils.getCurrentSession()</code>
 * 
 * @author Oleg V. Safonov
 * @version $Id: Session.java,v 1.6 2008/08/28 13:13:05 safonov Exp $
 */
public interface Session {
	
	/**
	 * ��� ������� ��������� �������� ��������� � ������
	 * 
	 */
	enum AttributeScopeType {
		
		/**
		 * �������� ����������, �������� �� ���������� ������� ����� ������
		 */
		APPLICATION,
		
		/**
		 * ������� �������, �������� �� ���������� ���������� �������
		 */
		CONVERSATION,
		
		/**
		 * �������� ����������, �������� �� ���������� ������� ����� ����������
		 */
		TRANSACTION
	}
	
	/**
	 * �������� ���������� ������
	 * 
	 * @return	����������
	 * 
	 * @deprecated
	 */
	@Deprecated
	int getHandle();

	/**
	 * �������� ����������
	 * 
	 * @return	����������
	 * 
	 * @deprecated
	 */
	@Deprecated
	int getNativeImpl();
	
	/**
	 * ��������� ������, �� �������� � ���������� ����
	 *
	 */
	void destroy();
	
	/**
	 * �������� ������� ����������
	 * 
	 * @return	������� ����������
	 * 
	 * @see WorkingConnection
	 */
	WorkingConnection getWorkingConnection();
	
	/**
	 * ���������� ������� ����������
	 * 
	 * @param connection	������� ����������
	 * 
	 * @deprecated
	 */
	@Deprecated
	void setWorkingConnection(WorkingConnection connection);
	
	/**
	 * �������� ������� ������� � ������� ����� ������������
	 * 
	 * @return	������� �������
	 */
	SystemTenant getSystemTenant();
	
	/**
	 * ��������� �������� ������, �������� ����� ������ ���� ���������� ��� ���� ������
	 * ���������� �� ������� ���������
	 * 
	 * @param key	���� ��������
	 * @param value	�������� ��������
	 * @param scope	������� ��������� ��������
	 */
	void setAttribute(String key, Object value, AttributeScopeType scope);
	
	/**
	 * ��������� �������� ������ � �������� ��������� <code>AttributeScopeType.APPLICATION</code>,
	 * ������������ ������ <code>setAttribute(key, value, AttributeScopeType.APPLICATION)</code>
	 * 
	 * @param key	���� ��������
	 * @param value	�������� ��������
	 * 
	 * @see #setAttribute(String, Object, AttributeScopeType)
	 */
	void setAttribute(String key, Object value);
	
	/**
	 * ��������� �������� ������
	 * 
	 * @param key	���� ��������
	 * @return		�������� �������� ��� <code>null</code> ���� �������� �� �������
	 */
	Object getAttribute(String key);
	
	/**
	 * ������� ������� �� ������
	 * 
	 * @param key
	 */
	void removeAttribute(String key);

	/**
	 * ���������� ������� ����������
	 */
	void stopApplication();

	/**
	 * �������� ������� �������������� (�����������) ������ ������
	 * 
	 * @return	���� <code>true</code> �� �������� ������ � �������������
	 */
	boolean isInteractive();

}
