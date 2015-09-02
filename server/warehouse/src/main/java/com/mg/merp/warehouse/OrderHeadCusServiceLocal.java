/*
 * OrderHeadCusServiceLocal.java
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
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.OrderHead;

/**
 * ������-��������� "������ �����������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: OrderHeadCusServiceLocal.java,v 1.4 2007/09/07 12:23:15 safonov Exp $
 */
public interface OrderHeadCusServiceLocal
		extends com.mg.merp.document.GoodsDocument<OrderHead, Integer, OrderHeadModelCusServiceLocal, OrderSpecCusServiceLocal>, DiscountDocument {

	/**
	 * ��� ����� ��� ������� �����������
	 */
	final static short FOLDER_PART = 42;

	/**
	 * docsection ��� ������� �����������
	 */
	final static short DOCSECTION = 22;

}
