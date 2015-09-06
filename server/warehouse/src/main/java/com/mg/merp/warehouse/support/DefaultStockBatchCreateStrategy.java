/* DefaultStockBatchUniqueStrategy.java
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

import com.mg.framework.api.math.Constants;
import com.mg.framework.api.orm.Criteria;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.DateTimeUtils;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.docflow.DocFlowPluginInvokeParams;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.warehouse.BatchPriceStrategy;
import com.mg.merp.warehouse.WarehouseBatchServiceLocal;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.WarehouseProcessListener;
import com.mg.merp.warehouse.WarehouseProcessStrategy;
import com.mg.merp.warehouse.generic.AbstractStockBatchCreateStrategy;
import com.mg.merp.warehouse.model.StockBatch;
import com.mg.merp.warehouse.model.StockBatchHistory;
import com.mg.merp.warehouse.model.StockBatchHistoryKind;
import com.mg.merp.warehouse.model.StockCard;

import java.math.BigDecimal;
import java.util.List;

/**
 * Стратегия создания складской партии "по-умолчанию"
 *
 * @author Valentin A. Poroxnenko
 * @author Artem V. Sharapov
 * @version $Id: DefaultStockBatchCreateStrategy.java,v 1.11 2008/10/30 10:06:46 safonov Exp $
 */
public class DefaultStockBatchCreateStrategy extends AbstractStockBatchCreateStrategy {

  private final static String CUSTOMS_DECLARATION_ATTRIBUTE = "CustomsDeclaration"; //$NON-NLS-1$
  private final static String COUNTRY_OF_ORIGIN_ATTRIBUTE = "CountryOfOrigin"; //$NON-NLS-1$

  private WarehouseBatchServiceLocal service;

  public DefaultStockBatchCreateStrategy(WarehouseProcessStrategy processStrategy, WarehouseProcessListener listener, boolean doInteractive) {
    super(processStrategy, listener, doInteractive);
  }

  private WarehouseBatchServiceLocal getWarehouseBatchService() {
    if (service == null)
      service = (WarehouseBatchServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService(WarehouseBatchServiceLocal.LOCAL_SERVICE_NAME);
    return service;
  }

  /**
   * Изменить/создать партию
   *
   * @param docLineData - данные по спецификации
   * @param stockCard   - КСУ
   * @param stockBatch  - складская партия
   * @param priceNat    - цена
   * @param priceCur    - цена в валюте
   * @param params      - параметры этапа "отработка по складу"
   * @return партия
   */
  private StockBatch modifyOrCreateStockBatch(WarehouseProcessDocumentLineData docLineData, StockCard stockCard, StockBatch stockBatch, BigDecimal priceNat, BigDecimal priceCur, DocFlowPluginInvokeParams params) {
    if (stockBatch == null) {
      // создаём новую партию
      DocSpec docSpec = docLineData.getDocumentSpec();
      DocHead docHead = docSpec.getDocHead();
      stockBatch = getWarehouseBatchService().initialize();
      stockBatch.setStockCard(stockCard);
      stockBatch.setContractor(docHead.getFrom());
      stockBatch.setDocType(docHead.getDocType().getCode().trim());
      stockBatch.setDocNumber(docHead.getDocNumber().trim());
      stockBatch.setDocDate(docHead.getDocDate());
      stockBatch.setCurrencyCode(docHead.getCurrency().getCode().trim());
      stockBatch.setBestBefore(docLineData.getDocumentSpec().getBestBefore());
      stockBatch.setBeginQuan(docLineData.getQuantity1());
      stockBatch.setEndQuan(docLineData.getQuantity1());
      stockBatch.setCreateDate(params.getProcessDate());
      stockBatch.setBeginQuan2(docLineData.getQuantity2());
      stockBatch.setEndQuan2(docLineData.getQuantity2());
      stockBatch.setPriceNat(priceNat);
      stockBatch.setPriceCur(priceCur);
      stockBatch.setCustomsDeclaration(docSpec.getCustomsDeclaration());
      stockBatch.setCountryOfOrigin(docSpec.getCountryOfOrigin());

      Integer sbId = getWarehouseBatchService().create(stockBatch);
      stockBatch.setNumberLot(sbId.toString());
    } else {
      stockBatch.setBeginQuan(MathUtils.addNullable(stockBatch.getBeginQuan(), docLineData.getQuantity1(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
      stockBatch.setEndQuan(MathUtils.addNullable(stockBatch.getEndQuan(), docLineData.getQuantity1(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
      stockBatch.setCreateDate(params.getProcessDate());
      stockBatch.setBeginQuan2(MathUtils.addNullable(stockBatch.getBeginQuan2(), docLineData.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT_EXT));
      stockBatch.setEndQuan2(MathUtils.addNullable(stockBatch.getEndQuan2(), docLineData.getQuantity2(), Constants.QUANTITY_ROUND_CONTEXT_EXT));

      getWarehouseBatchService().store(stockBatch);
    }
    return stockBatch;
  }

  /**
   * Найти партию
   *
   * @param docSpec   - позиция спецификации документа
   * @param stockCard - КСУ
   * @param priceNat  - цена
   * @param priceCur  - цена в валюте
   * @return партия или <code>null</code> если не найдена
   */
  private StockBatch findStockBatch(DocSpec docSpec, StockCard stockCard, BigDecimal priceNat, BigDecimal priceCur) {
    DocHead docHead = docSpec.getDocHead();

    Criteria criteria = OrmTemplate.createCriteria(StockBatch.class)
        .add(Restrictions.eq("StockCard", stockCard))
        .add(Restrictions.eq("Contractor", docHead.getFrom()))
        .add(Restrictions.eq("DocType", docHead.getDocType().getCode().trim()))
        .add(Restrictions.eq("DocNumber", docHead.getDocNumber().trim()))
        .add(Restrictions.eq("DocDate", docHead.getDocDate()))
        .add(Restrictions.eq("CurrencyCode", docHead.getCurrency().getCode().trim()))
        .add(Restrictions.eq("PriceNat", priceNat))
        .add(Restrictions.eq("PriceCur", priceCur));
    if (docSpec.getBestBefore() != null)
      criteria.add(Restrictions.eq("BestBefore", docSpec.getBestBefore()));

    if (docSpec.getCustomsDeclaration() != null)
      criteria.add(Restrictions.eq(CUSTOMS_DECLARATION_ATTRIBUTE, docSpec.getCustomsDeclaration()));
    else
      criteria.add(Restrictions.isNull(CUSTOMS_DECLARATION_ATTRIBUTE));

    if (docSpec.getCountryOfOrigin() != null)
      criteria.add(Restrictions.eq(COUNTRY_OF_ORIGIN_ATTRIBUTE, docSpec.getCountryOfOrigin()));
    else
      criteria.add(Restrictions.isNull(COUNTRY_OF_ORIGIN_ATTRIBUTE));

    List<StockBatch> sbL = OrmTemplate.getInstance().findByCriteria(criteria);
    return sbL.isEmpty() ? null : sbL.get(0);
  }

  @Override
  protected StockBatchHistory doFillStockBatchHistory(WarehouseProcessDocumentLineData docLineData, StockBatch stockBatch, DocFlowPluginInvokeParams params) {
    StockBatchHistory history = new StockBatchHistory();
    history.setDocHead(docLineData.getDocumentSpec().getDocHead());
    history.setDocSpec(docLineData.getDocumentSpec());
    history.setStockBatch(stockBatch);
    history.setDateTime(DateTimeUtils.nowDate());
    //приход
    history.setKind(StockBatchHistoryKind.IN);
    history.setQuantity(docLineData.getQuantity1());
    history.setProcessDate(params.getProcessDate());
    history.setQuantity2(docLineData.getQuantity2());

    return history;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.warehouse.generic.AbstractStockBatchCreateStrategy#doFillStockBatch(com.mg.merp.warehouse.WarehouseProcessDocumentLineData, com.mg.merp.warehouse.model.StockCard, com.mg.merp.docflow.DocFlowPluginInvokeParams, com.mg.merp.warehouse.BatchPriceStrategy)
   */
  @Override
  protected StockBatch doFillStockBatch(WarehouseProcessDocumentLineData docLineData, StockCard stockCard, DocFlowPluginInvokeParams params, BatchPriceStrategy strategy) {
    DocSpec docSpec = docLineData.getDocumentSpec();
    BigDecimal priceCur = strategy.doCalculate(docSpec);
    if (priceCur == null)
      priceCur = BigDecimal.ZERO;
    BigDecimal curCource = docSpec.getDocHead().getCurCource();
    if (curCource == null)
      curCource = BigDecimal.ONE;
    BigDecimal priceNat = curCource.multiply(priceCur);
    return modifyOrCreateStockBatch(docLineData, stockCard, findStockBatch(docSpec, stockCard, priceNat, priceCur), priceNat, priceCur, params);
  }

}
