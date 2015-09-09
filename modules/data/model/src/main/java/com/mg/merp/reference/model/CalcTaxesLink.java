/*
 * CalcTaxesLink.java
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * @author hbm2java
 * @version $Id: CalcTaxesLink.java,v 1.2 2006/10/10 10:38:18 leonova Exp $
 */
public class CalcTaxesLink extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.reference.model.Tax Tax;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.reference.model.CalcTaxesKind CalcTaxesKind;
  private java.lang.Short FeeOrder;
  private boolean Included;
  private com.mg.merp.reference.model.CalcTaxesSubject Subject;


  // Constructors

  /**
   * default constructor
   */
  public CalcTaxesLink() {
  }

  /**
   * constructor with id
   */
  public CalcTaxesLink(java.lang.Integer Id) {
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

  public com.mg.merp.reference.model.Tax getTax() {
    return this.Tax;
  }

  public void setTax(com.mg.merp.reference.model.Tax Tax) {
    this.Tax = Tax;
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

  public com.mg.merp.reference.model.CalcTaxesKind getCalcTaxesKind() {
    return this.CalcTaxesKind;
  }

  public void setCalcTaxesKind(com.mg.merp.reference.model.CalcTaxesKind CalcTaxesKind) {
    this.CalcTaxesKind = CalcTaxesKind;
  }

  /**

   */
  @DataItemName("Reference.TaxLink.FeeOrder")
  public java.lang.Short getFeeOrder() {
    return this.FeeOrder;
  }

  public void setFeeOrder(java.lang.Short FeeOrder) {
    this.FeeOrder = FeeOrder;
  }

  /**

   */
  @DataItemName("Reference.CalcTaxLink.Included")
  public boolean getIncluded() {
    return this.Included;
  }

  public void setIncluded(boolean Included) {
    this.Included = Included;
  }

  /**

   */

  public com.mg.merp.reference.model.CalcTaxesSubject getSubject() {
    return this.Subject;
  }

  public void setSubject(com.mg.merp.reference.model.CalcTaxesSubject Subject) {
    this.Subject = Subject;
  }


}