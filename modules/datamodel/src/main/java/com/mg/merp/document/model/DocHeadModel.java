/*
 * DocHeadModel.java
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
package com.mg.merp.document.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: DocHeadModel.java,v 1.12 2007/09/20 14:56:05 safonov Exp $
 */
@DataItemName("Document.DocHeadModel")
public class DocHeadModel extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysCompany sysCompany;

  private com.mg.merp.reference.model.Contractor Through;

  private com.mg.merp.core.model.Folder ModelDestFolder;

  private com.mg.merp.reference.model.Contractor From;

  private com.mg.merp.reference.model.Contractor To;

  private com.mg.merp.reference.model.Contractor DstMol;

  private com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority;

  private com.mg.merp.reference.model.Contractor SrcMol;

  private com.mg.merp.core.model.Folder Folder;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.Contractor DstStock;

  private com.mg.merp.reference.model.CurrencyRateType CurrencyRateType;

  private com.mg.merp.reference.model.Contractor SrcStock;

  private com.mg.merp.document.model.DocSection docSection;

  private java.lang.String ModelName;

  private com.mg.merp.document.model.DocType DocType;

  private java.lang.String DocNumber;

  private java.util.Date DocDate;

  private com.mg.merp.reference.model.Currency Currency;

  private java.math.BigDecimal CurCource;

  private java.math.BigDecimal SumCur;

  private java.math.BigDecimal SumNat;

  private com.mg.merp.document.model.DocHead BaseDocument;

  private com.mg.merp.document.model.DocType BaseDocType;

  private java.lang.String BaseDocNumber;

  private java.util.Date BaseDocDate;

  private com.mg.merp.document.model.DocHead Contract;

  private com.mg.merp.document.model.DocType ContractType;

  private java.lang.String ContractNumber;

  private java.util.Date ContractDate;

  private java.math.BigDecimal Weight;

  private java.math.BigDecimal Volume;

  private com.mg.merp.reference.model.PriceListHead PriceList;

  private com.mg.merp.reference.model.PriceType PriceType;

  private com.mg.merp.reference.model.CalcTaxesKind CalcTaxesKind;

  private com.mg.merp.core.model.Folder DiscountFolder;

  private boolean isManualDocNumber;

  private String description;

  // Constructors

  /**
   * default constructor
   */
  public DocHeadModel() {
  }

  /**
   * constructor with id
   */
  public DocHeadModel(java.lang.Integer Id) {
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
  @DataItemName("Document.Through")
  public com.mg.merp.reference.model.Contractor getThrough() {
    return this.Through;
  }

  public void setThrough(com.mg.merp.reference.model.Contractor Contractor) {
    this.Through = Contractor;
  }

  /**
   *
   */
  @DataItemName("Document.DocModel.ModelDestFolder")
  public com.mg.merp.core.model.Folder getModelDestFolder() {
    return this.ModelDestFolder;
  }

  public void setModelDestFolder(com.mg.merp.core.model.Folder Folder) {
    this.ModelDestFolder = Folder;
  }

  /**
   *
   */
  @DataItemName("Document.From")
  public com.mg.merp.reference.model.Contractor getFrom() {
    return this.From;
  }

  public void setFrom(com.mg.merp.reference.model.Contractor Contractor_1) {
    this.From = Contractor_1;
  }

  /**
   *
   */
  @DataItemName("Document.To")
  public com.mg.merp.reference.model.Contractor getTo() {
    return this.To;
  }

  public void setTo(com.mg.merp.reference.model.Contractor Contractor_2) {
    this.To = Contractor_2;
  }

  /**
   *
   */
  @DataItemName("Document.DstMol")
  public com.mg.merp.reference.model.Contractor getDstMol() {
    return this.DstMol;
  }

  public void setDstMol(com.mg.merp.reference.model.Contractor Contractor_3) {
    this.DstMol = Contractor_3;
  }

  /**
   *
   */
  @DataItemName("Document.CurrencyRateAuthority")
  public com.mg.merp.reference.model.CurrencyRateAuthority getCurrencyRateAuthority() {
    return this.CurrencyRateAuthority;
  }

  public void setCurrencyRateAuthority(
      com.mg.merp.reference.model.CurrencyRateAuthority RefCurrencyRateAuthority) {
    this.CurrencyRateAuthority = RefCurrencyRateAuthority;
  }

  /**
   *
   */
  @DataItemName("Document.SrcMol")
  public com.mg.merp.reference.model.Contractor getSrcMol() {
    return this.SrcMol;
  }

  public void setSrcMol(com.mg.merp.reference.model.Contractor Contractor_4) {
    this.SrcMol = Contractor_4;
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */
  @DataItemName("Document.DstStock")
  public com.mg.merp.reference.model.Contractor getDstStock() {
    return this.DstStock;
  }

  public void setDstStock(com.mg.merp.reference.model.Contractor Contractor_5) {
    this.DstStock = Contractor_5;
  }

  /**
   *
   */
  @DataItemName("Document.CurrencyRateType")
  public com.mg.merp.reference.model.CurrencyRateType getCurrencyRateType() {
    return this.CurrencyRateType;
  }

  public void setCurrencyRateType(
      com.mg.merp.reference.model.CurrencyRateType RefCurrencyRateType) {
    this.CurrencyRateType = RefCurrencyRateType;
  }

  /**
   *
   */
  @DataItemName("Document.StockSrc")
  public com.mg.merp.reference.model.Contractor getSrcStock() {
    return this.SrcStock;
  }

  public void setSrcStock(com.mg.merp.reference.model.Contractor Contractor_6) {
    this.SrcStock = Contractor_6;
  }

  /**
   *
   */
  public com.mg.merp.document.model.DocSection getDocSection() {
    return this.docSection;
  }

  public void setDocSection(com.mg.merp.document.model.DocSection docSection) {
    this.docSection = docSection;
  }

  /**
   *
   */
  @DataItemName("Document.DocModel.Name")
  public java.lang.String getModelName() {
    return this.ModelName;
  }

  public void setModelName(java.lang.String Modelname) {
    this.ModelName = Modelname;
  }

  /**
   *
   */
  @DataItemName("Document.DocHeadModel.DocType")
  public com.mg.merp.document.model.DocType getDocType() {
    return this.DocType;
  }

  public void setDocType(com.mg.merp.document.model.DocType Doctype) {
    this.DocType = Doctype;
  }

  /**
   *
   */
  @DataItemName("Document.DocNumber")
  public java.lang.String getDocNumber() {
    return this.DocNumber;
  }

  public void setDocNumber(java.lang.String Docnumber) {
    this.DocNumber = Docnumber;
  }

  /**
   *
   */
  @DataItemName("Document.DocDate")
  public java.util.Date getDocDate() {
    return this.DocDate;
  }

  public void setDocDate(java.util.Date Docdate) {
    this.DocDate = Docdate;
  }

  /**
   *
   */

  public com.mg.merp.reference.model.Currency getCurrency() {
    return this.Currency;
  }

  public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
    this.Currency = Currency;
  }

  /**
   *
   */
  @DataItemName("Document.CurCource")
  public java.math.BigDecimal getCurCource() {
    return this.CurCource;
  }

  public void setCurCource(java.math.BigDecimal Curcource) {
    this.CurCource = Curcource;
  }

  /**
   *
   */
  @DataItemName("Document.SumCur")
  public java.math.BigDecimal getSumCur() {
    return this.SumCur;
  }

  public void setSumCur(java.math.BigDecimal Summacur) {
    this.SumCur = Summacur;
  }

  /**
   *
   */
  @DataItemName("Document.SumNat")
  public java.math.BigDecimal getSumNat() {
    return this.SumNat;
  }

  public void setSumNat(java.math.BigDecimal Summanat) {
    this.SumNat = Summanat;
  }

  /**
   *
   */

  public com.mg.merp.document.model.DocHead getBaseDocument() {
    return this.BaseDocument;
  }

  public void setBaseDocument(com.mg.merp.document.model.DocHead BaseDoc) {
    this.BaseDocument = BaseDoc;
  }

  /**
   *
   */
  @DataItemName("Document.DocHeadModel.BaseDocType")
  public com.mg.merp.document.model.DocType getBaseDocType() {
    return this.BaseDocType;
  }

  public void setBaseDocType(com.mg.merp.document.model.DocType Basedoctype) {
    this.BaseDocType = Basedoctype;
  }

  /**
   *
   */
  @DataItemName("Document.BaseDocNumber")
  public java.lang.String getBaseDocNumber() {
    return this.BaseDocNumber;
  }

  public void setBaseDocNumber(java.lang.String Basedocnumber) {
    this.BaseDocNumber = Basedocnumber;
  }

  /**
   *
   */
  @DataItemName("Document.BaseDocDate")
  public java.util.Date getBaseDocDate() {
    return this.BaseDocDate;
  }

  public void setBaseDocDate(java.util.Date Basedocdate) {
    this.BaseDocDate = Basedocdate;
  }

  /**
   *
   */

  public com.mg.merp.document.model.DocHead getContract() {
    return this.Contract;
  }

  public void setContract(com.mg.merp.document.model.DocHead Contract) {
    this.Contract = Contract;
  }

  /**
   *
   */
  @DataItemName("Document.DocHeadModel.ContractType")
  public com.mg.merp.document.model.DocType getContractType() {
    return this.ContractType;
  }

  public void setContractType(com.mg.merp.document.model.DocType Contracttype) {
    this.ContractType = Contracttype;
  }

  /**
   *
   */
  @DataItemName("Document.ContractNumber")
  public java.lang.String getContractNumber() {
    return this.ContractNumber;
  }

  public void setContractNumber(java.lang.String Contractnumber) {
    this.ContractNumber = Contractnumber;
  }

  /**
   *
   */
  @DataItemName("Document.ContractDate")
  public java.util.Date getContractDate() {
    return this.ContractDate;
  }

  public void setContractDate(java.util.Date Contractdate) {
    this.ContractDate = Contractdate;
  }

  /**
   *
   */
  @DataItemName("Document.Weight")
  public java.math.BigDecimal getWeight() {
    return this.Weight;
  }

  public void setWeight(java.math.BigDecimal Weight) {
    this.Weight = Weight;
  }

  /**
   *
   */
  @DataItemName("Document.Volume")
  public java.math.BigDecimal getVolume() {
    return this.Volume;
  }

  public void setVolume(java.math.BigDecimal Volume) {
    this.Volume = Volume;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.PriceListHead getPriceList() {
    return this.PriceList;
  }

  public void setPriceList(com.mg.merp.reference.model.PriceListHead PriceList) {
    this.PriceList = PriceList;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.PriceType getPriceType() {
    return this.PriceType;
  }

  public void setPriceType(com.mg.merp.reference.model.PriceType PriceType) {
    this.PriceType = PriceType;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.CalcTaxesKind getCalcTaxesKind() {
    return this.CalcTaxesKind;
  }

  public void setCalcTaxesKind(
      com.mg.merp.reference.model.CalcTaxesKind CalcTaxesKind) {
    this.CalcTaxesKind = CalcTaxesKind;
  }

  /**
   *
   */
  @DataItemName("Document.DiscountFolder")
  public com.mg.merp.core.model.Folder getDiscountFolder() {
    return this.DiscountFolder;
  }

  public void setDiscountFolder(
      com.mg.merp.core.model.Folder DiscountFolder) {
    this.DiscountFolder = DiscountFolder;
  }

  /**
   * признак ручной установки номера документа, если <code>true</code> то номер не будет
   * формироваться автоматически
   *
   * @return the isManualDocNumber
   */
  public boolean isManualDocNumber() {
    return isManualDocNumber;
  }

  /**
   * @param isManualDocNumber the isManualDocNumber to set
   * @see #isManualDocNumber()
   */
  public void setManualDocNumber(boolean isManualDocNumber) {
    this.isManualDocNumber = isManualDocNumber;
  }

  /**
   * @return the sysCompany
   */
  public com.mg.merp.core.model.SysCompany getSysCompany() {
    return sysCompany;
  }

  /**
   * @param sysCompany the sysCompany to set
   */
  public void setSysCompany(com.mg.merp.core.model.SysCompany sysCompany) {
    this.sysCompany = sysCompany;
  }

  /**
   * @return the description
   */
  @DataItemName("Document.Description")
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

}