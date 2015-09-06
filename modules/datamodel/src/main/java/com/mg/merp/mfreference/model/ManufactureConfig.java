/*
 * ManufactureConfig.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.mfreference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Производство>"
 *
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: ManufactureConfig.java,v 1.6 2009/03/05 12:30:14 safonov Exp $
 */
public class ManufactureConfig extends com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private int sysClientId;

  private com.mg.merp.reference.model.Measure tickUM;

  private com.mg.merp.reference.model.Measure mainTimeUM;

  private com.mg.merp.reference.model.Currency BaseCurrency;

  private com.mg.merp.reference.model.Currency NatCurrency;

  private com.mg.merp.reference.model.CurrencyRateAuthority CurrencyRateAuthority;

  private com.mg.merp.reference.model.CurrencyRateType CurrencyRateType;

  private java.lang.Integer CurrencyPrec;

  private com.mg.merp.document.model.DocHeadModel InputMaterialModelBf;

  private com.mg.merp.document.model.DocHeadModel InputLaborModelBf;

  private com.mg.merp.document.model.DocHeadModel InputMachineModelBf;

  private com.mg.merp.reference.model.Catalog LaborTime;

  private com.mg.merp.reference.model.Catalog MachineTime;

  private com.mg.merp.reference.model.Catalog MaterialOverhead;

  private com.mg.merp.reference.model.Catalog LaborOverhead;

  private com.mg.merp.reference.model.Catalog MachineOverhead;

  private com.mg.merp.document.model.DocHeadModel PurchaseOrderModelMrpRec;

  private com.mg.merp.document.model.DocHeadModel ScrapMaterialModel;

  private com.mg.merp.document.model.DocHeadModel ScrapLaborModel;

  private com.mg.merp.document.model.DocHeadModel ScrapMachineModel;

  private com.mg.merp.document.model.DocHeadModel VarianceDocumentModel;

  // Constructors

  /**
   * default constructor
   */
  public ManufactureConfig() {
  }

  /**
   * constructor with id
   */
  public ManufactureConfig(int sysClientId) {
    this.sysClientId = sysClientId;
  }

  public com.mg.merp.reference.model.Measure getMainTimeUM() {
    return mainTimeUM;
  }

  public void setMainTimeUM(com.mg.merp.reference.model.Measure mainTimeUM) {
    this.mainTimeUM = mainTimeUM;
  }

  public com.mg.merp.reference.model.Measure getTickUM() {
    return tickUM;
  }

  public void setTickUM(com.mg.merp.reference.model.Measure tickUM) {
    this.tickUM = tickUM;
  }

  /**
   * @return the sysClientId
   */
  public int getSysClientId() {
    return sysClientId;
  }

  /**
   * @param sysClientId the sysClientId to set
   */
  public void setSysClientId(int sysClientId) {
    this.sysClientId = sysClientId;
  }

  /**
   * @return the baseCurrency
   */
  public com.mg.merp.reference.model.Currency getBaseCurrency() {
    return BaseCurrency;
  }

  /**
   * @param baseCurrency the baseCurrency to set
   */
  public void setBaseCurrency(com.mg.merp.reference.model.Currency baseCurrency) {
    BaseCurrency = baseCurrency;
  }

  /**
   * @return the natCurrency
   */
  public com.mg.merp.reference.model.Currency getNatCurrency() {
    return NatCurrency;
  }

  /**
   * @param natCurrency the natCurrency to set
   */
  public void setNatCurrency(com.mg.merp.reference.model.Currency natCurrency) {
    NatCurrency = natCurrency;
  }

  /**
   * @return the currencyRateAuthority
   */
  public com.mg.merp.reference.model.CurrencyRateAuthority getCurrencyRateAuthority() {
    return CurrencyRateAuthority;
  }

  /**
   * @param currencyRateAuthority the currencyRateAuthority to set
   */
  public void setCurrencyRateAuthority(
      com.mg.merp.reference.model.CurrencyRateAuthority currencyRateAuthority) {
    CurrencyRateAuthority = currencyRateAuthority;
  }

  /**
   * @return the currencyRateType
   */
  public com.mg.merp.reference.model.CurrencyRateType getCurrencyRateType() {
    return CurrencyRateType;
  }

  /**
   * @param currencyRateType the currencyRateType to set
   */
  public void setCurrencyRateType(
      com.mg.merp.reference.model.CurrencyRateType currencyRateType) {
    CurrencyRateType = currencyRateType;
  }

  /**
   * @return the currencyPrec
   */
  public java.lang.Integer getCurrencyPrec() {
    return CurrencyPrec;
  }

  /**
   * @param currencyPrec the currencyPrec to set
   */
  public void setCurrencyPrec(java.lang.Integer currencyPrec) {
    CurrencyPrec = currencyPrec;
  }

  /**
   * @return the laborOverhead
   */
  public com.mg.merp.reference.model.Catalog getLaborOverhead() {
    return LaborOverhead;
  }

  /**
   * @param laborOverhead the laborOverhead to set
   */
  public void setLaborOverhead(com.mg.merp.reference.model.Catalog laborOverhead) {
    LaborOverhead = laborOverhead;
  }

  /**
   * @return the laborTime
   */
  public com.mg.merp.reference.model.Catalog getLaborTime() {
    return LaborTime;
  }

  /**
   * @param laborTime the laborTime to set
   */
  public void setLaborTime(com.mg.merp.reference.model.Catalog laborTime) {
    LaborTime = laborTime;
  }

  /**
   * @return the machineOverhead
   */
  public com.mg.merp.reference.model.Catalog getMachineOverhead() {
    return MachineOverhead;
  }

  /**
   * @param machineOverhead the machineOverhead to set
   */
  public void setMachineOverhead(
      com.mg.merp.reference.model.Catalog machineOverhead) {
    MachineOverhead = machineOverhead;
  }

  /**
   * @return the machineTime
   */
  public com.mg.merp.reference.model.Catalog getMachineTime() {
    return MachineTime;
  }

  /**
   * @param machineTime the machineTime to set
   */
  public void setMachineTime(com.mg.merp.reference.model.Catalog machineTime) {
    MachineTime = machineTime;
  }

  /**
   * @return the materialOverhead
   */
  public com.mg.merp.reference.model.Catalog getMaterialOverhead() {
    return MaterialOverhead;
  }

  /**
   * @param materialOverhead the materialOverhead to set
   */
  public void setMaterialOverhead(
      com.mg.merp.reference.model.Catalog materialOverhead) {
    MaterialOverhead = materialOverhead;
  }

  /**
   * @return the varianceDocumentModel
   */
  @DataItemName("Manufacture.VarianceDocModel.Variance")
  public com.mg.merp.document.model.DocHeadModel getVarianceDocumentModel() {
    return VarianceDocumentModel;
  }

  /**
   * @param varianceDocumentModel the varianceDocumentModel to set
   */
  public void setVarianceDocumentModel(
      com.mg.merp.document.model.DocHeadModel varianceDocumentModel) {
    VarianceDocumentModel = varianceDocumentModel;
  }

  /**
   * @return the inputLaborModelBf
   */
  @DataItemName("Manufacture.InputDocModel.Labor")
  public com.mg.merp.document.model.DocHeadModel getInputLaborModelBf() {
    return InputLaborModelBf;
  }

  /**
   * @param inputLaborModelBf the inputLaborModelBf to set
   */
  public void setInputLaborModelBf(
      com.mg.merp.document.model.DocHeadModel inputLaborModelBf) {
    InputLaborModelBf = inputLaborModelBf;
  }

  /**
   * @return the inputMachineModelBf
   */
  @DataItemName("Manufacture.InputDocModel.Machine")
  public com.mg.merp.document.model.DocHeadModel getInputMachineModelBf() {
    return InputMachineModelBf;
  }

  /**
   * @param inputMachineModelBf the inputMachineModelBf to set
   */
  public void setInputMachineModelBf(
      com.mg.merp.document.model.DocHeadModel inputMachineModelBf) {
    InputMachineModelBf = inputMachineModelBf;
  }

  /**
   * @return the inputMaterialModelBf
   */
  @DataItemName("Manufacture.InputDocModel.Material")
  public com.mg.merp.document.model.DocHeadModel getInputMaterialModelBf() {
    return InputMaterialModelBf;
  }

  /**
   * @param inputMaterialModelBf the inputMaterialModelBf to set
   */
  public void setInputMaterialModelBf(
      com.mg.merp.document.model.DocHeadModel inputMaterialModelBf) {
    InputMaterialModelBf = inputMaterialModelBf;
  }

  /**
   * @return the scrapLaborModel
   */
  @DataItemName("Manufacture.ScrapDocumentModel.Labor")
  public com.mg.merp.document.model.DocHeadModel getScrapLaborModel() {
    return ScrapLaborModel;
  }

  /**
   * @param scrapLaborModel the scrapLaborModel to set
   */
  public void setScrapLaborModel(
      com.mg.merp.document.model.DocHeadModel scrapLaborModel) {
    ScrapLaborModel = scrapLaborModel;
  }

  /**
   * @return the scrapMachineModel
   */
  @DataItemName("Manufacture.ScrapDocumentModel.Machine")
  public com.mg.merp.document.model.DocHeadModel getScrapMachineModel() {
    return ScrapMachineModel;
  }

  /**
   * @param scrapMachineModel the scrapMachineModel to set
   */
  public void setScrapMachineModel(
      com.mg.merp.document.model.DocHeadModel scrapMachineModel) {
    ScrapMachineModel = scrapMachineModel;
  }

  /**
   * @return the scrapMaterialModel
   */
  @DataItemName("Manufacture.ScrapDocumentModel.Material")
  public com.mg.merp.document.model.DocHeadModel getScrapMaterialModel() {
    return ScrapMaterialModel;
  }

  /**
   * @param scrapMaterialModel the scrapMaterialModel to set
   */
  public void setScrapMaterialModel(
      com.mg.merp.document.model.DocHeadModel scrapMaterialModel) {
    ScrapMaterialModel = scrapMaterialModel;
  }

  /**
   * @return the purchaseOrderModelMrpRec
   */
  @DataItemName("Manufacture.OrderDocModel.Order")
  public com.mg.merp.document.model.DocHeadModel getPurchaseOrderModelMrpRec() {
    return PurchaseOrderModelMrpRec;
  }

  /**
   * @param purchaseOrderModelMrpRec the purchaseOrderModelMrpRec to set
   */
  public void setPurchaseOrderModelMrpRec(
      com.mg.merp.document.model.DocHeadModel purchaseOrderModelMrpRec) {
    PurchaseOrderModelMrpRec = purchaseOrderModelMrpRec;
  }

}