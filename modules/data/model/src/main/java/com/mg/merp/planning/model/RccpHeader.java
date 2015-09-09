/*
 * RccpHeader.java
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
 * @version $Id: RccpHeader.java,v 1.4 2006/06/20 06:08:59 leonova Exp $
 */
public class RccpHeader extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.planning.model.Mps Mps;

  private com.mg.merp.mfreference.model.WeekCalendar WeekCal;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String Name;

  private java.lang.Integer RccpVersion;

  private java.util.Date LastRunDateTime;

  // Constructors

  /**
   * default constructor
   */
  public RccpHeader() {
  }

  /**
   * constructor with id
   */
  public RccpHeader(java.lang.Integer Id) {
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
  public com.mg.merp.planning.model.Mps getMps() {
    return this.Mps;
  }

  public void setMps(com.mg.merp.planning.model.Mps Mps) {
    this.Mps = Mps;
  }

  /**
   *
   */
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
  @DataItemName("Planning.RCCP.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
  }

  /**
   *
   */
  @DataItemName("Planning.RCCP.RccpVersion")
  public java.lang.Integer getRccpVersion() {
    return this.RccpVersion;
  }

  public void setRccpVersion(java.lang.Integer RccpVersion) {
    this.RccpVersion = RccpVersion;
  }

  /**
   *
   */
  @DataItemName("Planning.RCCP.LastRunDateTime")
  public java.util.Date getLastRunDateTime() {
    return this.LastRunDateTime;
  }

  public void setLastRunDateTime(java.util.Date LastRunDateTime) {
    this.LastRunDateTime = LastRunDateTime;
  }
}