/*
 * SecuritySystem.java
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
package com.mg.framework.api.security;

import java.security.AccessControlException;
import java.security.Permission;
import java.util.Locale;

import javax.security.auth.login.LoginException;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.UserProfile;

/**
 * ������� ������������
 * 
 * @author Oleg V. Safonov
 * @version $Id: SecuritySystem.java,v 1.9 2007/10/19 06:31:34 safonov Exp $
 */
public interface SecuritySystem {
	
	/**
	 * ������������� ������ "��������������"
	 */
	final int ADMIN_GROUP = 1;
	
	/**
	 * �������� ����� �� ������
	 * 
	 * @param perm	������
	 * @throws AccessControlException ���� ������ ����������
	 */
	void checkPermission(final Permission perm) throws AccessControlException;
	
	/**
	 * ��������������
	 * 
	 * @param login		�����
	 * @param password	������
	 * @throws LoginException	� ������ ��������� �������
	 */
	void authenticate(String login, String password) throws LoginException;
	
	/**
	 * ��������� ����������
	 *
	 */
	void logout();
	
	/**
	 * ��������� ���������������� �������
	 * 
	 * @param login			�����
	 * @param locale		������ ������������
	 * @param defaultLocale	������ �� ���������, ����� ������������ � ������ ���� ���������� ������������ ������ ������������
	 * @return	�������
	 */
	UserProfile loadUserProfile(String login, Locale locale, Locale defaultLocale);
	
	/**
	 * ����������� ������������
	 * 
	 * @param name
	 * @param password
	 * @param smartCard
	 * @return
	 * @throws ApplicationException
	 * @throws InvalidUserNameOrPassword
	 * 
	 * @deprecated
	 */
	@Deprecated
	Integer login(String name, String password, boolean smartCard) throws ApplicationException, InvalidUserNameOrPassword;

	/**
	 * �������� ������������ ������� ������������ �������������
	 * 
	 * @param userId
	 * @return
	 * @throws ApplicationException
	 * 
	 * @deprecated
	 */
	@Deprecated
	String[] getModuleAccess(Integer userId) throws ApplicationException;
	
	/**
	 * ��������� ���������� �� ������� ��������
	 * 
	 * @param treeIdentifier	������������� ��������
	 * @param parentIdentifier	������������� �������� ��������
	 * @param treePart		��� ��������
	 */
	void grantTreePermission(int treeIdentifier, int parentIdentifier, int treePart);
	
	/**
	 * ������������� ��������� ���������� �� ������� ��������
	 * 
	 * @param treeIdentifier	������������� ��������
	 * @param treePart			��� ��������
	 * @param className			����� ������ ��������
	 * @param parentProperty	��� �������� ������ �� ��������
	 */
	void setupTreePermission(int treeIdentifier, int treePart, String className, String parentProperty);
	
	/**
	 * �������� ������������
	 * 
	 * @param userName	��� ������������
	 * @param passw		������
	 */
	void createUser(String userName, String passw);
	
	/**
	 * �������� ������������
	 * 
	 * @param userName	��� ������������
	 */
	void deleteUser(String userName);
	
	/**
	 * ��������� ������ ������������
	 * 
	 * @param userName	��� ������������
	 * @param passw		����� ������
	 */
	void changePassword(String userName, String passw);
	
	/**
	 * ������� ����� ������������ �������
	 * 
	 * @param auditType	��� �������
	 * @param beanName	�������� �������
	 * @param userName	��� ������������
	 * @param details	�������� �������
	 */
	void addAuditEvent(SecurityAuditType auditType, String beanName, String userName, String details);
	
}
