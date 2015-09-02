/*
 * OrderServiceLocal.java
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

import com.mg.merp.humanresources.model.Order;

/**
 * Сервис бизнес-компонента "Приказы"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: OrderServiceLocal.java,v 1.4 2007/08/27 13:35:10 sharapov Exp $
 */
public interface OrderServiceLocal extends com.mg.framework.api.DataBusinessObjectService<Order, Integer> {

	/**
	 * Имя сервиса
	 */
	final static String SERVICE_NAME = "merp/humanresources/Order"; //$NON-NLS-1$

	/**
	 * тип папки для приказов
	 */
	final static short FOLDER_TYPE = 11500;
	
	/**
	 * Не реализована
	 * @param orderId
	 * @return
	 */
	boolean prepareForSign(long orderId);

	/**
	 * Не реализована
	 * @param orderId
	 * @return
	 * @throws com.mg.framework.api.ApplicationException
	 */
	boolean signOrder(long orderId);

	/**
	 * Не реализована
	 * @param orderId
	 * @return
	 * @throws com.mg.framework.api.ApplicationException
	 */
	boolean processOrder(long orderId);

	/**
	 * Не реализована
	 * @param orderId
	 * @return
	 * @throws com.mg.framework.api.ApplicationException
	 */
	boolean rollbackOrder(long orderId);

}
