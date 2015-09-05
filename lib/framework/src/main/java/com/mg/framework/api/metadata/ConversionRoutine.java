/*
 * ConversionRoutine.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.framework.api.metadata;

import java.util.Map;

/**
 * Конвертация значений атрибутов для пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: ConversionRoutine.java,v 1.3 2006/07/04 15:49:51 safonov Exp $
 */
public interface ConversionRoutine<S, U> {

	/**
     * конвертация из значения пользовательского интерфейса в значение
     * обрабатываемое системой
	 * 
	 * @param value	значение введенное в пользовательском интерфейсе
	 * @return	значение обрабатываемое системой
	 */
	S inputConverse(U value);
	
	/**
     * конвертация из значения обрабатываемого системой в значение
     * для пользовательского интерфейса
	 * 
	 * @param value	значение обрабатываемое системой
	 * @return	значение введенное в пользовательском интерфейсе
	 */
	U outputConverse(S value);
	
	/**
	 * получить описание контекста импорта
	 * 
	 * @return	список наименование атрибутов для импорта из модели
	 * 
	 * @see #setImportContext(Map)
	 */
	String[] getImportContextDefinition();
	
	/**
	 * устанавливает контекст импорта, значения импортируются из модели контроллера
	 * формы текущей для данного преобразования
	 * 
	 * @param context	контекст
	 */
	void setImportContext(Map<String, Object> context);

}
