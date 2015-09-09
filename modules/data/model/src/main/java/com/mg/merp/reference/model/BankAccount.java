/*
 * BankAccount.java
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
 * @version $Id: BankAccount.java,v 1.4 2006/05/29 10:20:19 leonova Exp $
 */
@DataItemName("Reference.BankAccount")
public class BankAccount extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.Bank Bank;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.reference.model.Contractor Contractor;

  private com.mg.merp.reference.model.Currency Currency;

  private com.mg.merp.reference.model.BankAccountType BankAccountType;

  private java.lang.String Name;

  private java.lang.String Account;

  private boolean IsDefault;

  private java.lang.String Unid;

  // Constructors

  /**
   * default constructor
   */
  public BankAccount() {
  }

  /**
   * constructor with id
   */
  public BankAccount(java.lang.Integer Id) {
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
  public com.mg.merp.reference.model.Bank getBank() {
    return this.Bank;
  }

  public void setBank(com.mg.merp.reference.model.Bank Bank) {
    this.Bank = Bank;
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

  public com.mg.merp.reference.model.Contractor getContractor() {
    return this.Contractor;
  }

  public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
    this.Contractor = Contractor;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.Currency")
  public com.mg.merp.reference.model.Currency getCurrency() {
    return this.Currency;
  }

  public void setCurrency(com.mg.merp.reference.model.Currency Currency) {
    this.Currency = Currency;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.BankAccountType getBankAccountType() {
    return this.BankAccountType;
  }

  public void setBankAccountType(
      com.mg.merp.reference.model.BankAccountType BankAccountType) {
    this.BankAccountType = BankAccountType;
  }

  /**
   *
   */
  @DataItemName("Reference.Name")
  public java.lang.String getName() {
    return this.Name;
  }

  public void setName(java.lang.String Name) {
    this.Name = Name;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.Account")
  public java.lang.String getAccount() {
    return this.Account;
  }

  public void setAccount(java.lang.String Account) {
    this.Account = Account;
  }

  /**
   *
   */
  @DataItemName("Reference.Partner.IsDefault")
  public boolean getIsDefault() {
    return this.IsDefault;
  }

  public void setIsDefault(boolean IsDefault) {
    this.IsDefault = IsDefault;
  }

  /**
   *
   */

  public java.lang.String getUnid() {
    return this.Unid;
  }

  public void setUnid(java.lang.String Unid) {
    this.Unid = Unid;
  }

}