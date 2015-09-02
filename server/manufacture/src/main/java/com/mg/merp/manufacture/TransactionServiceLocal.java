/*
 * TransactionServiceLocal.java
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
package com.mg.merp.manufacture;

import java.math.BigDecimal;

import com.mg.merp.manufacture.model.Transaction;

/**
 * ������-��������� "���������������� ����������"
 * 
 * @author Oleg V. Safonov
 * @version $Id: TransactionServiceLocal.java,v 1.2 2007/07/30 10:28:17 safonov Exp $
 */
public interface TransactionServiceLocal
		extends com.mg.framework.api.DataBusinessObjectService<Transaction, Integer>
{
	/**
	 * ��� �������
	 */
	static final String SERVICE_NAME = "merp/manufacture/Transaction";

	/**
	 * ��������� ���������� ��������� �� �������
	 * 
	 * @param resourceId	������������� �������
	 * @return	��������� ����������
	 */
	BigDecimal getQuantityByResource(int resourceId);
}
