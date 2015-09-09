/*
 * ManualDistributionData.java
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
package com.mg.merp.contract.model;

import java.math.BigDecimal;

/**
 * Структура данных для распределения фактичекой суммы
 *
 * @author Artem V. Sharapov
 * @version $Id: ManualDistributionData.java,v 1.1 2007/03/07 12:31:28 sharapov Exp $
 */
public class ManualDistributionData {

  // Fields

  private Integer planItemId;
  private BigDecimal distSum;

  /* Default constructor*/
  public ManualDistributionData() {
  }

  public ManualDistributionData(Integer planItemId, BigDecimal distSum) {
    this.planItemId = planItemId;
    this.distSum = distSum;
  }

  // Property accessors

  /**
   * @return the distSum
   */
  public BigDecimal getDistSum() {
    return distSum;
  }

  /**
   * @param distSum the distSum to set
   */
  public void setDistSum(BigDecimal distSum) {
    this.distSum = distSum;
  }

  /**
   * @return the planItemId
   */
  public Integer getPlanItemId() {
    return planItemId;
  }

  /**
   * @param planItemId the planItemId to set
   */
  public void setPlanItemId(Integer planItemId) {
    this.planItemId = planItemId;
  }

}
