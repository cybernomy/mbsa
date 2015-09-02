/*
 * SessionControl.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api;

/**
 * �������� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SessionControl.java,v 1.3 2006/09/22 08:42:17 safonov Exp $
 */
public interface SessionControl {
	
	/**
	 * ���������� ������� ������, ������� ����� ����� ������������ � �������, ���� ������� <code>null</code>
	 * �� ���������� ����� ����� 
	 * 
	 * @param session	������
	 */
	void setCurrentSession(Session session);
	
	/**
	 * �������� ������� ������
	 * 
	 * @return	������� ������ ��� <code>null</code> ���� ������� ����� �� ������������ � �������
	 */
	Session getCurrentSession();
	
	/**
	 * �������� �������� ������
	 *
	 */
	void clear();

}
