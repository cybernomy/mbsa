/* WarehouseRollbackFactStrategy.java
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

import com.mg.framework.api.math.Constants;
import com.mg.framework.api.metadata.ApplicationDictionary;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.MiscUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.warehouse.BatchNotAliveException;
import com.mg.merp.warehouse.BinLocationProccessStrategy;
import com.mg.merp.warehouse.HistoryNotFoundException;
import com.mg.merp.warehouse.SerialNumberProccessStrategy;
import com.mg.merp.warehouse.WareCardServiceLocal;
import com.mg.merp.warehouse.WarehouseBatchHistoryServiceLocal;
import com.mg.merp.warehouse.WarehouseBatchServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseRollbackStrategy;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.support.DefaultBinLocationProccessStrategy;
import com.mg.merp.warehouse.support.DefaultSerialNumberProccessStrategy;

import java.math.BigDecimal;
import java.util.List;

/**
 * Стратегия отката этапа ДО "Отработка по складу"
 *
 * @author Valentin A. Poroxnenko
 * @author Artem V. Sharapov
 * @version $Id: WarehouseRollbackFactStrategy.java,v 1.10 2008/05/30 12:52:55 sharapov Exp $
 */
public class WarehouseRollbackFactStrategy implements WarehouseRollbackStrategy {

  protected BinLocationProccessStrategy binLocationProccessStrategy = new DefaultBinLocationProccessStrategy();
  protected SerialNumberProccessStrategy serialNumberProccessStrategy = new DefaultSerialNumberProccessStrategy();


  public void rollback(WarehouseProcessDocumentLineData docLineData) {
    doRollback(docLineData.getDocumentSpecItem().getData2());
    doRollback(docLineData.getDocumentSpecItem().getData1());
  }

  private void doRollback(Integer historyId) {
    if (historyId == null)
      return;

    PersistentManager pm = ServerUtils.getPersistentManager();
    StockBatchHistory history = pm.find(StockBatchHistory.class, historyId);
    // отсутствует история, откат невозможен
    if (history == null)
      throw new HistoryNotFoundException();
    switch (history.getKind()) {
      case IN:
        rollbackReceipt(history);
        break;
      case OUT:
        rollbackIssue(history);
        break;
    }
  }

  /**
   * Откат списания
   *
   * @param historyList список истории партии
   */
  protected void rollbackIssue(StockBatchHistory history) {
    if (history == null)
      return;

    ApplicationDictionary applicationDictionary = ApplicationDictionaryLocator.locate();
    WarehouseBatchServiceLocal wbServ = (WarehouseBatchServiceLocal) applicationDictionary.getBusinessService(WarehouseBatchServiceLocal.LOCAL_SERVICE_NAME);
    WarehouseBatchHistoryServiceLocal wbhServ = (WarehouseBatchHistoryServiceLocal) applicationDictionary.getBusinessService(WarehouseBatchHistoryServiceLocal.LOCAL_SERVICE_NAME);
    WareCardServiceLocal wcServ = (WareCardServiceLocal) applicationDictionary.getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);

    StockBatch sb = history.getStockBatch();
    StockCard sc = sb.getStockCard();
    if (sb.getEndQuan() != null && history.getQuantity() != null) {
      sb.setEndQuan(sb.getEndQuan().add(history.getQuantity()));
      sc.setQuantity(sc.getQuantity().add(history.getQuantity()));
    }
    if (sb.getEndQuan2() != null && history.getQuantity2() != null) {
      sb.setEndQuan2(sb.getEndQuan2().add(history.getQuantity2()));
      sc.setQuantity2(sc.getQuantity2().add(history.getQuantity2()));
    }
    wbServ.store(sb);
    wcServ.store(sc);
    wbhServ.erase(history);

    binLocationProccessStrategy.rollbackOnIssue(history);
    serialNumberProccessStrategy.rollbackOnIssue(history);

    rollbackIssue(history.getStockBatchHistory());
  }

  /**
   * Откат прихода
   *
   * @param historyList список истории партии
   */
  protected void rollbackReceipt(StockBatchHistory history) {
    if (history == null)
      return;

    ApplicationDictionary applicationDictionary = ApplicationDictionaryLocator.locate();
    WareCardServiceLocal wcServ = (WareCardServiceLocal) applicationDictionary.getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);
    WarehouseBatchServiceLocal wbServ = (WarehouseBatchServiceLocal) applicationDictionary.getBusinessService(WarehouseBatchServiceLocal.LOCAL_SERVICE_NAME);
    WarehouseBatchHistoryServiceLocal wbhServ = (WarehouseBatchHistoryServiceLocal) applicationDictionary.getBusinessService(WarehouseBatchHistoryServiceLocal.LOCAL_SERVICE_NAME);

    StockBatch stockBatch = history.getStockBatch();
    StockCard stockCard = stockBatch.getStockCard();
    BigDecimal q1 = history.getQuantity();
    BigDecimal q2 = history.getQuantity2();

    List<Object[]> issueQuans = MiscUtils.convertUncheckedList(Object[].class, OrmTemplate.getInstance().findByNamedParam("select sum(sbh.Quantity), sum(sbh.Quantity2) from com.mg.merp.warehouse.model.StockBatchHistory sbh where sbh.StockBatch = :stockBatch and sbh.Kind = com.mg.merp.warehouse.model.StockBatchHistoryKind.OUT", "stockBatch", stockBatch));
    BigDecimal issueQuan = (BigDecimal) issueQuans.get(0)[0];
    if (issueQuan == null)
      issueQuan = BigDecimal.ZERO;
    switch (stockBatch.getBeginQuan().subtract(q1).compareTo(issueQuan)) {
      case -1:
        //списаний больше чем останется в партии после отката данной истории
        throw new BatchNotAliveException();
      case 0:
        //это последняя история в партии и на ней нет списаний
        wbhServ.erase(history);
        wbServ.erase(stockBatch);
        stockCard.setQuantity(MathUtils.subtractNullable(stockCard.getQuantity(), q1, Constants.QUANTITY_ROUND_CONTEXT_EXT));
        stockCard.setQuantity2(MathUtils.subtractNullable(stockCard.getQuantity2(), q2, Constants.QUANTITY_ROUND_CONTEXT_EXT));
        wcServ.store(stockCard);
        break;
      case 1:
        //у партии есть истории, просто меняем количество
        stockBatch.setBeginQuan(MathUtils.subtractNullable(stockBatch.getBeginQuan(), q1, Constants.QUANTITY_ROUND_CONTEXT_EXT));
        stockBatch.setBeginQuan2(MathUtils.subtractNullable(stockBatch.getBeginQuan2(), q2, Constants.QUANTITY_ROUND_CONTEXT_EXT));
        stockBatch.setEndQuan(MathUtils.subtractNullable(stockBatch.getEndQuan(), q1, Constants.QUANTITY_ROUND_CONTEXT_EXT));
        stockBatch.setEndQuan2(MathUtils.subtractNullable(stockBatch.getEndQuan2(), q2, Constants.QUANTITY_ROUND_CONTEXT_EXT));

        stockCard.setQuantity(MathUtils.subtractNullable(stockCard.getQuantity(), q1, Constants.QUANTITY_ROUND_CONTEXT_EXT));
        stockCard.setQuantity2(MathUtils.subtractNullable(stockCard.getQuantity2(), q2, Constants.QUANTITY_ROUND_CONTEXT_EXT));

        wbhServ.erase(history);
        wbServ.store(stockBatch);
        wcServ.store(stockCard);
        break;
    }

    binLocationProccessStrategy.rollbackOnReceipt(history);
    serialNumberProccessStrategy.rollbackOnReceipt(history);

    wcServ.deleteStockCards(stockCard);
  }

}
