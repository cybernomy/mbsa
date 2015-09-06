/*
 * PermissibleAccounts.java
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
package com.mg.merp.account.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: PermissibleAccounts.java,v 1.2 2005/06/20 07:42:24 pashistova Exp $
 */
public class PermissibleAccounts extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.account.model.AnlPlan AnlKt2;

  private com.mg.merp.account.model.AccPlan AccKt;

  private com.mg.merp.account.model.AnlPlan AnlDb2;

  private com.mg.merp.account.model.AnlPlan AnlKt3;

  private com.mg.merp.account.model.AnlPlan AnlDb3;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.account.model.AnlPlan AnlDb4;

  private com.mg.merp.account.model.AnlPlan AnlDb1;

  private com.mg.merp.account.model.AnlPlan AnlKt5;

  private com.mg.merp.account.model.AccPlan AccDb;

  private com.mg.merp.account.model.AnlPlan AnlDb5;

  private com.mg.merp.account.model.AnlPlan AnlKt1;

  private com.mg.merp.account.model.AnlPlan AnlKt4;

  // Constructors

  /**
   * default constructor
   */
  public PermissibleAccounts() {
  }

  /**
   * constructor with id
   */
  public PermissibleAccounts(java.lang.Integer Id) {
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
  @DataItemName("Account.Perm.AnlPlanKtLevel2")
  public com.mg.merp.account.model.AnlPlan getAnlKt2() {
    return this.AnlKt2;
  }

  public void setAnlKt2(com.mg.merp.account.model.AnlPlan AnlKt2) {
    this.AnlKt2 = AnlKt2;
  }

  /**
   *
   */

  @DataItemName("Account.EconSpec.AccKt")
  public com.mg.merp.account.model.AccPlan getAccKt() {
    return this.AccKt;
  }

  public void setAccKt(com.mg.merp.account.model.AccPlan Accplan) {
    this.AccKt = Accplan;
  }

  /**
   *
   */
  @DataItemName("Account.Perm.AnlPlanDbLevel2")
  public com.mg.merp.account.model.AnlPlan getAnlDb2() {
    return this.AnlDb2;
  }

  public void setAnlDb2(com.mg.merp.account.model.AnlPlan AnlDb2) {
    this.AnlDb2 = AnlDb2;
  }

  /**
   *
   */
  @DataItemName("Account.Perm.AnlPlanKtLevel3")
  public com.mg.merp.account.model.AnlPlan getAnlKt3() {
    return this.AnlKt3;
  }

  public void setAnlKt3(com.mg.merp.account.model.AnlPlan AnlKt3) {
    this.AnlKt3 = AnlKt3;
  }

  /**
   *
   */
  @DataItemName("Account.Perm.AnlPlanDbLevel3")
  public com.mg.merp.account.model.AnlPlan getAnlDb3() {
    return this.AnlDb3;
  }

  public void setAnlDb3(com.mg.merp.account.model.AnlPlan AnlDb3) {
    this.AnlDb3 = AnlDb3;
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
  @DataItemName("Account.Perm.AnlPlanDbLevel4")
  public com.mg.merp.account.model.AnlPlan getAnlDb4() {
    return this.AnlDb4;
  }

  public void setAnlDb4(com.mg.merp.account.model.AnlPlan AnlDb4) {
    this.AnlDb4 = AnlDb4;
  }

  /**
   *
   */
  @DataItemName("Account.Perm.AnlPlanDbLevel1")
  public com.mg.merp.account.model.AnlPlan getAnlDb1() {
    return this.AnlDb1;
  }

  public void setAnlDb1(com.mg.merp.account.model.AnlPlan AnlDb1) {
    this.AnlDb1 = AnlDb1;
  }

  /**
   *
   */
  @DataItemName("Account.Perm.AnlPlanKtLevel5")
  public com.mg.merp.account.model.AnlPlan getAnlKt5() {
    return this.AnlKt5;
  }

  public void setAnlKt5(com.mg.merp.account.model.AnlPlan AnlKt5) {
    this.AnlKt5 = AnlKt5;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AccDb")
  public com.mg.merp.account.model.AccPlan getAccDb() {
    return this.AccDb;
  }

  public void setAccDb(com.mg.merp.account.model.AccPlan Accplan_1) {
    this.AccDb = Accplan_1;
  }

  /**
   *
   */
  @DataItemName("Account.Perm.AnlPlanDbLevel5")
  public com.mg.merp.account.model.AnlPlan getAnlDb5() {
    return this.AnlDb5;
  }

  public void setAnlDb5(com.mg.merp.account.model.AnlPlan AnlDb5) {
    this.AnlDb5 = AnlDb5;
  }

  /**
   *
   */
  @DataItemName("Account.Perm.AnlPlanKtLevel1")
  public com.mg.merp.account.model.AnlPlan getAnlKt1() {
    return this.AnlKt1;
  }

  public void setAnlKt1(com.mg.merp.account.model.AnlPlan AnlKt1) {
    this.AnlKt1 = AnlKt1;
  }

  /**
   *
   */
  @DataItemName("Account.Perm.AnlPlanKtLevel4")
  public com.mg.merp.account.model.AnlPlan getAnlKt4() {
    return this.AnlKt4;
  }

  public void setAnlKt4(com.mg.merp.account.model.AnlPlan AnlKt4) {
    this.AnlKt4 = AnlKt4;
  }

}