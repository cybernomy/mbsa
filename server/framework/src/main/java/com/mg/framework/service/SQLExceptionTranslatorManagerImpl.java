/*
 * SQLExceptionTranslatorManagerImpl.java
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
package com.mg.framework.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.mg.framework.api.DataAccessException;
import com.mg.framework.api.jdbc.SQLExceptionTranslator;
import com.mg.framework.api.jdbc.SQLExceptionTranslatorManager;

/**
 * Реализация менеджера трансляторов SQL ИС в пользовательские исключения
 * 
 * @author Oleg V. Safonov
 * @version $Id: SQLExceptionTranslatorManagerImpl.java,v 1.1 2006/11/17 14:28:55 safonov Exp $
 */
public class SQLExceptionTranslatorManagerImpl implements
		SQLExceptionTranslatorManager {
	List<SQLExceptionTranslator> translatorList = Collections.synchronizedList(new LinkedList<SQLExceptionTranslator>());

	/* (non-Javadoc)
	 * @see com.mg.framework.api.SQLExceptionTranslatorManager#registerTranslator(com.mg.framework.api.SQLExceptionTranslator)
	 */
	public void registerTranslator(SQLExceptionTranslator translator) {
		//добавляем в начало, чтобы перекрыть более ранние трансляторы
		translatorList.add(0, translator);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.SQLExceptionTranslatorManager#unregisterTranslator(com.mg.framework.api.SQLExceptionTranslator)
	 */
	public void unregisterTranslator(SQLExceptionTranslator translator) {
		translatorList.remove(translator);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.SQLExceptionTranslatorManager#translate(java.sql.SQLException)
	 */
	public DataAccessException translate(SQLException sqlException) {
		for (SQLExceptionTranslator translator : new LinkedList<SQLExceptionTranslator>(translatorList)) { //копируем, итераторы не потокозащищенные
			DataAccessException result = translator.translate(sqlException);
			//сработает первый который транслировал
			if (result != null)
				return result;
		}
		//если ни один обработчик не транслировал ИС сгенерируем стандартное
		return new DataAccessException(sqlException);
	}

}
