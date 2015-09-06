/*
 * DocumentTaxProcessorBean.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
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
package com.mg.merp.document.support;

import com.mg.framework.api.math.RoundContext;
import com.mg.framework.generic.AbstractPOJOBusinessObjectStatelessServiceBean;
import com.mg.framework.utils.MathUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.document.DocumentTaxProcessor;
import com.mg.merp.document.model.DocHead;
import com.mg.merp.document.model.DocSpec;
import com.mg.merp.document.model.DocumentSpecTax;
import com.mg.merp.reference.model.CalcTaxesLink;
import com.mg.merp.reference.model.CalcTaxesSubject;
import com.mg.merp.reference.model.Tax;
import com.mg.merp.reference.model.TaxGroup;
import com.mg.merp.reference.model.TaxLink;
import com.mg.merp.reference.model.TaxType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

/**
 * Реализация процессора обработки налогов на документах
 *
 * @author Oleg V. Safonov
 * @version $Id: DocumentTaxProcessorBean.java,v 1.11 2009/02/25 16:55:19 safonov Exp $
 */
@Stateless(name = "merp/document/DocumentTaxProcessor")
public class DocumentTaxProcessorBean extends
    AbstractPOJOBusinessObjectStatelessServiceBean implements DocumentTaxProcessor {
  private final static BigDecimal HUNDRED = new BigDecimal(BigInteger.valueOf(100), 0);

  /**
   * объединить список налогов в спецификации с новым списком налогов
   */
  protected void mergeDocumentSpecTaxes(List<CalcTaxItem> calcTaxItems, Set<DocumentSpecTax> specTaxes) {
    //удаляем налоги которые не попали в список налогов спецификации
    Set<DocumentSpecTax> deleteTaxes = new HashSet<DocumentSpecTax>();
    for (DocumentSpecTax specTax : specTaxes) {
      boolean find = false;
      for (CalcTaxItem taxItem : calcTaxItems)
        if (taxItem.tax.getTax().getId().equals(specTax.getTax().getId())) {
          find = true;
          break;
        }

      if (!find)
        deleteTaxes.add(specTax);
    }
    specTaxes.removeAll(deleteTaxes);
    //добавим новые налоги
    Set<DocumentSpecTax> newTaxes = new HashSet<DocumentSpecTax>();
    for (CalcTaxItem taxItem : calcTaxItems) {
      boolean find = false;
      for (DocumentSpecTax specTax : specTaxes)
        if (taxItem.tax.getTax().getId().equals(specTax.getTax().getId())) {
          find = true;
          //подменим налог, возмем из спецификации
          specTax.setSumElement(taxItem.tax.getSumElement());
          taxItem.tax = specTax;
          break;
        }

      if (!find)
        newTaxes.add(taxItem.tax);
    }
    specTaxes.addAll(newTaxes);
  }

  private List<CalcTaxItem> createDocumentSpecTaxes(DocSpec spec, DocHead docHead) {
    List<CalcTaxItem> result = new ArrayList<CalcTaxItem>();

    //для расчета налогов должны быть установлены группа налогов и вид начисления налогов
    if (docHead.getCalcTaxesKind() != null && spec.getTaxGroup() != null) {
      TaxGroup taxGroup = ServerUtils.getPersistentManager().find(TaxGroup.class, spec.getTaxGroup().getId());
      for (CalcTaxesLink calcTaxesLink : docHead.getCalcTaxesKind().getTaxLinks()) {
        for (TaxLink taxLink : taxGroup.getTaxLinks()) {
          Date activeDate = taxLink.getTax().getActiveDate();
          Date deactivateDate = taxLink.getTax().getDeactivateDate();
          Date docDate = docHead.getDocDate();
          //отбор по налогу и датам действия налога
          if (taxLink.getTax().getId() == calcTaxesLink.getTax().getId() &&
              (docDate.equals(activeDate) || docDate.after(activeDate)) && (docDate.equals(deactivateDate) || docDate.before(deactivateDate))) {
            DocumentSpecTax specTax = new DocumentSpecTax();
            specTax.setDocSpec(spec);
            specTax.setTax(taxLink.getTax());
            specTax.setSumElement(specTax.getTax().getSumm());

            result.add(new CalcTaxItem(specTax, calcTaxesLink));
          }
        }
      }
    }

    Set<DocumentSpecTax> specTaxes = spec.getTaxes();
    if (specTaxes != null) {
      //список налогов был, редактирование спецификации
      mergeDocumentSpecTaxes(result, specTaxes);
    } else {
      //спискок налогов пустой, создание спецификации
      Set<DocumentSpecTax> newTaxes = new HashSet<DocumentSpecTax>();
      for (CalcTaxItem taxItem : result)
        newTaxes.add(taxItem.tax);
      spec.setTaxes(newTaxes);
    }
    return result;
  }

  /**
   * отобрать и отсортировать налоги в соответствии с условиями
   *
   * @param allTaxes все налоги
   * @param total    признак сортировки
   * @param included включены/не включены
   * @return список налогов
   */
  protected List<CalcTaxItem> selectDocumentSpecTaxes(List<CalcTaxItem> allTaxes, final boolean total, boolean included) {
    List<CalcTaxItem> result = new LinkedList<CalcTaxItem>();
    for (CalcTaxItem item : allTaxes)
      if (item.calcLink.getIncluded() == included)
        result.add(item);
    //сортировка по feeOrder, если total == true, то обратная сортировка
    Collections.sort(result, new Comparator<CalcTaxItem>() {

      public int compare(CalcTaxItem o1, CalcTaxItem o2) {
        short order1 = o1.calcLink.getFeeOrder(), order2 = o2.calcLink.getFeeOrder();
        if (order1 < order2)
          return total ? 1 : -1;
        else if (order1 > order2)
          return total ? -1 : 1;
        else
          return 0;
      }

    });
    return result;
  }

  /**
   * получить сумму налога для уровня расчета
   *
   * @param allTaxes все налоги
   * @param feeOrder уоровень расчета
   * @param included включены/не включены
   * @return список налогов
   */
  protected BigDecimal loadLayerRate(List<CalcTaxItem> allTaxes, short feeOrder, boolean included) {
    BigDecimal result = BigDecimal.ZERO;
    for (CalcTaxItem item : allTaxes)
      if (item.calcLink.getIncluded() == included && item.calcLink.getFeeOrder() == feeOrder)
        result = result.add(item.tax.getTax().getDirectRate());
    return result;
  }

  private BigDecimal calculateTaxElement(BigDecimal elementValue, BigDecimal totalLayerRate, BigDecimal directRate) {
    MathContext mathContext = new MathContext(10);
    return elementValue.subtract(elementValue.divide(HUNDRED.divide(totalLayerRate, mathContext).add(BigDecimal.ONE), mathContext)).multiply(directRate).divide(HUNDRED, mathContext);
  }

  /**
   * расчет налогов
   *
   * @param total если true то расчет от summa, price (от общих), иначе расчет от summa1, price1 (от
   *              сум/цен с включенными налогами)
   */
  protected void modifyDocumentSpecTaxes(DocSpec spec, List<CalcTaxItem> allTaxes, BigDecimal price, BigDecimal sum, boolean total, RoundContext roundContext) {
    List<CalcTaxItem> docSpecTaxesAndLinks = selectDocumentSpecTaxes(allTaxes, total, false);
    BigDecimal quantity = spec.getQuantity() == null || MathUtils.compareToZero(spec.getQuantity()) == 0 ? BigDecimal.ONE : spec.getQuantity();
    if (total) {
      short oldLayer = -1;
      BigDecimal layerSum = BigDecimal.ZERO;
      BigDecimal layerPrice = BigDecimal.ZERO;
      BigDecimal tmpSum = sum;
      BigDecimal tmpPrice = price;
      BigDecimal totalLayerRate, taxPrice, taxSum, totalTaxPrice, totalTaxSum;

      for (CalcTaxItem specTaxAndLink : docSpecTaxesAndLinks) {
        DocumentSpecTax specTax = specTaxAndLink.tax;
        CalcTaxesLink calcLink = specTaxAndLink.calcLink;
        Tax tax = specTax.getTax();
        taxSum = tax.getSumm();

        if (oldLayer == -1)
          oldLayer = calcLink.getFeeOrder(); //first enter


        totalLayerRate = loadLayerRate(allTaxes, calcLink.getFeeOrder(), false);

        //if goto on next layer then change sum
        if (calcLink.getFeeOrder() < oldLayer) {
          tmpPrice = tmpPrice.subtract(layerPrice);
          tmpSum = tmpSum.subtract(layerSum);
        }

        //percent
        if (TaxType.PERCENT.equals(tax.getTaxType())) {
          taxPrice = calculateTaxElement(tmpPrice, totalLayerRate, tax.getDirectRate());
          taxSum = calculateTaxElement(tmpSum, totalLayerRate, tax.getDirectRate());

          if (CalcTaxesSubject.PRICE.equals(calcLink.getSubject())) {
            totalTaxSum = MathUtils.round(taxPrice.multiply(quantity), roundContext);
            totalTaxPrice = MathUtils.round(taxPrice, roundContext);
          } else {
            totalTaxSum = MathUtils.round(taxSum, roundContext);
            totalTaxPrice = MathUtils.divide(taxSum, quantity, roundContext);
          }
        } else {
          taxPrice = taxSum.divide(quantity, new MathContext(10)); //большая точность

          totalTaxPrice = MathUtils.round(taxPrice, roundContext);
          totalTaxSum = MathUtils.round(taxSum, roundContext);
        }

        if (oldLayer == calcLink.getFeeOrder()) {
          layerPrice = layerPrice.add(taxPrice);
          layerSum = layerSum.add(taxSum);
        } else {
          layerPrice = taxPrice;
          layerSum = taxSum;
          oldLayer = calcLink.getFeeOrder();
        }

        specTax.setSumElement(totalTaxSum);
        specTax.setPriceElement(totalTaxPrice);
      }
    } else {
      short oldLayer = -1;
      BigDecimal layerSum = BigDecimal.ZERO;
      BigDecimal layerPrice = BigDecimal.ZERO;
      BigDecimal taxPrice, taxSum, totalTaxPrice, totalTaxSum;
      BigDecimal tmpSum = sum;
      BigDecimal tmpPrice = price;

      for (CalcTaxItem specTaxAndLink : docSpecTaxesAndLinks) {
        DocumentSpecTax specTax = specTaxAndLink.tax;
        CalcTaxesLink calcLink = specTaxAndLink.calcLink;
        Tax tax = specTax.getTax();
        taxSum = tax.getSumm();

        if (oldLayer == -1)
          oldLayer = calcLink.getFeeOrder(); //first enter

        //if goto on next layer then change sum
        if (calcLink.getFeeOrder() > oldLayer) {
          tmpSum = tmpSum.add(layerSum);
          tmpPrice = tmpPrice.add(layerPrice);
        }

        //percent
        if (TaxType.PERCENT.equals(tax.getTaxType())) {
          taxPrice = tmpPrice.multiply(tax.getDirectRate()).divide(HUNDRED);
          taxSum = tmpSum.multiply(tax.getDirectRate()).divide(HUNDRED);
          if (CalcTaxesSubject.PRICE.equals(calcLink.getSubject())) {
            totalTaxSum = MathUtils.round(taxPrice.multiply(quantity), roundContext);
            totalTaxPrice = MathUtils.round(taxPrice, roundContext);
          } else {
            totalTaxSum = MathUtils.round(taxSum, roundContext);
            totalTaxPrice = MathUtils.divide(taxSum, quantity, roundContext);
          }
        } else {
          taxPrice = taxSum.divide(quantity, new MathContext(10)); //большая точность

          totalTaxPrice = MathUtils.round(taxPrice, roundContext);
          totalTaxSum = MathUtils.round(taxSum, roundContext);
        }

        if (oldLayer == calcLink.getFeeOrder()) {
          layerPrice = layerPrice.add(taxPrice);
          layerSum = layerSum.add(taxSum);
        } else {
          layerPrice = taxPrice;
          layerSum = taxSum;
          oldLayer = calcLink.getFeeOrder();
        }

        specTax.setSumElement(totalTaxSum);
        specTax.setPriceElement(totalTaxPrice);
      }
    }

    //calculate included taxes
    short oldLayer = -1;
    BigDecimal layerSum = BigDecimal.ZERO;
    BigDecimal layerPrice = BigDecimal.ZERO;
    BigDecimal taxPrice, taxSum, totalTaxPrice, totalTaxSum;
    BigDecimal tmpSum = sum;
    BigDecimal tmpPrice = price;
    docSpecTaxesAndLinks = selectDocumentSpecTaxes(allTaxes, false, true);
    for (CalcTaxItem specTaxAndLink : docSpecTaxesAndLinks) {
      DocumentSpecTax specTax = specTaxAndLink.tax;
      CalcTaxesLink calcLink = specTaxAndLink.calcLink;
      Tax tax = specTax.getTax();
      taxSum = tax.getSumm();

      BigDecimal totalLayerRate = loadLayerRate(allTaxes, calcLink.getFeeOrder(), true);

      if (oldLayer == -1)
        oldLayer = calcLink.getFeeOrder(); //first enter

      //if goto on next layer then change sum
      if (calcLink.getFeeOrder() < oldLayer) {
        tmpPrice = tmpPrice.subtract(layerPrice);
        tmpSum = tmpSum.subtract(layerSum);
      }

      //percent
      if (TaxType.PERCENT.equals(tax.getTaxType())) {
        taxPrice = calculateTaxElement(tmpPrice, totalLayerRate, tax.getDirectRate());
        taxSum = calculateTaxElement(tmpSum, totalLayerRate, tax.getDirectRate());
      } else {
        taxPrice = taxSum.divide(quantity);
      }

      if (oldLayer == calcLink.getFeeOrder()) {
        layerPrice = layerPrice.add(taxPrice);
        layerSum = layerSum.add(taxSum);
      } else {
        layerPrice = taxPrice;
        layerSum = taxSum;
        oldLayer = calcLink.getFeeOrder();
      }

      if (CalcTaxesSubject.PRICE.equals(calcLink.getSubject())) {
        totalTaxSum = MathUtils.round(taxPrice.multiply(quantity), roundContext);
        totalTaxPrice = MathUtils.round(taxPrice, roundContext);
      } else {
        totalTaxSum = MathUtils.round(taxSum, roundContext);
        totalTaxPrice = MathUtils.divide(totalTaxSum, quantity, roundContext);
      }

      specTax.setSumElement(totalTaxSum);
      specTax.setPriceElement(totalTaxPrice);
    }
  }

  /**
   * приведение спецификации в соответствии с расчитанными налогами
   */
  private void adjustDocumentSpec(DocSpec spec, List<CalcTaxItem> allTaxes, BigDecimal price, BigDecimal sum, RoundContext roundContext) {
    CalcTaxesSubject subject = null;
    int linkId = 0;
    BigDecimal taxesOnPrice = BigDecimal.ZERO, taxesOnSum = BigDecimal.ZERO;
    for (CalcTaxItem taxItem : allTaxes) {
      //расчитываем сумму не включенных налогов для цены и суммы спецификации
      if (!taxItem.calcLink.getIncluded()) {
        taxesOnPrice = taxesOnPrice.add(taxItem.tax.getPriceElement());
        taxesOnSum = taxesOnSum.add(taxItem.tax.getSumElement());
      }
      //на что начисляются налоги возмем из последней связи, для совместимости с предыдущей версией
      //в данной реализации не поддерживается ситуация начисления налогов и на цену и на сумму в рамках
      //одного вида начисления налогов
      if (taxItem.calcLink.getId() > linkId)
        subject = taxItem.calcLink.getSubject();
    }
    //изменим спецификацию документа
    if (subject == null || CalcTaxesSubject.PRICE.equals(subject)) {
      //налог на цену или нет налогов, если нет налогов то taxesOnPrice = 0
      spec.setPrice(MathUtils.add(price, taxesOnPrice, roundContext));
      if (spec.getQuantity() == null || MathUtils.compareToZero(spec.getQuantity()) == 0)
        spec.setSumma(spec.getPrice());
      else
        spec.setSumma(MathUtils.multiply(spec.getPrice(), spec.getQuantity(), roundContext));
    } else if (CalcTaxesSubject.SUMMA.equals(subject)) {
      //налог на сумму
      spec.setSumma(MathUtils.add(sum, taxesOnSum, roundContext));
      if (spec.getQuantity() == null || MathUtils.compareToZero(spec.getQuantity()) == 0)
        spec.setPrice(spec.getSumma());
      else
        spec.setPrice(MathUtils.divide(spec.getSumma(), spec.getQuantity(), roundContext));
    } else {
      throw new IllegalStateException("Unknown subject of tax calculation");
    }
  }

  /**
   * реализация расчета налогов
   */
  protected void doCalculateDocumentSpecTaxes(DocSpec spec, List<CalcTaxItem> allTaxes, BigDecimal price, BigDecimal sum, boolean total, RoundContext roundContext) {
    modifyDocumentSpecTaxes(spec, allTaxes, price != null ? price : BigDecimal.ZERO, sum != null ? sum : BigDecimal.ZERO, total, roundContext);
    adjustDocumentSpec(spec, allTaxes, price, sum, roundContext);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocumentTaxProcessor#calculateDocumentSpecTaxes(com.mg.merp.document.model.DocSpec, boolean, java.math.MathContext)
   */
  @PermitAll
  public void calculateDocumentSpecTaxes(DocSpec spec, boolean total, RoundContext roundContext) {
    if (getLogger().isDebugEnabled())
      getLogger().debug("Calculate taxes for specification: " + spec.getId());

    //загрузим сущность если нет в сессии
    if (!ServerUtils.getPersistentManager().contains(spec.getDocHead()))
      spec.setDocHead(ServerUtils.getPersistentManager().find(DocHead.class, spec.getDocHead().getId()));

    doCalculateDocumentSpecTaxes(spec, createDocumentSpecTaxes(spec, spec.getDocHead()), spec.getPrice1(), spec.getSumma1(), total, roundContext);
  }

  /* (non-Javadoc)
   * @see com.mg.merp.document.DocumentTaxProcessor#calculateDocumentSpecTaxes(com.mg.merp.document.model.DocSpec, java.math.BigDecimal, java.math.BigDecimal, boolean, com.mg.framework.api.math.RoundContext)
   */
  @PermitAll
  public void calculateDocumentSpecTaxes(DocSpec spec, BigDecimal price,
                                         BigDecimal sum, boolean total, RoundContext roundContext) {
    if (getLogger().isDebugEnabled())
      getLogger().debug("Calculate taxes for specification: " + spec.getId());

    //загрузим сущность если нет в сессии
    if (!ServerUtils.getPersistentManager().contains(spec.getDocHead()))
      spec.setDocHead(ServerUtils.getPersistentManager().find(DocHead.class, spec.getDocHead().getId()));

    doCalculateDocumentSpecTaxes(spec, createDocumentSpecTaxes(spec, spec.getDocHead()), price, sum, total, roundContext);
  }

  protected class CalcTaxItem {
    DocumentSpecTax tax;
    CalcTaxesLink calcLink;

    public CalcTaxItem(DocumentSpecTax tax, CalcTaxesLink calcLink) {
      this.tax = tax;
      this.calcLink = calcLink;
    }

  }

}
