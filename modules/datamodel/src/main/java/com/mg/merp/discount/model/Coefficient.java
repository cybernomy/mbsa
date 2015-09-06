/*
 * Coefficient.java
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
package com.mg.merp.discount.model;

import com.mg.framework.api.annotations.DataItemName;

/**
 * @author hbm2java
 * @version $Id: Coefficient.java,v 1.4 2006/08/30 11:59:51 leonova Exp $
 */
public class Coefficient extends
    com.mg.framework.service.PersistentObjectHibernate implements
    java.io.Serializable {

  // Fields

  private java.lang.Integer Id;

  private com.mg.merp.reference.model.Catalog Catalog;

  private com.mg.merp.reference.model.CatalogFolder CatalogFolder;

  private com.mg.merp.discount.model.Card Card;

  private com.mg.merp.core.model.SysClient SysClient;

  private java.math.BigDecimal Coefficient;

  // Constructors

  /**
   * default constructor
   */
  public Coefficient() {
  }

  /**
   * constructor with id
   */
  public Coefficient(java.lang.Integer Id) {
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
  @DataItemName("Discount.GlobalCoeff.Catalog")
  public com.mg.merp.reference.model.Catalog getCatalog() {
    return this.Catalog;
  }

  public void setCatalog(com.mg.merp.reference.model.Catalog Catalog) {
    this.Catalog = Catalog;
  }

  /**
   *
   */
  @DataItemName("Discount.GlobalCoeff.CatalogFolder")
  public com.mg.merp.reference.model.CatalogFolder getCatalogFolder() {
    return this.CatalogFolder;
  }

  public void setCatalogFolder(
      com.mg.merp.reference.model.CatalogFolder Catalogfolder) {
    this.CatalogFolder = Catalogfolder;
  }

  /**
   *
   */

  public com.mg.merp.discount.model.Card getCard() {
    return this.Card;
  }

  public void setCard(com.mg.merp.discount.model.Card DisCard) {
    this.Card = DisCard;
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
  @DataItemName("Discount.GlobalCoeff.Coefficient")
  public java.math.BigDecimal getCoefficient() {
    return this.Coefficient;
  }

  public void setCoefficient(java.math.BigDecimal Coefficient) {
    this.Coefficient = Coefficient;
  }

}