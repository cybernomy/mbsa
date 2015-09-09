/*
 * PlanningLevelBucket.java
 *
 * Copyright (c) 1998 - 2005 BusinessTechnology, Ltd.
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PlanningLevelBucket.java,v 1.3 2006/06/20 08:38:36 leonova Exp $
 */
@DataItemName("MfReference.PlanningLevelBucket")
public class PlanningLevelBucket extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.mfreference.model.PlanningLevel PlanningLevel;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.util.Date StartDate;

  private java.util.Date EndDate;

  private java.lang.Short BucketOffset;

  // Constructors

  /**
   * default constructor
   */
  public PlanningLevelBucket() {
  }

  /**
   * constructor with id
   */
  public PlanningLevelBucket(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */

  public com.mg.merp.mfreference.model.PlanningLevel getPlanningLevel() {
    return this.PlanningLevel;
  }

  public void setPlanningLevel(
      com.mg.merp.mfreference.model.PlanningLevel PlanningLevel) {
    this.PlanningLevel = PlanningLevel;
  }

  /**
   *
   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */

  @DataItemName("MfReference.PlanLevBuck.StDate")
  public java.util.Date getStartDate() {
    return this.StartDate;
  }

  public void setStartDate(java.util.Date StartDate) {
    this.StartDate = StartDate;
  }

  /**
   *
   */

  @DataItemName("MfReference.PlanLevBuck.EndDate")
  public java.util.Date getEndDate() {
    return this.EndDate;
  }

  public void setEndDate(java.util.Date EndDate) {
    this.EndDate = EndDate;
  }

  /**
   *
   */

  @DataItemName("MfReference.PlanLevBuck.BucketOffset")
  public java.lang.Short getBucketOffset() {
    return this.BucketOffset;
  }

  public void setBucketOffset(java.lang.Short BucketOffset) {
    this.BucketOffset = BucketOffset;
  }

}