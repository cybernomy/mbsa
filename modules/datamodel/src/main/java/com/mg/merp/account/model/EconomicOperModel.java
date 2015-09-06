/*
 * EconomicOperModel.java
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
 * @version $Id: EconomicOperModel.java,v 1.13 2008/03/13 06:19:46 alikaev Exp $
 */
public class EconomicOperModel extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.Folder ModelDestFolder;

  private com.mg.merp.reference.model.Contractor From;

  private com.mg.merp.reference.model.Contractor To;

  private com.mg.merp.core.model.Folder Folder;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.account.model.SpecMark SpecMark;

  private java.lang.String ModelName;

  private java.util.Date KeepDate;

  private java.lang.String Comment;

  private com.mg.merp.document.model.DocType BaseDocType;

  private java.lang.String BaseDocNumber;

  private java.util.Date BaseDocDate;

  private com.mg.merp.document.model.DocHead BaseDoc;

  private com.mg.merp.document.model.DocType ConfirmDocType;

  private java.lang.String ConfirmDocNumber;

  private java.util.Date ConfirmDocDate;

  private com.mg.merp.document.model.DocHead ConfirmDoc;

  private java.math.BigDecimal Summa;

  private SourceEconOperContractor SourceFrom;

  private SourceEconOperContractor SourceTo;

  private java.lang.Integer ContractId;

  private com.mg.merp.document.model.DocType ContractType;

  private java.lang.String ContractNumber;

  private java.util.Date ContractDate;

  private java.util.Set<EconomicSpecModel> economicSpecsModel;

  // Constructors

  /**
   * default constructor
   */
  public EconomicOperModel() {
  }

  /**
   * constructor with id
   */
  public EconomicOperModel(Integer Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public Integer getId() {
    return this.Id;
  }

  public void setId(Integer Id) {
    this.Id = Id;
  }

  /**
   *
   */
  @DataItemName("Account.EconModel.ModelDestFolder")
  public com.mg.merp.core.model.Folder getModelDestFolder() {
    return this.ModelDestFolder;
  }

  public void setModelDestFolder(
      com.mg.merp.core.model.Folder ModelDestFolder) {
    this.ModelDestFolder = ModelDestFolder;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.From")
  public com.mg.merp.reference.model.Contractor getFrom() {
    return this.From;
  }

  public void setFrom(com.mg.merp.reference.model.Contractor ContractorFrom) {
    this.From = ContractorFrom;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.To")
  public com.mg.merp.reference.model.Contractor getTo() {
    return this.To;
  }

  public void setTo(com.mg.merp.reference.model.Contractor ContractorTo) {
    this.To = ContractorTo;
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
  @DataItemName("Account.EconOper.SpecMark")
  public com.mg.merp.account.model.SpecMark getSpecMark() {
    return this.SpecMark;
  }

  public void setSpecMark(com.mg.merp.account.model.SpecMark SpecMark) {
    this.SpecMark = SpecMark;
  }

  /**
   *
   */
  @DataItemName("Account.EconModel.ModelName")
  public java.lang.String getModelName() {
    return this.ModelName;
  }

  public void setModelName(java.lang.String ModelName) {
    this.ModelName = ModelName;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.KeepDate")
  public java.util.Date getKeepDate() {
    return this.KeepDate;
  }

  public void setKeepDate(java.util.Date KeepDate) {
    this.KeepDate = KeepDate;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.BaseDocType")
  public com.mg.merp.document.model.DocType getBaseDocType() {
    return this.BaseDocType;
  }

  public void setBaseDocType(com.mg.merp.document.model.DocType DocBaseType) {
    this.BaseDocType = DocBaseType;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.BaseDocNumber")
  public java.lang.String getBaseDocNumber() {
    return this.BaseDocNumber;
  }

  public void setBaseDocNumber(java.lang.String DocBaseNumber) {
    this.BaseDocNumber = DocBaseNumber;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.BaseDocDate")
  public java.util.Date getBaseDocDate() {
    return this.BaseDocDate;
  }

  public void setBaseDocDate(java.util.Date DocBaseDate) {
    this.BaseDocDate = DocBaseDate;
  }

  /**
   *
   */

  public com.mg.merp.document.model.DocHead getBaseDoc() {
    return this.BaseDoc;
  }

  public void setBaseDoc(com.mg.merp.document.model.DocHead DocBaseId) {
    this.BaseDoc = DocBaseId;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.DocType")
  public com.mg.merp.document.model.DocType getConfirmDocType() {
    return this.ConfirmDocType;
  }

  public void setConfirmDocType(com.mg.merp.document.model.DocType DocType) {
    this.ConfirmDocType = DocType;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.DocNumber")
  public java.lang.String getConfirmDocNumber() {
    return this.ConfirmDocNumber;
  }

  public void setConfirmDocNumber(java.lang.String DocNumber) {
    this.ConfirmDocNumber = DocNumber;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.DocDate")
  public java.util.Date getConfirmDocDate() {
    return this.ConfirmDocDate;
  }

  public void setConfirmDocDate(java.util.Date DocDate) {
    this.ConfirmDocDate = DocDate;
  }

  /**
   *
   */

  public com.mg.merp.document.model.DocHead getConfirmDoc() {
    return this.ConfirmDoc;
  }

  public void setConfirmDoc(com.mg.merp.document.model.DocHead DocHead) {
    this.ConfirmDoc = DocHead;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.Summa")
  public java.math.BigDecimal getSumma() {
    return this.Summa;
  }

  public void setSumma(java.math.BigDecimal Summa) {
    this.Summa = Summa;
  }

  /**
   *
   */
  @DataItemName("Account.EconModel.SourceFrom")
  public SourceEconOperContractor getSourceFrom() {
    return this.SourceFrom;
  }

  public void setSourceFrom(SourceEconOperContractor SourceFrom) {
    this.SourceFrom = SourceFrom;
  }

  /**
   *
   */
  @DataItemName("Account.EconModel.SourceTo")
  public SourceEconOperContractor getSourceTo() {
    return this.SourceTo;
  }

  public void setSourceTo(SourceEconOperContractor SourceTo) {
    this.SourceTo = SourceTo;
  }

  /**
   *
   */

  public java.lang.Integer getContractId() {
    return this.ContractId;
  }

  public void setContractId(java.lang.Integer ContractId) {
    this.ContractId = ContractId;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.ContractType")
  public com.mg.merp.document.model.DocType getContractType() {
    return this.ContractType;
  }

  public void setContractType(com.mg.merp.document.model.DocType ContractType) {
    this.ContractType = ContractType;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.ContractNumber")
  public java.lang.String getContractNumber() {
    return this.ContractNumber;
  }

  public void setContractNumber(java.lang.String ContractNumber) {
    this.ContractNumber = ContractNumber;
  }

  /**
   *
   */
  @DataItemName("Account.EconOper.ContractDate")
  public java.util.Date getContractDate() {
    return this.ContractDate;
  }

  public void setContractDate(java.util.Date ContractDate) {
    this.ContractDate = ContractDate;
  }

  public java.util.Set<EconomicSpecModel> getEconomicSpecsModel() {
    return this.economicSpecsModel;
  }

  public void setEconomicSpecsModel(java.util.Set<EconomicSpecModel> economicSpecsModel) {
    this.economicSpecsModel = economicSpecsModel;
  }

}