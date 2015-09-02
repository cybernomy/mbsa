/*
 * BinLocationServiceLocal.java
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

import com.mg.merp.warehouse.model.BinLocation;
import com.mg.merp.warehouse.model.StockBatch;

/**
 * ������-��������� "C����� �������� �� �������"
 * 
 * @author leonova
 * @author Artem V. Sharapov
 * @version $Id: BinLocationServiceLocal.java,v 1.4 2008/05/30 12:39:54 sharapov Exp $
 */
public interface BinLocationServiceLocal extends com.mg.framework.api.DataBusinessObjectService<BinLocation, Integer> {
	
	/**
	 * ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/warehouse/BinLocation";
	
	/**
	 * ��� ��������: ������
	 */
	static final short RECEIPT_KIND = 0;
	
	/**
	 * ��� ��������: ������
	 */
	static final short ISSUE_KIND = 1; 
	
	/**
	 * �������� ������ ������ �������� � ������
	 * @param stockBatch - ������
	 * @return ������ ������ �������� � ������
	 */
	List<BinLocationDetailData> getBinLocationDetails(StockBatch stockBatch);
	
}
