/*
 * DefaultBinLocationProccessStrategy.java
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
package com.mg.merp.warehouse.support;

import com.mg.framework.api.BusinessException;
import com.mg.framework.api.math.RoundContext;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.PersistentManager;
import com.mg.framework.api.orm.ResultTransformer;
import com.mg.framework.api.ui.FormActionListener;
import com.mg.framework.api.ui.FormEvent;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.framework.utils.StringUtils;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.warehouse.BinLocationProccessStrategy;
import com.mg.merp.warehouse.BinLocationServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.model.BinLocation;
import com.mg.merp.warehouse.model.BinLocationDetail;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.Warehouse;
import com.mg.merp.warehouse.support.ui.BinLocationData;
import com.mg.merp.warehouse.support.ui.InputBinLocationDlg;
import com.mg.merp.warehouse.support.ui.SelectBinLocationDlg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Стандартная реализация стратегии обработки/отката секций хранения
 *
 * @author Artem V. Sharapov
 * @version $Id: DefaultBinLocationProccessStrategy.java,v 1.2 2008/07/15 08:24:22 safonov Exp $
 */
public class DefaultBinLocationProccessStrategy implements BinLocationProccessStrategy {

  private final RoundContext ROUND_CONTEXT_6 = new RoundContext(6);
  private final String INPUT_DIALOG_NAME = "com.mg.merp.warehouse.InputBinLocationDlg"; //$NON-NLS-1$
  private final String SELECT_DIALOG_NAME = "com.mg.merp.warehouse.SelectBinLocationDlg"; //$NON-NLS-1$
  private PersistentManager persistentManager = ServerUtils.getPersistentManager();
  private OrmTemplate ormTemplate = OrmTemplate.getInstance();

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.BinLocationProccessStrategy#proccessOnReceipt(com.mg.merp.warehouse.WarehouseProcessDocumentLineData, com.mg.merp.warehouse.model.StockBatch, com.mg.merp.warehouse.WarehouseProcessListener)
   */
  public void proccessOnReceipt(WarehouseProcessDocumentLineData docLineData, StockBatch stockBatch, WarehouseProcessListener processListener) {
    doProccessOnReceipt(docLineData, stockBatch, processListener);
  }

  /**
   * Выполнить размещение товара в секции хранения при приходовании на склад
   *
   * @param docLineData     - данные по спецификации
   * @param stockBatch      - партия
   * @param processListener - слушатель складского процессора
   */
  protected void doProccessOnReceipt(WarehouseProcessDocumentLineData docLineData, StockBatch stockBatch, final WarehouseProcessListener processListener) {
    internalProccessOnReceipt(BigDecimal.ZERO, docLineData.getQuantity1(), docLineData.getCatalog(), docLineData, stockBatch, docLineData.getDstStock(), processListener);
  }

  private void internalProccessOnReceipt(final BigDecimal totalQuantity, final BigDecimal quantity, final Catalog catalog, final WarehouseProcessDocumentLineData docLineData, final StockBatch stockBatch, final Warehouse warehouse, final WarehouseProcessListener processListener) {
    if (totalQuantity.compareTo(docLineData.getQuantity1()) >= 0)
      processListener.completed();
    else {
      final InputBinLocationDlg inputBinLocationDialog = (InputBinLocationDlg) ApplicationDictionaryLocator.locate().getWindow(INPUT_DIALOG_NAME);
      inputBinLocationDialog.addOkActionListener(new FormActionListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
         */
        public void actionPerformed(FormEvent event) {
          BinLocation binLocation = inputBinLocationDialog.getBinLocation();
          BigDecimal quantityTmp = inputBinLocationDialog.getQuantity();
          BinLocationOccupancy binLocationOccupancy = checkBinLocationOccupancy(catalog, binLocation, warehouse, quantity);
          switch (binLocationOccupancy) {
            case AVALIBLE:
              BigDecimal totalQuantityTmp = totalQuantity.add(quantityTmp);
              occupyBinLocation(binLocation, stockBatch, quantityTmp, docLineData.getDocumentSpec());
              internalProccessOnReceipt(totalQuantityTmp, docLineData.getQuantity1().subtract(totalQuantityTmp), catalog, docLineData, stockBatch, warehouse, processListener);
              break;
            case PRODUCT_MISMATCH:
              throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.BIN_LOCATION_PRODUCT_MISSMATCH), catalog.getCode().trim(), binLocation.getCode().trim()));
            case NOT_FOUND:
              throw new BusinessException(String.format(Messages.getInstance().getMessage(Messages.BIN_LOCATION_NOT_FOUND), binLocation.getCode().trim()));
          }
        }
      });
      inputBinLocationDialog.addCancelActionListener(new FormActionListener() {

        /* (non-Javadoc)
         * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
         */
        public void actionPerformed(FormEvent event) {
          processListener.aborted();
        }
      });
      inputBinLocationDialog.execute(docLineData.getQuantity1().subtract(totalQuantity), catalog.getCode(), catalog.getFullName(), warehouse);
    }
  }

  /**
   * Разместить товар в секции хранения в партии
   *
   * @param binLocation - секция хранения на складе
   * @param stockBatch  - партия
   * @param quantity    - кол-во товара, размещаемого в секции
   * @param docSpec     - позиция спецификации документа
   */
  private void occupyBinLocation(BinLocation binLocation, StockBatch stockBatch, BigDecimal quantity, DocSpec docSpec) {
    persistentManager.persist(initializeBinLocationDetail(binLocation, stockBatch, quantity, docSpec.getId(), BinLocationServiceLocal.RECEIPT_KIND));
  }

  /**
   * Инициализировать секцию хранения в партии
   *
   * @param binLocation - секция хранения на складе
   * @param stockBatch  - партия
   * @param quantity    - кол-во товара, размещаемого в секции
   * @param docSpecId   - идентификатор позиции спецификации документа
   * @param kind        - тип движения: 0 - приход, 1 - расход
   * @return секция хранения в партии
   */
  private BinLocationDetail initializeBinLocationDetail(BinLocation binLocation, StockBatch stockBatch, BigDecimal quantity, Integer docSpecId, short kind) {
    BinLocationDetail binLocationDetail = new BinLocationDetail();
    binLocationDetail.setBinLocation(binLocation);
    binLocationDetail.setStockBatch(stockBatch);
    binLocationDetail.setQuantity(quantity);
    binLocationDetail.setDocSpecId(docSpecId);
    binLocationDetail.setKind(kind);
    return binLocationDetail;
  }

  /**
   * Выполнить проверку, подходит ли выбранная секция по размерам и вместимости, и существует ли
   * вообще
   *
   * @param catalog     - позиция кталога
   * @param binLocation - секция хранения на складе
   * @param warehouse   - склад
   * @param quantity    - кол-во
   * @return результат проверки
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  protected BinLocationOccupancy checkBinLocationOccupancy(Catalog catalog, BinLocation binLocation, Warehouse warehouse, BigDecimal quantity) {
    List<BinLocationResult> result = ormTemplate.findByNamedQueryAndNamedParam("Warehouse.DefaultBinLocationProccessStrategy.checkBinLocationOccupancy", //$NON-NLS-1$
        new String[]{"binLocation", "warehouse"}, new Object[]{binLocation, warehouse}, //$NON-NLS-1$ //$NON-NLS-2$
        new ResultTransformer<BinLocationResult>() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
           */
          public BinLocationResult transformTuple(Object[] tuple, String[] aliases) {
            return new BinLocationResult((BinLocation) tuple[0], (String) tuple[1]);
          }
        });

    BinLocationOccupancy binLocationOccupancy = BinLocationOccupancy.AVALIBLE;
    String catalogCode = catalog.getUpCode().trim();
    BigDecimal quantityInSection = BigDecimal.ZERO;
    for (BinLocationResult binLocationResult : result) {
      if (binLocationResult.binLocation == null) {
        binLocationOccupancy = BinLocationOccupancy.NOT_FOUND;
        break;
      }
      quantityInSection = getQuantityInSection(binLocationResult.binLocation, warehouse, binLocationResult.catalogCode);
      if (MathUtils.compareToZero(quantityInSection) > 0
          && !StringUtils.stringNullOrEmpty(binLocationResult.catalogCode)
          && catalogCode.compareTo(binLocationResult.catalogCode) != 0) {
        binLocationOccupancy = BinLocationOccupancy.PRODUCT_MISMATCH;
        break;
      } else if (MathUtils.compareToZero(quantityInSection) > 0) {
        binLocationOccupancy = BinLocationOccupancy.AVALIBLE;
        break;
      }
    }
    return binLocationOccupancy;
  }

  /**
   * Получить кол-во товара, размещенного в секции хранения на складе
   *
   * @param binLocation - секция хранения на складе
   * @param warehouse   - склад
   * @param catalogCode - код товара
   * @return кол-во товара, размещенного в секции хранения на складе
   */
  private BigDecimal getQuantityInSection(BinLocation binLocation, Warehouse warehouse, String catalogCode) {
    BigDecimal inQuantity = getQuantityInSectionByKind(binLocation, warehouse, catalogCode, BinLocationServiceLocal.RECEIPT_KIND);
    BigDecimal outQuantity = getQuantityInSectionByKind(binLocation, warehouse, catalogCode, BinLocationServiceLocal.ISSUE_KIND);
    return MathUtils.subtract(inQuantity, outQuantity, ROUND_CONTEXT_6);
  }

  /**
   * Получить кол-во товара, размещенного в секции хранения на складе с учетом типа движения товара
   *
   * @param binLocation - секция хранения на складе
   * @param warehouse   - склад
   * @param catalogCode - код товара
   * @param kind        - тип движения товара: 0 - приход, 1 - расход
   * @return кол-во товара, размещенного в секции хранения на складе с учетом типа движения товара
   */
  @SuppressWarnings("unchecked") //$NON-NLS-1$
  private BigDecimal getQuantityInSectionByKind(BinLocation binLocation, Warehouse warehouse, String catalogCode, short kind) {
    BigDecimal quantityInSection = BigDecimal.ZERO;
    List<BigDecimal> quantityList = ormTemplate.findByNamedQueryAndNamedParam("Warehouse.DefaultBinLocationProccessStrategy.getQuantityInSectionByKind", //$NON-NLS-1$
        new String[]{"binLocation", "warehouse", "catalogCode", "kind"}, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        new Object[]{binLocation, warehouse, catalogCode, kind});

    if (!quantityList.isEmpty()) {
      quantityInSection = quantityList.get(0);
      return quantityInSection == null ? BigDecimal.ZERO : quantityInSection;
    } else
      return quantityInSection;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.BinLocationProccessStrategy#proccessOnIssue(com.mg.merp.warehouse.WarehouseProcessDocumentLineData, java.util.List, com.mg.merp.warehouse.WarehouseProcessListener)
   */
  public void proccessOnIssue(WarehouseProcessDocumentLineData docLineData, List<StockBatch> stockBatches, WarehouseProcessListener processListener) {
    doProccessOnIssue(docLineData, stockBatches, processListener);
  }

  protected void doProccessOnIssue(final WarehouseProcessDocumentLineData docLineData, List<StockBatch> stockBatches, final WarehouseProcessListener processListener) {
    internalProccessOnIssue(stockBatches.iterator(), docLineData, docLineData.getSrcStock(), stockBatches, processListener);
  }

  /**
   * Выполнить обработку секций хранения при списании товара
   *
   * @param stockBatchIterator - итератор партий
   * @param docLineData        - данные по спецификации для отработки
   * @param warehouse          - склад
   * @param stockBatches       - список партий
   * @param processListener    - слушатель складского процессора
   */
  private void internalProccessOnIssue(final Iterator<StockBatch> stockBatchIterator, final WarehouseProcessDocumentLineData docLineData, final Warehouse warehouse, final List<StockBatch> stockBatches, final WarehouseProcessListener processListener) {
    if (!stockBatchIterator.hasNext())
      processListener.completed();
    else {
      final StockBatch stockBatch = stockBatchIterator.next();
      Catalog catalog = docLineData.getCatalog();
      BigDecimal quantityToPerform = MathUtils.subtractNullable(stockBatch.getBeginQuan(), stockBatch.getEndQuan(), ROUND_CONTEXT_6);
      List<BinLocationData> binLocationDataItems = getStockBatchBinLocations(stockBatch);

      if (binLocationDataItems.size() > 1 && getTotalQuantityInSections(binLocationDataItems).compareTo(quantityToPerform) > 0) {
        final SelectBinLocationDlg selectBinLocationDlg = (SelectBinLocationDlg) ApplicationDictionaryLocator.locate().getWindow(SELECT_DIALOG_NAME);
        selectBinLocationDlg.addOkActionListener(new FormActionListener() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
           */
          public void actionPerformed(FormEvent event) {
            freeBinLocations(selectBinLocationDlg.getSelectedItemsList(), docLineData.getDocumentSpec().getId());
            internalProccessOnIssue(stockBatchIterator, docLineData, warehouse, stockBatches, processListener);
          }
        });
        selectBinLocationDlg.addCancelActionListener(new FormActionListener() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.ui.FormActionListener#actionPerformed(com.mg.framework.api.ui.FormEvent)
           */
          public void actionPerformed(FormEvent event) {
            processListener.aborted();
          }
        });
        selectBinLocationDlg.execute(quantityToPerform, binLocationDataItems, catalog.getCode(), catalog.getFullName(), stockBatch.getNumberLot(), stockBatch.getVendorLot());
      } else {
        proccessBinLocationData(binLocationDataItems, quantityToPerform);
        freeBinLocations(binLocationDataItems, docLineData.getDocumentSpec().getId());
        internalProccessOnIssue(stockBatchIterator, docLineData, warehouse, stockBatches, processListener);
      }
    }
  }

  /**
   * Выполнить подготовку перед созданием секций хранения в партии
   *
   * @param binLocationDataItems - список секций хранения в партии
   * @param quantity             - кол-во
   */
  private void proccessBinLocationData(List<BinLocationData> binLocationDataItems, BigDecimal quantity) {
    for (BinLocationData binLocationData : binLocationDataItems) {
      StockBatch stockBatch = binLocationData.getStockBatch();
      BigDecimal quantityToPerform = MathUtils.subtractNullable(stockBatch.getBeginQuan(), stockBatch.getEndQuan(), ROUND_CONTEXT_6);
      if (quantityToPerform.compareTo(binLocationData.getQuantityInSection()) > 0) {
        quantityToPerform = binLocationData.getQuantityInSection();
        binLocationData.setQuantityToPerform(quantityToPerform);
        quantity = MathUtils.subtractNullable(quantity, quantityToPerform, ROUND_CONTEXT_6);
      } else if (quantity.compareTo(quantityToPerform) >= 0) {
        binLocationData.setQuantityToPerform(quantityToPerform);
        quantity = MathUtils.subtractNullable(quantity, quantityToPerform, ROUND_CONTEXT_6);
      } else
        binLocationData.setQuantityToPerform(quantity);
    }
  }

  /**
   * Получить информациию о кол-ве товара в секции хранения
   *
   * @param stockBatch - партия
   * @return информациия о кол-ве товара в секции хранения
   */
  private List<BinLocationData> getStockBatchBinLocations(StockBatch stockBatch) {
    final List<BinLocationData> stockBatchBinLocations = new ArrayList<BinLocationData>();
    List<BinLocationData> result = ormTemplate.findByNamedQueryAndNamedParam("Warehouse.DefaultBinLocationProccessStrategy.getStockBatchBinLocations", //$NON-NLS-1$
        new String[]{"stockBatch"}, new Object[]{stockBatch}, //$NON-NLS-1$
        new ResultTransformer<BinLocationData>() {

          /* (non-Javadoc)
           * @see com.mg.framework.api.orm.ResultTransformer#transformTuple(java.lang.Object[], java.lang.String[])
           */
          public BinLocationData transformTuple(Object[] tuple, String[] aliases) {
            BigDecimal receiptQuan = (BigDecimal) tuple[2];
            BigDecimal issueQuan = (BigDecimal) tuple[3];
            BigDecimal quantityInSection = MathUtils.subtractNullable(receiptQuan, issueQuan, ROUND_CONTEXT_6);
            return new BinLocationData((BinLocation) tuple[0], (StockBatch) tuple[1], quantityInSection);
          }
        });
    for (BinLocationData binLocationData : result) {
      if (MathUtils.compareToZero(binLocationData.getQuantityInSection()) > 0)
        stockBatchBinLocations.add(binLocationData);
    }
    return stockBatchBinLocations;
  }

  /**
   * Получить общее кол-во товара в секциях хранения
   *
   * @param binLocationDataItems - список секцияй хранения
   * @return общее кол-во товара в секциях хранения
   */
  private BigDecimal getTotalQuantityInSections(List<BinLocationData> binLocationDataItems) {
    BigDecimal result = BigDecimal.ZERO;
    for (BinLocationData binLocationTableModelItem : binLocationDataItems) {
      BigDecimal quantityInSection = binLocationTableModelItem.getQuantityInSection() == null ? BigDecimal.ZERO : binLocationTableModelItem.getQuantityInSection();
      result = result.add(quantityInSection);
    }
    return result;
  }

  /**
   * Выполнить списание товара с секциий хранения
   *
   * @param binLocationDataItems - список секций хранения
   * @param docSpecId            - идентификатор позиции спецификации
   */
  private void freeBinLocations(List<BinLocationData> binLocationDataItems, Integer docSpecId) {
    List<BinLocationDetail> binLocationDetailList = new ArrayList<BinLocationDetail>();
    for (BinLocationData binLocationData : binLocationDataItems)
      binLocationDetailList.add(initializeBinLocationDetail(binLocationData.getBinLocation(), binLocationData.getStockBatch(), binLocationData.getQuantityToPerform(), docSpecId, BinLocationServiceLocal.ISSUE_KIND));
    createBinLocationDetail(binLocationDetailList);
  }

  /**
   * Выполнить создание секций хранения в партии
   *
   * @param binLocationDetailList - список секций хранения
   */
  private void createBinLocationDetail(List<BinLocationDetail> binLocationDetailList) {
    for (BinLocationDetail binLocationDetail : binLocationDetailList)
      persistentManager.persist(binLocationDetail);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.BinLocationProccessStrategy#rollbackOnReceipt(com.mg.merp.warehouse.model.StockBatchHistory)
   */
  public void rollbackOnReceipt(StockBatchHistory history) {
    doRollbackOnReceipt(history);
  }

  /**
   * Выполнить откат обработки секций хранения при приходовании товара на склад
   *
   * @param history - история партии
   */
  protected void doRollbackOnReceipt(StockBatchHistory history) {
    ormTemplate.bulkUpdateByNamedQuery("Warehouse.DefaultBinLocationProccessStrategy.deleteReceiptBinLocationDetail", //$NON-NLS-1$
        new String[]{"stockBatch", "docSpecId"}, //$NON-NLS-1$ //$NON-NLS-2$
        new Object[]{history.getStockBatch(), history.getDocSpec().getId()});
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.BinLocationProccessStrategy#rollbackOnIssue(com.mg.merp.warehouse.model.StockBatchHistory)
   */
  public void rollbackOnIssue(StockBatchHistory history) {
    doRollbackOnIssue(history);
  }

  /**
   * Выполнить откат обработки секций хранения при списании товара со склада
   *
   * @param history - история партии
   */
  protected void doRollbackOnIssue(StockBatchHistory history) {
    ormTemplate.bulkUpdateByNamedQuery("Warehouse.DefaultBinLocationProccessStrategy.deleteIssueBinLocationDetail", //$NON-NLS-1$
        new String[]{"stockBatch", "docSpecId"}, //$NON-NLS-1$ //$NON-NLS-2$
        new Object[]{history.getStockBatch(), history.getDocSpec().getId()});
  }

  /**
   * Наличие секции по размерам и вместимости
   */
  public enum BinLocationOccupancy {

    /**
     * Доступна для размещения товара
     */
    AVALIBLE,

    /**
     * Не найдена
     */
    NOT_FOUND,

    /**
     * Не доступна для размещения данного товара
     */
    PRODUCT_MISMATCH

  }

  private class BinLocationResult {
    BinLocation binLocation;
    String catalogCode;

    public BinLocationResult(BinLocation binLocation, String catalogCode) {
      this.binLocation = binLocation;
      this.catalogCode = catalogCode == null ? StringUtils.EMPTY_STRING : catalogCode.toUpperCase().trim();
    }
  }

}
