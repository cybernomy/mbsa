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
 * Рабочая сессия, для получения текущей сессии используйте <code>ServerUtils.getCurrentSession()</code>
 * 
 * @author Oleg V. Safonov
 * @version $Id: Session.java,v 1.6 2008/08/28 13:13:05 safonov Exp $
 */
public interface Session {
	
	/**
	 * Тип области видимости атрибута хранимого в сессии
	 * 
	 */
	enum AttributeScopeType {
		
		/**
		 * контекст приложения, хранится на протяжении времени жизни сессии
		 */
		APPLICATION,
		
		/**
		 * конекст диалога, хранится на протяжении выполнения диалога
		 */
		CONVERSATION,
		
		/**
		 * контекст транзакции, хранится на протяжении времени жизни транзакции
		 */
		TRANSACTION
	}
	
	/**
	 * получить дескриптор сессии
	 * 
	 * @return	дескриптор
	 * 
	 * @deprecated
	 */
	@Deprecated
	int getHandle();

	/**
	 * получить реализацию
	 * 
	 * @return	реализация
	 * 
	 * @deprecated
	 */
	@Deprecated
	int getNativeImpl();
	
	/**
	 * разрушить сессию, не вызывать в прикладном коде
	 *
	 */
	void destroy();
	
	/**
	 * получить рабочее соединение
	 * 
	 * @return	рабочее соединение
	 * 
	 * @see WorkingConnection
	 */
	WorkingConnection getWorkingConnection();
	
	/**
	 * установить рабочее соединение
	 * 
	 * @param connection	рабочее соединение
	 * 
	 * @deprecated
	 */
	@Deprecated
	void setWorkingConnection(WorkingConnection connection);
	
	/**
	 * получить текущий мандант в который вошел пользователь
	 * 
	 * @return	текущий мандант
	 */
	SystemTenant getSystemTenant();
	
	/**
	 * установка атрибута сессии, значение ключа должно быть уникальным для всей сессии
	 * независимо от области видимости
	 * 
	 * @param key	ключ атрибута
	 * @param value	значение атрибута
	 * @param scope	область видимости атрибута
	 */
	void setAttribute(String key, Object value, AttributeScopeType scope);
	
	/**
	 * установка атрибута сессии с областью видимости <code>AttributeScopeType.APPLICATION</code>,
	 * эквивалентно вызову <code>setAttribute(key, value, AttributeScopeType.APPLICATION)</code>
	 * 
	 * @param key	ключ атрибута
	 * @param value	значение атрибута
	 * 
	 * @see #setAttribute(String, Object, AttributeScopeType)
	 */
	void setAttribute(String key, Object value);
	
	/**
	 * получение атрибута сессии
	 * 
	 * @param key	ключ атрибута
	 * @return		значение атрибута или <code>null</code> если значение не найдено
	 */
	Object getAttribute(String key);
	
	/**
	 * удаляет атрибут из сессии
	 * 
	 * @param key
	 */
	void removeAttribute(String key);

	/**
	 * остановить текущее приложение
	 */
	void stopApplication();

	/**
	 * получить признак интерактивного (диалогового) режима сессии
	 * 
	 * @return	если <code>true</code> то возможен диалог с пользователем
	 */
	boolean isInteractive();

}
