/*
 * RemnVal.java
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
 * @version $Id: RemnVal.java,v 1.6 2006/10/18 06:08:30 leonova Exp $
 */
public class RemnVal extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private int Id;
  private com.mg.merp.account.model.AccPlan AccPlan;
  private com.mg.merp.reference.model.Catalog Catalog;
  private com.mg.merp.account.model.AnlPlan AnlPlan4;
  private com.mg.merp.account.model.AnlPlan AnlPlan1;
  private com.mg.merp.core.model.SysClient SysClient;
  private com.mg.merp.account.model.AnlPlan AnlPlan2;
  private com.mg.merp.reference.model.Contractor Contractor;
  private com.mg.merp.account.model.AnlPlan AnlPlan3;
  private com.mg.merp.account.model.AnlPlan AnlPlan5;
  private com.mg.merp.account.model.Period Period;
  private java.math.BigDecimal BeginQuan;
  private java.math.BigDecimal RemnBeginNat;
  private java.math.BigDecimal RemnBeginCur;
  private java.lang.Integer BatchId;
  private java.math.BigDecimal quantityDb;
  private java.math.BigDecimal endQuan;
  private java.math.BigDecimal quantityKt;
  private java.math.BigDecimal remnEndNat;
  private java.math.BigDecimal remnEndCur;
  private java.math.BigDecimal turnNatKt;
  private java.math.BigDecimal turnCurDb;
  private java.math.BigDecimal turnCurKt;
  private java.math.BigDecimal turnNatDb;


  // Constructors

  /**
   * default constructor
   */
  public RemnVal() {
  }

  /**
   * constructor with id
   */
  public RemnVal(int Id) {
    this.Id = Id;
  }


  // Property accessors

  /**

   */
  @DataItemName("ID")
  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  /**

   */

  public com.mg.merp.account.model.AccPlan getAccPlan() {
    return this.AccPlan;
  }

  public void setAccPlan(com.mg.merp.account.model.AccPlan Accplan) {
    this.AccPlan = Accplan;
  }

  /**

   */

  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**

   */
  @DataItemName("Account.RemnAnl.AnlPlan4")
  public com.mg.merp.account.model.AnlPlan getAnlPlan4() {
    return this.AnlPlan4;
  }

  public void setAnlPlan4(com.mg.merp.account.model.AnlPlan Anlplan) {
    this.AnlPlan4 = Anlplan;
  }

  /**

   */
  @DataItemName("Account.RemnAnl.AnlPlan1")
  public com.mg.merp.account.model.AnlPlan getAnlPlan1() {
    return this.AnlPlan1;
  }

  public void setAnlPlan1(com.mg.merp.account.model.AnlPlan Anlplan_1) {
    this.AnlPlan1 = Anlplan_1;
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
  @DataItemName("Account.RemnAnl.AnlPlan2")
  public com.mg.merp.account.model.AnlPlan getAnlPlan2() {
    return this.AnlPlan2;
  }

  public void setAnlPlan2(com.mg.merp.account.model.AnlPlan Anlplan_2) {
    this.AnlPlan2 = Anlplan_2;
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
  @DataItemName("Account.RemnAnl.AnlPlan3")
  public com.mg.merp.account.model.AnlPlan getAnlPlan3() {
    return this.AnlPlan3;
  }

  public void setAnlPlan3(com.mg.merp.account.model.AnlPlan Anlplan_3) {
    this.AnlPlan3 = Anlplan_3;
  }

  /**

   */
  @DataItemName("Account.RemnAnl.AnlPlan5")
  public com.mg.merp.account.model.AnlPlan getAnlPlan5() {
    return this.AnlPlan5;
  }

  public void setAnlPlan5(com.mg.merp.account.model.AnlPlan Anlplan_4) {
    this.AnlPlan5 = Anlplan_4;
  }

  /**

   */

  public com.mg.merp.account.model.Period getPeriod() {
    return this.Period;
  }

  public void setPeriod(com.mg.merp.account.model.Period Period) {
    this.Period = Period;
  }

  /**

   */
  @DataItemName("Account.RemnVal.BeginQuan")
  public java.math.BigDecimal getBeginQuan() {
    return this.BeginQuan;
  }

  public void setBeginQuan(java.math.BigDecimal Beginquan) {
    this.BeginQuan = Beginquan;
  }

  /**

   */
  @DataItemName("Account.RemnVal.RemnBeginNat")
  public java.math.BigDecimal getRemnBeginNat() {
    return this.RemnBeginNat;
  }

  public void setRemnBeginNat(java.math.BigDecimal Remnbeginnat) {
    this.RemnBeginNat = Remnbeginnat;
  }

  /**

   */
  @DataItemName("Account.RemnVal.RemnBeginCur")
  public java.math.BigDecimal getRemnBeginCur() {
    return this.RemnBeginCur;
  }

  public void setRemnBeginCur(java.math.BigDecimal Remnbegincur) {
    this.RemnBeginCur = Remnbegincur;
  }

  /**

   */

  public java.lang.Integer getBatchId() {
    return this.BatchId;
  }

  public void setBatchId(java.lang.Integer BatchId) {
    this.BatchId = BatchId;
  }

  @DataItemName("Account.RemnVal.EndQuan")
  public java.math.BigDecimal getEndQuan() {
    return endQuan;
  }

  public void setEndQuan(java.math.BigDecimal endQuan) {
    this.endQuan = endQuan;
  }

  @DataItemName("Account.RemnVal.QuantityDb")
  public java.math.BigDecimal getQuantityDb() {
    return quantityDb;
  }

  public void setQuantityDb(java.math.BigDecimal quantityDb) {
    this.quantityDb = quantityDb;
  }

  @DataItemName("Account.RemnVal.QuantityKt")
  public java.math.BigDecimal getQuantityKt() {
    return quantityKt;
  }

  public void setQuantityKt(java.math.BigDecimal quantityKt) {
    this.quantityKt = quantityKt;
  }

  @DataItemName("Account.RemnVal.RemnEndCur")
  public java.math.BigDecimal getRemnEndCur() {
    return remnEndCur;
  }

  public void setRemnEndCur(java.math.BigDecimal remnEndCur) {
    this.remnEndCur = remnEndCur;
  }

  @DataItemName("Account.RemnVal.RemnEndNat")
  public java.math.BigDecimal getRemnEndNat() {
    return remnEndNat;
  }

  public void setRemnEndNat(java.math.BigDecimal remnEndNat) {
    this.remnEndNat = remnEndNat;
  }

  @DataItemName("Account.RemnVal.TurnCurDb")
  public java.math.BigDecimal getTurnCurDb() {
    return turnCurDb;
  }

  public void setTurnCurDb(java.math.BigDecimal turnCurDb) {
    this.turnCurDb = turnCurDb;
  }

  @DataItemName("Account.RemnVal.TurnCurKt")
  public java.math.BigDecimal getTurnCurKt() {
    return turnCurKt;
  }

  public void setTurnCurKt(java.math.BigDecimal turnCurKt) {
    this.turnCurKt = turnCurKt;
  }

  @DataItemName("Account.RemnVal.TurnNatDb")
  public java.math.BigDecimal getTurnNatDb() {
    return turnNatDb;
  }

  public void setTurnNatDb(java.math.BigDecimal turnNatDb) {
    this.turnNatDb = turnNatDb;
  }

  @DataItemName("Account.RemnVal.TurnNatKt")
  public java.math.BigDecimal getTurnNatKt() {
    return turnNatKt;
  }

  public void setTurnNatKt(java.math.BigDecimal turnNatKt) {
    this.turnNatKt = turnNatKt;
  }
}