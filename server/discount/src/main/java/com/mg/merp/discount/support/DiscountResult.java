/*
 * DiscountResult.java
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
package com.mg.merp.discount.support;

import java.math.BigDecimal;

/**
 * Результат расчета группы скидок/наценок
 *
 * @author Artem V. Sharapov
 * @version $Id: DiscountResult.java,v 1.1 2007/10/30 14:13:48 sharapov Exp $
 */
public class DiscountResult {

  // Fields

  /**
   * Цена позиции с учетом скидки/наценки
   */
  private BigDecimal priceWithDiscount;

  /**
   * Скидка/наценка позиции
   */
  private BigDecimal discount;

  /**
   * Признак "действует ли скидка в данном контексте"
   */
  private boolean isApplied;

  private boolean isApplyDiscountOnDoc;

  // Default constructor
  public DiscountResult() {
  }


  // Property accessors

  /**
   * @return the priceWithDiscount
   */
  public BigDecimal getPriceWithDiscount() {
    return this.priceWithDiscount;
  }

  /**
   * @param priceWithDiscount the priceWithDiscount to set
   */
  public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
    this.priceWithDiscount = priceWithDiscount;
  }

  /**
   * @return the discount
   */
  public BigDecimal getDiscount() {
    return this.discount;
  }

  /**
   * @param discount the discount to set
   */
  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  /**
   * @return the isApplied
   */
  public boolean getIsApplied() {
    return this.isApplied;
  }

  /**
   * @param isApplied the isApplied to set
   */
  public void setIsApplied(boolean isApplied) {
    this.isApplied = isApplied;
  }

  /**
   * @return the isApplyDiscountOnDoc
   */
  public boolean getIsApplyDiscountOnDoc() {
    return this.isApplyDiscountOnDoc;
  }

  /**
   * @param isApplyDiscountOnDoc the isApplyDiscountOnDoc to set
   */
  public void setIsApplyDiscountOnDoc(boolean isApplyDiscountOnDoc) {
    this.isApplyDiscountOnDoc = isApplyDiscountOnDoc;
  }

}
