/*
 * RisePercent.java
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
package com.mg.merp.personnelref.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: RisePercent.java,v 1.3 2006/04/13 10:23:53 safonov Exp $
 */
public class RisePercent extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.personnelref.model.RiseScale RiseScale;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.Integer PercentNumber;

  private java.math.BigDecimal ServiceFrom;

  private java.math.BigDecimal ServiceTo;

  private java.math.BigDecimal RiseValue;

  // Constructors

  /**
   * default constructor
   */
  public RisePercent() {
  }

  /**
   * constructor with id
   */
  public RisePercent(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */

  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */

  public com.mg.merp.personnelref.model.RiseScale getRiseScale() {
    return this.RiseScale;
  }

  public void setRiseScale(
      com.mg.merp.personnelref.model.RiseScale PrefRiseScale) {
    this.RiseScale = PrefRiseScale;
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
  @DataItemName("PersonnelRef.RisePercent.PercentNumber")
  public java.lang.Integer getPercentNumber() {
    return this.PercentNumber;
  }

  public void setPercentNumber(java.lang.Integer PercentNumber) {
    this.PercentNumber = PercentNumber;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.RisePercent.ServiceFrom")
  public java.math.BigDecimal getServiceFrom() {
    return this.ServiceFrom;
  }

  public void setServiceFrom(java.math.BigDecimal ServiceFrom) {
    this.ServiceFrom = ServiceFrom;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.RisePercent.ServiceTo")
  public java.math.BigDecimal getServiceTo() {
    return this.ServiceTo;
  }

  public void setServiceTo(java.math.BigDecimal ServiceTo) {
    this.ServiceTo = ServiceTo;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.RisePercent.RiseValue")
  public java.math.BigDecimal getRiseValue() {
    return this.RiseValue;
  }

  public void setRiseValue(java.math.BigDecimal RiseValue) {
    this.RiseValue = RiseValue;
  }

}