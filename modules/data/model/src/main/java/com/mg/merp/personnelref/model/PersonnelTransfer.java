/*
 * PersonnelTransfer.java
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
 * @version $Id: PersonnelTransfer.java,v 1.4 2006/08/14 12:48:30 leonova Exp $
 */
public class PersonnelTransfer extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.humanresources.model.Order Order;

  private com.mg.merp.personnelref.model.StaffListUnit StaffListUnit;

  private com.mg.merp.personnelref.model.StaffListPosition StaffListPosition;

  private com.mg.merp.personnelref.model.Personnel Personnel;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.util.Date TransferDate;

  private java.math.BigDecimal Salary;

  private java.math.BigDecimal SalaryRaise;

  // Constructors

  /**
   * default constructor
   */
  public PersonnelTransfer() {
  }

  /**
   * constructor with id
   */
  public PersonnelTransfer(java.lang.Integer Id) {
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

  public com.mg.merp.humanresources.model.Order getOrder() {
    return this.Order;
  }

  public void setOrder(com.mg.merp.humanresources.model.Order HrOrder) {
    this.Order = HrOrder;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.StaffListUnit")
  public com.mg.merp.personnelref.model.StaffListUnit getStaffListUnit() {
    return this.StaffListUnit;
  }

  public void setStaffListUnit(
      com.mg.merp.personnelref.model.StaffListUnit PrefStaffListUnit) {
    this.StaffListUnit = PrefStaffListUnit;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.StaffListPosition")
  public com.mg.merp.personnelref.model.StaffListPosition getStaffListPosition() {
    return this.StaffListPosition;
  }

  public void setStaffListPosition(
      com.mg.merp.personnelref.model.StaffListPosition PrefStaffListPosition) {
    this.StaffListPosition = PrefStaffListPosition;
  }

  /**
   *
   */

  public com.mg.merp.personnelref.model.Personnel getPersonnel() {
    return this.Personnel;
  }

  public void setPersonnel(
      com.mg.merp.personnelref.model.Personnel PrefPersonnel) {
    this.Personnel = PrefPersonnel;
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
  @DataItemName("PersonnelRef.Personnel.TransferDate")
  public java.util.Date getTransferDate() {
    return this.TransferDate;
  }

  public void setTransferDate(java.util.Date TransferDate) {
    this.TransferDate = TransferDate;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.Salary")
  public java.math.BigDecimal getSalary() {
    return this.Salary;
  }

  public void setSalary(java.math.BigDecimal Salary) {
    this.Salary = Salary;
  }

  /**
   *
   */
  @DataItemName("PersonnelRef.Personnel.SalaryRaise")
  public java.math.BigDecimal getSalaryRaise() {
    return this.SalaryRaise;
  }

  public void setSalaryRaise(java.math.BigDecimal SalaryRaise) {
    this.SalaryRaise = SalaryRaise;
  }

}