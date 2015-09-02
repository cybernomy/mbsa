/*
 * AbstractConversionRoutine.java
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
package com.mg.framework.generic.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.mg.framework.api.metadata.ConversionRoutine;

/**
 * Базовая реализация конвертации атрибутов для пользовательского интерфейса
 * 
 * @author Oleg V. Safonov
 * @version $Id: AbstractConversionRoutine.java,v 1.2 2006/08/31 08:56:24 safonov Exp $
 */
public abstract class AbstractConversionRoutine<S, U> implements ConversionRoutine<S, U>, Serializable {
	private Map<String, Object> importContext = new HashMap<String, Object>();

	/**
	 * описывает контекст импорта
	 * 
	 * @return	контекст
	 */
	protected String[] defineImportContext() {
		return null;
	}

	/**
	 * получить значение контекста импорта
	 * 
	 * @param name	имя значения
	 * @return		значение
	 */
	protected Object getImportContextValue(String name) {
		return importContext.get(name);
	}

	/**
     * реализация конвертации из значения пользовательского интерфейса в значение
     * обрабатываемое системой, должен быть переопределен в классе
     * реализующем конвертацию
	 * 
	 * @param value	значение отображаемое в UI
	 * @return		значение для системы
	 */
	protected abstract S doInputConverse(U value);
	
	/**
     * реализация конвертации из значения обрабатываемого системой в значение
     * для пользовательского интерфейса, должен быть переопределен в классе
     * реализующем конвертацию
	 * 
	 * @param value	значение из системы
	 * @return		значение для отображения в UI
	 */
	protected abstract U doOutputConverse(S value);
	
	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ConversionRoutine#inputConverse(U)
	 */
	public S inputConverse(U value) {
		return doInputConverse(value);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ConversionRoutine#outputConverse(S)
	 */
	public U outputConverse(S value) {
		return doOutputConverse(value);
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ConversionRoutine#getImportContextDefinition()
	 */
	public String[] getImportContextDefinition() {
		return defineImportContext();
	}

	/* (non-Javadoc)
	 * @see com.mg.framework.api.metadata.ConversionRoutine#setImportContext(java.util.Map)
	 */
	public void setImportContext(Map<String, Object> context) {
		importContext.clear();
		importContext.putAll(context);
	}

}
