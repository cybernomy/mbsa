/*
 * PlanningLevel.java
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
 * @version $Id: PlanningLevel.java,v 1.4 2006/06/20 08:38:46 leonova Exp $
 */
@DataItemName("MfReference.PlanningLevel")
public class PlanningLevel extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.Short PlanningLevelNum;

  private java.lang.String Description;

  private java.lang.Short TimeFence;

  // Constructors

  /**
   * default constructor
   */
  public PlanningLevel() {
  }

  /**
   * constructor with id
   */
  public PlanningLevel(java.lang.Integer Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */

  @DataItemName("MfReference.PlanningLevel.PlanningLevelNum")
  public java.lang.Short getPlanningLevelNum() {
    return this.PlanningLevelNum;
  }

  public void setPlanningLevelNum(java.lang.Short PlanningLevelNum) {
    this.PlanningLevelNum = PlanningLevelNum;
  }

  /**
   *
   */

  @DataItemName("MfReference.PlanningLevel.Descr")
  public java.lang.String getDescription() {
    return this.Description;
  }

  public void setDescription(java.lang.String Description) {
    this.Description = Description;
  }

  /**
   *
   */

  @DataItemName("MfReference.PlanningLevel.TimeFence")
  public java.lang.Short getTimeFence() {
    return this.TimeFence;
  }

  public void setTimeFence(java.lang.Short TimeFence) {
    this.TimeFence = TimeFence;
  }
}