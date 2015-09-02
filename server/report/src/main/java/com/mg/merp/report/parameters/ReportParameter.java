/* ReportParameter.java
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
package com.mg.merp.report.parameters;

import java.util.List;
import java.util.Map;

/**
 * »нтерфейс доступа к параметрам отчЄта
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: ReportParameter.java,v 1.5 2008/10/24 14:04:21 safonov Exp $
 */
public interface ReportParameter {

	enum ParameterType {
		SCALAR_PARAMETER, FILTER_PARAMETER, LIST_PARAMETER, TABLE_PARAMETER, PARAMETER_GROUP, CASCADING_PARAMETER_GROUP
	};
	
	enum DataType {
		UNKNOWN, STRING, BOOLEAN, DATE_TIME, NUMBER, FLOAT, INTEGER, DATE, TIME, ENTITY
	};
	
	enum ControlType{
		TEXT_BOX, LIST_BOX, COMBO_BOX, RADIO_BUTTON, CHECK_BOX
	};

	/**
	 * @return тип параметра. scalar, filter, list, table или parameter group
	 */
	ParameterType getParameterType();

	/**
	 * получить тип параметра
	 * 
	 * @return parameter type
	 */
	DataType getDataType();

	/**
	 * получить java тип параметра
	 * 
	 * @return	java тип параметра
	 */
	Class<?> getJavaType();

	/**
	 * returns the name of the parameter
	 * 
	 * @return the name of the parameter
	 */
	String getName();

	/**
	 * returns the locale-specific display name for the parameter. The locale
	 * used is the locale in the getParameterDefinition task
	 * 
	 * @return display name under the request or default locale
	 */
	String getDisplayName();

	/**
	 * returns a collection of user-defined property name and value pairs
	 * 
	 * @return a collection of user-defined property name ane value pairs
	 */
	Map<String, Object> getUserPropertyValues();

	/**
	 * returns the value of a user-defined property
	 * 
	 * @return the value for a user-defined property
	 */
	String getUserPropertyValue(String name);

	/**
	 * ”становить значение параметру
	 * 
	 * @param value
	 *            значение
	 */
	void setValue(Object value);

	/**
	 * @return значение параметра
	 */
	Object getValue();
	
	/**
	 * установить наименование механизма помощи сущности
	 * 
	 * @param searchHelpName	наименование механизма помощи сущности
	 */
	void setSearchHelpName(String searchHelpName);
	
	/**
	 * получить наименование механизма помощи сущности
	 * 
	 * @return	наименование механизма помощи сущности
	 */
	String getSearchHelpName();
	
	/**
	 * ѕолучить список аттрибутов сущности которые будут отображатьс€ в поле редактора
	 * @return список аттрибутов сущности которые будут отображатьс€ в поле редактора
	 */
	String getEntityPropertyText();
	
	/**
	 * ѕолучить формат отображени€ сущности в поле редактора
	 * @return формат отображени€ сущности в поле редактора
	 */
	String getEntityPropertyTextFormat();
		
	/**
	 * @return задан-ли параметр списком
	 */
	boolean hasListValues();
	
	/**
	 * @return список перечислимых значений параметра
	 */
	List<SelectionChoice> getSelectionList();
	
	/**
	 * @return тип элемента дл€ отображени€
	 */
	ControlType getControlType();

	/**
	 * получить признак каскадной группы
	 * 
	 * @return	если <code>true</code>, то параметр принадлежит каскадной группе
	 */
	boolean cascade();

	/**
	 * получить им€ группы
	 * 
	 * @return	им€ группы или <code>null</code> если параметр не принадлежит группе
	 */
	String groupName();
	
	/**
	 * получить пор€дковый номер параметра в группе
	 * 
	 * @return	пор€дковый номер в группе или -1 если не принадлежит группе
	 */
	int indexInGroup();
	
	/**
	 * @return	признак показа параметра в форме запроса параметров
	 */
	boolean isHidden();

	/**
	 * @return	признак секретного параметра, в форме запроса параметров будет использован элемент скрывающий содержание
	 */
	boolean isValueConcealed();
	
	/**
	 * получить признак об€зательного заполнени€
	 * 
	 * @return	если <code>true</code>, то значение параметра не может быть пустым
	 */
	boolean isRequired();
	
}
