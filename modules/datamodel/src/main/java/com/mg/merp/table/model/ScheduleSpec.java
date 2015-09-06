/*
 * ScheduleSpec.java
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
package com.mg.merp.table.model;


/**
 * @author hbm2java
 * @version $Id: ScheduleSpec.java,v 1.2 2005/06/28 10:04:10 pashistova Exp $
 */
public class ScheduleSpec extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.table.model.TimeKind TimeKind;
  private com.mg.merp.table.model.ScheduleHead ScheduleHead;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.math.BigDecimal HoursQuantity;
  private java.util.Date ScheduleDate;


  // Constructors

  /**
   * default constructor
   */
  public ScheduleSpec() {
  }

  /**
   * constructor with id
   */
  public ScheduleSpec(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */

  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**

   */

  public com.mg.merp.table.model.TimeKind getTimeKind() {
    return this.TimeKind;
  }

  public void setTimeKind(com.mg.merp.table.model.TimeKind TabTimeKind) {
    this.TimeKind = TabTimeKind;
  }

  /**

   */

  public com.mg.merp.table.model.ScheduleHead getScheduleHead() {
    return this.ScheduleHead;
  }

  public void setScheduleHead(com.mg.merp.table.model.ScheduleHead TabScheduleHead) {
    this.ScheduleHead = TabScheduleHead;
  }

  /**

   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**

   */

  public java.math.BigDecimal getHoursQuantity() {
    return this.HoursQuantity;
  }

  public void setHoursQuantity(java.math.BigDecimal HoursQuantity) {
    this.HoursQuantity = HoursQuantity;
  }

  /**

   */

  public java.util.Date getScheduleDate() {
    return this.ScheduleDate;
  }

  public void setScheduleDate(java.util.Date ScheduleDate) {
    this.ScheduleDate = ScheduleDate;
  }


}