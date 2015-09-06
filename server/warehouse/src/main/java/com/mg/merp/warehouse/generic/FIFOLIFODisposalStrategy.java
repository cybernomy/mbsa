/* FIFOLIFODisposalStrategy.java
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
package com.mg.merp.warehouse.generic;

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.warehouse.WarehouseBatchServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessStrategy;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

/**
 * Стратегия списани со склада FIFO/LIFO
 *
 * @author Valentin A. Poroxnenko
 * @version $Id: FIFOLIFODisposalStrategy.java,v 1.13 2008/05/30 12:52:55 sharapov Exp $
 */
public class FIFOLIFODisposalStrategy extends AbstractGoodsIssueStrategy {
  private boolean isFIFO;

  public FIFOLIFODisposalStrategy(boolean isFIFO, WarehouseProcessDocumentLineData docLineData,
                                  Date processOnDate, WarehouseProcessStrategy processStrategy,
                                  WarehouseProcessListener batchProcessListener) {
    super(docLineData, processOnDate, processStrategy, batchProcessListener);
    this.isFIFO = isFIFO;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.StockOutComeStrategy#doOutcome()
   */
  @Override
  protected void internalIssue() {
    // WareCard
    changeWareCard();
    // StockBatch

    WarehouseBatchServiceLocal wbServ = (WarehouseBatchServiceLocal) ApplicationDictionaryLocator.locate()
        .getBusinessService(WarehouseBatchServiceLocal.LOCAL_SERVICE_NAME);

    availableBatches = getBatchesByOrder();
    Iterator<StockBatch> it = availableBatches.listIterator();
    BigDecimal disp = docLineData.getQuantity1();
    BigDecimal disp2 = null;
    if (docLineData.getQuantity2() != null)
      disp2 = docLineData.getQuantity2();
    BigDecimal quan = null;
    BigDecimal quan2 = null;
    StockBatchHistory prevHist = null;
    BigDecimal h1 = null;
    BigDecimal h2 = null;
    boolean stop = false;
    while (!stop) {
      StockBatch sb = it.next();
      BigDecimal stockBatchEndQuan = sb.getEndQuan();
      quan = disp.subtract(sb.getEndQuan());
      if (MathUtils.compareToZero(quan) == 1) {
        h1 = sb.getEndQuan();
        sb.setEndQuan(new BigDecimal(0));
        disp = quan;
      } else {
        h1 = disp;
        sb.setEndQuan(quan.negate());
        stop = true;
      }
      BigDecimal endQuan2 = sb.getEndQuan2();
      if (disp2 != null && endQuan2 != null) {
        quan2 = disp2.subtract(endQuan2);
        if (MathUtils.compareToZero(quan2) == 1) {
          h2 = endQuan2;
          sb.setEndQuan2(new BigDecimal(0));
          disp2 = quan2;
        } else {
          h2 = disp2;
          sb.setEndQuan2(quan2.negate());
        }
      }
      wbServ.store(sb);

      prevHist = createHistory(sb, h1, h2, prevHist);

      if (stockBatchEndQuan.compareTo(sb.getEndQuan()) != 0)
        disposedBatches.add(sb);
    }
    docLineData.getDocumentSpecItem().setData2(prevHist.getId());
    notifyComplete(); // moved from whprocessFactStrategy
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.generic.AbstractGoodsIssueStrategy#getComparator()
   */
  @Override
  protected Comparator<StockBatch> getComparator() {
    return new Comparator<StockBatch>() {
      public int compare(StockBatch o1, StockBatch o2) {
        if (o1.getCreateDate().equals(o2.getCreateDate()))
          return 0;
        else {
          int result = isFIFO ? 1 : -1;
          if (o1.getCreateDate().after(o2.getCreateDate()))
            return result;
          else
            return -result;
        }
      }
    };
  }

}
