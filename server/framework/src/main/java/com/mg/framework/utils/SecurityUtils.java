/*
 * SecurityUtils.java
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
package com.mg.framework.utils;

import java.security.AccessControlException;
import java.security.Permission;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import com.mg.framework.api.security.SecurityAuditType;
import com.mg.framework.service.SecuritySystemLocator;

/**
 * ������� ������������
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class SecurityUtils {

	/**
	 * �������� ����������
	 * 
	 * @param perm	����������
	 * @throws AccessControlException ���� ��� ����
	 */
	public static void checkPermission(Permission perm) throws AccessControlException {
		SecuritySystemLocator.locate().checkPermission(perm);
	}

	/**
	 * �������� ����������
	 * 
	 * @param perm	����������
	 * @return	<code>false</code> ���� ��� ����
	 */
	public static boolean tryCheckPermission(final Permission perm) {
		try {
			checkPermission(perm);
			return true;
		} catch (AccessControlException e) {
			return false;
		}
	}
	
	/**
	 * �������������� ������������
	 * 
	 * @param login	����� ������������
	 * @param password	������
	 * @throws LoginException	���� ��������� ������ ��������������
	 */
	public static void authenticate(String login, String password) throws LoginException {
		SecuritySystemLocator.locate().authenticate(login, password);
	}
	
	/**
	 * ���������� �������� ������������
	 */
	public static void logout() {
		SecuritySystemLocator.locate().logout();
	}
	
	/**
	 * �������� ������� � ����� ������������
	 * 
	 * @param auditType	��� �������
	 * @param beanName	�������� �������
	 * @param userName	��� ������������
	 * @param details	�������� �������
	 */
	public static void addAuditEvent(SecurityAuditType auditType, String beanName, String userName, String details) {
		SecuritySystemLocator.locate().addAuditEvent(auditType, beanName, userName, details);
	}

	/**
	 * �������� ������� � ����� ������������
	 * 
	 * @param auditType	��� �������
	 * @param beanName	�������� �������
	 * @param details	�������� �������
	 */
	public static void addAuditEvent(SecurityAuditType auditType, String beanName, String details) {
		addAuditEvent(auditType, beanName, ServerUtils.getCurrentSession().getWorkingConnection().getUserProfile().getUserName(), details);
	}

	/**
	 * �������� ���������� �����������
	 * 
	 * @param login	��� ������������
	 * @param password	������ ������������
	 * @param tenant	������� ��� �����������
	 * @return	��������� �����������
	 */
	public static Map<String, Object> createAuthenticateParams(String login, String password, String tenant) {
		Map<String, Object> loginParams = new HashMap<String, Object>();
		loginParams.put("loginParam", login);
		loginParams.put("passwParam", password);
		return loginParams;
	}

}
