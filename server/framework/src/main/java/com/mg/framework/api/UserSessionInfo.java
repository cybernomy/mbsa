/**
 * UserSessionInfo.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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

import java.io.Serializable;
import java.util.Date;

/**
 * ���������� � ������ ������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserSessionInfo.java,v 1.1 2008/07/14 13:52:20 safonov Exp $
 */
public interface UserSessionInfo extends Serializable {

	/**
	 * �������� ��� ������������
	 * 
	 * @return	��� ������������
	 */
	String getUserName();
	
	/**
	 * ������� ���������� ������
	 * 
	 * @return	<code>true</code> ���� �������
	 */
	boolean isActive();
	
	/**
	 * ������� �������� ������������
	 * 
	 * @return	<code>true</code> ���� ������ �������� ���������� � ������������ ���������� ������ ����������
	 */
	boolean isCurrentPrincipal();

	/**
	 * �������� ����� �������� ������
	 * 
	 * @return	����� ��������
	 */
	Date getCreationTime();
	
	/**
	 * �������� ����� ���������� �������
	 * 
	 * @return	����� ���������� �������
	 */
	Date getLastAccessedTime();
	
	/**
	 * �������� ����� �������������� �������
	 * 
	 * @return	����� �������������� �������
	 */
	Date getUsedServerTime();
	
	/**
	 * �������� ����� �������
	 * 
	 * @return	����� �������
	 */
	Date getIdleTime();
	
	/**
	 * �������� ����� ���������� ����� ����� HTTP ������
	 * 
	 * @return	���������� ����� �����
	 */
	Date getTTL();
	
	/**
	 * �������� ������������� HTTP ������
	 * 
	 * @return	������������� HTTP ������
	 */
	String getHttpSessionId();

	/**
	 * �������� ����� ���������� ����� � �������� ���� ������������ ������
	 * 
	 * @return	����� ���������� �����
	 */
	String getRemoteHost();

	/**
	 * �������� �������������� ����� �� ��������� �������
	 * 
	 * @return	�������������� ����� � ms
	 */
	int getLastUsedTime();

	/**
	 * �������� ����������� �������������� ����� �� ��������
	 * 
	 * @return	����������� �������������� ����� � ms
	 */
	int getMinUsedTime();

	/**
	 * �������� ����������� �������������� ����� �� ��������
	 * 
	 * @return	����������� �������������� ����� � ms
	 */
	int getMaxUsedTime();

	/**
	 * �������� ���������� ��������� � ������
	 * 
	 * @return	���������� ���������
	 */
	int getHits();

	/**
	 * �������� ������ ������
	 * 
	 * @return	������ ������
	 */
	long getSize();

	/**
	 * �������� ������ ���������� �������
	 * 
	 * @return	������ ���������� �������
	 */
	long getLastRequestSize();

	/**
	 * �������� ������ ���������� ������
	 * 
	 * @return	������ ���������� ������
	 */
	long getLastResponseSize();

	/**
	 * �������� ����� ������ ��������
	 * 
	 * @return	����� ������ ��������
	 */
	long getTotalRequestSize();

	/**
	 * �������� ����� ������ �������
	 * 
	 * @return	����� ������ �������
	 */
	long getTotalResponseSize();

}
