/*
 * Liability.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.paymentcontrol.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Обязательство"
 *
 * @author Artem V. Sharapov
 * @version $Id: Liability.java,v 1.9 2007/05/14 05:12:10 sharapov Exp $
 */
@DataItemName("PaymentControl.Liability") //$NON-NLS-1$
public class Liability extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private ContractorSource FromSource;
  private ContractorSource ToSource;

  private com.mg.merp.reference.model.Contractor From;
  private com.mg.merp.reference.model.Contractor To;

  private com.mg.merp.reference.model.BankAccount FromBankAcc;
  private com.mg.merp.reference.model.BankAccount ToBankAcc;

  private com.mg.merp.reference.model.Currency CurCode;
  private com.mg.merp.reference.model.CurrencyRateAuthority CurRateAuthority;
  private com.mg.merp.reference.model.CurrencyRateType CurRateType;

  private java.math.BigDecimal SumCur;

  private com.mg.merp.document.model.DocHead DocHead;
  private com.mg.merp.document.model.DocType DocType;
  private java.lang.String DocNumber;
  private java.util.Date DocDate;

  private com.mg.merp.document.model.DocHead BaseDoc;
  private com.mg.merp.document.model.DocType BaseDocType;
  private java.lang.String BaseDocNumber;
  private java.util.Date BaseDocDate;

  private com.mg.merp.document.model.DocHead Contract;
  private com.mg.merp.document.model.DocType ContractType;
  private java.lang.String ContractNumber;
  private java.util.Date ContractDate;

  private com.mg.merp.core.model.Folder PrefResourceFolder;
  private com.mg.merp.paymentcontrol.model.PmcResource PrefResource;
  private boolean PrefOnly;

  private com.mg.merp.core.model.Folder Folder;
  private com.mg.merp.core.model.Folder DestFolder;

  private com.mg.merp.paymentcontrol.model.Version Version;
  private com.mg.merp.paymentcontrol.model.VersionStatus VerStatus;

  private java.util.Date RegDate;
  private java.util.Date DateToExecute;

  private java.lang.Integer Priority;
  private java.lang.Integer PaymentDelay;
  private java.lang.String Num;

  private java.lang.String ModelName;
  private boolean IsModel;

  private boolean IsShared;
  private boolean Receivable;

  private java.lang.String Comments;
  private java.lang.Short TransferKind;

  private java.util.Set<Execution> Executions;
  private com.mg.merp.core.model.SysClient SysClient;


  // Constructors

  /**
   * default constructor
   */
  public Liability() {
  }

  /**
   * constructor with id
   */
  public Liability(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID") //$NON-NLS-1$
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.PartnerFrom") //$NON-NLS-1$
  public com.mg.merp.reference.model.Contractor getFrom() {
    return this.From;
  }

  public void setFrom(com.mg.merp.reference.model.Contractor Contractor) {
    this.From = Contractor;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.FromBankAcc") //$NON-NLS-1$
  public com.mg.merp.reference.model.BankAccount getFromBankAcc() {
    return this.FromBankAcc;
  }

  public void setFromBankAcc(
      com.mg.merp.reference.model.BankAccount RefBankAccount) {
    this.FromBankAcc = RefBankAccount;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.CurrencyRateAuthority getCurRateAuthority() {
    return this.CurRateAuthority;
  }

  public void setCurRateAuthority(
      com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
    this.CurRateAuthority = RefCurrencyRateAuthority;
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
  @DataItemName("PaymentControl.Liab.PrefResFolder") //$NON-NLS-1$
  public com.mg.merp.core.model.Folder getPrefResourceFolder() {
    return this.PrefResourceFolder;
  }

  public void setPrefResourceFolder(com.mg.merp.core.model.Folder Folder) {
    this.PrefResourceFolder = Folder;
  }

  /**
   *
   */
  public com.mg.merp.core.model.Folder getFolder() {
    return this.Folder;
  }

  public void setFolder(com.mg.merp.core.model.Folder Folder_1) {
    this.Folder = Folder_1;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.DocType") //$NON-NLS-1$
  public com.mg.merp.document.model.DocType getDocType() {
    return this.DocType;
  }

  public void setDocType(com.mg.merp.document.model.DocType Typedoc) {
    this.DocType = Typedoc;
  }

  /**
   *
   */
  public com.mg.merp.document.model.DocHead getContract() {
    return this.Contract;
  }

  public void setContract(com.mg.merp.document.model.DocHead Dochead_1) {
    this.Contract = Dochead_1;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.DestFolder") //$NON-NLS-1$
  public com.mg.merp.core.model.Folder getDestFolder() {
    return this.DestFolder;
  }

  public void setDestFolder(com.mg.merp.core.model.Folder Folder_2) {
    this.DestFolder = Folder_2;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.DocTypeContract") //$NON-NLS-1$
  public com.mg.merp.document.model.DocType getContractType() {
    return this.ContractType;
  }

  public void setContractType(com.mg.merp.document.model.DocType Typedoc_1) {
    this.ContractType = Typedoc_1;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.PartnerTo") //$NON-NLS-1$
  public com.mg.merp.reference.model.Contractor getTo() {
    return this.To;
  }

  public void setTo(com.mg.merp.reference.model.Contractor Contractor_1) {
    this.To = Contractor_1;
  }

  /**
   *
   */
  public com.mg.merp.document.model.DocHead getBaseDoc() {
    return this.BaseDoc;
  }

  public void setBaseDoc(com.mg.merp.document.model.DocHead Dochead_2) {
    this.BaseDoc = Dochead_2;
  }

  /**
   *
   */
  public com.mg.merp.paymentcontrol.model.VersionStatus getVerStatus() {
    return this.VerStatus;
  }

  public void setVerStatus(
      com.mg.merp.paymentcontrol.model.VersionStatus PmcVerstatus) {
    this.VerStatus = PmcVerstatus;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.ToBankAcc") //$NON-NLS-1$
  public com.mg.merp.reference.model.BankAccount getToBankAcc() {
    return this.ToBankAcc;
  }

  public void setToBankAcc(
      com.mg.merp.reference.model.BankAccount RefBankAccount_1) {
    this.ToBankAcc = RefBankAccount_1;
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
  @DataItemName("PaymentControl.DocTypeBase") //$NON-NLS-1$
  public com.mg.merp.document.model.DocType getBaseDocType() {
    return this.BaseDocType;
  }

  public void setBaseDocType(com.mg.merp.document.model.DocType Typedoc_2) {
    this.BaseDocType = Typedoc_2;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.CurrencyRateType getCurRateType() {
    return this.CurRateType;
  }

  public void setCurRateType(
      com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
    this.CurRateType = RefCurrencyRateType;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.PrefResource") //$NON-NLS-1$
  public com.mg.merp.paymentcontrol.model.PmcResource getPrefResource() {
    return this.PrefResource;
  }

  public void setPrefResource(
      com.mg.merp.paymentcontrol.model.PmcResource PmcResource) {
    this.PrefResource = PmcResource;
  }

  /**
   *
   */
  public com.mg.merp.paymentcontrol.model.Version getVersion() {
    return this.Version;
  }

  public void setVersion(com.mg.merp.paymentcontrol.model.Version PmcVersion) {
    this.Version = PmcVersion;
  }

  /**
   *
   */
  public boolean getIsShared() {
    return this.IsShared;
  }

  public void setIsShared(boolean IsShared) {
    this.IsShared = IsShared;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.Priority") //$NON-NLS-1$
  public java.lang.Integer getPriority() {
    return this.Priority;
  }

  public void setPriority(java.lang.Integer Priority) {
    this.Priority = Priority;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.Receivable") //$NON-NLS-1$
  public boolean getReceivable() {
    return this.Receivable;
  }

  public void setReceivable(boolean Receivable) {
    this.Receivable = Receivable;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.Num") //$NON-NLS-1$
  public java.lang.String getNum() {
    return this.Num;
  }

  public void setNum(java.lang.String Num) {
    this.Num = Num;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.RegDate") //$NON-NLS-1$
  public java.util.Date getRegDate() {
    return this.RegDate;
  }

  public void setRegDate(java.util.Date Regdate) {
    this.RegDate = Regdate;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.DateToExecute") //$NON-NLS-1$
  public java.util.Date getDateToExecute() {
    return this.DateToExecute;
  }

  public void setDateToExecute(java.util.Date Datetoexecute) {
    this.DateToExecute = Datetoexecute;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.SumCur") //$NON-NLS-1$
  public java.math.BigDecimal getSumCur() {
    return this.SumCur;
  }

  public void setSumCur(java.math.BigDecimal Sumcur) {
    this.SumCur = Sumcur;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.Currency getCurCode() {
    return this.CurCode;
  }

  public void setCurCode(com.mg.merp.reference.model.Currency Curcode) {
    this.CurCode = Curcode;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.PaymentDelay") //$NON-NLS-1$
  public java.lang.Integer getPaymentDelay() {
    return this.PaymentDelay;
  }

  public void setPaymentDelay(java.lang.Integer Paymentdelay) {
    this.PaymentDelay = Paymentdelay;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.DocNumber") //$NON-NLS-1$
  public java.lang.String getDocNumber() {
    return this.DocNumber;
  }

  public void setDocNumber(java.lang.String Docnumber) {
    this.DocNumber = Docnumber;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.DocDate") //$NON-NLS-1$
  public java.util.Date getDocDate() {
    return this.DocDate;
  }

  public void setDocDate(java.util.Date Docdate) {
    this.DocDate = Docdate;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.DocNumberBase") //$NON-NLS-1$
  public java.lang.String getBaseDocNumber() {
    return this.BaseDocNumber;
  }

  public void setBaseDocNumber(java.lang.String Basedocnumber) {
    this.BaseDocNumber = Basedocnumber;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.DocDateBase") //$NON-NLS-1$
  public java.util.Date getBaseDocDate() {
    return this.BaseDocDate;
  }

  public void setBaseDocDate(java.util.Date Basedocdate) {
    this.BaseDocDate = Basedocdate;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.DocNumberContract") //$NON-NLS-1$
  public java.lang.String getContractNumber() {
    return this.ContractNumber;
  }

  public void setContractNumber(java.lang.String Contractnumber) {
    this.ContractNumber = Contractnumber;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.DocDateContract") //$NON-NLS-1$
  public java.util.Date getContractDate() {
    return this.ContractDate;
  }

  public void setContractDate(java.util.Date Contractdate) {
    this.ContractDate = Contractdate;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.PrefOnly") //$NON-NLS-1$
  public boolean getPrefOnly() {
    return this.PrefOnly;
  }

  public void setPrefOnly(boolean Prefonly) {
    this.PrefOnly = Prefonly;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.Comments") //$NON-NLS-1$
  public java.lang.String getComments() {
    return this.Comments;
  }

  public void setComments(java.lang.String Comments) {
    this.Comments = Comments;
  }

  /**
   *
   */
  public java.lang.Short getTransferKind() {
    return this.TransferKind;
  }

  public void setTransferKind(java.lang.Short Transferkind) {
    this.TransferKind = Transferkind;
  }

  /**
   *
   */
  public boolean getIsModel() {
    return this.IsModel;
  }

  public void setIsModel(boolean IsModel) {
    this.IsModel = IsModel;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.ModelName") //$NON-NLS-1$
  public java.lang.String getModelName() {
    return this.ModelName;
  }

  public void setModelName(java.lang.String Modelname) {
    this.ModelName = Modelname;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.FromSource") //$NON-NLS-1$
  public ContractorSource getFromSource() {
    return this.FromSource;
  }

  public void setFromSource(ContractorSource Fromsource) {
    this.FromSource = Fromsource;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Liab.ToSource") //$NON-NLS-1$
  public ContractorSource getToSource() {
    return this.ToSource;
  }

  public void setToSource(ContractorSource Tosource) {
    this.ToSource = Tosource;
  }

  /**
   * @return the executions
   */
  public java.util.Set<Execution> getExecutions() {
    return Executions;
  }

  /**
   * @param executions the executions to set
   */
  public void setExecutions(java.util.Set<Execution> executions) {
    Executions = executions;
  }

}