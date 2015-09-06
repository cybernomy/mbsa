/*
 * DocumentSpecItem.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium ERP system.
 *
 */
package com.mg.merp.docflow;

import com.mg.merp.document.model.DocSpec;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Элемент спецификации документа при отработке (откате) документооборота
 *
 * @author Oleg V. Safonov
 * @version $Id: DocumentSpecItem.java,v 1.3 2007/12/14 08:46:31 safonov Exp $
 */
public class DocumentSpecItem {
  private DocSpec docSpec;
  //private BigDecimal initialFreeQuantity1;
  //private BigDecimal initialFreeQuantity2;
  //private BigDecimal initialFreeSum;
  private BigDecimal freeQuantity1;
  private BigDecimal freeQuantity2;
  private BigDecimal freeSum;
  private BigDecimal readyQuantity1;
  private BigDecimal readyQuantity2;
  private BigDecimal readySum;
  private BigDecimal performedQuantity1;
  private BigDecimal performedQuantity2;
  private BigDecimal performedSum;
  private Integer data1;
  private Integer data2;
  private Serializable stateValue;

  public DocumentSpecItem(DocSpec docSpec, BigDecimal freeQuantity1, BigDecimal freeQuantity2, BigDecimal freeSum,
                          BigDecimal readyQuantity1, BigDecimal readyQuantity2, BigDecimal readySum) {
    this.docSpec = docSpec;
    this.freeQuantity1 = freeQuantity1;
    this.freeQuantity2 = freeQuantity2;
    this.freeSum = freeSum;
    this.readyQuantity1 = readyQuantity1;
    this.readyQuantity2 = readyQuantity2;
    this.readySum = readySum;
    //this.initialFreeQuantity1 = freeQuantity1.add(BigDecimal.ZERO);//create copy
    //this.initialFreeQuantity2 = freeQuantity2.add(BigDecimal.ZERO);//create copy
    //this.performedQuantity1 = freeQuantity1;
    //this.performedQuantity2 = freeQuantity2;
    //this.performedSum = freeSum;
    //this.performedQuantity1 = BigDecimal.ZERO;
    //this.performedQuantity2 = BigDecimal.ZERO;
    //this.performedSum = BigDecimal.ZERO;
  }

  /**
   * Создание элемента спецификации для отката ДО
   *
   * @param docSpec        спецификация документа
   * @param readyQuantity1 отработанное количество в первой ЕИ
   * @param readyQuantity2 отработанное количество во второй ЕИ
   * @param readySum       отработанная сумма
   * @param data1          данные 1
   * @param data2          данные 2
   * @param stateValue     универсальное состояние отработанной спецификации
   */
  public DocumentSpecItem(DocSpec docSpec, BigDecimal readyQuantity1, BigDecimal readyQuantity2, BigDecimal readySum,
                          Integer data1, Integer data2, Serializable stateValue) {
    this.docSpec = docSpec;
    this.readyQuantity1 = readyQuantity1;
    this.readyQuantity2 = readyQuantity2;
    this.readySum = readySum;
    this.data1 = data1;
    this.data2 = data2;
    this.stateValue = stateValue;
  }

  /**
   * признак частичной отработки
   *
   * @return <code>true</code> если отработка частичная
   */
  public boolean isPartition() {
    //#2384 fixed, проверим на равенство, полученные значения могут превышать количества
    return freeQuantity1.compareTo(performedQuantity1) != 0 || freeQuantity2.compareTo(performedQuantity2) != 0;
  }

  /**
   * получить состояние выполнения документооборота 1, используется для совместимости с предыдущими
   * версиями, при разработке новых этапов ДО рекомендуется использовать {@link #getStateValue()}
   *
   * @return состояние 1
   */
  public Integer getData1() {
    return data1;
  }

  /**
   * установить состояние выполнения документооборота 1, используется для совместимости с
   * предыдущими версиями, при разработке новых этапов ДО рекомендуется использовать {@link
   * #setStateValue()}
   *
   * @param data1 состояние 1
   */
  public void setData1(Integer data1) {
    this.data1 = data1;
  }

  /**
   * получить состояние выполнения документооборота 2, используется для совместимости с предыдущими
   * версиями, при разработке новых этапов ДО рекомендуется использовать {@link #getStateValue()}
   *
   * @return состояние 2
   */
  public Integer getData2() {
    return data2;
  }

  /**
   * установить состояние выполнения документооборота 2, используется для совместимости с
   * предыдущими версиями, при разработке новых этапов ДО рекомендуется использовать {@link
   * #setStateValue()}
   *
   * @param data1 состояние 2
   */
  public void setData2(Integer data2) {
    this.data2 = data2;
  }

  /**
   * получить количество к отработке (откату) в первой ЕИ
   *
   * @return количество к отработке (откату)
   */
  public BigDecimal getPerformedQuantity1() {
    return performedQuantity1;
  }

  /**
   * установить количество к отработке (откату) в первой ЕИ
   *
   * @param performedQuantity1 количество к отработке (откату)
   */
  public void setPerformedQuantity1(BigDecimal performedQuantity1) {
    this.performedQuantity1 = performedQuantity1;
  }

  /**
   * получить количество к отработке (откату) во второй ЕИ
   *
   * @return количество к отработке (откату)
   */
  public BigDecimal getPerformedQuantity2() {
    return performedQuantity2;
  }

  /**
   * установить количество к отработке (откату) во второй ЕИ
   *
   * @param performedQuantity2 количество к отработке (откату)
   */
  public void setPerformedQuantity2(BigDecimal performedQuantity2) {
    this.performedQuantity2 = performedQuantity2;
  }

  /**
   * получить сумму к отработке (откату)
   *
   * @return сумма к отработке (откату)
   */
  public BigDecimal getPerformedSum() {
    return performedSum;
  }

  /**
   * установить сумму к отработке (откату)
   *
   * @param performedSum сумма к отработке (откату)
   */
  public void setPerformedSum(BigDecimal performedSum) {
    this.performedSum = performedSum;
  }

  /**
   * получить состояние выполнения документооборота, как правило используется в механизмах отката
   * для восстановления состояния системы
   *
   * @return состояние выполнения
   */
  public Serializable getStateValue() {
    return stateValue;
  }

  /**
   * установить состояние выполнения документооборота, используется для сохранения любой информации
   * полученной при отработке спецификации, данная информация будет доступна при выполнении отката
   *
   * @param stateValue состояние выполнения
   */
  public void setStateValue(Serializable specStateValue) {
    this.stateValue = specStateValue;
  }

  /**
   * получить спецификацию документа
   *
   * @return спецификация документа
   */
  public DocSpec getDocSpec() {
    return docSpec;
  }

  /**
   * получить количество в первой ЕИ не обработанное в текущем этапе документооборота, данное
   * значение может не совпадать с количеством для отработки по текущему этапу (см. {@link
   * #getPerformedQuantity1()})
   *
   * @return не обработанное количество
   */
  public BigDecimal getFreeQuantity1() {
    return freeQuantity1;
  }

  /**
   * получить количество во второй ЕИ не обработанное в текущем этапе документооборота, данное
   * значение может не совпадать с количеством для отработки по текущему этапу (см. {@link
   * #getPerformedQuantity2()})
   *
   * @return не обработанное количество
   */
  public BigDecimal getFreeQuantity2() {
    return freeQuantity2;
  }

  /**
   * получить сумму спецификации не обработанную в текущем этапе документооборота, данное значение
   * может не совпадать с суммой для отработки по текущему этапу (см. {@link #getPerformedSum()})
   *
   * @return не обработанная сумма спецификации
   */
  public BigDecimal getFreeSum() {
    return freeSum;
  }

  /**
   * получить количество в первой ЕИ обработанное в текущем этапе документооборота
   *
   * @return обработанное количество
   */
  public BigDecimal getReadyQuantity1() {
    return readyQuantity1;
  }

  /**
   * получить количество во второй ЕИ обработанное в текущем этапе документооборота
   *
   * @return обработанное количество
   */
  public BigDecimal getReadyQuantity2() {
    return readyQuantity2;
  }

  /**
   * получить сумму спецификации обработанную в текущем этапе документооборота
   *
   * @return обработанная сумма спецификации
   */
  public BigDecimal getReadySum() {
    return readySum;
  }

}
