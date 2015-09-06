/*
 * ContractSpec.java
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
package com.mg.merp.contract.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: ContractSpec.java,v 1.4 2006/06/07 11:49:24 leonova Exp $
 */
public class ContractSpec extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.reference.model.PriceType PriceType;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.contract.model.PhaseFactItem PhaseItemFact;

  private com.mg.merp.contract.model.PhasePlanItem PhaseItemPlan;

  private com.mg.merp.reference.model.PriceListHead PriceListHead;

  private java.math.BigDecimal Quantity;

  private java.math.BigDecimal Price;

  private java.math.BigDecimal Summa;

  // Constructors

  /**
   * default constructor
   */
  public ContractSpec() {
  }

  /**
   * constructor with id
   */
  public ContractSpec(java.lang.Integer Id) {
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
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.PriceType getPriceType() {
    return this.PriceType;
  }

  public void setPriceType(com.mg.merp.reference.model.PriceType Pricetype) {
    this.PriceType = Pricetype;
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

  public com.mg.merp.contract.model.PhaseFactItem getPhaseItemFact() {
    return this.PhaseItemFact;
  }

  public void setPhaseItemFact(
      com.mg.merp.contract.model.PhaseFactItem Phaseitemfact) {
    this.PhaseItemFact = Phaseitemfact;
  }

  /**
   *
   */

  public com.mg.merp.contract.model.PhasePlanItem getPhaseItemPlan() {
    return this.PhaseItemPlan;
  }

  public void setPhaseItemPlan(
      com.mg.merp.contract.model.PhasePlanItem Phaseitemplan) {
    this.PhaseItemPlan = Phaseitemplan;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.PriceListHead getPriceListHead() {
    return this.PriceListHead;
  }

  public void setPriceListHead(
      com.mg.merp.reference.model.PriceListHead Pricelisthead) {
    this.PriceListHead = Pricelisthead;
  }

  /**
   *
   */
  @DataItemName("Contract.Spec.Quantity")
  public java.math.BigDecimal getQuantity() {
    return this.Quantity;
  }

  public void setQuantity(java.math.BigDecimal Quantity) {
    this.Quantity = Quantity;
  }

  /**
   *
   */
  @DataItemName("Contract.Spec.Price")
  public java.math.BigDecimal getPrice() {
    return this.Price;
  }

  public void setPrice(java.math.BigDecimal Price) {
    this.Price = Price;
  }

  /**
   *
   */
  @DataItemName("Contract.Spec.Summa")
  public java.math.BigDecimal getSumma() {
    return this.Summa;
  }

  public void setSumma(java.math.BigDecimal Summa) {
    this.Summa = Summa;
  }

}