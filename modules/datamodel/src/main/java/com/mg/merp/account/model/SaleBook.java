/*
 * SaleBook.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: SaleBook.java,v 1.6 2006/11/02 15:40:06 safonov Exp $
 */
public class SaleBook extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.document.model.DocHead DocHead;

  private com.mg.merp.reference.model.Contractor Customer;

  private com.mg.merp.core.model.Folder Folder;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.document.model.DocType DocType;

  private com.mg.merp.reference.model.Contractor OrgUnit;

  private java.lang.String DocNumber;

  private java.util.Date DocDate;

  private java.util.Date InsertDate;

  private java.util.Date PayDate;

  private java.math.BigDecimal TotalSum;

  private java.math.BigDecimal SumWithoutNds1;

  private java.math.BigDecimal SumWithoutNds2;

  private java.math.BigDecimal Nds1Sum;

  private java.math.BigDecimal Nds2Sum;

  private java.math.BigDecimal NotTaxableSum;

  private java.math.BigDecimal NotTaxableExportSum;

  private java.math.BigDecimal NspSum;

  private java.math.BigDecimal SumWithoutNds3;

  private java.math.BigDecimal Nds3Sum;

  // Constructors

  /**
   * default constructor
   */
  public SaleBook() {
  }

  /**
   * constructor with id
   */
  public SaleBook(java.lang.Integer Id) {
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

  public com.mg.merp.document.model.DocHead getDocHead() {
    return this.DocHead;
  }

  public void setDocHead(com.mg.merp.document.model.DocHead Dochead) {
    this.DocHead = Dochead;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.Customer")
  public com.mg.merp.reference.model.Contractor getCustomer() {
    return this.Customer;
  }

  public void setCustomer(com.mg.merp.reference.model.Contractor Contractor) {
    this.Customer = Contractor;
  }

  /**
   *
   */

  public com.mg.merp.core.model.Folder getFolder() {
    return this.Folder;
  }

  public void setFolder(com.mg.merp.core.model.Folder Folder) {
    this.Folder = Folder;
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
  @DataItemName("Account.SaleBook.DocType")
  public com.mg.merp.document.model.DocType getDocType() {
    return this.DocType;
  }

  public void setDocType(com.mg.merp.document.model.DocType Typedoc) {
    this.DocType = Typedoc;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.OrgUnit")
  public com.mg.merp.reference.model.Contractor getOrgUnit() {
    return this.OrgUnit;
  }

  public void setOrgUnit(com.mg.merp.reference.model.Contractor Contractor_1) {
    this.OrgUnit = Contractor_1;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.DocNumber")
  public java.lang.String getDocNumber() {
    return this.DocNumber;
  }

  public void setDocNumber(java.lang.String Docnumber) {
    this.DocNumber = Docnumber;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.DocDate")
  public java.util.Date getDocDate() {
    return this.DocDate;
  }

  public void setDocDate(java.util.Date Docdate) {
    this.DocDate = Docdate;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.InsertDate")
  public java.util.Date getInsertDate() {
    return this.InsertDate;
  }

  public void setInsertDate(java.util.Date Insertdate) {
    this.InsertDate = Insertdate;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.PayDate")
  public java.util.Date getPayDate() {
    return this.PayDate;
  }

  public void setPayDate(java.util.Date Paydate) {
    this.PayDate = Paydate;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.TotalSum")
  public java.math.BigDecimal getTotalSum() {
    return this.TotalSum;
  }

  public void setTotalSum(java.math.BigDecimal Totalsum) {
    this.TotalSum = Totalsum;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.SumWithoutNds1")
  public java.math.BigDecimal getSumWithoutNds1() {
    return this.SumWithoutNds1;
  }

  public void setSumWithoutNds1(java.math.BigDecimal SumWithoutNds1) {
    this.SumWithoutNds1 = SumWithoutNds1;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.SumWithoutNds2")
  public java.math.BigDecimal getSumWithoutNds2() {
    return this.SumWithoutNds2;
  }

  public void setSumWithoutNds2(java.math.BigDecimal SumWithoutNds2) {
    this.SumWithoutNds2 = SumWithoutNds2;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.Nds1Sum")
  public java.math.BigDecimal getNds1Sum() {
    return this.Nds1Sum;
  }

  public void setNds1Sum(java.math.BigDecimal Nds1Sum) {
    this.Nds1Sum = Nds1Sum;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.Nds2Sum")
  public java.math.BigDecimal getNds2Sum() {
    return this.Nds2Sum;
  }

  public void setNds2Sum(java.math.BigDecimal Nds2Sum) {
    this.Nds2Sum = Nds2Sum;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.NotTaxableSum")
  public java.math.BigDecimal getNotTaxableSum() {
    return this.NotTaxableSum;
  }

  public void setNotTaxableSum(java.math.BigDecimal NotTaxableSum) {
    this.NotTaxableSum = NotTaxableSum;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.NotTaxableExportSum")
  public java.math.BigDecimal getNotTaxableExportSum() {
    return this.NotTaxableExportSum;
  }

  public void setNotTaxableExportSum(java.math.BigDecimal NotTaxableExportSum) {
    this.NotTaxableExportSum = NotTaxableExportSum;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.NspSum")
  public java.math.BigDecimal getNspSum() {
    return this.NspSum;
  }

  public void setNspSum(java.math.BigDecimal NspSum) {
    this.NspSum = NspSum;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.SumWithoutNds3")
  public java.math.BigDecimal getSumWithoutNds3() {
    return this.SumWithoutNds3;
  }

  public void setSumWithoutNds3(java.math.BigDecimal SumWithoutNds3) {
    this.SumWithoutNds3 = SumWithoutNds3;
  }

  /**
   *
   */
  @DataItemName("Account.SaleBook.Nds3Sum")
  public java.math.BigDecimal getNds3Sum() {
    return this.Nds3Sum;
  }

  public void setNds3Sum(java.math.BigDecimal Nds3Sum) {
    this.Nds3Sum = Nds3Sum;
  }

}