/*
 * InvoiceHeadModel.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: InvoiceHeadModel.java,v 1.7 2006/12/20 12:13:27 leonova Exp $
 */
public class InvoiceHeadModel extends com.mg.merp.document.model.DocHeadModel
    implements java.io.Serializable {

  // Fields

  private com.mg.merp.reference.model.BankAccount PartnerToBankReq;

  private com.mg.merp.reference.model.Contractor Consignee;

  private com.mg.merp.reference.model.Contractor Shipper;

  private com.mg.merp.reference.model.Contractor Responsible;

  private com.mg.merp.reference.model.BankAccount PartnerFromBankReq;

  private com.mg.merp.reference.model.Contractor Consumer;

  private java.math.BigDecimal SummaCurWithDiscount;

  private java.math.BigDecimal SummaNatWithDiscount;

  private java.math.BigDecimal AddExpenses;

  private java.math.BigDecimal DiscountOnDoc;

  private java.math.BigDecimal DiscountOnLine;

  private java.lang.String ToPayDocNumber;

  private java.util.Date ToPayDocDate;

  private java.lang.String Comment;

  private java.lang.String ProviderOKONH;

  private java.lang.String ProviderINN;

  private java.lang.String ProviderOKPO;

  private java.lang.String CustomerOKPO;

  private java.lang.String CustomerOKONH;

  private java.lang.String CustomerINN;

  // Constructors

  /**
   * default constructor
   */
  public InvoiceHeadModel() {
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHead.PartnerToBankReq")
  public com.mg.merp.reference.model.BankAccount getPartnerToBankReq() {
    return this.PartnerToBankReq;
  }

  public void setPartnerToBankReq(
      com.mg.merp.reference.model.BankAccount PartnerToBankReq) {
    this.PartnerToBankReq = PartnerToBankReq;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHeadModel.Consignee")
  public com.mg.merp.reference.model.Contractor getConsignee() {
    return this.Consignee;
  }

  public void setConsignee(com.mg.merp.reference.model.Contractor Consignee) {
    this.Consignee = Consignee;
  }

  @DataItemName("Warehouse.InvoiceHeadModel.Shipper")
  public com.mg.merp.reference.model.Contractor getShipper() {
    return this.Shipper;
  }

  public void setShipper(com.mg.merp.reference.model.Contractor Shipper) {
    this.Shipper = Shipper;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHeadModel.Responsible")
  public com.mg.merp.reference.model.Contractor getResponsible() {
    return this.Responsible;
  }

  public void setResponsible(
      com.mg.merp.reference.model.Contractor Responsible) {
    this.Responsible = Responsible;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHead.PartnerFromBankReq")
  public com.mg.merp.reference.model.BankAccount getPartnerFromBankReq() {
    return this.PartnerFromBankReq;
  }

  public void setPartnerFromBankReq(
      com.mg.merp.reference.model.BankAccount PartnerFromBankReq) {
    this.PartnerFromBankReq = PartnerFromBankReq;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHead.Consumer")
  public com.mg.merp.reference.model.Contractor getConsumer() {
    return this.Consumer;
  }

  public void setConsumer(com.mg.merp.reference.model.Contractor Consumer) {
    this.Consumer = Consumer;
  }

  /**
   *
   */

  public java.math.BigDecimal getSummaCurWithDiscount() {
    return this.SummaCurWithDiscount;
  }

  public void setSummaCurWithDiscount(
      java.math.BigDecimal SummaCurWithDiscount) {
    this.SummaCurWithDiscount = SummaCurWithDiscount;
  }

  /**
   *
   */

  public java.math.BigDecimal getSummaNatWithDiscount() {
    return this.SummaNatWithDiscount;
  }

  public void setSummaNatWithDiscount(
      java.math.BigDecimal SummaNatWithDiscount) {
    this.SummaNatWithDiscount = SummaNatWithDiscount;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHeadModel.AddExpenses")
  public java.math.BigDecimal getAddExpenses() {
    return this.AddExpenses;
  }

  public void setAddExpenses(java.math.BigDecimal AddExpenses) {
    this.AddExpenses = AddExpenses;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHeadModel.DiscountOnDoc")
  public java.math.BigDecimal getDiscountOnDoc() {
    return this.DiscountOnDoc;
  }

  public void setDiscountOnDoc(java.math.BigDecimal DiscountOnDoc) {
    this.DiscountOnDoc = DiscountOnDoc;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHeadModel.DiscountOnLine")
  public java.math.BigDecimal getDiscountOnLine() {
    return this.DiscountOnLine;
  }

  public void setDiscountOnLine(java.math.BigDecimal DiscountOnLine) {
    this.DiscountOnLine = DiscountOnLine;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHeadModel.ToPayDocNumber")
  public java.lang.String getToPayDocNumber() {
    return this.ToPayDocNumber;
  }

  public void setToPayDocNumber(java.lang.String ToPayDocNumber) {
    this.ToPayDocNumber = ToPayDocNumber;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHeadModel.ToPayDocDate")
  public java.util.Date getToPayDocDate() {
    return this.ToPayDocDate;
  }

  public void setToPayDocDate(java.util.Date ToPayDocDate) {
    this.ToPayDocDate = ToPayDocDate;
  }

  /**
   *
   */
  @DataItemName("Warehouse.InvoiceHeadModel.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  public java.lang.String getProviderOKONH() {
    return this.ProviderOKONH;
  }

  public void setProviderOKONH(java.lang.String ProviderOKONH) {
    this.ProviderOKONH = ProviderOKONH;
  }

  /**
   *
   */

  public java.lang.String getProviderINN() {
    return this.ProviderINN;
  }

  public void setProviderINN(java.lang.String ProviderINN) {
    this.ProviderINN = ProviderINN;
  }

  /**
   *
   */

  public java.lang.String getProviderOKPO() {
    return this.ProviderOKPO;
  }

  public void setProviderOKPO(java.lang.String ProviderOKPO) {
    this.ProviderOKPO = ProviderOKPO;
  }

  /**
   *
   */

  public java.lang.String getCustomerOKPO() {
    return this.CustomerOKPO;
  }

  public void setCustomerOKPO(java.lang.String CustomerOKPO) {
    this.CustomerOKPO = CustomerOKPO;
  }

  /**
   *
   */

  public java.lang.String getCustomerOKONH() {
    return this.CustomerOKONH;
  }

  public void setCustomerOKONH(java.lang.String CustomerOKONH) {
    this.CustomerOKONH = CustomerOKONH;
  }

  /**
   *
   */

  public java.lang.String getCustomerINN() {
    return this.CustomerINN;
  }

  public void setCustomerINN(java.lang.String CustomerINN) {
    this.CustomerINN = CustomerINN;
  }

}