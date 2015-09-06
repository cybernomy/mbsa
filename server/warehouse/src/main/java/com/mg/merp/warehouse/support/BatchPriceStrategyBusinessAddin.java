/*
 * BatchPriceStrategyBusinessAddin.java
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

import com.mg.merp.baiengine.generic.AbstractBusinessAddin;
import com.mg.merp.warehouse.BatchPriceStrategy;
import com.mg.merp.warehouse.WarehouseProcessDocumentLineData;
import com.mg.merp.warehouse.model.StockCard;

import java.util.Map;

/**
 * Базовый класс бизнес-расширения рассчета цены прихода на склад.<br> <p> Класс наследник должен
 * реализовывать следующий метод:<br> <code> protected BatchPriceStrategy
 * createBatchPriceStrategy()</code><br> Метод возвращает стратегию рассчёта цены прихода товарной
 * позиции на склад. <p> Пример данного метода:
 * <pre>
 * protected BatchPriceStrategy createBatchPriceStrategy() {
 *     return new TestBatchPriceStrategy();
 * }
 *
 * // Cтратегия рассчёта цены складской партии
 * class TestBatchPriceStrategy implements BatchPriceStrategy {
 *
 *  // рассчёт цены складской партии: полная цена позиции спецификации * 10
 *  public BigDecimal doCalculate(DocSpec docSpec) {
 *      return docSpec.getPrice1().multiply(BigDecimal.TEN);
 *  }
 * }
 * </pre>
 *
 * @author Valentin A. Poroxnenko
 * @author Artem V. Sharapov
 * @version $Id: BatchPriceStrategyBusinessAddin.java,v 1.2 2008/06/05 12:48:05 sharapov Exp $
 * @see com.mg.merp.warehouse.BatchPriceStrategy
 * @see com.mg.merp.warehouse.support.DefaultBatchPriceStrategy
 */
public abstract class BatchPriceStrategyBusinessAddin extends AbstractBusinessAddin<BatchPriceStrategy> {

  public final static String DOC_LINE_DATA_PARAM = "DOC_LINE_DATA_PARAM"; //$NON-NLS-1$
  public final static String STOCK_CARD_PARAM = "STOCK_CARD_PARAM"; //$NON-NLS-1$

  private WarehouseProcessDocumentLineData docLineData = null;
  private StockCard stockCard = null;


  /* (non-Javadoc)
   * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#extractParams(java.util.Map)
   */
  @Override
  protected void extractParams(Map<String, ? extends Object> params) {
    this.docLineData = (WarehouseProcessDocumentLineData) params.get(DOC_LINE_DATA_PARAM);
    this.stockCard = (StockCard) params.get(STOCK_CARD_PARAM);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.baiengine.generic.AbstractBusinessAddin#doPerform()
   */
  @Override
  protected final void doPerform() throws Exception {
    complete(createBatchPriceStrategy());
  }

  /**
   * Создать стратегию рассчёта цены партии
   *
   * @return стратегия рассчёта цены партии
   */
  protected abstract BatchPriceStrategy createBatchPriceStrategy();

  /**
   * Получить данные по спецификации для отработки по складам
   *
   * @return данные по спецификации для отработки по складам
   */
  public WarehouseProcessDocumentLineData getDocLineData() {
    return this.docLineData;
  }

  /**
   * Получить КСУ
   *
   * @return КСУ
   */
  public StockCard getStockCard() {
    return this.stockCard;
  }

}
