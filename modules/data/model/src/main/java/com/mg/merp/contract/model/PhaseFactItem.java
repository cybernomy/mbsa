/*
 * PhaseFactItem.java
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Фактический пункт контракта"
 *
 * @author Artem V. Sharapov
 * @version $Id: PhaseFactItem.java,v 1.7 2007/11/22 15:55:11 sharapov Exp $
 */
public class PhaseFactItem extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.document.model.DocHead DocHead;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.Contractor Contractor;

  private java.util.Date RegDate;

  private com.mg.merp.document.model.DocType docType;

  private java.lang.String docNumber;

  private java.util.Date docDate;

  private com.mg.merp.document.model.DocHead document;

  private java.math.BigDecimal FactSum;

  private java.math.BigDecimal DistSum;

  private ItemKind Kind;


  /**
   * default constructor
   */
  public PhaseFactItem() {
  }

  /**
   * constructor with id
   */
  public PhaseFactItem(java.lang.Integer Id) {
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
  public com.mg.merp.document.model.DocHead getDocHead() {
    return this.DocHead;
  }

  public void setDocHead(com.mg.merp.document.model.DocHead Dochead) {
    this.DocHead = Dochead;
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
  @DataItemName("Contract.ItemFact.Contractor") //$NON-NLS-1$
  public com.mg.merp.reference.model.Contractor getContractor() {
    return this.Contractor;
  }

  public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
    this.Contractor = Contractor;
  }

  /**
   *
   */
  @DataItemName("Contract.ItemFact.RegDate") //$NON-NLS-1$
  public java.util.Date getRegDate() {
    return this.RegDate;
  }

  public void setRegDate(java.util.Date Regdate) {
    this.RegDate = Regdate;
  }

  /**
   *
   */
  @DataItemName("Contract.ItemFact.FactSum") //$NON-NLS-1$
  public java.math.BigDecimal getFactSum() {
    return this.FactSum;
  }

  public void setFactSum(java.math.BigDecimal Factsum) {
    this.FactSum = Factsum;
  }

  /**
   *
   */
  @DataItemName("Contract.ItemFact.Distsum") //$NON-NLS-1$
  public java.math.BigDecimal getDistSum() {
    return this.DistSum;
  }

  public void setDistSum(java.math.BigDecimal Distsum) {
    this.DistSum = Distsum;
  }

  /**
   *
   */
  public ItemKind getKind() {
    return this.Kind;
  }

  public void setKind(ItemKind Kind) {
    this.Kind = Kind;
  }

  /**
   * @return the docType
   */
  @DataItemName("Document.DocType") //$NON-NLS-1$
  public com.mg.merp.document.model.DocType getDocType() {
    return this.docType;
  }

  /**
   * @param docType the docType to set
   */
  public void setDocType(com.mg.merp.document.model.DocType docType) {
    this.docType = docType;
  }

  /**
   * @return the docNumber
   */
  @DataItemName("Document.DocNumber") //$NON-NLS-1$
  public java.lang.String getDocNumber() {
    return this.docNumber;
  }

  /**
   * @param docNumber the docNumber to set
   */
  public void setDocNumber(java.lang.String docNumber) {
    this.docNumber = docNumber;
  }

  /**
   * @return the docDate
   */
  @DataItemName("Document.DocDate") //$NON-NLS-1$
  public java.util.Date getDocDate() {
    return this.docDate;
  }

  /**
   * @param docDate the docDate to set
   */
  public void setDocDate(java.util.Date docDate) {
    this.docDate = docDate;
  }

  /**
   * @return the document
   */
  public com.mg.merp.document.model.DocHead getDocument() {
    return this.document;
  }

  /**
   * @param document the document to set
   */
  public void setDocument(com.mg.merp.document.model.DocHead document) {
    this.document = document;
  }

}