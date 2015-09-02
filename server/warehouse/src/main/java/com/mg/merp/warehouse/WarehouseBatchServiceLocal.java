/*
 * WarehouseBatchServiceLocal.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.warehouse;

import com.mg.merp.warehouse.model.StockBatch;

/**
 * 
 * @author leonova
 * @version $Id: WarehouseBatchServiceLocal.java,v 1.1 2006/03/14 11:49:53
 *          safonov Exp $
 */
public interface WarehouseBatchServiceLocal extends
		com.mg.framework.api.DataBusinessObjectService<StockBatch, Integer> {
	/**
	 * ��������� ��� �������
	 */
	static final String LOCAL_SERVICE_NAME = "merp/warehouse/WarehouseBatch";
}
