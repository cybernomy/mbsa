/**
 * ApplicationServer.java
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

import java.util.Set;

/**
 * ������ ���������� ����������� ��������� ������� ����������
 * 
 * @author Oleg V. Safonov
 * @version $Id: ApplicationServer.java,v 1.2 2008/12/08 06:05:43 safonov Exp $
 */
public interface ApplicationServer {

	/**
	 * ��������� ���������� � ������� �������� �������������
	 * 
	 * @return	���������� � �������
	 * @throws Exception
	 */
	Set<UserSessionInfo> loadUserSessionInfos() throws Exception;

	/**
	 * ��������� ���������� � ������
	 * 
	 * @param httpSessionId	������������� ������
	 * @return	���������� � ������
	 * @throws Exception
	 */
	UserSessionInfo loadUserSessionInfo(String httpSessionId) throws Exception;

	/**
	 * ��������� ��������� �������������� � ������� ������
	 * 
	 * @param sessionIds	�������������� ������
	 * @param message	���������
	 * @throws Exception
	 */
	void sendAdminMessage(String[] sessionIds, String message) throws Exception;

	/**
	 * ��������� ��������� �������������� � ������� ������
	 * 
	 * @param sessionIds	�������������� ������ � ���� ����� ������, � �������� ����������� ������������ ������ <code>,</code>
	 * @param message	���������
	 * @throws Exception
	 */
	void sendAdminMessage(String sessionIds, String message) throws Exception;

	/**
	 * ��������� ������
	 * 
	 * @param sessionIds	�������������� ������
	 * @throws Exception
	 */
	void invalidateUserSessions(String[] sessionIds) throws Exception;

}
