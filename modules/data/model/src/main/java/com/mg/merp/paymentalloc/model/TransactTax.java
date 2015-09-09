/*
 * TransactTax.java
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
package com.mg.merp.paymentalloc.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Налоги в спецификации связанных документов"
 *
 * @author Artem V. Sharapov
 * @version $Id: TransactTax.java,v 1.5 2007/05/31 14:10:30 sharapov Exp $
 */
public class TransactTax extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.document.model.DocumentSpecTax TaxSumm;
  private com.mg.merp.paymentalloc.model.TransactSpec TrSpec;
  private com.mg.merp.reference.model.Tax Tax;
  private com.mg.merp.core.model.SysClient SysClient;
  private java.math.BigDecimal TotalSum;
  private java.math.BigDecimal AllocSum;


  // Constructors

  /**
   * default constructor
   */
  public TransactTax() {
  }

  /**
   * constructor with id
   */
  public TransactTax(java.lang.Integer Id) {
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

  /**
   *
   * @param Id
   */
  public void setId(java.lang.Integer Id) {
    this.Id = Id;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.document.model.DocumentSpecTax getTaxSumm() {
    return this.TaxSumm;
  }

  public void setTaxSumm(com.mg.merp.document.model.DocumentSpecTax Taxsumm) {
    this.TaxSumm = Taxsumm;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.paymentalloc.model.TransactSpec getTrSpec() {
    return this.TrSpec;
  }

  public void setTrSpec(com.mg.merp.paymentalloc.model.TransactSpec PmaTrspec) {
    this.TrSpec = PmaTrspec;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.reference.model.Tax getTax() {
    return this.Tax;
  }

  public void setTax(com.mg.merp.reference.model.Tax Tax) {
    this.Tax = Tax;
  }

  /**
   *
   * @return
   */
  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   * @return
   */
  @DataItemName("PaymentAlloc.TransactTax.TotalSum") //$NON-NLS-1$
  public java.math.BigDecimal getTotalSum() {
    return this.TotalSum;
  }

  public void setTotalSum(java.math.BigDecimal Totalsum) {
    this.TotalSum = Totalsum;
  }

  /**
   *
   * @return
   */
  @DataItemName("PaymentAlloc.TransactTax.AllocSum") //$NON-NLS-1$
  public java.math.BigDecimal getAllocSum() {
    return this.AllocSum;
  }

  /**
   *
   * @param Allocsum
   */
  public void setAllocSum(java.math.BigDecimal Allocsum) {
    this.AllocSum = Allocsum;
  }

}