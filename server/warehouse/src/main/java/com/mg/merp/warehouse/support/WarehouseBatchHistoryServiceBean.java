/* WarehouseBatchHistoryServiceBean.java
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
package com.mg.merp.warehouse.support;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import com.mg.framework.generic.AbstractPOJODataBusinessObjectServiceBean;
import com.mg.merp.warehouse.WarehouseBatchHistoryServiceLocal;
import com.mg.merp.warehouse.model.StockBatchHistory;

/**
 * Реализация бизнес-компонента "История фактического движения по партии"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseBatchHistoryServiceBean.java,v 1.3 2007/07/31 10:29:14 safonov Exp $
 */
@Stateless(name = "merp/warehouse/WarehouseBatchHistoryService")
@PermitAll
public class WarehouseBatchHistoryServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<StockBatchHistory, Integer>
		implements WarehouseBatchHistoryServiceLocal {

}
