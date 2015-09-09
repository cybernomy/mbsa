/*
 * Cancellation.java
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
package com.mg.merp.overall.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * Модель бизнес компонента "Погашение"
 *
 * @author Konstantin S. Alikaev
 * @version $Id: Cancellation.java,v 1.3 2008/06/30 04:15:16 alikaev Exp $
 */
public class Cancellation extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields
  private java.lang.Integer Id;
  private com.mg.merp.overall.model.OvrCardHist OvrCardHist;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.util.Date CancellationDate;
  private java.math.BigDecimal CurrentCancellationSumma;
  private java.lang.Integer DocHeadId;
  private com.mg.merp.document.model.DocType DocType;
  private java.lang.String DocNumber;
  private java.util.Date DocDate;
  private java.lang.String CancellationReason;

  // Constructors

  /**
   * default constructor
   */
  public Cancellation() {
  }

  /**
   * constructor with id
   */
  public Cancellation(java.lang.Integer Id) {
    this.Id = Id;
  }

  // Property accessors
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  public com.mg.merp.overall.model.OvrCardHist getOvrCardHist() {
    return this.OvrCardHist;
  }

  public void setOvrCardHist(com.mg.merp.overall.model.OvrCardHist OvrCardHist) {
    this.OvrCardHist = OvrCardHist;
  }

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  @DataItemName("Overall.Cancellation.CancellationDate")
  public java.util.Date getCancellationDate() {
    return this.CancellationDate;
  }

  public void setCancellationDate(java.util.Date CancellationDate) {
    this.CancellationDate = CancellationDate;
  }

  @DataItemName("Overall.Cancellation.CurrentCancellationSumma")
  public java.math.BigDecimal getCurrentCancellationSumma() {
    return this.CurrentCancellationSumma;
  }

  public void setCurrentCancellationSumma(java.math.BigDecimal currentCancellationSumma) {
    this.CurrentCancellationSumma = currentCancellationSumma;
  }

  public java.lang.Integer getDocHeadId() {
    return this.DocHeadId;
  }

  public void setDocHeadId(java.lang.Integer DocheadId) {
    this.DocHeadId = DocheadId;
  }

  public com.mg.merp.document.model.DocType getDocType() {
    return this.DocType;
  }

  public void setDocType(com.mg.merp.document.model.DocType docType) {
    this.DocType = docType;
  }

  @DataItemName("Document.DocNumber")
  public java.lang.String getDocNumber() {
    return this.DocNumber;
  }

  public void setDocNumber(java.lang.String Docnumber) {
    this.DocNumber = Docnumber;
  }

  @DataItemName("Document.DocDate")
  public java.util.Date getDocDate() {
    return this.DocDate;
  }

  public void setDocDate(java.util.Date Docdate) {
    this.DocDate = Docdate;
  }

  @DataItemName("Overall.Cancellation.CancellationReason")
  public java.lang.String getCancellationReason() {
    return this.CancellationReason;
  }

  public void setCancellationReason(java.lang.String CancellationReason) {
    this.CancellationReason = CancellationReason;
  }

}