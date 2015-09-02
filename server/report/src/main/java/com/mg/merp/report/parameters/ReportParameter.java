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
 * ��������� ������� � ���������� ������
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
	 * @return ��� ���������. scalar, filter, list, table ��� parameter group
	 */
	ParameterType getParameterType();

	/**
	 * �������� ��� ���������
	 * 
	 * @return parameter type
	 */
	DataType getDataType();

	/**
	 * �������� java ��� ���������
	 * 
	 * @return	java ��� ���������
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
	 * ���������� �������� ���������
	 * 
	 * @param value
	 *            ��������
	 */
	void setValue(Object value);

	/**
	 * @return �������� ���������
	 */
	Object getValue();
	
	/**
	 * ���������� ������������ ��������� ������ ��������
	 * 
	 * @param searchHelpName	������������ ��������� ������ ��������
	 */
	void setSearchHelpName(String searchHelpName);
	
	/**
	 * �������� ������������ ��������� ������ ��������
	 * 
	 * @return	������������ ��������� ������ ��������
	 */
	String getSearchHelpName();
	
	/**
	 * �������� ������ ���������� �������� ������� ����� ������������ � ���� ���������
	 * @return ������ ���������� �������� ������� ����� ������������ � ���� ���������
	 */
	String getEntityPropertyText();
	
	/**
	 * �������� ������ ����������� �������� � ���� ���������
	 * @return ������ ����������� �������� � ���� ���������
	 */
	String getEntityPropertyTextFormat();
		
	/**
	 * @return �����-�� �������� �������
	 */
	boolean hasListValues();
	
	/**
	 * @return ������ ������������ �������� ���������
	 */
	List<SelectionChoice> getSelectionList();
	
	/**
	 * @return ��� �������� ��� �����������
	 */
	ControlType getControlType();

	/**
	 * �������� ������� ��������� ������
	 * 
	 * @return	���� <code>true</code>, �� �������� ����������� ��������� ������
	 */
	boolean cascade();

	/**
	 * �������� ��� ������
	 * 
	 * @return	��� ������ ��� <code>null</code> ���� �������� �� ����������� ������
	 */
	String groupName();
	
	/**
	 * �������� ���������� ����� ��������� � ������
	 * 
	 * @return	���������� ����� � ������ ��� -1 ���� �� ����������� ������
	 */
	int indexInGroup();
	
	/**
	 * @return	������� ������ ��������� � ����� ������� ����������
	 */
	boolean isHidden();

	/**
	 * @return	������� ���������� ���������, � ����� ������� ���������� ����� ����������� ������� ���������� ����������
	 */
	boolean isValueConcealed();
	
	/**
	 * �������� ������� ������������� ����������
	 * 
	 * @return	���� <code>true</code>, �� �������� ��������� �� ����� ���� ������
	 */
	boolean isRequired();
	
}
