/*
 * SQLExceptionTranslatorManager.java
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
package com.mg.framework.api.jdbc;

import java.sql.SQLException;

import com.mg.framework.api.DataAccessException;

/**
 * Менеджер трансляторов SQL ИС в пользовательские исключения
 * 
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslatorManager.java,v 1.1 2006/11/17 14:13:38 safonov Exp $
 */
public interface SQLExceptionTranslatorManager {

	/**
	 * регистрация траслятора, становится первым в списке обработчиков, т.о. возможно
	 * переопределить поведение трасляторов добавив свой траслятор после инсталяции переопределяемого
	 * 
	 * @param translator	траслятор
	 */
	void registerTranslator(SQLExceptionTranslator translator);
	
	/**
	 * удаление траслятора из списка трансляторов
	 * 
	 * @param translator
	 */
	void unregisterTranslator(SQLExceptionTranslator translator);
	
	/**
	 * перевести SQL ИС в пользовательское исключение
	 * 
	 * @param sqlException	SQL ИС
	 * @return	пользовательское исключение
	 */
	DataAccessException translate(SQLException sqlException);
	
}
