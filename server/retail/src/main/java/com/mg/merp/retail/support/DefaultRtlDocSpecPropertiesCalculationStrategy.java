/*
 * DefaultDocSpecPropertiesCalculationStrategy.java
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
package com.mg.merp.retail.support;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.utils.MathUtils;
import com.mg.merp.document.DocumentTaxProcessor;
import com.mg.merp.document.generic.AbstractDocSpecPropertiesCalculationStrategy;
import com.mg.merp.retail.model.RtlInvoiceSpec;

import java.math.BigDecimal;

/**
 * Стандартная реализация стратегии расчета свойств спецификации розничного документа
 *
 * @author Artem V. Sharapov
 * @version $Id: DefaultRtlDocSpecPropertiesCalculationStrategy.java,v 1.2 2008/02/05 10:05:51
 *          safonov Exp $
 */
public class DefaultRtlDocSpecPropertiesCalculationStrategy extends AbstractDocSpecPropertiesCalculationStrategy {

  private RtlInvoiceSpec docSpec;
  private boolean isWithTaxes;
  private int currencyScale;
  private RoundContext roundContext;


  public DefaultRtlDocSpecPropertiesCalculationStrategy(RtlInvoiceSpec rtlInvoiceSpec, boolean isWithTaxes, int currencyScale, RoundContext roundContext) {
    this.docSpec = rtlInvoiceSpec;
    this.isWithTaxes = isWithTaxes;
    this.currencyScale = currencyScale;
    this.roundContext = roundContext;
  }

  /* (non-Javadoc)
   * @see com.mg.merp.retail.support.AbstractDocSpecPropertiesCalculationStrategy#doAdjust()
   */
  @Override
  protected void doAdjust() {
    BigDecimal quantity = docSpec.getQuantity();
    //скидка/наценка в % из документа
    BigDecimal docDiscount = docSpec.getDocDiscount();
    if (docDiscount == null)
      docDiscount = BigDecimal.ZERO;
    //сумма внешней скидки/наценки расчитанной для данной спецификации
    BigDecimal calculatedDiscount = docSpec.getExternalDiscountValue();
    if (calculatedDiscount == null) {
      calculatedDiscount = BigDecimal.ZERO;
      //+ скидка/наценка в % из спецификации если нет внешней скидки/наценки
      if (docSpec.getDiscount() != null)
        docDiscount = docDiscount.add(docSpec.getDiscount());
    }

    if (docSpec.getPrice1() == null || MathUtils.compareToZero(docSpec.getPrice1()) == 0) {
      //если не заданы цены и суммы, то выходим, вероятно здесь еще надо проверять
      //цены и суммы со скидками и от них уже вычислять
      if (docSpec.getPriceWithDiscount() != null && MathUtils.compareToZero(docSpec.getPriceWithDiscount()) != 0) {
        docSpec.setPrice1(MathUtils.round(MathUtils.divide(docSpec.getPriceWithDiscount(), MathUtils.divide(MathUtils.HUNDRED.add(docDiscount), MathUtils.HUNDRED, roundContext), roundContext), new RoundContext(currencyScale)));
        calculateSumWithIncldedTaxesAndSumWithDiscount(quantity);
      } else if (docSpec.getSumma1() == null || MathUtils.compareToZero(docSpec.getSumma1()) == 0) {
        //http://issues.m-g.ru/bugzilla/show_bug.cgi?id=4585
        docSpec.setSummaWithDiscount(docSpec.getSumma1());
        return;
      } else {
        boolean isZeroQuan = quantity == null || MathUtils.compareToZero(quantity) == 0;
        docSpec.setPrice1(isZeroQuan ?
            docSpec.getSumma1() : MathUtils.divide(docSpec.getSumma1(), quantity, roundContext));
        BigDecimal discountValue = docSpec.getPrice1().multiply(docDiscount).divide(MathUtils.HUNDRED);
        docSpec.setPriceWithDiscount(MathUtils.round(docSpec.getPrice1().add(calculatedDiscount).add(discountValue), roundContext));
        docSpec.setSummaWithDiscount(isZeroQuan ?
            docSpec.getPriceWithDiscount() : MathUtils.multiply(docSpec.getPriceWithDiscount(), quantity, roundContext));
      }
    } else {
      BigDecimal discountValue = docSpec.getPrice1().multiply(docDiscount).divide(MathUtils.HUNDRED);
      docSpec.setPriceWithDiscount(MathUtils.round(docSpec.getPrice1().add(calculatedDiscount).add(discountValue), roundContext));
      if (quantity != null || MathUtils.compareToZero(quantity) != 0) {
        docSpec.setSummaWithDiscount(MathUtils.multiply(docSpec.getPriceWithDiscount(), quantity, roundContext));
        docSpec.setSumma1(MathUtils.multiply(docSpec.getPrice1(), quantity, roundContext));
      } else {
        docSpec.setSummaWithDiscount(docSpec.getPriceWithDiscount());
        docSpec.setSumma1(docSpec.getPrice1());
      }
    }
    //если есть внешняя скидка/наценка то расчитаем в % для данной спецификации
    if (docSpec.getExternalDiscountValue() != null)
      docSpec.setDiscount(MathUtils.divide(calculatedDiscount.multiply(MathUtils.HUNDRED), docSpec.getPrice1(), new RoundContext(6)));
  }

  /* (non-Javadoc)
   * @see com.mg.merp.retail.support.AbstractDocSpecPropertiesCalculationStrategy#doCalculateTaxes()
   */
  @Override
  protected void doCalculateTaxes() {
    if (isWithTaxes) {
      DocumentTaxProcessor taxProcessor = (DocumentTaxProcessor) ApplicationDictionaryLocator.locate().getBusinessService(DocumentTaxProcessor.SERVICE_NAME);
      taxProcessor.calculateDocumentSpecTaxes(docSpec, docSpec.getPriceWithDiscount(), docSpec.getSummaWithDiscount(), false, roundContext);
    } else {
      docSpec.setSumma(docSpec.getSummaWithDiscount());
      docSpec.setPrice(docSpec.getPriceWithDiscount());
    }
  }

  /**
   * Рассчитать сумму(с включенными налогами) и сумму со с/н
   *
   * @param quantity - кол-во
   */
  private void calculateSumWithIncldedTaxesAndSumWithDiscount(BigDecimal quantity) {
    if (quantity != null || MathUtils.compareToZero(quantity) != 0) {
      docSpec.setSummaWithDiscount(MathUtils.multiply(docSpec.getPriceWithDiscount(), quantity, roundContext));
      docSpec.setSumma1(MathUtils.multiply(docSpec.getPrice1(), quantity, roundContext));
    } else {
      docSpec.setSummaWithDiscount(docSpec.getPriceWithDiscount());
      docSpec.setSumma1(docSpec.getPrice1());
    }
  }

  /**
   * @return the docSpec
   */
  protected RtlInvoiceSpec getDocSpec() {
    return this.docSpec;
  }

  /**
   * @return the isWithTaxes
   */
  protected boolean isWithTaxes() {
    return this.isWithTaxes;
  }

  /**
   * @return the currencyScale
   */
  protected int getCurrencyScale() {
    return this.currencyScale;
  }

  /**
   * @return the roundContext
   */
  protected RoundContext getRoundContext() {
    return this.roundContext;
  }

}
