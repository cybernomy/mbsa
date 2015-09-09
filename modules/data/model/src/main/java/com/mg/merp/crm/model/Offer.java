/*
 * Offer.java
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
package com.mg.merp.crm.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Offer.java,v 1.5 2008/03/18 12:22:26 alikaev Exp $
 */
public class Offer extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.crm.model.OfferStatus Status;

  private com.mg.merp.crm.model.OfferReason FailReason;

  private com.mg.merp.crm.model.OfferReason SuccessReason;

  private com.mg.merp.crm.model.User Responsible;

  private com.mg.merp.crm.model.Operation Operation;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.crm.model.OfferForecast Forecast;

  private com.mg.merp.crm.model.Contact Contact;

  private com.mg.merp.crm.model.Relation Relation;

  private com.mg.merp.crm.model.OfferKind Kind;

  private java.lang.String Code;

  private java.lang.String Name;

  private java.util.Date OfferDate;

  private java.util.Date ValidUntil;

  private java.lang.String SwotS;

  private java.lang.String SwotW;

  private java.lang.String SwotO;

  private java.lang.String SwotT;

  private java.util.Set<LinkedDocument> linkedDocs;

  // Constructors

  /**
   * default constructor
   */
  public Offer() {
  }

  /**
   * constructor with id
   */
  public Offer(int Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID")
  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  /**
   *
   */
  public com.mg.merp.crm.model.OfferStatus getStatus() {
    return this.Status;
  }

  public void setStatus(com.mg.merp.crm.model.OfferStatus CrmOfferStatus) {
    this.Status = CrmOfferStatus;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.FailReason")
  public com.mg.merp.crm.model.OfferReason getFailReason() {
    return this.FailReason;
  }

  public void setFailReason(com.mg.merp.crm.model.OfferReason CrmOfferReason) {
    this.FailReason = CrmOfferReason;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.SuccessReason")
  public com.mg.merp.crm.model.OfferReason getSuccessReason() {
    return this.SuccessReason;
  }

  public void setSuccessReason(
      com.mg.merp.crm.model.OfferReason CrmOfferReason_1) {
    this.SuccessReason = CrmOfferReason_1;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.Responsible")
  public com.mg.merp.crm.model.User getResponsible() {
    return this.Responsible;
  }

  public void setResponsible(com.mg.merp.crm.model.User CrmUser) {
    this.Responsible = CrmUser;
  }

  /**
   *
   */

  public com.mg.merp.crm.model.Operation getOperation() {
    return this.Operation;
  }

  public void setOperation(com.mg.merp.crm.model.Operation CrmOperation) {
    this.Operation = CrmOperation;
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
  public com.mg.merp.crm.model.OfferForecast getForecast() {
    return this.Forecast;
  }

  public void setForecast(com.mg.merp.crm.model.OfferForecast CrmOfferForecast) {
    this.Forecast = CrmOfferForecast;
  }

  /**
   *
   */

  public com.mg.merp.crm.model.Contact getContact() {
    return this.Contact;
  }

  public void setContact(com.mg.merp.crm.model.Contact CrmContact) {
    this.Contact = CrmContact;
  }

  /**
   *
   */

  public com.mg.merp.crm.model.Relation getRelation() {
    return this.Relation;
  }

  public void setRelation(com.mg.merp.crm.model.Relation CrmRelation) {
    this.Relation = CrmRelation;
  }

  /**
   *
   */
  public com.mg.merp.crm.model.OfferKind getKind() {
    return this.Kind;
  }

  public void setKind(com.mg.merp.crm.model.OfferKind CrmOfferKind) {
    this.Kind = CrmOfferKind;
  }

  /**
   *
   */
  @DataItemName("CRM.Code")
  public java.lang.String getCode() {
    return this.Code;
  }

  public void setCode(java.lang.String Code) {
    this.Code = Code;
  }

  /**
   *
   */
  @DataItemName("CRM.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.OfferDate")
  public java.util.Date getOfferDate() {
    return this.OfferDate;
  }

  public void setOfferDate(java.util.Date OfferDate) {
    this.OfferDate = OfferDate;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.ValidUntil")
  public java.util.Date getValidUntil() {
    return this.ValidUntil;
  }

  public void setValidUntil(java.util.Date ValidUntil) {
    this.ValidUntil = ValidUntil;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.SwotS")
  public java.lang.String getSwotS() {
    return this.SwotS;
  }

  public void setSwotS(java.lang.String SwotS) {
    this.SwotS = SwotS;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.SwotW")
  public java.lang.String getSwotW() {
    return this.SwotW;
  }

  public void setSwotW(java.lang.String SwotW) {
    this.SwotW = SwotW;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.SwotO")
  public java.lang.String getSwotO() {
    return this.SwotO;
  }

  public void setSwotO(java.lang.String SwotO) {
    this.SwotO = SwotO;
  }

  /**
   *
   */
  @DataItemName("CRM.Offer.SwotT")
  public java.lang.String getSwotT() {
    return this.SwotT;
  }

  public void setSwotT(java.lang.String SwotT) {
    this.SwotT = SwotT;
  }

  /**
   * @return linkedDocs
   */
  public java.util.Set<LinkedDocument> getLinkedDocs() {
    return linkedDocs;
  }

  /**
   * @param linkedDocs задаваемое linkedDocs
   */
  public void setLinkedDocs(java.util.Set<LinkedDocument> linkedDocs) {
    this.linkedDocs = linkedDocs;
  }

}