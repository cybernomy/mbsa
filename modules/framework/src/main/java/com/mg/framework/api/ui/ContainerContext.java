/**
 * ContainerContext.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.framework.api.ui;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Среда контейнера выполнения WEB приложения
 * 
 * @author Oleg V. Safonov
 * @version $Id: ContainerContext.java,v 1.1 2007/11/09 11:50:39 safonov Exp $
 */
public interface ContainerContext {

	/**
	 * получить HTTP сессию
	 * 
	 * @return	HTTP сессия
	 */
	HttpSession getHttpSession();
	
	/**
	 * получить контекст сервлета
	 * 
	 * @return	контекст сервлета
	 */
	ServletContext getServletContext();
	
	/**
	 * получить HTTP запрос
	 * 
	 * @return	HTTP запрос
	 */
	HttpServletRequest getServletRequest();

	/**
	 * получить HTTP ответ
	 * 
	 * @return	HTTP ответ
	 */
	HttpServletResponse getServletResponse();
	
	/**
	 * получить конфигурацию сервлета
	 * 
	 * @return	конфигурация сервлета
	 */
	ServletConfig getServletConfig();

	/**
	 * получить URL web сервера 
	 * 
	 * @return	URL web сервера
	 */
	String getServerURL();
	
	/**
	 * получить URL web приложения
	 * 
	 * @return	URL web приложения
	 */
	String getApplicationURL();
	
}
