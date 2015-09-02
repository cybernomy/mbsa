/*
 * Constants.java
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
package com.mg.merp.wb.report.birt.data.oda.badi.util;

/**
 * ����� ���������� ��������
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: Constants.java,v 1.11 2008/08/12 09:30:31 safonov Exp $
 */
public final class Constants {

	public static final int DATA_SOURCE_MAJOR_VERSION = 0;

	public static final int DATA_SOURCE_MINOR_VERSION = 10;

	/**
	 * ������������� ��������� ������
	 */
	public static final String DATA_SOURCE_PRODUCT_NAME = "com.mg.merp.wb.report.birt.data.oda.badi";

	//public static final int CACHED_RESULT_SET_LENGTH = 10000;

	/**
	 * �������� "��� ���������" � �������
	 */
	public static final String BAI_CODE = "BAI_CODE";

	/**
	 * �������� "������" � �������
	 */
	public static final String QUERY_TEXT = "queryText";

	/**
	 * ������������� ������ ������ MBSA(�� �� MERP)
	 */
	public static final String MERP_DATASET_ID = "com.mg.merp.wb.report.birt.data.oda.badi.dataSet";

	/**
	 * �������� "��������������� ����������" � �������
	 */
	public static final String CONST_PROP_RELATIONINFORMATION = "RELATIONINFORMATION";

	/**
	 * ��� ���������, ����������� ��������� ������
	 */
	public static final String REPORT_PARAMS = "REPORT_PARAMS";

	/**
	 * ��� ���������, ����������� ��������� ����������� ������ ������
	 */
	public static final String DATASET_PARAMS = "DATASET_PARAMS";

	/**
	 * ��� ���������, ����������� �������� ���������� ������
	 */
	public static final String REPORT_CONTEXT_PARAMS = "REPORT_CONTEXT_PARAMS";

	/**
	 * ��� ���������, ����������� ����-������, ����������� ��� ���������� ������
	 * ������
	 */
	public static final String DATASET_METADATA = "DATASET_METADATA";
	
	public static final String DATASET_COLUMN_NAMES = "DATASET_COLUMN_NAMES";
	
	public static final String DATASET_COLUMN_TYPES = "DATASET_COLUMN_TYPES";
	
	/**
	 * ��� ���������, ����������� ��������� � �������� ������ BAi
	 */
	public static final String BAI_ENGINE_PARAM = "BAI_ENGINE_PARAM";

	/**
	 * ��� ���������, ����������� �������������� ���������
	 */
	public static final String ENTITY_IDS = "ENTITY_IDS";

	/**
	 * ��� ���������, ����������� ������-��������� ��� �������� ������������ �����
	 */
	public static final String BUSINESS_SERVICE = "BUSINESS_SERVICE";

	/**
	 * ��� ���������, ����������� ������� ������ � ��������� ����������
	 */
	public static final String CURRENT_SESSION_PARAM = "CURRENT_SESSION_PARAM";

	/**
	 * ��� ���������, ����������� �������� ����������
	 */
	public static final String APP_REPORT_CONTEXT = "__mg_ReportContext";
}
