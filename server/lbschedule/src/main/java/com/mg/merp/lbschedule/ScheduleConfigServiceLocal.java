/*
 * ScheduleConfigServiceLocal.java
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
package com.mg.merp.lbschedule;

import com.mg.merp.lbschedule.model.ScheduleConfig;

/**
 * Сервис бизнес-компонента "Конфигурация модуля <Графики исполнения обязательств>"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: ScheduleConfigServiceLocal.java,v 1.3 2007/04/21 11:49:33 sharapov Exp $
 */
public interface ScheduleConfigServiceLocal extends com.mg.framework.api.DataBusinessObjectService<ScheduleConfig, Integer> {
	
	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/lbschedule/ScheduleConfig"; //$NON-NLS-1$
	
}
