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
 * Параметры запуска платформы MBIRT (Millennium BI and Report Tools)
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptProperties.java,v 1.8 2008/10/24 13:59:13 safonov Exp $
 */
public interface RptProperties {

	/**
	 * зарезервированное имя параметра отчета для передачи идентификаторов сущностей
	 * отмеченных пользователем или переданных в отчет
	 */
	final static String ENTITY_IDS_DATASET_PARAMETER = "__mg_entityIds";
	
	/**
	 * зарезервированное имя параметра отчета для передачи имя бизнес-компонента
	 * для которого выполняется отчет
	 */
	final static String BUSINESS_SERVICE_DATASET_PARAMETER = "__mg_businessService";
	
	/**
	 * Формат представления отчёта
	 */
	enum OutputFormat {
		HTML,
		PDF,
		XLS,
		DOC,
		PPT
	}

	/**
	 * установка формата вывода, если не установлен то используется {@link OutputFormat#HTML}
	 * 
	 * @param outputFormat	формат вывода
	 */
	void setOutputFormat(OutputFormat outputFormat);

	/**
	 * получить формат вывода
	 * 
	 * @return	формат вывода или <code>null</code> если не установлен
	 */
	OutputFormat getOutputFormat();

	/**
	 * установка идентификаторов сущностей для которых выполняется генерация отчета
	 * 
	 * @see	#setBusinessService(Integer)
	 * 
	 * @param entityIds	идентификаторы сущностей, может быть <code>null</code>
	 */
	void setEntityIds(final Serializable[] entityIds);

	/**
	 * получить идентификаторы сущностей для которых выполняется генерация отчета
	 * 
	 * @return идентификаторы сущностей или <code>null</code> если не установлены
	 */
	Serializable[] getEntityIds();

	/**
	 * установка бизнес-компонента для которого выполняется генерация отчета 
	 * 
	 * @param businessService	бизнес-компонент, может быть <code>null</code>
	 */
	void setBusinessService(final BusinessObjectService businessService);

	/**
	 * получить бизнес-компонент для которого выполняется генерация отчета
	 * 
	 * @return	бизнес-компонент или <code>null</code> если не установлен
	 */
	BusinessObjectService getBusinessService();

	/**
	 * установить локаль генерации отчета
	 * 
	 * @param locale	локаль
	 */
	void setLocale(Locale locale);
	
	/**
	 * получить локаль генерации отчета
	 * 
	 * @return	локаль
	 */
	Locale getLocale();

	/**
	 * установить значение параметра отчета
	 * 
	 * @param name	имя параметра, если параметр с данным именем не описан в щаблоне, то значение будет игнорировано
	 * @param value	значение параметра, тип значения должен совпадать с типом описанном в шаблоне отчета
	 */
	void setParameterValue(String name, Object value);

	/**
	 * получить значение параметра отчета установленного с помощью {@link #setParameterValue(String, Object)}
	 * 
	 * @param name	имя параметра
	 * @return	значение параметра
	 */
	Object getParameterValue(String name);

	/**
	 * получить признак установки параметра отчета
	 * 
	 * @param name	имя параметра
	 * @return	<code>true</code> если параметр был установлен с помощью {@link #setParameterValue(String, Object)}
	 */
	boolean hasParameterValue(String name);

	/**
	 * установить признак показа диалога запроса параметров, в случае если признак имеет значение <code>false</code>
	 * то диалог не будет показан
	 * 
	 * @param show	признак показа диалога
	 */
	void setShowParametersDialog(boolean show);

	/**
	 * получить признак показа диалога запроса параметров
	 * 
	 * @return	признак показа диалога
	 */
	boolean isShowParametersDialog();

}
