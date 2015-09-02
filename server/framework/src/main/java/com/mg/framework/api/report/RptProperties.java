/*
 * RptProperties.java
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
package com.mg.framework.api.report;

import java.io.Serializable;
import java.util.Locale;

import com.mg.framework.api.BusinessObjectService;

/**
 * ��������� ������� ��������� MBIRT (Millennium BI and Report Tools)
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptProperties.java,v 1.8 2008/10/24 13:59:13 safonov Exp $
 */
public interface RptProperties {

	/**
	 * ����������������� ��� ��������� ������ ��� �������� ��������������� ���������
	 * ���������� ������������� ��� ���������� � �����
	 */
	final static String ENTITY_IDS_DATASET_PARAMETER = "__mg_entityIds";
	
	/**
	 * ����������������� ��� ��������� ������ ��� �������� ��� ������-����������
	 * ��� �������� ����������� �����
	 */
	final static String BUSINESS_SERVICE_DATASET_PARAMETER = "__mg_businessService";
	
	/**
	 * ������ ������������� ������
	 */
	enum OutputFormat {
		HTML,
		PDF,
		XLS,
		DOC,
		PPT
	}

	/**
	 * ��������� ������� ������, ���� �� ���������� �� ������������ {@link OutputFormat#HTML}
	 * 
	 * @param outputFormat	������ ������
	 */
	void setOutputFormat(OutputFormat outputFormat);

	/**
	 * �������� ������ ������
	 * 
	 * @return	������ ������ ��� <code>null</code> ���� �� ����������
	 */
	OutputFormat getOutputFormat();

	/**
	 * ��������� ��������������� ��������� ��� ������� ����������� ��������� ������
	 * 
	 * @see	#setBusinessService(Integer)
	 * 
	 * @param entityIds	�������������� ���������, ����� ���� <code>null</code>
	 */
	void setEntityIds(final Serializable[] entityIds);

	/**
	 * �������� �������������� ��������� ��� ������� ����������� ��������� ������
	 * 
	 * @return �������������� ��������� ��� <code>null</code> ���� �� �����������
	 */
	Serializable[] getEntityIds();

	/**
	 * ��������� ������-���������� ��� �������� ����������� ��������� ������ 
	 * 
	 * @param businessService	������-���������, ����� ���� <code>null</code>
	 */
	void setBusinessService(final BusinessObjectService businessService);

	/**
	 * �������� ������-��������� ��� �������� ����������� ��������� ������
	 * 
	 * @return	������-��������� ��� <code>null</code> ���� �� ����������
	 */
	BusinessObjectService getBusinessService();

	/**
	 * ���������� ������ ��������� ������
	 * 
	 * @param locale	������
	 */
	void setLocale(Locale locale);
	
	/**
	 * �������� ������ ��������� ������
	 * 
	 * @return	������
	 */
	Locale getLocale();

	/**
	 * ���������� �������� ��������� ������
	 * 
	 * @param name	��� ���������, ���� �������� � ������ ������ �� ������ � �������, �� �������� ����� ������������
	 * @param value	�������� ���������, ��� �������� ������ ��������� � ����� ��������� � ������� ������
	 */
	void setParameterValue(String name, Object value);

	/**
	 * �������� �������� ��������� ������ �������������� � ������� {@link #setParameterValue(String, Object)}
	 * 
	 * @param name	��� ���������
	 * @return	�������� ���������
	 */
	Object getParameterValue(String name);

	/**
	 * �������� ������� ��������� ��������� ������
	 * 
	 * @param name	��� ���������
	 * @return	<code>true</code> ���� �������� ��� ���������� � ������� {@link #setParameterValue(String, Object)}
	 */
	boolean hasParameterValue(String name);

	/**
	 * ���������� ������� ������ ������� ������� ����������, � ������ ���� ������� ����� �������� <code>false</code>
	 * �� ������ �� ����� �������
	 * 
	 * @param show	������� ������ �������
	 */
	void setShowParametersDialog(boolean show);

	/**
	 * �������� ������� ������ ������� ������� ����������
	 * 
	 * @return	������� ������ �������
	 */
	boolean isShowParametersDialog();

}
