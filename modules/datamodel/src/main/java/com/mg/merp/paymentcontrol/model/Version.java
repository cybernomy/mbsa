/*
 * Version.java
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
package com.mg.merp.paymentcontrol.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Version.java,v 1.4 2006/10/04 07:17:10 leonova Exp $
 */
public class Version extends com.mg.framework.service.PersistentObjectHibernate
    implements java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.security.model.SecUser Creator;

  private java.lang.String Name;

  private boolean Available;

  private java.util.Date CreateDate;

  private java.util.Set SetOfPmcLiability;

  private java.util.Set SetOfPmcVerstatus;

  private java.util.Set SetOfPmcExecution;

  // Constructors

  /**
   * default constructor
   */
  public Version() {
  }

  /**
   * constructor with id
   */
  public Version(java.lang.Integer Id) {
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

  public com.mg.merp.core.model.SysClient getSysClient() {
    return this.SysClient;
  }

  public void setSysClient(com.mg.merp.core.model.SysClient SysClient) {
    this.SysClient = SysClient;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Version.Creator")
  public com.mg.merp.security.model.SecUser getCreator() {
    return this.Creator;
  }

  public void setCreator(com.mg.merp.security.model.SecUser SecUsers) {
    this.Creator = SecUsers;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Payment.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Version.Available")
  public boolean getAvailable() {
    return this.Available;
  }

  public void setAvailable(boolean Available) {
    this.Available = Available;
  }

  /**
   *
   */
  @DataItemName("PaymentControl.Version.CreateDate")
  public java.util.Date getCreateDate() {
    return this.CreateDate;
  }

  public void setCreateDate(java.util.Date Createdate) {
    this.CreateDate = Createdate;
  }

  /**
   *
   */

  public java.util.Set getSetOfPmcLiability() {
    return this.SetOfPmcLiability;
  }

  public void setSetOfPmcLiability(java.util.Set SetOfPmcLiability) {
    this.SetOfPmcLiability = SetOfPmcLiability;
  }

  /**
   *
   */

  public java.util.Set getSetOfPmcVerstatus() {
    return this.SetOfPmcVerstatus;
  }

  public void setSetOfPmcVerstatus(java.util.Set SetOfPmcVerstatus) {
    this.SetOfPmcVerstatus = SetOfPmcVerstatus;
  }

  /**
   *
   */

  public java.util.Set getSetOfPmcExecution() {
    return this.SetOfPmcExecution;
  }

  public void setSetOfPmcExecution(java.util.Set SetOfPmcExecution) {
    this.SetOfPmcExecution = SetOfPmcExecution;
  }

}