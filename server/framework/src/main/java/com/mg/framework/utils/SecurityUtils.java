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
 * Утилиты безопасности
 * 
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class SecurityUtils {

	/**
	 * проверка разрешения
	 * 
	 * @param perm	разрешение
	 * @throws AccessControlException если нет прав
	 */
	public static void checkPermission(Permission perm) throws AccessControlException {
		SecuritySystemLocator.locate().checkPermission(perm);
	}

	/**
	 * проверка разрешения
	 * 
	 * @param perm	разрешение
	 * @return	<code>false</code> если нет прав
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
	 * аутентификация пользователя
	 * 
	 * @param login	логин пользователя
	 * @param password	пароль
	 * @throws LoginException	если произошка ошибка аутентификации
	 */
	public static void authenticate(String login, String password) throws LoginException {
		SecuritySystemLocator.locate().authenticate(login, password);
	}
	
	/**
	 * отключение текущего пользователя
	 */
	public static void logout() {
		SecuritySystemLocator.locate().logout();
	}
	
	/**
	 * добавить событие в аудит безопасности
	 * 
	 * @param auditType	тип события
	 * @param beanName	источник события
	 * @param userName	имя пользователя
	 * @param details	описание события
	 */
	public static void addAuditEvent(SecurityAuditType auditType, String beanName, String userName, String details) {
		SecuritySystemLocator.locate().addAuditEvent(auditType, beanName, userName, details);
	}

	/**
	 * добавить событие в аудит безопасности
	 * 
	 * @param auditType	тип события
	 * @param beanName	источник события
	 * @param details	описание события
	 */
	public static void addAuditEvent(SecurityAuditType auditType, String beanName, String details) {
		addAuditEvent(auditType, beanName, ServerUtils.getCurrentSession().getWorkingConnection().getUserProfile().getUserName(), details);
	}

	/**
	 * создание параметров подключения
	 * 
	 * @param login	имя пользователя
	 * @param password	пароль пользователя
	 * @param tenant	мандант для подключения
	 * @return	параметры подключения
	 */
	public static Map<String, Object> createAuthenticateParams(String login, String password, String tenant) {
		Map<String, Object> loginParams = new HashMap<String, Object>();
		loginParams.put("loginParam", login);
		loginParams.put("passwParam", password);
		return loginParams;
	}

}
