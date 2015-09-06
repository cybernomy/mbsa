/*
 * LinkedDocument.java
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
 * @version $Id: LinkedDocument.java,v 1.3 2006/09/06 05:18:40 leonova Exp $
 */
public class LinkedDocument extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.crm.model.Solution Solution;
  private com.mg.merp.crm.model.Offer Offer;
  private com.mg.merp.crm.model.Problem Problem;
  private com.mg.merp.crm.model.Operation Operation;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.crm.model.Contact Contact;
  private com.mg.merp.reference.model.OriginalDocument Original;
  private com.mg.merp.crm.model.Relation Relation;


  // Constructors

  /**
   * default constructor
   */
  public LinkedDocument() {
  }

  /**
   * constructor with id
   */
  public LinkedDocument(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */
  @DataItemName("ID")
  public java.lang.Integer getId() {
    return this.Id;
  }

  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**

   */

  public com.mg.merp.crm.model.Solution getSolution() {
    return this.Solution;
  }

  public void setSolution(com.mg.merp.crm.model.Solution CrmSolution) {
    this.Solution = CrmSolution;
  }

  /**

   */

  public com.mg.merp.crm.model.Offer getOffer() {
    return this.Offer;
  }

  public void setOffer(com.mg.merp.crm.model.Offer CrmOffer) {
    this.Offer = CrmOffer;
  }

  /**

   */

  public com.mg.merp.crm.model.Problem getProblem() {
    return this.Problem;
  }

  public void setProblem(com.mg.merp.crm.model.Problem CrmProblem) {
    this.Problem = CrmProblem;
  }

  /**

   */

  public com.mg.merp.crm.model.Operation getOperation() {
    return this.Operation;
  }

  public void setOperation(com.mg.merp.crm.model.Operation CrmOperation) {
    this.Operation = CrmOperation;
  }

  /**

   */

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**

   */

  public com.mg.merp.crm.model.Contact getContact() {
    return this.Contact;
  }

  public void setContact(com.mg.merp.crm.model.Contact CrmContact) {
    this.Contact = CrmContact;
  }

  /**

   */

  public com.mg.merp.reference.model.OriginalDocument getOriginal() {
    return this.Original;
  }

  public void setOriginal(com.mg.merp.reference.model.OriginalDocument RefOriginalDocument) {
    this.Original = RefOriginalDocument;
  }

  /**

   */

  public com.mg.merp.crm.model.Relation getRelation() {
    return this.Relation;
  }

  public void setRelation(com.mg.merp.crm.model.Relation CrmRelation) {
    this.Relation = CrmRelation;
  }


}