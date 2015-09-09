/*
 * CostDetail.java
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


/**
 * @author hbm2java
 * @version $Id: CostDetail.java,v 1.2 2005/07/18 13:57:09 safonov Exp $
 */
public class CostDetail extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;
  private com.mg.merp.core.model.SysClient SysClient;


  // Constructors

  /**
   * default constructor
   */
  public CostDetail() {
  }

  /**
   * constructor with id
   */
  public CostDetail(java.lang.Integer Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */

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
}