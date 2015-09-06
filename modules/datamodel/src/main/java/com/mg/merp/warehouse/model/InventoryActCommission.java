/*
 * InventoryActCommission.java
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
package com.mg.merp.warehouse.model;

import com.mg.framework.api.annotations.DataItemName;


/**
 * @author hbm2java
 * @version $Id: InventoryActCommission.java,v 1.3 2006/10/23 05:34:03 leonova Exp $
 */
public class InventoryActCommission extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.reference.model.Contractor Contractor;
  private com.mg.merp.document.model.DocHead InventoryAct;


  // Constructors

  /**
   * default constructor
   */
  public InventoryActCommission() {
  }

  /**
   * constructor with id
   */
  public InventoryActCommission(java.lang.Integer Id) {
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

  public com.mg.merp.reference.model.Contractor getContractor() {
    return this.Contractor;
  }

  public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
    this.Contractor = Contractor;
  }

  /**

   */

  public com.mg.merp.document.model.DocHead getInventoryAct() {
    return this.InventoryAct;
  }

  public void setInventoryAct(com.mg.merp.document.model.DocHead DocHead) {
    this.InventoryAct = DocHead;
  }


}