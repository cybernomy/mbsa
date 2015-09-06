/*
 * StockBatch.java
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
import com.mg.merp.reference.model.Country;
import com.mg.merp.reference.model.CustomsDeclaration;

/**
 * @author hbm2java
 * @version $Id: StockBatch.java,v 1.6 2008/08/27 09:36:21 sharapov Exp $
 */
@DataItemName("Warehouse.StockBatch")
public class StockBatch extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.warehouse.model.StockCard StockCard;

  private com.mg.merp.core.model.SysClient SysClient;

  private com.mg.merp.warehouse.model.StockKind StockKind;

  private com.mg.merp.reference.model.Contractor Contractor;

  private com.mg.merp.reference.model.Contractor Owner;

  private java.math.BigDecimal BeginQuan;

  private java.math.BigDecimal EndQuan;

  private java.math.BigDecimal PriceNat;

  private java.math.BigDecimal PriceCur;

  private java.lang.String CurrencyCode;

  private java.lang.String DocType;

  private java.lang.String DocNumber;

  private java.util.Date DocDate;

  private java.util.Date CreateDate;

  private java.util.Date BestBefore;

  private java.lang.String NumberLot;

  private java.lang.String VendorLot;

  private java.lang.String Certificate;

  private java.lang.String Comment;

  private java.math.BigDecimal BeginQuan2;

  private java.math.BigDecimal EndQuan2;

  private CustomsDeclaration customsDeclaration;

  private Country countryOfOrigin;

  private java.util.Set SetOfWhSerialNum;

  private java.util.Set SetOfWhBinLocationDetail;

  // Constructors

  /**
   * default constructor
   */
  public StockBatch() {
  }

  /**
   * constructor with id
   */
  public StockBatch(java.lang.Integer Id) {
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

  public com.mg.merp.warehouse.model.StockCard getStockCard() {
    return this.StockCard;
  }

  public void setStockCard(com.mg.merp.warehouse.model.StockCard StockCard) {
    this.StockCard = StockCard;
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

  public com.mg.merp.warehouse.model.StockKind getStockKind() {
    return this.StockKind;
  }

  public void setStockKind(com.mg.merp.warehouse.model.StockKind StockKind) {
    this.StockKind = StockKind;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.Contractor")
  public com.mg.merp.reference.model.Contractor getContractor() {
    return this.Contractor;
  }

  public void setContractor(com.mg.merp.reference.model.Contractor Contractor) {
    this.Contractor = Contractor;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.Owner")
  public com.mg.merp.reference.model.Contractor getOwner() {
    return this.Owner;
  }

  public void setOwner(com.mg.merp.reference.model.Contractor Owner) {
    this.Owner = Owner;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.BeginQuan")
  public java.math.BigDecimal getBeginQuan() {
    return this.BeginQuan;
  }

  public void setBeginQuan(java.math.BigDecimal BeginQuan) {
    this.BeginQuan = BeginQuan;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.EndQuan")
  public java.math.BigDecimal getEndQuan() {
    return this.EndQuan;
  }

  public void setEndQuan(java.math.BigDecimal EndQuan) {
    this.EndQuan = EndQuan;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.PriceNat")
  public java.math.BigDecimal getPriceNat() {
    return this.PriceNat;
  }

  public void setPriceNat(java.math.BigDecimal PriceNat) {
    this.PriceNat = PriceNat;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.PriceCur")
  public java.math.BigDecimal getPriceCur() {
    return this.PriceCur;
  }

  public void setPriceCur(java.math.BigDecimal PriceCur) {
    this.PriceCur = PriceCur;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.CurrencyCode")
  public java.lang.String getCurrencyCode() {
    return this.CurrencyCode;
  }

  public void setCurrencyCode(java.lang.String CurrencyCode) {
    this.CurrencyCode = CurrencyCode;
  }

  /**
   *
   */

  @DataItemName("Warehouse.StockBatch.DocType")
  public java.lang.String getDocType() {
    return this.DocType;
  }

  public void setDocType(java.lang.String DocType) {
    this.DocType = DocType;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.DocNumber")
  public java.lang.String getDocNumber() {
    return this.DocNumber;
  }

  public void setDocNumber(java.lang.String DocNumber) {
    this.DocNumber = DocNumber;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.DocDate")
  public java.util.Date getDocDate() {
    return this.DocDate;
  }

  public void setDocDate(java.util.Date DocDate) {
    this.DocDate = DocDate;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.CreateDate")
  public java.util.Date getCreateDate() {
    return this.CreateDate;
  }

  public void setCreateDate(java.util.Date CreateDate) {
    this.CreateDate = CreateDate;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.BestBefore")
  public java.util.Date getBestBefore() {
    return this.BestBefore;
  }

  public void setBestBefore(java.util.Date BestBefore) {
    this.BestBefore = BestBefore;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.NumberLot")
  public java.lang.String getNumberLot() {
    return this.NumberLot;
  }

  public void setNumberLot(java.lang.String NumberLot) {
    this.NumberLot = NumberLot;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.VendorLot")
  public java.lang.String getVendorLot() {
    return this.VendorLot;
  }

  public void setVendorLot(java.lang.String VendorLot) {
    this.VendorLot = VendorLot;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.Certificate")
  public java.lang.String getCertificate() {
    return this.Certificate;
  }

  public void setCertificate(java.lang.String Certificate) {
    this.Certificate = Certificate;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.Comment")
  public java.lang.String getComment() {
    return this.Comment;
  }

  public void setComment(java.lang.String Comment) {
    this.Comment = Comment;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.BeginQuan2")
  public java.math.BigDecimal getBeginQuan2() {
    return this.BeginQuan2;
  }

  public void setBeginQuan2(java.math.BigDecimal BeginQuan2) {
    this.BeginQuan2 = BeginQuan2;
  }

  /**
   *
   */
  @DataItemName("Warehouse.StockBatch.EndQuan2")
  public java.math.BigDecimal getEndQuan2() {
    return this.EndQuan2;
  }

  public void setEndQuan2(java.math.BigDecimal EndQuan2) {
    this.EndQuan2 = EndQuan2;
  }

  /**
   *
   */

  public java.util.Set getSetOfWhSerialNum() {
    return this.SetOfWhSerialNum;
  }

  public void setSetOfWhSerialNum(java.util.Set SetOfWhSerialNum) {
    this.SetOfWhSerialNum = SetOfWhSerialNum;
  }

  /**
   *
   */

  public java.util.Set getSetOfWhBinLocationDetail() {
    return this.SetOfWhBinLocationDetail;
  }

  public void setSetOfWhBinLocationDetail(
      java.util.Set SetOfWhBinLocationDetail) {
    this.SetOfWhBinLocationDetail = SetOfWhBinLocationDetail;
  }

  /**
   * @return the customsDeclaration
   */
  @DataItemName("Reference.CD") //$NON-NLS-1$
  public CustomsDeclaration getCustomsDeclaration() {
    return this.customsDeclaration;
  }

  /**
   * @param customsDeclaration the customsDeclaration to set
   */
  public void setCustomsDeclaration(CustomsDeclaration customsDeclaration) {
    this.customsDeclaration = customsDeclaration;
  }

  /**
   * @return the countryOfOrigin
   */
  @DataItemName("Reference.CountryOfOrigin") //$NON-NLS-1$
  public Country getCountryOfOrigin() {
    return this.countryOfOrigin;
  }

  /**
   * @param countryOfOrigin the countryOfOrigin to set
   */
  public void setCountryOfOrigin(Country countryOfOrigin) {
    this.countryOfOrigin = countryOfOrigin;
  }

}