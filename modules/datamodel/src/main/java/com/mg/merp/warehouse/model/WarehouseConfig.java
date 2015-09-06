/*
 * WarehouseConfig.java
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
package com.mg.merp.warehouse.model;

/**
 * Модель бизнес-компонента "Конфигурация модуля <Склады, снабжение, сбыт>"
 *
 * @author hbm2java
 * @author Artem V. Sharapov
 * @version $Id: WarehouseConfig.java,v 1.4 2007/01/13 13:35:22 sharapov Exp $
 */
public class WarehouseConfig extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private int sysClientId;
  private com.mg.merp.reference.model.Currency baseCurrency;
  private com.mg.merp.reference.model.Currency natCurrency;
  private com.mg.merp.reference.model.CurrencyRateType currencyRateType;
  private com.mg.merp.reference.model.CurrencyRateAuthority currencyRateAuthority;
  private java.lang.Integer currencyPrec;

  // Constructors

  /**
   * default constructor
   */
  public WarehouseConfig() {
  }

  /**
   * constructor with id
   */
  public WarehouseConfig(int sysClientId) {
    this.sysClientId = sysClientId;
  }

  public com.mg.merp.reference.model.Currency getBaseCurrency() {
    return baseCurrency;
  }

  public void setBaseCurrency(com.mg.merp.reference.model.Currency baseCurrency) {
    this.baseCurrency = baseCurrency;
  }

  public java.lang.Integer getCurrencyPrec() {
    return currencyPrec;
  }

  public void setCurrencyPrec(java.lang.Integer currencyPrec) {
    this.currencyPrec = currencyPrec;
  }

  public com.mg.merp.reference.model.CurrencyRateAuthority getCurrencyRateAuthority() {
    return currencyRateAuthority;
  }

  public void setCurrencyRateAuthority(
      com.mg.merp.reference.model.CurrencyRateAuthority currencyRateAuthority) {
    this.currencyRateAuthority = currencyRateAuthority;
  }

  public com.mg.merp.reference.model.CurrencyRateType getCurrencyRateType() {
    return currencyRateType;
  }

  public void setCurrencyRateType(
      com.mg.merp.reference.model.CurrencyRateType currencyRateType) {
    this.currencyRateType = currencyRateType;
  }

  public com.mg.merp.reference.model.Currency getNatCurrency() {
    return natCurrency;
  }

  public void setNatCurrency(com.mg.merp.reference.model.Currency natCurrency) {
    this.natCurrency = natCurrency;
  }

  public int getSysClientId() {
    return sysClientId;
  }

  public void setSysClientId(int sysClientId) {
    this.sysClientId = sysClientId;
  }

}