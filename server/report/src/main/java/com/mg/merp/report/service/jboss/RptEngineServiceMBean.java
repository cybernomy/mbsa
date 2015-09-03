/*
 * RptEngineServiceMBean.java
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
package com.mg.merp.report.service.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.framework.api.report.RptEngine;

/**
 * Сервис MBIRT (Millennium BI and Report Tools) для AS JBoss
 * 
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: RptEngineServiceMBean.java,v 1.3 2008/08/12 09:24:56 safonov Exp $
 */
public interface RptEngineServiceMBean extends RptEngine, ServiceMBean {
	/**
	 * получить расположение платформы
	 * 
	 * @return	абсолютный путь к платформе
	 */
	String getBIRTLocation();

	/**
	 * установить расположение платформы
	 * 
	 * @param birtLocation	абсолютный путь к платформе
	 */
	void setBIRTLocation(String birtLocation);

	/**
	 * получить формат вывода отчета по умолчанию
	 * 
	 * @return	формат вывода
	 */
	String getDefaultOutputFormat();
	
	/**
	 * установить формат вывода отчета по умолчанию
	 * 
	 * @param outputFormat	формат вывода
	 */
	void setDefaultOutputFormat(String outputFormat);
	
	/**
	 * получить уровень логирования
	 * 
	 * @return	уровень логирования
	 */
	String getLogLevel();
	
	/**
	 * установить уровень логирования
	 * 
	 * @param logLevel	уровень логирования
	 */
	void setLogLevel(String logLevel);
	
	/**
	 * получить имя приложения для просмотра отчетов
	 * 
	 * @return	имя приложения
	 */
	String getHTMLReportViewerApp();
	
	/**
	 * установить имя приложения для просмотра отчетов
	 * 
	 * @param viwerApp	имя приложения
	 */
	void setHTMLReportViewerApp(String viwerApp);
	
	/**
	 * получить папку для хранения шаблонов отчетов
	 * 
	 * @return	папка хранения шаблонов
	 */
	String getReportFolder();
	
	/**
	 * установить папку для хранения шаблонов отчетов
	 * 
	 * @param reportFolder	папка хранения шаблонов
	 */
	void setReportFolder(String reportFolder);

}
