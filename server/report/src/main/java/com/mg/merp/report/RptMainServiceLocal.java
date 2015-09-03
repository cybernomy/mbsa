/*
 * RptMainServiceLocal.java
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
package com.mg.merp.report;

import java.util.List;

import com.mg.merp.report.model.RptMain;

/**
 * Бизнес-компонент "Репозитарий отчетов"
 * 
 * @author leonova
 * @author Oleg V. Safonov
 * @version $Id: RptMainServiceLocal.java,v 1.4 2007/08/30 14:20:51 safonov Exp $
 */
public interface RptMainServiceLocal extends
		com.mg.framework.api.DataBusinessObjectService<RptMain, Integer> {
	/**
	 * Имя сервиса
	 */
	static final String SERVICE_NAME = "merp/report/RptMain";
	
	/**
	 * загрузка отчетов связанных с бизнес-компонентом и доступных пользователю
	 * 
	 * @param classId	идентификатор бизнес-компонента, если <code>null</code>, то будут загружены отчеты для всех бизнес-компонентов
	 * @return	список отчетов
	 */
	List<RptMain> loadAvailableReports(Integer classId);

}
