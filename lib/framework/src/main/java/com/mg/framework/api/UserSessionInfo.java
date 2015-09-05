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
 * Информация о сессии пользователя
 * 
 * @author Oleg V. Safonov
 * @version $Id: UserSessionInfo.java,v 1.1 2008/07/14 13:52:20 safonov Exp $
 */
public interface UserSessionInfo extends Serializable {

	/**
	 * получить имя пользователя
	 * 
	 * @return	имя пользователя
	 */
	String getUserName();
	
	/**
	 * признак активности сессии
	 * 
	 * @return	<code>true</code> если активна
	 */
	boolean isActive();
	
	/**
	 * признак текущего пользователя
	 * 
	 * @return	<code>true</code> если объект содержит информацию о пользователе получающем данную информацию
	 */
	boolean isCurrentPrincipal();

	/**
	 * получить время создания сессии
	 * 
	 * @return	время создания
	 */
	Date getCreationTime();
	
	/**
	 * получить время последнего доступа
	 * 
	 * @return	время последнего доступа
	 */
	Date getLastAccessedTime();
	
	/**
	 * получить время использованное сессией
	 * 
	 * @return	время использованное сессией
	 */
	Date getUsedServerTime();
	
	/**
	 * получить время простоя
	 * 
	 * @return	время простоя
	 */
	Date getIdleTime();
	
	/**
	 * получить время оставшееся время жизни HTTP сессии
	 * 
	 * @return	оставшееся время жизни
	 */
	Date getTTL();
	
	/**
	 * получить идентификатор HTTP сессии
	 * 
	 * @return	идентификатор HTTP сессии
	 */
	String getHttpSessionId();

	/**
	 * получить адрес удаленного хоста с которого была инициирована сессия
	 * 
	 * @return	адрес удаленного хоста
	 */
	String getRemoteHost();

	/**
	 * получить использованное время на последнем запросе
	 * 
	 * @return	использлванное время в ms
	 */
	int getLastUsedTime();

	/**
	 * получить минимальное использованное время на запросах
	 * 
	 * @return	минимальное использованное время в ms
	 */
	int getMinUsedTime();

	/**
	 * получить масимальное использованное время на запросах
	 * 
	 * @return	масимальное использованное время в ms
	 */
	int getMaxUsedTime();

	/**
	 * получить количество обращений к сессии
	 * 
	 * @return	количество обращений
	 */
	int getHits();

	/**
	 * получить размер сессии
	 * 
	 * @return	размер сессии
	 */
	long getSize();

	/**
	 * получить размер последнего запроса
	 * 
	 * @return	размер последнего запроса
	 */
	long getLastRequestSize();

	/**
	 * получить размер последнего ответа
	 * 
	 * @return	размер последнего ответа
	 */
	long getLastResponseSize();

	/**
	 * получить общий размер запросов
	 * 
	 * @return	общий размер запросов
	 */
	long getTotalRequestSize();

	/**
	 * получить общий размер ответов
	 * 
	 * @return	общий размер ответов
	 */
	long getTotalResponseSize();

}
