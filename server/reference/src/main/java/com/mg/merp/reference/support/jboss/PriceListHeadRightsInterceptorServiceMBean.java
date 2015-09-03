/*
 * PriceListHeadRightsInterceptorServiceMBean.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.support.jboss;

import org.jboss.system.ServiceMBean;

/**
 * Сервис перехватчика для обслуживания прайс-листов
 * 
 * @author Konstantin S. Alikaev
 * @version $Id: PriceListHeadRightsInterceptorServiceMBean.java,v 1.1 2008/05/13 09:54:17 alikaev Exp $
 */
public interface PriceListHeadRightsInterceptorServiceMBean extends	ServiceMBean {

	/**
	 * наименование сервиса
	 */
	final static String SERVICE_NAME = "merp:reference=PriceListHeadRightsInterceptorService"; //$NON-NLS-1$

}
