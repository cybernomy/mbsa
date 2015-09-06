/*
 * AdvanceRepHead.java
 *
 * Copyright (c) 1998 - 2008 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Авансовые отчеты"
 *
 * @author hbm2java
 * @version $Id: AdvanceRepHead.java,v 1.7 2008/03/12 11:17:30 alikaev Exp $
 */
public class AdvanceRepHead extends com.mg.merp.document.model.DocHead implements java.io.Serializable, org.hibernate.bytecode.javassist.FieldHandled {

  // Fields

  private com.mg.merp.account.model.AccPlan Acc;

  private com.mg.merp.reference.model.Contractor AccountAnt;

  private com.mg.merp.reference.model.Contractor Chief;

  private com.mg.merp.reference.model.Contractor Company;

  private com.mg.merp.document.model.DocHead RestDoc;

  private com.mg.merp.reference.model.Contractor ChiefAccountAnt;

  private java.lang.String Purpose;

  private java.math.BigDecimal PrevAdvanceSum;

  private java.lang.String Received1Src;

  private java.util.Date Received1Date;

  private java.math.BigDecimal Received1Sum;

  private java.lang.String Received2Src;

  private java.util.Date Received2Date;

  private java.math.BigDecimal Received2Sum;

  private java.lang.String Received3Src;

  private java.util.Date Received3Date;

  private java.math.BigDecimal Received3Sum;

  private boolean RestDocKind;

  private com.mg.merp.document.model.DocType restDocType;

  private java.lang.String RestDocNumber;

  private java.util.Date RestDocDate;

  private java.math.BigDecimal RestSum;

  private java.lang.Integer AttachedDocs;

  private java.lang.Integer AttachedDocsSheets;

  private java.lang.String Comments;

  private java.lang.String Office;

  private java.lang.String TabNum;

  private java.math.BigDecimal balanceSum;

  private java.math.BigDecimal receivedSum;

  private boolean balanceOrOverRun;

  // Constructors

  /**
   * default constructor
   */
  public AdvanceRepHead() {
  }

  // Property accessors

  @DataItemName("Account.Adv.Acc")
  public com.mg.merp.account.model.AccPlan getAcc() {
    return this.Acc;
  }

  public void setAcc(com.mg.merp.account.model.AccPlan Acc) {
    this.Acc = Acc;
  }

  public com.mg.merp.reference.model.Contractor getAccountAnt() {
    return this.AccountAnt;
  }

  public void setAccountAnt(com.mg.merp.reference.model.Contractor AccountAnt) {
    this.AccountAnt = AccountAnt;
  }

  public java.math.BigDecimal getReceivedSum() {
    return receivedSum;
  }

  public void setReceivedSum(java.math.BigDecimal receivedSum) {
    this.receivedSum = receivedSum;
  }

  public com.mg.merp.reference.model.Contractor getChief() {
    return this.Chief;
  }

  public void setChief(com.mg.merp.reference.model.Contractor Chief) {
    this.Chief = Chief;
  }

  @DataItemName("Account.Adv.Company")
  public com.mg.merp.reference.model.Contractor getCompany() {
    return this.Company;
  }

  public void setCompany(com.mg.merp.reference.model.Contractor Company) {
    this.Company = Company;
  }

  public com.mg.merp.document.model.DocHead getRestDoc() {
    return this.RestDoc;
  }

  public void setRestDoc(com.mg.merp.document.model.DocHead RestDoc) {
    this.RestDoc = RestDoc;
  }

  public com.mg.merp.reference.model.Contractor getChiefAccountAnt() {
    return this.ChiefAccountAnt;
  }

  public void setChiefAccountAnt(
      com.mg.merp.reference.model.Contractor ChiefAccountAnt) {
    this.ChiefAccountAnt = ChiefAccountAnt;
  }

  @DataItemName("Account.Adv.Purpose")
  public java.lang.String getPurpose() {
    return this.Purpose;
  }

  public void setPurpose(java.lang.String Purpose) {
    this.Purpose = Purpose;
  }

  @DataItemName("Account.Adv.PrevAdvanceSum")
  public java.math.BigDecimal getPrevAdvanceSum() {
    return this.PrevAdvanceSum;
  }

  public void setPrevAdvanceSum(java.math.BigDecimal PrevAdvanceSum) {
    this.PrevAdvanceSum = PrevAdvanceSum;
  }

  @DataItemName("Account.Adv.Received1Src")
  public java.lang.String getReceived1Src() {
    return this.Received1Src;
  }

  public void setReceived1Src(java.lang.String Received1Src) {
    this.Received1Src = Received1Src;
  }

  @DataItemName("Account.Adv.Received1Date")
  public java.util.Date getReceived1Date() {
    return this.Received1Date;
  }

  public void setReceived1Date(java.util.Date Received1Date) {
    this.Received1Date = Received1Date;
  }

  @DataItemName("Account.Adv.Received1Sum")
  public java.math.BigDecimal getReceived1Sum() {
    return this.Received1Sum;
  }

  public void setReceived1Sum(java.math.BigDecimal Received1Sum) {
    this.Received1Sum = Received1Sum;
  }

  @DataItemName("Account.Adv.Received2Src")
  public java.lang.String getReceived2Src() {
    return this.Received2Src;
  }

  public void setReceived2Src(java.lang.String Received2Src) {
    this.Received2Src = Received2Src;
  }

  @DataItemName("Account.Adv.Received2Date")
  public java.util.Date getReceived2Date() {
    return this.Received2Date;
  }

  public void setReceived2Date(java.util.Date Received2Date) {
    this.Received2Date = Received2Date;
  }

  @DataItemName("Account.Adv.Received2Sum")
  public java.math.BigDecimal getReceived2Sum() {
    return this.Received2Sum;
  }

  public void setReceived2Sum(java.math.BigDecimal Received2Sum) {
    this.Received2Sum = Received2Sum;
  }

  @DataItemName("Account.Adv.Received3Src")
  public java.lang.String getReceived3Src() {
    return this.Received3Src;
  }

  public void setReceived3Src(java.lang.String Received3Src) {
    this.Received3Src = Received3Src;
  }

  @DataItemName("Account.Adv.Received3Date")
  public java.util.Date getReceived3Date() {
    return this.Received3Date;
  }

  public void setReceived3Date(java.util.Date Received3Date) {
    this.Received3Date = Received3Date;
  }

  @DataItemName("Account.Adv.Received3Sum")
  public java.math.BigDecimal getReceived3Sum() {
    return this.Received3Sum;
  }

  public void setReceived3Sum(java.math.BigDecimal Received3Sum) {
    this.Received3Sum = Received3Sum;
  }

  @DataItemName("Account.Adv.RestDocKind")
  public boolean getRestDocKind() {
    return this.RestDocKind;
  }

  public void setRestDocKind(boolean RestDocKind) {
    this.RestDocKind = RestDocKind;
  }

  @DataItemName("Account.Adv.RestDocNumber")
  public java.lang.String getRestDocNumber() {
    return this.RestDocNumber;
  }

  public void setRestDocNumber(java.lang.String RestDocNumber) {
    this.RestDocNumber = RestDocNumber;
  }

  @DataItemName("Account.Adv.RestDocDate")
  public java.util.Date getRestDocDate() {
    return this.RestDocDate;
  }

  public void setRestDocDate(java.util.Date RestDocDate) {
    this.RestDocDate = RestDocDate;
  }

  @DataItemName("Account.Adv.RestSum")
  public java.math.BigDecimal getRestSum() {
    return this.RestSum;
  }

  public void setRestSum(java.math.BigDecimal RestSum) {
    this.RestSum = RestSum;
  }

  @DataItemName("Account.Adv.AttachedDocs")
  public java.lang.Integer getAttachedDocs() {
    return this.AttachedDocs;
  }

  public void setAttachedDocs(java.lang.Integer AttachedDocs) {
    this.AttachedDocs = AttachedDocs;
  }

  @DataItemName("Account.Adv.AttachedDocsSheets")
  public java.lang.Integer getAttachedDocsSheets() {
    return this.AttachedDocsSheets;
  }

  public void setAttachedDocsSheets(java.lang.Integer AttachedDocsSheets) {
    this.AttachedDocsSheets = AttachedDocsSheets;
  }

  @DataItemName("Account.Adv.Comment")
  public java.lang.String getComments() {
    return this.Comments;
  }

  public void setComments(java.lang.String Comments) {
    this.Comments = Comments;
  }

  public java.lang.String getOffice() {
    return this.Office;
  }

  public void setOffice(java.lang.String Office) {
    this.Office = Office;
  }

  public java.lang.String getTabNum() {
    return this.TabNum;
  }

  public void setTabNum(java.lang.String TabNum) {
    this.TabNum = TabNum;
  }

  public java.math.BigDecimal getBalanceSum() {
    return this.receivedSum != null ? (getSumCur() != null ? this.receivedSum.subtract(getSumCur()) : this.receivedSum) : getSumCur().negate();
  }

  @DataItemName("Account.Adv.Adv.DocType")
  public com.mg.merp.document.model.DocType getRestDocType() {
    return restDocType;
  }

  public void setRestDocType(com.mg.merp.document.model.DocType restDocType) {
    this.restDocType = restDocType;
  }

  public boolean getBalanceOrOverRun() {
    return balanceOrOverRun;
  }

  public void setBalanceOrOverRun(boolean balanceOrOverRun) {
    this.balanceOrOverRun = balanceOrOverRun;
  }

}