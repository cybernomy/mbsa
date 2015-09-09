/*
 * CostDetailLine.java
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
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * @author hbm2java
 * @version $Id: CostDetailLine.java,v 1.3 2006/10/25 12:01:34 leonova Exp $
 */
public class CostDetailLine extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.mfreference.model.CostCategories CostCategories;
  private com.mg.merp.reference.model.Currency Currency;
  private com.mg.merp.mfreference.model.CostDetail CostDetail;
  private java.math.BigDecimal Summa;


  // Constructors

  /**
   * default constructor
   */
  public CostDetailLine() {
  }

  /**
   * constructor with id
   */
  public CostDetailLine(java.lang.Integer Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**

   */

  public com.mg.merp.mfreference.model.CostCategories getCostCategories() {
    return this.CostCategories;
  }

  public void setCostCategories(com.mg.merp.mfreference.model.CostCategories CostCategories) {
    this.CostCategories = CostCategories;
  }

  /**

   */

  public com.mg.merp.reference.model.Currency getCurrency() {
    return this.Currency;
  }

  public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
    this.Currency = Currency;
  }

  /**

   */

  public com.mg.merp.mfreference.model.CostDetail getCostDetail() {
    return this.CostDetail;
  }

  public void setCostDetail(com.mg.merp.mfreference.model.CostDetail CostDetail) {
    this.CostDetail = CostDetail;
  }

  /**

   */
  @DataItemName("MfReference.CostDetailLine.Summa")
  public java.math.BigDecimal getSumma() {
    return this.Summa;
  }

  public void setSumma(java.math.BigDecimal Summa) {
    this.Summa = Summa;
  }


}