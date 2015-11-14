/*
 * BankDocument.java
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
 * @version $Id: BankDocument.java,v 1.8 2008/02/29 12:31:40 safonov Exp $
 */
public class BankDocument extends com.mg.merp.document.model.DocHead implements
    java.io.Serializable, org.hibernate.bytecode.internal.javassist.FieldHandled {

  // Fields

  private com.mg.merp.reference.model.BankAccount PayerBankReq;

  private com.mg.merp.reference.model.BankAccount RecipientBankReq;

  private com.mg.merp.reference.model.Contractor PaymentFor;

  private com.mg.merp.reference.model.Contractor Responsible;

  private java.math.BigDecimal Nds1Rate;

  private java.math.BigDecimal Nds2Rate;

  private java.math.BigDecimal Nds1Summa;

  private java.math.BigDecimal Nds2Summa;

  private java.math.BigDecimal ClearSumma;

  private java.math.BigDecimal SummaWithNds1;

  private java.math.BigDecimal SummaWithNds2;

  private PayWayType PayWay;

  private java.lang.String Comment;

  private java.util.Date PayTime;

  private java.lang.Short PayTurn;

  private java.lang.String PayTarget;

  private java.lang.String PayType;

  private java.lang.String PayCode;

  private com.mg.merp.reference.model.Kbk Kbk;

  private com.mg.merp.reference.model.Okato Okato;

  private java.lang.String PaymentBaseIdx;

  private java.lang.String TaxPeriodIdx1;

  private java.lang.String TaxPeriodIdx2;

  private java.lang.String TaxPeriodIdx3;

  private java.lang.String DocNumberIdx;

  private java.lang.String DocDateIdx;

  private java.lang.String PaymentTypeIdx;

  private java.lang.String PayerStatus;

  // Constructors

  /**
   * default constructor
   */
  public BankDocument() {
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("Account.BankIn.PayerBankReq")
  public com.mg.merp.reference.model.BankAccount getPayerBankReq() {
    return this.PayerBankReq;
  }

  public void setPayerBankReq(
      com.mg.merp.reference.model.BankAccount PayerBankReq) {
    this.PayerBankReq = PayerBankReq;
  }

  @DataItemName("Account.BankIn.RecipientBankReq")
  public com.mg.merp.reference.model.BankAccount getRecipientBankReq() {
    return this.RecipientBankReq;
  }

  public void setRecipientBankReq(
      com.mg.merp.reference.model.BankAccount RecipientBankReq) {
    this.RecipientBankReq = RecipientBankReq;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Contractor getPaymentFor() {
    return this.PaymentFor;
  }

  public void setPaymentFor(com.mg.merp.reference.model.Contractor PaymentFor) {
    this.PaymentFor = PaymentFor;
  }

  /**
   *
   */

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
  @DataItemName("Account.BankIn.Nds1Rate")
  public java.math.BigDecimal getNds1Rate() {
    return this.Nds1Rate;
  }

  public void setNds1Rate(java.math.BigDecimal Nds1Rate) {
    this.Nds1Rate = Nds1Rate;
  }

  /**
   *
   */
  @DataItemName("Account.BankIn.Nds2Rate")
  public java.math.BigDecimal getNds2Rate() {
    return this.Nds2Rate;
  }

  public void setNds2Rate(java.math.BigDecimal Nds2Rate) {
    this.Nds2Rate = Nds2Rate;
  }

  /**
   *
   */
  @DataItemName("Account.BankIn.Nds1Summa")
  public java.math.BigDecimal getNds1Summa() {
    return this.Nds1Summa;
  }

  public void setNds1Summa(java.math.BigDecimal Nds1Summa) {
    this.Nds1Summa = Nds1Summa;
  }

  /**
   *
   */
  @DataItemName("Account.BankIn.Nds2Summa")
  public java.math.BigDecimal getNds2Summa() {
    return this.Nds2Summa;
  }

  public void setNds2Summa(java.math.BigDecimal Nds2Summa) {
    this.Nds2Summa = Nds2Summa;
  }

  /**
   *
   */
  @DataItemName("Account.BankIn.ClearSumma")
  public java.math.BigDecimal getClearSumma() {
    return this.ClearSumma;
  }

  public void setClearSumma(java.math.BigDecimal ClearSumma) {
    this.ClearSumma = ClearSumma;
  }

  /**
   *
   */
  @DataItemName("Account.BankIn.SummaWithNds1")
  public java.math.BigDecimal getSummaWithNds1() {
    return this.SummaWithNds1;
  }

  public void setSummaWithNds1(java.math.BigDecimal SummaWithNds1) {
    this.SummaWithNds1 = SummaWithNds1;
  }

  /**
   *
   */

  @DataItemName("Account.BankIn.SummaWithNds2")
  public java.math.BigDecimal getSummaWithNds2() {
    return this.SummaWithNds2;
  }

  public void setSummaWithNds2(java.math.BigDecimal SummaWithNds2) {
    this.SummaWithNds2 = SummaWithNds2;
  }

  /**
   *
   */

  public PayWayType getPayWay() {
    return this.PayWay;
  }

  public void setPayWay(PayWayType PayWay) {
    this.PayWay = PayWay;
  }

  /**
   *
   */
  @DataItemName("Account.BankDocument.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("Account.BankOut.PayTime")
  public java.util.Date getPayTime() {
    return this.PayTime;
  }

  public void setPayTime(java.util.Date PayTime) {
    this.PayTime = PayTime;
  }

  /**
   *
   */
  @DataItemName("Account.Adv.PayTurn")
  public java.lang.Short getPayTurn() {
    return this.PayTurn;
  }

  public void setPayTurn(java.lang.Short PayTurn) {
    this.PayTurn = PayTurn;
  }

  /**
   *
   */
  @DataItemName("Account.Adv.PayTarget")
  public java.lang.String getPayTarget() {
    return this.PayTarget;
  }

  public void setPayTarget(java.lang.String PayTarget) {
    this.PayTarget = PayTarget;
  }

  /**
   *
   */
  @DataItemName("Account.Adv.PayType")
  public java.lang.String getPayType() {
    return this.PayType;
  }

  public void setPayType(java.lang.String PayType) {
    this.PayType = PayType;
  }

  /**
   *
   */
  @DataItemName("Account.Adv.PayCode")
  public java.lang.String getPayCode() {
    return this.PayCode;
  }

  public void setPayCode(java.lang.String PayCode) {
    this.PayCode = PayCode;
  }

  /**
   *
   */
  @DataItemName("Account.BankOut.Kbk")
  public com.mg.merp.reference.model.Kbk getKbk() {
    return this.Kbk;
  }

  public void setKbk(com.mg.merp.reference.model.Kbk Kbk) {
    this.Kbk = Kbk;
  }

  /**
   *
   */
  @DataItemName("Account.BankOut.Okato")
  public com.mg.merp.reference.model.Okato getOkato() {
    return this.Okato;
  }

  public void setOkato(com.mg.merp.reference.model.Okato Okato) {
    this.Okato = Okato;
  }

  /**
   *
   */
  @DataItemName("Account.BankDocument.PaymentBaseIdx")
  public java.lang.String getPaymentBaseIdx() {
    return this.PaymentBaseIdx;
  }

  public void setPaymentBaseIdx(java.lang.String PaymentBaseIdx) {
    this.PaymentBaseIdx = PaymentBaseIdx;
  }

  /**
   *
   */
  @DataItemName("Account.BankDocument.TaxPeriodIdx1")
  public java.lang.String getTaxPeriodIdx1() {
    return this.TaxPeriodIdx1;
  }

  public void setTaxPeriodIdx1(java.lang.String TaxPeriodIdx1) {
    this.TaxPeriodIdx1 = TaxPeriodIdx1;
  }

  /**
   *
   */
  @DataItemName("Account.BankDocument.TaxPeriodIdx2")
  public java.lang.String getTaxPeriodIdx2() {
    return this.TaxPeriodIdx2;
  }

  public void setTaxPeriodIdx2(java.lang.String TaxPeriodIdx2) {
    this.TaxPeriodIdx2 = TaxPeriodIdx2;
  }

  /**
   *
   */
  @DataItemName("Account.BankDocument.TaxPeriodIdx3")
  public java.lang.String getTaxPeriodIdx3() {
    return this.TaxPeriodIdx3;
  }

  public void setTaxPeriodIdx3(java.lang.String TaxPeriodIdx3) {
    this.TaxPeriodIdx3 = TaxPeriodIdx3;
  }

  /**
   *
   */
  @DataItemName("Account.BankOut.DocNumberIdx")
  public java.lang.String getDocNumberIdx() {
    return this.DocNumberIdx;
  }

  public void setDocNumberIdx(java.lang.String DocNumberIdx) {
    this.DocNumberIdx = DocNumberIdx;
  }

  /**
   *
   */
  @DataItemName("Account.BankOut.DocDateIdx")
  public java.lang.String getDocDateIdx() {
    return this.DocDateIdx;
  }

  public void setDocDateIdx(java.lang.String DocDateIdx) {
    this.DocDateIdx = DocDateIdx;
  }

  /**
   *
   */
  @DataItemName("Account.BankDocument.PaymentTypeIdx")
  public java.lang.String getPaymentTypeIdx() {
    return this.PaymentTypeIdx;
  }

  public void setPaymentTypeIdx(java.lang.String PaymentTypeIdx) {
    this.PaymentTypeIdx = PaymentTypeIdx;
  }

  /**
   *
   */
  @DataItemName("Account.BankDocument.PayerStatus")
  public java.lang.String getPayerStatus() {
    return this.PayerStatus;
  }

  public void setPayerStatus(java.lang.String PayerStatus) {
    this.PayerStatus = PayerStatus;
  }

}