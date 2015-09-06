/*
 * ItemFamily.java
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
package com.mg.merp.planning.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: ItemFamily.java,v 1.4 2007/02/05 16:07:41 safonov Exp $
 */
public class ItemFamily extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.mfreference.model.PlanningLevel PlanningLevel;

  private com.mg.merp.planning.model.GenericItem ChildGenericItem;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.planning.model.GenericItem ParentGenericItem;

  private java.util.Date EffOnDate;

  private java.util.Date EffOffDate;

  private java.math.BigDecimal AllocationPercent;

  // Constructors

  /**
   * default constructor
   */
  public ItemFamily() {
  }

  /**
   * constructor with id
   */
  public ItemFamily(java.lang.Integer Id) {
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
  @DataItemName("Planning.ItemFamily.ChildGenericItem")
  public com.mg.merp.planning.model.GenericItem getChildGenericItem() {
    return this.ChildGenericItem;
  }

  public void setChildGenericItem(
      com.mg.merp.planning.model.GenericItem ChildGenericItem) {
    this.ChildGenericItem = ChildGenericItem;
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
  @DataItemName("Planning.ItemFamily.ParentGenericItem")
  public com.mg.merp.planning.model.GenericItem getParentGenericItem() {
    return this.ParentGenericItem;
  }

  public void setParentGenericItem(
      com.mg.merp.planning.model.GenericItem ParentGenericItem) {
    this.ParentGenericItem = ParentGenericItem;
  }

  /**
   *
   */
  @DataItemName("Planning.ItemFamily.EffOnDate")
  public java.util.Date getEffOnDate() {
    return this.EffOnDate;
  }

  public void setEffOnDate(java.util.Date EffOnDate) {
    this.EffOnDate = EffOnDate;
  }

  /**
   *
   */
  @DataItemName("Planning.ItemFamily.EffOffDate")
  public java.util.Date getEffOffDate() {
    return this.EffOffDate;
  }

  public void setEffOffDate(java.util.Date EffOffDate) {
    this.EffOffDate = EffOffDate;
  }

  /**
   *
   */
  @DataItemName("Planning.ItemFamily.AllocationPercent")
  public java.math.BigDecimal getAllocationPercent() {
    return this.AllocationPercent;
  }

  public void setAllocationPercent(java.math.BigDecimal AllocationPercent) {
    this.AllocationPercent = AllocationPercent;
  }

}