/*
 * ResourceGroupCapacity.java
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
 * @version $Id: ResourceGroupCapacity.java,v 1.1 2005/06/10 06:51:32 safonov Exp $
 */
public class ResourceGroupCapacity extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.mfreference.model.ResourceGroup ResourceGroup;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.math.BigDecimal PlanningCapacity;

  private java.math.BigDecimal MaximumCapacity;

  private java.util.Date EffOnDate;

  private java.util.Date EffOffDate;

  // Constructors

  /**
   * default constructor
   */
  public ResourceGroupCapacity() {
  }

  /**
   * constructor with id
   */
  public ResourceGroupCapacity(java.lang.Integer Id) {
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

  public com.mg.merp.mfreference.model.ResourceGroup getResourceGroup() {
    return this.ResourceGroup;
  }

  public void setResourceGroup(
      com.mg.merp.mfreference.model.ResourceGroup ResourceGroup) {
    this.ResourceGroup = ResourceGroup;
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

  @DataItemName("MfReference.RGCapacity.PlanCap")
  public java.math.BigDecimal getPlanningCapacity() {
    return this.PlanningCapacity;
  }

  public void setPlanningCapacity(java.math.BigDecimal PlanningCapacity) {
    this.PlanningCapacity = PlanningCapacity;
  }

  /**
   *
   */

  @DataItemName("MfReference.RGCapacity.MaxCap")
  public java.math.BigDecimal getMaximumCapacity() {
    return this.MaximumCapacity;
  }

  public void setMaximumCapacity(java.math.BigDecimal MaximumCapacity) {
    this.MaximumCapacity = MaximumCapacity;
  }

  /**
   *
   */

  @DataItemName("MfReference.RGCapacity.EffOnDate")
  public java.util.Date getEffOnDate() {
    return this.EffOnDate;
  }

  public void setEffOnDate(java.util.Date EffOnDate) {
    this.EffOnDate = EffOnDate;
  }

  /**
   *
   */

  @DataItemName("MfReference.RGCapacity.EffOffDate")
  public java.util.Date getEffOffDate() {
    return this.EffOffDate;
  }

  public void setEffOffDate(java.util.Date EffOffDate) {
    this.EffOffDate = EffOffDate;
  }

}