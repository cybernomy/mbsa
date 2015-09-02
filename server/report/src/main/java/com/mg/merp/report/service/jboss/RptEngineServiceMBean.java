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
 * —ервис MBIRT (Millennium BI and Report Tools) дл€ AS JBoss
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
	 * получить уровень логировани€
	 * 
	 * @return	уровень логировани€
	 */
	String getLogLevel();
	
	/**
	 * установить уровень логировани€
	 * 
	 * @param logLevel	уровень логировани€
	 */
	void setLogLevel(String logLevel);
	
	/**
	 * получить им€ приложени€ дл€ просмотра отчетов
	 * 
	 * @return	им€ приложени€
	 */
	String getHTMLReportViewerApp();
	
	/**
	 * установить им€ приложени€ дл€ просмотра отчетов
	 * 
	 * @param viwerApp	им€ приложени€
	 */
	void setHTMLReportViewerApp(String viwerApp);
	
	/**
	 * получить папку дл€ хранени€ шаблонов отчетов
	 * 
	 * @return	папка хранени€ шаблонов
	 */
	String getReportFolder();
	
	/**
	 * установить папку дл€ хранени€ шаблонов отчетов
	 * 
	 * @param reportFolder	папка хранени€ шаблонов
	 */
	void setReportFolder(String reportFolder);

}
