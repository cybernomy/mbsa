/*
 * Packing.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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
package com.mg.merp.reference.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * Модель бизнес-компонента "Упаковка товара"
 *
 * @author Artem V. Sharapov
 * @version $Id: Packing.java,v 1.6 2007/11/19 11:06:55 sharapov Exp $
 */
@DataItemName("Reference.Packing") //$NON-NLS-1$
public class Packing extends com.mg.framework.service.PersistentObjectHibernate implements java.io.Serializable {

  // Fields

  private int Id;

  private com.mg.merp.reference.model.Measure Measure;
  private short Priority;

  private java.math.BigDecimal Weight;
  private com.mg.merp.reference.model.Measure WeightMeasure;

  private java.math.BigDecimal Volume;
  private com.mg.merp.reference.model.Measure VolumeMeasure;

  private com.mg.merp.reference.model.Catalog Catalog;

  private java.math.BigDecimal quantityMeasure1;
  private java.math.BigDecimal quantityMeasure2;

  private com.mg.merp.core.model.SysClient SysClient;


  // Constructors

  /**
   * default constructor
   */
  public Packing() {
  }

  /**
   * constructor with id
   */
  public Packing(int Id) {
    this.Id = Id;
  }

  // Property accessors

  /**
   *
   */
  @DataItemName("ID") //$NON-NLS-1$
  public int getId() {
    return this.Id;
  }

  public void setId(int Id) {
    this.Id = Id;
  }

  /**
   *
   */
  @DataItemName("Reference.Packing.Measure") //$NON-NLS-1$
  public com.mg.merp.reference.model.Measure getMeasure() {
    return this.Measure;
  }

  public void setMeasure(com.mg.merp.reference.model.Measure Measure) {
    this.Measure = Measure;
  }

  /**
   *
   */
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**
   *
   */
  @DataItemName("Reference.Packing.WeightMeasure") //$NON-NLS-1$
  public com.mg.merp.reference.model.Measure getWeightMeasure() {
    return this.WeightMeasure;
  }

  public void setWeightMeasure(com.mg.merp.reference.model.Measure WeightMeasure) {
    this.WeightMeasure = WeightMeasure;
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
  @DataItemName("Reference.Packing.VolumeMeasure") //$NON-NLS-1$
  public com.mg.merp.reference.model.Measure getVolumeMeasure() {
    return this.VolumeMeasure;
  }

  public void setVolumeMeasure(com.mg.merp.reference.model.Measure VolumeMeasure) {
    this.VolumeMeasure = VolumeMeasure;
  }

  /**
   *
   */
  @DataItemName("Reference.Catalog.Packing.Priority") //$NON-NLS-1$
  public short getPriority() {
    return this.Priority;
  }

  public void setPriority(short Priority) {
    this.Priority = Priority;
  }

  /**
   *
   */
  @DataItemName("Reference.Packing.Weight") //$NON-NLS-1$
  public java.math.BigDecimal getWeight() {
    return this.Weight;
  }

  public void setWeight(java.math.BigDecimal Weight) {
    this.Weight = Weight;
  }

  /**
   *
   */
  @DataItemName("Reference.Packing.Volume") //$NON-NLS-1$
  public java.math.BigDecimal getVolume() {
    return this.Volume;
  }

  public void setVolume(java.math.BigDecimal Volume) {
    this.Volume = Volume;
  }

  /**
   * Получить количество в основной ЕИ в упаковке
   *
   * @return количество в основной ЕИ в упаковке
   */
  @DataItemName("Reference.Packing.QuantityMeasure1") //$NON-NLS-1$
  public java.math.BigDecimal getQuantityMeasure1() {
    return this.quantityMeasure1;
  }

  /**
   * Установить количество в основной ЕИ в упаковке
   *
   * @param quantityMeasure1 - количество в основной ЕИ в упаковке
   */
  public void setQuantityMeasure1(java.math.BigDecimal quantityMeasure1) {
    this.quantityMeasure1 = quantityMeasure1;
  }

  /**
   * Получить количество в дополнительной ЕИ в упаковке
   *
   * @return количество в дополнительной ЕИ в упаковке
   */
  @DataItemName("Reference.Packing.QuantityMeasure2") //$NON-NLS-1$
  public java.math.BigDecimal getQuantityMeasure2() {
    return this.quantityMeasure2;
  }

  /**
   * Установить количество в дополнительной ЕИ в упаковке
   *
   * @param quantityMeasure2 - количество в дополнительной ЕИ в упаковке
   */
  public void setQuantityMeasure2(java.math.BigDecimal quantityMeasure2) {
    this.quantityMeasure2 = quantityMeasure2;
  }

}