/*
 * EconomicSpecModel.java
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
 * @version $Id: EconomicSpecModel.java,v 1.8 2006/10/19 11:07:32 leonova Exp $
 */
public class EconomicSpecModel extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.account.model.EconomicSpecFeature Feature1;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.account.model.EconomicSpecFeature Feature2;

  private com.mg.merp.account.model.AnlPlan AnlKt2;

  private com.mg.merp.account.model.AccPlan AccKt;

  private com.mg.merp.baiengine.model.Repository SumAlg;

  private com.mg.merp.account.model.AnlPlan AnlDb2;

  private com.mg.merp.reference.model.TaxGroup TaxGroup;

  private com.mg.merp.account.model.EconomicSpecFeature Feature3;

  private com.mg.merp.account.model.EconomicSpecFeature Feature4;

  private com.mg.merp.account.model.AnlPlan AnlKt3;

  private com.mg.merp.account.model.EconomicOperModel EconomicOperModel;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.account.model.AnlPlan AnlDb3;

  private com.mg.merp.account.model.AnlPlan AnlDb1;

  private com.mg.merp.account.model.AnlPlan AnlDb4;

  private com.mg.merp.account.model.AnlPlan AnlKt5;

  private com.mg.merp.account.model.AccPlan AccDb;

  private com.mg.merp.account.model.AnlPlan AnlDb5;

  private com.mg.merp.baiengine.model.Repository QtyAlg;

  private com.mg.merp.account.model.AnlPlan AnlKt1;

  private com.mg.merp.account.model.EconomicSpecFeature Feature5;

  private com.mg.merp.account.model.AnlPlan AnlKt4;

  private com.mg.merp.reference.model.CatalogFolder EntryFolder;

  private java.math.BigDecimal Quantity;

  private java.math.BigDecimal SummaNat;

  private java.math.BigDecimal SummaCur;

  private java.math.BigDecimal CurCource;

  private java.lang.String SummaFormula;

  private java.lang.String QuanFormula;

  private com.mg.merp.reference.model.CatalogType EntryGoodType;

  // Constructors

  /**
   * default constructor
   */
  public EconomicSpecModel() {
  }

  /**
   * constructor with id
   */
  public EconomicSpecModel(java.lang.Integer Id) {
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
  @DataItemName("Account.EconSpec.Feature1")
  public com.mg.merp.account.model.EconomicSpecFeature getFeature1() {
    return this.Feature1;
  }

  public void setFeature1(
      com.mg.merp.account.model.EconomicSpecFeature Feature1) {
    this.Feature1 = Feature1;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.Catalog")
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.Feature2")
  public com.mg.merp.account.model.EconomicSpecFeature getFeature2() {
    return this.Feature2;
  }

  public void setFeature2(
      com.mg.merp.account.model.EconomicSpecFeature Feature2) {
    this.Feature2 = Feature2;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlKt2")
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

  public void setAccKt(com.mg.merp.account.model.AccPlan AccKt) {
    this.AccKt = AccKt;
  }

  /**
   *
   */
  @DataItemName("Account.EconMod.SummaAlg")
  public com.mg.merp.baiengine.model.Repository getSumAlg() {
    return this.SumAlg;
  }

  public void setSumAlg(com.mg.merp.baiengine.model.Repository SumaLg) {
    this.SumAlg = SumaLg;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlDb2")
  public com.mg.merp.account.model.AnlPlan getAnlDb2() {
    return this.AnlDb2;
  }

  public void setAnlDb2(com.mg.merp.account.model.AnlPlan AnlDb2) {
    this.AnlDb2 = AnlDb2;
  }

  /**
   *
   */
  @DataItemName("Account.EconMod.TaxGroup")
  public com.mg.merp.reference.model.TaxGroup getTaxGroup() {
    return this.TaxGroup;
  }

  public void setTaxGroup(com.mg.merp.reference.model.TaxGroup TaxGroup) {
    this.TaxGroup = TaxGroup;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.Feature3")
  public com.mg.merp.account.model.EconomicSpecFeature getFeature3() {
    return this.Feature3;
  }

  public void setFeature3(
      com.mg.merp.account.model.EconomicSpecFeature Feature3) {
    this.Feature3 = Feature3;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.Feature4")
  public com.mg.merp.account.model.EconomicSpecFeature getFeature4() {
    return this.Feature4;
  }

  public void setFeature4(
      com.mg.merp.account.model.EconomicSpecFeature Feature4) {
    this.Feature4 = Feature4;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlKt3")
  public com.mg.merp.account.model.AnlPlan getAnlKt3() {
    return this.AnlKt3;
  }

  public void setAnlKt3(com.mg.merp.account.model.AnlPlan AnlKt3) {
    this.AnlKt3 = AnlKt3;
  }

  /**
   *
   */

  public com.mg.merp.account.model.EconomicOperModel getEconomicOperModel() {
    return this.EconomicOperModel;
  }

  public void setEconomicOperModel(
      com.mg.merp.account.model.EconomicOperModel EconomicOperModel) {
    this.EconomicOperModel = EconomicOperModel;
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
  @DataItemName("Account.EconSpec.AnlDb3")
  public com.mg.merp.account.model.AnlPlan getAnlDb3() {
    return this.AnlDb3;
  }

  public void setAnlDb3(com.mg.merp.account.model.AnlPlan AnlDb3) {
    this.AnlDb3 = AnlDb3;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlDb1")
  public com.mg.merp.account.model.AnlPlan getAnlDb1() {
    return this.AnlDb1;
  }

  public void setAnlDb1(com.mg.merp.account.model.AnlPlan AnlDb1) {
    this.AnlDb1 = AnlDb1;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlDb4")
  public com.mg.merp.account.model.AnlPlan getAnlDb4() {
    return this.AnlDb4;
  }

  public void setAnlDb4(com.mg.merp.account.model.AnlPlan AnlDb4) {
    this.AnlDb4 = AnlDb4;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlKt5")
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

  public void setAccDb(com.mg.merp.account.model.AccPlan AccDb) {
    this.AccDb = AccDb;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlDb5")
  public com.mg.merp.account.model.AnlPlan getAnlDb5() {
    return this.AnlDb5;
  }

  public void setAnlDb5(com.mg.merp.account.model.AnlPlan AnlDb5) {
    this.AnlDb5 = AnlDb5;
  }

  /**
   *
   */
  @DataItemName("Account.EconMod.QuanAlg")
  public com.mg.merp.baiengine.model.Repository getQtyAlg() {
    return this.QtyAlg;
  }

  public void setQtyAlg(com.mg.merp.baiengine.model.Repository QtyaLg) {
    this.QtyAlg = QtyaLg;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlKt1")
  public com.mg.merp.account.model.AnlPlan getAnlKt1() {
    return this.AnlKt1;
  }

  public void setAnlKt1(com.mg.merp.account.model.AnlPlan AnlKt1) {
    this.AnlKt1 = AnlKt1;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.Feature5")
  public com.mg.merp.account.model.EconomicSpecFeature getFeature5() {
    return this.Feature5;
  }

  public void setFeature5(
      com.mg.merp.account.model.EconomicSpecFeature Feature5) {
    this.Feature5 = Feature5;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.AnlKt4")
  public com.mg.merp.account.model.AnlPlan getAnlKt4() {
    return this.AnlKt4;
  }

  public void setAnlKt4(com.mg.merp.account.model.AnlPlan AnlKt4) {
    this.AnlKt4 = AnlKt4;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.CatalogFolder getEntryFolder() {
    return this.EntryFolder;
  }

  public void setEntryFolder(
      com.mg.merp.reference.model.CatalogFolder EntryFolder) {
    this.EntryFolder = EntryFolder;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.Quantity")
  public java.math.BigDecimal getQuantity() {
    return this.Quantity;
  }

  public void setQuantity(java.math.BigDecimal Quantity) {
    this.Quantity = Quantity;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.SummaNat")
  public java.math.BigDecimal getSummaNat() {
    return this.SummaNat;
  }

  public void setSummaNat(java.math.BigDecimal SummaNat) {
    this.SummaNat = SummaNat;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.SummaCur")
  public java.math.BigDecimal getSummaCur() {
    return this.SummaCur;
  }

  public void setSummaCur(java.math.BigDecimal SummaCur) {
    this.SummaCur = SummaCur;
  }

  /**
   *
   */
  @DataItemName("Account.EconSpec.CurCource")
  public java.math.BigDecimal getCurCource() {
    return this.CurCource;
  }

  public void setCurCource(java.math.BigDecimal CurCource) {
    this.CurCource = CurCource;
  }

  /**
   *
   */
  @DataItemName("Account.EconMod.SummaFormula")
  public java.lang.String getSummaFormula() {
    return this.SummaFormula;
  }

  public void setSummaFormula(java.lang.String SummaFormula) {
    this.SummaFormula = SummaFormula;
  }

  /**
   *
   */
  @DataItemName("Account.EconMod.QuanFormula")
  public java.lang.String getQuanFormula() {
    return this.QuanFormula;
  }

  public void setQuanFormula(java.lang.String QuanFormula) {
    this.QuanFormula = QuanFormula;
  }

  /**
   *
   */
  @DataItemName("Account.EconMod.EntryGoodType")
  public com.mg.merp.reference.model.CatalogType getEntryGoodType() {
    return this.EntryGoodType;
  }

  public void setEntryGoodType(
      com.mg.merp.reference.model.CatalogType EntryGoodType) {
    this.EntryGoodType = EntryGoodType;
  }

}