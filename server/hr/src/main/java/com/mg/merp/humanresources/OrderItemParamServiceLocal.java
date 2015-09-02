/*
 * OrderItemParamServiceLocal.java
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
package com.mg.merp.humanresources;

import com.mg.merp.humanresources.model.OrderItemParam;

/**
 * Сервис бизнес-компонента "Параметр пункта приказа"
 *  
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: OrderItemParamServiceLocal.java,v 1.3 2007/08/27 12:16:04 sharapov Exp $
 */
public interface OrderItemParamServiceLocal extends com.mg.framework.api.DataBusinessObjectService<OrderItemParam, Integer> {
	
	/**
	 * Имя сервиса
	 */
	final static String SERVICE_NAME = "merp/humanresources/OrderItemParam"; //$NON-NLS-1$
	
}
