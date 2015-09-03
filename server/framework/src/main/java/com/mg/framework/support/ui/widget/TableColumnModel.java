/*
 * TableColumnModel.java
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
package com.mg.framework.support.ui.widget;

import java.io.Serializable;
import java.util.Locale;

import com.mg.framework.api.metadata.ui.FieldMetadata;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;

/**
 * Модель столбца таблицы
 * 
 * @author Oleg V. Safonov
 * @version $Id: TableColumnModel.java,v 1.4 2008/07/24 15:24:24 safonov Exp $
 */
public class TableColumnModel implements Serializable {
	private String fieldName;
	private String title;
	private FieldMetadata metadata;
	private Locale locale;
	
	/**
	 * конструктор
	 * 
	 * @param name	имя столбца
	 * @param title	заголовок столбца
	 */
	public TableColumnModel(String name, String title) {
		this.fieldName = name;
		this.title = title;
		this.locale = ServerUtils.getUserLocale();
	}

	/**
	 * конструктор
	 * 
	 * @param name		имя столбца
	 * @param metadata	метаданные столбца
	 */
	public TableColumnModel(String name, FieldMetadata metadata) {
		this.fieldName = name;
		this.locale = ServerUtils.getUserLocale();
		setMetadata(metadata);
	}

	private void setTitle(String fieldName, FieldMetadata metadata) {
		//если нет метеданных, то установим заголовок именем поля, будет не локализованым
		this.title = metadata == null ? fieldName : metadata.getHeader();
	}
	
	/**
	 * получить имя столбца
	 * 
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * получить заголовок
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * установить имя столбца
	 * 
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * установить заголовок
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * получить метаданные столбца
	 * 
	 * @return the metadata or <code>null</code>
	 */
	public FieldMetadata getMetadata() {
		return metadata;
	}

	/**
	 * установить метаданные
	 * 
	 * @param metadata the metadata to set
	 */
	public void setMetadata(FieldMetadata metadata) {
		this.metadata = metadata;
		setTitle(fieldName, metadata);
	}

	/**
	 * Returns the decorator value for the column
	 * 
	 * @param value	value
	 * @return	decorator value
	 */
	public Object getDecoratorValue(Object value) {
		return MiscUtils.getPropertyTextRepresentation(value, metadata, locale);
	}
	
}
