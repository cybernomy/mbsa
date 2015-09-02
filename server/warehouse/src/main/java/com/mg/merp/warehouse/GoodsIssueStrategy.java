/**
 * GoodsIssueStrategy.java.java
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

import java.math.BigDecimal;
import java.util.List;

import com.mg.merp.warehouse.model.StockBatch;

/**
 * ��������� ������� ������� �� ������
 * 
 * @author Oleg V. Safonov
 * @version $Id: GoodsIssueStrategy.java,v 1.1 2007/09/04 13:07:02 safonov Exp $
 */
public interface GoodsIssueStrategy {

	/**
	 * ��������� ������ �� ������
	 */
	void doIssue();
	
	/**
	 * �������� ������ ������ ������ � ������� �� ��������, ���������������� ���������
	 * 
	 * @return	������ ������ ������
	 */
	List<StockBatch> getBatchesByOrder();

	/**
	 * ��������� � ���������� ������� �� ������
	 */
	void notifyComplete();

	/**
	 * ��������� �� ������ ������� �� ������
	 */
	void notifyCancel();

	/**
	 * �������� ���������� ��������� ��� �������
	 * 
	 * @return	���������� ��������� ��� �������
	 */
	BigDecimal getAvailable();
	
}
