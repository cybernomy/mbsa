/*
 * DiscountBAiResult.java
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
 * Результат расчета скидок/наценок по рекламному мероприятию
 *
 * @author Artem V. Sharapov
 * @version $Id: PromotionDiscountResult.java,v 1.1 2007/10/30 14:13:48 sharapov Exp $
 */
public class PromotionDiscountResult {

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
   * Признак "действует ли рекламное мероприятие в контексте бизнес-логики"
   */
  private boolean isApplied;

  /**
   * Признак "расчет скидок/наценок осуществляется в рамках одного рекламного мероприятия"
   */
  private boolean isAlonePromotion;


  // Default constructor
  public PromotionDiscountResult() {
  }

  // Constructor with params

  /**
   * @param priceWithDiscount
   * @param discount
   * @param isApplied
   * @param isAlonePromotion
   */
  public PromotionDiscountResult(BigDecimal priceWithDiscount, BigDecimal discount, boolean isApplied, boolean isAlonePromotion) {
    this.priceWithDiscount = priceWithDiscount;
    this.discount = discount;
    this.isApplied = isApplied;
    this.isAlonePromotion = isAlonePromotion;
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
   * @return the isAlonePromotion
   */
  public boolean isAlonePromotion() {
    return this.isAlonePromotion;
  }

  /**
   * @param isAlonePromotion the isAlonePromotion to set
   */
  public void setAlonePromotion(boolean isAlonePromotion) {
    this.isAlonePromotion = isAlonePromotion;
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

}
