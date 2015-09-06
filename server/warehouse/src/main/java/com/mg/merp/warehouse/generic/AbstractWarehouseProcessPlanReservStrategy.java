/* AbstractWarehouseProcessPlanResrvStrategy.java
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

import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.reference.model.Employees;
import com.mg.merp.warehouse.WareCardServiceLocal;
import com.mg.merp.warehouse.WarehousePlanHistoryServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessStrategy;
import com.mg.merp.warehouse.model.StockCard;
import com.mg.merp.warehouse.model.StockPlanHistory;
import com.mg.merp.warehouse.model.StockPlanHistoryDirection;
import com.mg.merp.warehouse.model.StockPlanHistoryKind;
import com.mg.merp.warehouse.model.Warehouse;

import java.util.EnumSet;

/**
 * Абстрактный класс-предок для классов-стратегий складского планирования и резервирования
 *
 * @author Valentin A. Poroxnenko
 * @author Oleg V. Safonov
 * @version $Id: AbstractWarehouseProcessPlanReservStrategy.java,v 1.6 2008/04/18 15:18:19 safonov
 *          Exp $
 */
public abstract class AbstractWarehouseProcessPlanReservStrategy implements
    WarehouseProcessStrategy {

  private DocFlowPluginInvokeParams params;

  public AbstractWarehouseProcessPlanReservStrategy(
      DocFlowPluginInvokeParams params) {
    this.params = params;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.WarehouseProcessStrategy#process(com.mg.merp.warehouse.WarehouseProcessDocumentLineData)
   */
  public void process(WarehouseProcessDocumentLineData docLineData) {
    doProcess(docLineData);
  }

  /**
   * Создание КСУ
   *
   * @param docSpec спецификация документа
   * @return КСУ
   */
  private StockCard fillStockCard(WarehouseProcessDocumentLineData docLineData, StockPlanHistoryKind kind, StockPlanHistoryDirection dir) {
    WareCardServiceLocal service = (WareCardServiceLocal) ApplicationDictionaryLocator.locate()
        .getBusinessService(WareCardServiceLocal.LOCAL_SERVICE_NAME);
    Employees mol = docLineData.getDstMol();
    Warehouse stock = docLineData.getDstStock();
    if (StockPlanHistoryDirection.OUT.equals(dir) || EnumSet.of(StockPlanHistoryKind.IN_RESERVE, StockPlanHistoryKind.OUT_RESERVE).contains(kind)) {
      mol = docLineData.getSrcMol();
      stock = docLineData.getSrcStock();
    }

    StockCard stockCard = service.findStockCard(stock, mol, docLineData.getCatalog(), false);
    if (stockCard == null) {
      // КСУ отсутствует
      stockCard = service.initialize();
      stockCard.setCatalog(docLineData.getCatalog());
      stockCard.setMol(mol);

      createQuanAttribute(stockCard, docLineData, dir);

      stockCard.setStock(stock);

      service.create(stockCard);
    } else {
      updateQuanAttribute(stockCard, docLineData, dir);
      service.store(stockCard);
    }
    return stockCard;
  }

  public void makeHistory(WarehouseProcessDocumentLineData docLineData, StockPlanHistoryKind kind, StockPlanHistoryDirection dir) {
    WarehousePlanHistoryServiceLocal sphServ = (WarehousePlanHistoryServiceLocal) ApplicationDictionaryLocator.locate()
        .getBusinessService(WarehousePlanHistoryServiceLocal.LOCAL_SERVICE_NAME);

    StockCard stockCard = fillStockCard(docLineData, kind, dir);

    StockPlanHistory history = new StockPlanHistory();
    history.setDirection(dir);
    history.setDocHead(docLineData.getDocumentSpec().getDocHead());
    history.setDocSpec(docLineData.getDocumentSpec());
    history.setKind(kind);
    history.setProcessDate(params.getProcessDate());
    history.setQuantity(docLineData.getQuantity1());
    history.setQuantity2(docLineData.getQuantity2());
    history.setStockCard(stockCard);
    history.setSysDateTime(DateTimeUtils.nowDate());
    history.setProcessDate(params.getProcessDate());

    sphServ.create(history);

    switch (dir) {
      case IN:
        docLineData.getDocumentSpecItem().setData1(history.getId());
        break;
      case OUT:
        docLineData.getDocumentSpecItem().setData2(history.getId());
        break;
    }
  }

  /**
   * инициализация КСУ
   *
   * @param stockCard   КСУ
   * @param docLineData информация о спецификации
   * @param direction   направление
   */
  protected abstract void createQuanAttribute(StockCard stockCard,
                                              WarehouseProcessDocumentLineData docLineData, StockPlanHistoryDirection direction);

  /**
   * изменение КСУ
   *
   * @param stockCard   КСУ
   * @param docLineData информация о спецификации
   * @param direction   направление
   */
  protected abstract void updateQuanAttribute(StockCard stockCard,
                                              WarehouseProcessDocumentLineData docLineData, StockPlanHistoryDirection direction);

  /**
   * реализация процесса
   *
   * @param docLineData информация о спецификации
   */
  protected abstract void doProcess(WarehouseProcessDocumentLineData docLineData);

}
