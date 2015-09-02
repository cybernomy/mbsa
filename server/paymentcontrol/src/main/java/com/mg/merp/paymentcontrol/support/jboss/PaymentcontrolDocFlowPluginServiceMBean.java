/*
 * PaymentcontrolDocFlowPluginServiceMBean.java
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
package com.mg.merp.paymentcontrol.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * —ервис дополнительных модулей подсистемы "ѕлатежный календарь"
 * 
 * @author Artem V. Sharapov
 * @version $Id: PaymentcontrolDocFlowPluginServiceMBean.java,v 1.1 2007/05/14 05:21:27 sharapov Exp $
 */
public interface PaymentcontrolDocFlowPluginServiceMBean extends ServiceMBean {

	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:paymentcontrol=DocFlowPluginService"; //$NON-NLS-1$

}
