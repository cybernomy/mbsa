/* WarehouseRollbackReservStrategy.java
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
package com.mg.merp.warehouse.generic;

import com.mg.framework.api.math.Constants;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.warehouse.WareCardServiceLocal;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.StockPlanHistory;

/**
 * Стратегия отката этапа ДО "Резервирование по складу"
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: WarehouseRollbackReservStrategy.java,v 1.5 2008/04/18 15:18:20 safonov Exp $
 */
public class WarehouseRollbackReservStrategy extends
    AbstractWarehouseRollbackPlanReservStrategy {

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.generic.AbstractWarehouseRollbackPlanReservStrategy#rollbackOperation(com.mg.merp.warehouse.model.StockPlanHistory)
   */
  @Override
  public void rollbackOperation(StockPlanHistory history) {
    StockCard stockCard = history.getStockCard();
    stockCard.setReserve(MathUtils.subtractNullable(stockCard.getReserve(), history.getQuantity(), Constants.QUANTITY_ROUND_CONTEXT));
    stockCard.setReserve2(MathUtils.subtractNullable(stockCard.getReserve2(), history.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT));

    WareCardServiceLocal wcServ = (WareCardServiceLocal) ApplicationDictionaryLocator.locate()
        .getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);
    wcServ.deleteStockCards(stockCard);
  }

}
