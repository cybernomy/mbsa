/*
 * Cell.java
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
 * @version $Id: Cell.java,v 1.4 2006/06/06 08:01:41 leonova Exp $
 */
@DataItemName("MfReference.Cell")
public class Cell extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.mfreference.model.WeekCalendar WeekCal;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.mfreference.model.WorkCenter WorkCenter;

  private java.lang.String CellName;

  private java.math.BigDecimal CapacityFactor;

  private java.lang.String Comment;

  // Constructors

  /**
   * default constructor
   */
  public Cell() {
  }

  /**
   * constructor with id
   */
  public Cell(java.lang.Integer Id) {
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

  @DataItemName("MfReference.Cell.WeekCal")
  public com.mg.merp.mfreference.model.WeekCalendar getWeekCal() {
    return this.WeekCal;
  }

  public void setWeekCal(com.mg.merp.mfreference.model.WeekCalendar WeekCal) {
    this.WeekCal = WeekCal;
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

  @DataItemName("MfReference.Cell.WorkCenter")
  public com.mg.merp.mfreference.model.WorkCenter getWorkCenter() {
    return this.WorkCenter;
  }

  public void setWorkCenter(
      com.mg.merp.mfreference.model.WorkCenter WorkCenter) {
    this.WorkCenter = WorkCenter;
  }

  /**
   *
   */

  @DataItemName("MfReference.Cell.CellName")
  public java.lang.String getCellName() {
    return this.CellName;
  }

  public void setCellName(java.lang.String CellName) {
    this.CellName = CellName;
  }

  /**
   *
   */

  @DataItemName("MfReference.Cell.CapacityFactor")
  public java.math.BigDecimal getCapacityFactor() {
    return this.CapacityFactor;
  }

  public void setCapacityFactor(java.math.BigDecimal CapacityFactor) {
    this.CapacityFactor = CapacityFactor;
  }

  /**
   *
   */

  @DataItemName("MfReference.Cell.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }
}