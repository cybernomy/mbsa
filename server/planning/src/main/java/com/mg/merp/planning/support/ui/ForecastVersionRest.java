/*
 * ForecastVersionRest.java
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
package com.mg.merp.planning.support.ui;

import com.mg.framework.api.annotations.DataItemName;
import com.mg.framework.generic.ui.DefaultRestrictionForm;
import com.mg.merp.mfreference.model.PlanningLevel;
import com.mg.merp.planning.model.ForecastMethod;
import com.mg.merp.planning.model.ForecastType;
import com.mg.merp.planning.model.GenericItem;
import com.mg.merp.reference.model.Catalog;
import com.mg.merp.reference.model.Contractor;

import java.util.Date;

/**
 * @author leonova
 * @version $Id: ForecastVersionRest.java,v 1.3 2007/07/30 10:36:31 safonov Exp $
 */
public class ForecastVersionRest extends DefaultRestrictionForm {

  @DataItemName("Planning.ForecastVersion.Code")
  private String code = "";
  @DataItemName("Planning.Description")
  private String description = "";
  private Catalog catalogCode = null;
  @DataItemName("Planning.ForecastLine.Contractor")
  private Contractor warehouseCode = null;
  @DataItemName("Planning.ForecastLine.GenericItem")
  private GenericItem planningItemCode = null;
  private ForecastType forecastType = null;
  private ForecastMethod forecastMethod = null;
  @DataItemName("Planning.Cond.ForecastVersion.RequiredDateFrom")
  private Date requiredDateFrom = null;
  @DataItemName("Planning.Cond.ForecastVersion.RequiredDateTill")
  private Date requiredDateTill = null;
  private PlanningLevel planningLevelCode = null;
  @DataItemName("Planning.ForLine.BucketStartDate")
  private Date bucketStartDate = null;
  @DataItemName("Planning.ForLine.BucketEndDate")
  private Date bucketEndDate = null;
  @DataItemName("Planning.ForecastLine.Bucket")
  private String bucketOffset = "";

  @Override
  protected void doClearRestrictionItem() {
    this.code = "";
    this.description = "";
    this.catalogCode = null;
    this.planningItemCode = null;
    this.warehouseCode = null;
    this.forecastMethod = null;
    this.forecastType = null;
    this.requiredDateFrom = null;
    this.requiredDateTill = null;
    this.planningLevelCode = null;
    this.bucketEndDate = null;
    this.bucketOffset = "";
    this.bucketStartDate = null;
  }

  /**
   * @return Returns the bucketEndDate.
   */
  protected Date getBucketEndDate() {
    return bucketEndDate;
  }

  /**
   * @return Returns the bucketOffset.
   */
  protected String getBucketOffset() {
    return bucketOffset;
  }

  /**
   * @return Returns the bucketStartDate.
   */
  protected Date getBucketStartDate() {
    return bucketStartDate;
  }

  /**
   * @return Returns the catalogCode.
   */
  protected Catalog getCatalogCode() {
    return catalogCode;
  }

  /**
   * @return Returns the code.
   */
  protected String getCode() {
    return code;
  }

  /**
   * @return Returns the description.
   */
  protected String getDescription() {
    return description;
  }

  /**
   * @return Returns the forecastMethod.
   */
  protected ForecastMethod getForecastMethod() {
    return forecastMethod;
  }

  /**
   * @return Returns the forecastType.
   */
  protected ForecastType getForecastType() {
    return forecastType;
  }

  /**
   * @return Returns the planningItemCode.
   */
  protected GenericItem getPlanningItemCode() {
    return planningItemCode;
  }

  /**
   * @return Returns the planningLevelCode.
   */
  protected PlanningLevel getPlanningLevelCode() {
    return planningLevelCode;
  }

  /**
   * @return Returns the requiredDateFrom.
   */
  protected Date getRequiredDateFrom() {
    return requiredDateFrom;
  }

  /**
   * @return Returns the requiredDateTill.
   */
  protected Date getRequiredDateTill() {
    return requiredDateTill;
  }

  /**
   * @return Returns the warehouseCode.
   */
  protected Contractor getWarehouseCode() {
    return warehouseCode;
  }


}
