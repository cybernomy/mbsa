/*
 * DataType.java
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
package com.mg.merp.core.model;

import java.util.Date;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.api.annotations.EnumConstantText;
import com.mg.framework.api.metadata.BuiltInType;
import com.mg.framework.api.orm.PersistentObject;

/**
 * Тип данных в дополнительных признаках
 * 
 * @author leonova
 * @version $Id: DataKind.java,v 1.2 2008/12/23 09:45:55 safonov Exp $
 */
@DataItemName ("Core.Feature.DataType")
public enum DataKind {
	/**
	 * Перечислимый
	 */
	@EnumConstantText ("resource://com.mg.merp.core.resources.dataitemlabels#Feature.DataType.Enum")
	ENUM,
	
	/**
	 * Строка
	 */
	@EnumConstantText ("resource://com.mg.merp.core.resources.dataitemlabels#Feature.DataType.String")
	STRING,
	
	/**
	 * Число целое
	 */
	@EnumConstantText ("resource://com.mg.merp.core.resources.dataitemlabels#Feature.DataType.Integer")
	INTEGER,
	
	/**
	 * Число вещественное
	 */
	@EnumConstantText ("resource://com.mg.merp.core.resources.dataitemlabels#Feature.DataType.Double")
	DOUBLE,
	
	/**
	 * Дата
	 */
	@EnumConstantText ("resource://com.mg.merp.core.resources.dataitemlabels#Feature.DataType.Data")
	DATA,
	
	/**
	 * Бизнес-компонент
	 */
	@EnumConstantText ("resource://com.mg.merp.core.resources.dataitemlabels#Feature.DataType.Entity")
	ENTITY,
	
	/**
	 * Логический
	 */
	@EnumConstantText ("resource://com.mg.merp.core.resources.dataitemlabels#Feature.DataType.Boolean")
	BOOLEAN;

	/**
	 * преобразовать во встроенный тип приложения
	 * 
	 * @return	встроенный тип приложения
	 */
	public BuiltInType toBuiltInType() {
		switch (this) {
		case BOOLEAN:
			return BuiltInType.BOOLEAN;
		case DATA:
			return BuiltInType.DATE;
		case DOUBLE:
			return BuiltInType.DOUBLE;
		case ENTITY:
			return BuiltInType.ENTITY;
		case ENUM:
			return BuiltInType.ENUM;
		case INTEGER:
			return BuiltInType.INTEGER;
		case STRING:
			return BuiltInType.STRING;
		default:
			return null;
		}
	}

	/**
	 * преобразовать в Java класс
	 * 
	 * @return
	 */
	public Class<?> toJavaClass() {
		switch (this) {
		case BOOLEAN:
			return Boolean.class;
		case DATA:
			return Date.class;
		case DOUBLE:
			return Double.class;
		case ENTITY:
			return PersistentObject.class;
		case ENUM:
			return Enum.class;
		case INTEGER:
			return Integer.class;
		case STRING:
			return String.class;
		default:
			return null;
		}
	}

}