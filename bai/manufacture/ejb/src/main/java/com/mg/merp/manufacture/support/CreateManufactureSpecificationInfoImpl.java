/*
 * CreateManufactureSpecificationInfoImpl.java
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
package com.mg.merp.manufacture.support;

import com.mg.merp.document.support.CreateSpecificationInfoImpl;
import com.mg.merp.manufacture.model.JobRouteResource;
import com.mg.merp.mfreference.model.CostCategories;
import com.mg.merp.reference.model.Measure;

import java.math.BigDecimal;

/**
 * @author Oleg V. Safonov
 * @version $Id: CreateManufactureSpecificationInfoImpl.java,v 1.1 2007/08/06 12:44:54 safonov Exp
 *          $
 */
public class CreateManufactureSpecificationInfoImpl extends
    CreateSpecificationInfoImpl {
  private JobRouteResource jobRouteResource;
  private CostCategories costCategory;
  private Measure measure1;
  private Measure measure2;

  public CreateManufactureSpecificationInfoImpl(Integer catalogId, Integer pricelistId, BigDecimal price, BigDecimal quantity1, BigDecimal quantity2, JobRouteResource jobRouteResource, CostCategories costCategory, Measure measure1, Measure measure2) {
    super(catalogId, pricelistId, price, quantity1, quantity2);
    this.jobRouteResource = jobRouteResource;
    this.costCategory = costCategory;
    this.measure1 = measure1;
    this.measure2 = measure2;
  }

  /**
   * @return the costCategory
   */
  public CostCategories getCostCategory() {
    return costCategory;
  }

  /**
   * @return the jobRouteResource
   */
  public JobRouteResource getJobRouteResource() {
    return jobRouteResource;
  }

  /**
   * @return the measure1
   */
  public Measure getMeasure1() {
    return measure1;
  }

  /**
   * @return the measure2
   */
  public Measure getMeasure2() {
    return measure2;
  }

}
