/*
 * TariffScaleClass.java
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
 * @version $Id: TariffScaleClass.java,v 1.4 2006/10/02 10:16:28 leonova Exp $
 */
public class TariffScaleClass extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.personnelref.model.TariffScale TariffScale;

  private java.lang.Integer ClassNumber;

  private java.math.BigDecimal Factor;

  private java.math.BigDecimal Rate;

  // Constructors

  /**
   * default constructor
   */
  public TariffScaleClass() {
  }

  /**
   * constructor with id
   */
  public TariffScaleClass(java.lang.Integer Id) {
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

  public com.mg.merp.personnelref.model.TariffScale getTariffScale() {
    return this.TariffScale;
  }

  public void setTariffScale(
      com.mg.merp.personnelref.model.TariffScale PrefTariffScale) {
    this.TariffScale = PrefTariffScale;
  }

  /**
   *
   */
  @DataItemName("Reference.TariffingScale.ClassNumber")
  public java.lang.Integer getClassNumber() {
    return this.ClassNumber;
  }

  public void setClassNumber(java.lang.Integer ClassNumber) {
    this.ClassNumber = ClassNumber;
  }

  /**
   *
   */
  @DataItemName("Reference.TariffingScale.Factor")
  public java.math.BigDecimal getFactor() {
    return this.Factor;
  }

  public void setFactor(java.math.BigDecimal Factor) {
    this.Factor = Factor;
  }

  /**
   *
   */
  @DataItemName("Reference.TariffingScale.Rate")
  public java.math.BigDecimal getRate() {
    return this.Rate;
  }

  public void setRate(java.math.BigDecimal Rate) {
    this.Rate = Rate;
  }

}