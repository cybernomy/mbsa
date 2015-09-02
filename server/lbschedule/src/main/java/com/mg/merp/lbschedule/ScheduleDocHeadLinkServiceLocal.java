/*
 * ScheduleDocHeadLinkServiceLocal.java
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
package com.mg.merp.lbschedule;

import com.mg.framework.api.DataBusinessObjectService;
import com.mg.merp.lbschedule.model.ScheduleDocHeadLink;

/**
 * Сервис бизнес-компонента "Связь графика исполнения обязательств с документом"
 * 
 * @author Artem V. Sharapov
 * @version $Id: ScheduleDocHeadLinkServiceLocal.java,v 1.1 2007/04/21 11:49:33 sharapov Exp $
 */
public interface ScheduleDocHeadLinkServiceLocal extends DataBusinessObjectService<ScheduleDocHeadLink, Integer> {
	
	/**
	 * Локальное имя сервиса
	 */
	static final String LOCAL_SERVICE_NAME = "merp/lbschedule/ScheduleDocHeadLink"; //$NON-NLS-1$
	
}
