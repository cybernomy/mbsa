/*
 * BinLocationProccessStrategy.java
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
package com.mg.merp.warehouse;

import java.util.List;

import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;

/**
 * ��������� ���������/������ ���������� ������ � ������� ��������
 * 
 * @author Artem V. Sharapov
 * @version $Id: BinLocationProccessStrategy.java,v 1.1 2008/05/30 12:39:54 sharapov Exp $
 */
public interface BinLocationProccessStrategy {
	
	/**
	 * O�������� ������ �������� ��� ������������ ������ �� �����
	 * @param docLineData - ������ �� ������������ ��� ���������
	 * @param stockBatch - ������
	 * @param processListener - ��������� ���������� ����������
	 */
	void proccessOnReceipt(WarehouseProcessDocumentLineData docLineData, StockBatch stockBatch, WarehouseProcessListener processListener);
	
	/**
	 * ��������� ������ �������� ��� �������� ������ �� ������
	 * @param docLineData - ������ �� ������������ ��� ���������
	 * @param stockBatches - ������
	 * @param processListener - ��������� ���������� ����������
	 */
	void proccessOnIssue(WarehouseProcessDocumentLineData docLineData, List<StockBatch> stockBatches, WarehouseProcessListener processListener);
	
	/**
	 * ����� ��������� ������ �������� ��� ������������ ������ �� �����
	 * @param history - �������
	 */
	void rollbackOnReceipt(StockBatchHistory history);
	
	/**
	 * ����� ��������� ������ �������� ��� �������� ������ �� ������
	 * @param history - �������
	 */
	void rollbackOnIssue(StockBatchHistory history);

}
