/*
 * OrderModelItemServiceLocal.java
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

import com.mg.merp.humanresources.model.OrderModelItem;

/**
 * ������ ������-���������� "������� ������� �������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: OrderModelItemServiceLocal.java,v 1.4 2007/08/27 13:35:10 sharapov Exp $
 */
public interface OrderModelItemServiceLocal extends com.mg.framework.api.DataBusinessObjectService<OrderModelItem, Integer> {

	/**
	 * ��� �������
	 */
	final static String SERVICE_NAME = "merp/humanresources/OrderModelItem"; //$NON-NLS-1$

}
