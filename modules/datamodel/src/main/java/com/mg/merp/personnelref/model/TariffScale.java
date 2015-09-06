/*
 * TariffScale.java
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
 * @version $Id: TariffScale.java,v 1.4 2006/10/12 11:50:17 safonov Exp $
 */
public class TariffScale extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.baiengine.model.Repository FirstClassAlg;

  private java.lang.String SCode;

  private java.lang.String SName;

  private TariffingScaleType SType;

  private java.util.Date BeginDate;

  private java.util.Set SetOfPrefTariffScaleClass;

  // Constructors

  /**
   * default constructor
   */
  public TariffScale() {
  }

  /**
   * constructor with id
   */
  public TariffScale(java.lang.Integer Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.TariffScale.FirstClassAlg")
  public com.mg.merp.baiengine.model.Repository getFirstClassAlg() {
    return this.FirstClassAlg;
  }

  public void setFirstClassAlg(
      com.mg.merp.baiengine.model.Repository AlgRepository) {
    this.FirstClassAlg = AlgRepository;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.TariffScale.Code")
  public java.lang.String getSCode() {
    return this.SCode;
  }

  public void setSCode(java.lang.String Scode) {
    this.SCode = Scode;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.TariffScale.Name")
  public java.lang.String getSName() {
    return this.SName;
  }

  public void setSName(java.lang.String Sname) {
    this.SName = Sname;
  }

  /**
   *
   */
  @DataItemName("Reference.TariffingScale.TariffingScaleType")
  public TariffingScaleType getSType() {
    return this.SType;
  }

  public void setSType(TariffingScaleType Stype) {
    this.SType = Stype;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.TariffScale.BeginDate")
  public java.util.Date getBeginDate() {
    return this.BeginDate;
  }

  public void setBeginDate(java.util.Date Begindate) {
    this.BeginDate = Begindate;
  }

  /**
   *
   */

  public java.util.Set getSetOfPrefTariffScaleClass() {
    return this.SetOfPrefTariffScaleClass;
  }

  public void setSetOfPrefTariffScaleClass(
      java.util.Set SetOfPrefTariffScaleClass) {
    this.SetOfPrefTariffScaleClass = SetOfPrefTariffScaleClass;
  }

}