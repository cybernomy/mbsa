/*
 * TransactSpec.java
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
package com.mg.merp.paymentalloc.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * @author hbm2java
 * @version $Id: TransactSpec.java,v 1.3 2006/09/01 13:09:25 leonova Exp $
 */
public class TransactSpec extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.document.model.DocSpec DocSpec;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.paymentalloc.model.TransactHead TrHead;
  private java.math.BigDecimal TotalQty;
  private java.math.BigDecimal TotalSum;
  private java.math.BigDecimal AllocQty;
  private java.math.BigDecimal AllocSum;
  private java.util.Set SetOfPmaTrtax;


  // Constructors

  /**
   * default constructor
   */
  public TransactSpec() {
  }

  /**
   * constructor with id
   */
  public TransactSpec(java.lang.Integer Id) {
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

  public com.mg.merp.document.model.DocSpec getDocSpec() {
    return this.DocSpec;
  }

  public void setDocSpec(com.mg.merp.document.model.DocSpec Docspec) {
    this.DocSpec = Docspec;
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

  public com.mg.merp.paymentalloc.model.TransactHead getTrHead() {
    return this.TrHead;
  }

  public void setTrHead(com.mg.merp.paymentalloc.model.TransactHead PmaTrhead) {
    this.TrHead = PmaTrhead;
  }

  /**

   */
  @DataItemName("PaymentAlloc.TransactSpec.TotalQty")
  public java.math.BigDecimal getTotalQty() {
    return this.TotalQty;
  }

  public void setTotalQty(java.math.BigDecimal Totalqty) {
    this.TotalQty = Totalqty;
  }

  /**

   */
  @DataItemName("PaymentAlloc.TransactSpec.TotalSum")
  public java.math.BigDecimal getTotalSum() {
    return this.TotalSum;
  }

  public void setTotalSum(java.math.BigDecimal Totalsum) {
    this.TotalSum = Totalsum;
  }

  /**

   */
  @DataItemName("PaymentAlloc.TransactSpec.AllocQty")
  public java.math.BigDecimal getAllocQty() {
    return this.AllocQty;
  }

  public void setAllocQty(java.math.BigDecimal Allocqty) {
    this.AllocQty = Allocqty;
  }

  /**

   */
  @DataItemName("PaymentAlloc.TransactSpec.AllocSum")
  public java.math.BigDecimal getAllocSum() {
    return this.AllocSum;
  }

  public void setAllocSum(java.math.BigDecimal Allocsum) {
    this.AllocSum = Allocsum;
  }

  /**

   */

  public java.util.Set getSetOfPmaTrtax() {
    return this.SetOfPmaTrtax;
  }

  public void setSetOfPmaTrtax(java.util.Set SetOfPmaTrtax) {
    this.SetOfPmaTrtax = SetOfPmaTrtax;
  }


}