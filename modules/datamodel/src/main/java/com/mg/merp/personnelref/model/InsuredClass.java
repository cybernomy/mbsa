/*
 * InsuredClass.java
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
 * @version $Id: InsuredClass.java,v 1.5 2006/07/05 11:49:20 leonova Exp $
 */
@DataItemName("PersonnelRef.InsuredClass")
public class InsuredClass extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String CCode;

  private java.lang.String CName;

  private java.math.BigDecimal InsuredPercent;

  private java.math.BigDecimal EmployerPercent;

  // Constructors

  /**
   * default constructor
   */
  public InsuredClass() {
  }

  /**
   * constructor with id
   */
  public InsuredClass(int Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
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

  @DataItemName("PersonnelRef.InsuredClass.Code")
  public java.lang.String getCCode() {
    return this.CCode;
  }

  public void setCCode(java.lang.String Ccode) {
    this.CCode = Ccode;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.InsuredClass.Name")
  public java.lang.String getCName() {
    return this.CName;
  }

  public void setCName(java.lang.String Cname) {
    this.CName = Cname;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.InsuredClass.InsPerc")
  public java.math.BigDecimal getInsuredPercent() {
    return this.InsuredPercent;
  }

  public void setInsuredPercent(java.math.BigDecimal InsuredPercent) {
    this.InsuredPercent = InsuredPercent;
  }

  /**
   *
   */

  @DataItemName("PersonnelRef.InsuredClass.EmplPerc")
  public java.math.BigDecimal getEmployerPercent() {
    return this.EmployerPercent;
  }

  public void setEmployerPercent(java.math.BigDecimal EmployerPercent) {
    this.EmployerPercent = EmployerPercent;
  }

}