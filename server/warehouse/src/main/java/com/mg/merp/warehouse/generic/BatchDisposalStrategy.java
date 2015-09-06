/* BatchDisposalStrategy.java
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

import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.warehouse.WarehouseBatchServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessStrategy;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.support.BatchQuanTransfer;
import com.mg.merp.warehouse.support.ui.WarehouseBatchSelectionDialog;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Стратегия партионного списани со склада
 *
 * @author Valentin A. Poroxnenko
 * @author Artem V. Sharapov
 * @version $Id: BatchDisposalStrategy.java,v 1.14 2008/06/05 12:32:25 sharapov Exp $
 */
public class BatchDisposalStrategy extends AbstractGoodsIssueStrategy {

  public BatchDisposalStrategy(WarehouseProcessDocumentLineData docLineData, Date processOnDate, WarehouseProcessStrategy processStrategy,
                               WarehouseProcessListener batchProcessListener) {
    super(docLineData, processOnDate, processStrategy, batchProcessListener);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.GoodsIssueStrategy#doDisposal()
   */
  @Override
  protected void internalIssue() {
    WarehouseBatchSelectionDialog dlg = (WarehouseBatchSelectionDialog) UIProducer
        .produceForm(WarehouseBatchSelectionDialog.FORM_PATH);
    dlg.setBatchesList(availableBatches);
    dlg.setDocSpec(docLineData.getDocumentSpec());

    dlg.addOkActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        performIssue(((WarehouseBatchSelectionDialog) event.getSource()).getDispQuan());
      }

    });
    dlg.addCancelActionListener(new FormActionListener() {

      public void actionPerformed(FormEvent event) {
        notifyCancel();
      }

    });

    dlg.execute();
  }

  private void performIssue(List<BatchQuanTransfer> transf) {
    // WareCard
    changeWareCard();

    // StockBatch
    StockBatchHistory prevHist = null;
    WarehouseBatchServiceLocal wbServ = (WarehouseBatchServiceLocal) ApplicationDictionaryLocator.locate()
        .getBusinessService(WarehouseBatchServiceLocal.LOCAL_SERVICE_NAME);

    availableBatches = getBatchesByOrder();
    for (int i = 0; i < availableBatches.size(); i++) {
      StockBatch sb = availableBatches.get(i);
      BigDecimal stockBatchEndQuan = sb.getEndQuan();
      BigDecimal quan1 = transf.get(i).quan1;
      BigDecimal quan2 = transf.get(i).quan2;
      BigDecimal endQuan = sb.getEndQuan();
      if (quan1 != null && endQuan != null)
        sb.setEndQuan(endQuan.subtract(quan1));
      BigDecimal endQuan2 = sb.getEndQuan2();
      if (quan2 != null && endQuan2 != null)
        sb.setEndQuan2(endQuan2.subtract(quan2));
      wbServ.store(sb);

      if (MathUtils.compareToZeroOrNull(quan1) > 0 || MathUtils.compareToZeroOrNull(quan2) > 0)
        prevHist = createHistory(sb, quan1, quan2, prevHist);

      if (stockBatchEndQuan.compareTo(sb.getEndQuan()) != 0)
        disposedBatches.add(sb);
    }
    docLineData.getDocumentSpecItem().setData2(prevHist.getId());
    notifyComplete();
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
        else if (o1.getCreateDate().after(o2.getCreateDate()))
          return 1;
        else
          return -1;
      }
    };
  }

}
