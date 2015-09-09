/*
 * Employees.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Employees.java,v 1.6 2007/11/19 13:46:09 safonov Exp $
 */
@DataItemName("Reference.Employees")
public class Employees extends com.mg.merp.reference.model.Contractor implements
    java.io.Serializable {

  // Fields

  private com.mg.merp.reference.model.NaturalPerson NaturalPerson;

  private java.lang.String Office;

  private java.lang.String TabNum;

  private boolean IsDefault;

  // Constructors

  /**
   * default constructor
   */
  public Employees() {
  }

  // Property accessors

  /**
   *
   */
  public com.mg.merp.reference.model.NaturalPerson getNaturalPerson() {
    return this.NaturalPerson;
  }

  public void setNaturalPerson(
      com.mg.merp.reference.model.NaturalPerson NaturalPerson) {
    this.NaturalPerson = NaturalPerson;
  }

  @DataItemName("Reference.Employee.Office")
  public java.lang.String getOffice() {
    return this.Office;
  }

  public void setOffice(java.lang.String Office) {
    this.Office = Office;
  }

  /**
   *
   */
  @DataItemName("Reference.Employee.TabNum")
  public java.lang.String getTabNum() {
    return this.TabNum;
  }

  public void setTabNum(java.lang.String TabNum) {
    this.TabNum = TabNum;
  }

  /**
   *
   */
  @DataItemName("Reference.Employee.IsDefault")
  public boolean getIsDefault() {
    return this.IsDefault;
  }

  public void setIsDefault(boolean IsDefault) {
    this.IsDefault = IsDefault;
  }
}