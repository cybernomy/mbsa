/*
 * TaxResult.java
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
package com.mg.merp.salary.support;

import java.math.BigDecimal;

/**
 * Класс-результат: информация о налоге
 *
 * @author Oleg V. Safonov
 * @author Artem V. Sharapov
 * @version $Id: TaxResult.java,v 1.1 2007/08/21 05:33:09 sharapov Exp $
 */
public class TaxResult {

  private Integer rateNumber;
  private BigDecimal minIncome;
  private BigDecimal maxIncome;
  private BigDecimal percent;
  private BigDecimal constValue;
  private BigDecimal privilegeRatio;


  public TaxResult() {
  }

  /**
   * Создать информацию о налоге
   *
   * @param rateNumber     - номер ставки
   * @param minIncome      - минимальный доход, начиная с которого действует данная ставка
   * @param maxIncome      - максимальный доход, до которого действует данная ставка
   * @param percent        - процент ставки
   * @param constValue     - фиксированная сумма
   * @param privilegeRatio - коэффициент для льгот
   */
  public TaxResult(Integer rateNumber, BigDecimal minIncome, BigDecimal maxIncome, BigDecimal percent, BigDecimal constValue, BigDecimal privilegeRatio) {
    this.rateNumber = rateNumber;
    this.minIncome = minIncome;
    this.maxIncome = maxIncome;
    this.percent = percent;
    this.constValue = constValue;
    this.privilegeRatio = privilegeRatio;
  }

  /**
   * @return фиксированная сумма
   */
  public BigDecimal getConstValue() {
    return constValue;
  }

  /**
   * @return максимальный доход, до которого действует данная ставка
   */
  public BigDecimal getMaxIncome() {
    return maxIncome;
  }

  /**
   * @return минимальный доход, начиная с которого действует данная ставка
   */
  public BigDecimal getMinIncome() {
    return minIncome;
  }

  /**
   * @return процент ставки
   */
  public BigDecimal getPercent() {
    return percent;
  }

  /**
   * @return коэффициент для льгот
   */
  public BigDecimal getPrivilegeRatio() {
    return privilegeRatio;
  }

  /**
   * @return номер ставки
   */
  public Integer getRateNumber() {
    return rateNumber;
  }

}
