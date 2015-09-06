/*
 * CalcListSectionRef.java
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
package com.mg.merp.salary.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: CalcListSectionRef.java,v 1.3 2005/08/11 12:44:03 pashistova Exp $
 */
@DataItemName("Salary.CalcListSectionRef")
public class CalcListSectionRef extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.lang.String SName;

  private java.lang.Integer Priority;

  private TripleSumSign SumSign;

  // Constructors

  /**
   * default constructor
   */
  public CalcListSectionRef() {
  }

  /**
   * constructor with id
   */
  public CalcListSectionRef(java.lang.Integer Id) {
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
  @DataItemName("Salary.Name")
  public java.lang.String getSName() {
    return this.SName;
  }

  public void setSName(java.lang.String Sname) {
    this.SName = Sname;
  }

  /**
   *
   */
  @DataItemName("Salary.SectionRef.Priority")
  public java.lang.Integer getPriority() {
    return this.Priority;
  }

  public void setPriority(java.lang.Integer Priority) {
    this.Priority = Priority;
  }

  /**
   *
   */
  @DataItemName("Salary.SectionRef.SumSign")
  public TripleSumSign getSumSign() {
    return this.SumSign;
  }

  public void setSumSign(TripleSumSign SumSign) {
    this.SumSign = SumSign;
  }

}