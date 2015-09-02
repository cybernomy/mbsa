/* WarehousePlanHistoryServiceBean.java
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
import com.mg.merp.warehouse.WarehousePlanHistoryServiceLocal;
import com.mg.merp.warehouse.model.StockPlanHistory;

/**
 * Реализация бизнес-компонента "История планового движения и резервирования по партии"
 * 
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehousePlanHistoryServiceBean.java,v 1.2 2007/09/25 09:31:34 safonov Exp $
 */
@Stateless(name = "merp/warehouse/WarehousePlanHistoryService")
@PermitAll
public class WarehousePlanHistoryServiceBean extends
		AbstractPOJODataBusinessObjectServiceBean<StockPlanHistory, Integer>
		implements WarehousePlanHistoryServiceLocal {

}
