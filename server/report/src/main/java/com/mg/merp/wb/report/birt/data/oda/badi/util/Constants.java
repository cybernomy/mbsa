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
 * Набор постоянных драйвера
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: Constants.java,v 1.11 2008/08/12 09:30:31 safonov Exp $
 */
public final class Constants {

	public static final int DATA_SOURCE_MAJOR_VERSION = 0;

	public static final int DATA_SOURCE_MINOR_VERSION = 10;

	/**
	 * Идентификатор источника данных
	 */
	public static final String DATA_SOURCE_PRODUCT_NAME = "com.mg.merp.wb.report.birt.data.oda.badi";

	//public static final int CACHED_RESULT_SET_LENGTH = 10000;

	/**
	 * Параметр "Код алгоритма" в шаблоне
	 */
	public static final String BAI_CODE = "BAI_CODE";

	/**
	 * Параметр "Запрос" в шаблоне
	 */
	public static final String QUERY_TEXT = "queryText";

	/**
	 * Идентификатор набора данных MBSA(он же MERP)
	 */
	public static final String MERP_DATASET_ID = "com.mg.merp.wb.report.birt.data.oda.badi.dataSet";

	/**
	 * Параметр "Вспомогательная информация" в шаблоне
	 */
	public static final String CONST_PROP_RELATIONINFORMATION = "RELATIONINFORMATION";

	/**
	 * Имя параметра, содержащего параметры отчёта
	 */
	public static final String REPORT_PARAMS = "REPORT_PARAMS";

	/**
	 * Имя параметра, содержащего параметры конкретного набора данных
	 */
	public static final String DATASET_PARAMS = "DATASET_PARAMS";

	/**
	 * Имя параметра, содержащего контекст выполнения отчета
	 */
	public static final String REPORT_CONTEXT_PARAMS = "REPORT_CONTEXT_PARAMS";

	/**
	 * Имя параметра, содержащего мета-данные, необходимые для построения набора
	 * данных
	 */
	public static final String DATASET_METADATA = "DATASET_METADATA";
	
	public static final String DATASET_COLUMN_NAMES = "DATASET_COLUMN_NAMES";
	
	public static final String DATASET_COLUMN_TYPES = "DATASET_COLUMN_TYPES";
	
	/**
	 * Имя параметра, содержащего интерфейс к сервисам машины BAi
	 */
	public static final String BAI_ENGINE_PARAM = "BAI_ENGINE_PARAM";

	/**
	 * Имя параметра, содержащего идентификаторы сущностей
	 */
	public static final String ENTITY_IDS = "ENTITY_IDS";

	/**
	 * Имя параметра, содержащего бизнес-компонент для которого генерируется отчет
	 */
	public static final String BUSINESS_SERVICE = "BUSINESS_SERVICE";

	/**
	 * Имя параметра, содержащего текущую сессию в контексте приложения
	 */
	public static final String CURRENT_SESSION_PARAM = "CURRENT_SESSION_PARAM";

	/**
	 * Имя параметра, содержащего контекст приложения
	 */
	public static final String APP_REPORT_CONTEXT = "__mg_ReportContext";
}
