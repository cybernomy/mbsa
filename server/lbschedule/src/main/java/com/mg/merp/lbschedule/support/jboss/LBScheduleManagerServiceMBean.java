/*
 * LBScheduleManagerServiceMBean.java
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
package com.mg.merp.lbschedule.support.jboss;

import org.jboss.system.ServiceMBean;

import com.mg.merp.document.LBScheduleManager;

/**
 * Сервис менеджера управления графиками исполнения обязательств
 * 
 * @author Artem V. Sharapov
 * @version $Id: LBScheduleManagerServiceMBean.java,v 1.1 2007/04/21 11:49:33 sharapov Exp $
 */
public interface LBScheduleManagerServiceMBean extends LBScheduleManager, ServiceMBean {

}
