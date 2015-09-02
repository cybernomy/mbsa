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
 * —истема безопасности
 * 
 * @author Oleg V. Safonov
 * @version $Id: SecuritySystem.java,v 1.9 2007/10/19 06:31:34 safonov Exp $
 */
public interface SecuritySystem {
	
	/**
	 * идентификатор группы "јдминистраторы"
	 */
	final int ADMIN_GROUP = 1;
	
	/**
	 * проверка права на ресурс
	 * 
	 * @param perm	ресурс
	 * @throws AccessControlException если ресурс недоступен
	 */
	void checkPermission(final Permission perm) throws AccessControlException;
	
	/**
	 * аутентификаци€
	 * 
	 * @param login		логин
	 * @param password	пароль
	 * @throws LoginException	в случае неудачной попытки
	 */
	void authenticate(String login, String password) throws LoginException;
	
	/**
	 * выполнить отключение
	 *
	 */
	void logout();
	
	/**
	 * загрузить пользовательский профайл
	 * 
	 * @param login			логин
	 * @param locale		локаль пользовател€
	 * @param defaultLocale	локаль по умолчанию, будет использована в случае если невозможно использовать локаль пользовател€
	 * @return	профайл
	 */
	UserProfile loadUserProfile(String login, Locale locale, Locale defaultLocale);
	
	/**
	 * подключение пользовател€
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
	 * загрузка наименований модулей используемых пользователем
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
	 * установка разрешений на элемент иерархии
	 * 
	 * @param treeIdentifier	идентификатор элемента
	 * @param parentIdentifier	идентификатор родител€ элемента
	 * @param treePart		вид иерархии
	 */
	void grantTreePermission(int treeIdentifier, int parentIdentifier, int treePart);
	
	/**
	 * интерактивна€ настройка разрешений на элемент иерархии
	 * 
	 * @param treeIdentifier	идентификатор элемента
	 * @param treePart			вид иерархии
	 * @param className			класс модели иерархии
	 * @param parentProperty	им€ свойства ссылки на родител€
	 */
	void setupTreePermission(int treeIdentifier, int treePart, String className, String parentProperty);
	
	/**
	 * создание пользовател€
	 * 
	 * @param userName	им€ пользовател€
	 * @param passw		пароль
	 */
	void createUser(String userName, String passw);
	
	/**
	 * удаление пользовател€
	 * 
	 * @param userName	им€ пользовател€
	 */
	void deleteUser(String userName);
	
	/**
	 * изменение парол€ пользовател€
	 * 
	 * @param userName	им€ пользовател€
	 * @param passw		новый пароль
	 */
	void changePassword(String userName, String passw);
	
	/**
	 * создать аудит безопасности системы
	 * 
	 * @param auditType	тип событи€
	 * @param beanName	источник событи€
	 * @param userName	им€ пользовател€
	 * @param details	описание событи€
	 */
	void addAuditEvent(SecurityAuditType auditType, String beanName, String userName, String details);
	
}
